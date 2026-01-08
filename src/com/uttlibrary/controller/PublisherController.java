/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.controller;

import com.uttlibrary.dao.PublisherDAO;
import com.uttlibrary.model.Publisher;
import com.uttlibrary.util.Validator;

import java.util.List;
/**
 *
 * @author ADMIN
 */
public class PublisherController {

    private final PublisherDAO dao = new PublisherDAO();

    // =============================
    // 1. LẤY DANH SÁCH NHÀ XUẤT BẢN
    // =============================
    public List<Publisher> getAllPublishers() {
        return dao.findAll();
    }

    // =============================
    // 2. VALIDATE DỮ LIỆU
    // =============================
    private String validate(Publisher p) {
        if (Validator.isEmpty(p.getPublisherName()))
            return "Tên NXB không được để trống!";

        // Address và email có thể để trống, không bắt validate bắt buộc
        return null;
    }

    // =============================
    // 3. THÊM NHÀ XUẤT BẢN
    // =============================
    public String addPublisher(Publisher p) {
        String err = validate(p);
        if (err != null) return err;

        boolean ok = dao.insert(p);
        return ok ? "SUCCESS" : "Không thể thêm NXB!";
    }

    // =============================
    // 4. CẬP NHẬT NHÀ XUẤT BẢN
    // =============================
    public String updatePublisher(Publisher p) {
        if (p.getPublisherId() <= 0)
            return "ID NXB không hợp lệ!";

        String err = validate(p);
        if (err != null) return err;

        boolean ok = dao.update(p);
        return ok ? "SUCCESS" : "Không thể cập nhật NXB!";
    }

    // =============================
    // 5. XÓA NHÀ XUẤT BẢN
    // =============================
    public String deletePublisher(int id) {
        if (id <= 0) return "ID NXB không hợp lệ!";

        boolean ok = dao.delete(id);
        return ok ? "SUCCESS" : "Không thể xóa NXB!";
    }

    // =============================
    // 6. TÌM NHÀ XUẤT BẢN THEO ID
    // =============================
    public Publisher findById(int id) {
        return dao.findById(id);
    }
}