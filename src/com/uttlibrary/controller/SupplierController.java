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

    public List<Supplier> getAllSuppliers() {
        return dao.findAll();
    }

    private String validate(Supplier s) {
        if (Validator.isEmpty(s.getSupplierName())) return "Tên NCC không được để trống!";
        if (Validator.isEmpty(s.getAddress())) return "Địa chỉ không được để trống!";
        if (Validator.isEmpty(s.getPhone())) return "SĐT không được để trống!";
        return null;
    }

    public String addSupplier(Supplier s) {
        String err = validate(s);
        if (err != null) return err;
        return dao.insert(s) ? "SUCCESS" : "Lỗi khi thêm!";
    }

    public String updateSupplier(Supplier s) {
        if (s.getSupplierId() <= 0) return "ID không hợp lệ!";
        String err = validate(s);
        if (err != null) return err;
        return dao.update(s) ? "SUCCESS" : "Lỗi khi sửa!";
    }

    public String deleteSupplier(int id) {
        if (id <= 0) return "ID không hợp lệ!";
        return dao.delete(id) ? "SUCCESS" : "Lỗi khi xóa!";
    }
    
}
