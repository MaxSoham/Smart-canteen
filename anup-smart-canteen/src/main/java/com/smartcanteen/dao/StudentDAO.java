package com.smartcanteen.dao;

import com.smartcanteen.DBConnection;
import com.smartcanteen.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    // Register Student
    public boolean registerStudent(Student student) {

        String sql = "INSERT INTO student " +
                "(student_id, name, rollno, department, phone_no, email, password) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            String studentId = "STU" + System.currentTimeMillis();

            statement.setString(1, studentId);
            statement.setString(2, student.getName());
            statement.setString(3, student.getRollno());
            statement.setString(4, student.getDepartment());
            statement.setString(5, student.getPhoneNo());
            statement.setString(6, student.getEmail());
            statement.setString(7, student.getPassword());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Student registration failed!");
            e.printStackTrace();
            return false;
        }
    }

    // Student Login
    public Student loginStudent(String email, String password) {

        String sql = "SELECT * FROM student WHERE email = ? AND password = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Student(
                        resultSet.getString("student_id"),
                        resultSet.getString("name"),
                        resultSet.getString("rollno"),
                        resultSet.getString("department"),
                        resultSet.getString("phone_no"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }

        } catch (SQLException e) {
            System.out.println("Student login failed!");
            e.printStackTrace();
        }

        return null;
    }
}
