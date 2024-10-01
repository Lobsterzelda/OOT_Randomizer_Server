package org.lobsterZelda.models;

// Used in the adjacency list for the graph, where the source node ID is the key, and the destination is represented by a WeightedVertex object.
public class WeightedVertex
{
    Integer destinationID;
    Integer weight;

    public Integer getDestinationID() {
        return destinationID;
    }

    public void setDestinationID(Integer destinationID) {
        this.destinationID = destinationID;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
