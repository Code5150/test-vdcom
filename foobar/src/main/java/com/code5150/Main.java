package com.code5150;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args )
    {
        int n = 0;
        var sc = new Scanner(System.in);
        while (n < 1) {
            try {
                n = sc.nextInt();
                if (n < 1) {
                    System.out.println("Integer must be positive. Please, try again: ");
                    sc.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("Failed to read integer. Please, try again: ");
                sc.nextLine();
            }
        }
        var foobar = new Foobar();
        System.out.println(foobar.naiveStreamFooBar(n));
        System.out.println("=============================");
        System.out.println(foobar.patternFooBar(n));
        System.out.println("=============================");
        System.out.println(foobar.multithreadedFooBar(n));
    }
}
