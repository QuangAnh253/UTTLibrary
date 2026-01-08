/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.view.panel;

import com.uttlibrary.component.CustomButton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LoanDetailPanel extends JPanel {

    // ========== COMPONENTS ==========
    public JTextField txtLoanTicketId;
    public JTextField txtBookId;
    public JTextField txtQuantity;

    public JButton btnAdd;
    public JButton btnUpdate;
    public JButton btnDelete;
    public JButton btnClear;

    public JTable table;
    public DefaultTableModel tableModel;
    public JTextField txtSearch;
    public JButton btnSearch;

    // ========== CONSTRUCTOR ==========
    public LoanDetailPanel() {
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
        formPanel.setBorder(BorderFactory.createTitledBorder("Chi tiết Mượn Sách"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Loan Ticket ID
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("ID Phiếu Mượn:"), gbc);
        gbc.gridx = 1;
        txtLoanTicketId = new JTextField(20);
        formPanel.add(txtLoanTicketId, gbc);

        // Book ID
        gbc.gridx = 2; gbc.gridy = 0;
        formPanel.add(new JLabel("ID Sách:"), gbc);
        gbc.gridx = 3;
        txtBookId = new JTextField(20);
        formPanel.add(txtBookId, gbc);

        // Quantity
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Số Lượng:"), gbc);
        gbc.gridx = 1;
        txtQuantity = new JTextField(20);
        formPanel.add(txtQuantity, gbc);

        // BUTTON PANEL
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        btnPanel.setOpaque(false);

        btnAdd = new CustomButton("Thêm");
        btnUpdate = new CustomButton("Sửa");
        btnDelete = new CustomButton("Xóa");
        btnClear = new CustomButton("Làm mới");

        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnClear);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    // ========== TABLE PANEL ==========
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Danh sách Chi tiết Mượn"));

        // SEARCH BAR
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.WHITE);
        txtSearch = new JTextField(25);
        btnSearch = new CustomButton("Tìm theo Phiếu");
        searchPanel.add(new JLabel("ID Phiếu Mượn:"));
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        // TABLE
        String[] columns = {"ID", "ID Phiếu Mượn", "ID Sách", "Tên Sách", "Số Lượng"};
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
        txtLoanTicketId.setText("");
        txtBookId.setText("");
        txtQuantity.setText("");
        table.clearSelection();
    }

    public int getSelectedId() {
        int row = table.getSelectedRow();
        if (row == -1) return -1;
        return (int) tableModel.getValueAt(row, 0);
    }
}