package org.lobsterZelda.models;

public class ItemCheckLocation
{
    private int itemCheckLocationId;
    private int mapId;
    private String itemCheckLocationName;
    private String itemCheckLocationDescription;
    private boolean isOOTItemCheckLocation;

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
}
