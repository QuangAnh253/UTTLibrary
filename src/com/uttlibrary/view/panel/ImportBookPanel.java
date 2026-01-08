/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.view.panel;

import com.uttlibrary.controller.ImportBookController;
import com.uttlibrary.model.ImportBook;
import com.uttlibrary.util.MessageBox;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ADMIN
 */
public class ImportBookPanel extends JPanel{
    private final ImportBookController controller = new ImportBookController();
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtBookId, txtQuantity, txtPrice;
    private int selectedId = -1;

    public ImportBookPanel() {
        setLayout(new BorderLayout());
        initUI();
        loadTableData();
    }

    private void initUI() {
        // 1. FORM NHẬP DỮ LIỆU
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Thông tin Nhập sách"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtBookId = new JTextField(20);
        txtQuantity = new JTextField(20);
        txtPrice = new JTextField(20);

        int row = 0;
        addField(form, gbc, row++, "Mã Sách (ID):", txtBookId);
        addField(form, gbc, row++, "Số lượng:", txtQuantity);
        addField(form, gbc, row++, "Giá nhập:", txtPrice);

        add(form, BorderLayout.NORTH);

        // 2. BẢNG HIỂN THỊ
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
            "ID Phiếu", "Mã Sách", "Số lượng", "Giá nhập", "Ngày nhập"
        });

        table = new JTable(model);
        table.setRowHeight(25);
        table.getSelectionModel().addListSelectionListener(e -> loadSelectedRow());

        add(new JScrollPane(table), BorderLayout.CENTER);

        // 3. NÚT CHỨC NĂNG
        JPanel buttons = new JPanel();
        JButton btnAdd = new JButton("Nhập sách");
        JButton btnUpdate = new JButton("Sửa phiếu");
        JButton btnDelete = new JButton("Xóa phiếu");
        JButton btnClear = new JButton("Làm mới");

        // Sử dụng [TênLớp.this] để tránh lỗi con trỏ this trong sự kiện
        btnAdd.addActionListener(e -> addImport());
        btnUpdate.addActionListener(e -> updateImport());
        btnDelete.addActionListener(e -> deleteImport());
        btnClear.addActionListener(e -> clearForm());

        buttons.add(btnAdd);
        buttons.add(btnUpdate);
        buttons.add(btnDelete);
        buttons.add(btnClear);

        add(buttons, BorderLayout.SOUTH);
    }

    private void addField(JPanel form, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.3;
        form.add(new JLabel(label), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        form.add(field, gbc);
    }

    private void loadTableData() {
        model.setRowCount(0);
        List<ImportBook> list = controller.getAllImportBooks();
        for (ImportBook ib : list) {
            model.addRow(new Object[]{
                ib.getImportId(),
                ib.getBookId(),
                ib.getQuantity(),
                ib.getImportPrice(),
                ib.getImportDate()
            });
        }
    }

    private ImportBook getFormData() throws Exception {
        ImportBook ib = new ImportBook();
        ib.setBookId(Integer.parseInt(txtBookId.getText().trim()));
        ib.setQuantity(Integer.parseInt(txtQuantity.getText().trim()));
        ib.setImportPrice(Double.parseDouble(txtPrice.getText().trim()));
        return ib;
    }

    private void addImport() {
        try {
            ImportBook ib = getFormData();
            String msg = controller.addImport(ib);
            if (msg.equals("SUCCESS")) {
                MessageBox.info(null, "Nhập sách thành công!");
                loadTableData();
                clearForm();
            } else {
                MessageBox.error(null, msg);
            }
        } catch (Exception e) {
            MessageBox.error(null, "Vui lòng nhập số hợp lệ cho ID sách, số lượng và giá!");
        }
    }

    private void updateImport() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Chọn một phiếu nhập để sửa!");
            return;
        }
        try {
            ImportBook ib = getFormData();
            ib.setImportId(selectedId);
            String msg = controller.updateImport(ib);
            if (msg.equals("SUCCESS")) {
                MessageBox.info(null, "Cập nhật thành công!");
                loadTableData();
            } else {
                MessageBox.error(null, msg);
            }
        } catch (Exception e) {
            MessageBox.error(null, "Dữ liệu nhập vào không hợp lệ!");
        }
    }

    private void deleteImport() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Chọn phiếu nhập cần xóa!");
            return;
        }
        if (!MessageBox.confirm(null, "Bạn có muốn xóa phiếu nhập này không?")) return;
        
        String msg = controller.deleteImport(selectedId);
        if (msg.equals("SUCCESS")) {
            MessageBox.info(null, "Đã xóa!");
            loadTableData();
            clearForm();
        } else {
            MessageBox.error(null, msg);
        }
    }

    private void loadSelectedRow() {
        int row = table.getSelectedRow();
        if (row < 0) return;
        selectedId = (int) model.getValueAt(row, 0);
        txtBookId.setText(model.getValueAt(row, 1).toString());
        txtQuantity.setText(model.getValueAt(row, 2).toString());
        txtPrice.setText(model.getValueAt(row, 3).toString());
    }

    private void clearForm() {
        selectedId = -1;
        txtBookId.setText("");
        txtQuantity.setText("");
        txtPrice.setText("");
        table.clearSelection();
    }
}
