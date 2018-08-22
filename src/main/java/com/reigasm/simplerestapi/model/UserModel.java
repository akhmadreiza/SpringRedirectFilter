package com.reigasm.simplerestapi.model;

/*Represent Database Object*/

public class UserModel{

    public UserModel(String id, String userName, String idNo, String dob) {
        this.id = id;
        this.userName = userName;
        this.idNo = idNo;
        this.dob = dob;
    }

    private String id;
    private String userName;
    private String idNo;
    private String dob;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
