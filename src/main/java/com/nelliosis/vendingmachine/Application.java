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

import org.json.*;

public class Application {
  public static void main(String[] args) throws IOException {
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
        System.out.print("No File loaded. Load file? [Y/N]: ");
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
        fm.LoadData();
        vl.LoadMoney();
        break;
      }
    } while (ch == 'Y' || ch == 'y');

    System.out.println("File Detected.");
    ClearConsole();

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

    // create the menu
    char des;
    do { // loop for trying again
      int selection;
      String SelectionCheck;
      System.out.println(
          "\t\tYour Run of the Mill Vending Machine.\nWhat would you like to do? Type in the number which applies to you and press enter.");
      System.out.println("1. Open the Menu and Buy.");
      System.out.println("2. Enter Money.");
      System.out.println("3. Create a log file");
      System.out.println("4. Exit\n");
      System.out.println("Your current Balance: " + CashAtHand);
      System.out.print("Choice: ");
      SelectionCheck = input.next();
      selection = vl.ReadInt(SelectionCheck, input);

      switch (selection) { // switch case for selection in the menu
        case 1:
          ClearConsole();
          // delcare the choice variable

          int key;
          // print the table
          vl.PrintTable(ht, items.length());
          System.out.println("\nYour current Balance: " + CashAtHand);
          System.out.println("only 1 item is accepted per transaction.");
          System.out.print("Code: ");
          key = input.nextInt();

          // check if key exists
          if (vl.KeyExists(key, VendingCodes)) {
            // Get the item and convert price from string to double
            JSONObject obj = ht.get(key);
            Double price = vl.ConvertPrice(obj.getString("price"));

            // check money against price
            if (!vl.PriceGreaterThanCash(price, CashAtHand)) {
              // if cash is greater proceed with transaction
              // Get the amount and decrease by 1
              int amount = obj.getInt("amount");

              // check if amount is > 0
              if (amount == 0) {
                System.out.println("Sorry! This item is sold out!");
                break;
              } else {
                amount--;
                obj.put("amount", amount);
              }

              // Calculate
              CashAtHand = vl.Payment(price, CashAtHand);

              System.out.println("New Balance: " + CashAtHand);
              System.out.println("Thank you for your purchase!");

            } else {
              // if cash is less, halt and break.
              System.out.println("Not enough money inside the machine. Insert more to allow the transaction.");

            }
          } else {
            System.out.println("Sorry! This item does not exist.");
            break;
          }

          break;
        case 2:
          ClearConsole();
          // print the money table
          vl.PrintMoneyTable();

          // Enter money
          System.out
              .print("Enter the amount based on the code.\nFor example: 55555 is equal to $5.\nEnter selection: ");
          String SelectMoney = input.next();
          Double Temporary = CashAtHand;
          CashAtHand = vl.GetMoney(SelectMoney, Temporary);
          System.out.println("Your current balance is currently: $" + CashAtHand);

          break;
        case 3:
          break;
        case 4:
          break;
        default:
          break;
      }

      System.out.print("Do you want to make another transaction? [Y/N]: ");
      des = input.next().charAt(0);
      ClearConsole();
    } while (des == 'y' || des == 'Y');

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
