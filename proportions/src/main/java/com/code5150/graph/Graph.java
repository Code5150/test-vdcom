package com.code5150.graph;

import java.util.*;

public class Graph {
    private final HashMap<String, Node> nodes = new HashMap<>();

    public void addNode(String nodeId) {
        if (!nodes.containsKey(nodeId)) {
            nodes.put(nodeId, new Node(nodeId));
        }
    }

    public void addEdge(String startNodeId, double startNodeWeight, String endNodeId, double endNodeWeight) {
        var startNode = nodes.get(startNodeId);
        var endNode = nodes.get(endNodeId);
        startNode.addEdge(endNode, endNodeWeight / startNodeWeight);
        endNode.addEdge(startNode, startNodeWeight / endNodeWeight);
    }

    public Double calculatePath(String startNodeId, String endNodeId) {
        Double result = null;
        var nodes = findPath(startNodeId, endNodeId);
        if (nodes != null) {
            result = calculatePath(nodes);
        }
        return result;
    }

    public List<Node> findPath(String startNodeId, String endNodeId) {
        List<Node> result = null;
        if (nodes.containsKey(startNodeId) && nodes.containsKey(endNodeId)) {
            result = findPathRecursive(nodes.get(startNodeId), endNodeId, Collections.emptySet());
        }
        return result;
    }

    protected List<Node> findPathRecursive(Node currentNode, String endNodeId, Set<String> visited) {
        List<Node> result = null;
        if (!visited.contains(currentNode.getId())) {
            if (currentNode.getNeighbor(endNodeId) != null) {
                result = Arrays.asList(currentNode, currentNode.getNeighbor(endNodeId));
            } else {
                var currentVisited = new HashSet<>(visited);
                currentVisited.add(currentNode.getId());
                for (var node : currentNode.getAllNeighbors()) {
                    var chain = findPathRecursive(node, endNodeId, currentVisited);
                    if (chain != null) {
                        result = new ArrayList<>(chain);
                        result.add(0, currentNode);
                        break;
                    }
                }
            }
        }
        return result;
    }

    public Double calculatePath(List<Node> pathNodes) {
        double result = 1.0;
        for (int i = 1; i < pathNodes.size(); i++) {
            var nodePrev = pathNodes.get(i - 1);
            var node = pathNodes.get(i);
            result *= nodePrev.getWeight(node.getId());
        }
        return result;
    }
}
