/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.view.panel;

/**
 *
 * @author ADMIN
 */

import com.uttlibrary.controller.ReaderController;
import com.uttlibrary.model.Reader;
import com.uttlibrary.util.MessageBox;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReaderPanel extends JPanel{
    // CONTROLLER
    // ==============================
    private ReaderController controller = new ReaderController();

    // ==============================
    // KHAI BÁO UI
    // ==============================
    private JTable table;
    private DefaultTableModel model;

    private JTextField txtFullName, txtBirthday, txtPhone;
    private JRadioButton rdMale, rdFemale;
    private JComboBox<String> cboReaderType;

    private int selectedId = -1;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ReaderPanel() {
        setLayout(new BorderLayout());
        initUI();
        loadTableData();
    }

    // =====================================================
    // KHỞI TẠO GIAO DIỆN
    // =====================================================
    private void initUI() {

        // ============================
        // FORM NHẬP DỮ LIỆU
        // ============================
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Thông tin độc giả"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        txtFullName = new JTextField();
        txtBirthday = new JTextField(); // yyyy-MM-dd
        txtPhone = new JTextField();

        rdMale = new JRadioButton("Nam");
        rdFemale = new JRadioButton("Nữ");

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rdMale);
        genderGroup.add(rdFemale);
        rdMale.setSelected(true);

        cboReaderType = new JComboBox<>(
                new String[]{"Sinh viên", "Giảng viên", "Khách"}
        );

        int row = 0;

        addField(form, gbc, row++, "Họ tên:", txtFullName);
        addField(form, gbc, row++, "Ngày sinh (yyyy-MM-dd):", txtBirthday);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(rdMale);
        genderPanel.add(rdFemale);
        addField(form, gbc, row++, "Giới tính:", genderPanel);

        addField(form, gbc, row++, "Số điện thoại:", txtPhone);
        addField(form, gbc, row++, "Loại độc giả:", cboReaderType);

        add(form, BorderLayout.NORTH);

        // ============================
        // TABLE
        // ============================
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                "ID", "Họ tên", "Ngày sinh", "Giới tính", "SĐT", "Loại"
        });

        table = new JTable(model);
        table.setRowHeight(25);
        table.getSelectionModel().addListSelectionListener(e -> loadSelectedRow());

        add(new JScrollPane(table), BorderLayout.CENTER);

        // ============================
        // BUTTON
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
        gbc.weightx = 0.3;
        form.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        form.add(field, gbc);
    }

    // =====================================================
    // LOAD DỮ LIỆU TABLE
    // =====================================================
    private void loadTableData() {
        model.setRowCount(0);

        List<Reader> list = controller.getAllReaders();
        for (Reader r : list) {
            model.addRow(new Object[]{
                    r.getReaderId(),
                    r.getFullName(),
                    sdf.format(r.getBirthday()),
                    r.getGender() != null && r.getGender() ? "Nam" : "Nữ",
                    r.getPhone(),
                    r.getReaderType()
            });
        }
    }

    // =====================================================
    // LẤY DỮ LIỆU TỪ FORM → MODEL
    // =====================================================
    private Reader getFormData() throws Exception {
        Reader r = new Reader();

        r.setFullName(txtFullName.getText());
        r.setBirthday(sdf.parse(txtBirthday.getText()));
        r.setGender(rdMale.isSelected());
        r.setPhone(txtPhone.getText());
        r.setReaderType((String) cboReaderType.getSelectedItem());

        return r;
    }

    // =====================================================
    // THÊM ĐỘC GIẢ
    // =====================================================
    private void addReader() {
        try {
            Reader r = getFormData();
            String msg = controller.addReader(r);

            if (msg.equals("SUCCESS")) {
                MessageBox.info(null, "Thêm độc giả thành công!");
                loadTableData();
                clearForm();
            } else {
                MessageBox.error(null, msg);
            }
        } catch (Exception e) {
            MessageBox.error(null, "Ngày sinh phải đúng định dạng yyyy-MM-dd");
        }
    }

    // =====================================================
    // SỬA ĐỘC GIẢ
    // =====================================================
    private void updateReader() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Vui lòng chọn độc giả!");
            return;
        }

        try {
            Reader r = getFormData();
            r.setReaderId(selectedId);

            String msg = controller.updateReader(r);
            if (msg.equals("SUCCESS")) {
                MessageBox.info(null, "Cập nhật thành công!");
                loadTableData();
            } else {
                MessageBox.error(null, msg);
            }
        } catch (Exception e) {
            MessageBox.error(null, "Ngày sinh không hợp lệ!");
        }
    }

    // =====================================================
    // XÓA ĐỘC GIẢ
    // =====================================================
    private void deleteReader() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Chọn độc giả để xóa!");
            return;
        }

        if (!MessageBox.confirm(null, "Bạn chắc chắn muốn xóa?")) return;

        String msg = controller.deleteReader(selectedId);
        if (msg.equals("SUCCESS")) {
            MessageBox.info(null, "Đã xóa!");
            loadTableData();
            clearForm();
        } else {
            MessageBox.error(null, msg);
        }
    }

    // =====================================================
    // LOAD TABLE → FORM
    // =====================================================
    private void loadSelectedRow() {
        int row = table.getSelectedRow();
        if (row < 0) return;

        selectedId = (int) model.getValueAt(row, 0);

        txtFullName.setText(model.getValueAt(row, 1).toString());
        txtBirthday.setText(model.getValueAt(row, 2).toString());

        String gender = model.getValueAt(row, 3).toString();
        if (gender.equals("Nam")) rdMale.setSelected(true);
        else rdFemale.setSelected(true);

        txtPhone.setText(model.getValueAt(row, 4).toString());
        cboReaderType.setSelectedItem(model.getValueAt(row, 5).toString());
    }

    // =====================================================
    // CLEAR FORM
    // =====================================================
    private void clearForm() {
        selectedId = -1;
        txtFullName.setText("");
        txtBirthday.setText("");
        txtPhone.setText("");
        rdMale.setSelected(true);
        cboReaderType.setSelectedIndex(0);
        table.clearSelection();
    }
}
