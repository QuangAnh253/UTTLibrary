/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.view;

/**
 *
 * @author ADMIN
 */

import com.uttlibrary.component.RoundedPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StatisticPanel extends JPanel {

    public JComboBox<String> cboFilter = new JComboBox<>(new String[]{
            "Sách mượn nhiều",
            "Sách trễ hạn",
            "Độc giả mượn nhiều nhất",
            "Số lượng sách theo danh mục"
    });

    public JButton btnView = new JButton("Xem thống kê");
    public JTable table = new JTable();
    public DefaultTableModel tableModel = new DefaultTableModel();

    public StatisticPanel() {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // TOP FILTER PANEL
        RoundedPanel filterPanel = new RoundedPanel(Color.WHITE, 15);
        filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

        cboFilter.setPreferredSize(new Dimension(250, 30));
        btnView.setPreferredSize(new Dimension(150, 30));

        filterPanel.add(new JLabel("Chọn loại thống kê:"));
        filterPanel.add(cboFilter);
        filterPanel.add(btnView);

        // TABLE
        tableModel.setColumnIdentifiers(new String[]{"ID", "Tên", "Số liệu"});
        table.setModel(tableModel);

        JScrollPane scroll = new JScrollPane(table);

        add(filterPanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }
}
