package org.lobsterZelda.caches.staticData;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JWTSecretKeyCache {

    // A cache that maps from JWT version number to its associated secret (which should be a random 64 byte value).
    // There should really only be 1 secret key version - unless the JWT secret is discovered
    // and needs to be updated, at which point a version 2 would be made.
    private static final Map<Long, byte[]> jwtVersionToSecretKeyMap = new ConcurrentHashMap<>();
    private static Integer currentJwtVersion = null;

    private static final byte[] JWT_SECRET = new byte[64]; // TODO: Store this in a secret file in the environment (which will be loaded at runtime), and use an actual, secure random value.

    static
    {
        Arrays.fill(JWT_SECRET, (byte) 31);
    }

    public static byte[] getSecretKeyForVersion(Integer jwtVersion)
    {
        return jwtVersionToSecretKeyMap.get(jwtVersion);
    }

    public static void populateCache()
    {
        jwtVersionToSecretKeyMap.put(Long.valueOf(1), JWT_SECRET); // TODO: Actually set this to the right secret enviroment file.
    }

    public static int getMostRecentJWTVersionNumber() {
        return currentJwtVersion;
    }

    public static void setMostRecentJWTVersionNumber(Integer newVersionNum)
    {
        currentJwtVersion = newVersionNum;
    }
}