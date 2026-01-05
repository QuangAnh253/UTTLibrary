/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.model;

/**
 *
 * @author ADMIN
 */
public class Category {
    
    private Integer id;
    private String categoryName;

    public Category() {

    }

    public Category(Integer id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", categoryName=" + categoryName + '}';
    }

    public int getCategoryId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

}
