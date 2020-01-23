import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class RandomFile {


    private int size = 2100;
    private static ArrayList<Integer> IDlist = new ArrayList<Integer>(2100);
    private static ArrayList<Integer> SINlist = new ArrayList<Integer>(2100);

    public static int getRandomNumberInts(int min, int max) {
        java.util.Random random = new java.util.Random();
        return random.ints(min, (max + 1)).findFirst().getAsInt();

    }

    public void writingIdsToFile () {
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream("RandomIds.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file");
            System.exit(0);
        }

        //Generating a list of random unique IDs
        Random randID = new Random();

        while (IDlist.size() < size) {
            int randomID = randID.ints(100000, (900000 + 1)).findFirst().getAsInt();
            IDlist.add(randomID);

            //Checking if there is a duplicate in the list already;
            if (!IDlist.contains(randomID)) {
                IDlist.add(randomID);
            }
        }
        outputStream.println(IDlist);
        outputStream.close();
    }

    public void writingSinToFile(){
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream("RandomSin.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file");
            System.exit(0);
        }
        //Generating a list of random unique SINs
        Random randSIN = new Random ();

        while (SINlist.size() < size) {
            int randomSIN = randSIN.nextInt (999999999);

            //Converting int to String to make sure we have SIN in a 6-digit format:
            String randomSINToString = String.format("%06d", randomSIN);
            int randomSINBackToInt = Integer.parseInt(randomSINToString);
            SINlist.add(randomSIN);

            //Checking if there is a duplicate in the list already;
            if (!SINlist.contains(randomSINBackToInt)) {
                SINlist.add(randomSINBackToInt);
            }
        }
        outputStream.println(SINlist);
        outputStream.close();
    }


        private static String RandomName() {
        String[] names = { "ennett","Dahlia"+"Jacinta","Pandora"," Suzie" ,"Tiffaney","Camilla", "Ma", "Nicky","Walter" ," Kiyoko","Darwin","Kellee","Xuan"," Stephaine" ,"John", "Sami", "James", "Oliver", "Nour", "Emilia", "Kayla", "Olga", "Sarah", "salam","Jasmin","Alaa"};
        java.util.Random random = new java.util.Random();
        int index = random.nextInt(names.length);
        return names[index];
    }

    private static String RandomDepartment() {
        String[] names = {"ComputerScience", "Software", "  Arts", "Business", "Literature", " Medicine", "Mechanical", "Education", "Security", "Electrical"};
        java.util.Random random = new java.util.Random();
        int index = random.nextInt(names.length);
        return names[index];
    }

    private static String RandomAddress() {
        String[] strings = {"SaintLaurent", "Kirkland", "Outroument", "pierrfond", "westMount"};
        java.util.Random random = new java.util.Random();
        int index = random.nextInt(strings.length);
        return strings[index];
    }


    public static void generateRandomTextFile(String fileName) {
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream(fileName));
        } catch (FileNotFoundException e) {

            System.out.println("Error opening the file.");
            System.exit(0);
        }
        System.out.println("Writing to file.");

        outputStream.println("EmployeeID "+"SIN "+ "Name " + "Department " +"Address "  + "Salary");
        for(int i=0 ; i<2100 ; i++){
            Employee emp = new Employee(IDlist.get(i), SINlist.get(i),
                    RandomName(), RandomDepartment(), RandomAddress(),
                    getRandomNumberInts(80000, 150000));

            outputStream.println(emp.toString());
        }

        outputStream.println("EOF");

        outputStream.close();
        System.out.println("A new text file of records was created successfully");
    }
}