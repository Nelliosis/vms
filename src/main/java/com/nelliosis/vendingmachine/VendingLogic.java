package com.nelliosis.vendingmachine;

import java.util.Hashtable;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;

import dnl.utils.text.table.TextTable;

public class VendingLogic {

    public void hash(Hashtable<Integer, JSONObject> hashtable, int codes[], JSONArray items) {

        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            hashtable.put(codes[i], item);
        }

    }

    public void PrintTable(Hashtable<Integer, JSONObject> ht, int length) {
        // Declare column titles and the data array
        String[] columns = { "code:", "name:", "price:", "amount left:" };
        String[][] data = new String[length][];
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

}
