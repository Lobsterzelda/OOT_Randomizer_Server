package org.lobsterZelda.caches.staticData;

import org.lobsterZelda.models.ItemGraph;

import java.security.InvalidParameterException;

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

    // Returns a new (deep-copy) item graph for a seed with the specified settings (used when creating a new seed).
    // For invalid/impossible combinations of settings, this function throws an InvalidParameterException
    public static ItemGraph getInitialItemGraphForSettings(boolean ootItemCheckLocationsIncluded, boolean mmItemCheckLocationsIncluded, boolean ootItemsIncluded, boolean mmItemsIncluded)
    {
        if (ootItemCheckLocationsIncluded && mmItemCheckLocationsIncluded)
        {
            if (ootItemsIncluded && mmItemsIncluded)
                return graphDeepCopy(ootItemCheckLocations_mmItemCheckLocations_ootItems_mmItems);
            else if (ootItemsIncluded)
                return graphDeepCopy(ootItemCheckLocations_mmItemCheckLocations_ootItems_noMMItems);
            else if (mmItemsIncluded)
                return graphDeepCopy(ootItemCheckLocations_mmItemCheckLocations_noOOTItems_mmItems);
            else
                throw new InvalidParameterException("Error: Invalid combination of setting options was entered for ItemsCache retrieval. ootItemCheckLocationsIncluded was " + ootItemCheckLocationsIncluded + ", mmItemCheckLocationsIncluded was "  + mmItemCheckLocationsIncluded + ", ootItemsIncluded was " + ootItemsIncluded + ", and mmItemsIncluded was " + mmItemsIncluded);

        }
        else if (ootItemCheckLocationsIncluded)
        {
            if (ootItemsIncluded && mmItemsIncluded)
                return graphDeepCopy(ootItemCheckLocations_noMMItemCheckLocations_ootItems_mmItems);
            else if (ootItemsIncluded)
                return graphDeepCopy(ootItemCheckLocations_noMMItemCheckLocations_ootItems_noMMItems);
            else if (mmItemsIncluded)
                return graphDeepCopy(ootItemCheckLocations_noMMItemCheckLocations_noOOTItems_mmItems);
            else
                throw new InvalidParameterException("Error: Invalid combination of setting options was entered for ItemsCache retrieval. ootItemCheckLocationsIncluded was " + ootItemCheckLocationsIncluded + ", mmItemCheckLocationsIncluded was "  + mmItemCheckLocationsIncluded + ", ootItemsIncluded was " + ootItemsIncluded + ", and mmItemsIncluded was " + mmItemsIncluded);
        }
        else if (mmItemCheckLocationsIncluded)
        {
            if (ootItemsIncluded && mmItemsIncluded)
                return graphDeepCopy(noOOTItemCheckLocations_mmItemCheckLocations_ootItems_mmItems);
            else if (ootItemsIncluded)
                return graphDeepCopy(noOOTItemCheckLocations_mmItemCheckLocations_ootItems_noMMItems);
            else if (mmItemsIncluded)
                return graphDeepCopy(noOOTItemCheckLocations_mmItemCheckLocations_noOOTItems_mmItems);
            else
                throw new InvalidParameterException("Error: Invalid combination of setting options was entered for ItemsCache retrieval. ootItemCheckLocationsIncluded was " + ootItemCheckLocationsIncluded + ", mmItemCheckLocationsIncluded was "  + mmItemCheckLocationsIncluded + ", ootItemsIncluded was " + ootItemsIncluded + ", and mmItemsIncluded was " + mmItemsIncluded);
        }
        else
            throw new InvalidParameterException("Error: Invalid combination of setting options was entered for ItemsCache retrieval. ootItemCheckLocationsIncluded was " + ootItemCheckLocationsIncluded + ", mmItemCheckLocationsIncluded was "  + mmItemCheckLocationsIncluded + ", ootItemsIncluded was " + ootItemsIncluded + ", and mmItemsIncluded was " + mmItemsIncluded);
    }

    private static ItemGraph graphDeepCopy(ItemGraph inputGraph)
    {
        return inputGraph == null ? null : inputGraph.deepCopy();
    }
}
