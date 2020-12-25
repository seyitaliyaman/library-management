package com.edu.aydin.model;

public class UserModel implements Comparable<UserModel> {
    private String id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String department;
    private Boolean isAdmin;


    public UserModel(String id, String name, String surname, String username, String password, String department, Boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.department = department;
        this.isAdmin = isAdmin;
    }


    public UserModel() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return id + "___" + name + "___" + surname + "___" + username + "___" + password + "___" + department + "___" + isAdmin.toString();
    }

    @Override
    public int compareTo(UserModel o) {
        return Integer.valueOf(this.getId()).compareTo(Integer.valueOf(o.getId()));
    }
}
