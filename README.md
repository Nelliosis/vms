# README

Java 11 base application.

## BASE SETUP

### Dependencies

* Junit 5
* AssertJ
* Mockito
* Json.org
* Gson

### Support

* general GitIgnore file generated using [gitignore.io](https://www.gitignore.io)
* checkstyle with [google java style](https://checkstyle.sourceforge.io/google_style.html)

### License, copyright and header

Automatically update license header and copyright header in each src file, default license is:

* MIT License

Easily changed in the POM select from a list of available licenses.

To see what is available:

```bash
  mvn license:license-list
```

To see the license details:

```bash
  mvn license:license-list -Ddetail
```

### REFERENCES

Special thanks to the following for helping me in developing this short program:

Guides:

* [Java Class Methods on GeeksForGeeks](https://www.w3schools.com/java/java_class_methods.asp)
* [Java Classes and Objects by GeeksForGeeks](https://www.w3schools.com/java/java_classes.asp)
* [How to restrict file choosers in java to specific files? on StackOverFlow](https://stackoverflow.com/questions/18575655/how-to-restrict-file-choosers-in-java-to-specific-files)
* [How to parse a part of JSON message to String and pass it to another class? on StackOverFlow](https://stackoverflow.com/questions/67058008/how-to-parse-a-part-of-json-message-to-string-and-pass-it-to-another-class)
* [Java - How to Read JSON File as String by Amir Ghahrai](https://devqa.io/java-read-json-file-as-string/)
* [Calling a Class from a Different File by Henrik Frank](http://www.henrikfrank.dk/abaptips/javaforsap/javabasics/calling_class_i_another_file.htm)
* [Show simple open file dialog using JFileChooser by Nam Ha Minh](https://mail.codejava.net/java-se/swing/show-simple-open-file-dialog-using-jfilechooser)
* [Norman Pietek's Guide on Gson](https://futurestud.io/tutorials/gson-mapping-of-arrays-and-lists-of-objects)
* [Program that will remember where a file is stored. On StackOverFlow](https://stackoverflow.com/questions/59089139/i-want-to-write-a-program-that-can-remember-where-it-stored-a-file)
* [Preferences.get on Microsoft Docs](https://docs.microsoft.com/en-us/dotnet/api/java.util.prefs.preferences.get?view=xamarin-android-sdk-12)
* [Java Preferences API on amitph](https://www.amitph.com/introduction-to-java-preferences-api/)
* [How to clear the Console on StackOverFlow](https://stackoverflow.com/questions/2979383/how-to-clear-the-console/33379766#33379766)
* [Clear the Console on Java on DelftStack](https://www.delftstack.com/howto/java/java-clear-console/)
* [Types of Exceptions in Java by Stackify](https://stackify.com/types-of-exceptions-java/)
* [Arrays in Java on GeeksForGeeks](https://www.geeksforgeeks.org/arrays-in-java/)
* [How to generate random numbers in Java on educative.io](https://www.educative.io/answers/how-to-generate-random-numbers-in-java)
* [How to Pass by Reference in Java on educative.io](https://www.educative.io/edpresso/how-to-pass-by-reference-in-java)
* [HashTable in Java on GeeksForGeeks](https://www.geeksforgeeks.org/hashtable-in-java/)
* [Hashing in Java on GeeksForGeeks](https://www.geeksforgeeks.org/hashing-in-java/)
* [How to Iterate through Hash table in Java on GeeksForGeeks](https://www.geeksforgeeks.org/how-to-iterate-through-hashtable-in-java/)
* [How to Approach a Design Problem like Vending Machine on StackOverFlow](https://stackoverflow.com/questions/1127478/how-to-approach-design-problems-like-design-a-vending-machine)
* [Java – Iterate through Hashtable in 6 ways on BenchResources.net](https://www.benchresources.net/various-ways-to-iterate-through-hashtable-in-java-6-ways/)
* [How to Print a Table in java on DelftStack](https://www.delftstack.com/howto/java/print-a-table-in-java/)
* [Convert int to String on JavatPoint](https://www.javatpoint.com/java-int-to-string)
* [Formatting a Java Console Table breaking on StackOverFlow](https://stackoverflow.com/questions/19126629/formatting-a-java-console-table-breaking)
* [J-Text-Utils wiki on Tables](https://code.google.com/archive/p/j-text-utils/wikis/UsingTextTable.wiki)
* [How to take Input in Java Using Scanner Class and BufferedReader Class by TechVidvan](https://techvidvan.com/tutorials/taking-string-input-in-java/)
* [Hashtable Get method in Java by GeeksForGeeks](https://www.geeksforgeeks.org/hashtable-get-method-in-java/)
* [Java Convert Char to Integer by JavatPoint](https://www.javatpoint.com/java-char-to-int)
* [Java Convert Double to String by JournalDev](https://www.journaldev.com/18380/java-convert-double-to-string)
* [How to read a Character in Java by JavatPoint](https://www.javatpoint.com/how-to-read-character-in-java)
* [Java extends Keyword on W3Schools](https://www.w3schools.com/java/ref_keyword_extends.asp)
* [Java – How to round double / float value to 2 decimal places by mkyong](https://mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-in-java/#bigdecimal)
* [Removing Dollar and comma from string on StackOverFlow](https://stackoverflow.com/questions/20351323/removing-dollar-and-comma-from-string)
* [Java Convert String to double on JavatPoint](https://www.javatpoint.com/java-string-to-double)
* [Update elements in a JSONObejct on StackOverFlow](https://stackoverflow.com/questions/15159610/update-elements-in-a-jsonobject)
* [What is a Constant in Java and how to declare it? by Edureka](https://www.edureka.co/blog/what-is-java-constant/)
* [StoneHill CS104's NumberFormatException example](https://web.stonehill.edu/compsci/CS104/Stuff/Exception%20examples.pdf)
* [java.util.NoSuchElementException - Scanner reading user input on StackOverFlow](https://stackoverflow.com/questions/13042008/java-util-nosuchelementexception-scanner-reading-user-input)
* [Java logging through multiple classes on StackOverFlow](https://stackoverflow.com/questions/20737880/java-logging-through-multiple-classes)
* [Disable showing logs on console programmatically on StackOverFlow](https://stackoverflow.com/questions/68471943/disable-showing-logs-on-console-programmatically)
* [Is there a way to disable java.util.logging and enable it back later? on StackOverFlow](https://stackoverflow.com/questions/50533856/is-there-a-way-to-disable-java-util-logging-and-enable-it-back-later)
* [Logging in Java – Best Practices and Tips on SolarWinds PaperTrail](https://www.papertrail.com/solution/tips/logging-in-java-best-practices-and-tips/)
* [Standard way to implement a Logger across classes in Java? on StackOverFlow](https://stackoverflow.com/questions/26600757/standard-way-to-implement-a-logger-across-classes-in-java)
* [Java.util.logging Examples by Rohit Joshi on Java Code Geeks](https://examples.javacodegeeks.com/core-java/util/logging/java-util-logging-example/)
* [Good examples using java.util.logging [closed] on StackOverFlow](https://stackoverflow.com/questions/5950557/good-examples-using-java-util-logging)

Resources

* [Toptal's Gitignore.io](https://www.toptal.com/developers/gitignore)
* [Visual Studio Code](https://code.visualstudio.com/)
