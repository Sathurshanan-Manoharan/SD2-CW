import java.util.Scanner;

public class Main {

    public static final int MAX_SEATS = 100;
    public static int availableSeats = 100; // Available seats (This is used to check if there are any available seats)
    public static int registeredStudents = 0; // Registered students (This is used so the array can be easily accessed without having to loop through the array to get the number of registered students)

    public static int[] studentIdArray = new int[MAX_SEATS];
    public static String[] studentNameArray = new String[MAX_SEATS];

    public static void main(String[] args) {
        // System.out.println("""
        //         \t======================================
        //         \t= Student Activity Management System =
        //         \t======================================\n
        //         """);

        while(true){
            // System.out.println("""
            //     1. Check available seats\s
            //     2. Register student (with ID)\s
            //     3. Delete student\s
            //     4. Find student (with student ID)\s
            //     5. Store student details into a file\s
            //     6. Load student details from the file to the system\s
            //     7. View the list of students based on their names
            //     """);

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
            int deletingIndex = 0;
            for (int i = 0; i < studentIdArray.length; i++){
                if(studentIdArray[i] == studentID){
                    deletingIndex = i;
                    studentIdArray[i] = 0;
                    studentNameArray[i] = null;
                    registeredStudents--;
                    availableSeats++;
                }
            }

            for (int i = 0; i < studentIdArray.length; i++) {
                System.out.println(studentIdArray[i] + studentNameArray[i]);
            }
            System.out.println("After====");

            for(int i = deletingIndex; i < studentIdArray.length - 1; i++){
                studentIdArray[i] = studentIdArray[i + 1];
                studentNameArray[i] = studentNameArray[i + 1];
            }
            studentIdArray[MAX_SEATS - 1] = 0;
            studentNameArray[MAX_SEATS - 1] = null;

            for (int i = 0; i < studentIdArray.length; i++){
                System.out.println(studentIdArray[i] + studentNameArray[i]);
            }
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
               //  technically should return other details
                //also if returning other details check the location from here, save it to a varaible and then get other details from the same location
            
            }
        }

        if(isStudentAvailable){
            System.out.println("Name - " + studentNameArray[studentIdIndex] + "\nID - " + studentIdArray[studentIdIndex]);
        }else{
            System.out.println("Student not available!");
        }

    }

    public static void storeStudentDetails(){
        System.out.println("Store student details");
    }

    public static void loadStudentDetails(){
        System.out.println("Load student details");
    }

    public static void viewListOfStudents(){
       
    }

    public static void invalidInput(){
        System.out.println("Invalid input");
    }
}