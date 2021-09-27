# 213-Project-1

## Project Description  ##

You will develop a simple software to manage the information of a collection of albums owned by the user. This
project uses the IDE console as the user interface and utilizes the Java standard input and output to manage the
read/write of the data being processed.
In this project, you will implement an array-based container class Collection and use it to hold a list of albums the
user owns. Each album shall include the title, artist, genre, release date, and a flag identifying the availability. An
instance of the Collection class is a growable array list data structure with an initial capacity of 4, and automatically
grows (increases) the capacity by 4 whenever it is full. The array list does not decrease in capacity. You are NOT
allowed to use the Java library class ArrayList, or you will get 0 points for this project.
You must include a CollectionManger class as the user interface class to manage the collection. The user shall be
able to add/delete albums to/from the collection. In addition, the user can set the availability of an album when
lending out the album to a friend or a friend is returning the album. The software shall also provide the functionality
of displaying the album collection sorted by the release dates or by the genres, in ascending order.
You can use the Java library classes Scanner and StringTokenizer to read the command lines from the console.
When your project starts running, it shall display "Collection Manager starts running.". Next, it will read
and process the command lines continuously until the user enters the “Q” command to quit. Before the project stops
running, display "Collection Manager terminated."
A command line always begins with a command, in uppercase letters, and followed by a comma and some data tokens.
Each data token is also delimited by a comma. Some examples of valid command lines are demonstrated below. All
commands are case-sensitive, which means the commands with lowercase letters are invalid. You are required to deal
with bad commands that are not supported. Your software must support the following commands.
#### 1. A command, to add an album to the collection, for example, ####
   
    A,Famous Friends,Chris Young,Country,8/6/2021

    The above command line adds an album to the collection, including the title, artist, genre, and the release date. If
    the command line includes a random genre, use “unknown” as the genre. You should set the album as available
    by default when you are adding a new album to the collection. The release date shall be given in mm/dd/yyyy
    format. You must always check if the date is valid with the isValid() method in the Date class. You should also
    check if the album is already in the collection before you add it. For simplicity, we consider two albums as equal
    if their title and artist are the same. In this case, do not add it to the collection. See sample output for the messages
    to display.

#### 2. D command to remove an album from the collection, for example, #### 

    D,Famous Friends,Chris Young

    The above command line removes an album from the collection provided the title and artist. Let’s assume that
    multiple albums may have the same artist, or same title, but the combination of the tile and artist is unique. Only
    the album with the matching title and artist will be deleted from the collection. See sample output for the messages
    to display.

#### 3. L command to lend out an album to a friend, for example ####
  
    L,Famous Friends,Chris Young

    The above command line sets the album to be “not available”. For simplicity, we do not keep track of the friend’s
    information. Display proper messages depending on the results. See sample output.

#### 4. R command to return an album when the friend is returning the album, for example ####
    
    R,Famous Friends,Chris Young
    
    The command line above sets the album to be “available”. Display proper messages depending on the results.
    See sample output.

#### 5. P command to display the list of albums in the collection, for example, ####    

    P //display the collection without specifying the order
    PD //display the collection sorted by the release dates
    PG //display the collection sorted by the genres  
    
#### 6. Q command to stop the program execution and display "Collection Manager terminated." ####    



## Program Requirement ##

1. You MUST follow the software development ground rules. You will lose points if you are not following the rules.
2. There are sample input and output at the end of this document for your reference. The graders will be using the
sample input as the test cases to test your project. Your project should take the sample input in sequence without
getting any exception and without terminating abnormally. You will lose 2 points for each incorrect output or
each exception.
3. Each Java class must go in a separate file. -2 points if you put more than one Java class into a file.
4. Your program MUST handle bad commands! -2 points for each bad command not handled.
5. You are not allowed to use any Java library classes, except Scanner, StringTokenizer and Calendar classes.
You will lose 5 points for each additional Java library class imported.
6. When you import Java library classes, be specific and DO NOT import unnecessary classes or import the whole
package. For example, import java.util.*;, this will import all classes in the java.util package. You will
lose 2 points for using the asterisk “*” to include all the Java classes in the java.util package, or other java
packages.    
7. You MUST include the classes below. -10 points for each class missing. You CAN add necessary constructors,
private methods (helper methods), and other public methods to each class. You can also create an additional class
to define all the constant identifiers needed in this project.
