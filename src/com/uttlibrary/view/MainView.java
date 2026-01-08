package com.uttlibrary.view;

import com.uttlibrary.component.SidebarMenu;
import com.uttlibrary.controller.*;
import javax.swing.*;
import java.awt.*;
import com.uttlibrary.view.panel.*;

public class MainView extends JFrame {

    private SidebarMenu sidebar = new SidebarMenu();
    public JPanel content = new JPanel(new CardLayout());

    // ========== PANELS ==========
    DashboardPanel dashboardPanel = new DashboardPanel();
    BookPanel bookPanel = new BookPanel();
    StatisticPanel statisticPanel = new StatisticPanel();
    ReaderPanel readerPanel = new ReaderPanel();
    AuthorPanel authorPanel = new AuthorPanel();
    PublisherPanel publisherPanel = new PublisherPanel();
    
    // --- THÊM MỚI ---
    LoanTicketPanel loanTicketPanel = new LoanTicketPanel();
    LoanDetailPanel loanDetailPanel = new LoanDetailPanel();

    // ========== CONTROLLERS ==========
    private StatisticController statisticController;
    
    // --- THÊM MỚI ---
    private LoanTicketController loanTicketController;
    private LoanDetailController loanDetailController;

    public MainView() {
        setTitle("UTT Library Management");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(sidebar, BorderLayout.WEST);
        add(content, BorderLayout.CENTER);

        // ========== INIT CONTROLLERS ==========
        statisticController = new StatisticController(statisticPanel);
        
        // --- THÊM MỚI ---
        loanTicketController = new LoanTicketController(loanTicketPanel);
        loanDetailController = new LoanDetailController(loanDetailPanel);

        // ========== ADD PANELS TO CONTENT ==========
        content.add(dashboardPanel, "dashboard");
        content.add(bookPanel, "book");
        content.add(statisticPanel, "statistic");
        content.add(authorPanel, "author");
        content.add(publisherPanel, "publisher");
        content.add(readerPanel, "reader");
        
        // --- THÊM MỚI ---
        content.add(loanTicketPanel, "loanticket");
        content.add(loanDetailPanel, "loandetail");

        // Mặc định mở dashboard
        showCard("dashboard");

        addSidebarActions();

        setVisible(true);
    }

    private void addSidebarActions() {
        sidebar.btnDashboard.addActionListener(e -> showCard("dashboard"));
        sidebar.btnBook.addActionListener(e -> showCard("book"));
        sidebar.btnStatistic.addActionListener(e -> showCard("statistic"));
        sidebar.btnAuthor.addActionListener(e -> showCard("author"));
        sidebar.btnPublisher.addActionListener(e -> showCard("publisher"));
        sidebar.btnReader.addActionListener(e -> showCard("reader"));
        
        // --- THÊM MỚI ---
        sidebar.btnLoanTicket.addActionListener(e -> showCard("loanticket"));
        sidebar.btnLoanDetail.addActionListener(e -> showCard("loandetail"));
    }

    private void showCard(String name) {
        CardLayout cl = (CardLayout) content.getLayout();
        cl.show(content, name);
    }
}