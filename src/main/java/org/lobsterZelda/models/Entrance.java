package org.lobsterZelda.models;

// There are 2 types of Entrance objects: Those that represent a map (ex. "Fire Temple"), and those that represent entrances within a map (ex. "Fire Temple to DMC exit")
public class Entrance
{
    private int entranceID; // The ID for a location/map (ex. 1). 0 is the first map/area. Negative values represent special entrances, like warp songs and owl warps.
    private int mapEntranceID; // The ID of the map containing the entrance. If the entrance is a map, then this value is set to -1. Maps containing only 1 entrance (like certain houses and grottos) also have a value of -1 for this. Also, special entrances that don't exist inside of a map like childSaveWarp have a value of -1 for mapEntranceID as well.
    private int mapForDisplayEntranceID; // The ID of the map that the entrance is displayed on. For entrances that only appear in text form (like generic grottos), this is -1. This is the ID of the map that the entrance is displayed on - which is different from the map ID (the map ID is the actual map that the scene is located in).
    // Example: Kakariko Archery shop would have a mapEntranceID of -1, since it's an entrance in a scene with only one entrance. However, it would have a mapForDisplayEntranceId of Kakariko Village, since the archery shop is displayed in the Kakriko Village map.

    private String entranceName; // The printable name for a location/map (ex. "Spirit Temple main entrance to Desert Colossus").

    private boolean isOOTOwlEntrance; // True if this is a Kappora Gaebora flight in OOT (either the  owl at Lake Hylia or the top of DMT), and false otherwise.
    private boolean isOOTWarpSong; // True if this is an OOT warp song (like Bolero of Fire), and false otherwise.
    private boolean isOOTChildSaveWarp; // True if this is an entrance for a child OOT save warp, and false otherwise.
    private boolean isOOTAdultSaveWarp; // True if this is an entrance for an adult OOT save warp, and false otherwise.
    private boolean isMMSaveWarp; // True if this is an entrance for save-warping in MM (i.e. playing Song of Time), and false otherwise.
    private boolean isMMSongOfSoaringWarp; // True if this is where an MM owl-statue warp leads to, and false otherwise.
    private boolean isWallmasterWarp; // True if this is a one-way warp from getting caught by a Wallmaster, and false otherwise.
    private boolean isVoidPointWarp; // True if this is a one-way warp from voiding out in a certain location, and false otherwise.

    private boolean isOOTEntrance; // True if this is an OOT entrance, and false if this is an MM entrance.
    private boolean isInDungeon; // True if the entrance is in a dungeon map (or is a dungeon map)
    private boolean isBossRoom; // True if this is in a boss room (or is a boss room). Boss Room refers specifically to bosses which are behind a loading zone in vanilla gameplay.
    private boolean isInGrotto; // True if the entrance is in a grotto map (or is a grotto map)
    private boolean isInHouse; // True if the entrance is in an interior/house map (or is a grotto map)
    private boolean isAMap; // True if the Entrance represents a map (ex. "Hyrule Field" would have a value of true, but "Hyrule Field to Lost Woods Exit" would have a value of false).

    private boolean isOOTToMMEntrance; // True if this is the spot that leads from OOT to MM (i.e. the Happy Mask Shop), and false otherwise.
    private boolean isChildOnlyEntrance; // True if only child can access the entrance, and false otherwise.
    private boolean isAdultOnlyEntrance; // True if only adult can access the entrance, and false otherwise.

    public int getEntranceID() {
        return entranceID;
    }

    public void setEntranceID(int entranceID) {
        this.entranceID = entranceID;
    }

    public int getMapEntranceID() {
        return mapEntranceID;
    }

    public void setMapEntranceID(int mapEntranceID) {
        this.mapEntranceID = mapEntranceID;
    }

    public int getMapForDisplayEntranceID() {
        return mapForDisplayEntranceID;
    }

    public void setMapForDisplayEntranceID(int mapForDisplayEntranceID) {
        this.mapForDisplayEntranceID = mapForDisplayEntranceID;
    }

