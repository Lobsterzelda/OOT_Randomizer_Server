package org.lobsterZelda.models;

// This class stores the settings that were used to create a seed.
public class SeedCreationSettings {

    // A valid seed must have one or both of the "includesOOTLocations" and "includesMMLocations"
    // variables set to true in order to be a valid seed
    // (otherwise, no locations from OOT or MM would be in the seed, which makes no sense).
    private boolean includesOOTLocations = false; // True if OOT locations are in the seed, and false otherwise.
    private boolean includeMQDungeons = false; // True if MQ dungeons are in the OOT dungeon pool, and false otherwise.
    private boolean includesMMLocations = false; // True if MM locations are in the seed, and false otherwise.

    private boolean randomizedOOTWarpSongs = false; // True if OOT Warp songs (ex. Bolero) can take you to random locations, and false otherwise.
    private boolean randomizedOOTOwlLocations = false; // True if the owl in OOT can take you to random locations, and false otherwise.
    private boolean randomizedOOTWallmasters = false; // True if OOT wallmasters can take the player to a random location, and false otherwise.
    private boolean randomizedOOTThievesHideoutLocations = false; // True if each of the OOT Thieves Hideout Locations is randomized, and false otherwise (since the map gets really cluttered if all of these entrances are displayed, and they usually aren't randomized, and can therefore be hidden).

    private boolean randomizedMMSongOfSoaringWarps = false; // True if the locations that each song-of-soaring location can take the player to are randomized, and false otherwise.
    private boolean randomizedMMWallmasters = false; // True if MM wallmasters can take the player to a random location, and false otherwise.
    private boolean randomizedMMWaterVoidouts = false; // True if voiding out in the water near GreatBay can take the player to a random location, and false otherwise.

    private boolean randomizedLocationOfGameChange = false; // True if the location of the happy mask shop (in combo-randomizers) is randomized, and false otherwise. In non-combo randos, this value is set to false and ignored.

    private boolean randomizedOOTChildSavewarpLocation = false; // True if child savewarp location in OOT is randomized, and false otherwise. This value is set to false and ignored if OOT locations are not part of the randomizer.
    private boolean randomizedOOTAdultSavewarpLocation = false; // True if adult savewarp location in OOT is randomized, and false otherwise. This value is set to false and ignored if OOT locations are not part of the randomizer.
    private boolean randomizedMMSavewarpLocation = false; // True if the savewarp location in MM (i.e. where Link spawns after playing Song of Time in MM) is randomized, and false otherwise. This value is set to false and ignored if MM locations are not part of the randomizer.

    private boolean decoupledEntrances = false;

    private boolean randomizedOOTItems = false; // True if OOT items are both included in the seed and randomized, and false otherwise.
    private boolean randomizedMMItems = false; // True if MM items are both included in the seed and randomized, and false otherwise.

    private boolean allowOOTWarpSongsInMM = false; // True if OOT warp songs work in MM, and false otherwise. If this is not a combo-rando, then this value is set to false and ignored.
    private boolean allowMMWarpSongInOOT = false; // True if song of soaring can be played in OOT, and false otherwise. If this is not a combo-rando, then this value is set to false and ignored.

    private String optionalTrackerCreatorUserName =  null; // The user can optionally set a username to be associated with their Tracker.

    private int jwtVersionId = -1;

    public SeedCreationSettings() {}

    public boolean isIncludesOOTLocations() {
        return includesOOTLocations;
    }

    public void setIncludesOOTLocations(boolean includesOOTLocations) {
        this.includesOOTLocations = includesOOTLocations;
    }

    public boolean isIncludeMQDungeons() {
        return includeMQDungeons;
    }

    public void setIncludeMQDungeons(boolean includeMQDungeons) {
        this.includeMQDungeons = includeMQDungeons;
    }

    public boolean isIncludesMMLocations() {
        return includesMMLocations;
    }

    public void setIncludesMMLocations(boolean includesMMLocations) {
        this.includesMMLocations = includesMMLocations;
    }

    public boolean isRandomizedOOTWarpSongs() {
        return randomizedOOTWarpSongs;
    }

    public void setRandomizedOOTWarpSongs(boolean randomizedOOTWarpSongs) {
        this.randomizedOOTWarpSongs = randomizedOOTWarpSongs;
    }

    public boolean isRandomizedOOTOwlLocations() {
        return randomizedOOTOwlLocations;
    }

    public void setRandomizedOOTOwlLocations(boolean randomizedOOTOwlLocations) {
        this.randomizedOOTOwlLocations = randomizedOOTOwlLocations;
    }

    public boolean isRandomizedOOTWallmasters() {
        return randomizedOOTWallmasters;
    }

    public void setRandomizedOOTWallmasters(boolean randomizedOOTWallmasters) {
        this.randomizedOOTWallmasters = randomizedOOTWallmasters;
    }

    public boolean isRandomizedOOTThievesHideoutLocations() {
        return randomizedOOTThievesHideoutLocations;
    }

    public void setRandomizedOOTThievesHideoutLocations(boolean randomizedOOTThievesHideoutLocations) {
        this.randomizedOOTThievesHideoutLocations = randomizedOOTThievesHideoutLocations;
    }

    public boolean isRandomizedMMSongOfSoaringWarps() {
        return randomizedMMSongOfSoaringWarps;
    }

    public void setRandomizedMMSongOfSoaringWarps(boolean randomizedMMSongOfSoaringWarps) {
        this.randomizedMMSongOfSoaringWarps = randomizedMMSongOfSoaringWarps;
    }

