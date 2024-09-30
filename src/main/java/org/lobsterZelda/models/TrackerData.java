package org.lobsterZelda.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

    private List<String> reminders;

    // This value is true if a TrackerData was last added to the cache on a write/modify operation, and false otherwise.
    // We load TrackerData into the cache when the first read or write to the Tracker happens.
    // However, the first attempt to alter the Tracker will cause the Tracker to be loaded again into the cache, with this value set to true.
    // From here, the full Tracker data won't be re-loaded from the database into the cache until the cache is cleared.
    @JsonIgnore
    private Boolean loadedFromWrite;

    // This lock needs to be acquired/locked before the TrackerData can be modified.
    // However, read-only operations on the TrackerData (such as the call to get the Tracker associated with a given publicTrackerID)
    // do not attempt to acquire the lock. That way, reads and writes can occur at the same time.
    @JsonIgnore
    private final Lock writeLock = new ReentrantLock();

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

    public Boolean getLoadedFromWrite() {
        return loadedFromWrite;
    }

    public void setLoadedFromWrite(Boolean loadedFromWrite) {
        this.loadedFromWrite = loadedFromWrite;
    }

    public Lock getWriteLock()
    {
        return writeLock;
    }
}