    public String getEntranceName() {
        return entranceName;
    }

    public void setEntranceName(String entranceName) {
        this.entranceName = entranceName;
    }

    public boolean getIsOOTOwlEntrance() {
        return isOOTOwlEntrance;
    }

    public void setIsOOTOwlEntrance(boolean OOTOwlEntrance) {
        isOOTOwlEntrance = OOTOwlEntrance;
    }

    public boolean getIsOOTWarpSong() {
        return isOOTWarpSong;
    }

    public void setIsOOTWarpSong(boolean OOTWarpSong) {
        isOOTWarpSong = OOTWarpSong;
    }

    public boolean getIsOOTChildSaveWarp() {
        return isOOTChildSaveWarp;
    }

    public void setIsOOTChildSaveWarp(boolean OOTChildSaveWarp) {
        isOOTChildSaveWarp = OOTChildSaveWarp;
    }

    public boolean getIsOOTAdultSaveWarp() {
        return isOOTAdultSaveWarp;
    }

    public void setIsOOTAdultSaveWarp(boolean OOTAdultSaveWarp) {
        isOOTAdultSaveWarp = OOTAdultSaveWarp;
    }

    public boolean getIsMMSaveWarp() {
        return isMMSaveWarp;
    }

    public void setIsMMSaveWarp(boolean MMSaveWarp) {
        isMMSaveWarp = MMSaveWarp;
    }

    public boolean getIsMMSongOfSoaringWarp() {
        return isMMSongOfSoaringWarp;
    }

    public void setIsMMSongOfSoaringWarp(boolean MMSongOfSoaringWarp) {
        isMMSongOfSoaringWarp = MMSongOfSoaringWarp;
    }

    public boolean getIsWallmasterWarp() {
        return isWallmasterWarp;
    }

    public void setIsWallmasterWarp(boolean wallmasterWarp) {
        isWallmasterWarp = wallmasterWarp;
    }

    public boolean getIsVoidPointWarp() {
        return isVoidPointWarp;
    }

    public void setIsVoidPointWarp(boolean voidPointWarp) {
        isVoidPointWarp = voidPointWarp;
    }

    public boolean getIsOOTEntrance() {
        return isOOTEntrance;
    }

    public void setIsOOTEntrance(boolean OOTEntrance) {
        isOOTEntrance = OOTEntrance;
    }

    public boolean getIsInDungeon() {
        return isInDungeon;
    }

    public void setIsInDungeon(boolean inDungeon) {
        isInDungeon = inDungeon;
    }

    public boolean getIsBossRoom() {
        return isBossRoom;
    }

    public void setIsBossRoom(boolean bossRoom) {
        isBossRoom = bossRoom;
    }

    public boolean getIsInGrotto() {
        return isInGrotto;
    }

    public void setIsInGrotto(boolean inGrotto) {
        isInGrotto = inGrotto;
    }

    public boolean getIsInHouse() {
        return isInHouse;
    }

    public void setIsInHouse(boolean inHouse) {
        isInHouse = inHouse;
    }

    public boolean getIsAMap() {
        return isAMap;
    }

    public void setIsAMap(boolean AMap) {
        isAMap = AMap;
    }

    public boolean getIsOOTToMMEntrance() {
        return isOOTToMMEntrance;
    }

    public void setIsOOTToMMEntrance(boolean OOTToMMEntrance) {
        isOOTToMMEntrance = OOTToMMEntrance;
    }

    public boolean getIsChildOnlyEntrance() {
        return isChildOnlyEntrance;
    }

    public void setIsChildOnlyEntrance(boolean childOnlyEntrance) {
        isChildOnlyEntrance = childOnlyEntrance;
    }

    public boolean getIsAdultOnlyEntrance() {
        return isAdultOnlyEntrance;
    }

    public void setIsAdultOnlyEntrance(boolean adultOnlyEntrance) {
        isAdultOnlyEntrance = adultOnlyEntrance;
    }
}
