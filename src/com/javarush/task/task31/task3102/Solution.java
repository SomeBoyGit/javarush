package com.javarush.task.task31.task3102;

import java.io.File;
import java.util.*;

/* 
Находим все файлы
*/

public class Solution {
    public static List<String> getFileTree(String root) {
        File rootDirectory = new File(root);
        List<String> fileList = new ArrayList<>();
        Queue<File> fileTree = new PriorityQueue<>();

        Collections.addAll(fileTree, Objects.requireNonNull(rootDirectory.listFiles()));
        while (!fileTree.isEmpty()) {
            File currentFile = fileTree.remove();
            if (currentFile.isDirectory()) {
                Collections.addAll(fileTree, Objects.requireNonNull(currentFile.listFiles()));
            } else {
                fileList.add(currentFile.getAbsolutePath());
            }
        }
        return fileList;
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(getFileTree("/home/someboy/Документы"));
        for (String s : list) {
            System.out.println(s);
        }
    }
}
