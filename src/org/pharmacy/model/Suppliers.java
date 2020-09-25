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
public class Suppliers {
    private int Sid;
    private String CompanyName;
    private String Address;
    private String CompanyCountry;
    private String Email;
    private Long ContactNo;
    private int aid;

    public int getSid() {
        return Sid;
    }

    public void setSid(int Sid) {
        this.Sid = Sid;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getCompanyCountry() {
        return CompanyCountry;
    }

    public void setCompanyCountry(String CompanyCountry) {
        this.CompanyCountry = CompanyCountry;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Long getContactNo() {
        return ContactNo;
    }

    public void setContactNo(Long ContactNo) {
        this.ContactNo = ContactNo;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }
    
}
