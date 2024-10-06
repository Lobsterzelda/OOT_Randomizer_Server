package org.lobsterZelda.caches.staticData;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JWTSecretKeyCache {

    // A cache that maps from JWT version number to its associated secret (which should be a random 64 byte value).
    // There should really only be 1 secret key version - unless the JWT secret is discovered
    // and needs to be updated, at which point a version 2 would be made.
    private static final Map<Integer, byte[]> jwtVersionToSecretKeyMap = new ConcurrentHashMap<>();
    private static Integer currentJwtVersion = null;

    public static byte[] getSecretKeyForVersion(Integer jwtVersion)
    {
        return jwtVersionToSecretKeyMap.get(jwtVersion);
    }

    public static void addSecretForVersionToCache(Integer version, byte[] secret)
    {
        //For debugging purposes, version 1 corresponds to 64 bytes of 0x33 (51 decimal). In base64, this corresponds to Mz repeating
        jwtVersionToSecretKeyMap.put(version, secret); // TODO: Actually set this to the right secret environment file.
    }

    public static int getMostRecentJWTVersionNumber() {
        return currentJwtVersion;
    }

    public static void setMostRecentJWTVersionNumber(Integer newVersionNum)
    {
        currentJwtVersion = newVersionNum;
    }
}