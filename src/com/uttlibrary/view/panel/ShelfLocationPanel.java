package com.uttlibrary.view.panel;

import com.uttlibrary.controller.ShelfLocationController;
import com.uttlibrary.model.ShelfLocation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ShelfLocationPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtId, txtCode, txtDesc;
    private ShelfLocationController controller = new ShelfLocationController();

    public ShelfLocationPanel() {
        setLayout(new BorderLayout());
        initUI();
        loadData();
    }

    private void initUI() {
        // ===== TABLE =====
        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Mã kệ", "Mô tả"}, 0
        );
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== FORM =====
        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));

        txtId = new JTextField();
        txtId.setEditable(false);
        txtCode = new JTextField();
        txtDesc = new JTextField();

        form.add(new JLabel("ID"));
        form.add(txtId);
        form.add(new JLabel("Mã kệ"));
        form.add(txtCode);
        form.add(new JLabel("Mô tả"));
        form.add(txtDesc);

        add(form, BorderLayout.NORTH);

        // ===== BUTTONS =====
        JPanel buttons = new JPanel();

        JButton btnAdd = new JButton("Thêm");
        JButton btnUpdate = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        JButton btnClear = new JButton("Làm mới");

        buttons.add(btnAdd);
        buttons.add(btnUpdate);
        buttons.add(btnDelete);
        buttons.add(btnClear);

        add(buttons, BorderLayout.SOUTH);

        // ===== EVENTS =====
        table.getSelectionModel().addListSelectionListener(e -> showDetail());

        btnAdd.addActionListener(e -> addShelf());
        btnUpdate.addActionListener(e -> updateShelf());
        btnDelete.addActionListener(e -> deleteShelf());
        btnClear.addActionListener(e -> clearForm());
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<ShelfLocation> list = controller.getAll();
        for (ShelfLocation s : list) {
            tableModel.addRow(new Object[]{
                    s.getShelfId(),
                    s.getShelfCode(),
                    s.getDescription()
            });
        }
    }

    private void showDetail() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            txtId.setText(table.getValueAt(row, 0).toString());
            txtCode.setText(table.getValueAt(row, 1).toString());
            txtDesc.setText(table.getValueAt(row, 2).toString());
        }
    }

    private void addShelf() {
        ShelfLocation s = new ShelfLocation(
                0,
                txtCode.getText(),
                txtDesc.getText()
        );
        if (controller.add(s)) {
            loadData();
            clearForm();
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        }
    }

    private void updateShelf() {
        if (txtId.getText().isEmpty()) return;

        ShelfLocation s = new ShelfLocation(
                Integer.parseInt(txtId.getText()),
                txtCode.getText(),
                txtDesc.getText()
        );
        if (controller.update(s)) {
            loadData();
            JOptionPane.showMessageDialog(this, "Cập nhật thành công");
        }
    }

    private void deleteShelf() {
        if (txtId.getText().isEmpty()) return;

        int id = Integer.parseInt(txtId.getText());
        if (controller.delete(id)) {
            loadData();
            clearForm();
            JOptionPane.showMessageDialog(this, "Xóa thành công");
        }
    }

    private void clearForm() {
        txtId.setText("");
        txtCode.setText("");
        txtDesc.setText("");
        table.clearSelection();
    }
}
