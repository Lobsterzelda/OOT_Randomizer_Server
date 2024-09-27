package org.lobsterZelda.utils;

// A Base64-encoded JWTToken is what gets set in the cookies and used by the person who created the seed in order to be able to edit the seed.
// This class contains the methods to create a new JWTToken when a seed is created, to validate if a JWTToken passed into an API call is valid,
// and a function to get the payload of the JWT (which stores the public Tracker ID a given JWT refers to, the date the seed was created, and the optional name of the person who created the seed).

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import org.lobsterZelda.constants.Constants;
import org.lobsterZelda.models.JWTPayload;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

// The creator of the seed can also give this token to other people that they want to be able to edit the seed on their behalf, like their Twitch mods.
public class JWTTokenUtils
{
    private static final byte[] JWT_SECRET = new byte[64]; // TODO: Store this in the environment, and use an actual, secure random value.

    static
    {
        Arrays.fill(JWT_SECRET, (byte) 31);
    }

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


        final String creationDateTime = Instant.now().toString();

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

        jwsObject.sign(new MACSigner(JWT_SECRET));

        final String base64JWTString = jwsObject.serialize();

        JWSObject validationObj = JWSObject.parse(base64JWTString);
        if (!validationObj.verify(new MACVerifier(JWT_SECRET)))
            throw new RuntimeException("Error: Attempt to validate the JWTToken that was created failed! This indicates an internal server error.");

        return base64JWTString;
    }

    public static boolean isJWTTokenValid(String base64EncodedJWTToken)
    {
        return false;
    }

    public static JWTPayload getJWTPayload(String base64EncodedJWTToken)
    {
        return null;
    }

    private static boolean isWholeStringAlphaNumeric(String inputString)
    {
        if (inputString == null)
            return true;
        int strLength = inputString.length();

        for (int i = 0; i < strLength; ++i)
        {
            if (!Character.isLetterOrDigit(inputString.charAt(i)))
                return false;
        }
        return true;
    }

    private static boolean isWholeStringASCIIPrintableChars(String inputString)
    {
        if (inputString == null)
            return true;
        int strLength = inputString.length();

        for (int i = 0; i < strLength; ++i)
        {
            char currentChar = inputString.charAt(i);

            if (currentChar < 0x20 || currentChar >= 0x7F)
                return false;
        }

        return true;
    }




}
