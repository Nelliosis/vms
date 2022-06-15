# README

The VMS (Vending Machine Software) is a short, lightweight, console-based program made to demonstrate proficiency is core areas of OOP, Java, file manipulation and logging.

Read how to run the program in the [Usage section.](#usage)

## Rationale & Approach

### On the approach

The program overall was constructed with OOP as the guiding principle. However, demonstration was also shown in Procedural Programming in the main method. The reason for this was 2-fold:

* As a one-person project, this is the best way to demonstrate step by step procedures in the project.
* The entire main method is *not fully* procedural. About half of the user options, especially with regards to file manipulation is fully object oriented.

Important functions of the program which I assume has uses beyond the program are fully modular. This is the angle I chose with regards on how to approach the problem using OOP.

The `FileManipulator` and `logger` classes are such example of this modularity. It is object-oriented in the textbook definition of the concept. Each function can stand on its own outside of the program proper.

### On the `VendingLogic` & `Money` Classes

The `VendingLogic` class is the main API used to perform different VMS functions. This class also inherits the `Money` class where all monetary transactions, calculations and checks are handled.

* The reason why I decided to separate the `Money` as a separate class is for better readability and modularity of the program. By itself, the `Money` class can perform way beyond its intended use in the VMS.
* The reason why I decided to instead have `VendingLogic` inhert `Money` is to extend the capabilities of the core capabilities of `VendingLogic`. This also allows me to instantiate `VendingLogic` in the main method instead of both.

Suffice to say, `VendingLogic` is the main class. Manipulating the data made heavy use of JSONObjects and Hashtables.

* JSONObjects was used as this was the object parsed by the `FileManipulator` class.
* The use of Hashtables is predetermined by the logic of a regular vending machine itself. Each code or *button* is mapped to a certain product. In the same vein, each object or *money* inserted into the machine is mapped to certain values inside the machine. The textbook concept of this is called *key-value pairs*. Hashtables provide a logical and easy way to simulate this key-value pairing.

As such, when parsing the items, and the money entered, I made heavy use of hashtables.

### On the `FileManipulator` Class

The `FileManipulator` class meanwhile is admittedly, was where I took most of the time programming, it was also the first I programmed before anything else. The class is detrimental to the design of the program. Especially in how this class will influence the future structure.

At first I decided to use the Gson dependency. But I quickly found out that the class does not function the way I want it to. It does not provide simple methods to parse a JSON file. The dependency that I found that fit best for my use-case was org.json. This class provided exactly what I wanted in parsing JSON files.

I wanted the program to remember when a file was selected and would load on it start. In order to accomplish this, I parsed the path and stored in cache using Java.preferences. Everytime the program will launch, it will check the cache for a path of a json file.

If no path was detected, the program will prompt the user to input a file. If no file was inputted, the program will close.

The `FileManipulator` class can also destroy and input a new file to load. However this would need the program to be restarted for the new file to take effect.

A nice to have would be to have the `FileManipulator` write to the JSON when amounts are decreasing. However, I would assume that an actual vending machine would always be powered on. As such I labeled this potential feature as a bonus. (Which I would not implement because of time constraints).

### On the config object of the JSON file

I assume this config object in the JSON is for the placement of products in a GUI. However, I spent too much time on the `FileManipulator` class to learn the new standard of JavaFX. I could not make use of the config object.

Instead, I ordered the interface based on the order of the item hashtable. I would then use the `J-text-utils` library to create an *at least* pleasing to look at table that would *hopefully* simulate the preferences of the config object.

### On the logger class

I wanted to create a simple logging class that I can apply to any program on any project. I found a [StackOverFlow thread](https://stackoverflow.com/questions/20737880/java-logging-through-multiple-classes) in which I based my logging class at. They are properly credited.

The `logger` class with a lowercase l, is lightweight logging class that uses Java's built-in `Java.Logger` class. It's the perfect solution for this small program. Being a built-in class, I can theoretically use this class in other projects that require logging.

When it comes to actual logging, I must apologize. I do not know logging best practices nor was it a consideration in my personal projects. However, I tried reading up in the time that I have and used the pdf file supplied as the primary guide in where, how and which to log. In the case of this program, I tried logging everything.

Usually, logging would happen to print in the console and *then* write to a file. However I cannot write to the console due to the console being used by the program itself. This is another part of the program where I assume a GUI is necessary however I was not able to implement in time.

Instead, I decided to *not* write into the console at all and write to a text file instead. Each time the program closes or crashes, if a log file has not been created in the relative directory of the program, the `FileHandler` method of the `Java.Logger` class will create one. If a file has already been created, the log's behavior will find the EOF value and concatenate the new log there.

I assume that having one log for this entire short program is enough. It keeps the program tidy and reduces clutter. However, I would also assume that in bigger programs and systems, logs based on days or on actions would be created. As such the `logger` class can be modified to such by setting the append value to `false`.

### On the challenge as a whole

I find this challenge particularly engaging. It helped me rekindle my proficiencies in file handling. The challenge of course, is *how* the data is parsed. I believe that my experience in JavaScript, taking [CS50](https://www.edx.org/course/introduction-computer-science-harvardx-cs50x) and certification in Java all contributed in my coding this program.

Admittedly, the part in which I had the most difficulty was the `FileManipulator` class, taking over 3 days in completing the class to it's final form. It took me time to finding a library that I like and to construct a fully modular class that can be used elsewhere. It especially took time to find *edge cases* and cover them with the `FileManipulator`.

The second hardest part of the challenge was setting up VS Code for Java development with Maven. It took me 2 days to properly understand and setup the environment. However, my understanding with the *Node Package Manager*, and DevOps in my Azure certification helped greatly in understanding Maven.

The easiest part of the program to code was the VMS itself, since it does not require any external dependency. It took me 2 days to code both the logger and the VMS proper.

## Core Setup

This program was made on a computer with the following:

* macOS Big Sur 11.6.6
* Java JDK 11.0.11 2021-04-20 LTS
* Maven 4.0.0
* Git 2.36.1

## Dependencies

* Junit 5
* AssertJ
* Mockito
* Json.org
* J-Text-Utils

## Usage

Download the project using Git with

```git
git clone https://github.com/Nelliosis/vms.git
```

or in GitHub, click the green button that says `code` and select `Download ZIP`.

Extract the project and run `Application.java` in:

```bash
src/main/java/com/nelliosis/vendingmachine/Application.java
```

To run the project, I recommend opening in cmd or terminal window. Use these two commands to run the project:

```bash
javac Application.java
java Application
```

Or if you are using VS Code, open the project folder using VS Code and in the `JAVA PROJECTS` menu, right click `vms` and select run. As seen below:

![vms-run](https://user-images.githubusercontent.com/65330187/173813398-5b28e96b-f61d-49e5-b2a4-9a2339ceb668.png)

## License, copyright and header

This project is under the MIT License. See more [here.](https://github.com/Nelliosis/vms/blob/main/LICENSE)

## References

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
* [How to store printStackTrace into a string [duplicate] on StackOverFlow](https://stackoverflow.com/questions/4812570/how-to-store-printstacktrace-into-a-string)
* [e.printStackTrace(); in string on StackOverFlow](https://stackoverflow.com/questions/7242596/e-printstacktrace-in-string)

Resources

* [Toptal's Gitignore.io](https://www.toptal.com/developers/gitignore)
* [Visual Studio Code](https://code.visualstudio.com/)
