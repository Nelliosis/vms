package com.nelliosis.vendingmachine;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManipulator {

    // Function that selects and returns a file of type JSON
    public String ChooseFile() {
        FileNameExtensionFilter FileFilter = new FileNameExtensionFilter("json", "json");
        JFileChooser FileChooser = new JFileChooser(FileSystemView.getFileSystemView());

        // set filter and open the dialogbox
        FileChooser.setFileFilter(FileFilter);
        FileChooser.showOpenDialog(null);

        // store choice into a variable and log the filepath.
        File NewFile = FileChooser.getSelectedFile();
        String FilePath = NewFile.getAbsolutePath();

        System.out.println("File Selected: " + FilePath);

        return FilePath;
    }

    // Function that transforms JSON into String
    public String FileToString(String file) throws IOException {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}
