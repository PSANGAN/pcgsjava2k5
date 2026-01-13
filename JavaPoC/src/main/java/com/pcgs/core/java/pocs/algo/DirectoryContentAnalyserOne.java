package com.pcgs.core.java.pocs.algo;

import java.io.File;
import java.util.Objects;

public class DirectoryContentAnalyserOne  { // do not forgive to name your file

    private static final StringBuilder indentation = new StringBuilder();

    public static void main(String[] args) {
        // Here you pass the path to the directory to be scanned
        getDirectoryContent("C:\\DirOne\\DirTwo\\AndSoOn"); // update the path
    }

    private static void getDirectoryContent(String filePath) {

        File currentDirOrFile = new File(filePath);

        if (!currentDirOrFile.exists()) {
            return;
        } else if (currentDirOrFile.isFile()) {
            System.out.println(indentation + currentDirOrFile.getName());
            return;
        } else {
            System.out.println("\n" + indentation + "|_" + currentDirOrFile.getName());
            indentation.append("   ");

            for (String currentFileOrDirName : Objects.requireNonNull(currentDirOrFile.list())) {
                getDirectoryContent(currentDirOrFile + "\\" + currentFileOrDirName);
            }

            if (indentation.length() - 3 > 3) {
                indentation.delete(indentation.length() - 3, indentation.length());
            }
        }
    }

}