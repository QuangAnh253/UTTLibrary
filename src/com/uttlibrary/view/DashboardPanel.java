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
import com.uttlibrary.dao.DashboardDAO;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    private DashboardDAO dao = new DashboardDAO();

    public DashboardPanel() {

        int totalBooks = dao.countBooks();
        int borrowing = dao.countBorrowing();
        int totalUsers = dao.countUsers();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(236, 240, 241));

        JPanel row = new JPanel(new GridLayout(1, 3, 20, 20));
        row.setOpaque(false);
        row.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        row.add(statCard("Tổng số sách",
                String.valueOf(totalBooks),
                "📚",
                new Color(52, 152, 219),
                "+12% so với tháng trước"
        ));

        row.add(statCard("Sách đang mượn",
                String.valueOf(borrowing),
                "📖",
                new Color(46, 204, 113),
                "+3% tuần này"
        ));

        row.add(statCard("Người dùng",
                String.valueOf(totalUsers),
                "👥",
                new Color(241, 196, 15),
                "+5 tài khoản mới"
        ));

        add(Box.createVerticalGlue());
        add(row);
        add(Box.createVerticalGlue());
    }

    // ============================================================
    //                          CARD
    // ============================================================
    private JPanel statCard(String title, String value, String icon, Color mainColor, String subInfo) {

        RoundedPanel card = new RoundedPanel(new Color(255, 255, 255, 230), 22) {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(mainColor.getRed(), mainColor.getGreen(), mainColor.getBlue(), 40),
                        0, getHeight(), new Color(255, 255, 255, 0)
                );

                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 22, 22);
            }
        };
        card.setPreferredSize(new Dimension(300, 180));

        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));

        // ICON
        JLabel lbIcon = new JLabel(icon);
        lbIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));

        // TITLE
        JLabel lbTitle = new JLabel(title);
        lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lbTitle.setForeground(new Color(85, 85, 85));

        // VALUE (giảm size cho đẹp hơn)
        JLabel lbValue = new JLabel(value);
        lbValue.setFont(new Font("Segoe UI", Font.BOLD, 34));
        lbValue.setForeground(mainColor);

        // SUB INFO
        JLabel lbSub = new JLabel(subInfo);
        lbSub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lbSub.setForeground(new Color(120, 120, 120));

        // ----------------------------------------------------------------
        // TOP: Title + icon
        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(lbTitle, BorderLayout.WEST);
        top.add(lbIcon, BorderLayout.EAST);

        // ----------------------------------------------------------------
        // BOTTOM: Sub text
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setOpaque(false);
        bottom.add(lbSub, BorderLayout.WEST);

        // ----------------------------------------------------------------
        // CARD add
        card.add(top, BorderLayout.NORTH);
        card.add(lbValue, BorderLayout.CENTER);
        card.add(bottom, BorderLayout.SOUTH);

        // ----------------------------------------------------------------
        // HOVER EFFECT CLEAN
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                card.setCursor(new Cursor(Cursor.HAND_CURSOR));
                card.setBackground(new Color(255, 255, 255)); // sáng nhẹ
                card.setBorder(BorderFactory.createLineBorder(mainColor, 2, true));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                card.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
            }
        });

        return card;
    }
}
