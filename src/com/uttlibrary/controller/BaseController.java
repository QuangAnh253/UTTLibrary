/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uttlibrary.controller;

/**
 *
 * @author ADMIN
 */
public abstract class BaseController<T> {
    public abstract void loadTable();
    public abstract void add();
    public abstract void update();
    public abstract void delete();
}
