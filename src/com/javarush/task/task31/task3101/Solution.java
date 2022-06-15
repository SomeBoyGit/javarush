package com.javarush.task.task31.task3101;

/*
Проход по дереву файлов
*/

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class Solution {
    public static void main(String[] args) {
//        args = new String[]{"/home/someboy/Документы/test", "/home/someboy/Документы/test/test.txt"};
        if(args.length == 2) {
            File directory = new File(args[0]);
            File resultFileAbsolutePath = new File(args[1]);
            File allFilesContent = new File(resultFileAbsolutePath.getParentFile() + "/allFilesContent.txt");
            renameFile(resultFileAbsolutePath, allFilesContent);
            filterFile(directory, allFilesContent);
        }
    }
    private static void filterFile(File directoryName, File allFilesContent) {
        Arrays.stream(Objects.requireNonNull(directoryName.listFiles()))
                .filter(file -> file.length() < 51)
                .forEachOrdered(file -> readAndWriteFromFile(file, allFilesContent));
    }
    private static void readAndWriteFromFile(File readFile, File writeFile) {
        try (BufferedReader inputStream = new BufferedReader(new FileReader(readFile));
             BufferedWriter outputStream = new BufferedWriter(new FileWriter(writeFile.getAbsoluteFile(), true))) {
            String count;
            while ((count = inputStream.readLine()) != null) {
                outputStream.write(count);
            }
            outputStream.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void renameFile(File fileName, File newFileName) {
        if(FileUtils.isExist(fileName)) {
            FileUtils.deleteFile(fileName);
        }
        FileUtils.renameFile(fileName, newFileName);
    }
}
