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
    private final ShelfLocationDAO dao = new ShelfLocationDAO();
// lấy danh sách phiếu nhập
    public List<ShelfLocation> getAllShelves() {
        return dao.findAll();
    }
// kiểm tra 
    private String validate(ShelfLocation s) {
        if (s.getShelfName() == null || s.getShelfName().trim().isEmpty()) {
            return "Tên kệ không được để trống!";
        }
        return null;
    }
// thêm 
    public String addShelf(ShelfLocation s) {
        String err = validate(s);
        if (err != null) return err;

        boolean ok = dao.insert(s);
        return ok ? "SUCCESS" : "Không thể thêm vị trí kệ!";
    }
// cập nhật 
    public String updateShelf(ShelfLocation s) {
        if (s.getShelfId() <= 0) return "ID kệ không hợp lệ!";
        
        String err = validate(s);
        if (err != null) return err;

        boolean ok = dao.update(s);
        return ok ? "SUCCESS" : "Không thể cập nhật vị trí kệ!";
    }
//  xóa 
    public String deleteShelf(int id) {
        if (id <= 0) return "ID kệ không hợp lệ!";
        
        boolean ok = dao.delete(id);
        return ok ? "SUCCESS" : "Có lỗi xảy ra hoặc kệ đang được sử dụng, không thể xóa!";
    }
    public ShelfLocation findById(int id) {
        return dao.findById(id);
    }
}