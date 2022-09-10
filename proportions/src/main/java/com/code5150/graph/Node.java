package com.code5150.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Node {
    private final String id;
    private final HashMap<String, Edge> edges = new HashMap<>();

    public String getId() {
        return id;
    }

    public Double getWeight(String nodeId) {
        Double result = null;
        if (edges.containsKey(nodeId)) {
            result = edges.get(nodeId).getWeight();
        }
        return result;
    }

    public Node(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id.equals(node.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public void addEdge(Node node2, double weight) {
        edges.put(node2.id, new Edge(node2, weight));
    }

    public Node getNeighbor(String neighborId) {
        Node result = null;
        if (edges.containsKey(neighborId)) {
            result = edges.get(neighborId).getNode();
        }
        return result;
    }

    public Collection<Node> getAllNeighbors() {
        return edges.values().stream().map(Edge::getNode).collect(Collectors.toList());
    }
}
