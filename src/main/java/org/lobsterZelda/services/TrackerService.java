package org.lobsterZelda.services;

import jakarta.servlet.http.HttpServletResponse;
import org.lobsterZelda.models.SeedCreationSettings;

public interface TrackerService
{
    String generateNewTracker(SeedCreationSettings seedCreationSettings, HttpServletResponse httpServletResponse);
    String getTracker(String publicTokenID);
    void testMethod();
}
