/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.dao;

import com.uttlibrary.model.Category;
import com.uttlibrary.util.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CategoryDAO extends BaseDAO<Category> {
    
    public boolean insert(Category c) {
        String sql = "INSERT INTO Category (category_name) VALUES (?)";
        try (Connection conn = getConn(); 
        PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, a.getCategoryName());
            return ps.executeUpdate() > 0;


        } catch (SqlException e) {
            throw new SqlException("Lỗi khi tạo danh mục mới");
        }
        return false;
    }

}
