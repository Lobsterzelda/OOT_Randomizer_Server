package org.lobsterZelda.models;

// This class contains the fields that make up the payload of the JWT that is used to edit the seed.
public class JWTPayload
{
    private String publicTrackerID;
    private Long trackerCreationDate; // Represents the number of seconds since January 1, 1970 00:00 AM, UTC
    private String optionalTrackerCreatorUserName;
    private Long jwtVersionNumber;

    public JWTPayload()
    {
    }

    public String getPublicTrackerID() {
        return publicTrackerID;
    }

    public void setPublicTrackerID(String publicTrackerID) {
        this.publicTrackerID = publicTrackerID;
    }

    public Long getTrackerCreationDate() {
        return trackerCreationDate;
    }

    public void setTrackerCreationDate(Long trackerCreationDate) {
        this.trackerCreationDate = trackerCreationDate;
    }

    public String getOptionalTrackerCreatorUserName() {
        return optionalTrackerCreatorUserName;
    }

    public void setOptionalTrackerCreatorUserName(String optionalTrackerCreatorUserName) {
        this.optionalTrackerCreatorUserName = optionalTrackerCreatorUserName;
    }

    public Long getJwtVersionNumber() {
        return jwtVersionNumber;
    }

    public void setJwtVersionNumber(Long jwtVersionNumber) {
        this.jwtVersionNumber = jwtVersionNumber;
    }

    @Override
    public String toString() {
        return "JWTPayload{" +
                "publicTrackerID='" + publicTrackerID + '\'' +
                ", trackerCreationDate='" + trackerCreationDate + '\'' +
                ", optionalTrackerCreatorUserName='" + optionalTrackerCreatorUserName + '\'' +
                ", jwtVersionNumber='" + jwtVersionNumber + '\'' +
                '}';
    }
}
