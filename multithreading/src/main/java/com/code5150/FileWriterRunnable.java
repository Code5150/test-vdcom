package com.code5150;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.locks.ReentrantLock;

public class FileWriterRunnable implements Runnable{
    private final int n;
    private final RandomAccessFile file;
    private final ReentrantLock mutex;

    public FileWriterRunnable(int n, ReentrantLock mutex, RandomAccessFile file) {
        this.n = n;
        this.file = file;
        this.mutex = mutex;
    }
    @Override
    public void run() {
        try {
            int a;
            for (int j = 0; j < n/2; j++) {
                try {
                    mutex.lock();
                    file.seek(0);
                    a = file.readInt();
                    System.out.print("Old: " + a);
                    a++;
                    file.seek(0);
                    file.writeInt(a);
                    System.out.print(" , new: " + a);
                    System.out.println(" , thread: " + Thread.currentThread().getName());
                } finally {
                    mutex.unlock();
                    //Sleep illustrates parallel thread work
                    Thread.sleep(1L);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
