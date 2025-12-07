/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.auth;

/**
 *
 * @author ADMIN
 */

import com.uttlibrary.view.MainView;

public class LoginController {

    private LoginView view = new LoginView();
    private AuthService service = new AuthService();

    public LoginController() {
        view.btnLogin.addActionListener(e -> login());
    }

    private void login() {
        String user = view.txtUser.getText();
        String pass = String.valueOf(view.txtPass.getPassword());

        if(service.login(user, pass)){
            view.dispose();
            new MainView();
        } else {
            javax.swing.JOptionPane.showMessageDialog(view,"Sai tài khoản hoặc mật khẩu!");
        }
    }
}