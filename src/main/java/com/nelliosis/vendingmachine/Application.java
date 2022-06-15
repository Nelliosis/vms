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
import java.util.Hashtable;
import java.util.Scanner;
import java.util.logging.Level;

import org.json.*;

public class Application {
  public static void main(String[] args) throws IOException {
    logger.log(Level.INFO, "Main program initiated at: " + Application.class.getName());
    ClearConsole();
    // declare a new instance of FManip, VendLogic, FilePath variable, user input
    // and the cash at hand
    FileManipulator fm = new FileManipulator();
    VendingLogic vl = new VendingLogic();
    String SelectedFile;
    Double CashAtHand = 0.00;
    Scanner input = new Scanner(System.in);

    System.out.println("Vending Machine Software");

    // Retrieve file
    System.out.println("Checking for File.");
    SelectedFile = fm.RetrieveFile();

    // If there is no file detected, select and save the path to preferences.
    char ch;
    do {
      if (SelectedFile == null) {
        logger.log(Level.WARNING, "No File loaded.");
        System.out.print("No File loaded. Load file? [Y/N]: ");
        ch = input.next().charAt(0);
        logger.log(Level.INFO, "User asked to input. User input: " + ch);
        switch (ch) {
          case 'Y':
          case 'y':
            logger.log(Level.INFO, "User accepted request to initialize a file.");
            fm.ChooseFile();
            fm.SaveFile();
            SelectedFile = fm.RetrieveFile();
            break;
          case 'N':
          case 'n':
            logger.log(Level.SEVERE,
                "User did not accept request to initialize a file. Program cannot run. Exited with status 1");
            System.err.println("Program cannot run without an input file. Program will close.");
            System.exit(1);
            break;
          default:
            logger.log(Level.WARNING, "User entered wrong output in File Load selection.");
            System.out.print("Incorrect choice. Load a File? [Y/N]: ");
            break;
        }
      } else {
        fm.LoadData();
        vl.LoadMoney();
        logger.log(Level.INFO, "JSON data and money successfully loaded.");
        break;
      }
    } while (ch == 'Y' || ch == 'y');

    System.out.println("File Detected.");
    logger.log(Level.INFO, "File detected.");
    ClearConsole();
    logger.log(Level.INFO, "Program initiated.");
    // Declare config, items, selection codes array and hash table
    // JSONObject config = fm.GetFileConfig();
    JSONArray items = fm.GetFileItems();
    int VendingCodes[] = new int[items.length()];
    Hashtable<Integer, JSONObject> ht = new Hashtable<Integer, JSONObject>(items.length());

    /*
     * Declare random codes for items
     * Then hash each code to each item
     */
    VendingCodes = fm.VendingCodes();
    vl.hash(ht, VendingCodes, items);
    logger.log(Level.INFO, "codes to items hash complete.");

    // create the menu
    char des;
    do { // loop for trying again
      int selection;
      String SelectionCheck;
      System.out.println(
          "\t\tYour Run of the Mill Vending Machine.\nWhat would you like to do? Type in the number which applies to you and press enter.");
      System.out.println("1. Open the Menu and Buy.");
      System.out.println("2. Enter Money.");
      System.out.println("3. Retrieve Money.");
      System.out.println("4. Change Product Selection");
      System.out.println("5. Exit\n");
      System.out.println("Your current Balance: " + CashAtHand);
      System.out.print("Choice: ");
      SelectionCheck = input.next();
      selection = vl.ReadInt(SelectionCheck, input);
      logger.log(Level.INFO, "user asked to input with selection as: " + Integer.toString(selection));
      switch (selection) { // switch case for selection in the menu
        case 1:
          logger.log(Level.INFO, "User selected option 1.");
          ClearConsole();
          // delcare the choice variable
          int key;
          // print the table
          vl.PrintTable(ht, items.length());
          System.out.println("\nYour current Balance: " + CashAtHand);
          System.out.println("only 1 item is accepted per transaction.");
          System.out.print("Code: ");
          key = input.nextInt();
          logger.log(Level.INFO, "Item code inputted with value: " + Integer.toString(key));
          // check if key exists
          if (vl.KeyExists(key, VendingCodes)) {
            // Get the item and convert price from string to double
            logger.log(Level.INFO, "Key check success.");

            JSONObject obj = ht.get(key);
            Double price = vl.ConvertPrice(obj.getString("price"));

            logger.log(Level.INFO, "Price conversion success");
            logger.log(Level.INFO, "User has selected product: " + obj.getString("name"));
            // check money against price
            if (!vl.PriceGreaterThanCash(price, CashAtHand)) {
              // if cash is greater proceed with transaction
              logger.log(Level.INFO, "Cash is greater than price. Proceeding with transaction.");
              logger.log(Level.INFO, "Obtaining item amount");
              int amount = obj.getInt("amount");

              // check if amount is > 0
              if (amount == 0) {
                // if sold out break.
                logger.log(Level.WARNING, "item: " + obj.getString("name") + " is out of stock.");
                System.out.println("Sorry! This item is sold out!");
                break;
              } else {
                // Get the amount and decrease by 1
                logger.log(Level.INFO, "Product in stock. Decreasing by 1 and store into memory.");
                amount--;
                obj.put("amount", amount);
              }

              // Calculate
              logger.log(Level.INFO, "Commence payment.");
              CashAtHand = vl.Payment(price, CashAtHand);

              System.out.println("New Balance: " + CashAtHand);
              System.out.println("Thank you for your purchase!");
              logger.log(Level.INFO, "Payment successful.");

            } else {
              // if cash is less, halt and break.
              logger.log(Level.WARNING, "Money not enough to purchase product.");
              System.out.println("Not enough money inside the machine. Insert more to allow the transaction.");

            }
          } else {
            // if key does not exist, break.
            logger.log(Level.WARNING, "Inputted key mismatch with any existing key in the hashtable.");
            System.out.println("Sorry! This item does not exist.");
            break;
          }

          break;
        case 2:
          logger.log(Level.INFO, "User selected option 2");
          ClearConsole();
          // print the money table
          vl.PrintMoneyTable();

          // Enter money
          System.out
              .print("Enter the amount based on the code.\nFor example: 55555 is equal to $5.\nEnter selection: ");
          String SelectMoney = input.next();
          logger.log(Level.INFO, "User input: " + SelectMoney);
          Double Temporary = CashAtHand;
          logger.log(Level.INFO, "Money transaction started.");
          CashAtHand = vl.GetMoney(SelectMoney, Temporary);
          logger.log(Level.INFO,
              "Money transaction successful with total cash in the machine as: " + Double.toString(CashAtHand));
          System.out.println("Your current balance is currently: $" + CashAtHand);

          break;
        case 3:
          logger.log(Level.INFO, "User selected option 3");
          /*
           * imagine controlling an actual vending machine and
           * insert some hardware-controlling algorithm to
           * spit out the money
           */
          CashAtHand = 0.00;
          logger.log(Level.INFO, "Cash successfully withdrawn.");
          System.out.println("Thank you for using the Run of the Mill Vending Machine!");

          break;
        case 4:
          logger.log(Level.INFO, "User selected option 4.");
          logger.log(Level.WARNING, "Using this option will delete the current preference.");
          ClearConsole();
          fm.DestroyPref();
          fm.ChooseFile();
          fm.SaveFile();
          SelectedFile = fm.RetrieveFile();
          logger.log(Level.INFO, "New file preference stored and successfully parsed.");
          logger.log(Level.WARNING, "Recommended to restart machine to load new selection.");
          System.out.println("Please restart the machine to allow the changes to take effect.");

          break;
        case 5:
          logger.log(Level.INFO, "User selected option 5. Shutting down with status 0.");
          input.close();
          System.exit(0);

          break;
        default:
          logger.log(Level.WARNING, "User selection mismatch with any known choice.");
          System.out.println("Selection out of bounds. Please retry.");
          break;
      }

      System.out.print("Do you want to make another transaction? [Y/N]: ");
      des = input.next().charAt(0);
      ClearConsole();
      logger.log(Level.INFO, "User wants new selection with choice input as: " + des);
    } while (des == 'y' || des == 'Y');

    logger.log(Level.INFO, "User wants no more transactions. Shutting down with status 0.");
    logger.log(Level.INFO, "Program shut down successfully.");
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
      System.err.println("Error at clearing the screen");
      logger.log(Level.SEVERE, "Error at clearing the console. Trace" + logger.TraceToString(ex));
    }

  }

}
