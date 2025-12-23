/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.controller;

import com.uttlibrary.dao.BookingDAO;
import com.uttlibrary.model.Booking;
import com.uttlibrary.util.Validator;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class BookingController {

    private final BookingDAO dao = new BookingDAO();

    // =============================
    // 1. LẤY DANH SÁCH BOOKING
    // =============================
    public List<Booking> getAllBookings() {
        return dao.findAll();
    }

    // =============================
    // 2. VALIDATE DỮ LIỆU
    // =============================
    private String validate(Booking b) {

        if (b.getReaderId() == null)
            return "ID độc giả không hợp lệ!";

        if (b.getBookId() == null)
            return "ID sách không hợp lệ!";

        if (b.getBookingDate() == null)
            return "Ngày đặt sách không hợp lệ!";

        if (Validator.isEmpty(b.getStatus()))
            return "Trạng thái không được để trống!";

        return null; // hợp lệ
    }

    // =============================
    // 3. THÊM BOOKING
    // =============================
    public String addBooking(Booking booking) {
        String err = validate(booking);
        if (err != null) return err;

        boolean ok = dao.insert(booking);
        return ok ? "SUCCESS" : "Không thể thêm booking!";
    }

    // =============================
    // 4. CẬP NHẬT BOOKING
    // =============================
    public String updateBooking(Booking booking) {
        if (booking.getBookingId() <= 0)
            return "ID booking không hợp lệ!";

        String err = validate(booking);
        if (err != null) return err;

        boolean ok = dao.update(booking);
        return ok ? "SUCCESS" : "Không thể cập nhật booking!";
    }

    // =============================
    // 5. XÓA BOOKING
    // =============================
    public String deleteBooking(int id) {
        if (id <= 0) return "ID booking không hợp lệ!";

        boolean ok = dao.delete(id);
        return ok ? "SUCCESS" : "Không thể xóa booking!";
    }

    // =============================
    // 6. TÌM BOOKING THEO ID
    // =============================
    public Booking findById(int id) {
        return dao.findById(id);
    }
}
