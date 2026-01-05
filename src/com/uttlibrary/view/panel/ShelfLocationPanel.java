package com.uttlibrary.view.panel;

import com.uttlibrary.controller.ShelfLocationController;
import com.uttlibrary.model.ShelfLocation;
import com.uttlibrary.util.MessageBox;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ShelfLocationPanel extends JPanel {
    private final ShelfLocationController controller = new ShelfLocationController();
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtShelfName, txtDescription;
    private int selectedId = -1;

    public ShelfLocationPanel() {
        setLayout(new BorderLayout());
        initUI();
        loadTableData();
    }

    private void initUI() {
        // 1. FORM NHẬP DỮ LIỆU
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Thông tin Vị trí kệ"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtShelfName = new JTextField(20);
        txtDescription = new JTextField(20);

        int row = 0;
        addField(form, gbc, row++, "Tên kệ:", txtShelfName);
        addField(form, gbc, row++, "Mô tả:", txtDescription);

        add(form, BorderLayout.NORTH);

        // 2. BẢNG HIỂN THỊ
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
            "ID Kệ", "Tên kệ", "Mô tả"
        });

        table = new JTable(model);
        table.setRowHeight(25);
        table.getSelectionModel().addListSelectionListener(e -> loadSelectedRow());

        add(new JScrollPane(table), BorderLayout.CENTER);

        // 3. NÚT CHỨC NĂNG
        JPanel buttons = new JPanel();
        JButton btnAdd = new JButton("Thêm mới");
        JButton btnUpdate = new JButton("Cập nhật");
        JButton btnDelete = new JButton("Xóa kệ");
        JButton btnClear = new JButton("Làm mới");

        btnAdd.addActionListener(e -> addShelf());
        btnUpdate.addActionListener(e -> updateShelf());
        btnDelete.addActionListener(e -> deleteShelf());
        btnClear.addActionListener(e -> clearForm());

        buttons.add(btnAdd);
        buttons.add(btnUpdate);
        buttons.add(btnDelete);
        buttons.add(btnClear);

        add(buttons, BorderLayout.SOUTH);
    }

    private void addField(JPanel form, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.3;
        form.add(new JLabel(label), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        form.add(field, gbc);
    }

    private void loadTableData() {
        model.setRowCount(0);
        List<ShelfLocation> list = controller.getAllShelves();
        for (ShelfLocation s : list) {
            model.addRow(new Object[]{
                s.getShelfId(),
                s.getShelfName(),
                s.getDescription()
            });
        }
    }

    private ShelfLocation getFormData() {
        ShelfLocation s = new ShelfLocation();
        s.setShelfName(txtShelfName.getText().trim());
        s.setDescription(txtDescription.getText().trim());
        return s;
    }

    private void addShelf() {
        ShelfLocation s = getFormData();
        String msg = controller.addShelf(s);
        if (msg.equals("SUCCESS")) {
            MessageBox.info(null, "Thêm kệ mới thành công!");
            loadTableData();
            clearForm();
        } else {
            MessageBox.error(null, msg);
        }
    }

    private void updateShelf() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Vui lòng chọn một kệ để sửa!");
            return;
        }
        ShelfLocation s = getFormData();
        s.setShelfId(selectedId);
        String msg = controller.updateShelf(s);
        if (msg.equals("SUCCESS")) {
            MessageBox.info(null, "Cập nhật thành công!");
            loadTableData();
        } else {
            MessageBox.error(null, msg);
        }
    }

    private void deleteShelf() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Chọn kệ cần xóa!");
            return;
        }
        if (!MessageBox.confirm(null, "Bạn có chắc chắn muốn xóa kệ này không?")) return;
        
        String msg = controller.deleteShelf(selectedId);
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
        txtShelfName.setText(model.getValueAt(row, 1).toString());
        txtDescription.setText(model.getValueAt(row, 2) != null ? model.getValueAt(row, 2).toString() : "");
    }

    private void clearForm() {
        selectedId = -1;
        txtShelfName.setText("");
        txtDescription.setText("");
        table.clearSelection();
    }
}