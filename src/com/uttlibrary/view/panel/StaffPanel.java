/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.view.panel;

import com.uttlibrary.controller.StaffController;
import com.uttlibrary.model.Staff;
import com.uttlibrary.util.MessageBox;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class StaffPanel extends JPanel {

    private StaffController controller = new StaffController();

    private JTable table;
    private DefaultTableModel model;

    private JTextField txtUsername, txtPassword, txtFullName, txtRole;

    private int selectedId = -1;

    public StaffPanel() {
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
        form.setBorder(BorderFactory.createTitledBorder("Thông tin nhân viên"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        txtUsername = new JTextField();
        txtPassword = new JTextField();
        txtFullName = new JTextField();
        txtRole = new JTextField();

        int row = 0;
        addField(form, gbc, row++, "Username:", txtUsername);
        addField(form, gbc, row++, "Password:", txtPassword);
        addField(form, gbc, row++, "Full Name:", txtFullName);
        addField(form, gbc, row++, "Role:", txtRole);

        add(form, BorderLayout.NORTH);

        // ============================
        // TABLE
        // ============================
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Username", "Password", "Full Name", "Role"});

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

        btnAdd.addActionListener(e -> addStaff());
        btnUpdate.addActionListener(e -> updateStaff());
        btnDelete.addActionListener(e -> deleteStaff());
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

        List<Staff> list = controller.getAllStaff();
        for (Staff s : list) {
            model.addRow(new Object[]{
                s.getStaffId(),
                s.getUsername(),
                s.getPassword(),
                s.getFullName(),
                s.getRole()
            });
        }
    }

    // =========================================================
    // LẤY DỮ LIỆU TỪ FORM → MODEL STAFF
    // =========================================================
    private Staff getFormData() {
        Staff s = new Staff();

        s.setUsername(txtUsername.getText());
        s.setPassword(txtPassword.getText());
        s.setFullName(txtFullName.getText());
        s.setRole(txtRole.getText());

        return s;
    }

    // =========================================================
    // NÚT THÊM
    // =========================================================
    private void addStaff() {
        Staff s = getFormData();
        String msg = controller.addStaff(s);

        if (msg.equals("SUCCESS")) {
            MessageBox.info(null, "Thêm nhân viên thành công!");
            loadTableData();
            clearForm();
        } else {
            MessageBox.error(null, msg);
        }
    }

    // =========================================================
    // NÚT SỬA
    // =========================================================
    private void updateStaff() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Vui lòng chọn nhân viên cần sửa!");
            return;
        }

        Staff s = getFormData();
        s.setStaffId(selectedId);

        String msg = controller.updateStaff(s);

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
    private void deleteStaff() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Chọn nhân viên để xóa!");
            return;
        }

        if (!MessageBox.confirm(null, "Xóa nhân viên này?")) {
            return;
        }

        String msg = controller.deleteStaff(selectedId);

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
        if (row < 0) return;

        selectedId = (int) model.getValueAt(row, 0);

        txtUsername.setText(model.getValueAt(row, 1).toString());
        txtPassword.setText(model.getValueAt(row, 2).toString());
        txtFullName.setText(model.getValueAt(row, 3).toString());
        txtRole.setText(model.getValueAt(row, 4).toString());
    }

    // =========================================================
    // CLEAR FORM
    // =========================================================
    private void clearForm() {
        selectedId = -1;
        txtUsername.setText("");
        txtPassword.setText("");
        txtFullName.setText("");
        txtRole.setText("");
        table.clearSelection();
    }  
}
