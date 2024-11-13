import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PhonebookApp {
    public static void main(String[] args) throws FileNotFoundException {
        final int MAX_ENTRIES = 10;
        String[] firstNames = new String[MAX_ENTRIES];
        String[] lastNames = new String[MAX_ENTRIES];
        String[] phoneNumbers = new String[MAX_ENTRIES];
        Scanner keyboardSc = new Scanner(System.in);

        System.out.print("Name of input file: ");
        String fileName = keyboardSc.next();
        Scanner fileSc = new Scanner(new File(fileName));

        int numEntries = readEntries(firstNames, lastNames, phoneNumbers, fileSc);
        char choice;
        do {
            System.out.println("\nChoices:");
            System.out.println("l: lookup, r: reverse lookup, c: change number, a: add entry, q: quit");
            choice = keyboardSc.next().charAt(0);
            switch (choice) {
                case 'l':
                    lookup(firstNames, lastNames, phoneNumbers, numEntries, keyboardSc);
                    break;
                case 'r':
                    reverseLookup(firstNames, lastNames, phoneNumbers, numEntries, keyboardSc);
                    break;
                case 'c':
                    changeNumber(firstNames, lastNames, phoneNumbers, numEntries, keyboardSc);
                    break;
                case 'a':
                    boolean added = addEntry(firstNames, lastNames, phoneNumbers, numEntries, MAX_ENTRIES, keyboardSc);
                    if (added) {
                        numEntries++;
                    }
                    break;
                case 'q':
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 'q');
        
        System.out.print("Name of output file: ");
        fileName = keyboardSc.next();
        printEntries(firstNames, lastNames, phoneNumbers, numEntries, fileName);
    }

    public static int readEntries(String[] firstNames, String[] lastNames, String[] phoneNumbers, Scanner fileSc) {
        int count = 0;
        while (fileSc.hasNext() && count < firstNames.length) {
            firstNames[count] = fileSc.next();
            lastNames[count] = fileSc.next();
            phoneNumbers[count] = fileSc.next();
            count++;
        }
        return count;
    }

    public static void lookup(String[] firstNames, String[] lastNames, String[] phoneNumbers, int numEntries, Scanner keyboardSc) {
        System.out.print("First name: ");
        String firstName = keyboardSc.next();
        System.out.print("Last name: ");
        String lastName = keyboardSc.next();

        int index = getIndexBasedOnName(firstNames, lastNames, numEntries, firstName, lastName);

        if (index == -1) {
            System.out.println("Name not found");
        } else {
            System.out.println(firstName + " " + lastName + "'s phone number is " + phoneNumbers[index]);
        }
    }

    public static void reverseLookup(String[] firstNames, String[] lastNames, String[] phoneNumbers, int numEntries, Scanner keyboardSc) {
        System.out.print("Phone number: ");
        String phoneNumber = keyboardSc.next();

        int index = -1;
        for (int i = 0; i < numEntries; i++) {
            if (phoneNumbers[i].equals(phoneNumber)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Phone number not found");
        } else {
            System.out.println(phoneNumber + " is " + firstNames[index] + " " + lastNames[index] + "'s phone number");
        }
    }

    public static void changeNumber(String[] firstNames, String[] lastNames, String[] phoneNumbers, int numEntries, Scanner keyboardSc) {
        System.out.print("First name: ");
        String firstName = keyboardSc.next();
        System.out.print("Last name: ");
        String lastName = keyboardSc.next();

        int index = getIndexBasedOnName(firstNames, lastNames, numEntries, firstName, lastName);

        if (index == -1) {
            System.out.println("Name not found");
        } else {
            System.out.print("New phone number: ");
            String newPhoneNumber = keyboardSc.next();
            phoneNumbers[index] = newPhoneNumber;
            System.out.println("Phone number updated");
        }
    }

    public static boolean addEntry(String[] firstNames, String[] lastNames, String[] phoneNumbers, int numEntries, int maxEntries, Scanner keyboardSc) {
        if (numEntries >= maxEntries) {
            System.out.println("Database is full");
            return false;
        }

        System.out.print("First name: ");
        String firstName = keyboardSc.next();
        System.out.print("Last name: ");
        String lastName = keyboardSc.next();

        int index = getIndexBasedOnName(firstNames, lastNames, numEntries, firstName, lastName);
        if (index != -1) {
            System.out.println("That name already exists");
            return false;
        }

        System.out.print("Phone number: ");
        String phoneNumber = keyboardSc.next();

        firstNames[numEntries] = firstName;
        lastNames[numEntries] = lastName;
        phoneNumbers[numEntries] = phoneNumber;
        System.out.println("Entry added");
        return true;
    }

    public static int getIndexBasedOnName(String[] firstNames, String[] lastNames, int numEntries, String firstName, String lastName) {
        for (int i = 0; i < numEntries; i++) {
            if (firstNames[i].equals(firstName) && lastNames[i].equals(lastName)) {
                return i;
            }
        }
        return -1;
    }

    public static void printEntries(String[] firstNames, String[] lastNames, String[] phoneNumbers, int numEntries, String fileName) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(fileName);
        for (int i = 0; i < numEntries; i++) {
            printWriter.println(firstNames[i] + " " + lastNames[i] + " " + phoneNumbers[i]);
        }
        printWriter.close();
    }
}
