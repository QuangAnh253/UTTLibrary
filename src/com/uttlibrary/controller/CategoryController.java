package com.uttlibrary.controller;

import com.uttlibrary.dao.CategoryDAO;
import com.uttlibrary.model.Category;
import java.util.List;

public class CategoryController {

    private CategoryDAO categoryDAO;

    public CategoryController() {
        categoryDAO = new CategoryDAO();
    }

    public List<Category> getAllCategories() {
        return categoryDAO.findAll();
    }

    public boolean addCategory(String name, String description) {
        Category c = new Category();
        c.setCategoryName(name);
        c.setDescription(description);
        return categoryDAO.insert(c);
    }

    public boolean updateCategory(int id, String name, String description) {
        Category c = new Category(id, name, description);
        return categoryDAO.update(c);
    }

    public boolean deleteCategory(int id) {
        return categoryDAO.delete(id);
    }
}
