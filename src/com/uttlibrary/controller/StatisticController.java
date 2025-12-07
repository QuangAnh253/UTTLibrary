/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.controller;

/**
 *
 * @author ADMIN
 */

import com.uttlibrary.dao.StatisticDAO;
import com.uttlibrary.view.StatisticPanel;

import java.util.List;

public class StatisticController {

    private final StatisticPanel view;
    private final StatisticDAO dao = new StatisticDAO();

    public StatisticController(StatisticPanel view) {
        this.view = view;

        view.btnView.addActionListener(e -> loadStatistic());
    }

    private void loadStatistic() {
        String type = view.cboFilter.getSelectedItem().toString();
        List<Object[]> data = null;

        switch (type) {
            case "Sách mượn nhiều" -> data = dao.mostBorrowedBooks();
            case "Sách trễ hạn" -> data = dao.overdueBooks();
            case "Độc giả mượn nhiều nhất" -> data = dao.topReaders();
            case "Số lượng sách theo danh mục" -> data = dao.bookByCategory();
        }

        updateTable(data);
    }

    private void updateTable(List<Object[]> rows) {
        view.tableModel.setRowCount(0);

        for (Object[] r : rows) {
            view.tableModel.addRow(r);
        }
    }
}