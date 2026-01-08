/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.controller;

/**
 *
 * @author ADMIN
 */

import com.uttlibrary.dao.BookDAO;
import com.uttlibrary.model.Book;
import com.uttlibrary.util.Validator;

import java.util.List;
import javax.swing.*;

public class BookController {

    private final BookDAO dao = new BookDAO();

    // =============================
    // 1. LẤY DANH SÁCH SÁCH
    // =============================
    public List<Book> getAllBooks() {
        return dao.findAll();
    }

    // =============================
    // 2. VALIDATE DỮ LIỆU
    // =============================
    private String validate(Book b) {

        if (Validator.isEmpty(b.getBookTitle()))
            return "Tên sách không được để trống!";

        if (b.getAuthorId() == null)
            return "Tác giả không hợp lệ!";

        if (b.getPublisherId() == null)
            return "Nhà xuất bản không hợp lệ!";

        if (b.getCategoryId() == null)
            return "Thể loại không hợp lệ!";

        if (b.getShelfId() == null)
            return "Vị trí kệ không hợp lệ!";

        if (b.getPublishYear() != null && b.getPublishYear() < 0)
            return "Năm xuất bản không hợp lệ!";

        if (b.getQuantity() != null && b.getQuantity() < 0)
            return "Số lượng không hợp lệ!";

        if (b.getPrice() != null && b.getPrice() < 0)
            return "Giá sách không hợp lệ!";

        return null; // hợp lệ
    }

    // =============================
    // 3. THÊM SÁCH
    // =============================
    public String addBook(Book book) {
        String err = validate(book);
        if (err != null) return err;

        boolean ok = dao.insert(book);
        return ok ? "SUCCESS" : "Không thể thêm sách!";
    }

    // =============================
    // 4. CẬP NHẬT SÁCH
    // =============================
    public String updateBook(Book book) {
        if (book.getBookId() <= 0)
            return "ID sách không hợp lệ!";

        String err = validate(book);
        if (err != null) return err;

        boolean ok = dao.update(book);
        return ok ? "SUCCESS" : "Không thể cập nhật sách!";
    }

    // =============================
    // 5. XÓA SÁCH
    // =============================
    public String deleteBook(int id) {
        if (id <= 0) return "ID sách không hợp lệ!";

        boolean ok = dao.delete(id);
        return ok ? "SUCCESS" : "Không thể xóa sách!";
    }

    // =============================
    // 6. TÌM SÁCH THEO ID
    // =============================
    public Book findById(int id) {
        return dao.findById(id);
    }
}