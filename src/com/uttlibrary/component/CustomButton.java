/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.component;

/**
 *
 * @author ADMIN
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {

    private Color normalColor = new Color(52, 152, 219);
    private Color hoverColor = new Color(41, 128, 185);
    private Color pressedColor = new Color(31, 97, 141);

    public CustomButton(String text) {
        super(text);
        setFocusPainted(false);
        setBackground(normalColor);
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 14));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { setBackground(hoverColor); }

            @Override
            public void mouseExited(MouseEvent e) { setBackground(normalColor); }

            @Override
            public void mousePressed(MouseEvent e) { setBackground(pressedColor); }

            @Override
            public void mouseReleased(MouseEvent e) { setBackground(hoverColor); }
        });
    }
}