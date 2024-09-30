package org.lobsterZelda.services;

import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.lobsterZelda.models.SeedCreationSettings;
import org.lobsterZelda.repositories.CreateTablesAndReferenceDataRepository;
import org.lobsterZelda.repositories.TrackerRepository;
import org.lobsterZelda.utils.JWTTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.text.ParseException;

import static org.lobsterZelda.constants.Constants.JWT_EDITOR_COOKIE_TOKEN_NAME;
import static org.lobsterZelda.constants.Constants.PUBLIC_TRACKER_ID_SIZE;

@Service
public class TrackerServiceImpl implements TrackerService {

    @Autowired
    private TrackerRepository trackerRepository;

    @Autowired
    private CreateTablesAndReferenceDataRepository createTablesAndReferenceDataRepository;

    @Override
    public String generateNewTracker(SeedCreationSettings seedCreationSettings, HttpServletResponse httpServletResponse) {
        String newPublicTrackerID;
        String base64EncodedJWT;

        do {
            newPublicTrackerID = generateNewPublicTrackerID();
        } while (trackerRepository.publicIDExistsInDatabase(newPublicTrackerID));

        try {
            base64EncodedJWT = generatePrivateJWTTokenAndAddAsCookie(newPublicTrackerID, seedCreationSettings.getOptionalTrackerCreatorUserName(), httpServletResponse);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
        trackerRepository.createNewTracker(newPublicTrackerID, seedCreationSettings, base64EncodedJWT);
        return newPublicTrackerID;
    }

    @Override
    public String getTracker(String publicTokenID) {
        return "";
    }

    private String generatePrivateJWTTokenAndAddAsCookie(String newPublicTrackerID, String optionalTrackerCreatorUserName, HttpServletResponse httpServletResponse) throws ParseException, JOSEException {
        final String base64EncodedJWTString = JWTTokenUtils.createNewJWT(newPublicTrackerID, optionalTrackerCreatorUserName);
        Cookie jwtTrackerEditToken = new Cookie(JWT_EDITOR_COOKIE_TOKEN_NAME, base64EncodedJWTString);
        jwtTrackerEditToken.setSecure(true);
        jwtTrackerEditToken.setHttpOnly(false);
        jwtTrackerEditToken.setMaxAge(Integer.MAX_VALUE - 2);
        httpServletResponse.addCookie(jwtTrackerEditToken);
        return base64EncodedJWTString;
    }

    private static final char[] alphaNumericCharsArray = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
    };
    private static final int ALPHA_NUMERIC_CHAR_ARRAY_SIZE = 62;

    // Public token should be 16 random alphanumeric characters.
    private String generateNewPublicTrackerID() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            int charsGenerated = 0;
            StringBuilder newPublicTrackerId = new StringBuilder();
            while (charsGenerated < PUBLIC_TRACKER_ID_SIZE) {
                char nextRandChar = alphaNumericCharsArray[secureRandom.nextInt(ALPHA_NUMERIC_CHAR_ARRAY_SIZE)];
                ++charsGenerated;
            }
            return newPublicTrackerId.toString();
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void testMethod()
    {
        createTablesAndReferenceDataRepository.deleteAllTables();
        createTablesAndReferenceDataRepository.createAndInitializeAllTables();
    }
}
