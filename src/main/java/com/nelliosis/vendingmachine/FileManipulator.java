package com.nelliosis.vendingmachine;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.prefs.Preferences;

public class FileManipulator {

    // Declare file and its filepath and variable for remembering the path
    private File file; // File Object
    private String FileData; // File as a String
    private String FilePath; // File path as a String
    Preferences prefs = Preferences.userRoot().node(this.getClass().getName()); // File path to remember by system

    Scanner input = new Scanner(System.in);

    // Function that selects and returns a file of type JSON
    public void ChooseFile() throws IOException {
        FileNameExtensionFilter FileFilter = new FileNameExtensionFilter("json", "json");
        JFileChooser FileChooser = new JFileChooser(FileSystemView.getFileSystemView());

        // set filter and open the dialogbox
        FileChooser.setFileFilter(FileFilter);

        // Open a Java window and allow for file selection
        char ch;
        do {
            System.out.println("A window will open shortly.");
            FileChooser.showOpenDialog(null);
            try {
                // store choice into a variable and log the filepath.
                // if in any of the variables point to null, ask to try again
                this.file = FileChooser.getSelectedFile();
                this.FilePath = file.getAbsolutePath();
                logger.log(Level.INFO, "File selection succeeded.");
                break;
            } catch (NullPointerException e) {
                logger.log(Level.WARNING, "Caught NullPointerException at" + FileManipulator.class.getName());
                logger.log(Level.INFO, "User cancelled selection. Trying again.");
                System.out.print("You must choose a file to load the program. Try again? [Y/N]: ");
                ch = input.next().charAt(0);
            }

            // If the user selects no, the program will exit.
            if (ch == 'n' || ch == 'N') {
                logger.log(Level.SEVERE, "User cancelled selection. No file to initialize. Closing with status 1.");
                System.err.println("Program cannot run without initializing a file.");
                System.exit(1);
            }
        } while (ch == 'Y' || ch == 'y');

        System.out.println("File Selected at: " + this.FilePath);

        // store JSON as string into a variable
        this.FileData = new String(Files.readAllBytes(Paths.get(this.FilePath)));
        logger.log(Level.FINE, "File parsed.");
    }

    public void LoadData() throws IOException {
        this.file = new File(RetrieveFile());
        this.FilePath = RetrieveFile();
        this.FileData = new String(Files.readAllBytes(Paths.get(this.FilePath)));
        logger.log(Level.INFO, "File loaded from: " + this.FilePath);
    }

    public void SaveFile() {
        // If success, return true. If not, return false.
        try {
            this.prefs.put("PREF_PATH", this.FilePath);
            System.out.println("File " + this.file.getName() + " has been stored");
            logger.log(Level.INFO, "File selection stored in memory.");
        } catch (Exception e) {
            System.err.println("Problem in saving file. Exiting.");
            logger.log(Level.SEVERE, "Problem in file saving. See trace: " + logger.TraceToString(e));
        }
    }

    public void DestroyPref() {
        // destroy the remembered path
        try {
            this.prefs.remove("PREF_PATH");
            logger.log(Level.WARNING, "File selection removed at command.");
        } catch (Exception e) {
            System.err.println("Something went wrong while deleting the preference. See:");
            logger.log(Level.SEVERE, "Delete preference failed. See trace: " + logger.TraceToString(e));
        }

    }

    public String RetrieveFile() {

        // return the path if it exists, if not return null
        try {
            logger.log(Level.INFO, "File retrieved on stored preference path.");
            return this.prefs.get("PREF_PATH", null);
        } catch (Exception e) {
            logger.log(Level.WARNING, "No file stored on preference path.");
            return null;
        }

    }

    public JSONObject GetFileConfig() {
        JSONObject object = new JSONObject(this.FileData);
        JSONObject config = object.getJSONObject("config");
        logger.log(Level.INFO, "JSON config retrieved.");
        return config;
    }

    public JSONArray GetFileItems() {
        JSONObject object = new JSONObject(this.FileData);
        JSONArray items = object.getJSONArray("items");
        logger.log(Level.INFO, "JSON items retrieved.");
        return items;
    }

    public int[] VendingCodes() {
        JSONArray data = GetFileItems();
        int length = data.length();
        // Declare codes array, the min and max values.
        int Codes[] = new int[length];
        int max = 600;
        int min = 500;

        /*
         * Math logic from educative.io at:
         * https://www.educative.io/answers/how-to-generate-random-numbers-in-java
         */
        for (int i = 0; i < length; i++) {
            Codes[i] = (int) Math.floor(Math.random() * (max - min + 1) + min);
        }
        logger.log(Level.FINE, "Vending codes initialized.");
        return Codes;
    }

    public int ParseConfigColumn(JSONObject config) {
        try {
            logger.log(Level.INFO, "Config column data retrieved.");
            return config.getInt("column");
        } catch (JSONException e) {
            logger.log(Level.WARNING, "Config column not an int. Parsed as string then into int.");
            return Integer.parseInt((config.getString("columns")));
        }
    }

    public int ParseConfigRow(JSONObject config) {
        try {
            logger.log(Level.INFO, "Config row data retrieved.");
            return config.getInt("rows");
        } catch (JSONException e) {
            logger.log(Level.WARNING, "Config row not an int. Parsed as string then into int.");
            return Integer.parseInt((config.getString("rows")));
        }
    }

}
