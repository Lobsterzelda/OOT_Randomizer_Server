package org.lobsterZelda.utils;

import org.lobsterZelda.caches.staticData.JWTSecretKeyCache;

public class StaticCacheLoader
{
    public void loadAllCaches()
    {
        JWTSecretKeyCache.populateCache();
    }
}
