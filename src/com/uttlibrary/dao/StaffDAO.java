/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.dao;

import com.uttlibrary.model.Staff;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class StaffDAO extends BaseDAO<Staff> {

    @Override
    public List<Staff> findAll() {
        List<Staff> list = new ArrayList<>();
        String sql = "SELECT * FROM Staff";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Staff s = new Staff(
                        rs.getInt("staff_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getString("role")
                );
                list.add(s);
            }
        } catch (Exception e) {
            System.err.println("findAll error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public Staff findById(int id) {
        String sql = "SELECT * FROM Staff WHERE staff_id=?";
        Staff s = null;

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                s = new Staff(
                        rs.getInt("staff_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getString("role")
                );
            }
        } catch (Exception e) {
            System.err.println("findById error: " + e.getMessage());
        }
        return s;
    }

    @Override
    public boolean insert(Staff s) {
        String sql = """
                INSERT INTO Staff(username, password, full_name, role)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getUsername());
            ps.setString(2, s.getPassword());
            ps.setString(3, s.getFullName());
            ps.setString(4, s.getRole());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.err.println("insert error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Staff s) {
        String sql = """
                UPDATE Staff
                SET username=?, password=?, full_name=?, role=?
                WHERE staff_id=?
                """;

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getUsername());
            ps.setString(2, s.getPassword());
            ps.setString(3, s.getFullName());
            ps.setString(4, s.getRole());
            ps.setInt(5, s.getStaffId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.err.println("update error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Staff WHERE staff_id=?";

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
