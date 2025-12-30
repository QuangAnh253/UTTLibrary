/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.controller;

import com.uttlibrary.dao.LoanTicketDAO;
import com.uttlibrary.dao.LoanDetailDAO;
import com.uttlibrary.model.LoanTicket;
import com.uttlibrary.util.DateFormatter;
import com.uttlibrary.util.MessageBox;
import com.uttlibrary.util.SessionHelper;
import com.uttlibrary.util.Validator;
import com.uttlibrary.view.panel.LoanTicketPanel;

import javax.swing.*;
import java.util.Date;
import java.util.List;

public class LoanTicketController extends BaseController<LoanTicket> {

    private LoanTicketPanel view;
    private LoanTicketDAO dao;
    private LoanDetailDAO detailDAO;

    public LoanTicketController(LoanTicketPanel view) {
        this.view = view;
        this.dao = new LoanTicketDAO();
        this.detailDAO = new LoanDetailDAO();

        loadTable();
        addEvents();
    }

    @Override
    public void loadTable() {
        view.tableModel.setRowCount(0);
        List<Object[]> list = dao.findAllWithReaderInfo();

        for (Object[] row : list) {
            view.tableModel.addRow(row);
        }
    }

    private void addEvents() {
        view.btnAdd.addActionListener(e -> add());
        view.btnUpdate.addActionListener(e -> update());
        view.btnDelete.addActionListener(e -> delete());
        view.btnClear.addActionListener(e -> view.clearForm());
        view.btnViewDetail.addActionListener(e -> viewDetail());
        view.btnSearch.addActionListener(e -> searchByReaderId());

        view.table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                fillForm();
            }
        });
    }

    @Override
    public void add() {
        if (!validateInput()) return;

        LoanTicket lt = new LoanTicket();
        lt.setReaderId(Integer.parseInt(view.txtReaderId.getText().trim()));
        lt.setStaffId(SessionHelper.userId); // Lấy từ session đăng nhập
        lt.setBorrowDate(DateFormatter.parseDisplay(view.txtLoanDate.getText().trim()));
        lt.setDueDate(DateFormatter.parseDisplay(view.txtReturnDate.getText().trim()));
        lt.setStatus("Borrowing"); // Mặc định đang mượn
        lt.setTotalFine(0.0);

        if (dao.insert(lt)) {
            MessageBox.info(null, "Thêm phiếu mượn thành công!");
            loadTable();
            view.clearForm();
        } else {
            MessageBox.error(null, "Thêm phiếu mượn thất bại!");
        }
    }

    @Override
    public void update() {
        int id = view.getSelectedId();
        if (id == -1) {
            MessageBox.warning(null, "Vui lòng chọn phiếu mượn cần sửa!");
            return;
        }

        if (!validateInput()) return;

        LoanTicket lt = dao.findById(id);
        if (lt == null) {
            MessageBox.error(null, "Không tìm thấy phiếu mượn!");
            return;
        }

        lt.setReaderId(Integer.parseInt(view.txtReaderId.getText().trim()));
        lt.setBorrowDate(DateFormatter.parseDisplay(view.txtLoanDate.getText().trim()));
        lt.setDueDate(DateFormatter.parseDisplay(view.txtReturnDate.getText().trim()));

        if (dao.update(lt)) {
            MessageBox.info(null, "Cập nhật phiếu mượn thành công!");
            loadTable();
            view.clearForm();
        } else {
            MessageBox.error(null, "Cập nhật phiếu mượn thất bại!");
        }
    }

    @Override
    public void delete() {
        int id = view.getSelectedId();
        if (id == -1) {
            MessageBox.warning(null, "Vui lòng chọn phiếu mượn cần xóa!");
            return;
        }

        boolean confirm = MessageBox.confirm(null, "Bạn có chắc muốn xóa phiếu mượn này?\n(Chi tiết mượn cũng sẽ bị xóa)");
        if (!confirm) return;

        detailDAO.deleteByTicketId(id);

        if (dao.delete(id)) {
            MessageBox.info(null, "Xóa phiếu mượn thành công!");
            loadTable();
            view.clearForm();
        } else {
            MessageBox.error(null, "Xóa phiếu mượn thất bại!");
        }
    }

    private void fillForm() {
        int row = view.table.getSelectedRow();
        if (row == -1) return;

        view.txtReaderId.setText(view.tableModel.getValueAt(row, 1).toString());
        view.txtLoanDate.setText(view.tableModel.getValueAt(row, 3).toString());
        view.txtReturnDate.setText(view.tableModel.getValueAt(row, 4).toString());
    }

    private void viewDetail() {
        int id = view.getSelectedId();
        if (id == -1) {
            MessageBox.warning(null, "Vui lòng chọn phiếu mượn để xem chi tiết!");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Chi tiết Phiếu Mượn #").append(id).append("\n\n");

        List<Object[]> details = detailDAO.findAllWithBookInfo();
        boolean found = false;

        for (Object[] row : details) {
            int ticketId = (int) row[1];
            if (ticketId == id) {
                found = true;
                sb.append("- Sách: ").append(row[3])
                  .append(" (ID: ").append(row[2])
                  .append(") - Số lượng: ").append(row[4])
                  .append("\n");
            }
        }

        if (!found) {
            sb.append("Chưa có sách nào được mượn trong phiếu này.");
        }

        JOptionPane.showMessageDialog(null, sb.toString(), "Chi tiết Phiếu Mượn", JOptionPane.INFORMATION_MESSAGE);
    }

    private void searchByReaderId() {
        String search = view.txtSearch.getText().trim();

        if (search.isEmpty()) {
            loadTable();
            return;
        }

        if (!Validator.isInteger(search)) {
            MessageBox.warning(null, "ID Độc Giả phải là số nguyên!");
            return;
        }

        view.tableModel.setRowCount(0);
        List<Object[]> all = dao.findAllWithReaderInfo();

        int readerId = Integer.parseInt(search);
        for (Object[] row : all) {
            if ((int) row[1] == readerId) {
                view.tableModel.addRow(row);
            }
        }

        if (view.tableModel.getRowCount() == 0) {
            MessageBox.info(null, "Không tìm thấy phiếu mượn nào của độc giả này!");
        }
    }

    private boolean validateInput() {
        String readerId = view.txtReaderId.getText().trim();
        String loanDate = view.txtLoanDate.getText().trim();
        String returnDate = view.txtReturnDate.getText().trim();

        if (Validator.isEmpty(readerId) || Validator.isEmpty(loanDate) || Validator.isEmpty(returnDate)) {
            MessageBox.warning(null, "Vui lòng nhập đầy đủ thông tin!");
            return false;
        }

        if (!Validator.isPositiveNumber(readerId)) {
            MessageBox.warning(null, "ID Độc Giả phải là số nguyên dương!");
            return false;
        }

        Date loan = DateFormatter.parseDisplay(loanDate);
        Date ret = DateFormatter.parseDisplay(returnDate);

        if (loan == null) {
            MessageBox.warning(null, "Ngày mượn không đúng định dạng dd/MM/yyyy!");
            return false;
        }

        if (ret == null) {
            MessageBox.warning(null, "Ngày trả không đúng định dạng dd/MM/yyyy!");
            return false;
        }

        if (ret.before(loan)) {
            MessageBox.warning(null, "Ngày trả phải sau ngày mượn!");
            return false;
        }

        return true;
    }
}