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
                    a++;
                    file.seek(0);
                    file.writeInt(a);
                } finally {
                    mutex.unlock();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
