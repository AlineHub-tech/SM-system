
import java.util.*;
import java.io.*;

public class StudentManager {
    private static final String FILE_PATH = "data/students.txt";

    public void addStudent(Student s){
        try(FileWriter fw = new FileWriter(FILE_PATH, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)){
            out.println(s.toString());
        } catch(IOException e){
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public List<Student> getAllStudents(){
        List<Student> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))){
            String line;
            while((line = br.readLine()) != null){
                Student s = Student.fromString(line);
                if(s != null) list.add(s);
            }
        } catch(FileNotFoundException e){
            // File not created yet
        } catch(IOException e){
            System.out.println("Error reading file: " + e.getMessage());
        }
        return list;
    }

    public void updateStudent(String name, Student updated){
        List<Student> list = getAllStudents();
        try(PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))){
            for(Student s : list){
                if(s.getName().equalsIgnoreCase(name)){
                    pw.println(updated.toString());
                } else {
                    pw.println(s.toString());
                }
            }
        } catch(IOException e){
            System.out.println("Error updating: " + e.getMessage());
        }
    }

    public void deleteStudent(String name){
        List<Student> list = getAllStudents();
        try(PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))){
            for(Student s : list){
                if(!s.getName().equalsIgnoreCase(name)){
                    pw.println(s.toString());
                }
            }
        } catch(IOException e){
            System.out.println("Error deleting: " + e.getMessage());
        }
    }

    public Student searchStudent(String name){
        List<Student> list = getAllStudents();
        for(Student s : list){
            if(s.getName().equalsIgnoreCase(name)){
                return s;
            }
        }
        return null;
    }
}

