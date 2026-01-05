/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.dao;

import com.uttlibrary.model.ShelfLocation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ADMIN
 */
// Vị trí kệ sách
public class ShelfLocationDAO extends BaseDAO<ShelfLocation>{
   @Override
    public List<ShelfLocation> findAll() {
        List<ShelfLocation> list = new ArrayList<>();
        String sql = "SELECT * FROM ShelfLocation ORDER BY shelf_id DESC";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ShelfLocation shelf = new ShelfLocation(
                        rs.getInt("shelf_id"),
                        rs.getString("shelf_name"),
                        rs.getString("description")
                );
                list.add(shelf);
            }
        } catch (SQLException e) {
            System.err.println("findAll ShelfLocation error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public ShelfLocation findById(int id) {
        String sql = "SELECT * FROM ShelfLocation WHERE shelf_id = ?";
        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ShelfLocation(
                            rs.getInt("shelf_id"),
                            rs.getString("shelf_name"),
                            rs.getString("description")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("findById ShelfLocation error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean insert(ShelfLocation s) {
        String sql = "INSERT INTO ShelfLocation (shelf_name, description) VALUES (?, ?)";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getShelfName());
            ps.setString(2, s.getDescription());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("insert ShelfLocation error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(ShelfLocation s) {
        String sql = "UPDATE ShelfLocation SET shelf_name = ?, description = ? WHERE shelf_id = ?";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getShelfName());
            ps.setString(2, s.getDescription());
            ps.setInt(3, s.getShelfId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("update ShelfLocation error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM ShelfLocation WHERE shelf_id = ?";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("delete ShelfLocation error: " + e.getMessage());
        }
        return false;
    }
} 


    