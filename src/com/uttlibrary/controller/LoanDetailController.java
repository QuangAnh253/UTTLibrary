/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.controller;

import com.uttlibrary.dao.LoanDetailDAO;
import com.uttlibrary.model.LoanDetail;
import com.uttlibrary.util.MessageBox;
import com.uttlibrary.util.Validator;
import com.uttlibrary.view.panel.LoanDetailPanel;

import java.util.List;

public class LoanDetailController extends BaseController<LoanDetail> {

    private LoanDetailPanel view;
    private LoanDetailDAO dao;

    public LoanDetailController(LoanDetailPanel view) {
        this.view = view;
        this.dao = new LoanDetailDAO();

        loadTable();
        addEvents();
    }

    @Override
    public void loadTable() {
        view.tableModel.setRowCount(0);
        List<Object[]> list = dao.findAllWithBookInfo();

        for (Object[] row : list) {
            view.tableModel.addRow(row);
        }
    }

    private void addEvents() {
        view.btnAdd.addActionListener(e -> add());
        view.btnUpdate.addActionListener(e -> update());
        view.btnDelete.addActionListener(e -> delete());
        view.btnClear.addActionListener(e -> view.clearForm());
        view.btnSearch.addActionListener(e -> searchByTicketId());

        view.table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                fillForm();
            }
        });
    }

    @Override
    public void add() {
        if (!validateInput()) return;

        LoanDetail ld = new LoanDetail();
        ld.setTicketId(Integer.parseInt(view.txtLoanTicketId.getText().trim()));
        ld.setBookId(Integer.parseInt(view.txtBookId.getText().trim()));
        ld.setQuantity(Integer.parseInt(view.txtQuantity.getText().trim()));
        ld.setNote("");

        if (dao.insert(ld)) {
            MessageBox.info(null, "Thêm chi tiết mượn thành công!");
            loadTable();
            view.clearForm();
        } else {
            MessageBox.error(null, "Thêm chi tiết mượn thất bại!");
        }
    }

    @Override
    public void update() {
        int id = view.getSelectedId();
        if (id == -1) {
            MessageBox.warning(null, "Vui lòng chọn chi tiết mượn cần sửa!");
            return;
        }

        if (!validateInput()) return;

        LoanDetail ld = dao.findById(id);
        if (ld == null) {
            MessageBox.error(null, "Không tìm thấy chi tiết mượn!");
            return;
        }

        ld.setTicketId(Integer.parseInt(view.txtLoanTicketId.getText().trim()));
        ld.setBookId(Integer.parseInt(view.txtBookId.getText().trim()));
        ld.setQuantity(Integer.parseInt(view.txtQuantity.getText().trim()));

        if (dao.update(ld)) {
            MessageBox.info(null, "Cập nhật chi tiết mượn thành công!");
            loadTable();
            view.clearForm();
        } else {
            MessageBox.error(null, "Cập nhật chi tiết mượn thất bại!");
        }
    }

    @Override
    public void delete() {
        int id = view.getSelectedId();
        if (id == -1) {
            MessageBox.warning(null, "Vui lòng chọn chi tiết mượn cần xóa!");
            return;
        }

        boolean confirm = MessageBox.confirm(null, "Bạn có chắc muốn xóa chi tiết mượn này?");
        if (!confirm) return;

        if (dao.delete(id)) {
            MessageBox.info(null, "Xóa chi tiết mượn thành công!");
            loadTable();
            view.clearForm();
        } else {
            MessageBox.error(null, "Xóa chi tiết mượn thất bại!");
        }
    }

    private void fillForm() {
        int row = view.table.getSelectedRow();
        if (row == -1) return;

        view.txtLoanTicketId.setText(view.tableModel.getValueAt(row, 1).toString());
        view.txtBookId.setText(view.tableModel.getValueAt(row, 2).toString());
        view.txtQuantity.setText(view.tableModel.getValueAt(row, 4).toString());
    }

    private void searchByTicketId() {
        String search = view.txtSearch.getText().trim();

        if (search.isEmpty()) {
            loadTable();
            return;
        }

        if (!Validator.isInteger(search)) {
            MessageBox.warning(null, "ID Phiếu Mượn phải là số nguyên!");
            return;
        }

        view.tableModel.setRowCount(0);
        List<Object[]> all = dao.findAllWithBookInfo();

        int ticketId = Integer.parseInt(search);
        for (Object[] row : all) {
            if ((int) row[1] == ticketId) {
                view.tableModel.addRow(row);
            }
        }

        if (view.tableModel.getRowCount() == 0) {
            MessageBox.info(null, "Không tìm thấy chi tiết mượn nào của phiếu này!");
        }
    }

    private boolean validateInput() {
        String ticketId = view.txtLoanTicketId.getText().trim();
        String bookId = view.txtBookId.getText().trim();
        String quantity = view.txtQuantity.getText().trim();

        if (Validator.isEmpty(ticketId) || Validator.isEmpty(bookId) || Validator.isEmpty(quantity)) {
            MessageBox.warning(null, "Vui lòng nhập đầy đủ thông tin!");
            return false;
        }

        if (!Validator.isPositiveNumber(ticketId)) {
            MessageBox.warning(null, "ID Phiếu Mượn phải là số nguyên dương!");
            return false;
        }

        if (!Validator.isPositiveNumber(bookId)) {
            MessageBox.warning(null, "ID Sách phải là số nguyên dương!");
            return false;
        }

        if (!Validator.isPositiveNumber(quantity)) {
            MessageBox.warning(null, "Số lượng phải là số nguyên dương!");
            return false;
        }

        return true;
    }
}