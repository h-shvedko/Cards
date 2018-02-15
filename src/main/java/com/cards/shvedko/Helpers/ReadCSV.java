package com.cards.shvedko.Helpers;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ReadCSV {

    public static String fileNameValue = null;
    public static List<String> fileContent;
    private static final String ENCODING = "UTF-8";
    private static final String EXTENTION_LABEL = "CSV files (*.CSV)";
    private static final String EXTENTION = "*.csv";

    public static void read() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(EXTENTION_LABEL, EXTENTION);
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(null);
        fileNameValue = file.getPath();

        Charset charset = Charset.forName(ENCODING);
        Path path = Paths.get(file.getPath());

        try {
            fileContent = Files.readAllLines(path, charset);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}