/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.dao;

import com.uttlibrary.model.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ReaderDAO extends BaseDAO<Reader> {

    @Override
    public List<Reader> findAll() {
        List<Reader> list = new ArrayList<>();
        String sql = "SELECT * FROM reader";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Reader r = new Reader(
                        rs.getInt("reader_id"),
                        rs.getString("full_name"),
                        rs.getDate("birthday"),
                        rs.getObject("gender", Boolean.class),
                        rs.getString("phone"),
                        rs.getString("reader_type")
                );
                list.add(r);
            }
        } catch (Exception e) {
            System.err.println("findAll Reader error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public Reader findById(int id) {
        String sql = "SELECT * FROM reader WHERE reader_id=?";
        Reader r = null;

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                r = new Reader(
                        rs.getInt("reader_id"),
                        rs.getString("full_name"),
                        rs.getDate("birthday"),
                        rs.getObject("gender", Boolean.class),
                        rs.getString("phone"),
                        rs.getString("reader_type")
                );
            }
        } catch (Exception e) {
            System.err.println("findById Reader error: " + e.getMessage());
        }
        return r;
    }

    @Override
    public boolean insert(Reader r) {
        String sql = """
                INSERT INTO reader(full_name, birthday, gender, phone, reader_type)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, r.getFullName());
            ps.setObject(2, r.getBirthday());
            ps.setObject(3, r.getGender());
            ps.setString(4, r.getPhone());
            ps.setString(5, r.getReaderType());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.err.println("insert Reader error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Reader r) {
        String sql = """
                UPDATE reader
                SET full_name=?, birthday=?, gender=?, phone=?, reader_type=?
                WHERE reader_id=?
                """;

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, r.getFullName());
            ps.setObject(2, r.getBirthday());
            ps.setObject(3, r.getGender());
            ps.setString(4, r.getPhone());
            ps.setString(5, r.getReaderType());
            ps.setInt(6, r.getReaderId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.err.println("update Reader error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM reader WHERE reader_id=?";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.err.println("delete Reader error: " + e.getMessage());
        }
        return false;
    }
}
