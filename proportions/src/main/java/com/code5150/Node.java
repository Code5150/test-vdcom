package com.code5150;

import java.util.Collection;
import java.util.HashMap;

public class Node {
    private final String id;
    private final double weight;
    private final HashMap<String, Node> edges = new HashMap<>();

    public String getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public Node(String id, double weight) {
        this.id = id;
        this.weight = weight;
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

    public void addEdge(Node node2) {
        edges.put(node2.id, node2);
    }

    public Node getNeighbor(String neighborId) {
        return edges.get(neighborId);
    }

    public Collection<Node> getAllNeighbors() {
        return edges.values();
    }
}
