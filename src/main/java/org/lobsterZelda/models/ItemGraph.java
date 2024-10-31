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

    public ItemGraph deepCopy()
    {
        ItemGraph returnGraph = new ItemGraph();

        for (Integer id : this.uniqueItemIdentifierToItemMap.keySet())
            returnGraph.uniqueItemIdentifierToItemMap.put(new Integer(id), generateItemDeepCopy(this.uniqueItemIdentifierToItemMap.get(id)));
        for (String name : this.itemNameToItemMap.keySet())
            returnGraph.itemNameToItemMap.put(name, generateItemDeepCopy(this.itemNameToItemMap.get(name)));
        for (Integer id : this.uniqueItemCheckLocationIdentifierToItemCheckLocationMap.keySet())
            returnGraph.uniqueItemCheckLocationIdentifierToItemCheckLocationMap.put(new Integer(id), generateItemCheckLocationDeepCopy(this.uniqueItemCheckLocationIdentifierToItemCheckLocationMap.get(id)));
        for (String name : this.itemCheckLocationNameToItemCheckLocationMap.keySet())
            returnGraph.itemCheckLocationNameToItemCheckLocationMap.put(name, generateItemCheckLocationDeepCopy(this.itemCheckLocationNameToItemCheckLocationMap.get(name)));
        for (Integer id : this.itemIdToItemCheckLocationMap.keySet())
            returnGraph.itemIdToItemCheckLocationMap.put(new Integer(id), new Integer(this.itemIdToItemCheckLocationMap.get(id)));
        for (Integer id : this.itemCheckLocationToItemIdMap.keySet())
            returnGraph.itemCheckLocationToItemIdMap.put(new Integer(id), new Integer(this.itemCheckLocationToItemIdMap.get(id)));
        for (Integer id : this.itemsAcquired)
            returnGraph.itemsAcquired.add(new Integer(id));

        return returnGraph;
    }

    private Item generateItemDeepCopy(Item input)
    {
            Item returnItem = new Item();

            returnItem.setItemID(input.getItemID());
            returnItem.setMapId(input.getMapId());
            returnItem.setItemName(input.getItemName());
            returnItem.setItemDescription(input.getItemDescription());
            returnItem.setIsOOTItem(input.getIsOOTItem());
            returnItem.setPercentFromLeftEdgeOfMap(input.getPercentFromLeftEdgeOfMap());
            returnItem.setPercentFromTopEdgeOfMap(input.getPercentFromTopEdgeOfMap());

            return returnItem;
    }

    private ItemCheckLocation generateItemCheckLocationDeepCopy(ItemCheckLocation input)
    {
        ItemCheckLocation returnValue = new ItemCheckLocation();

        returnValue.setItemCheckLocationId(input.getItemCheckLocationId());
        returnValue.setMapId(input.getMapId());
        returnValue.setItemCheckLocationName(input.getItemCheckLocationName());
        returnValue.setItemCheckLocationDescription(input.getItemCheckLocationDescription());
        returnValue.setIsOOTItemCheckLocation(input.getIsOOTItemCheckLocation());
        returnValue.setPercentFromLeftEdgeOfMap(input.getPercentFromLeftEdgeOfMap());
        returnValue.setPercentFromTopEdgeOfMap(input.getPercentFromTopEdgeOfMap());

        return returnValue;
    }
}
