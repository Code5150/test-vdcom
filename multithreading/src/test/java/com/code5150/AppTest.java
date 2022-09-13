package com.code5150;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppTest {
    public static final String FILE_PATH = "C:\\Users\\Vladislav\\Desktop\\VDCom test\\multithreading\\src\\main\\resources\\file.txt";

    @Test
    public void compareWriteResults() {
        var fw = new FileWriter();
        assertEquals(100, fw.multithreadedWrite(100, FILE_PATH));
        assertEquals(10000, fw.multithreadedWrite(10000, FILE_PATH));
        assertEquals(536, fw.multithreadedWrite(536, FILE_PATH));
    }
}
