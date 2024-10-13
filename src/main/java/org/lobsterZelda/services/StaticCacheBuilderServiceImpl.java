package org.lobsterZelda.services;

import jakarta.annotation.PostConstruct;
import org.lobsterZelda.caches.staticData.EntrancesCache;
import org.lobsterZelda.caches.staticData.JWTSecretKeyCache;
import org.lobsterZelda.constants.Constants;
import org.lobsterZelda.models.Entrance;
import org.lobsterZelda.models.EntranceGraph;
import org.lobsterZelda.models.WeightedVertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class StaticCacheBuilderServiceImpl implements StaticCacheBuilderService {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    Environment env;

    @Autowired
    public StaticCacheBuilderServiceImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    @PostConstruct
    public void buildAllCaches()
    {
        populateJWTSecretKeyCache();
        populateEntrancesCache();
    }

    private static final String JWT_VERSION_QUERY = "SELECT * FROM " + Constants.JWT_VERSION_TABLE_NAME;

    // temp class only used within the method which populates the JWTSecretKeyCache
    private static class TempJwtVersionData
    {
        public Integer versionID;
        public Long creationDate;
        public Long expirationDate;
    }
    private void populateJWTSecretKeyCache()
    {
        Map<Integer, TempJwtVersionData> jwtVersionData = new HashMap<>();
        final Integer[] highestVersion = {-1};

        namedParameterJdbcTemplate.query(JWT_VERSION_QUERY, (ResultSetExtractor<Object>) resultSet -> {
            while (resultSet.next())
            {
                TempJwtVersionData newVersionObj = new TempJwtVersionData();
                newVersionObj.versionID = resultSet.getInt(Constants.JWT_VERSION_ID_COLUMN_NAME);
                if (newVersionObj.versionID > highestVersion[0])
                    highestVersion[0] = newVersionObj.versionID;
                newVersionObj.creationDate = resultSet.getDate(Constants.JWT_VERSION_CREATION_DATE_COLUMN_NAME).getTime() / 1000L;
                newVersionObj.expirationDate = resultSet.getDate(Constants.JWT_VERSION_EXPIRATION_DATE_COLUMN_NAME).getTime() / 1000L;
                jwtVersionData.put(newVersionObj.versionID, newVersionObj);
            }
            return null;
        });

        if (jwtVersionData.isEmpty())
        {
            throw new InvalidParameterException("Error: JWT Version table was empty. Need at least one entry to start the application!");
        }

        Long currentTime = Instant.now().getEpochSecond();

        TempJwtVersionData highestVersionJWT = jwtVersionData.get(highestVersion[0]);
        if (currentTime < highestVersionJWT.creationDate || currentTime >= highestVersionJWT.expirationDate)
        {
            throw new InvalidParameterException("Error: Highest JWT version was not inside the time range between creation date and expiration date. This is the JWT version that is used to create new seeds, so this may not be expired! Please create a new JWT and associated version for the app");
        }
        JWTSecretKeyCache.setMostRecentJWTVersionNumber(highestVersion[0]);

        for (Integer versionNum : jwtVersionData.keySet())
        {
            String base64SecretKey = env.getProperty(Constants.JWT_VERSION_PROPERTY_PREFIX + String.valueOf(versionNum));
            if (base64SecretKey == null || base64SecretKey.isBlank())
            {
                throw new InvalidParameterException("Error: JWT secret key for version " + versionNum + " was not found.");
            }

            byte[] secretKeyBytes = Base64.getDecoder().decode(base64SecretKey.getBytes(StandardCharsets.UTF_8));
            if (secretKeyBytes.length != Constants.JWT_SECRET_KEY_LENGTH_IN_BYTES)
                throw new InvalidParameterException("Error: Secret key did not have the required length!");
            JWTSecretKeyCache.addSecretForVersionToCache(versionNum, secretKeyBytes);
        }
    }

    private void populateEntrancesCache()
    {
        initializeGraph(EntrancesCache.ootOnly_WallmastersOff_VoidPointsOff_Graph, true, false, false, false);
        initializeGraph(EntrancesCache.ootOnly_WallmastersOff_VoidPointsOn_Graph, true, false, false, true);
        initializeGraph(EntrancesCache.ootOnly_WallmastersOn_VoidPointsOff_Graph, true, false, true, false);
        initializeGraph(EntrancesCache.ootOnly_WallmastersOn_VoidPointsOn_Graph, true, false, true, true);

        initializeGraph(EntrancesCache.mmOnly_WallmastersOff_VoidPointsOff_Graph, false, true, false, false);
        initializeGraph(EntrancesCache.mmOnly_WallmastersOff_VoidPointsOn_Graph, false, true, false, true);
        initializeGraph(EntrancesCache.mmOnly_WallmastersOn_VoidPointsOff_Graph, false, true, true, false);
        initializeGraph(EntrancesCache.mmOnly_WallmastersOn_VoidPointsOn_Graph, false, true, true, true);

        initializeGraph(EntrancesCache.ootAndMm_WallmastersOff_VoidPointsOff_Graph, true, true, false, false);
        initializeGraph(EntrancesCache.ootAndMm_WallmastersOff_VoidPointsOn_Graph, true, true, false, true);
        initializeGraph(EntrancesCache.ootAndMm_WallmastersOn_VoidPointsOff_Graph, true, true, true, false);
        initializeGraph(EntrancesCache.ootAndMm_WallmastersOn_VoidPointsOn_Graph, true, true, true, true);
    }

    private void initializeGraph(EntranceGraph entranceGraph, boolean includesOOT, boolean includesMM, boolean wallmastersRandomized, boolean voidPointsRandomized)
    {
        final StringBuilder query = new StringBuilder("SELECT * FROM ").append(Constants.STATIC_ENTRANCE_DATA_TABLE_NAME).append(" ");
        boolean addedWhereClause = false;

        // We only need to add filtering when either OOT locations should be excluded or MM locations should be excluded.
        // Otherwise, we add the locations from both games.
        if (!includesOOT || !includesMM)
        {
            if (!includesOOT && !includesMM)
            {
                throw new InvalidParameterException("Error: at least one of OOT and MM must be included in a graph.");
            }

            query.append(" WHERE isOOTEntrance = ").append(includesOOT ? "true " : "false ");
            addedWhereClause = true;
        }

        if (!wallmastersRandomized) {
            if (!addedWhereClause) {
                query.append(" WHERE ");
                addedWhereClause = true;
            } else
            {
                query.append(" AND ");
            }
            query.append(" isWallmasterWarp = false ");
        }

        if (!voidPointsRandomized)
        {
            if (!addedWhereClause) {
                query.append(" WHERE ");
                addedWhereClause = true;
            }
            else
            {
                query.append(" AND ");
            }
            query.append(" isVoidPointWarp = false");
        }

        // This map contains only Entrances which represent an area map. The keys of the map are the ID of the map, and the value is their associated EntranceObject
        Map<Integer, Entrance> idOfMapToEntranceObject = new HashMap<>();

        namedParameterJdbcTemplate.query(query.toString(), resultSet -> {
            while (resultSet.next())
            {
                Entrance nextEntrance = new Entrance();

                nextEntrance.setEntranceID(resultSet.getInt(Constants.ENTRANCE_ID_COLUMN_NAME));
                nextEntrance.setMapEntranceID(resultSet.getInt(Constants.MAP_ENTRANCE_ID_COLUMN_NAME));
                nextEntrance.setMapForDisplayEntranceID(resultSet.getInt(Constants.MAP_FOR_DISPLAY_ENTRANCE_ID_COLUMN_NAME));
                nextEntrance.setEntranceName(resultSet.getString(Constants.ENTRANCE_NAME_COLUMN_NAME));
                nextEntrance.setIsOOTOwlEntrance(resultSet.getBoolean(Constants.IS_OOT_OWL_ENTRANCE_COLUMN_NAME));
                nextEntrance.setIsOOTWarpSong(resultSet.getBoolean(Constants.IS_OOT_WARP_SONG_COLUMN_NAME));
                nextEntrance.setIsOOTChildSaveWarp(resultSet.getBoolean(Constants.IS_OOT_CHILD_SAVE_WARP_COLUMN_NAME));
                nextEntrance.setIsOOTAdultSaveWarp(resultSet.getBoolean(Constants.IS_OOT_ADULT_SAVE_WARP_COLUMN_NAME));
                nextEntrance.setIsMMSaveWarp(resultSet.getBoolean(Constants.IS_MM_SAVE_WARP_COLUMN_NAME));
                nextEntrance.setIsMMSongOfSoaringWarp(resultSet.getBoolean(Constants.IS_MM_SONG_OF_SOARING_WARP_COLUMN_NAME));
                nextEntrance.setIsWallmasterWarp(resultSet.getBoolean(Constants.IS_WALLMASTER_WARP_COLUMN_NAME));
                nextEntrance.setIsVoidPointWarp(resultSet.getBoolean(Constants.IS_VOID_POINT_WARP_COLUMN_NAME));
                nextEntrance.setIsOOTEntrance(resultSet.getBoolean(Constants.IS_OOT_ENTRANCE_COLUMN_NAME));
                nextEntrance.setIsInDungeon(resultSet.getBoolean(Constants.IS_IN_DUNGEON_COLUMNN_NAME));
                nextEntrance.setIsBossRoom(resultSet.getBoolean(Constants.IS_BOSS_ROOM_COLUMN_NAME));
                nextEntrance.setIsInGrotto(resultSet.getBoolean(Constants.IS_IN_GROTTO_COLUMN_NAME));
                nextEntrance.setIsInHouse(resultSet.getBoolean(Constants.IS_IN_HOUSE_COLUMN_NAME));
                nextEntrance.setIsAMap(resultSet.getBoolean(Constants.IS_A_MAP_COLUMN_NAME));
                nextEntrance.setIsOOTToMMEntrance(resultSet.getBoolean(Constants.IS_OOT_TO_MM_ENTRANCE_COLUMN_NAME));
                nextEntrance.setIsChildOnlyEntrance(resultSet.getBoolean(Constants.IS_CHILD_ONLY_ENTRANCE_COLUMN_NAME));
                nextEntrance.setIsAdultOnlyEntrance(resultSet.getBoolean(Constants.IS_ADULT_ONLY_ENTRANCE_COLUMN_NAME));
                nextEntrance.setMapPercentFromLeftToInBounds(resultSet.getFloat(Constants.MAP_PERCENT_FROM_LEFT_COLUMN_NAME));
                nextEntrance.setMapPercentFromTopToInBounds(resultSet.getFloat(Constants.MAP_PERCENT_FROM_TOP_COLUMN_NAME));
                nextEntrance.setMapPercentFromRightToInBounds(resultSet.getFloat(Constants.MAP_PERCENT_FROM_RIGHT_COLUMN_NAME));
                nextEntrance.setMapPercentFromDownToInBound(resultSet.getFloat(Constants.MAP_PERCENT_FROM_BOTTOM_COLUMN_NAME));
                nextEntrance.setPercentFromLeftEdgeOfMap(resultSet.getFloat(Constants.PERCENT_FROM_LEFT_EDGE_OF_MAP_COLUMN_NAME));
                nextEntrance.setPercentFromTopEdgeOfMap(resultSet.getFloat(Constants.PERCENT_FROM_TOP_EDGE_OF_MAP_COLUMN_NAME));

                if (nextEntrance.getIsAMap())
                    idOfMapToEntranceObject.put(nextEntrance.getEntranceID(), nextEntrance);

                entranceGraph.getIdToEntranceMap().put(nextEntrance.getMapEntranceID(), nextEntrance);
                entranceGraph.getNameToEntranceMap().put(nextEntrance.getEntranceName(), nextEntrance);
                entranceGraph.getIdToConnectedEntrancesAdjacencyList().put(nextEntrance.getEntranceID(), new ArrayList<>());
            }

            // Now, connecting all mapLocations with each of the entrances inside of the map (with a weight of 0 for each connection).
            for (Integer mapId : idOfMapToEntranceObject.keySet())
            {
                Entrance mapEntrance = idOfMapToEntranceObject.get(mapId);
                for (Integer entranceId : entranceGraph.getIdToEntranceMap().keySet())
                {
                    Entrance entranceObj = entranceGraph.getIdToEntranceMap().get(entranceId);
                    if (mapId.equals(entranceObj.getMapEntranceID()))
                    {
                        entranceGraph.getIdToConnectedEntrancesAdjacencyList().get(mapId).add(new WeightedVertex(entranceId, 0));
                        entranceGraph.getIdToConnectedEntrancesAdjacencyList().get(entranceId).add(new WeightedVertex(mapId, 0));
                    }
                }
            }
        });
    }
}
