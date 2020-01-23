import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static DataManagement dm = new DataManagement();
    private static ArrayList<Employee> sortedData = new ArrayList<Employee>();
    private static ArrayList<Employee> readData = new ArrayList<Employee>();
    private static HeapSort hs = new HeapSort();
    private static BinarySearchTree bst = new BinarySearchTree();
    private static RandomFile rf = new RandomFile();

    public static void main(String[] args) throws FileNotFoundException {
        rf.writingIdsToFile();
        rf.writingSinToFile();

        Scanner scanner = new Scanner(System.in);
        boolean answer = false;
        System.out.println("The Employee database has been created");
        printMenu();
        while (!answer) {
            System.out.println("Enter 8 to view MainMenu choices");
            while (!scanner.hasNextInt()) {
                System.out.println("Please Enter a valid number");
                scanner.next();
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 0:
                    //Creating the database and sorting it by Employee ID using HeapSort and by Employee SIN using BST
                    RandomFile.generateRandomTextFile("RandomEmployeeRecords.txt");
                    System.out.println("Sorting by Employee ID and by Employee SIN completed");
                    heapSortRecord();
                    createBST();
                    break;
                case 1:
                    //Displaying the database sorted by Employee ID using HeapSort
                    System.out.println("Database sorted by Employee ID:");
                    displayRecordHeap();
                    break;
                case 2:
                    //Displaying the database sorted by Employee SIN using Binary Search Tree
                    System.out.println("Database sorted by Employee SIN:");
                    displayBST();
                    break;
                case 3:
                    //Add a record and Sort it in the DataBase
                    addNewEmployee();
                    break;
                case 4:
                    //Search by employee ID
                    searchRecordId();
                    break;
                case 5:
                    //Search by SIN
                    searchRecordSin();
                    break;
                case 6:
                    //update an old record using Employee ID and sort it in the DataBase
                    updateRecordUsingID();
                    break;
                case 7:
                    //Updating an old record using Employee SIN and sorting it in the database
                    updateRecordUsingSIN();
                    break;
                case 8:
                    System.out.println("The Employee database has been created");
                    printMenu();
                    break;
                case 9:
                    System.out.println("Exit Data Base");
                    answer = true;
                    break;

            }

        }

    }

    // Read data from textFile to Array List
    public static ArrayList<Employee> readFileToArrayList() throws FileNotFoundException {

        // a method to read data from text file and put it in Array list
        Scanner s = new Scanner(new File("RandomEmployeeRecords.txt"));
        ArrayList<Employee> employees = new ArrayList<Employee>(3000);
        int i = 0;
        s.nextLine();
        while (s.hasNextInt()) {
            employees.add((i++), new Employee(s.nextInt(), s.nextInt(), s.next(), s.next(), s.next(), s.nextInt()));
        }

        return employees;
    }

    // sorting the data and saving it to a new Array list
    public static void heapSortRecord() throws FileNotFoundException {
        readData = readFileToArrayList();
        HeapSort hs = new HeapSort();
        sortedData = hs.heapSort(readData);
    }

    // print sorted ArrayList
    public static void displayRecordHeap() throws FileNotFoundException {
        System.out.println(sortedData);
    }

    //Creating Binary Search Tree structure
    public static void createBST() throws FileNotFoundException {
        readData = readFileToArrayList();
        for (int i = 0; i < readData.size(); ++i) {
            bst.add(readData.get(i));
        }
    }

    //Printing the records sorted by SIN (using BST structure)
    public static void displayBST() throws FileNotFoundException {
        bst.showElements();
    }

    //Add a new Employee record to Data base then sort the whole list.
    public static void addNewEmployee() throws FileNotFoundException {
        System.out.println("Enter new Employee Id :");
        while (!scanner.hasNextInt()) {
            System.out.println("Please Enter a valid number");
            scanner.next();
        }
        int id = scanner.nextInt();
        System.out.println("Enter new SIN:");
        while (!scanner.hasNextInt()) {
            System.out.println("Please Enter a valid number");
            scanner.next();
        }
        int sin = scanner.nextInt();
        System.out.println("Enter new Name :");
        String name = scanner.next();
        System.out.println("Enter new Department:");
        String department = scanner.next();
        System.out.println("Enter new Address :");
        String address = scanner.next();
        System.out.println("Enter new Salary:");
        while (!scanner.hasNextInt()) {
            System.out.println("Please Enter a valid number");
            scanner.next();
        }
        int salary = scanner.nextInt();
        Employee employee = new Employee(id, sin, name, department, address, salary);
        if (dm.addRecord(employee, sortedData, bst)) {
            //heapSortRecord();
            hs.heapSort(sortedData);
            bst.add(employee);
            System.out.println("The new entered Employee was added and sorted to the Employee Data Base");

        } else {
            System.out.println("cannot add, ID (" + id + ") or SIN (" + sin + ") is already in the database.");

        }
    }

    //Search for an Existing Record using Employee Id and print its information
    public static void searchRecordId() {
        System.out.println("Please Enter an Employee Id to Search an Existing Employee Record");
        while (!scanner.hasNextInt()) {
            System.out.println("Please Enter a valid number");
            scanner.next();
        }
        int id = scanner.nextInt();
        Employee employee = dm.searchHeapRecord(id, sortedData);
        if (employee == null) {
            System.out.println("Employee not found in the database");
        } else {
            System.out.println(sortedData.get(sortedData.indexOf(employee)));
        }

    }

    //Search for an Existing Record using Employee SIN and print its information
    public static void searchRecordSin() {
        System.out.println("Please Enter an Employee SIN to Search an Existing Employee Record");
        while (!scanner.hasNextInt()) {
            System.out.println("Please Enter a valid number");
            scanner.next();
        }
        int sin = scanner.nextInt();
        Employee employee = bst.returnEmployeeEntry(sin, bst.getRoot());
        if (employee == null) {
            System.out.println("Employee not found in the database");
        } else {
            System.out.println(employee);
        }
    }

    //Update an existing record by typing its Id, replace it with a new Record, sort data again
    public static void updateRecordUsingID() {
        System.out.println("Enter Employee Id you want to update:");
        while (!scanner.hasNextInt()) {
            System.out.println("Please Enter a valid number");
            scanner.next();
        }
        int oldId = scanner.nextInt();
        Employee existingRecord = dm.searchHeapRecord(oldId, sortedData);
        if (existingRecord == null) {
            System.out.println("Record not found(main)");
            return;
        }
        System.out.println("Enter new 6 digit EmployeeId:");
        while (!scanner.hasNextInt()) {
            System.out.println("Please Enter a valid number");
            scanner.next();
        }
        int newId = scanner.nextInt();
        System.out.println("Enter new 9 digit SIN:");
        while (!scanner.hasNextInt()) {
            System.out.println("Please Enter a valid number");
            scanner.next();
        }
        int sin = scanner.nextInt();
        System.out.println("Enter new Name :");
        String name = scanner.next();
        System.out.println("Enter new Department:");
        String department = scanner.next();
        System.out.println("Enter new Address :");
        String address = scanner.next();
        System.out.println("Enter new Salary :");
        while (!scanner.hasNextInt()) {
            System.out.println("Please Enter a valid number");
            scanner.next();
        }
        int salary = scanner.nextInt();


        Employee newEmployee = new Employee(newId, sin, name, department, address, salary);
        if (dm.updateRecord(existingRecord, newEmployee, sortedData)) {
            hs.heapSort(sortedData);
            bst.delete(existingRecord);
            bst.add(newEmployee);
            System.out.println("Successfully updated record.\nThe new Record has been sorted in the DataBase");
        } else {
            System.out.println("Error updating record");

        }

    }

    public static void updateRecordUsingSIN() throws FileNotFoundException {
        System.out.println("Enter Employee SIN you want to update:");
        while (!scanner.hasNextInt()) {
            System.out.println("Please Enter a valid number");
            scanner.next();
        }
        int oldSIN = scanner.nextInt();
        if (!bst.contains(oldSIN)) {
            System.out.println("Record not found(main)");
            return;
        }
        Employee oldEmployee = bst.returnEmployeeEntry(oldSIN, bst.getRoot());
        Employee existingRecord = dm.searchHeapRecord(oldEmployee.getEmployeeId(), sortedData);
        if (existingRecord == null) {
            System.out.println("Record not found(main)");
            return;
        }
        System.out.println("Enter new 6 digit EmployeeId:");
        while (!scanner.hasNextInt()) {
            System.out.println("Please Enter a valid number");
            scanner.next();
        }
        int id = scanner.nextInt();
        System.out.println("Enter new 9 digit SIN:");
        while (!scanner.hasNextInt()) {
            System.out.println("Please Enter a valid number");
            scanner.next();
        }
        int sin = scanner.nextInt();
        System.out.println("Enter new Name :");
        String name = scanner.next();
        System.out.println("Enter new Department:");
        String department = scanner.next();
        System.out.println("Enter new Address :");
        String address =scanner.next();
        System.out.println("Enter new Salary:");
        while (!scanner.hasNextInt()) {
            System.out.println("Please Enter a valid number");
            scanner.next();
        }
        int salary = scanner.nextInt();
        Employee newEmployee = new Employee(id, sin, name, department, address, salary);
        if (dm.updateRecord(existingRecord, newEmployee, sortedData)) {
            hs.heapSort(sortedData);
            bst.delete(oldEmployee);
            bst.add(newEmployee);
            System.out.println("Successfully updated record.\nThe new Record has been sorted in the DataBase");
        } else {
            System.out.println("Error updating record");

        }
    }

    public static void printMenu() {
        System.out.println("Please select from the following options: ");//Start of user input
        System.out.print("0.Create Your Employee Database\n" +
                "1.Display the list sorted by Employee ID\n"
                + "2.Display the list sorted by Employee SIN\n" +
                "3.Add a new record\n4.Search by Employee ID\n5.Search by Employee SIN\n" +
                "6.Update an existing record using ID\n7.Update an existing record using SIN\n8.Display the Main Menu\n9.Exit the Database\n");
    }


}
