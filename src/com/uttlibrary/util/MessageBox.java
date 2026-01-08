/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.util;

/**
 *
 * @author ADMIN
 */
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MessageBox {

    // Show thông báo thường
    public static void info(JFrame parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    // Show lỗi
    public static void error(JFrame parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    // Show xác nhận YES / NO
    public static boolean confirm(JFrame parent, String message) {
        int choice = JOptionPane.showConfirmDialog(
                parent,
                message,
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        return choice == JOptionPane.YES_OPTION;
    }

    // Show cảnh báo
    public static void warning(JFrame parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Cảnh báo", JOptionPane.WARNING_MESSAGE);
    }
}