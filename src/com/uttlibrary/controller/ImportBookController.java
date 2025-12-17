/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.controller;

import com.uttlibrary.dao.ImportBookDAO;
import com.uttlibrary.model.ImportBook;
import com.uttlibrary.util.Validator;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class ImportBookController {
    private final ImportBookDAO dao = new ImportBookDAO();

    // =============================
    // 1. LẤY DANH SÁCH PHIẾU NHẬP
    // =============================
    public List<ImportBook> getAllImportBooks() {
        return dao.findAll(); // 
    }

    // =============================
    // 2. KIỂM TRA DỮ LIỆU (VALIDATE)
    // =============================
    private String validate(ImportBook ib) {
        // Kiểm tra mã sách
        if (ib.getBookId() <= 0) {
            return "Mã sách không hợp lệ!";
        }

        // Kiểm tra số lượng nhập (phải > 0)
        if (ib.getQuantity() <= 0) {
            return "Số lượng nhập phải lớn hơn 0!";
        }

        // Kiểm tra giá nhập (phải >= 0)
        if (ib.getImportPrice() < 0) {
            return "Giá nhập không được âm!";
        }

        return null; // Dữ liệu hợp lệ 
    }

    // =============================
    // 3. THÊM PHIẾU NHẬP MỚI
    // =============================
    public String addImport(ImportBook ib) {
        String err = validate(ib);
        if (err != null) return err;

        boolean ok = dao.insert(ib);
        return ok ? "SUCCESS" : "Không thể thêm phiếu nhập sách!"; // 
    }

    // =============================
    // 4. CẬP NHẬT PHIẾU NHẬP
    // =============================
    public String updateImport(ImportBook ib) {
        if (ib.getImportId() <= 0) {
            return "ID phiếu nhập không hợp lệ!";
        }

        String err = validate(ib);
        if (err != null) return err;

        boolean ok = dao.update(ib);
        return ok ? "SUCCESS" : "Không thể cập nhật phiếu nhập!";
    }

    // =============================
    // 5. XÓA PHIẾU NHẬP
    // =============================
    public String deleteImport(int id) {
        if (id <= 0) return "ID phiếu nhập không hợp lệ!";

        boolean ok = dao.delete(id);
        return ok ? "SUCCESS" : "Không thể xóa phiếu nhập!";
    }

    // =============================
    // 6. TÌM THEO ID
    // =============================
    public ImportBook findById(int id) {
        return dao.findById(id);
    }
}
