package org.lobsterZelda.models;

import java.util.List;
import java.util.Map;

public class TrackerData
{
    // The settings that the user has enabled for this rando
    private Map<String, String> seedSettings;

    // These 3 variables should be more or less the same for all seeds (except that OOT only seeds contain only OOT data, MM only seeds contain only MM data, and combo-randos contain both).
    private Map<Integer, String> entranceIdentifierToNameMap;
    private Map<Integer, String> itemIdentifierToNameMap;
    private Map<Integer, String> itemCheckIdentifierToNameMap;

    // The actual data for a specific seed that has been recorded by the user so far
    private Map<Integer, Integer> seedSourceEntranceToDestinationEntranceMap;
    private Map<Integer, Integer> seedItemCheckToItemMap;
    private List<String> reminders;

    public Map<String, String> getSeedSettings() {
        return seedSettings;
    }

    public void setSeedSettings(Map<String, String> seedSettings) {
        this.seedSettings = seedSettings;
    }

    public Map<Integer, String> getEntranceIdentifierToNameMap() {
        return entranceIdentifierToNameMap;
    }

    public void setEntranceIdentifierToNameMap(Map<Integer, String> entranceIdentifierToNameMap) {
        this.entranceIdentifierToNameMap = entranceIdentifierToNameMap;
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
}
