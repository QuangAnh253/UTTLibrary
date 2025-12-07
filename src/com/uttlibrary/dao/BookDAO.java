/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.dao;

/**
 *
 * @author ADMIN
 */

import com.uttlibrary.model.Book;
import com.uttlibrary.util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends BaseDAO<Book> {

    @Override
    public List<Book> findAll() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM Book";

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Book b = new Book(
                        rs.getInt("book_id"),
                        rs.getString("book_title"),
                        rs.getInt("publish_year"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("cover_image"),
                        rs.getInt("author_id"),
                        rs.getInt("publisher_id"),
                        rs.getInt("category_id"),
                        rs.getInt("shelf_id")
                );
                list.add(b);
            }
        } catch (Exception e) {
            System.err.println("findAll error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public Book findById(int id) {
        String sql = "SELECT * FROM Book WHERE book_id=?";
        Book b = null;

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                b = new Book(
                        rs.getInt("book_id"),
                        rs.getString("book_title"),
                        rs.getInt("publish_year"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("cover_image"),
                        rs.getInt("author_id"),
                        rs.getInt("publisher_id"),
                        rs.getInt("category_id"),
                        rs.getInt("shelf_id")
                );
            }
        } catch (Exception e) {
            System.err.println("findById error: " + e.getMessage());
        }
        return b;
    }

    @Override
    public boolean insert(Book b) {
        String sql = """
                INSERT INTO Book(book_title, publish_year, price, quantity, cover_image,
                                  author_id, publisher_id, category_id, shelf_id)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, b.getBookTitle());
            ps.setObject(2, b.getPublishYear());
            ps.setObject(3, b.getPrice());
            ps.setObject(4, b.getQuantity());
            ps.setObject(5, b.getCoverImage());
            ps.setObject(6, b.getAuthorId());
            ps.setObject(7, b.getPublisherId());
            ps.setObject(8, b.getCategoryId());
            ps.setObject(9, b.getShelfId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.err.println("insert error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Book b) {
        String sql = """
                UPDATE Book 
                SET book_title=?, publish_year=?, price=?, quantity=?, cover_image=?, 
                    author_id=?, publisher_id=?, category_id=?, shelf_id=?
                WHERE book_id=?
                """;

        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, b.getBookTitle());
            ps.setObject(2, b.getPublishYear());
            ps.setObject(3, b.getPrice());
            ps.setObject(4, b.getQuantity());
            ps.setObject(5, b.getCoverImage());
            ps.setObject(6, b.getAuthorId());
            ps.setObject(7, b.getPublisherId());
            ps.setObject(8, b.getCategoryId());
            ps.setObject(9, b.getShelfId());
            ps.setInt(10, b.getBookId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.err.println("update error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Book WHERE book_id=?";

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
