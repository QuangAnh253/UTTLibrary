/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.model;

import java.sql.Timestamp;
/**
 *
 * @author ADMIN
 */
public class ImportBook {
    private int importId;
    private int bookId;
    private int quantity;
    private double importPrice; // DECIMAL(10,2) tương ứng với double
    private Timestamp importDate;

    public ImportBook() {
    }

    public ImportBook(int importId, int bookId, int quantity, double importPrice, Timestamp importDate) {
        this.importId = importId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.importPrice = importPrice;
        this.importDate = importDate;
    }

    public int getImportId() {
        return importId;
    }

    public void setImportId(int importId) {
        this.importId = importId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public Timestamp getImportDate() {
        return importDate;
    }

    public void setImportDate(Timestamp importDate) {
        this.importDate = importDate;
    }
    
    
}
