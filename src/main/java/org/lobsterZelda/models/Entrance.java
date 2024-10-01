package org.lobsterZelda.models;

// There are 2 types of Entrance objects: Those that represent a map (ex. "Fire Temple"), and those that represent entrances within a map (ex. "Fire Temple to DMC exit")
public class Entrance
{
    private Integer entranceID; // The ID for a location/map (ex. 1). 1 is the first map/area. Negative values represent special entrances, like warp songs and owl warps. 0 is unused.
    private String entranceName; // The printable name for a location/map (ex. "Spirit Temple main entrance to Desert Colossus").

    private Boolean isOOTOwlEntrance; // True if this is a Kappora Gaebora flight in OOT (either the  owl at Lake Hylia or the top of DMT), and false otherwise.
    private Boolean isOOTWarpSong; // True if this is an OOT warp song (like Bolero of Fire), and false otherwise.
    private Boolean isOOTChildSaveWarp; // True if this is an entrance for a child OOT save warp, and false otherwise.
    private Boolean isOOTAdultSaveWarp; // True if this is an entrance for an adult OOT save warp, and false otherwise.
    private Boolean isMMSaveWarp; // True if this is an entrance for save-warping in MM (i.e. playing Song of Time), and false otherwise.
    private Boolean isMMSongOfSoaringWarp; // True if this is where an MM owl-statue warp leads to, and false otherwise.
    private Boolean isWallmasterWarp; // True if this is a one-way warp from getting caught by a Wallmaster, and false otherwise.
    private Boolean isVoidPointWarp; // True if this is a one-way warp from voiding out in a certain location, and false otherwise.

    private Boolean isOOTEntrance; // True if this is an OOT entrance, and false if this is an MM entrance.
    private Boolean isInDungeon; // True if the entrance is in a dungeon map (or is a dungeon map)
    private Boolean isBossRoom; // True if this is in a boss room (or is a boss room). Boss Room refers specifically to bosses which are behind a loading zone in vanilla gameplay.
    private Boolean isInGrotto; // True if the entrance is in a grotto map (or is a grotto map)
    private Boolean isInHouse; // True if the entrance is in an interior/house map (or is a grotto map)
    private Boolean isAMap; // True if the Entrance represents a map (ex. "Hyrule Field" would have a value of true, but "Hyrule Field to Lost Woods Exit" would have a value of false).

    private Boolean isOOTToMMEntrance; // True if this is the spot that leads from OOT to MM (i.e. the Happy Mask Shop), and false otherwise.
    private Boolean isChildOnlyEntrance; // True if only child can access the entrance, and false otherwise.
    private Boolean isAdultOnlyEntrance; // True if only adult can access the entrance, and false otherwise.
}
