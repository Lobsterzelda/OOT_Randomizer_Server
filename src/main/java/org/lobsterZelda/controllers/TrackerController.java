package org.lobsterZelda.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.lobsterZelda.models.SeedCreationSettings;
import org.lobsterZelda.services.TrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TrackerController
{

    @Autowired
    private TrackerService trackerService;

    @PostMapping("/create")
    public ResponseEntity<String> createNewTracker(@RequestBody SeedCreationSettings seedCreationSettings, HttpServletResponse httpServletResponse)
    {
        String publicTrackerID = trackerService.generateNewTracker(seedCreationSettings, httpServletResponse);
        return new ResponseEntity<>(publicTrackerID, HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testMethod()
    {
        System.out.println("At start of /api/test method...");
        trackerService.testMethod();
        System.out.println("At end of /api/test method...");
        return new ResponseEntity<>("testing...", HttpStatus.OK);
    }

}
