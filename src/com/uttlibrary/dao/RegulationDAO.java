/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.dao;

import com.uttlibrary.model.Regulation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class RegulationDAO extends BaseDAO<Regulation>{
    @Override
    public List<Regulation> findAll() {
        List<Regulation> list = new ArrayList<>();
        String sql = "SELECT * FROM Regulation";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Regulation r = new Regulation(
                        rs.getInt("regulation_id"),
                        rs.getString("regulation_name"),
                        rs.getInt("value"),
                        rs.getString("description")
                );
                list.add(r);
            }
        } catch (SQLException e) {
            System.err.println("findAll Regulation error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public Regulation findById(int id) {
        String sql = "SELECT * FROM Regulation WHERE regulation_id = ?";
        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Regulation(
                            rs.getInt("regulation_id"),
                            rs.getString("regulation_name"),
                            rs.getInt("value"),
                            rs.getString("description")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("findById Regulation error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean insert(Regulation r) {
        String sql = "INSERT INTO Regulation (regulation_name, value, description) VALUES (?, ?, ?)";
        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, r.getRegulationName());
            ps.setInt(2, r.getValue());
            ps.setString(3, r.getDescription());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("insert Regulation error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Regulation r) {
        String sql = "UPDATE Regulation SET regulation_name = ?, value = ?, description = ? WHERE regulation_id = ?";
        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, r.getRegulationName());
            ps.setInt(2, r.getValue());
            ps.setString(3, r.getDescription());
            ps.setInt(4, r.getRegulationId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("update Regulation error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Regulation WHERE regulation_id = ?";
        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("delete Regulation error: " + e.getMessage());
        }
        return false;
    }
}
