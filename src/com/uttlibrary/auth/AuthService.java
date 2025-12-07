/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.auth;

/**
 *
 * @author ADMIN
 */

import com.uttlibrary.util.DBConnect;
import java.sql.*;

public class AuthService {

    public boolean login(String username, String password) {
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "SELECT * FROM staff WHERE username=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            throw new RuntimeException("Login error: " + e.getMessage());
        }
    }
}
