/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.dao;

/**
 *
 * @author ADMIN
 */

import com.uttlibrary.util.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardDAO {

    public int countBooks() {
    return count("SELECT COUNT(*) FROM Book");
}

    public int countBorrowing() {
        return count("SELECT COUNT(*) FROM LoanTicket WHERE status='Borrowing'");
    }

    public int countUsers() {
        return count("SELECT COUNT(*) FROM Staff");
    }


    private int count(String sql) {
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            System.out.println("Dashboard count error: " + e.getMessage());
        }
        return 0;
    }
}