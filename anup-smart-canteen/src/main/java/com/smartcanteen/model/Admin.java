
package com.smartcanteen.model;

public class Admin {

    private int adminId;
    private String name;
    private String email;
    private String password;


    public Admin() {
    }


    public Admin(int adminId,
                 String name,
                 String email,
                 String password) {

        this.adminId = adminId;
        this.name = name;
        this.email = email;
        this.password = password;
    }


    public int getAdminId() {

        return adminId;
    }


    public void setAdminId(int adminId) {

        this.adminId = adminId;
    }


    public String getName() {

        return name;
    }


    public void setName(String name) {

        this.name = name;
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

