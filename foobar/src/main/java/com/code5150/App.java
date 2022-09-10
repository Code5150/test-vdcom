package com.code5150;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        var sc = new Scanner(System.in);
        int n = sc.nextInt();
        naiveStreamFooBar(n);
        System.out.println("=============================");
        //patternFooBar(n);
        multithreadedFooBar(n);
    }

    protected static void naiveFooBar(int n) {
        for (int i = 1; i <= n; i++) {
            boolean three = i % 3 == 0;
            boolean five = i % 5 == 0;
            if (three) {
                System.out.print("Foo");
            }
            if (five) {
                System.out.print("Bar");
            }
            if (!(three || five)) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    protected static void naiveStreamFooBar(int n) {
        System.out.println(IntStream.range(1, n).mapToObj(i ->
                (i % 3 == 0 ? "Foo" : "") +
                        (i % 5 == 0 ? "Bar" : "") +
                        (i % 3 != 0 && i % 5 != 0 ? i : "") + "\n"
        ).collect(Collectors.joining()));
    }

    protected static void patternFooBar(int n) {
        System.out.println(patternFooBar(1, n));
    }

    protected static String patternFooBar(int from, int to) {
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

    protected static void multithreadedFooBar(int n) {
        int threadsNum = 6;

        var countsPerThread = countsByThreads(n, threadsNum);

        var executorService = Executors.newFixedThreadPool(threadsNum);
        var result = IntStream.range(0, threadsNum).mapToObj(i ->
                CompletableFuture.supplyAsync(() -> {
                    int from = i > 0 ? countsPerThread[i-1] + 1 : 1;
                    int to = countsPerThread[i];
                    return patternFooBar(from, to);
                }, executorService)
        ).map(CompletableFuture::join).collect(Collectors.joining(""));
        System.out.println(result);
        executorService.shutdownNow();
    }

    protected static int[] countsByThreads(int n, int threadsNum) {
        int[] countsPerThread = new int[threadsNum];
        int countPerThread = n / threadsNum;
        int left = n % threadsNum;
        for (int i = 0; i < threadsNum; i++) {
            countsPerThread[i] = countPerThread + (i > 0 ? countsPerThread[i-1] : 0);
            if (left > 0) {
                countsPerThread[i] += 1;
                left -= 1;
            }
        }
        return countsPerThread;
    }
}
