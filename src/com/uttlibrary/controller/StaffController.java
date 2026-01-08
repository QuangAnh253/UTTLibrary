/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.controller;

import com.uttlibrary.dao.StaffDAO;
import com.uttlibrary.model.Staff;
import com.uttlibrary.util.Validator;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class StaffController {

    private final StaffDAO dao = new StaffDAO();

    // =============================
    // 1. LẤY DANH SÁCH NHÂN VIÊN
    // =============================
    public List<Staff> getAllStaff() {
        return dao.findAll();
    }

    // =============================
    // 2. VALIDATE DỮ LIỆU
    // =============================
    private String validate(Staff s) {
        if (Validator.isEmpty(s.getUsername()))
            return "Tên đăng nhập không được để trống!";

        if (Validator.isEmpty(s.getPassword()))
            return "Mật khẩu không được để trống!";

        if (Validator.isEmpty(s.getFullName()))
            return "Họ và tên không được để trống!";

        if (Validator.isEmpty(s.getRole()))
            return "Vai trò không được để trống!";

        return null; // hợp lệ
    }

    // =============================
    // 3. THÊM NHÂN VIÊN
    // =============================
    public String addStaff(Staff staff) {
        String err = validate(staff);
        if (err != null) return err;

        boolean ok = dao.insert(staff);
        return ok ? "SUCCESS" : "Không thể thêm nhân viên!";
    }

    // =============================
    // 4. CẬP NHẬT NHÂN VIÊN
    // =============================
    public String updateStaff(Staff staff) {
        if (staff.getStaffId() <= 0)
            return "ID nhân viên không hợp lệ!";

        String err = validate(staff);
        if (err != null) return err;

        boolean ok = dao.update(staff);
        return ok ? "SUCCESS" : "Không thể cập nhật nhân viên!";
    }

    // =============================
    // 5. XÓA NHÂN VIÊN
    // =============================
    public String deleteStaff(int id) {
        if (id <= 0) return "ID nhân viên không hợp lệ!";

        boolean ok = dao.delete(id);
        return ok ? "SUCCESS" : "Không thể xóa nhân viên!";
    }

    // =============================
    // 6. TÌM NHÂN VIÊN THEO ID
    // =============================
    public Staff findById(int id) {
        return dao.findById(id);
    }
}
