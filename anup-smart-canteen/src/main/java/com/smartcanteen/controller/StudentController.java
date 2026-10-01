
        package com.smartcanteen.controller;

import com.smartcanteen.dao.StudentDAO;
import com.smartcanteen.model.Student;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
@CrossOrigin
public class StudentController {

    private final StudentDAO studentDAO = new StudentDAO();

    @PostMapping("/login")
    public String loginStudent(@RequestBody StudentLoginRequest request) {

        Student student = studentDAO.loginStudent(
                request.getEmail(),
                request.getPassword()
        );

        if (student != null) {

            // Return the actual student ID
            return student.getStudentId();
        }

        return "Invalid Email or Password!";
    }


    @PostMapping("/register")
    public String registerStudent(@RequestBody Student student) {

        boolean result =
                studentDAO.registerStudent(student);

        if (result) {

            return "Registration Successful!";
        }

        return "Registration Failed!";
    }


    public static class StudentLoginRequest {

        private String email;
        private String password;


        public String getEmail() {

            return email;
        }


        public void setEmail(String email) {

            this.email = email;
        }


        public String getPassword() {

            return password;
        }


        public void setPassword(String password) {

            this.password = password;
        }
    }
}

