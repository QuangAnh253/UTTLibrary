/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.view.panel;

import com.uttlibrary.controller.RegulationController;
import com.uttlibrary.model.Regulation;
import com.uttlibrary.util.MessageBox;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ADMIN
 */
public class RegulationPanel extends JPanel{
    private final RegulationController controller = new RegulationController();
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtName, txtValue;
    private JTextArea txtDescription;
    private int selectedId = -1;

    public RegulationPanel() {
        setLayout(new BorderLayout());
        initUI();
        loadTableData();
    }

    private void initUI() {
        // 1. FORM NHẬP DỮ LIỆU (Phía trên)
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Thông tin Quy định"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtName = new JTextField(20);
        txtValue = new JTextField(20);
        txtDescription = new JTextArea(3, 20);
        txtDescription.setLineWrap(true);
        JScrollPane scrollDesc = new JScrollPane(txtDescription);

        int row = 0;
        addField(form, gbc, row++, "Tên quy định:", txtName);
        addField(form, gbc, row++, "Giá trị (Số):", txtValue);
        addField(form, gbc, row++, "Mô tả:", scrollDesc);

        add(form, BorderLayout.NORTH);

        // 2. BẢNG HIỂN THỊ (Ở giữa)
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(new String[]{"ID", "Tên quy định", "Giá trị", "Mô tả"});

        table = new JTable(model);
        table.setRowHeight(25);
        table.getSelectionModel().addListSelectionListener(e -> loadSelectedRow());

        add(new JScrollPane(table), BorderLayout.CENTER);

        // 3. NÚT CHỨC NĂNG (Phía dưới)
        JPanel buttons = new JPanel();
        JButton btnAdd = new JButton("Thêm mới");
        JButton btnUpdate = new JButton("Cập nhật");
        JButton btnDelete = new JButton("Xóa");
        JButton btnClear = new JButton("Làm mới");

        btnAdd.addActionListener(e -> addRegulation());
        btnUpdate.addActionListener(e -> updateRegulation());
        btnDelete.addActionListener(e -> deleteRegulation());
        btnClear.addActionListener(e -> clearForm());

        buttons.add(btnAdd);
        buttons.add(btnUpdate);
        buttons.add(btnDelete);
        buttons.add(btnClear);

        add(buttons, BorderLayout.SOUTH);
    }

    private void addField(JPanel form, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.2;
        form.add(new JLabel(label), gbc);
        gbc.gridx = 1; gbc.weightx = 0.8;
        form.add(field, gbc);
    }

    private void loadTableData() {
        model.setRowCount(0);
        List<Regulation> list = controller.getAllRegulations();
        for (Regulation r : list) {
            model.addRow(new Object[]{
                r.getRegulationId(),
                r.getRegulationName(),
                r.getValue(),
                r.getDescription()
            });
        }
    }

    private Regulation getFormData() throws Exception {
        String name = txtName.getText().trim();
        String valStr = txtValue.getText().trim();
        String desc = txtDescription.getText().trim();

        if (name.isEmpty() || valStr.isEmpty()) {
            throw new Exception("Vui lòng nhập tên và giá trị quy định!");
        }

        Regulation r = new Regulation();
        r.setRegulationName(name);
        r.setValue(Integer.parseInt(valStr));
        r.setDescription(desc);
        return r;
    }

    private void addRegulation() {
        try {
            Regulation r = getFormData();
            String msg = controller.addRegulation(r);
            if ("SUCCESS".equals(msg)) {
                MessageBox.info(null, "Thêm quy định thành công!");
                loadTableData();
                clearForm();
            } else {
                MessageBox.error(null, msg);
            }
        } catch (NumberFormatException e) {
            MessageBox.error(null, "Giá trị quy định phải là số nguyên!");
        } catch (Exception e) {
            MessageBox.error(null, e.getMessage());
        }
    }

    private void updateRegulation() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Chọn một quy định để sửa!");
            return;
        }
        try {
            Regulation r = getFormData();
            r.setRegulationId(selectedId);
            String msg = controller.updateRegulation(r);
            if ("SUCCESS".equals(msg)) {
                MessageBox.info(null, "Cập nhật thành công!");
                loadTableData();
            } else {
                MessageBox.error(null, msg);
            }
        } catch (Exception e) {
            MessageBox.error(null, "Dữ liệu không hợp lệ!");
        }
    }

    private void deleteRegulation() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Chọn quy định cần xóa!");
            return;
        }
        if (!MessageBox.confirm(null, "Bạn có chắc muốn xóa quy định này?")) return;

        String msg = controller.deleteRegulation(selectedId);
        if ("SUCCESS".equals(msg)) {
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
        txtName.setText(model.getValueAt(row, 1).toString());
        txtValue.setText(model.getValueAt(row, 2).toString());
        txtDescription.setText(model.getValueAt(row, 3) != null ? model.getValueAt(row, 3).toString() : "");
    }

    private void clearForm() {
        selectedId = -1;
        txtName.setText("");
        txtValue.setText("");
        txtDescription.setText("");
        table.clearSelection();
    }
}
