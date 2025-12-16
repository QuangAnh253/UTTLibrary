/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.dao;

import com.uttlibrary.model.Author;
import com.uttlibrary.util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class AuthorDAO extends BaseDAO<Author> {

    @Override
    public List<Author> findAll() {
        List<Author> list = new ArrayList<>();
        String sql = "SELECT * FROM Author";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Author a = new Author(
                        rs.getInt("author_id"),
                        rs.getString("author_name"),
                        rs.getString("website"),
                        rs.getString("note")
                );
                list.add(a);
            }

        } catch (SQLException e) {
            System.err.println("findAll error: " + e.getMessage());
        }

        return list;
    }

    @Override
    public Author findById(int id) {
        Author a = null;
        String sql = "SELECT * FROM Author WHERE author_id=?";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    a = new Author(
                            rs.getInt("author_id"),
                            rs.getString("author_name"),
                            rs.getString("website"),
                            rs.getString("note")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("findById error: " + e.getMessage());
        }

        return a;
    }

    @Override
    public boolean insert(Author a) {
        String sql = "INSERT INTO Author(author_name, website, note) VALUES(?,?,?)";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, a.getAuthorName());
            ps.setString(2, a.getWebsite());
            ps.setString(3, a.getNote());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("insert error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean update(Author a) {
        String sql = "UPDATE Author SET author_name=?, website=?, note=? WHERE author_id=?";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, a.getAuthorName());
            ps.setString(2, a.getWebsite());
            ps.setString(3, a.getNote());
            ps.setInt(4, a.getAuthorId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("update error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Author WHERE author_id=?";

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