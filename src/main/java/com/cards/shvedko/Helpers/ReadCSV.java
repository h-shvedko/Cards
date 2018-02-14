package com.cards.shvedko.Helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadCSV {

    static String[] read(String fileName) throws FileNotFoundException {
        String res = "";
        Scanner scanner = new Scanner(new File(fileName));
        scanner.useDelimiter(",");
        while (scanner.hasNext()) {
            System.out.print(scanner.next() + "|");
        }
        scanner.close();

        return new String[]{res};
    }

}
