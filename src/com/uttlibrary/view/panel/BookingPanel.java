/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.view.panel;

import com.uttlibrary.controller.BookingController;
import com.uttlibrary.model.Booking;
import com.uttlibrary.util.MessageBox;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class BookingPanel extends JPanel {

    private BookingController controller = new BookingController();

    private JTable table;
    private DefaultTableModel model;

    private JTextField txtBookingDate;
    private JComboBox<String> cboReader, cboBook, cboStatus;

    private int selectedId = -1;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public BookingPanel() {
        setLayout(new BorderLayout());
        initUI();
        loadTableData();
    }

    private void initUI() {
        // ============================
        // FORM NHẬP LIỆU
        // ============================
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Thông tin đặt sách"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        txtBookingDate = new JTextField();
        cboReader = new JComboBox<>(new String[]{"1", "2", "3"});
        cboBook = new JComboBox<>(new String[]{"1", "2", "3"});
        cboStatus = new JComboBox<>(new String[]{"PENDING", "APPROVED", "CANCELED"});

        int row = 0;
        addField(form, gbc, row++, "Độc giả ID:", cboReader);
        addField(form, gbc, row++, "Sách ID:", cboBook);
        addField(form, gbc, row++, "Ngày đặt (yyyy-MM-dd):", txtBookingDate);
        addField(form, gbc, row++, "Trạng thái:", cboStatus);

        add(form, BorderLayout.NORTH);

        // ============================
        // TABLE
        // ============================
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                "ID", "Độc giả", "Sách", "Ngày đặt", "Trạng thái"
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

        btnAdd.addActionListener(e -> addBooking());
        btnUpdate.addActionListener(e -> updateBooking());
        btnDelete.addActionListener(e -> deleteBooking());
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

    // =========================================================
    // LOAD TABLE DATA
    // =========================================================
    private void loadTableData() {
        model.setRowCount(0);

        List<Booking> list = controller.getAllBookings();
        for (Booking b : list) {
            model.addRow(new Object[]{
                    b.getBookingId(),
                    b.getReaderId(),
                    b.getBookId(),
                    b.getBookingDate() != null ? sdf.format(b.getBookingDate()) : "",
                    b.getStatus()
            });
        }
    }

    // =========================================================
    // LẤY DỮ LIỆU TỪ FORM → MODEL BOOKING
    // =========================================================
    private Booking getFormData() {
        Booking b = new Booking();
        b.setReaderId(Integer.valueOf((String) cboReader.getSelectedItem()));
        b.setBookId(Integer.valueOf((String) cboBook.getSelectedItem()));
        try {
            Date date = sdf.parse(txtBookingDate.getText());
            b.setBookingDate(date);
        } catch (Exception e) {
            b.setBookingDate(null);
        }
        b.setStatus((String) cboStatus.getSelectedItem());
        return b;
    }

    // =========================================================
    // NÚT THÊM
    // =========================================================
    private void addBooking() {
        Booking b = getFormData();
        String msg = controller.addBooking(b);

        if (msg.equals("SUCCESS")) {
            MessageBox.info(null, "Thêm booking thành công!");
            loadTableData();
            clearForm();
        } else {
            MessageBox.error(null, msg);
        }
    }

    // =========================================================
    // NÚT SỬA
    // =========================================================
    private void updateBooking() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Vui lòng chọn booking cần sửa!");
            return;
        }

        Booking b = getFormData();
        b.setBookingId(selectedId);

        String msg = controller.updateBooking(b);

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
    private void deleteBooking() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Chọn booking để xóa!");
            return;
        }

        if (!MessageBox.confirm(null, "Xóa booking này?")) {
            return;
        }

        String msg = controller.deleteBooking(selectedId);

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
        cboReader.setSelectedItem(model.getValueAt(row, 1).toString());
        cboBook.setSelectedItem(model.getValueAt(row, 2).toString());
        txtBookingDate.setText(model.getValueAt(row, 3).toString());
        cboStatus.setSelectedItem(model.getValueAt(row, 4).toString());
    }

    // =========================================================
    // CLEAR FORM
    // =========================================================
    private void clearForm() {
        selectedId = -1;
        txtBookingDate.setText("");
        cboReader.setSelectedIndex(0);
        cboBook.setSelectedIndex(0);
        cboStatus.setSelectedIndex(0);
        table.clearSelection();
    }
}