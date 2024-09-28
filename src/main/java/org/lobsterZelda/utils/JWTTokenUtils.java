package org.lobsterZelda.utils;

// A Base64-encoded JWTToken is what gets set in the cookies and used by the person who created the seed in order to be able to edit the seed.
// This class contains the methods to create a new JWTToken when a seed is created, to validate if a JWTToken passed into an API call is valid,
// and a function to get the payload of the JWT (which stores the public Tracker ID a given JWT refers to, the date the seed was created, and the optional name of the person who created the seed).

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import org.lobsterZelda.caches.staticData.JWTSecretKeyCache;
import org.lobsterZelda.constants.Constants;
import org.lobsterZelda.models.JWTPayload;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.time.Instant;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;

import static org.lobsterZelda.utils.LobsterStringUtils.isWholeStringASCIIPrintableChars;
import static org.lobsterZelda.utils.LobsterStringUtils.isWholeStringAlphaNumeric;

// The creator of the seed can also give this token to other people that they want to be able to edit the seed on their behalf, like their Twitch mods.
public class JWTTokenUtils
{

    public static String createNewJWT(String publicTrackerID, String optionalUserName) throws JOSEException, ParseException {
        //SecureRandom secureRandom = new SecureRandom();
        // byte[] sharedSecret = new byte[64];
        //secureRandom.nextBytes(sharedSecret);

        if (publicTrackerID == null || !isWholeStringAlphaNumeric(publicTrackerID))
            throw new InvalidParameterException("Error: Public Tracker ID must contain alphanumeric characters, only.");

        if (Constants.PUBLIC_TRACKER_ID_SIZE != publicTrackerID.length())
            throw new InvalidParameterException("Error: Public Tracker ID did not contain exactly " + Constants.PUBLIC_TRACKER_ID_SIZE + " characters.");

        if (optionalUserName != null && !isWholeStringASCIIPrintableChars(optionalUserName))
            throw new InvalidParameterException("Error: optional username of seed creator must contain only ASCII printable characters.");


        final long creationDateTime = Instant.now().getEpochSecond();

        JWTPayload jwtPayload = new JWTPayload();
        jwtPayload.setPublicTrackerID(publicTrackerID);
        jwtPayload.setTrackerCreationDate(creationDateTime);
        jwtPayload.setOptionalTrackerCreatorUserName(optionalUserName);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .serializeNullClaims(true)
                .claim(Constants.JWT_PUBLIC_TRACKER_ID_FIELD_NAME, publicTrackerID)
                .claim(Constants.JWT_TRACKER_CREATION_DATE_FIELD_NAME, creationDateTime)
                .claim(Constants.JWT_OPTIONAL_TRACKER_CREATOR_USERNAME, optionalUserName)
                .build();

        JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS512), jwtClaimsSet.toPayload());

        // Use the most recent JWT version when generating a new JWT.
        Long currentJWTVersion = JWTSecretKeyCache.getMostRecentJWTVersionNumber();
        jwsObject.sign(new MACSigner(JWTSecretKeyCache.getSecretKeyForVersion(currentJWTVersion)));

        final String base64JWTString = jwsObject.serialize();

        try {
            verifyJWTTokenAndGetPayload(base64JWTString, currentJWTVersion);
        } catch (Exception e)
        {
            throw new RuntimeException("Error: Attempt to validate JWT token failed immediately after creating the JWT Token! This indicates an internal server error.");
        }

        return base64JWTString;
    }

    // This function throws an Exception if the String passed into it doesn't represent a valid JWT for the application.
    // Otherwise, it returns the parsed contents of the JWT's payload as a JWTPayload object.
    public static JWTPayload verifyJWTTokenAndGetPayload(String base64EncodedJWTToken, Long jwtVersion) throws ParseException, InvalidPropertiesFormatException {
        final JWTPayload parsedPayload = new JWTPayload();
        try {
            JWSObject jwsObject = JWSObject.parse(base64EncodedJWTToken);
            if (!jwsObject.verify(new MACVerifier(JWTSecretKeyCache.getSecretKeyForVersion(jwtVersion))))
                throw new RuntimeException("");
            Map<String, Object> claims = jwsObject.getPayload().toJSONObject();
            final String publicTrackerID = (String) claims.get(Constants.JWT_PUBLIC_TRACKER_ID_FIELD_NAME);
            if (!isValidPublicTrackerID(publicTrackerID))
                throw new RuntimeException("");
            final Long creationDateTime = (Long) claims.get(Constants.JWT_TRACKER_CREATION_DATE_FIELD_NAME);
            if (creationDateTime == null || creationDateTime > Instant.now().getEpochSecond())
                throw new RuntimeException("");

            final String optionalTrackerCreatorUsername = (String) claims.get(Constants.JWT_OPTIONAL_TRACKER_CREATOR_USERNAME);

            parsedPayload.setPublicTrackerID(publicTrackerID);
            parsedPayload.setTrackerCreationDate(creationDateTime);
            parsedPayload.setOptionalTrackerCreatorUserName(optionalTrackerCreatorUsername);
            return parsedPayload;
        }
        catch (Exception e)
        {
            throw new InvalidPropertiesFormatException("Invalid String passed in for JWT!");
        }
    }

    private static boolean isValidPublicTrackerID(String publicTrackerID)
    {
        return publicTrackerID != null && isWholeStringAlphaNumeric(publicTrackerID) && publicTrackerID.length() == Constants.PUBLIC_TRACKER_ID_SIZE;
    }

}
