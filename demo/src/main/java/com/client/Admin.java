package com.client;

public class Admin extends User {

    public Admin(){ } 


    
    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public String getFirstname() {
        return super.getFirstname();
    }

    @Override
    public String getLastname() {
        return super.getLastname();
    }
    @Override
    public String getBirthdate() {
        return super.getBirthdate();
    }
    @Override
    public String getTel() {
        return super.getTel();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    @Override
    public void setFirstname(String firstname) {
        super.setFirstname(firstname);
    }

    @Override
    public void setLastname(String lastname) {
        super.setLastname(lastname);
    }

    @Override
    public void setBirthdate(String birthdate) {
        super.setBirthdate(birthdate);
    }

    @Override
    public void setTel(String tel) {
        super.setTel(tel);
    }
}
