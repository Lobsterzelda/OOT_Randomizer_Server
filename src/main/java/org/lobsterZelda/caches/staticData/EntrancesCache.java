package org.lobsterZelda.caches.staticData;

import org.lobsterZelda.models.EntranceGraph;

public class EntrancesCache
{
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




}
