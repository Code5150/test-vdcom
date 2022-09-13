package com.code5150;

import com.code5150.graph.Graph;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class AppTest {
    @Test
    public void testGraph1() {
        var graph = new Graph();
        var proportions = Arrays.asList(
                "1024 byte = 1 kilobyte",
                "2 bar = 12 ring",
                "16.8 ring = 2 pyramid",
                "4 hare = 1 cat",
                "5 cat = 0.5 giraffe",
                "1 byte = 8 bit",
                "15 ring = 2.5 bar");

        var pattern = Pattern.compile("(\\d+(\\.?\\d+)?)\\s+(.+)\\s+\\=+\\s+(\\d+(\\.?\\d+)?)\\s+(.+)");
        for (var input: proportions) {
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
                    System.err.println("Failed to read data.");
                }
            }
        }

        var targets = Arrays.asList("1 pyramid = ? bar",
                "1 giraffe = ? hare",
                "0.5 byte = ? cat",
                "2 kilobyte = ? bit");

        pattern = Pattern.compile("(\\d+(\\.?\\d+)?)\\s+(.+)\\s+\\=+\\s+\\??\\s+(.+)");
        var result = new ArrayList<String>();
        for (var input: targets) {
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

                    result.add(resultCount != null
                            ? countStr + " " + startNodeId + " = " + resultCountStr
                            : resultCountStr);
                } else {
                    System.err.println("Failed to read data.");
                }
            }
        }

        var targetResult = Arrays.asList("1 pyramid = 1.4 bar",
                "1 giraffe = 40 hare",
                "Conversion not possible.",
                "2 kilobyte = 16384 bit");

        for (int i = 0; i < targets.size(); i++) {
            assertEquals(targetResult.get(i), result.get(i));
        }
    }
}
