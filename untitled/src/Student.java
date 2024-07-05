import java.util.ArrayList;

public class Student {
    private String studentId;
    private String studentName;

    private ArrayList<Module> modules;



    public Student(String StudentID, String studentName){
        this.studentId = StudentID;
        this.studentName = studentName;
        this.modules = new ArrayList<Module>();
    }

}
