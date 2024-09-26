package org.lobsterZelda.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.lobsterZelda.models.SeedCreationSettings;
import org.lobsterZelda.repositories.TrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

import static org.lobsterZelda.constants.Constants.JWT_EDITOR_COOKIE_TOKEN_NAME;
import static org.lobsterZelda.constants.Constants.PUBLIC_TRACKER_ID_SIZE;

@Service
public class TrackerServiceImpl implements TrackerService {

    @Autowired
    private TrackerRepository trackerRepository;

    @Override
    public String generateNewTracker(SeedCreationSettings seedCreationSettings, HttpServletResponse httpServletResponse) {
        String newPublicTrackerID;

        do {
            newPublicTrackerID = generateNewPublicTrackerID();
        } while (trackerRepository.publicIDExistsInDatabase(newPublicTrackerID));

        generatePrivateJWTToken(httpServletResponse);
        trackerRepository.createNewTracker(newPublicTrackerID, seedCreationSettings);
        return newPublicTrackerID;
    }

    @Override
    public String getTracker(String publicTokenID) {
        return "";
    }

    private void generatePrivateJWTToken(HttpServletResponse httpServletResponse)
    {
        Cookie jwtTrackerEditToken = new Cookie(JWT_EDITOR_COOKIE_TOKEN_NAME, "emptyVal");
        jwtTrackerEditToken.setSecure(true);
        jwtTrackerEditToken.setHttpOnly(false);
        jwtTrackerEditToken.setMaxAge(Integer.MAX_VALUE - 2);
        httpServletResponse.addCookie(jwtTrackerEditToken);
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
                char nextRandChar = alphaNumericCharsArray[secureRandom.nextInt() % ALPHA_NUMERIC_CHAR_ARRAY_SIZE];
                ++charsGenerated;
            }
            return newPublicTrackerId.toString();
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
