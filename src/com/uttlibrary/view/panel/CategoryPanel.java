/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.view.panel;
import com.uttlibrary.controller.CategoryController; 
import com.uttlibrary.model.Category;
import com.uttlibrary.util.MessageBox;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ADMIN
 */
public class CategoryPanel extends JPanel{
    private final CategoryController controller = new CategoryController();
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtCategoryName;
    private int selectedId = -1;

    public CategoryPanel() {
        setLayout(new BorderLayout());
        initUI();
        loadTableData();
    }

    private void initUI() {
        // 1. FORM NHẬP DỮ LIỆU
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Thông tin Danh mục"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtCategoryName = new JTextField(20);

        int row = 0;
        // Danh mục thường chỉ cần nhập tên, ID thường tự tăng (Auto Increment)
        addField(form, gbc, row++, "Tên Danh mục:", txtCategoryName);

        add(form, BorderLayout.NORTH);

        // 2. BẢNG HIỂN THỊ
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
            "ID Danh mục", "Tên Danh mục"
        });

        table = new JTable(model);
        table.setRowHeight(25);
        table.getSelectionModel().addListSelectionListener(e -> loadSelectedRow());

        add(new JScrollPane(table), BorderLayout.CENTER);

        // 3. NÚT CHỨC NĂNG
        JPanel buttons = new JPanel();
        JButton btnAdd = new JButton("Thêm mới");
        JButton btnUpdate = new JButton("Cập nhật");
        JButton btnDelete = new JButton("Xóa");
        JButton btnClear = new JButton("Làm mới");

        btnAdd.addActionListener(e -> addCategory());
        btnUpdate.addActionListener(e -> updateCategory());
        btnDelete.addActionListener(e -> deleteCategory());
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
        try {
            List<Category> list = controller.getAllCategories();
            for (Category cat : list) {
                model.addRow(new Object[]{
                    cat.getId(),
                    cat.getCategoryName()
                });
            }
        } catch (Exception e) {
            // Xử lý nếu controller chưa có dữ liệu hoặc lỗi kết nối
        }
    }

    private Category getFormData() {
        String name = txtCategoryName.getText().trim();
        if (name.isEmpty()) {
            return null;
        }
        Category cat = new Category();
        cat.setCategoryName(name);
        return cat;
    }

    private void addCategory() {
        Category cat = getFormData();
        if (cat == null) {
            MessageBox.warning(null, "Vui lòng nhập tên danh mục!");
            return;
        }
        
        String msg = controller.addCategory(cat);
        if ("SUCCESS".equals(msg)) {
            MessageBox.info(null, "Thêm danh mục thành công!");
            loadTableData();
            clearForm();
        } else {
            MessageBox.error(null, msg);
        }
    }

    private void updateCategory() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Chọn một danh mục để sửa!");
            return;
        }
        
        Category cat = getFormData();
        if (cat == null) {
            MessageBox.warning(null, "Tên danh mục không được để trống!");
            return;
        }
        
        cat.setId(selectedId);
        String msg = controller.updateCategory(cat);
        if ("SUCCESS".equals(msg)) {
            MessageBox.info(null, "Cập nhật thành công!");
            loadTableData();
        } else {
            MessageBox.error(null, msg);
        }
    }

    private void deleteCategory() {
        if (selectedId <= 0) {
            MessageBox.warning(null, "Chọn danh mục cần xóa!");
            return;
        }
        
        if (!MessageBox.confirm(null, "Bạn có chắc chắn muốn xóa danh mục này?")) return;
        
        String msg = controller.deleteCategory(selectedId);
        if ("SUCCESS".equals(msg)) {
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
        txtCategoryName.setText(model.getValueAt(row, 1).toString());
    }

    private void clearForm() {
        selectedId = -1;
        txtCategoryName.setText("");
        table.clearSelection();
    }
}

