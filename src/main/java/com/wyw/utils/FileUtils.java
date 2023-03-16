package com.wyw.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {
    public static final String INVALID_CONSTRUCT = "This is a utility class and cannot be instantiated";

    private FileUtils() {
        throw new UnsupportedOperationException(INVALID_CONSTRUCT);
    }

    public static String readFile(String url) {
        return readFileWithNIO(url);
    }

    public static String readFile(Path path) {
        return readFileWithNIO(path);
    }

    public static String readFileWithBufferedReader(String url) {
        try (BufferedReader reader = new BufferedReader(new FileReader(url))){
            StringBuilder sb = new StringBuilder();
            String line;
            while (( line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            System.err.println("File not found!!!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String readFileWithNIO(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String readFileWithNIO(String url) {
        return readFileWithNIO(Paths.get(url));
    }

    public static String readFileWithNIO2(String url) {
        return readFileWithNIO2(Paths.get(url));
    }

    public static String readFileWithNIO2(Path path) {
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String readFileWithNIO3(String url) {
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(url));
            StringBuilder sb = new StringBuilder();
            String line;
            while (( line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String readFileWithFiles(String url) {
        try {
            Stream<String> lines = Files.lines(Paths.get(url));
            return lines.collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getFileExtension(String url) {
        var index = url.lastIndexOf('.');
        assert 0 <= index;
        return url.substring(index);
    }
}
