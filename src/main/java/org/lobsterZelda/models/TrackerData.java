package org.lobsterZelda.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// This stores all data about a User's seed. This is loaded from the database into the TrackerCache whenever a user queries for information about that seed
// (and an associated TrackerData object is put into the TrackerCache when the user first creates a new seed, as well).
public class TrackerData
{
    // The settings that the user selected when creating the rando.
    private SeedCreationSettings seedCreationSettings;

    // The data for the entrance locations/connections that have been recorded by the user so far
    private EntranceGraph entranceGraph;

    // The data for the item locations that has been recorded by the user so far.
    private ItemGraph itemGraph;

    // Reminders that the user has set.
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

    public EntranceGraph getEntranceGraph() {
        return entranceGraph;
    }

    public void setEntranceGraph(EntranceGraph entranceGraph) {
        this.entranceGraph = entranceGraph;
    }

    public ItemGraph getItemGraph() {
        return itemGraph;
    }

    public void setItemGraph(ItemGraph itemGraph) {
        this.itemGraph = itemGraph;
    }

    public List<String> getReminders() {
        return reminders;
    }

    public void setReminders(List<String> reminders) {
        this.reminders = reminders;
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
