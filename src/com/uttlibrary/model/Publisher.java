/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.model;

/**
 *
 * @author ADMIN
 */
public class Publisher {

    private int publisherId;
    private String publisherName;
    private String address;
    private String email;

    public Publisher() {
    }

    public Publisher(int publisherId, String publisherName, String address, String email) {
        this.publisherId = publisherId;
        this.publisherName = publisherName;
        this.address = address;
        this.email = email;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
