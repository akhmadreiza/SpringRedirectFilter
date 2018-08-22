package com.reigasm.simplerestapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"userName", "idNo", "dob"})
public class UserTO {

    @JsonProperty("userName")
    @JsonPropertyDescription("Username given by the user from frontend. data got from this API DB.")
    private String userName;

    @JsonProperty("idNo")
    @JsonPropertyDescription("ID Number based on user's ID. data got from this API DB.")
    private String idNo;

    @JsonProperty("dob")
    @JsonPropertyDescription("User Date of Birth. data got from this API DB.")
    private String dob;

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
