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

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatisticDAO {
    public List<Object[]> mostBorrowedBooks() {
        String sql = """
            SELECT b.book_id, b.book_title, COUNT(ld.detail_id) AS total
            FROM LoanDetail ld
            JOIN Book b ON ld.book_id = b.book_id
            GROUP BY b.book_id, b.book_title
            ORDER BY total DESC
        """;
        return fetch(sql);
    }

    // ==========================================
    // 2) Sách trễ hạn
    // ==========================================
    public List<Object[]> overdueBooks() {
        String sql = """
            SELECT lt.ticket_id, b.book_title, DATEDIFF(CURDATE(), lt.due_date) AS overdue_days
            FROM LoanTicket lt
            JOIN LoanDetail ld ON lt.ticket_id = ld.ticket_id
            JOIN Book b ON ld.book_id = b.book_id
            WHERE lt.status = 'Borrowing'
              AND lt.due_date < CURDATE()
        """;
        return fetch(sql);
    }

    // ==========================================
    // 3) Độc giả mượn nhiều nhất
    // ==========================================
    public List<Object[]> topReaders() {
        String sql = """
            SELECT r.reader_id, r.full_name, COUNT(lt.ticket_id) AS total_borrow
            FROM Reader r
            JOIN LoanTicket lt ON r.reader_id = lt.reader_id
            GROUP BY r.reader_id, r.full_name
            ORDER BY total_borrow DESC
        """;
        return fetch(sql);
    }

    // ==========================================
    // 4) Số lượng sách theo danh mục
    // ==========================================
    public List<Object[]> bookByCategory() {
        String sql = """
            SELECT c.category_id, c.category_name, COUNT(b.book_id)
            FROM Category c
            LEFT JOIN Book b ON c.category_id = b.category_id
            GROUP BY c.category_id, c.category_name
        """;
        return fetch(sql);
    }

    // ==========================================
    // Hàm chung
    // ==========================================
    private List<Object[]> fetch(String sql) {
        List<Object[]> list = new ArrayList<>();

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            int columnCount = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                list.add(row);
            }

        } catch (Exception e) {
            System.out.println("StatisticDAO error: " + e.getMessage());
        }

        return list;
    }
}