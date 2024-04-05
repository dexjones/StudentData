public class Student {
    String name;
    String address;
    double gpa;

    public Student (String name, String address, double gpa) {
        this.name = name;
        this.address = address;
        this.gpa = gpa;
    }
    @Override
    public String toString() {
        return "\nName: " + name + ", Address: " + address + ", GPA: " + gpa;
    }
}
