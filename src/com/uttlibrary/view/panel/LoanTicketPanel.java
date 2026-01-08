/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.view.panel;

import com.uttlibrary.component.CustomButton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LoanTicketPanel extends JPanel {

    // ========== COMPONENTS ==========
    public JTextField txtReaderId;
    public JTextField txtLoanDate;
    public JTextField txtReturnDate;

    public JButton btnAdd;
    public JButton btnUpdate;
    public JButton btnDelete;
    public JButton btnClear;
    public JButton btnViewDetail;

    public JTable table;
    public DefaultTableModel tableModel;
    public JTextField txtSearch;
    public JButton btnSearch;

    // ========== CONSTRUCTOR ==========
    public LoanTicketPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(236, 240, 241));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(createTopPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
    }

    // ========== TOP PANEL (Form + Buttons) ==========
    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setOpaque(false);

        // FORM
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin Phiếu Mượn"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Reader ID
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("ID Độc Giả:"), gbc);
        gbc.gridx = 1;
        txtReaderId = new JTextField(20);
        formPanel.add(txtReaderId, gbc);

        // Loan Date
        gbc.gridx = 2; gbc.gridy = 0;
        formPanel.add(new JLabel("Ngày Mượn (dd/MM/yyyy):"), gbc);
        gbc.gridx = 3;
        txtLoanDate = new JTextField(20);
        formPanel.add(txtLoanDate, gbc);

        // Return Date
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Ngày Trả (dd/MM/yyyy):"), gbc);
        gbc.gridx = 1;
        txtReturnDate = new JTextField(20);
        formPanel.add(txtReturnDate, gbc);

        // BUTTON PANEL
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        btnPanel.setOpaque(false);

        btnAdd = new CustomButton("Thêm");
        btnUpdate = new CustomButton("Sửa");
        btnDelete = new CustomButton("Xóa");
        btnClear = new CustomButton("Làm mới");
        btnViewDetail = new CustomButton("Xem Chi tiết");

        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnClear);
        btnPanel.add(btnViewDetail);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    // ========== TABLE PANEL ==========
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Danh sách Phiếu Mượn"));

        // SEARCH BAR
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.WHITE);
        txtSearch = new JTextField(25);
        btnSearch = new CustomButton("Tìm kiếm");
        searchPanel.add(new JLabel("Tìm theo ID Độc Giả:"));
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        // TABLE
        String[] columns = {"ID", "ID Độc Giả", "Tên Độc Giả", "Ngày Mượn", "Ngày Trả Dự Kiến", "Trạng Thái"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // ========== HELPER METHODS ==========
    public void clearForm() {
        txtReaderId.setText("");
        txtLoanDate.setText("");
        txtReturnDate.setText("");
        table.clearSelection();
    }

    public int getSelectedId() {
        int row = table.getSelectedRow();
        if (row == -1) return -1;
        return (int) tableModel.getValueAt(row, 0);
    }
}