/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.dao;

import com.uttlibrary.model.LoanTicket;
import com.uttlibrary.util.DateFormatter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanTicketDAO extends BaseDAO<LoanTicket> {

    @Override
    public List<LoanTicket> findAll() {
        List<LoanTicket> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConn();
            String sql = "SELECT * FROM LoanTicket ORDER BY ticket_id DESC";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                LoanTicket lt = new LoanTicket();
                lt.setTicketId(rs.getInt("ticket_id"));
                lt.setReaderId(rs.getInt("reader_id"));
                lt.setStaffId(rs.getInt("staff_id"));
                lt.setBorrowDate(rs.getTimestamp("borrow_date"));
                lt.setDueDate(rs.getTimestamp("due_date"));
                lt.setStatus(rs.getString("status"));
                lt.setTotalFine(rs.getDouble("total_fine"));
                list.add(lt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, ps, conn);
        }
        return list;
    }

    @Override
    public LoanTicket findById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConn();
            String sql = "SELECT * FROM LoanTicket WHERE ticket_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                LoanTicket lt = new LoanTicket();
                lt.setTicketId(rs.getInt("ticket_id"));
                lt.setReaderId(rs.getInt("reader_id"));
                lt.setStaffId(rs.getInt("staff_id"));
                lt.setBorrowDate(rs.getTimestamp("borrow_date"));
                lt.setDueDate(rs.getTimestamp("due_date"));
                lt.setStatus(rs.getString("status"));
                lt.setTotalFine(rs.getDouble("total_fine"));
                return lt;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, ps, conn);
        }
        return null;
    }

    @Override
    public boolean insert(LoanTicket entity) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConn();
            String sql = "INSERT INTO LoanTicket (reader_id, staff_id, borrow_date, due_date, status, total_fine) VALUES (?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, entity.getReaderId());
            ps.setInt(2, entity.getStaffId());
            ps.setTimestamp(3, new Timestamp(entity.getBorrowDate().getTime()));
            ps.setTimestamp(4, new Timestamp(entity.getDueDate().getTime()));
            ps.setString(5, entity.getStatus());
            ps.setDouble(6, entity.getTotalFine());

            int rows = ps.executeUpdate();
            
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    entity.setTicketId(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(null, ps, conn);
        }
        return false;
    }

    @Override
    public boolean update(LoanTicket entity) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConn();
            String sql = "UPDATE LoanTicket SET reader_id = ?, staff_id = ?, borrow_date = ?, due_date = ?, status = ?, total_fine = ? WHERE ticket_id = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, entity.getReaderId());
            ps.setInt(2, entity.getStaffId());
            ps.setTimestamp(3, new Timestamp(entity.getBorrowDate().getTime()));
            ps.setTimestamp(4, new Timestamp(entity.getDueDate().getTime()));
            ps.setString(5, entity.getStatus());
            ps.setDouble(6, entity.getTotalFine());
            ps.setInt(7, entity.getTicketId());

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
            String sql = "DELETE FROM LoanTicket WHERE ticket_id = ?";
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
    
    public List<LoanTicket> findByReaderId(int readerId) {
        List<LoanTicket> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConn();
            String sql = "SELECT * FROM LoanTicket WHERE reader_id = ? ORDER BY borrow_date DESC";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, readerId);
            rs = ps.executeQuery();

            while (rs.next()) {
                LoanTicket lt = new LoanTicket();
                lt.setTicketId(rs.getInt("ticket_id"));
                lt.setReaderId(rs.getInt("reader_id"));
                lt.setStaffId(rs.getInt("staff_id"));
                lt.setBorrowDate(rs.getTimestamp("borrow_date"));
                lt.setDueDate(rs.getTimestamp("due_date"));
                lt.setStatus(rs.getString("status"));
                lt.setTotalFine(rs.getDouble("total_fine"));
                list.add(lt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, ps, conn);
        }
        return list;
    }

    public List<Object[]> findAllWithReaderInfo() {
        List<Object[]> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConn();
            String sql = "SELECT lt.ticket_id, lt.reader_id, r.full_name, lt.borrow_date, lt.due_date, lt.status " +
                        "FROM LoanTicket lt " +
                        "INNER JOIN Reader r ON lt.reader_id = r.reader_id " +
                        "ORDER BY lt.ticket_id DESC";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Object[] row = new Object[6];
                row[0] = rs.getInt("ticket_id");
                row[1] = rs.getInt("reader_id");
                row[2] = rs.getString("full_name");
                row[3] = DateFormatter.formatDisplay(rs.getDate("borrow_date"));
                row[4] = DateFormatter.formatDisplay(rs.getDate("due_date"));
                row[5] = rs.getString("status");
                list.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, ps, conn);
        }
        return list;
    }
}