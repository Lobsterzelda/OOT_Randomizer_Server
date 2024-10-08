package org.lobsterZelda.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ItemGraph {

    // A map whose keys are the unique ID for an item, and whose associated value is the human-readable name for the item. For example, a key-value pair in this map could be {5:"Hookshot"}
    private final Map<Integer, String> itemIdentifierToNameMap = new HashMap<>();

    // A map whose keys are the unique ID for an item-check location, and whose associated value is the human-readable name for the item. For example, a key-value pair in this map could be {8:"Dampe_Hookshot_Chest"}
    private Map<Integer, String> itemCheckIdentifierToNameMap = new HashMap<>();

    // A map whose keys are the unique ID for an item check location, and whose associated value is the unique ID for the item located at that location. For example, using the values given in the 2 examples above, if the Hookshot was located in  the vanilla location (in the Dampe chest), then there would be a key-value pair in the map of: {8: 5}
    private Map<Integer, Integer> seedItemCheckToItemMap = new HashMap<>();

    // A set whose values are the unique IDs for items that the user has acquired. If an item isn't in this set, it means that the user hasn't marked off that they have that item.
    private Set<Integer> itemsAcquiredMap = new HashSet<>();

    public Map<Integer, String> getItemIdentifierToNameMap() {
        return itemIdentifierToNameMap;
    }

    public Map<Integer, String> getItemCheckIdentifierToNameMap() {
        return itemCheckIdentifierToNameMap;
    }

    public Map<Integer, Integer> getSeedItemCheckToItemMap() {
        return seedItemCheckToItemMap;
    }

    public Set<Integer> getItemsAcquiredMap() {
        return itemsAcquiredMap;
    }
}
