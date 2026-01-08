/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.dao;

/**
 *
 * @author ADMIN
 */

import com.uttlibrary.util.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO<T> {

    protected Connection getConn() throws SQLException {
        return DBConnect.getConnection();
    }

    protected void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) {
        try { if (rs != null) rs.close(); } catch (Exception ignored) {}
        try { if (ps != null) ps.close(); } catch (Exception ignored) {}
        try { if (conn != null) conn.close(); } catch (Exception ignored) {}
    }

    public abstract List<T> findAll();

    public abstract T findById(int id);

    public abstract boolean insert(T entity);

    public abstract boolean update(T entity);

    public abstract boolean delete(int id);
}
