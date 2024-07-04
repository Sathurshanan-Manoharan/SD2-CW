import java.util.Scanner;

public class Main {

    public static final int MAX_SEATS = 100;
    public static int availableSeats = 100; // Available seats (This is used to check if there are any available seats)
    public static int registeredStudents = 0; // Registered students (This is used so the array can be easily accessed without having to loop through the array to get the number of registered students)

    public static int[] studentArray = new int[MAX_SEATS];

    public static void main(String[] args) {
        System.out.println("""
                \t======================================
                \t= Student Activity Management System =
                \t======================================\n
                """);

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
                    registerStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    findStudent();
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

    public static void registerStudent(){
        if(availableSeats > 0){
            Scanner input = new Scanner(System.in);
            System.out.print("Enter student ID: ");
            int studentID = input.nextInt();

            studentArray[registeredStudents] = studentID;
            registeredStudents++;
            availableSeats--;

            System.out.println("Student " + studentID + " registered successfully");
        }else{
            System.out.println("No available seats");
        }
    }

    public static void deleteStudent(){
        System.out.println("Delete student");
    }

    public static void findStudent(){
        System.out.println("Find student");
    }

    public static void storeStudentDetails(){
        System.out.println("Store student details");
    }

    public static void loadStudentDetails(){
        System.out.println("Load student details");
    }

    public static void viewListOfStudents(){
        System.out.println("View list of students");
    }

    public static void invalidInput(){
        System.out.println("Invalid input");
    }
}