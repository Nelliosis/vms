package com.nelliosis.vendingmachine;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
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
                break;
            } catch (NullPointerException e) {
                System.out.print("You must choose a file to load the program. Try again? [Y/N]: ");
                ch = input.next().charAt(0);
            }

            // If the user selects no, the program will exit.
            if (ch == 'n' || ch == 'N') {
                System.out.println("Exiting program.");
                System.exit(1);
            }
        } while (ch == 'Y' || ch == 'y');

        System.out.println("File Selected at: " + this.FilePath);

        // store JSON as string into a variable
        this.FileData = new String(Files.readAllBytes(Paths.get(this.FilePath)));
    }

    public void SaveFile() {
        // If success, return true. If not, return false.
        try {
            this.prefs.put("PREF_PATH", this.FilePath);
            System.out.println("File " + this.file.getName() + " has been stored");
        } catch (Exception e) {
            throw e;
        }
    }

    public void DestroyPref() {
        // destroy the remembered path
        try {
            this.prefs.remove("PREF_PATH");
        } catch (Exception e) {
            System.out.println("Something went wrong while deleting the preference. See:");
            e.printStackTrace();
        }

    }

    public String RetrieveFile() {

        // return the path if it exists, if not return null
        try {
            return this.prefs.get("PREF_PATH", null);
        } catch (Exception e) {
            return null;
        }

    }

    public JSONObject GetFileConfig() {
        JSONObject object = new JSONObject(this.FileData);
        JSONObject config = object.getJSONObject("config");
        return config;
    }

    public JSONArray GetFileItems() {
        JSONObject object = new JSONObject(this.FileData);
        JSONArray items = object.getJSONArray("Items");
        return items;
    }

}
