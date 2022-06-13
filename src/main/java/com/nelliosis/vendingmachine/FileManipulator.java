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
import java.util.prefs.Preferences;

public class FileManipulator {

    // Declare file and its filepath and variable for remembering the path
    private File file; // File Object
    private String FileData; // File as a String
    private String FilePath; // File path as a String
    // private String PrefPath; // Path stored in preferences

    // Function that selects and returns a file of type JSON
    public void ChooseFile() throws IOException {
        FileNameExtensionFilter FileFilter = new FileNameExtensionFilter("json", "json");
        JFileChooser FileChooser = new JFileChooser(FileSystemView.getFileSystemView());

        // set filter and open the dialogbox
        FileChooser.setFileFilter(FileFilter);
        FileChooser.showOpenDialog(null);

        // store choice into a variable and log the filepath.
        this.file = FileChooser.getSelectedFile();
        this.FilePath = file.getAbsolutePath();

        System.out.println("File Selected at: " + this.FilePath);

        // store JSON as string into a variable
        this.FileData = new String(Files.readAllBytes(Paths.get(this.FilePath)));
    }

    public void SaveFile() {
        // Declare preferences
        Preferences prefs = Preferences.userRoot().node(this.getClass().getName());

        // If success, return true. If not, return false.
        try {
            prefs.put("PREF_PATH", this.FilePath);
            System.out.println("File " + this.file.getName() + " has been stored");
        } catch (Exception e) {
            throw e;
        }
    }

    public String RetrieveFile() throws IOException {

        Preferences prefs = Preferences.userRoot().node(this.getClass().getName());

        try {
            return prefs.get("PREF_PATH", null);
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
