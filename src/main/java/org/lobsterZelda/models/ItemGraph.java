package org.lobsterZelda.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ItemGraph {

    // The keys of this map are the IDs for items, and the values are the associated Item object.
    private final Map<Integer, Item> uniqueItemIdentifierToItemMap = new HashMap<>();

    // The keys of this map are the name for items, and the values are the associated Item object.
    private final Map<String, Item> itemNameToItemMap = new HashMap<>();

    // The keys of this map are the ID for itemCheckLocations, and the values are the associated itemCheckLocations
    private final Map<Integer, ItemCheckLocation> uniqueItemCheckLocationIdentifierToItemCheckLocationMap = new HashMap<>();

    // The keys of this map are the name for itemCheckLocations, and the values are the associated itemCheckLocations
    private final Map<String, ItemCheckLocation> itemCheckLocationNameToItemCheckLocationMap = new HashMap<>();

    // The keys of this map are item IDs, and the values are the IDs of the itemCheckLocation where that item is located.
    private final Map<Integer, Integer> itemIdToItemCheckLocationMap = new HashMap<>();

    // The keys of this map are itemCheckLocation IDs, and the values are the item IDs of the item located at that location. These 2 maps have a relation such that if itemIdToItemCheckLocationMap contains {5 : 3}, then itemCheckLocationToItemIdMap must contain {3 : 5}
    private final Map<Integer, Integer> itemCheckLocationToItemIdMap = new HashMap<>();

    // Stores the IDs of the items that the user has acquired (the above 2 maps store where the item is located, while this map records the IDs of items that the user has actually collected).
    // When a user collects an item, its ID is added to this set. If an item's ID isn't in this set, then that means the user doesn't have the item yet.
    private final Set<Integer> itemsAcquired = new HashSet<>();

    public Map<Integer, Item> getUniqueItemIdentifierToItemMap() {
        return uniqueItemIdentifierToItemMap;
    }

    public Map<String, Item> getItemNameToItemMap() {
        return itemNameToItemMap;
    }

    public Map<Integer, ItemCheckLocation> getUniqueItemCheckLocationIdentifierToItemCheckLocationMap() {
        return uniqueItemCheckLocationIdentifierToItemCheckLocationMap;
    }

    public Map<String, ItemCheckLocation> getItemCheckLocationNameToItemCheckLocationMap() {
        return itemCheckLocationNameToItemCheckLocationMap;
    }

    public Map<Integer, Integer> getItemIdToItemCheckLocationMap() {
        return itemIdToItemCheckLocationMap;
    }

    public Map<Integer, Integer> getItemCheckLocationToItemIdMap() {
        return itemCheckLocationToItemIdMap;
    }

    public Set<Integer> getItemsAcquired() {
        return itemsAcquired;
    }
}
