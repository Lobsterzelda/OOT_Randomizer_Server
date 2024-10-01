package org.lobsterZelda.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntranceGraph
{
    private final Map<Integer, Entrance> idToEntranceMap = new HashMap<>();
    private final Map<String, Entrance> nameToEntranceMap = new HashMap<>();
    private final Map<Integer, List<WeightedVertex>> idToConnectedEntrancesAdjacencyList = new HashMap<>();

    public Map<Integer, Entrance> getIdToEntranceMap() {
        return idToEntranceMap;
    }

    public Map<String, Entrance> getNameToEntranceMap() {
        return nameToEntranceMap;
    }

    public Map<Integer, List<WeightedVertex>> getIdToConnectedEntrancesAdjacencyList() {
        return idToConnectedEntrancesAdjacencyList;
    }
}
