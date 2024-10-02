package org.lobsterZelda.utils;

import org.lobsterZelda.caches.staticData.EntrancesCache;
import org.lobsterZelda.caches.staticData.JWTSecretKeyCache;

public class StaticCacheLoader
{
    public static void loadAllCaches()
    {
        JWTSecretKeyCache.populateCache();
        EntrancesCache.populateCache();
    }
}
