package com.uttlibrary.view;

import com.uttlibrary.component.SidebarMenu;
import com.uttlibrary.controller.StatisticController;
import javax.swing.*;
import java.awt.*;
import com.uttlibrary.view.panel.BookPanel;
import com.uttlibrary.view.panel.AuthorPanel;
import com.uttlibrary.view.panel.PublisherPanel;


public class MainView extends JFrame {

    private SidebarMenu sidebar = new SidebarMenu();
    public JPanel content = new JPanel(new CardLayout());

    DashboardPanel dashboardPanel = new DashboardPanel();
    BookPanel bookPanel = new BookPanel();
    StatisticPanel statisticPanel = new StatisticPanel();

    private StatisticController statisticController;

    AuthorPanel authorPanel = new AuthorPanel();
    PublisherPanel publisherPanel = new PublisherPanel();
    public MainView() {

        setTitle("UTT Library Management");

        // ===== FULLSCREEN =====
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false); // giữ thanh title bar để kéo, không fullscreen cứng

        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(sidebar, BorderLayout.WEST);
        add(content, BorderLayout.CENTER);

        // Controller
        statisticController = new StatisticController(statisticPanel);

        // Thêm panel vào Content
        content.add(dashboardPanel, "dashboard");
        content.add(bookPanel, "book");
        content.add(statisticPanel, "statistic");
        content.add(authorPanel, "author");
        content.add(publisherPanel, "publisher");
        
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

    }

    private void showCard(String name) {
        CardLayout cl = (CardLayout) content.getLayout();
        cl.show(content, name);
    }
    
}
