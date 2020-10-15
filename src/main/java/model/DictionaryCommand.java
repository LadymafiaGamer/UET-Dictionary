package main.java.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class DictionaryCommand extends Dictionary {

    private static final String HISTORY_FILE_PATH = "src/main/resources/history.txt";
    private static final String DICTIONARY_FILE_PATH = "src/main/resources/dictionary.txt";
    private static final String FAVOURITE_FILE_PATH =  "src/main/resources/favourite.txt";
    private static final String SPLIT_CHARACTER = "\t";

    public static void importFromFile() {
        try {
            Scanner inputFile = new Scanner(new File(DICTIONARY_FILE_PATH));
            while (inputFile.hasNext()) {
                Word word = new Word();
                String curLine = inputFile.nextLine();
                String[] splitWords = curLine.split(SPLIT_CHARACTER);
                word.setWord_target(splitWords[0].trim());
                word.setWord_explain(splitWords[1].trim());
                dictionary.put(word.getWord_target(), word.getWord_explain());
                virtualDictionary.add(word.getWord_target());
            }
            inputFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void importFromHistory() {
        try {
            File historyFile = new File(HISTORY_FILE_PATH);
            Scanner inputFile = new Scanner(historyFile);
            while (inputFile.hasNext()) {
                String word = inputFile.nextLine();
                searchedWords.add(word);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void importFromFavourite() {
        try {
            File favouriteFile = new File(FAVOURITE_FILE_PATH);
            Scanner inputFile = new Scanner(favouriteFile);
            while (inputFile.hasNext()) {
                String favouriteWord = inputFile.nextLine();
                bookmarkedWords.add(favouriteWord);
            }
            inputFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void updateFavourite() {
        try {
            File exportedDict = new File(FAVOURITE_FILE_PATH);
            FileWriter fileWriter = new FileWriter(exportedDict);
            for(String favouriteWord : bookmarkedWords) {
                fileWriter.write(favouriteWord + "\n");
            }
            fileWriter.close();
        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void dictionaryExportToFile() {
        try {
            File exportedDict = new File(DICTIONARY_FILE_PATH);
            FileWriter fileWriter = new FileWriter(exportedDict);
            for(Map.Entry<String, String> word : dictionary.entrySet()) {
                fileWriter.write(word.getKey() + "\t" + word.getValue() + "\n");
            }
            fileWriter.close();
        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }

}
