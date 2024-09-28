package org.lobsterZelda.caches.dynamic;

import org.lobsterZelda.models.JWTPayload;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JWTCache
{
    // Whenever a valid JWT is encountered, we check if the SHA-512 for the JWT is in the validJwtHashToPayloadMap.
    // If it's in the cache, then we skip validating/parsing the JWT again, and load the payload data associated with that hash from the map.
    // Otherwise, we validate the token. If the token is valid, then we will add its hash and payload into the map.
    // The values in this cache will be cleared once a day, to prevent running out of memory or wasting space on old Trackers that don't need to be kept in memory anymore.
    private static final Map<String, JWTPayload> validJwtHashToPayloadMap = new ConcurrentHashMap<>();

    // Takes as input a SHA-512 hash of a JWT. Returns the JWTPayload associated with the JWT, if the JWT hash corresponds to a valid JWT whose payload is already stored in the cache.
    // Otherwise, returns null to indicate that the hash wasn't in the cache.
    public static JWTPayload getPayloadFromCache(String hashString)
    {
        return validJwtHashToPayloadMap.get(hashString);
    }

    // Adds the hash of a validated JWT token into the cache, with an associated jwtPayload as its associated value.
    public static void addPayloadToCache(String hashString, JWTPayload jwtPayload)
    {
        validJwtHashToPayloadMap.put(hashString, jwtPayload);
    }

    // Called once per day to empty the cache and save memory space.
    public static void emptyCache()
    {
        validJwtHashToPayloadMap.clear();
    }
}
