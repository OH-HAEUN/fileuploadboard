package com.oheproject.fileuploadboard.user;

public class UserDTO {

    private int id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String company;
    private int enabled;

    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getCompany() {
        return company;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setCompany(String company) {
        this.company = company;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
