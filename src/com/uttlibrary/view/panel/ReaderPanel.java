/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.view.panel;

import com.uttlibrary.controller.ReaderController;
import com.uttlibrary.model.Reader;
import com.uttlibrary.util.MessageBox;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ReaderPanel extends JPanel {

    private ReaderController controller = new ReaderController();

    private JTable table;
    private DefaultTableModel model;

    private JTextField txtFullName, txtBirthday, txtPhone;
    private JComboBox<String> cboGender, cboReaderType;

    private int selectedId = -1;

    public ReaderPanel() {
        setLayout(new BorderLayout());
        initUI();
        loadTableData();
    }

    private void initUI() {

        // ============================
        // FORM NHẬP LIỆU
        // ============================
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Thông tin đọc giả"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        txtFullName = new JTextField();
        txtBirthday = new JTextField();
        txtPhone = new JTextField();

        cboGender = new JComboBox<>(new String[]{"Nam", "Nữ"});
        cboReaderType = new JComboBox<>(new String[]{"Sinh viên", "Giảng viên", "Khác"});

        int row = 0;
        addField(form, gbc, row++, "Họ và tên:", txtFullName);
        addField(form, gbc, row++, "Ngày sinh (yyyy-mm-dd):", txtBirthday);
        addField(form, gbc, row++, "Giới tính:", cboGender);
        addField(form, gbc, row++, "SĐT:", txtPhone);
        addField(form, gbc, row++, "Loại đọc giả:", cboReaderType);

        add(form, BorderLayout.NORTH);

        // ============================
        // TABLE
        // ============================
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                "ID", "Họ và tên", "Ngày sinh", "Giới tính", "SĐT", "Loại đọc giả"
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

        btnAdd.addActionListener(e -> addReader());
        btnUpdate.addActionListener(e -> updateReader());
        btnDelete.addActionListener(e -> deleteReader());
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

        List<Reader> list = controller.getAllReaders();
        for (Reader r : list) {
            model.addRow(new Object[]{
                    r.getReaderId(),
                    r.getFullName(),
                    r.getBirthday(),
                    r.getGender(),
                    r.getPhone(),
                    r.getReaderType()
            });
        }
    }

    // =========================================================
    // LẤY DỮ LIỆU TỪ FORM → MODEL READER
    // =========================================================
    private Reader getFormData() {
        Reader r = new Reader();
        r.setFullName(txtFullName.getText());
        r.setBirthday(txtBirthday.getText());
        r.setGender((String) cboGender.getSelectedItem());
        r.setPhone(txtPhone.getText());
        r.setReaderType((String) cboReaderType.getSelectedItem());
        return r;
    }

    // =========================================================
    // NÚT THÊM
    // =========================================================
    private void addReader() {
        Reader r = getFormData();
        String msg = controller.addReader(r);

        if (msg.equals("SUCCESS")) {
            MessageBox.info(null, "Thêm đọc giả thành công!");
            loadTableData();
            clearForm();
        } else {
            MessageBox.error(null, msg);
        }
    }

    // =========================================================
    // NÚT SỬA
    // =========================================================
    private void updateReader() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Vui lòng chọn đọc giả cần sửa!");
            return;
        }

        Reader r = getFormData();
        r.setReaderId(selectedId);

        String msg = controller.updateReader(r);

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
    private void deleteReader() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Chọn đọc giả để xóa!");
            return;
        }

        if (!MessageBox.confirm(null, "Xóa đọc giả này?")) {
            return;
        }

        String msg = controller.deleteReader(selectedId);

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

        txtFullName.setText(model.getValueAt(row, 1).toString());
        txtBirthday.setText(model.getValueAt(row, 2).toString());
        cboGender.setSelectedItem(model.getValueAt(row, 3).toString());
        txtPhone.setText(model.getValueAt(row, 4).toString());
        cboReaderType.setSelectedItem(model.getValueAt(row, 5).toString());
    }

    // =========================================================
    // CLEAR FORM
    // =========================================================
    private void clearForm() {
        selectedId = -1;
        txtFullName.setText("");
        txtBirthday.setText("");
        txtPhone.setText("");
        cboGender.setSelectedIndex(0);
        cboReaderType.setSelectedIndex(0);

        table.clearSelection();
    }
}
