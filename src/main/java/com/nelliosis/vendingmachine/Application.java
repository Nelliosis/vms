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

//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.io.IOException;
//import java.io.Reader;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Map;

//import com.google.gson.Gson;
//import com.google.gson.JsonObject;

import org.json.*;

public class Application {
  public static void main(String[] args) throws IOException {

    // declare a new instance of FManip and copy path to FilePath
    FileManipulator fm = new FileManipulator();
    String FilePath = fm.ChooseFile();

    // copy JSON content into String and transform into an object
    String file = fm.FileToString(FilePath);
    JSONObject obj = new JSONObject(file);

    // Separate config and items
    JSONObject config = obj.getJSONObject("config");
    JSONArray items = obj.getJSONArray("items");

    System.out.println(config);
    System.out.println(items);

  }
}
/*
 * Gson gson = new Gson();
 * 
 * try {
 * Reader reader = Files.newBufferedReader(Paths.get(SelectedFile));
 * 
 * // convert JSON file to map
 * Map<?, ?> map = gson.fromJson(reader, Map.class);
 * 
 * // print map entries
 * for (Map.Entry<?, ?> entry : map.entrySet()) {
 * System.out.println(entry.getKey() + "=" + entry.getValue());
 * }
 * 
 * // close reader
 * reader.close();
 * } catch (IOException e) {
 * e.printStackTrace();
 * }
 */