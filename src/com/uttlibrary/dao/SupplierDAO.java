/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.dao;

import com.uttlibrary.model.Supplier;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class SupplierDAO extends BaseDAO<Supplier>{
    @Override
    public List<Supplier> findAll() {
        List<Supplier> list = new ArrayList<>();
        String sql = "SELECT * FROM Supplier";
        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Supplier(
                    rs.getInt("supplier_id"),
                    rs.getString("supplier_name"),
                    rs.getString("address"),
                    rs.getString("phone")
                ));
            }
        } catch (SQLException e) {
            System.err.println("findAll Supplier error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public Supplier findById(int id) {
        String sql = "SELECT * FROM Supplier WHERE supplier_id=?";
        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Supplier(
                        rs.getInt("supplier_id"),
                        rs.getString("supplier_name"),
                        rs.getString("address"),
                        rs.getString("phone")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("findById Supplier error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean insert(Supplier s) {
        String sql = "INSERT INTO Supplier(supplier_name, address, phone) VALUES(?,?,?)";
        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getSupplierName());
            ps.setString(2, s.getAddress());
            ps.setString(3, s.getPhone());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("insert Supplier error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Supplier s) {
        String sql = "UPDATE Supplier SET supplier_name=?, address=?, phone=? WHERE supplier_id=?";
        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getSupplierName());
            ps.setString(2, s.getAddress());
            ps.setString(3, s.getPhone());
            ps.setInt(4, s.getSupplierId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("update Supplier error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Supplier WHERE supplier_id=?";
        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("delete Supplier error: " + e.getMessage());
        }
        return false;
    }
}
