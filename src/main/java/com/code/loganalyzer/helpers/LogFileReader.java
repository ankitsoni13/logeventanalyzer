package com.code.loganalyzer.helpers;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileReader;
import java.io.RandomAccessFile;

/**
 * Helper Class to read log file line by line.
 */
@Slf4j
public final class LogFileReader {

    private RandomAccessFile randomAccessFile;

    private LogFileReader(RandomAccessFile randomAccessFile){
        this.randomAccessFile = randomAccessFile;
    }

    @SneakyThrows
    public static LogFileReader getInstance(String filePath){
        return new LogFileReader(new RandomAccessFile(new File(filePath),"r"));
    }

    @SneakyThrows
    public String readLine(){
        return randomAccessFile.readLine();
    }

    @SneakyThrows
    public void close(){
        randomAccessFile.close();
    }

}
