/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.view.panel;


import com.uttlibrary.controller.AuthorController;
import com.uttlibrary.model.Author;
import com.uttlibrary.util.MessageBox;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class AuthorPanel extends JPanel {

    private final AuthorController controller = new AuthorController();

    private JTable table;
    private DefaultTableModel model;

    private JTextField txtName, txtWebsite;
    private JTextArea txtNote;

    private int selectedId = -1;

    public AuthorPanel() {
        setLayout(new BorderLayout());
        initUI();
        loadTableData();
    }

    // =========================================================
    // KHỞI TẠO GIAO DIỆN
    // =========================================================
    private void initUI() {
        // Form nhập liệu
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Thông tin tác giả"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,8,8,8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        txtName = new JTextField();
        txtWebsite = new JTextField();
        txtNote = new JTextArea(3, 20);

        int row = 0;
        addField(form, gbc, row++, "Tên tác giả:", txtName);
        addField(form, gbc, row++, "Website:", txtWebsite);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.2;
        form.add(new JLabel("Ghi chú:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.8;
        form.add(new JScrollPane(txtNote), gbc);

        add(form, BorderLayout.NORTH);

        // Bảng hiển thị
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Tên tác giả", "Website", "Ghi chú"});

        table = new JTable(model);
        table.setRowHeight(25);
        table.getSelectionModel().addListSelectionListener(e -> loadSelectedRow());

        add(new JScrollPane(table), BorderLayout.CENTER);

        // Nút thao tác
        JPanel buttons = new JPanel();
        JButton btnAdd = new JButton("Thêm");
        JButton btnUpdate = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        JButton btnClear = new JButton("Làm mới");

        btnAdd.addActionListener(e -> addAuthor());
        btnUpdate.addActionListener(e -> updateAuthor());
        btnDelete.addActionListener(e -> deleteAuthor());
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
        List<Author> list = controller.getAllAuthors();
        for (Author a : list) {
            model.addRow(new Object[]{
                    a.getAuthorId(),
                    a.getAuthorName(),
                    a.getWebsite(),
                    a.getNote()
            });
        }
    }

    // =========================================================
    // LẤY DỮ LIỆU TỪ FORM
    // =========================================================
    private Author getFormData() {
        Author a = new Author();
        a.setAuthorName(txtName.getText());
        a.setWebsite(txtWebsite.getText());
        a.setNote(txtNote.getText());
        return a;
    }

    // =========================================================
    // NÚT THÊM
    // =========================================================
    private void addAuthor() {
        String msg = controller.addAuthor(getFormData());
        if ("SUCCESS".equals(msg)) {
            MessageBox.info(null, "Thêm tác giả thành công!");
            loadTableData();
            clearForm();
        } else {
            MessageBox.error(null, msg);
        }
    }

    // =========================================================
    // NÚT SỬA
    // =========================================================
    private void updateAuthor() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Chọn tác giả để sửa!");
            return;
        }
        Author a = getFormData();
        a.setAuthorId(selectedId);

        String msg = controller.updateAuthor(a);
        if ("SUCCESS".equals(msg)) {
            MessageBox.info(null, "Cập nhật thành công!");
            loadTableData();
        } else {
            MessageBox.error(null, msg);
        }
    }

    // =========================================================
    // NÚT XÓA
    // =========================================================
    private void deleteAuthor() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Chọn tác giả để xóa!");
            return;
        }
        if (!MessageBox.confirm(null, "Xóa tác giả này?")) return;

        String msg = controller.deleteAuthor(selectedId);
        if ("SUCCESS".equals(msg)) {
            MessageBox.info(null, "Đã xóa!");
            loadTableData();
            clearForm();
        } else {
            MessageBox.error(null, msg);
        }
    }

    // =========================================================
    // KHI CLICK TABLE → LOAD FORM
    // =========================================================
    private void loadSelectedRow() {
        int row = table.getSelectedRow();
        if (row < 0) return;

        selectedId = (int) model.getValueAt(row, 0);
        txtName.setText(model.getValueAt(row, 1).toString());
        txtWebsite.setText(model.getValueAt(row, 2).toString());
        txtNote.setText(model.getValueAt(row, 3) != null ? model.getValueAt(row, 3).toString() : "");
    }

    // =========================================================
    // CLEAR FORM
    // =========================================================
    private void clearForm() {
        selectedId = -1;
        txtName.setText("");
        txtWebsite.setText("");
        txtNote.setText("");
        table.clearSelection();
    }
}