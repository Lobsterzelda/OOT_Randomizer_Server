package org.lobsterZelda.caches.staticData;

import org.lobsterZelda.models.EntranceGraph;

import java.security.InvalidParameterException;

public class EntrancesCache
{
    //These maps cache all of the possible combinations of maps, so we can load them right away for the user.
    // In general, the conditions that maps of entrance identifiers/names are separated on are:
    // 1. Wallmasters randomized vs. not randomized.
    // 2. Void-points randomized vs. not randomized.
    // 3. Includes OOT, Includes MM, or includes Both.
    // These are separated out to avoid the maps containing huge amounts of unused entrances that waste space and slow down fastest-path calculations.

    // These 4 graphs correspond to OOT-only seeds.
    public static final EntranceGraph ootOnly_WallmastersOff_VoidPointsOff_Graph = new EntranceGraph();
    public static final EntranceGraph ootOnly_WallmastersOff_VoidPointsOn_Graph = new EntranceGraph();
    public static final EntranceGraph ootOnly_WallmastersOn_VoidPointsOff_Graph = new EntranceGraph();
    public static final EntranceGraph ootOnly_WallmastersOn_VoidPointsOn_Graph = new EntranceGraph();

    // These 4 graphs correspond to MM-only seeds.
    public static final EntranceGraph mmOnly_WallmastersOff_VoidPointsOff_Graph = new EntranceGraph();
    public static final EntranceGraph mmOnly_WallmastersOff_VoidPointsOn_Graph = new EntranceGraph();
    public static final EntranceGraph mmOnly_WallmastersOn_VoidPointsOff_Graph = new EntranceGraph();
    public static final EntranceGraph mmOnly_WallmastersOn_VoidPointsOn_Graph = new EntranceGraph();

    // These 4 graphs correspond to OOT-MM combo randos.
    public static final EntranceGraph ootAndMm_WallmastersOff_VoidPointsOff_Graph = new EntranceGraph();
    public static final EntranceGraph ootAndMm_WallmastersOff_VoidPointsOn_Graph = new EntranceGraph();
    public static final EntranceGraph ootAndMm_WallmastersOn_VoidPointsOff_Graph = new EntranceGraph();
    public static final EntranceGraph ootAndMm_WallmastersOn_VoidPointsOn_Graph = new EntranceGraph();

    // Returns a new (deep-copy) entrance graph for a seed with the specified settings (used when creating a new seed).
    public static EntranceGraph getEntranceGraphForSettings(boolean ootIncluded, boolean mmIncluded, boolean wallmastersRandomized, boolean voidPointsRandomized)
    {
        if (ootIncluded && !mmIncluded)
        {
            if (wallmastersRandomized)
            {
                if (voidPointsRandomized)
                    return graphDeepCopy(ootOnly_WallmastersOn_VoidPointsOn_Graph);
                else
                    return graphDeepCopy(ootOnly_WallmastersOn_VoidPointsOff_Graph);
            }
            else if (voidPointsRandomized)
                return graphDeepCopy(ootOnly_WallmastersOff_VoidPointsOn_Graph);
            else
                return graphDeepCopy(ootOnly_WallmastersOff_VoidPointsOff_Graph);
        }
        else if (mmIncluded && !ootIncluded)
        {
            if (wallmastersRandomized)
            {
                if (voidPointsRandomized)
                    return graphDeepCopy(mmOnly_WallmastersOn_VoidPointsOn_Graph);
                else
                    return graphDeepCopy(mmOnly_WallmastersOn_VoidPointsOff_Graph);
            }
            else if (voidPointsRandomized)
                return graphDeepCopy(mmOnly_WallmastersOff_VoidPointsOn_Graph);
            else
                return graphDeepCopy(mmOnly_WallmastersOff_VoidPointsOff_Graph);
        }
        else if (ootIncluded && mmIncluded)
        {
            if (wallmastersRandomized)
            {
                if (voidPointsRandomized)
                    return graphDeepCopy(ootAndMm_WallmastersOn_VoidPointsOn_Graph);
                else
                    return graphDeepCopy(ootAndMm_WallmastersOn_VoidPointsOff_Graph);
            }
            else if (voidPointsRandomized)
                return graphDeepCopy(ootAndMm_WallmastersOff_VoidPointsOn_Graph);
            else
                return graphDeepCopy(ootAndMm_WallmastersOff_VoidPointsOff_Graph);
        }
        else
            throw new InvalidParameterException("Error: Invalid combination of setting options was entered. ootIncluded was " + ootIncluded + ", mmIncluded was "  + mmIncluded + ", wallmastersRandmized was " + wallmastersRandomized + ", and voidPointsRandomzied was " + voidPointsRandomized);
    }

    private static EntranceGraph graphDeepCopy(EntranceGraph inputGraph)
    {
        return inputGraph == null ? null : inputGraph.deepCopy();
    }
}
