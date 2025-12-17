/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.dao;

import com.uttlibrary.model.ImportBook;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class ImportBookDAO extends BaseDAO<ImportBook> {
    @Override
    public List<ImportBook> findAll() {
        List<ImportBook> list = new ArrayList<>();
        String sql = "SELECT * FROM ImportBook ORDER BY import_date DESC";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ImportBook ib = new ImportBook(
                        rs.getInt("import_id"),
                        rs.getInt("book_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("import_price"),
                        rs.getTimestamp("import_date")
                );
                list.add(ib);
            }
        } catch (SQLException e) {
            System.err.println("findAll ImportBook error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public ImportBook findById(int id) {
        String sql = "SELECT * FROM ImportBook WHERE import_id = ?";
        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ImportBook(
                            rs.getInt("import_id"),
                            rs.getInt("book_id"),
                            rs.getInt("quantity"),
                            rs.getDouble("import_price"),
                            rs.getTimestamp("import_date")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("findById ImportBook error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean insert(ImportBook ib) {
        // import_id tự tăng, import_date mặc định là CURRENT_TIMESTAMP
        String sql = "INSERT INTO ImportBook (book_id, quantity, import_price) VALUES (?, ?, ?)";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ib.getBookId());
            ps.setInt(2, ib.getQuantity());
            ps.setDouble(3, ib.getImportPrice());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("insert ImportBook error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(ImportBook ib) {
        String sql = "UPDATE ImportBook SET book_id = ?, quantity = ?, import_price = ? WHERE import_id = ?";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ib.getBookId());
            ps.setInt(2, ib.getQuantity());
            ps.setDouble(3, ib.getImportPrice());
            ps.setInt(4, ib.getImportId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("update ImportBook error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM ImportBook WHERE import_id = ?";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("delete ImportBook error: " + e.getMessage());
        }
        return false;
    }
    
}
