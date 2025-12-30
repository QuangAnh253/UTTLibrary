/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.dao;

import com.uttlibrary.model.LoanDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanDetailDAO extends BaseDAO<LoanDetail> {

    @Override
    public List<LoanDetail> findAll() {
        List<LoanDetail> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConn();
            String sql = "SELECT * FROM LoanDetail ORDER BY detail_id DESC";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                LoanDetail ld = new LoanDetail();
                ld.setDetailId(rs.getInt("detail_id"));
                ld.setTicketId(rs.getInt("ticket_id"));
                ld.setBookId(rs.getInt("book_id"));
                ld.setQuantity(rs.getInt("quantity"));
                ld.setNote(rs.getString("note"));
                list.add(ld);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, ps, conn);
        }
        return list;
    }

    @Override
    public LoanDetail findById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConn();
            String sql = "SELECT * FROM LoanDetail WHERE detail_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                LoanDetail ld = new LoanDetail();
                ld.setDetailId(rs.getInt("detail_id"));
                ld.setTicketId(rs.getInt("ticket_id"));
                ld.setBookId(rs.getInt("book_id"));
                ld.setQuantity(rs.getInt("quantity"));
                ld.setNote(rs.getString("note"));
                return ld;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, ps, conn);
        }
        return null;
    }

    @Override
    public boolean insert(LoanDetail entity) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConn();
            String sql = "INSERT INTO LoanDetail (ticket_id, book_id, quantity, note) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, entity.getTicketId());
            ps.setInt(2, entity.getBookId());
            ps.setInt(3, entity.getQuantity());
            ps.setString(4, entity.getNote());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, ps, conn);
        }
        return false;
    }

    @Override
    public boolean update(LoanDetail entity) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConn();
            String sql = "UPDATE LoanDetail SET ticket_id = ?, book_id = ?, quantity = ?, note = ? WHERE detail_id = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, entity.getTicketId());
            ps.setInt(2, entity.getBookId());
            ps.setInt(3, entity.getQuantity());
            ps.setString(4, entity.getNote());
            ps.setInt(5, entity.getDetailId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, ps, conn);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConn();
            String sql = "DELETE FROM LoanDetail WHERE detail_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, ps, conn);
        }
        return false;
    }

    // ========== CUSTOM METHODS ==========

    public List<LoanDetail> findByTicketId(int ticketId) {
        List<LoanDetail> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConn();
            String sql = "SELECT * FROM LoanDetail WHERE ticket_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ticketId);
            rs = ps.executeQuery();

            while (rs.next()) {
                LoanDetail ld = new LoanDetail();
                ld.setDetailId(rs.getInt("detail_id"));
                ld.setTicketId(rs.getInt("ticket_id"));
                ld.setBookId(rs.getInt("book_id"));
                ld.setQuantity(rs.getInt("quantity"));
                ld.setNote(rs.getString("note"));
                list.add(ld);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, ps, conn);
        }
        return list;
    }

    public List<Object[]> findAllWithBookInfo() {
        List<Object[]> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConn();
            String sql = "SELECT ld.detail_id, ld.ticket_id, ld.book_id, b.book_title, ld.quantity " +
                        "FROM LoanDetail ld " +
                        "INNER JOIN Book b ON ld.book_id = b.book_id " +
                        "ORDER BY ld.detail_id DESC";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = rs.getInt("detail_id");
                row[1] = rs.getInt("ticket_id");
                row[2] = rs.getInt("book_id");
                row[3] = rs.getString("book_title");
                row[4] = rs.getInt("quantity");
                list.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, ps, conn);
        }
        return list;
    }

    public boolean deleteByTicketId(int ticketId) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConn();
            String sql = "DELETE FROM LoanDetail WHERE ticket_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ticketId);

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, ps, conn);
        }
        return false;
    }
}