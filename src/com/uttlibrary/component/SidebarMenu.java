package com.uttlibrary.component;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.uttlibrary.util.DBConnect;

public class SidebarMenu extends JPanel {

    // ================= BUTTONS ==================
    public JButton btnDashboard = new JButton("Dashboard");
    public JButton btnStatistic = new JButton("Thống Kê");

    // --- QUANG ANH ---
    public JButton btnBook = new JButton("Quản lý Sách");
    public JButton btnLoanTicket = new JButton("Phiếu Mượn");
    public JButton btnLoanDetail = new JButton("Chi Tiết Mượn");

    // --- DUY THÀNH ---
    public JButton btnReader = new JButton("Độc Giả");
    public JButton btnStaff = new JButton("Nhân Viên");
    public JButton btnBooking = new JButton("Đặt Sách");

    // --- THÙY TRANG ---
    public JButton btnSupplier = new JButton("Nhà Cung Cấp");
    public JButton btnImportBook = new JButton("Nhập Sách");
    public JButton btnRegulation = new JButton("Quy Định");

    // --- HỒNG ---
    public JButton btnCategory = new JButton("Thể Loại");
    public JButton btnShelf = new JButton("Kệ Sách");

    // --- LỘC ---
    public JButton btnAuthor = new JButton("Tác Giả");
    public JButton btnPublisher = new JButton("NXB");

    // FOOTER LABELS
    private JLabel lblTime;
    private JLabel lblStatus;

    // ================= CONSTRUCTOR ==================
    public SidebarMenu() {

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(230, 700));
        setBackground(new Color(44, 62, 80));

        // ===== MAIN PANEL (MENU) =====
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(44, 62, 80));

        // ----- SECTIONS -----
        addSection(menuPanel, "DASHBOARD & HỆ THỐNG");
        addMenu(menuPanel, btnDashboard);
        addMenu(menuPanel, btnStatistic);

        addSection(menuPanel, "NGHIỆP VỤ CHÍNH");
        addMenu(menuPanel, btnBook);
        addMenu(menuPanel, btnLoanTicket);
        addMenu(menuPanel, btnLoanDetail);
        addMenu(menuPanel, btnBooking);
        addMenu(menuPanel, btnImportBook);

        addSection(menuPanel, "QUẢN LÝ ĐỐI TƯỢNG");
        addMenu(menuPanel, btnReader);
        addMenu(menuPanel, btnStaff);
        addMenu(menuPanel, btnAuthor);
        addMenu(menuPanel, btnPublisher);
        addMenu(menuPanel, btnSupplier);

        addSection(menuPanel, "DANH MỤC & CẤU HÌNH");
        addMenu(menuPanel, btnCategory);
        addMenu(menuPanel, btnShelf);
        addMenu(menuPanel, btnRegulation);

        menuPanel.add(Box.createVerticalGlue());

        // ADD INTO SIDEBAR
        add(menuPanel, BorderLayout.CENTER);

        // ===== FOOTER =====
        add(createFooter(), BorderLayout.SOUTH);
    }

    // ==========================================================
    // ================ UI SUPPORT METHODS =====================
    // ==========================================================

    private void addSection(JPanel panel, String title) {
        JLabel lbl = new JLabel(title);
        lbl.setForeground(new Color(189, 195, 199));
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lbl.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 10));

        panel.add(lbl);
    }

    private void addMenu(JPanel panel, JButton btn) {
        config(btn);
        panel.add(btn);
    }

    private void config(JButton btn) {
        btn.setFocusPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(52, 73, 94));
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 10));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
    }

    // ==========================================================
    // ======================= FOOTER ============================
    // ==========================================================

    private JPanel createFooter() {
        JPanel footer = new JPanel();
        footer.setPreferredSize(new Dimension(200, 90));
        footer.setBackground(new Color(36, 49, 68));
        footer.setLayout(new GridLayout(3, 1));

        // APP NAME
        JLabel lblApp = new JLabel("UTT Library © 2025", SwingConstants.CENTER);
        lblApp.setForeground(new Color(200, 200, 200));
        lblApp.setFont(new Font("Segoe UI", Font.BOLD, 13));

        // TIME
        lblTime = new JLabel("", SwingConstants.CENTER);
        lblTime.setForeground(new Color(180, 180, 180));

        // DB STATUS
        lblStatus = new JLabel("", SwingConstants.CENTER);
        lblStatus.setForeground(Color.GREEN);

        footer.add(lblApp);
        footer.add(lblTime);
        footer.add(lblStatus);

        startClock();
        checkDBStatus();

        return footer;
    }

    private void startClock() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss  |  dd/MM/yyyy");
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                lblTime.setText(sdf.format(new Date()));
            }
        }, 0, 1000);
    }

    private void checkDBStatus() {
        try {
            if (DBConnect.getConnection() != null) {
                lblStatus.setText("✔ Connected");
                lblStatus.setForeground(Color.GREEN);
            } else {
                lblStatus.setText("✖ DB Error");
                lblStatus.setForeground(Color.RED);
            }
        } catch (Exception e) {
            lblStatus.setText("✖ DB Error");
            lblStatus.setForeground(Color.RED);
        }
    }
}
