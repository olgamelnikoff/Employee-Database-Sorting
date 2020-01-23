Employee Database

Authors:

1) Emilia Segura
2) Nour Yaghmour
3) Olga Melnikova 


General Information: 

The program allows to create, sort and update a database of employees.


Description:

The database includes 2100 randomly generated records. Each record has the following data:

Employee ID (unique), Employee SIN (unique), Name, Department, Address, Salary

The database is sorted by Employee ID using Heapsort and by Employee SIN using Binary Search Tree data structure.


List of files:

Java code files:

Employee.java - a class for one record that includes all the data variables for one Employee (ID, SIN, name, department, address and salary)
RandomFile.java - a class for random generation of 2100 Employee records
HeapSort.java - a class that sorts the database by unique Employee ID using Heapsort
BinarySearchTree.java - a class that sorts the database by unique Employee SIN using Binary Search Tree 
DataManagement.java - a class that has methods for adding, searching and updating the records.
Main.java - a class where the user is prompted to select one of the 10 options:

	0) create the Employee database of 2100 records and sorting it by unique Employee ID (using Heapsort) and by unique Employee SIN (using Binary Search Tree)
	1) display the records sorted by Employee ID using HeapSort class
	2) display the list sorted by SIN using BinarySearchTree class
	3) add a new record and re-sorting the database by ID and SIN
	4) search the database by Employee ID
	5) search the database by Employee SIN
	6) update an existing record using ID
	7) update an existing record using SIN
	8) display the Main Menu
	9) exit the database

Compiled Java class files:
Employee.class
RandomFile.class
HeapSort.class
BinarySearchTree.class
DataManagement.class
Main.class




