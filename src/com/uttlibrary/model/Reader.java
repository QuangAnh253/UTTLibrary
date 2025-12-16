/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Reader {
    private Integer readerId;
    private String fullName;
    private Date birthday;
    private Boolean gender;
    private String phone;
    private String readerType;

    public Reader() {
    }

    public Reader(Integer readerId, String fullName, Date birthday, Boolean gender, String phone, String readerType) {
        this.readerId = readerId;
        this.fullName = fullName;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.readerType = readerType;
    }

    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReaderType() {
        return readerType;
    }

    public void setReaderType(String readerType) {
        this.readerType = readerType;
    }
    
    
    
}
