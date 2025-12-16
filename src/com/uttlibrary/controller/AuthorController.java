/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.controller;

import com.uttlibrary.dao.AuthorDAO;
import com.uttlibrary.model.Author;
import com.uttlibrary.util.Validator;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class AuthorController {

    private final AuthorDAO dao = new AuthorDAO();

    // =============================
    // 1. LẤY DANH SÁCH TÁC GIẢ
    // =============================
    public List<Author> getAllAuthors() {
        return dao.findAll();
    }

    // =============================
    // 2. VALIDATE DỮ LIỆU
    // =============================
    private String validate(Author a) {
        if (Validator.isEmpty(a.getAuthorName()))
            return "Tên tác giả không được để trống!";

        // Website và note có thể để trống, không validate bắt buộc
        return null; // hợp lệ
    }

    // =============================
    // 3. THÊM TÁC GIẢ
    // =============================
    public String addAuthor(Author a) {
        String err = validate(a);
        if (err != null) return err;

        boolean ok = dao.insert(a);
        return ok ? "SUCCESS" : "Không thể thêm tác giả!";
    }

    // =============================
    // 4. CẬP NHẬT TÁC GIẢ
    // =============================
    public String updateAuthor(Author a) {
        if (a.getAuthorId() <= 0)
            return "ID tác giả không hợp lệ!";

        String err = validate(a);
        if (err != null) return err;

        boolean ok = dao.update(a);
        return ok ? "SUCCESS" : "Không thể cập nhật tác giả!";
    }

    // =============================
    // 5. XÓA TÁC GIẢ
    // =============================
    public String deleteAuthor(int id) {
        if (id <= 0) return "ID tác giả không hợp lệ!";

        boolean ok = dao.delete(id);
        return ok ? "SUCCESS" : "Không thể xóa tác giả!";
    }

    // =============================
    // 6. TÌM TÁC GIẢ THEO ID
    // =============================
    public Author findById(int id) {
        return dao.findById(id);
    }
}