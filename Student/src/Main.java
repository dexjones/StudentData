import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static LinkedList<Student> students = new LinkedList<>();
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int size = 10;
        String name, address;
        String fName = "student.txt";
        double gpa;
        String s;

        System.out.print("Class Information\n-------------------------------\n");

        for (int i = 0; i < size; i++) {
            System.out.print("Please enter name of the " + ordinal(i + 1) +" student: ");
            name = scnr.nextLine();
            System.out.print("Please enter the address of the " + ordinal(i + 1) + " student: ");
            address = scnr.nextLine();
            System.out.print("Please enter the GPA of the " + ordinal(i + 1) + " student: ");

            while (true) {
                s = scnr.nextLine();
                try {
                   gpa = Double.parseDouble(s);
                   while (gpa < 0 || gpa > 4) { // boundary check for grade -- no extra credit allowed
                        System.out.print("Number is out of bounds. Please enter a GPA between 0-4.0: ");
                        s = scnr.nextLine();
                        try {
                            gpa = Double.parseDouble(s);
                        }
                        catch (NumberFormatException e) {
                            System.out.print("Invalid entry. Please enter a number for the grade: ");
                        }
                   }
                   break;
                }
                catch (NumberFormatException e) {
                    System.out.print("Invalid entry. Please enter a number for the GPA: ");
                }
            }
            students.add(new Student(name, address, gpa));
            System.out.print("------------------------------------------------------\n");
        }
        HeapSort.heapSort(students, new SortName());
        System.out.println("The student information has been sorted");
        studentLog(fName);
        System.out.println("Exiting Program, goodbye.");
        scnr.close();
    }
    public static String ordinal(int num) {
        String ord;
        switch (num) {
            case 1:
                ord = "first";
                break;
            case 2:
                ord = "second";
                break;
            case 3:
                ord = "third";
                break;
            case 4:
                ord = "fourth";
                break;
            case 5:
                ord = "fifth";
                break;
            case 6:
                ord = "sixth";
                break;
            case 7:
                ord = "seventh";
                break;
            case 8:
                ord = "eighth";
                break;
            case 9:
                ord = "ninth";
                break;
            case 10:
                ord = "tenth";
                break;
            default:
                ord = Integer.toString(num);
        }
        return ord;
    }
    public static void studentLog(String fileName) {
        try (FileWriter fw = new FileWriter(fileName)) { //Opens file writer
            for (Student student : students) { // Iterates through students, writing each element to file
                fw.write(student.toString());
                fw.write("\n");
            }
            System.out.println("The student information has been logged to " + fileName + " in the file directory.\n");
        }
        catch (IOException e) {
            System.out.println("Error. Unable to write file.");
            System.out.println(e.getMessage());
        }
    }
}