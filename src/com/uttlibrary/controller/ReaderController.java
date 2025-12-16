/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.controller;

import com.uttlibrary.dao.ReaderDAO;
import com.uttlibrary.model.Reader;
import com.uttlibrary.util.Validator;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class ReaderController {

    private final ReaderDAO dao = new ReaderDAO();

    // =============================
    // 1. LẤY DANH SÁCH ĐỘC GIẢ
    // =============================
    public List<Reader> getAllReaders() {
        return dao.findAll();
    }

    // =============================
    // 2. VALIDATE DỮ LIỆU
    // =============================
    private String validate(Reader r) {

        if (Validator.isEmpty(r.getFullName()))
            return "Tên độc giả không được để trống!";

        if (r.getBirthday() == null)
            return "Ngày sinh không hợp lệ!";

        if (r.getGender() == null)
            return "Vui lòng chọn giới tính!";

        if (Validator.isEmpty(r.getPhone()))
            return "Số điện thoại không được để trống!";

        if (Validator.isEmpty(r.getReaderType()))
            return "Loại độc giả không hợp lệ!";

        return null; // hợp lệ
    }

    // =============================
    // 3. THÊM ĐỘC GIẢ
    // =============================
    public String addReader(Reader reader) {
        String err = validate(reader);
        if (err != null) return err;

        boolean ok = dao.insert(reader);
        return ok ? "SUCCESS" : "Không thể thêm độc giả!";
    }

    // =============================
    // 4. CẬP NHẬT ĐỘC GIẢ
    // =============================
    public String updateReader(Reader reader) {

        if (reader.getReaderId() == null || reader.getReaderId() <= 0)
            return "ID độc giả không hợp lệ!";

        String err = validate(reader);
        if (err != null) return err;

        boolean ok = dao.update(reader);
        return ok ? "SUCCESS" : "Không thể cập nhật độc giả!";
    }

    // =============================
    // 5. XÓA ĐỘC GIẢ
    // =============================
    public String deleteReader(int id) {
        if (id <= 0) return "ID độc giả không hợp lệ!";

        boolean ok = dao.delete(id);
        return ok ? "SUCCESS" : "Không thể xóa độc giả!";
    }

    // =============================
    // 6. TÌM ĐỘC GIẢ THEO ID
    // =============================
    public Reader findById(int id) {
        return dao.findById(id);
    }
}
