package org.lobsterZelda.models;

// This class stores the settings that were used to create a seed.
public class SeedCreationSettings {

    // A valid seed must have one or both of the "includesOOTLocations" and "includesMMLocations"
    // variables set to true in order to be a valid seed
    // (otherwise, no locations from OOT or MM would be in the seed, which makes no sense).

    private boolean includesOOTLocations = false; // True if OOT locations are in the seed, and false otherwise.
    private boolean includeMQDungeons = false; // True if MQ dungeons are in the OOT dungeon pool, and false otherwise.
    private boolean includesMMLocations = false; // True if MM locations are in the seed, and false otherwise.

    private boolean randomizeOOTWarpSongs = false; // True if OOT Warp songs (ex. Bolero) can take you to random locations, and false otherwise.
    private boolean randomizeOOTOwlLocations = false; // True if the owl in OOT can take you to random locations, and false otherwise.
    private boolean randomizeOOTWallmasters = false; // True if OOT wallmasters can take the player to a random location, and false otherwise.
    private boolean randomizeOOTThievesHideoutLocations = false; // True if each of the OOT Thieves Hideout Locations is randomized, and false otherwise (since the map gets really cluttered if all of these entrances are displayed, and they usually aren't randomized, and can therefore be hidden).

    private boolean randomizeMMSongOfSoaringWarps = false; // True if the locations that each song-of-soaring location can take the player to are randomized, and false otherwise.
    private boolean randomizeMMWallmasters = false; // True if MM wallmasters can take the player to a random location, and false otherwise.
    private boolean randomizeMMWaterVoidouts = false; // True if voiding out in the water near GreatBay can take the player to a random location, and false otherwise.

    private boolean randomizeLocationOfGameChange = false; // True if the location of the happy mask shop (in combo-randomizers) is randomized, and false otherwise. In non-combo randos, this value is set to false and ignored.

    private boolean randomizeOOTChildSavewarpLocation = false; // True if child savewarp location in OOT is randomized, and false otherwise. This value is set to false and ignored if OOT locations are not part of the randomizer.
    private boolean randomizeOOTAdultSavewarpLocation = false; // True if adult savewarp location in OOT is randomized, and false otherwise. This value is set to false and ignored if OOT locations are not part of the randomizer.
    private boolean randomizeMMSavewarpLocation = false; // True if the savewarp location in MM (i.e. where Link spawns after playing Song of Time in MM) is randomized, and false otherwise. This value is set to false and ignored if MM locations are not part of the randomizer.

    private boolean decoupledEntrances = false;

    private boolean randomizeOOTItems = false; // True if OOT items are both included in the seed and randomized, and false otherwise.
    private boolean randomizeMMItems = false; // True if MM items are both included in the seed and randomized, and false otherwise.

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

    public boolean isRandomizeOOTWarpSongs() {
        return randomizeOOTWarpSongs;
    }

    public void setRandomizeOOTWarpSongs(boolean randomizeOOTWarpSongs) {
        this.randomizeOOTWarpSongs = randomizeOOTWarpSongs;
    }

    public boolean isRandomizeOOTOwlLocations() {
        return randomizeOOTOwlLocations;
    }

    public void setRandomizeOOTOwlLocations(boolean randomizeOOTOwlLocations) {
        this.randomizeOOTOwlLocations = randomizeOOTOwlLocations;
    }

    public boolean isRandomizeOOTWallmasters() {
        return randomizeOOTWallmasters;
    }

    public void setRandomizeOOTWallmasters(boolean randomizeOOTWallmasters) {
        this.randomizeOOTWallmasters = randomizeOOTWallmasters;
    }

    public boolean isRandomizeOOTThievesHideoutLocations() {
        return randomizeOOTThievesHideoutLocations;
    }

    public void setRandomizeOOTThievesHideoutLocations(boolean randomizeOOTThievesHideoutLocations) {
        this.randomizeOOTThievesHideoutLocations = randomizeOOTThievesHideoutLocations;
    }

    public boolean isRandomizeMMSongOfSoaringWarps() {
        return randomizeMMSongOfSoaringWarps;
    }

