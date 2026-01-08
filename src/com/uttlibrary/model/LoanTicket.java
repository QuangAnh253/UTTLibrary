/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.model;

/**
 *
 * @author ADMIN
 */

import java.util.Date;

public class LoanTicket {
    private int ticketId;
    private int readerId;
    private int staffId;
    private Date borrowDate;
    private Date dueDate;
    private String status;
    private double totalFine;

    public LoanTicket() {
    }

    public LoanTicket(int ticketId, int readerId, int staffId, Date borrowDate, Date dueDate, String status, double totalFine) {
        this.ticketId = ticketId;
        this.readerId = readerId;
        this.staffId = staffId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.status = status;
        this.totalFine = totalFine;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalFine() {
        return totalFine;
    }

    public void setTotalFine(double totalFine) {
        this.totalFine = totalFine;
    }
}
