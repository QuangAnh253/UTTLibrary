/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.controller;

import com.uttlibrary.dao.RegulationDAO;
import com.uttlibrary.model.Regulation;
import com.uttlibrary.util.Validator;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class RegulationController {
    private final RegulationDAO dao = new RegulationDAO();

    // =============================
    // 1. LẤY DANH SÁCH QUY ĐỊNH
    // =============================
    public List<Regulation> getAllRegulations() {
        return dao.findAll();
    }

    // =============================
    // 2. KIỂM TRA DỮ LIỆU (VALIDATE)
    // =============================
    private String validate(Regulation r) {
        // Kiểm tra tên quy định không được để trống
        if (Validator.isEmpty(r.getRegulationName())) {
            return "Tên quy định không được để trống!";
        }

        // Kiểm tra giá trị (thường các quy định thư viện phải >= 0)
        if (r.getValue() < 0) {
            return "Giá trị quy định không được là số âm!";
        }

        return null; // Hợp lệ
    }

    // =============================
    // 3. THÊM QUY ĐỊNH MỚI
    // =============================
    public String addRegulation(Regulation r) {
        String err = validate(r);
        if (err != null) return err;

        boolean ok = dao.insert(r);
        return ok ? "SUCCESS" : "Không thể thêm quy định mới!";
    }

    // =============================
    // 4. CẬP NHẬT QUY ĐỊNH
    // =============================
    public String updateRegulation(Regulation r) {
        if (r.getRegulationId() <= 0) {
            return "ID quy định không hợp lệ!";
        }

        String err = validate(r);
        if (err != null) return err;

        boolean ok = dao.update(r);
        return ok ? "SUCCESS" : "Không thể cập nhật quy định!";
    }

    // =============================
    // 5. XÓA QUY ĐỊNH
    // =============================
    public String deleteRegulation(int id) {
        if (id <= 0) return "ID quy định không hợp lệ!";

        boolean ok = dao.delete(id);
        return ok ? "SUCCESS" : "Không thể xóa quy định!";
    }

    // =============================
    // 6. TÌM QUY ĐỊNH THEO ID
    // =============================
    public Regulation findById(int id) {
        return dao.findById(id);
    }
}
