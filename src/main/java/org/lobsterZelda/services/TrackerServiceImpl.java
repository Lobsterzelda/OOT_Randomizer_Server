package org.lobsterZelda.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.lobsterZelda.repositories.TrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;

import static org.lobsterZelda.constants.Constants.JWT_EDITOR_COOKIE_TOKEN_NAME;
import static org.lobsterZelda.constants.Constants.PUBLIC_TRACKER_ID_SIZE;

public class TrackerServiceImpl implements TrackerService {

    @Autowired
    private TrackerRepository trackerRepository;

    @Override
    public String generateNewTracker(Map<String, String> settings, HttpServletResponse httpServletResponse) {
        Cookie jwtTrackerEditToken = new Cookie(JWT_EDITOR_COOKIE_TOKEN_NAME, "emptyVal");
        jwtTrackerEditToken.setSecure(true);
        jwtTrackerEditToken.setHttpOnly(false);
        jwtTrackerEditToken.setMaxAge(Integer.MAX_VALUE - 2);
        httpServletResponse.addCookie(jwtTrackerEditToken);
        return "";
    }

    @Override
    public String getTracker(String publicTokenID) {
        return "";
    }

    private String generateNewPublicTrackerID() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        int charsGenerated = 0;
        StringBuilder newPublicTrackerId = new StringBuilder();
        while (charsGenerated < PUBLIC_TRACKER_ID_SIZE)
        {
            char nextRandChar = (char)((secureRandom.nextInt() % 75) + 48);
            if (
                    (nextRandChar >= '0' && nextRandChar <= '9') ||
                            (nextRandChar >= 'a' && nextRandChar <= 'z') ||
                            (nextRandChar >= 'A' && nextRandChar <= 'Z')
            )
            {
                newPublicTrackerId.append(nextRandChar);
                ++charsGenerated;
            }
        }
        return newPublicTrackerId.toString();
    }
}
