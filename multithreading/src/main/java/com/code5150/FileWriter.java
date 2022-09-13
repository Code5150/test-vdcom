package com.code5150;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class FileWriter {
    public int multithreadedWrite() {
        int n = 0;
        var sc = new Scanner(System.in);
        while (n < 1 || n % 2 != 0) {
            try {
                n = sc.nextInt();
                if (n < 1) {
                    System.out.println("Integer must be positive. Please, try again: ");
                    sc.nextLine();
                } else if (n % 2 != 0) {
                    System.out.println("Integer must be even. Please, try again: ");
                    sc.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("Failed to read integer. Please, try again: ");
                sc.nextLine();
            }
        }

        while (true) {
            System.out.println("Enter file path: ");
            sc.nextLine();
            var path = sc.nextLine();
            try (var file = new RandomAccessFile(path, "rw")) {
                file.seek(0);
                file.writeInt(0);
                var mutex = new ReentrantLock();
                var thread1 = new Thread(new FileWriterRunnable(n, mutex, file), "Thread1");
                var thread2 = new Thread(new FileWriterRunnable(n, mutex, file), "Thread2");

                thread1.start();
                thread2.start();

                thread1.join();
                thread2.join();
                file.seek(0);
                var result = file.readInt();
                if (result == n) {
                    System.out.println("Successful");
                } else {
                    System.err.println("FAILED: result = " + result + ", n = " + n);
                }
                return result;
            } catch (FileNotFoundException e) {
                System.out.println("File not found or cannot be created. Please, try again: ");
                sc.nextLine();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
                break;
            }
        }
        return -1;
    }

    public int multithreadedWrite(int n, String path) {
        try (var file = new RandomAccessFile(path, "rw")) {
            file.seek(0);
            file.writeInt(0);
            var mutex = new ReentrantLock();
            var thread1 = new Thread(new FileWriterRunnable(n, mutex, file), "Thread1");
            var thread2 = new Thread(new FileWriterRunnable(n, mutex, file), "Thread2");

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();
            file.seek(0);
            return file.readInt();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
