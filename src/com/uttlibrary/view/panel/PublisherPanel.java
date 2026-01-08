/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.view.panel;

import com.uttlibrary.controller.PublisherController;
import com.uttlibrary.model.Publisher;
import com.uttlibrary.util.MessageBox;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class PublisherPanel extends JPanel {

    private final PublisherController controller = new PublisherController();

    private JTable table;
    private DefaultTableModel model;

    private JTextField txtName, txtAddress, txtEmail;
    private int selectedId = -1;

    public PublisherPanel() {
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        add(createForm(), BorderLayout.NORTH);
        add(createTableScroll(), BorderLayout.CENTER);
        add(createButtons(), BorderLayout.SOUTH);

        loadTableData();
    }

    // =========================================================
    // FORM NHẬP LIỆU
    // =========================================================
    private JPanel createForm() {
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Thông tin nhà xuất bản"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,8,8,8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        txtName = new JTextField();
        txtAddress = new JTextField();
        txtEmail = new JTextField();

        int row = 0;
        addField(form, gbc, row++, "Tên NXB:", txtName);
        addField(form, gbc, row++, "Địa chỉ:", txtAddress);
        addField(form, gbc, row++, "Email:", txtEmail);

        return form;
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
    // TABLE
    // =========================================================
    private JScrollPane createTableScroll() {
        model = new DefaultTableModel(new String[]{"ID", "Tên NXB", "Địa chỉ", "Email"}, 0);
        table = new JTable(model);
        table.setRowHeight(25);
        table.getSelectionModel().addListSelectionListener(e -> loadSelectedRow());
        return new JScrollPane(table);
    }

    private void loadTableData() {
        model.setRowCount(0);
        List<Publisher> list = controller.getAllPublishers();
        for (Publisher p : list) {
            model.addRow(new Object[]{
                    p.getPublisherId(),
                    p.getPublisherName(),
                    p.getAddress(),
                    p.getEmail()
            });
        }
    }

    // =========================================================
    // BUTTONS
    // =========================================================
    private JPanel createButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));

        JButton btnAdd = new JButton("Thêm");
        JButton btnUpdate = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        JButton btnClear = new JButton("Làm mới");

        btnAdd.addActionListener(e -> addPublisher());
        btnUpdate.addActionListener(e -> updatePublisher());
        btnDelete.addActionListener(e -> deletePublisher());
        btnClear.addActionListener(e -> clearForm());

        panel.add(btnAdd);
        panel.add(btnUpdate);
        panel.add(btnDelete);
        panel.add(btnClear);

        return panel;
    }

    // =========================================================
    // CRUD
    // =========================================================
    private void addPublisher() {
        Publisher p = getFormData();
        String msg = controller.addPublisher(p);
        if ("SUCCESS".equals(msg)) {
            MessageBox.info(null, "Thêm thành công!");
            loadTableData();
            clearForm();
        } else {
            MessageBox.error(null, msg);
        }
    }

    private void updatePublisher() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Chọn nhà xuất bản cần sửa!");
            return;
        }
        Publisher p = getFormData();
        p.setPublisherId(selectedId);

        String msg = controller.updatePublisher(p);
        if ("SUCCESS".equals(msg)) {
            MessageBox.info(null, "Cập nhật thành công!");
            loadTableData();
        } else {
            MessageBox.error(null, msg);
        }
    }

    private void deletePublisher() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Chọn nhà xuất bản cần xóa!");
            return;
        }
        if (!MessageBox.confirm(null, "Xóa nhà xuất bản này?")) return;

        String msg = controller.deletePublisher(selectedId);
        if ("SUCCESS".equals(msg)) {
            MessageBox.info(null, "Đã xóa!");
            loadTableData();
            clearForm();
        } else {
            MessageBox.error(null, msg);
        }
    }

    // =========================================================
    // LẤY DỮ LIỆU FORM
    // =========================================================
    private Publisher getFormData() {
        Publisher p = new Publisher();
        p.setPublisherName(txtName.getText());
        p.setAddress(txtAddress.getText());
        p.setEmail(txtEmail.getText());
        return p;
    }

    // =========================================================
    // LOAD ROW CHỌN VÀ CLEAR FORM
    // =========================================================
    private void loadSelectedRow() {
        int row = table.getSelectedRow();
        if (row < 0) return;

        selectedId = (int) model.getValueAt(row, 0);
        txtName.setText(model.getValueAt(row, 1).toString());
        txtAddress.setText(model.getValueAt(row, 2).toString());
        txtEmail.setText(model.getValueAt(row, 3).toString());
    }

    private void clearForm() {
        selectedId = -1;
        txtName.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        table.clearSelection();
    }
    
     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test PublisherPanel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 500);
            frame.setLocationRelativeTo(null);

            PublisherPanel panel = new PublisherPanel();
            frame.setContentPane(panel);

            frame.setVisible(true);
        });
    }
}