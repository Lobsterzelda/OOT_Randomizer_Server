package org.lobsterZelda.caches.dynamic;

import org.lobsterZelda.models.TrackerData;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TrackerCache {

    // Used to store a cached copy of Trackers. The keys are publicTrackerIDs (contained in the URL), and the TrackerData is the data associated with that Tracker.
    // This cache is cleared every so often, in order to prevent old/unused data from accumulating in the cache.
    private static final Map<String, TrackerData> publicTrackerIDToTrackerDataMap = new ConcurrentHashMap<>();

    // Returns the TrackerData associated with a specific publicTrackerID in the cache, or returns null if the publicTrackerID wasn't stored in the cache.
    public static TrackerData getTrackerDataFromCache(String publicTrackerID)
    {
        return publicTrackerIDToTrackerDataMap.get(publicTrackerID);
    }

    // Adds a publicTrackerID to the cache as a key, with its TrackerData as its associated value.
    public static void addTrackerDataToCache(String publicTrackerID, TrackerData trackerData)
    {
        publicTrackerIDToTrackerDataMap.put(publicTrackerID, trackerData);
    }

    // This function is called every so often to empty the cache.
    public static void emptyCache()
    {
        publicTrackerIDToTrackerDataMap.clear();
    }
}
