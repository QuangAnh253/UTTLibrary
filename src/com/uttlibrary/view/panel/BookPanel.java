/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.view.panel;

/**
 *
 * @author ADMIN
 */
import com.uttlibrary.controller.BookController;
import com.uttlibrary.model.Book;
import com.uttlibrary.util.MessageBox;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BookPanel extends JPanel {

    private BookController controller = new BookController();

    private JTable table;
    private DefaultTableModel model;

    private JTextField txtTitle, txtYear, txtPrice, txtQuantity, txtCover;
    private JComboBox<String> cboAuthor, cboPublisher, cboCategory, cboShelf;

    private int selectedId = -1;

    public BookPanel() {
        setLayout(new BorderLayout());
        initUI();
        loadTableData();
    }

    // =========================================================
    // UI KHỞI TẠO
    // =========================================================

    private void initUI() {

        // ============================
        // FORM NHẬP LIỆU
        // ============================
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Thông tin sách"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        txtTitle = new JTextField();
        txtYear = new JTextField();
        txtPrice = new JTextField();
        txtQuantity = new JTextField();
        txtCover = new JTextField();

        cboAuthor = new JComboBox<>(new String[]{"1", "2", "3"});
        cboPublisher = new JComboBox<>(new String[]{"1", "2", "3"});
        cboCategory = new JComboBox<>(new String[]{"1", "2", "3"});
        cboShelf = new JComboBox<>(new String[]{"1", "2", "3"});

        int row = 0;

        addField(form, gbc, row++, "Tên sách:", txtTitle);
        addField(form, gbc, row++, "Tác giả ID:", cboAuthor);
        addField(form, gbc, row++, "NXB ID:", cboPublisher);
        addField(form, gbc, row++, "Thể loại ID:", cboCategory);
        addField(form, gbc, row++, "Kệ sách ID:", cboShelf);
        addField(form, gbc, row++, "Năm XB:", txtYear);
        addField(form, gbc, row++, "Giá:", txtPrice);
        addField(form, gbc, row++, "Số lượng:", txtQuantity);
        addField(form, gbc, row++, "Ảnh bìa (URL):", txtCover);

        add(form, BorderLayout.NORTH);

        // ============================
        // TABLE
        // ============================
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                "ID", "Tên sách", "Tác giả", "NXB", "Thể loại",
                "Kệ", "Năm XB", "Giá", "Số lượng", "Ảnh"
        });

        table = new JTable(model);
        table.setRowHeight(25);
        table.getSelectionModel().addListSelectionListener(e -> loadSelectedRow());

        add(new JScrollPane(table), BorderLayout.CENTER);

        // ============================
        // BUTTONS
        // ============================
        JPanel buttons = new JPanel();

        JButton btnAdd = new JButton("Thêm");
        JButton btnUpdate = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        JButton btnClear = new JButton("Làm mới");

        btnAdd.addActionListener(e -> addBook());
        btnUpdate.addActionListener(e -> updateBook());
        btnDelete.addActionListener(e -> deleteBook());
        btnClear.addActionListener(e -> clearForm());

        buttons.add(btnAdd);
        buttons.add(btnUpdate);
        buttons.add(btnDelete);
        buttons.add(btnClear);

        add(buttons, BorderLayout.SOUTH);
    }

    private void addField(JPanel form, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.2;
        form.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.8;
        form.add(field, gbc);
    }

    // =========================================================
    // LOAD TABLE DATA
    // =========================================================
    private void loadTableData() {
        model.setRowCount(0);

        List<Book> list = controller.getAllBooks();
        for (Book b : list) {
            model.addRow(new Object[]{
                b.getBookId(),
                b.getBookTitle(),
                b.getAuthorId(),
                b.getPublisherId(),
                b.getCategoryId(),
                b.getShelfId(),
                b.getPublishYear(),
                b.getPrice(),
                b.getQuantity(),
                b.getCoverImage()
            });
        }
    }

    // =========================================================
    // LẤY DỮ LIỆU TỪ FORM → MODEL BOOK
    // =========================================================
    private Book getFormData() {
        Book b = new Book();

        b.setBookTitle(txtTitle.getText());
        b.setPublishYear(Integer.valueOf(txtYear.getText()));
        b.setPrice(Double.valueOf(txtPrice.getText()));
        b.setQuantity(Integer.valueOf(txtQuantity.getText()));
        b.setCoverImage(txtCover.getText());

        b.setAuthorId(Integer.valueOf((String) cboAuthor.getSelectedItem()));
        b.setPublisherId(Integer.valueOf((String) cboPublisher.getSelectedItem()));
        b.setCategoryId(Integer.valueOf((String) cboCategory.getSelectedItem()));
        b.setShelfId(Integer.valueOf((String) cboShelf.getSelectedItem()));

        return b;
    }

    // =========================================================
    // NÚT THÊM
    // =========================================================
    private void addBook() {
        Book b = getFormData();
        String msg = controller.addBook(b);

        if (msg.equals("SUCCESS")) {
            MessageBox.info(null, "Thêm sách thành công!");
            loadTableData();
            clearForm();
        } else {
            MessageBox.error(null, msg);
        }
    }

    // =========================================================
    // NÚT SỬA
    // =========================================================
    private void updateBook() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Vui lòng chọn sách cần sửa!");
            return;
        }

        Book b = getFormData();
        b.setBookId(selectedId);

        String msg = controller.updateBook(b);

        if (msg.equals("SUCCESS")) {
            MessageBox.info(null, "Cập nhật thành công!");
            loadTableData();
        } else {
            MessageBox.error(null, msg);
        }
    }

    // =========================================================
    // NÚT XÓA
    // =========================================================
    private void deleteBook() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Chọn sách để xóa!");
            return;
        }

        if (!MessageBox.confirm(null, "Xóa sách này?")) {
            return;
        }

        String msg = controller.deleteBook(selectedId);

        if (msg.equals("SUCCESS")) {
            MessageBox.info(null, "Đã xóa");
            loadTableData();
            clearForm();
        } else {
            MessageBox.error(null, msg);
        }
    }

    // =========================================================
    // KHI CLICK TABLE → LOAD LÊN FORM
    // =========================================================
    private void loadSelectedRow() {
        int row = table.getSelectedRow();
        if (row < 0) {
            return;
        }

        selectedId = (int) model.getValueAt(row, 0);

        txtTitle.setText(model.getValueAt(row, 1).toString());
        cboAuthor.setSelectedItem(model.getValueAt(row, 2).toString());
        cboPublisher.setSelectedItem(model.getValueAt(row, 3).toString());
        cboCategory.setSelectedItem(model.getValueAt(row, 4).toString());
        cboShelf.setSelectedItem(model.getValueAt(row, 5).toString());
        txtYear.setText(model.getValueAt(row, 6).toString());
        txtPrice.setText(model.getValueAt(row, 7).toString());
        txtQuantity.setText(model.getValueAt(row, 8).toString());
        txtCover.setText(model.getValueAt(row, 9) != null ? model.getValueAt(row, 9).toString() : "");
    }

    // =========================================================
    // CLEAR FORM
    // =========================================================
    private void clearForm() {
        selectedId = -1;
        txtTitle.setText("");
        txtYear.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");
        txtCover.setText("");

        cboAuthor.setSelectedIndex(0);
        cboPublisher.setSelectedIndex(0);
        cboCategory.setSelectedIndex(0);
        cboShelf.setSelectedIndex(0);

        table.clearSelection();
    }
}
