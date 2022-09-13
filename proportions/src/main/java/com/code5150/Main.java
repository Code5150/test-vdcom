package com.code5150;

import com.code5150.graph.Graph;
import com.code5150.graph.Node;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        var graph = new Graph();
        var sc = new Scanner(System.in);
        var pattern = Pattern.compile("(\\d+(\\.?\\d+)?)\\s+(.+)\\s+\\=+\\s+(\\d+(\\.?\\d+)?)\\s+(.+)");
        while (true) {
            var input = sc.nextLine();
            if (!input.isBlank()) {
                var data = pattern.matcher(input.trim());
                if (data.matches()) {
                    //groups 1, 3, 4, 6
                    var startNodeId = data.group(3);
                    var endNodeId = data.group(6);
                    graph.addNode(startNodeId);
                    graph.addNode(endNodeId);
                    var startNodeWeight = Double.parseDouble(data.group(1));
                    var endNodeWeight = Double.parseDouble(data.group(4));
                    graph.addEdge(startNodeId, startNodeWeight, endNodeId, endNodeWeight);
                } else {
                    System.out.println("Failed to read data. Please, try again: ");
                    sc.nextLine();
                }
            } else {
                break;
            }
        }
        pattern = Pattern.compile("(\\d+(\\.?\\d+)?)\\s+(.+)\\s+\\=+\\s+\\??\\s+(.+)");
        var result = new ArrayList<String>();
        while (true) {
            var input = sc.nextLine();
            if (!input.isBlank()) {
                var data = pattern.matcher(input.trim());
                if (data.matches()) {
                    var count = Double.parseDouble(data.group(1));
                    var countStr = count % 1 == 0 ? String.valueOf((int) count) : String.valueOf(count);

                    var startNodeId = data.group(3);
                    var endNodeId = data.group(4);

                    var resultCount = graph.calculatePath(startNodeId, endNodeId);
                    if (resultCount != null) {
                        resultCount *= count;
                    }
                    var resultCountStr = resultCount != null
                            ? (resultCount % 1 == 0
                                    ? String.valueOf(resultCount.intValue())
                                    : String.valueOf(resultCount)) + " " + endNodeId
                            : "Conversion not possible.";

                    result.add(countStr + " " + startNodeId + " = " + resultCountStr);
                } else {
                    System.out.println("Failed to read data. Please, try again: ");
                    sc.nextLine();
                }
            } else {
                break;
            }
        }
        result.forEach(System.out::println);
    }
}