    public void setRandomizeMMSongOfSoaringWarps(boolean randomizeMMSongOfSoaringWarps) {
        this.randomizeMMSongOfSoaringWarps = randomizeMMSongOfSoaringWarps;
    }

    public boolean isRandomizeMMWallmasters() {
        return randomizeMMWallmasters;
    }

    public void setRandomizeMMWallmasters(boolean randomizeMMWallmasters) {
        this.randomizeMMWallmasters = randomizeMMWallmasters;
    }

    public boolean isRandomizeMMWaterVoidouts() {
        return randomizeMMWaterVoidouts;
    }

    public void setRandomizeMMWaterVoidouts(boolean randomizeMMWaterVoidouts) {
        this.randomizeMMWaterVoidouts = randomizeMMWaterVoidouts;
    }

    public boolean isRandomizeLocationOfGameChange() {
        return randomizeLocationOfGameChange;
    }

    public void setRandomizeLocationOfGameChange(boolean randomizeLocationOfGameChange) {
        this.randomizeLocationOfGameChange = randomizeLocationOfGameChange;
    }

    public boolean isRandomizeOOTChildSavewarpLocation() {
        return randomizeOOTChildSavewarpLocation;
    }

    public void setRandomizeOOTChildSavewarpLocation(boolean randomizeOOTChildSavewarpLocation) {
        this.randomizeOOTChildSavewarpLocation = randomizeOOTChildSavewarpLocation;
    }

    public boolean isRandomizeOOTAdultSavewarpLocation() {
        return randomizeOOTAdultSavewarpLocation;
    }

    public void setRandomizeOOTAdultSavewarpLocation(boolean randomizeOOTAdultSavewarpLocation) {
        this.randomizeOOTAdultSavewarpLocation = randomizeOOTAdultSavewarpLocation;
    }

    public boolean isRandomizeMMSavewarpLocation() {
        return randomizeMMSavewarpLocation;
    }

    public void setRandomizeMMSavewarpLocation(boolean randomizeMMSavewarpLocation) {
        this.randomizeMMSavewarpLocation = randomizeMMSavewarpLocation;
    }

    public boolean isDecoupledEntrances() {
        return decoupledEntrances;
    }

    public void setDecoupledEntrances(boolean decoupledEntrances) {
        this.decoupledEntrances = decoupledEntrances;
    }

    public boolean isRandomizeOOTItems() {
        return randomizeOOTItems;
    }

    public void setRandomizeOOTItems(boolean randomizeOOTItems) {
        this.randomizeOOTItems = randomizeOOTItems;
    }

    public boolean isRandomizeMMItems() {
        return randomizeMMItems;
    }

    public void setRandomizeMMItems(boolean randomizeMMItems) {
        this.randomizeMMItems = randomizeMMItems;
    }

    @Override
    public String toString() {
        return "SeedCreationSettings{" +
                "includesOOTLocations=" + includesOOTLocations +
                ", includeMQDungeons=" + includeMQDungeons +
                ", includesMMLocations=" + includesMMLocations +
                ", randomizeOOTWarpSongs=" + randomizeOOTWarpSongs +
                ", randomizeOOTOwlLocations=" + randomizeOOTOwlLocations +
                ", randomizeOOTWallmasters=" + randomizeOOTWallmasters +
                ", randomizeOOTThievesHideoutLocations=" + randomizeOOTThievesHideoutLocations +
                ", randomizeMMSongOfSoaringWarps=" + randomizeMMSongOfSoaringWarps +
                ", randomizeMMWallmasters=" + randomizeMMWallmasters +
                ", randomizeMMWaterVoidouts=" + randomizeMMWaterVoidouts +
                ", randomizeLocationOfGameChange=" + randomizeLocationOfGameChange +
                ", randomizeOOTChildSavewarpLocation=" + randomizeOOTChildSavewarpLocation +
                ", randomizeOOTAdultSavewarpLocation=" + randomizeOOTAdultSavewarpLocation +
                ", randomizeMMSavewarpLocation=" + randomizeMMSavewarpLocation +
                ", decoupledEntrances=" + decoupledEntrances +
                ", randomizeOOTItems=" + randomizeOOTItems +
                ", randomizeMMItems=" + randomizeMMItems +
                '}';
    }
}
