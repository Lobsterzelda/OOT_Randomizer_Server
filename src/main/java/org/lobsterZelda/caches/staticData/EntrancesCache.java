package org.lobsterZelda.caches.staticData;

import org.lobsterZelda.constants.Constants;
import org.lobsterZelda.models.Entrance;
import org.lobsterZelda.models.EntranceGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EntrancesCache
{
    @Autowired
    private static NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //These maps cache all of the possible combinations of maps, so we can load them right away for the user.
    // In general, the conditions that maps of entrance identifiers/names are seperated on are:
    // 1. Wallmasters randomized vs. not randomized.
    // 2. Void-points randomized vs. not randomized.
    // 3. Includes OOT, Includes MM, or includes Both.
    // These are seperated out to avoid the maps containing huge amounts of unused entrances that waste space and slow down fastest-path calculations.

    // These 4 graphs correspond to OOT-only seeds.
    private static final EntranceGraph ootOnly_WallmastersOff_VoidPointsOff_Graph = new EntranceGraph();
    private static final EntranceGraph ootOnly_WallmastersOff_VoidPointsOn_Graph = new EntranceGraph();
    private static final EntranceGraph ootOnly_WallmastersOn_VoidPointsOff_Graph = new EntranceGraph();
    private static final EntranceGraph ootOnly_WallmastersOn_VoidPointsOn_Graph = new EntranceGraph();

    // These 4 graphs correspond to MM-only seeds.
    private static final EntranceGraph mmOnly_WallmastersOff_VoidPointsOff_Graph = new EntranceGraph();
    private static final EntranceGraph mmOnly_WallmastersOff_VoidPointsOn_Graph = new EntranceGraph();
    private static final EntranceGraph mmOnly_WallmastersOn_VoidPointsOff_Graph = new EntranceGraph();
    private static final EntranceGraph mmOnly_WallmastersOn_VoidPointsOn_Graph = new EntranceGraph();

    // These 4 graphs correspond to OOT-MM combo randos.
    private static final EntranceGraph ootAndMm_WallmastersOff_VoidPointsOff_Graph = new EntranceGraph();
    private static final EntranceGraph ootAndMm_WallmastersOff_VoidPointsOn_Graph = new EntranceGraph();
    private static final EntranceGraph ootAndMm_WallmastersOn_VoidPointsOff_Graph = new EntranceGraph();
    private static final EntranceGraph ootAndMm_WallmastersOn_VoidPointsOn_Graph = new EntranceGraph();

    // This is the only public method that sets up the cache when the app starts up.
    public static void populateCache()
    {
       initializeGraph(ootOnly_WallmastersOff_VoidPointsOff_Graph,true, false, false, false);
       initializeGraph(ootOnly_WallmastersOff_VoidPointsOn_Graph, true, false, false, true);
       initializeGraph(ootOnly_WallmastersOn_VoidPointsOff_Graph, true, false, true, false);
       initializeGraph(ootOnly_WallmastersOn_VoidPointsOn_Graph, true, false, true, true);

       initializeGraph(mmOnly_WallmastersOff_VoidPointsOff_Graph, false, true, false, false);
       initializeGraph(mmOnly_WallmastersOff_VoidPointsOn_Graph, false, true, false, true);
       initializeGraph(mmOnly_WallmastersOn_VoidPointsOff_Graph, false, true, true, false);
       initializeGraph(mmOnly_WallmastersOn_VoidPointsOn_Graph, false, true, true, true);

       initializeGraph(ootAndMm_WallmastersOff_VoidPointsOff_Graph, true, true, false, false);
       initializeGraph(ootAndMm_WallmastersOff_VoidPointsOn_Graph, true, true, false, true);
       initializeGraph(ootAndMm_WallmastersOn_VoidPointsOff_Graph, true, true, true, false);
       initializeGraph(ootAndMm_WallmastersOn_VoidPointsOn_Graph, true, true, true, true);
    }

    private static void initializeGraph(EntranceGraph entranceGraph, boolean includesOOT, boolean includesMM, boolean wallmastersRandomized, boolean voidPointsRandomized)
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

                if (nextEntrance.getIsAMap())
                    idOfMapToEntranceObject.put(nextEntrance.getEntranceID(), nextEntrance);

                entranceGraph.getIdToEntranceMap().put(nextEntrance.getMapEntranceID(), nextEntrance);
                entranceGraph.getNameToEntranceMap().put(nextEntrance.getEntranceName(), nextEntrance);
                entranceGraph.getIdToConnectedEntrancesAdjacencyList().put(nextEntrance.getEntranceID(), new ArrayList<>());
            }
            return null;
        });

    }

}
