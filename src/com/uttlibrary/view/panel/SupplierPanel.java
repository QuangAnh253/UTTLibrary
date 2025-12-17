/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.view.panel;


import com.uttlibrary.controller.SupplierController;
import com.uttlibrary.model.Supplier;
import com.uttlibrary.util.MessageBox;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class SupplierPanel extends JPanel {
    private SupplierController controller = new SupplierController();

    // UI COMPONENTS
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtSupplierName, txtAddress, txtPhone;
    
    private int selectedId = -1; // Lưu ID của dòng đang được chọn

    public SupplierPanel() {
        setLayout(new BorderLayout());
        initUI();
        loadTableData();
    }

    // =====================================================
    // KHỞI TẠO GIAO DIỆN
    // =====================================================
    private void initUI() {
        // 1. FORM NHẬP DỮ LIỆU (Phía trên)
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Thông tin Nhà cung cấp"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        txtSupplierName = new JTextField(20);
        txtAddress = new JTextField(20);
        txtPhone = new JTextField(20);

        int row = 0;
        addField(form, gbc, row++, "Tên nhà cung cấp:", txtSupplierName);
        addField(form, gbc, row++, "Địa chỉ:", txtAddress);
        addField(form, gbc, row++, "Số điện thoại:", txtPhone);

        add(form, BorderLayout.NORTH);

        // 2. BẢNG HIỂN THỊ (Ở giữa)
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                "ID", "Tên nhà cung cấp", "Địa chỉ", "Số điện thoại"
        });

        table = new JTable(model);
        table.setRowHeight(25);
        // Sự kiện khi click vào một dòng trên bảng
        table.getSelectionModel().addListSelectionListener(e -> loadSelectedRow());

        add(new JScrollPane(table), BorderLayout.CENTER);

        // 3. NÚT CHỨC NĂNG (Phía dưới)
        JPanel buttons = new JPanel();
        JButton btnAdd = new JButton("Thêm");
        JButton btnUpdate = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        JButton btnClear = new JButton("Làm mới");

        btnAdd.addActionListener(e -> addSupplier());
        btnUpdate.addActionListener(e -> updateSupplier());
        btnDelete.addActionListener(e -> deleteSupplier());
        btnClear.addActionListener(e -> clearForm());

        buttons.add(btnAdd);
        buttons.add(btnUpdate);
        buttons.add(btnDelete);
        buttons.add(btnClear);

        add(buttons, BorderLayout.SOUTH);
    }

    // Hàm hỗ trợ thêm label và textfield nhanh vào form
    private void addField(JPanel form, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        form.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        form.add(field, gbc);
    }

    // =====================================================
    // XỬ LÝ DỮ LIỆU
    // =====================================================

    private void loadTableData() {
        model.setRowCount(0); // Xóa sạch bảng cũ
        List<Supplier> list = controller.getAllSuppliers();
        for (Supplier s : list) {
            model.addRow(new Object[]{
                    s.getSupplierId(),
                    s.getSupplierName(),
                    s.getAddress(),
                    s.getPhone()
            });
        }
    }

    private Supplier getFormData() {
        Supplier s = new Supplier();
        s.setSupplierName(txtSupplierName.getText().trim());
        s.setAddress(txtAddress.getText().trim());
        s.setPhone(txtPhone.getText().trim());
        return s;
    }

    private void addSupplier() {
        Supplier s = getFormData();
        String msg = controller.addSupplier(s);

        if (msg.equals("SUCCESS")) {
            MessageBox.info(null, "Thêm nhà cung cấp thành công!");
            loadTableData();
            clearForm();
        } else {
            MessageBox.error(null, msg);
        }
    }

    private void updateSupplier() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Vui lòng chọn một nhà cung cấp từ bảng!");
            return;
        }

        Supplier s = getFormData();
        s.setSupplierId(selectedId);

        String msg = controller.updateSupplier(s);
        if (msg.equals("SUCCESS")) {
            MessageBox.info(null, "Cập nhật thành công!");
            loadTableData();
        } else {
            MessageBox.error(null, msg);
        }
    }

    private void deleteSupplier() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Vui lòng chọn nhà cung cấp cần xóa!");
            return;
        }

        if (!MessageBox.confirm(null, "Bạn có chắc chắn muốn xóa nhà cung cấp này?")) return;

        String msg = controller.deleteSupplier(selectedId);
        if (msg.equals("SUCCESS")) {
            MessageBox.info(null, "Đã xóa thành công!");
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
        txtSupplierName.setText(model.getValueAt(row, 1).toString());
        txtAddress.setText(model.getValueAt(row, 2).toString());
        txtPhone.setText(model.getValueAt(row, 3).toString());
    }

    private void clearForm() {
        selectedId = -1;
        txtSupplierName.setText("");
        txtAddress.setText("");
        txtPhone.setText("");
        table.clearSelection();
    }

    private void setLayout(BorderLayout borderLayout) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void add(JPanel form, String NORTH) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
