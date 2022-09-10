package com.code5150;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class App {
    public static final String FILE_PATH = "C:\\Users\\Vladislav\\Desktop\\VDCom test\\multithreading\\src\\main\\resources\\file.txt";

    public static void main(String[] args) throws IOException {
        var sc = new Scanner(System.in);
        int n = sc.nextInt();

        try (var file = new RandomAccessFile(FILE_PATH, "rw")) {
            file.seek(0);
            file.writeInt(0);
            var mutex = new ReentrantLock();
            var thread1 = new Thread(new FileWriterRunnable(n, mutex, file), "Thread1");
            var thread2 = new Thread(new FileWriterRunnable(n, mutex, file), "Thread2");

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("File closed");
    }
}
