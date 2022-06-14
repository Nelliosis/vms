package com.nelliosis.vendingmachine;

import java.util.Hashtable;
import java.util.Scanner;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;

import dnl.utils.text.table.TextTable;

public class VendingLogic extends Money {

    public void hash(Hashtable<Integer, JSONObject> hashtable, int codes[], JSONArray items) {

        // hash each item into a key
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            hashtable.put(codes[i], item);
        }

    }

    public void PrintTable(Hashtable<Integer, JSONObject> ht, int length) {
        // Declare column titles and the data array
        String columns[] = { "code:", "name:", "price:", "amount left:" };
        String data[][] = new String[length][];
        int i = 0;

        // iterate through Hash table and print data through each row
        for (Entry<Integer, JSONObject> e : ht.entrySet()) {
            // Logic for iterating through HashTable from GeeksForGeeks at:
            // https://www.geeksforgeeks.org/hashtable-in-java/
            JSONObject obj = e.getValue();
            String key = String.valueOf(e.getKey());
            String amount = String.valueOf(obj.getInt("amount"));
            data[i] = new String[] { key, obj.getString("name"), obj.getString("price"), amount };
            i++;
        }

        // declare a new TextTable instance and print
        TextTable table = new TextTable(columns, data);
        table.printTable();
    }

    public boolean KeyExists(int key, int VendingCodes[]) {
        for (int i = 0; i < VendingCodes.length; i++) {
            if (key == VendingCodes[i]) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

    /*
     * ReadInt logic from StoneHill's CS104 class
     * An example on NumberFormatException
     * at: https://web.stonehill.edu/compsci/CS104/Stuff/Exception%20examples.pdf
     */
    public int ReadInt(String SelectionCheck, Scanner input) {
        // declare the key, the boolean variable and the actual value to test
        String key = SelectionCheck;
        boolean correct = false;
        int value = 0;

        // initial check of SelectionCheck, if valid, set correct to true
        if (value == 0) {
            try {
                value = Integer.parseInt(key);
                correct = true;
            } catch (NumberFormatException e) {
                System.out.println("Bad choice. Reenter:");
            }
        }

        // while correct is not true, continue to prompt the user
        while (!correct) {
            try {
                key = input.next();
                value = Integer.parseInt(key);
                correct = true;
            } catch (NumberFormatException e) {
                System.out.println("Bad choice. Reenter:");
            }
        }
        return value;
    }

}
