package com.uttlibrary.model;

public class ShelfLocation {

    private int shelfId;
    private String shelfCode;
    private String description;

    public ShelfLocation() {}

    public ShelfLocation(int shelfId, String shelfCode, String description) {
        this.shelfId = shelfId;
        this.shelfCode = shelfCode;
        this.description = description;
    }

    public int getShelfId() {
        return shelfId;
    }

    public void setShelfId(int shelfId) {
        this.shelfId = shelfId;
    }

    public String getShelfCode() {
        return shelfCode;
    }

    public void setShelfCode(String shelfCode) {
        this.shelfCode = shelfCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
