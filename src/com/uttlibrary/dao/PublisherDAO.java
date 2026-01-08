/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.dao;

import com.uttlibrary.model.Publisher;
import com.uttlibrary.util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */


public class PublisherDAO extends BaseDAO<Publisher> {

    @Override
    public List<Publisher> findAll() {
        List<Publisher> list = new ArrayList<>();
        String sql = "SELECT * FROM Publisher";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Publisher p = new Publisher(
                        rs.getInt("publisher_id"),
                        rs.getString("publisher_name"),
                        rs.getString("address"),
                        rs.getString("email")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            System.err.println("findAll error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public Publisher findById(int id) {
        Publisher p = null;
        String sql = "SELECT * FROM Publisher WHERE publisher_id=?";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p = new Publisher(
                            rs.getInt("publisher_id"),
                            rs.getString("publisher_name"),
                            rs.getString("address"),
                            rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("findById error: " + e.getMessage());
        }

        return p;
    }

    @Override
    public boolean insert(Publisher p) {
        String sql = "INSERT INTO Publisher(publisher_name, address, email) VALUES(?,?,?)";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getPublisherName());
            ps.setString(2, p.getAddress());
            ps.setString(3, p.getEmail());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("insert error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean update(Publisher p) {
        String sql = "UPDATE Publisher SET publisher_name=?, address=?, email=? WHERE publisher_id=?";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getPublisherName());
            ps.setString(2, p.getAddress());
            ps.setString(3, p.getEmail());
            ps.setInt(4, p.getPublisherId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("update error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Publisher WHERE publisher_id=?";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("delete error: " + e.getMessage());
        }

        return false;
    }
}