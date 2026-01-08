/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.auth;

/**
 *
 * @author ADMIN
 */

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    public JTextField txtUser = new JTextField(20);
    public JPasswordField txtPass = new JPasswordField(20);
    public JButton btnLogin = new JButton("Đăng nhập");

    public LoginView() {
        setTitle("Đăng nhập hệ thống");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3,2,10,10));
        panel.add(new JLabel("Tài khoản:"));
        panel.add(txtUser);
        panel.add(new JLabel("Mật khẩu:"));
        panel.add(txtPass);
        panel.add(new JLabel(""));
        panel.add(btnLogin);

        add(panel);
        setVisible(true);
    }
}
