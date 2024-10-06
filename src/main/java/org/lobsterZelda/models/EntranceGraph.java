package org.lobsterZelda.models;

import java.util.ArrayList;
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

    public EntranceGraph deepCopy() {
        EntranceGraph returnGraph = new EntranceGraph();

        for (Integer id : this.idToEntranceMap.keySet())
            returnGraph.idToEntranceMap.put(new Integer(id), generateEntranceDeepCopy(this.idToEntranceMap.get(id)));

        for (String entranceName : this.nameToEntranceMap.keySet())
            returnGraph.nameToEntranceMap.put(entranceName, generateEntranceDeepCopy(this.nameToEntranceMap.get(entranceName)));

        for (Integer id : this.idToConnectedEntrancesAdjacencyList.keySet())
            returnGraph.idToConnectedEntrancesAdjacencyList.put(new Integer(id), generateWeightedVertexListDeepCopy(this.idToConnectedEntrancesAdjacencyList.get(id)));

        return returnGraph;
    }

    private Entrance generateEntranceDeepCopy(Entrance input)
    {
        if (input == null)
            return null;
        Entrance output = new Entrance();
        output.setEntranceID(input.getEntranceID());
        output.setEntranceName(input.getEntranceName());
        output.setIsAdultOnlyEntrance(input.getIsAdultOnlyEntrance());
        output.setIsAMap(input.getIsAMap());
        output.setIsChildOnlyEntrance(input.getIsChildOnlyEntrance());
        output.setIsBossRoom(input.getIsBossRoom());
        output.setIsInDungeon(input.getIsInDungeon());
        output.setIsInGrotto(input.getIsInGrotto());
        output.setIsOOTEntrance(input.getIsOOTEntrance());
        output.setIsInHouse(input.getIsInHouse());
        output.setIsOOTToMMEntrance(input.getIsOOTToMMEntrance());
        output.setMapEntranceID(input.getMapEntranceID());
        output.setMapForDisplayEntranceID(input.getMapForDisplayEntranceID());
        output.setIsMMSaveWarp(input.getIsMMSaveWarp());
        output.setIsOOTOwlEntrance(input.getIsOOTOwlEntrance());
        output.setIsMMSongOfSoaringWarp(input.getIsMMSongOfSoaringWarp());
        output.setIsOOTAdultSaveWarp(input.getIsOOTAdultSaveWarp());
        output.setIsOOTChildSaveWarp(input.getIsOOTChildSaveWarp());
        output.setIsOOTWarpSong(input.getIsOOTWarpSong());
        output.setIsVoidPointWarp(input.getIsVoidPointWarp());
        output.setIsWallmasterWarp(input.getIsWallmasterWarp());
        return output;
    }

    private List<WeightedVertex> generateWeightedVertexListDeepCopy(List<WeightedVertex> inputList)
    {
        if (inputList == null)
            return null;
        int listSize = inputList.size();
        List<WeightedVertex> outputList = new ArrayList<>();

        for (int i = 0; i < listSize; ++i)
        {
            WeightedVertex originalVertex = inputList.get(i);
            WeightedVertex newVertex = new WeightedVertex(new Integer(originalVertex.getDestinationID()), new Integer(originalVertex.getWeight()));
            outputList.add(newVertex);
        }
        return outputList;
    }
}
