/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.model;

/**
 *
 * @author ADMIN
 */
public class LoanDetail {
    private int id;
    private int loanTicketId;
    private int bookId;
    private int quantity;

    public LoanDetail() {
    }

    public LoanDetail(int id, int loanTicketId, int bookId, int quantity) {
        this.id = id;
        this.loanTicketId = loanTicketId;
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoanTicketId() {
        return loanTicketId;
    }

    public void setLoanTicketId(int loanTicketId) {
        this.loanTicketId = loanTicketId;
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
    
    
}
