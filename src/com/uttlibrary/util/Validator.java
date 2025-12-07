/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.util;

/**
 *
 * @author ADMIN
 */
public class Validator {

    // Kiểm tra chuỗi rỗng hoặc null
    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    // Kiểm tra độ dài tối thiểu
    public static boolean minLength(String value, int length) {
        if (value == null) return false;
        return value.trim().length() >= length;
    }

    // Kiểm tra dạng email
    public static boolean isEmail(String value) {
        if (isEmpty(value)) return false;
        return value.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    // Kiểm tra số nguyên
    public static boolean isInteger(String value) {
        if (isEmpty(value)) return false;
        return value.matches("^-?\\d+$");
    }

    // Kiểm tra số dương
    public static boolean isPositiveNumber(String value) {
        if (!isInteger(value)) return false;
        return Integer.parseInt(value) > 0;
    }

    // Kiểm tra float / double
    public static boolean isDouble(String value) {
        if (isEmpty(value)) return false;
        return value.matches("^-?\\d+(\\.\\d+)?$");
    }

    // Kiểm tra theo regex tùy chỉnh
    public static boolean match(String value, String regex) {
        if (value == null) return false;
        return value.matches(regex);
    }
}
