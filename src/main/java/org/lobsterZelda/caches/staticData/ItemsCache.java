package org.lobsterZelda.caches.staticData;

import org.lobsterZelda.models.ItemGraph;

public class ItemsCache
{
    // We cache all combinations of oot check locations, mm check locations, oot items, and mm items.
    // All seeds need at least one of ootItemCheckLocations or mmItemCheckLocations enabled (or both)
    // Additionally, all seeds need at least one of ootItems and mmItems enabled (or both)
    // The 4 boolean conditions these graphs are split on are ootItemCheckLocations (if OOT maps are present in the seed),
    // mmItemCheckLocations (if MM maps are present in the seed), ootItems (if items from Ocarina of Time are present in the seed),
    // and mmItems if items from Majora's Mask are present in the seed).
    // There are 16 combinations of these variables. However, we can eliminate the 4 conditions where both ootItemCheckLocations
    // and mmItemCheckLocations are not enabled, and the 3 other spots where neither ootItems nor mmItems are enabled.
    // This leaves us with 16 - (4 + 3) = 16 - 7 = 9 possible caches.

    public static final ItemGraph ootItemCheckLocations_noMMItemCheckLocations_ootItems_noMMItems = new ItemGraph();
    public static final ItemGraph ootItemCheckLocations_noMMItemCheckLocations_noOOTItems_mmItems = new ItemGraph();
    public static final ItemGraph ootItemCheckLocations_noMMItemCheckLocations_ootItems_mmItems = new ItemGraph();

    public static final ItemGraph noOOTItemCheckLocations_mmItemCheckLocations_ootItems_noMMItems = new ItemGraph();
    public static final ItemGraph noOOTItemCheckLocations_mmItemCheckLocations_noOOTItems_mmItems = new ItemGraph();
    public static final ItemGraph noOOTItemCheckLocations_mmItemCheckLocations_ootItems_mmItems = new ItemGraph();

    public static final ItemGraph ootItemCheckLocations_mmItemCheckLocations_ootItems_noMMItems = new ItemGraph();
    public static final ItemGraph ootItemCheckLocations_mmItemCheckLocations_noOOTItems_mmItems = new ItemGraph();
    public static final ItemGraph ootItemCheckLocations_mmItemCheckLocations_ootItems_mmItems = new ItemGraph();

}
