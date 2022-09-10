package com.code5150;

import java.util.*;

public class Graph {
    private final HashMap<String, Node> nodes = new HashMap<>();

    public void addNode(Node node) {
        nodes.put(node.getId(), node);
    }

    public List<Node> findPath(String startNodeId, String endNodeId) {
        List<Node> result = null;
        if (nodes.containsKey(startNodeId) && nodes.containsKey(endNodeId)) {
            result = findPathRecursive(nodes.get(startNodeId), endNodeId);
        }
        return result;
    }

    protected List<Node> findPathRecursive(Node currentNode, String endNodeId) {
        List<Node> result = null;
        if (currentNode.getNeighbor(endNodeId) != null) {
            result = Collections.singletonList(currentNode.getNeighbor(endNodeId));
        } else {
            for (var node: currentNode.getAllNeighbors()) {
                var chain = findPathRecursive(node, endNodeId);
                if (chain != null) {
                    result = new ArrayList<>(chain);
                    result.add(0, currentNode);
                }
            }
        }
        return result;
    }

    public Double calculatePath(String startNodeId, String endNodeId) {
        return 0.0;
    }
}
