package org.lobsterZelda.caches.staticData;

import org.lobsterZelda.constants.Constants;
import org.lobsterZelda.models.EntranceGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.security.InvalidParameterException;

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

        namedParameterJdbcTemplate.query(query.toString(), resultSet -> {
            while (resultSet.next())
            {

            }
            return null;
        });

    }

}
