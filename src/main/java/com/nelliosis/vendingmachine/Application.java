/*-
 * =====LICENSE-START=====
 * Java 11 Application
 * ------
 * Copyright (C) 2020 - 2022 Organization Name
 * ------
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =====LICENSE-END=====
 */

package com.nelliosis.vendingmachine;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import org.json.*;

public class Application {
  public static void main(String[] args) throws IOException {
    ClearConsole();
    // declare a new instance of FManip, FilePath variable, and user input
    FileManipulator fm = new FileManipulator();
    String SelectedFile;
    Scanner input = new Scanner(System.in);

    System.out.println("Vending Machine Software");

    // Retrieve file
    System.out.println("Checking for File.");

    SelectedFile = fm.RetrieveFile();

    // If there is no file detected, select and save the path to preferences.
    System.out.print("No File loaded. Load file? [Y/N]: ");
    char ch;
    do {
      if (SelectedFile == null) {
        ch = input.next().charAt(0);
        switch (ch) {
          case 'Y':
          case 'y':
            fm.ChooseFile();
            fm.SaveFile();
            SelectedFile = fm.RetrieveFile();
            break;
          case 'N':
          case 'n':
            System.out.println("Program cannot run without an input file. Program will close.");
            System.exit(1);
            break;
          default:
            System.out.print("Incorrect choice. Load a File? [Y/N]: ");
            break;
        }
      } else {
        break;
      }
    } while (ch == 'Y' || ch == 'y');

    System.out.println("File Detected.");
    ClearConsole();

    System.out.println("\t\tYour Run of the Mill Vending Machine.");
    System.out.println(SelectedFile);
    // fm.DestroyPref();
    input.close();
    System.exit(0);
  }

  public static void ClearConsole() {
    // Clears Screen in java. Works for both Windows and UNIX-based systems.
    try {
      if (System.getProperty("os.name").contains("Windows"))
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      else
        new ProcessBuilder("clear").inheritIO().start().waitFor();
    } catch (IOException | InterruptedException ex) {
      System.out.println("Error at clearing the screen");
      ex.printStackTrace();
    }
  }

}

// iterature through jsonarray and print
/*
 * for (int i = 0; i < items.length(); i++) {
 * JSONObject json = items.getJSONObject(i);
 * Iterator<String> keys = json.keys();
 * 
 * while (keys.hasNext()) {
 * String key = keys.next();
 * System.out.println(key + " " + json.get(key));
 * }
 * System.out.println("\n\n");
 * }
 */
/*
 * // parse data into int
 * int col = Integer.parseInt((config.getString("columns")));
 * System.out.println(col);
 */
