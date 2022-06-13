package com.nelliosis.vendingmachine;

import java.util.Hashtable;

import org.json.JSONArray;
import org.json.JSONObject;

public class VendingLogic {

    public void hash(Hashtable<Integer, JSONObject> hashtable, int codes[], JSONArray items) {

        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            hashtable.put(codes[i], item);
        }

    }

}
