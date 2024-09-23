package org.lobsterZelda.services;

import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

public interface TrackerService
{
    String generateNewTracker(Map<String, String> settings, HttpServletResponse httpServletResponse);
    String getTracker(String publicTokenID);
}
