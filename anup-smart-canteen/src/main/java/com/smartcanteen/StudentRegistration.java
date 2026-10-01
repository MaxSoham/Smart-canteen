package com.smartcanteen;

import com.smartcanteen.dao.StudentDAO;
import com.smartcanteen.model.Student;

import java.util.Scanner;

public class StudentRegistration {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== SMART CANTEEN =====");
        System.out.println("Student Registration");

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Roll No: ");
        String rollno = scanner.nextLine();

        System.out.print("Enter Department: ");
        String department = scanner.nextLine();

        System.out.print("Enter Phone No: ");
        String phoneNo = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        Student student = new Student();

        student.setName(name);
        student.setRollno(rollno);
        student.setDepartment(department);
        student.setPhoneNo(phoneNo);
        student.setEmail(email);
        student.setPassword(password);

        StudentDAO studentDAO = new StudentDAO();

        boolean result = studentDAO.registerStudent(student);

        if (result) {
            System.out.println("\nRegistration Successful!");
        } else {
            System.out.println("\nRegistration Failed!");
        }

        scanner.close();
    }
}
