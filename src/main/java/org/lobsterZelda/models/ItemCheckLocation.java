package org.lobsterZelda.models;

public class ItemCheckLocation
{
    private int itemCheckLocationId;
    private int mapId;
    private String itemCheckLocationName;
    private String itemCheckLocationDescription;
    private boolean isOOTItemCheckLocation;
    private float percentFromLeftEdgeOfMap; // The percentage of the distance between the left and right edge of the map that the itemCheckLocation is located at (after adding in the map offsets).
    private float percentFromTopEdgeOfMap; // The percentage of the distance between the top and bottom edge of the map that the itemCheckLocation is located at (after adding in the map offsets).


    public int getItemCheckLocationId() {
        return itemCheckLocationId;
    }

    public void setItemCheckLocationId(int itemCheckLocationId) {
        this.itemCheckLocationId = itemCheckLocationId;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public String getItemCheckLocationName() {
        return itemCheckLocationName;
    }

    public void setItemCheckLocationName(String itemCheckLocationName) {
        this.itemCheckLocationName = itemCheckLocationName;
    }

    public String getItemCheckLocationDescription() {
        return itemCheckLocationDescription;
    }

    public void setItemCheckLocationDescription(String itemCheckLocationDescription) {
        this.itemCheckLocationDescription = itemCheckLocationDescription;
    }

    public boolean getIsOOTItemCheckLocation() {
        return isOOTItemCheckLocation;
    }

    public void setIsOOTItemCheckLocation(boolean OOTItemCheckLocation) {
        isOOTItemCheckLocation = OOTItemCheckLocation;
    }

    public float getPercentFromLeftEdgeOfMap() {
        return percentFromLeftEdgeOfMap;
    }

    public void setPercentFromLeftEdgeOfMap(float percentFromLeftEdgeOfMap) {
        this.percentFromLeftEdgeOfMap = percentFromLeftEdgeOfMap;
    }

    public float getPercentFromTopEdgeOfMap() {
        return percentFromTopEdgeOfMap;
    }

    public void setPercentFromTopEdgeOfMap(float percentFromTopEdgeOfMap) {
        this.percentFromTopEdgeOfMap = percentFromTopEdgeOfMap;
    }
}
