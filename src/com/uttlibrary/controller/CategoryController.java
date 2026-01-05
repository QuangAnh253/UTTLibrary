/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.controller;
import com.uttlibrary.dao.CategoryDAO;
import com.uttlibrary.model.Category;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class CategoryController {
    private final CategoryDAO dao = new CategoryDAO();

    // =============================
    // 1. LẤY DANH SÁCH DANH MỤC
    // =============================
    public List<Category> getAllCategories() {
        return dao.findAll();
    }

    // =============================
    // 2. KIỂM TRA DỮ LIỆU (VALIDATE)
    // =============================
    private String validate(Category c) {
        if (c == null) {
            return "Danh mục không được rỗng!";
        }

        if (c.getCategoryName() == null || c.getCategoryName().trim().isEmpty()) {
            return "Tên danh mục không được để trống!";
        }

        if (c.getCategoryName().length() > 100) {
            return "Tên danh mục quá dài!";
        }

        return null; // hợp lệ
    }

    // =============================
    // 3. THÊM DANH MỤC MỚI
    // =============================
    public String addCategory(Category c) {
        String err = validate(c);
        if (err != null) return err;

        boolean ok = dao.insert(c);
        return ok ? "SUCCESS" : "Không thể thêm danh mục!";
    }

    // =============================
    // 4. CẬP NHẬT DANH MỤC
    // =============================
    public String updateCategory(Category c) {
        if (c.getCategoryId() <= 0) {
            return "ID danh mục không hợp lệ!";
        }

        String err = validate(c);
        if (err != null) return err;

        boolean ok = dao.update(c);
        return ok ? "SUCCESS" : "Không thể cập nhật danh mục!";
    }

    // =============================
    // 5. XÓA DANH MỤC
    // =============================
    public String deleteCategory(int id) {
        if (id <= 0) {
            return "ID danh mục không hợp lệ!";
        }

        boolean ok = dao.delete(id);
        return ok ? "SUCCESS" : "Không thể xóa danh mục!";
    }

    // =============================
    // 6. TÌM THEO ID
    // =============================
    public Category findById(int id) {
        return dao.findById(id);
    }
}

