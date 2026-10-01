package com.smartcanteen;

import com.smartcanteen.dao.StudentDAO;
import com.smartcanteen.model.Student;

import java.util.Scanner;

public class StudentLogin {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== SMART CANTEEN =====");
        System.out.println("Student Login");

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        StudentDAO studentDAO = new StudentDAO();

        Student student = studentDAO.loginStudent(email, password);

        if (student != null) {
            System.out.println("\nLogin Successful!");
            System.out.println("Welcome, " + student.getName());
            System.out.println("Student ID: " + student.getStudentId());
            System.out.println("Department: " + student.getDepartment());
        } else {
            System.out.println("\nInvalid Email or Password!");
        }

        scanner.close();
    }
}