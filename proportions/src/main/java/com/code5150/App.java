package com.code5150;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {
        var graph = new Graph();
        var sc = new Scanner(System.in);
        var pattern = Pattern.compile("(\\d+(\\.?\\d+)?)\\s+(.+)\\s+\\=+\\s+(\\d+(\\.?\\d+)?)\\s+(.+)");
        while (true) {
            var input = sc.nextLine();
            if (!input.isBlank()) {
                var data = pattern.matcher(sc.nextLine().trim());
                if (data.matches()) {
                    //groups 1, 3, 4, 6
                    var node1 = new Node(data.group(3), Double.parseDouble(data.group(1)));
                    var node2 = new Node(data.group(6), Double.parseDouble(data.group(4)));
                    node1.addEdge(node2);
                    node2.addEdge(node1);
                    graph.addNode(node1);
                    graph.addNode(node2);
                } else {
                    System.out.println("Failed to read date. Please, try again: ");
                }
            } else {
                break;
            }
        }
    }
}
