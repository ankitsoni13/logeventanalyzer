package com.code.loganalyzer.helpers;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.util.ResourceUtils;

import java.io.RandomAccessFile;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class LogFileReaderTest {

    LogFileReader logFileReader;

    @SneakyThrows
    @Test
    void getInstance() {
        Assertions.assertNotNull(LogFileReader.getInstance(Files.createTempFile("foo","txt").toFile().getAbsolutePath()));
    }


    @Test
    void readLine() {
        Assertions.assertDoesNotThrow(()->LogFileReader.getInstance(Files.createTempFile("foo","txt").toFile().getAbsolutePath()).readLine());
    }

    @Test
    void close() {
        Assertions.assertDoesNotThrow(()->LogFileReader.getInstance(Files.createTempFile("foo","txt").toFile().getAbsolutePath()).close());
    }
}