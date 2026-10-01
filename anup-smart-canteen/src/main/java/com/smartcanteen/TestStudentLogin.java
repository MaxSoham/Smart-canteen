package com.smartcanteen;

import com.smartcanteen.dao.StudentDAO;
import com.smartcanteen.model.Student;

public class TestStudentLogin {

    public static void main(String[] args) {

        StudentDAO dao = new StudentDAO();

        Student student = dao.loginStudent("yash@gmail.com", "12345");

        if (student != null) {
            System.out.println("Login Successful!");
            System.out.println("Name: " + student.getName());
            System.out.println("Roll No: " + student.getRollno());
            System.out.println("Department: " + student.getDepartment());
            System.out.println("Email: " + student.getEmail());
        } else {
            System.out.println("Invalid Email or Password!");
        }
    }
}