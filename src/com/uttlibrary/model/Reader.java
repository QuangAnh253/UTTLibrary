/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.model;

/**
 *
 * @author ADMIN
 */
public class Reader {
    private int readerId;
    private String fullName;
    private String birthday;
    private String gender;
    private String phone;
    private String readerType;

    // Constructor mặc định
    public Reader() {
    }

    // Constructor đầy đủ tham số
    public Reader(int readerId, String fullName, String birthday, String gender, String phone, String readerType) {
        this.readerId = readerId;
        this.fullName = fullName;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.readerType = readerType;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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
