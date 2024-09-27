package org.lobsterZelda.models;

// This class contains the fields that make up the payload of the JWT that is used to edit the seed.
public class JWTPayload
{
    private String publicTrackerID;
    private String trackerCreationDate;
    private String optionalTrackerCreatorUserName;

    public JWTPayload()
    {
    }

    public String getPublicTrackerID() {
        return publicTrackerID;
    }

    public void setPublicTrackerID(String publicTrackerID) {
        this.publicTrackerID = publicTrackerID;
    }

    public String getTrackerCreationDate() {
        return trackerCreationDate;
    }

    public void setTrackerCreationDate(String trackerCreationDate) {
        this.trackerCreationDate = trackerCreationDate;
    }

    public String getOptionalTrackerCreatorUserName() {
        return optionalTrackerCreatorUserName;
    }

    public void setOptionalTrackerCreatorUserName(String optionalTrackerCreatorUserName) {
        this.optionalTrackerCreatorUserName = optionalTrackerCreatorUserName;
    }

    @Override
    public String toString() {
        return "JWTPayload{" +
                "publicTrackerID='" + publicTrackerID + '\'' +
                ", trackerCreationDate='" + trackerCreationDate + '\'' +
                ", optionalTrackerCreatorUserName='" + optionalTrackerCreatorUserName + '\'' +
                '}';
    }
}
