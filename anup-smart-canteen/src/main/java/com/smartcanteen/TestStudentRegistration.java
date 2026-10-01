package com.smartcanteen;

import com.smartcanteen.dao.StudentDAO;
import com.smartcanteen.model.Student;

public class TestStudentRegistration {

    public static void main(String[] args) {

        Student student = new Student();

        student.setName("Yash");
        student.setRollno("230");
        student.setDepartment("IT");
        student.setPhoneNo("9876543210");
        student.setEmail("yash@gmail.com");
        student.setPassword("12345");

        StudentDAO studentDAO = new StudentDAO();

        boolean result = studentDAO.registerStudent(student);

        if (result) {
            System.out.println("Student Registered Successfully!");
        } else {
            System.out.println("Student Registration Failed!");
        }
    }
}
