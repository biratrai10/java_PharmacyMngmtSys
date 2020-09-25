/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pharmacy.model;
import java.io.*;
import java.util.*;
/**
 *
 * @author DELL
 */
public class ProductsSuppliers {
    private int pid;
    private String ProductName;
    private String SupplierName;
    private String ProductCategory;
    private Date MfgYear;
    private int Quantity;
    private double Price;
    private int cid;
    private int aid;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String SupplierName) {
        this.SupplierName = SupplierName;
    }

    public String getProductCategory() {
        return ProductCategory;
    }

    public void setProductCategory(String ProductCategory) {
        this.ProductCategory = ProductCategory;
    }

    public Date getMfgYear() {
        return MfgYear;
    }

    public void setMfgYear(Date MfgYear) {
        this.MfgYear = MfgYear;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

   public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }
    

  
       
}
