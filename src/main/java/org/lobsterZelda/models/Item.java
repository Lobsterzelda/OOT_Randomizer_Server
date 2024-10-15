package org.lobsterZelda.models;

public class Item
{
    private int itemID;
    private int mapId;
    private String itemName;
    private String itemDescription;
    private boolean isOOTItem;
    private float percentFromLeftEdgeOfMap; // The percentage of the distance between the left and right edge of the map that the item is located at (after adding in the map offsets).
    private float percentFromTopEdgeOfMap; // The percentage of the distance between the top and bottom edge of the map that the item is located at (after adding in the map offsets).


    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public boolean getIsOOTItem() {
        return isOOTItem;
    }

    public void setIsOOTItem(boolean OOTItem) {
        isOOTItem = OOTItem;
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