    public boolean isRandomizedMMWallmasters() {
        return randomizedMMWallmasters;
    }

    public void setRandomizedMMWallmasters(boolean randomizedMMWallmasters) {
        this.randomizedMMWallmasters = randomizedMMWallmasters;
    }

    public boolean isRandomizedMMWaterVoidouts() {
        return randomizedMMWaterVoidouts;
    }

    public void setRandomizedMMWaterVoidouts(boolean randomizedMMWaterVoidouts) {
        this.randomizedMMWaterVoidouts = randomizedMMWaterVoidouts;
    }

    public boolean isRandomizedLocationOfGameChange() {
        return randomizedLocationOfGameChange;
    }

    public void setRandomizedLocationOfGameChange(boolean randomizedLocationOfGameChange) {
        this.randomizedLocationOfGameChange = randomizedLocationOfGameChange;
    }

    public boolean isRandomizedOOTChildSavewarpLocation() {
        return randomizedOOTChildSavewarpLocation;
    }

    public void setRandomizedOOTChildSavewarpLocation(boolean randomizedOOTChildSavewarpLocation) {
        this.randomizedOOTChildSavewarpLocation = randomizedOOTChildSavewarpLocation;
    }

    public boolean isRandomizedOOTAdultSavewarpLocation() {
        return randomizedOOTAdultSavewarpLocation;
    }

    public void setRandomizeOOTAdultSavewarpLocation(boolean randomizedOOTAdultSavewarpLocation) {
        this.randomizedOOTAdultSavewarpLocation = randomizedOOTAdultSavewarpLocation;
    }

    public boolean isRandomizedMMSavewarpLocation() {
        return randomizedMMSavewarpLocation;
    }

    public void setRandomizedMMSavewarpLocation(boolean randomizedMMSavewarpLocation) {
        this.randomizedMMSavewarpLocation = randomizedMMSavewarpLocation;
    }

    public boolean isDecoupledEntrances() {
        return decoupledEntrances;
    }

    public void setDecoupledEntrances(boolean decoupledEntrances) {
        this.decoupledEntrances = decoupledEntrances;
    }

    public boolean isRandomizedOOTItems() {
        return randomizedOOTItems;
    }

    public void setRandomizedOOTItems(boolean randomizedOOTItems) {
        this.randomizedOOTItems = randomizedOOTItems;
    }

    public boolean isRandomizeMMItems() {
        return randomizedMMItems;
    }

    public void setRandomizeMMItems(boolean randomizedMMItems) {
        this.randomizedMMItems = randomizedMMItems;
    }

    public boolean isAllowOOTWarpSongsInMM() {
        return allowOOTWarpSongsInMM;
    }

    public void setAllowOOTWarpSongsInMM(boolean allowOOTWarpSongsInMM) {
        this.allowOOTWarpSongsInMM = allowOOTWarpSongsInMM;
    }

    public boolean isAllowMMWarpSongInOOT() {
        return allowMMWarpSongInOOT;
    }

    public void setAllowMMWarpSongInOOT(boolean allowMMWarpSongInOOT) {
        this.allowMMWarpSongInOOT = allowMMWarpSongInOOT;
    }

    public String getOptionalTrackerCreatorUserName() {
        return optionalTrackerCreatorUserName;
    }

    public void setOptionalTrackerCreatorUserName(String optionalTrackerCreatorUserName) {
        this.optionalTrackerCreatorUserName = optionalTrackerCreatorUserName;
    }

    public int getJwtVersionId() {
        return jwtVersionId;
    }

    public void setJwtVersionId(int jwtVersionId) {
        this.jwtVersionId = jwtVersionId;
    }

    @Override
    public String toString() {
        return "SeedCreationSettings{" +
                "includesOOTLocations=" + includesOOTLocations +
                ", includeMQDungeons=" + includeMQDungeons +
                ", includesMMLocations=" + includesMMLocations +
                ", randomizedOOTWarpSongs=" + randomizedOOTWarpSongs +
                ", randomizedOOTOwlLocations=" + randomizedOOTOwlLocations +
                ", randomizedOOTWallmasters=" + randomizedOOTWallmasters +
                ", randomizedOOTThievesHideoutLocations=" + randomizedOOTThievesHideoutLocations +
                ", randomizedMMSongOfSoaringWarps=" + randomizedMMSongOfSoaringWarps +
                ", randomizedMMWallmasters=" + randomizedMMWallmasters +
                ", randomizedMMWaterVoidouts=" + randomizedMMWaterVoidouts +
                ", randomizedLocationOfGameChange=" + randomizedLocationOfGameChange +
                ", randomizedOOTChildSavewarpLocation=" + randomizedOOTChildSavewarpLocation +
                ", randomizedOOTAdultSavewarpLocation=" + randomizedOOTAdultSavewarpLocation +
                ", randomizedMMSavewarpLocation=" + randomizedMMSavewarpLocation +
                ", decoupledEntrances=" + decoupledEntrances +
                ", randomizedOOTItems=" + randomizedOOTItems +
                ", randomizedMMItems=" + randomizedMMItems +
                ", allowOOTWarpSongsInMM=" + allowOOTWarpSongsInMM +
                ", allowMMWarpSongInOOT=" + allowMMWarpSongInOOT +
                ", optionalTrackerCreatorUserName=" + optionalTrackerCreatorUserName +
                ", jwtVersionId=" + jwtVersionId +
                '}';
    }
}
