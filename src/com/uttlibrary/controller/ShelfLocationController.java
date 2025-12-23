/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.controller;
import com.uttlibrary.dao.ShelfLocationDAO;
import com.uttlibrary.model.ShelfLocation;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class ShelfLocationController {
     private ShelfLocationDAO ShelfDAO;

    public ShelfLocationController() {
        ShelfDAO = new ShelfLocationDAO() {
            public boolean insert(Object entity) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            public boolean update(Object entity) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
    }

    // Lấy tất cả vị trí kệ sách
    public List<ShelfLocation> getAll() {
        return ShelfDAO.findAll();
    }

    // Thêm mới vị trí kệ
    public boolean add(ShelfLocation s) {
        if (s == null) return false;
        if (s.getShelfCode() == null || s.getShelfCode().isEmpty()) return false;
        return ShelfDAO.insert(s);
    }

    // Cập nhật vị trí kệ
    public boolean update(ShelfLocation s) {
        if (s == null) return false;
        if (s.getShelfId() <= 0) return false;
        return ShelfDAO.update(s);
    }

    // Xóa vị trí kệ theo ID
    public boolean delete(int id) {
        if (id <= 0) return false;
        return ShelfDAO.delete(id);
    }

    // Tìm theo ID (nếu cần)
    public ShelfLocation findById(int id) {
        return ShelfDAO.findById(id);
    }
}

