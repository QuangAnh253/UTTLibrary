/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.controller;

import com.uttlibrary.dao.SupplierDAO;
import com.uttlibrary.model.Supplier;
import com.uttlibrary.util.Validator;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class SupplierController {
    private final SupplierDAO dao = new SupplierDAO();

    // =============================
    // 1. LẤY DANH SÁCH NHÀ CUNG CẤP
    // =============================
    public List<Supplier> getAllSuppliers() {
        return dao.findAll();
    }

    // =============================
    // 2. KIỂM TRA DỮ LIỆU (VALIDATE)
    // =============================
    private String validate(Supplier s) {
        // Kiểm tra tên nhà cung cấp không được để trống
        if (Validator.isEmpty(s.getSupplierName())) {
            return "Tên nhà cung cấp không được để trống!";
        }

        // Kiểm tra địa chỉ không được để trống
        if (Validator.isEmpty(s.getAddress())) {
            return "Địa chỉ không được để trống!";
        }

        // Kiểm tra số điện thoại không được để trống
        if (Validator.isEmpty(s.getPhone())) {
            return "Số điện thoại không được để trống!";
        }
        
        // Bạn có thể thêm kiểm tra định dạng SĐT nếu cần (ví dụ: phải là số)
        
        return null; // Trả về null nếu tất cả dữ liệu đều hợp lệ
    }

    // =============================
    // 3. THÊM NHÀ CUNG CẤP
    // =============================
    public String addSupplier(Supplier supplier) {
        // Bước 1: Validate dữ liệu đầu vào
        String err = validate(supplier);
        if (err != null) return err;

        // Bước 2: Gọi DAO để chèn vào DB
        boolean ok = dao.insert(supplier);
        return ok ? "SUCCESS" : "Không thể thêm nhà cung cấp!";
    }

    // =============================
    // 4. CẬP NHẬT NHÀ CUNG CẤP
    // =============================
    public String updateSupplier(Supplier supplier) {
        // Kiểm tra ID trước khi sửa
        if (supplier.getSupplierId() <= 0) {
            return "ID nhà cung cấp không hợp lệ!";
        }

        // Validate các thông tin còn lại
        String err = validate(supplier);
        if (err != null) return err;

        // Gọi DAO để cập nhật
        boolean ok = dao.update(supplier);
        return ok ? "SUCCESS" : "Không thể cập nhật nhà cung cấp!";
    }

    // =============================
    // 5. XÓA NHÀ CUNG CẤP
    // =============================
    public String deleteSupplier(int id) {
        if (id <= 0) return "ID nhà cung cấp không hợp lệ!";

        boolean ok = dao.delete(id);
        return ok ? "SUCCESS" : "Không thể xóa nhà cung cấp!";
    }

    // =============================
    // 6. TÌM NHÀ CUNG CẤP THEO ID
    // =============================
    public Supplier findById(int id) {
        return dao.findById(id);
    }
}
