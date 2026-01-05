package com.uttlibrary.model;

public class ShelfLocation {

    private int shelfId;
    private String shelfCode;
    private String description;

    public ShelfLocation(int shelfId, String shelfCode, String description) {
        this.shelfId = shelfId;
        this.shelfCode = shelfCode;
        this.description = description;
    }

    public ShelfLocation() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getShelfId() {
        return shelfId;
    }

    public String getShelfCode() {
        return shelfCode;
    }

    public String getDescription() {
        return description;
    }

    public void setShelfId(int shelfId) {
        this.shelfId = shelfId;
    }

    public void setShelfCode(String shelfCode) {
        this.shelfCode = shelfCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShelfName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setShelfName(String trim) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

    