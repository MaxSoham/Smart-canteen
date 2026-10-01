package com.smartcanteen.model;

public class Student {

    private String studentId;
    private String name;
    private String rollno;
    private String department;
    private String phoneNo;
    private String email;
    private String password;

    public Student() {
    }

    public Student(String studentId, String name, String rollno,
                   String department, String phoneNo,
                   String email, String password) {
        this.studentId = studentId;
        this.name = name;
        this.rollno = rollno;
        this.department = department;
        this.phoneNo = phoneNo;
        this.email = email;
        this.password = password;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

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