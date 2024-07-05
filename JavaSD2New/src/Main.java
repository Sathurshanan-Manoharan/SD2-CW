import java.io.*;
import java.util.Scanner;

public class Main {

    public static final int MAX_SEATS = 100;
    public static int availableSeats = 100; // Available seats (This is used to check if there are any available seats)
    public static int registeredStudents = 0; // Registered students (This is used so the array can be easily accessed without having to loop through the array to get the number of registered students)

    public static int[] studentIdArray = new int[MAX_SEATS];
    public static String[] studentNameArray = new String[MAX_SEATS];

    public static void main(String[] args) {
         System.out.println("""
                 \t======================================
                 \t= Student Activity Management System =
                 \t======================================
                 """);

        File PreviousData = new File("Programme_Data_File.txt");
        if (PreviousData.exists()) {
            System.out.println("\nWARNING! Previous data has been found. If you want to load that data select option 6\n");
        }
        while(true){
             System.out.println("""

                 1. Check available seats\s
                 2. Register student (with ID)\s
                 3. Delete student\s
                 4. Find student (with student ID)\s
                 5. Store student details into a file\s
                 6. Load student details from the file to the system\s
                 7. View the list of students based on their names
                 """);

            Scanner input = new Scanner(System.in);
            System.out.print("Select an option: ");
            int userInput = input.nextInt();

            switch (userInput){
                case 1:
                    checkAvailableSeats();
                    break;
                case 2:
                    registerStudent(input);
                    break;
                case 3:
                    deleteStudent(input);
                    break;
                case 4:
                    findStudent(input);
                    break;
                case 5:
                    storeStudentDetails();
                    break;
                case 6:
                    loadStudentDetails();
                    break;
                case 7:
                    viewListOfStudents();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    public static void checkAvailableSeats(){
        System.out.println("Available seats: " + availableSeats);
    }

    public static void registerStudent(Scanner input){
        if(availableSeats > 0){
            System.out.print("Enter student ID to add: ");
            int studentID = input.nextInt();

            System.out.print("Enter student's name: ");
            String studentName = input.next();

            studentIdArray[registeredStudents] = studentID;
            studentNameArray[registeredStudents] = studentName;
            registeredStudents++;
            availableSeats--;

            System.out.println( studentName+ " having " + studentID + " Id registered successfully");
        }else{
            System.out.println("No available seats");
        }
    }

    public static void deleteStudent(Scanner input){
        if(registeredStudents > 0){
            System.out.print("Enter student ID to delete: ");
            int studentID = input.nextInt();
            int deletingIndex = 0; // This is used to store the index of the student to be deleted

            for (int i = 0; i < studentIdArray.length; i++){
                if(studentIdArray[i] == studentID){
                    deletingIndex = i;
                    studentIdArray[i] = 0;
                    studentNameArray[i] = null;
                    registeredStudents--;
                    availableSeats++;
                }
            }

            // Shifting the array elements after deleting the student
            for(int i = deletingIndex; i < studentIdArray.length - 1; i++){
                studentIdArray[i] = studentIdArray[i + 1];
                studentNameArray[i] = studentNameArray[i + 1];
            }
            // Setting the last element to 0 and null
            studentIdArray[MAX_SEATS - 1] = 0;
            studentNameArray[MAX_SEATS - 1] = null;

        }else{
            System.out.println("No student records are there to delete!");
        }
    }

    public static void findStudent(Scanner input){
        System.out.print("Enter student ID to search: ");
        int studentID = input.nextInt();

        int studentIdIndex = 0;
        boolean isStudentAvailable = false;

        for(int i = 0; i < studentIdArray.length; i++){
            if (studentIdArray[i] == studentID) {
                studentIdIndex = i;
                isStudentAvailable = true;
            
            }
        }

        if(isStudentAvailable){
            System.out.println("Name - " + studentNameArray[studentIdIndex] + " | ID - " + studentIdArray[studentIdIndex]);
        }else{
            System.out.println("Student not available!");
        }

    }

    public static void storeStudentDetails(){
        // Storing the student details
        try {
            FileOutputStream fileOut = new FileOutputStream("Programme_Data_File.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(availableSeats);
            objectOut.writeObject(registeredStudents);
            objectOut.writeObject(studentIdArray);
            objectOut.writeObject(studentNameArray);
            objectOut.close();
            System.out.println("\nSuccessfully stored the Data into the file.\n");

        } catch (IOException e) {
            System.out.println("An error occurred!: " + e);
        }

    }

    public static void loadStudentDetails(){
        try {
            FileInputStream fileIn = new FileInputStream("Programme_Data_File.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            availableSeats = (int) objectIn.readObject();    //loading available seats
            registeredStudents = (int) objectIn.readObject();    //loading registered students
            studentIdArray = (int[]) objectIn.readObject();    //loading student id array
            studentNameArray = (String[]) objectIn.readObject();    //loading student name array
            objectIn.close();
            System.out.println("File successfully loaded!");

        } catch (IOException e) {
            System.out.println("File does not exist!\n");
        } catch (ClassNotFoundException e1) {
            System.out.println("Class not found!\n");
        }
    }

    public static void viewListOfStudents() {
        // Sorting the student names
        // Copying the student names and ids to a new array
        String[] newStudentNameArray = new String[registeredStudents];
        int[] newStudentIdArray = new int[registeredStudents];

        for (int i = 0; i < registeredStudents; i++) {
            newStudentNameArray[i] = studentNameArray[i];
            newStudentIdArray[i] = studentIdArray[i];
        }

        for (int i = 0; i < registeredStudents; i++) {
            for (int j = i + 1; j < registeredStudents; j++) {
                if (newStudentNameArray[i].compareTo(newStudentNameArray[j]) > 0) {
                    String tempName = newStudentNameArray[i];
                    newStudentNameArray[i] = newStudentNameArray[j];
                    newStudentNameArray[j] = tempName;

                    int tempId = newStudentIdArray[i];
                    newStudentIdArray[i] = newStudentIdArray[j];
                    newStudentIdArray[j] = tempId;
                }
            }
        }

        // Displaying the sorted student names
        for (int i = 0; i < registeredStudents; i++) {
            System.out.println((i + 1) + ". Name - " + newStudentNameArray[i] + " | ID - " + newStudentIdArray[i]);
        }
    }
}