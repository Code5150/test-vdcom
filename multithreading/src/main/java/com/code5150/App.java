package com.code5150;

public class App {
    public static final String FILE_PATH = "C:\\Users\\Vladislav\\Desktop\\VDCom test\\multithreading\\src\\main\\resources\\file.txt";

    public static void main(String[] args) {
        var writer = new FileWriter();
        writer.multithreadedWrite(FILE_PATH);
    }
}
