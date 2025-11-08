public class Student {
    private String name;
    private int age;
    private String grade;

    public Student(String name, int age, String grade){
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getName(){ return name; }
    public int getAge(){ return age; }
    public String getGrade(){ return grade; }

    public void setName(String name){ this.name = name; }
    public void setAge(int age){ this.age = age; }
    public void setGrade(String grade){ this.grade = grade; }

    @Override
    public String toString(){
        return name + "|" + age + "|" + grade;
    }

    public static Student fromString(String line){
        String[] parts = line.split("\\|");
        if(parts.length != 3) return null;
        return new Student(parts[0], Integer.parseInt(parts[1]), parts[2]);
    }
}
