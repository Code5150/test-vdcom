package com.code5150.graph;

public class Edge {
    private double weight;
    private Node node;

    public Edge(Node node, double weight) {
        this.node = node;
        this.weight = weight;
    }


    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
