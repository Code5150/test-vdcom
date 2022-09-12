package com.code5150;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args )
    {
        var sc = new Scanner(System.in);
        int n = sc.nextInt();
        var foobar = new Foobar();
        foobar.naiveStreamFooBar(n);
        System.out.println("=============================");
        foobar.patternFooBar(n);
        System.out.println("=============================");
        foobar.multithreadedFooBar(n);
    }
}
