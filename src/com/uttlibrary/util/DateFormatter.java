/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.util;

/**
 *
 * @author ADMIN
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    public static final String DEFAULT_PATTERN = "yyyy-MM-dd";
    public static final String DISPLAY_PATTERN = "dd/MM/yyyy";

    // Format Date -> String
    public static String format(Date date) {
        return new SimpleDateFormat(DEFAULT_PATTERN).format(date);
    }

    // Format dạng hiển thị dd/MM/yyyy
    public static String formatDisplay(Date date) {
        return new SimpleDateFormat(DISPLAY_PATTERN).format(date);
    }

    // Parse String -> Date (yyyy-MM-dd)
    public static Date parse(String dateStr) {
        try {
            return new SimpleDateFormat(DEFAULT_PATTERN).parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    // Parse dạng dd/MM/yyyy
    public static Date parseDisplay(String dateStr) {
        try {
            return new SimpleDateFormat(DISPLAY_PATTERN).parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    // Chuyển đổi từ dd/MM/yyyy sang yyyy-MM-dd
    public static String displayToSQL(String dateStr) {
        Date d = parseDisplay(dateStr);
        return d != null ? format(d) : null;
    }

    // Chuyển đổi từ yyyy-MM-dd sang dd/MM/yyyy
    public static String sqlToDisplay(String dateStr) {
        Date d = parse(dateStr);
        return d != null ? formatDisplay(d) : null;
    }
}
