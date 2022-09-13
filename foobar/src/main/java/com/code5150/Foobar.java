package com.code5150;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Foobar {
    public String naiveFooBar(int n) {
        var sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            boolean three = i % 3 == 0;
            boolean five = i % 5 == 0;
            if (three) {
                sb.append("Foo");
            }
            if (five) {
                sb.append("Bar");
            }
            if (!(three || five)) {
                sb.append(i);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String naiveStreamFooBar(int n) {
        return IntStream.range(1, n+1).mapToObj(i ->
                (i % 3 == 0 ? "Foo" : "") +
                        (i % 5 == 0 ? "Bar" : "") +
                        (i % 3 != 0 && i % 5 != 0 ? i : "") + "\n"
        ).collect(Collectors.joining());
    }

    public String patternFooBar(int n) {
        return patternFooBar(1, n);
    }

    protected String patternFooBar(int from, int to) {
        var sb = new StringBuilder();
        int left = to % 15;
        for (int i = from; i < (to - left); i += 15) {
            sb.append(i).append("\n")
                    .append(i + 1).append("\n")
                    .append("Foo").append("\n")
                    .append(i+3).append("\n")
                    .append("Bar").append("\n")
                    .append("Foo").append("\n")
                    .append(i+6).append("\n")
                    .append(i+7).append("\n")
                    .append("Foo").append("\n")
                    .append("Bar").append("\n")
                    .append(i+10).append("\n")
                    .append("Foo").append("\n")
                    .append(i+12).append("\n")
                    .append(i+13).append("\n")
                    .append("FooBar").append("\n");
        }
        if (left > 0) {
            for (int i = to - left + 1; i <= to; i++) {
                boolean three = i % 3 == 0;
                boolean five = i % 5 == 0;
                if (three) {
                    sb.append("Foo");
                }
                if (five) {
                    sb.append("Bar");
                }
                if (!(three || five)) {
                    sb.append(i);
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String multithreadedFooBar(int n) {
        int threadsNum = 6;

        int left = n % 15;
        var countsPerThread = countsByThreads(n, threadsNum);

        var executorService = Executors.newFixedThreadPool(threadsNum);
        var result = Stream.concat(IntStream.range(0, threadsNum).mapToObj(i ->
                CompletableFuture.supplyAsync(() -> {
                    int from = i > 0 ? countsPerThread[i-1] + 1 : 1;
                    int to = countsPerThread[i];
                    return patternFooBar(from, to);
                }, executorService)
        ).map(CompletableFuture::join), Stream.of(patternFooBar(n - left + 1, n))).collect(Collectors.joining());
        executorService.shutdownNow();
        return result;
    }

    protected int[] countsByThreads(int n, int threadsNum) {
        int[] countsPerThread = new int[threadsNum];
        Arrays.fill(countsPerThread, 0);
        int numberOfPortions = n / 15;
        for (int i = 0; i < numberOfPortions; i++) {
            countsPerThread[i % threadsNum] += 15;
        }
        for (int i = 1; i < threadsNum; i++) {
            countsPerThread[i] += countsPerThread[i-1];
        }
        return countsPerThread;
    }
}
