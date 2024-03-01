package com.client;

public class User {

    public String id ;
    public String firstname ;
    public String lastname ;
    public String tel ;
    public String password ;
    public String Cfpassword ;
    public String birthdate ;

    public User(){ }
    
    public void setId(String id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public void setCfpassword(String cfpassword) {
        Cfpassword = cfpassword;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getPassword() {
        return password;
    }
    
    public String getCfpassword() {
        return Cfpassword;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getTel() {
        return tel;
    }
   

    
}
