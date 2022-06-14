package com.nelliosis.vendingmachine;

import java.util.Hashtable;
import java.util.Map.Entry;

import dnl.utils.text.table.TextTable;

public class Money {

    /*
     * // coins
     * private double quarter = 0.25;
     * private double dime = 0.10;
     * private double nickel = 0.05;
     * private double penny = 0.01;
     * 
     * // dollars
     * private double one = 1.00;
     * private double two = 2.00;
     * private double five = 5.00;
     * private double ten = 10.00;
     * private double twenty = 20.00;
     * private double fifty = 50.00;
     * private double oneHundred = 100.00;
     */
    private static final int MAX = 9;
    // declare the keys, the dollar denominations and the reference table
    int keys[] = new int[MAX];
    double money[] = { 0.01, 0.05, 0.10, 0.25, 1.00, 2.00, 5.00, 10.00, 20.00, 50.00, 100.00 };
    Hashtable<Integer, Double> MoneyTable = new Hashtable<Integer, Double>(MAX);

    private Hashtable<Integer, Double> HashMoney(Hashtable<Integer, Double> ht) {

        // populate keys from numbers 1-MAX
        for (int i = 1; i <= MAX; i++) {
            this.keys[i - 1] = i;
        }

        // hash the keys to each denomination
        for (int i = 0; i < MAX; i++) {
            ht.put(this.keys[i], this.money[i]);
        }

        return ht;
    }

    public void LoadMoney() {
        this.MoneyTable = HashMoney(this.MoneyTable);
    }

    public void PrintMoneyTable() {
        String columns[] = { "code:", "denomination:" };
        String data[][] = new String[MAX][];
        int i = 0;
        // iterate through Hash table and print data through each row
        for (Entry<Integer, Double> e : this.MoneyTable.entrySet()) {
            // Logic for iterating through HashTable from GeeksForGeeks at:
            // https://www.geeksforgeeks.org/hashtable-in-java/
            String key = String.valueOf(e.getKey());
            String denomination = String.valueOf(e.getValue());
            data[i] = new String[] { key, denomination };
            i++;
        }

        // declare a new TextTable instance and print
        TextTable table = new TextTable(columns, data);
        table.printTable();
    }

    public double GetMoney(String input, Double Temporary) {
        // declare the buffer that will be added to the money
        // declare the key
        Double buffer = 0.00;
        int key;

        /*
         * read each character and transform into integers to use as key
         * use key to add to the buffer
         * repeat until end of line
         */
        for (int i = 0; i < input.length(); i++) {
            key = Integer.parseInt(String.valueOf(input.charAt(i)));
            buffer += this.MoneyTable.get(key);
        }
        /*
         * Math.round formula from:
         * https://mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-
         * in-java/#bigdecimal
         */
        return Math.round((buffer + Temporary) * 100.0) / 100.0;

    }

    public double ConvertPrice(String Price) {
        double buffer = Double.parseDouble(Price.replace("$", ""));
        return buffer;
    }

    public boolean PriceGreaterThanCash(double Price, double CashAtHand) {
        if (Price > CashAtHand)
            return true;
        else
            return false;
    }

    public double Payment(double price, double CashAtHand) {
        return Math.round((CashAtHand - price) * 100.0) / 100.0;
    }

}
