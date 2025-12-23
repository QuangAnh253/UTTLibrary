/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.dao;

import com.uttlibrary.model.Booking;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class BookingDAO extends BaseDAO<Booking> {

    @Override
    public List<Booking> findAll() {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM Booking";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Booking b = new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("reader_id"),
                        rs.getInt("book_id"),
                        rs.getDate("booking_date"),
                        rs.getString("status")
                );
                list.add(b);
            }
        } catch (Exception e) {
            System.err.println("findAll error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public Booking findById(int id) {
        String sql = "SELECT * FROM Booking WHERE booking_id=?";
        Booking b = null;

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                b = new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("reader_id"),
                        rs.getInt("book_id"),
                        rs.getDate("booking_date"),
                        rs.getString("status")
                );
            }
        } catch (Exception e) {
            System.err.println("findById error: " + e.getMessage());
        }
        return b;
    }

    @Override
    public boolean insert(Booking b) {
        String sql = """
                INSERT INTO Booking(reader_id, book_id, booking_date, status)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, b.getReaderId());
            ps.setObject(2, b.getBookId());
            ps.setDate(3, new java.sql.Date(b.getBookingDate().getTime()));
            ps.setString(4, b.getStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.err.println("insert error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Booking b) {
        String sql = """
                UPDATE Booking
                SET reader_id=?, book_id=?, booking_date=?, status=?
                WHERE booking_id=?
                """;

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, b.getReaderId());
            ps.setObject(2, b.getBookId());
            ps.setDate(3, new java.sql.Date(b.getBookingDate().getTime()));
            ps.setString(4, b.getStatus());
            ps.setInt(5, b.getBookingId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.err.println("update error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Booking WHERE booking_id=?";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.err.println("delete error: " + e.getMessage());
        }
        return false;
    }
}