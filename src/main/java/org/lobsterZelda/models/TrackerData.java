package org.lobsterZelda.models;

import java.util.List;
import java.util.Map;

public class TrackerData
{
    // The settings that the user selected when creating the rando.
    private SeedCreationSettings seedCreationSettings;

    // These 4 variables should be more or less the same for all seeds (except that OOT only seeds contain only OOT data, MM only seeds contain only MM data, and combo-randos contain both).
    private Map<Integer, String> entranceIdentifierToNameMap;
    private Map<Integer, Boolean> isLocationInOOTMap; // Keys are the identifier of a location, and the value is true if the location is in OOT, and false if the location is in MM. If a location is not listed in this map, then it's not a valid location (or an error has occurred).
    private Map<Integer, String> itemIdentifierToNameMap;
    private Map<Integer, String> itemCheckIdentifierToNameMap;

    // The actual data for a specific seed that has been recorded by the user so far
    private Map<Integer, Integer> seedSourceEntranceToDestinationEntranceMap;
    private Map<Integer, Integer> seedItemCheckToItemMap;
    private Map<Integer, Boolean> isDungeonMQ; // Keys are the identifiers for a dungeon's main entrance (from inside of the dungeon map). The value is true if the dungeon is an MQ dungeon, and false otherwise.

    private Boolean isChild; // True if currently a child, and false if currently an adult.

    private List<String> reminders;

    public SeedCreationSettings getSeedCreationSettings() {
        return seedCreationSettings;
    }

    public void setSeedCreationSettings(SeedCreationSettings seedCreationSettings) {
        this.seedCreationSettings = seedCreationSettings;
    }

    public Map<Integer, String> getEntranceIdentifierToNameMap() {
        return entranceIdentifierToNameMap;
    }

    public void setEntranceIdentifierToNameMap(Map<Integer, String> entranceIdentifierToNameMap) {
        this.entranceIdentifierToNameMap = entranceIdentifierToNameMap;
    }

    public Map<Integer, Boolean> getIsLocationInOOTMap() {
        return isLocationInOOTMap;
    }

    public void setIsLocationInOOTMap(Map<Integer, Boolean> isLocationInOOTMap) {
        this.isLocationInOOTMap = isLocationInOOTMap;
    }

    public Map<Integer, String> getItemIdentifierToNameMap() {
        return itemIdentifierToNameMap;
    }

    public void setItemIdentifierToNameMap(Map<Integer, String> itemIdentifierToNameMap) {
        this.itemIdentifierToNameMap = itemIdentifierToNameMap;
    }

    public Map<Integer, String> getItemCheckIdentifierToNameMap() {
        return itemCheckIdentifierToNameMap;
    }

    public void setItemCheckIdentifierToNameMap(Map<Integer, String> itemCheckIdentifierToNameMap) {
        this.itemCheckIdentifierToNameMap = itemCheckIdentifierToNameMap;
    }

    public Map<Integer, Integer> getSeedSourceEntranceToDestinationEntranceMap() {
        return seedSourceEntranceToDestinationEntranceMap;
    }

    public void setSeedSourceEntranceToDestinationEntranceMap(Map<Integer, Integer> seedSourceEntranceToDestinationEntranceMap) {
        this.seedSourceEntranceToDestinationEntranceMap = seedSourceEntranceToDestinationEntranceMap;
    }

    public Map<Integer, Integer> getSeedItemCheckToItemMap() {
        return seedItemCheckToItemMap;
    }

    public void setSeedItemCheckToItemMap(Map<Integer, Integer> seedItemCheckToItemMap) {
        this.seedItemCheckToItemMap = seedItemCheckToItemMap;
    }

    public List<String> getReminders() {
        return reminders;
    }

    public void setReminders(List<String> reminders) {
        this.reminders = reminders;
    }

    public Map<Integer, Boolean> getIsDungeonMQ() {
        return isDungeonMQ;
    }

    void setIsDungeonMQ(Map<Integer, Boolean> isDungeonMQ)
    {
        this.isDungeonMQ = isDungeonMQ;
    }

    public Boolean getChild() {
        return isChild;
    }

    public void setChild(Boolean child) {
        isChild = child;
    }

}
