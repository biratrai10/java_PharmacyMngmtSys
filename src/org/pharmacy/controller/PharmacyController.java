/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pharmacy.controller;
import java.sql.*;
import java.util.*;
import java.io.*;
import org.pharmacy.model.*;
import org.pharmacy.util.*;
/**
 *
 * @author DELL
 */
public class PharmacyController {
    
    public void saveData(List<SalesProduct> list)
    {
       Connection con=null;
       try{
           con=DBConnection.getConnection();
           con.setAutoCommit(false);
           for(int i=0;i<list.size();i++)
           {
               String sql="insert into products_orders(pid,oid,quantityOrder) values (?,?,?) ";
               PreparedStatement pst=con.prepareStatement(sql);
               pst.setInt(1,list.get(i).getPid());
               pst.setInt(2, list.get(i).getOid());
               pst.setInt(3,list.get(i).getQuantity());
               //pst.setDate(4, new java.sql.Date(list.get(i).getDos().getTime()));
               pst.execute();
               //get stock quantity from product table
               PharmacyDAO pdao=new PharmacyDAO();
               int qty=pdao.getQty(list.get(i).getPid(),con);
           //update stock quantity of product table
           int newQty=qty-list.get(i).getQuantity();
           pdao.updateQty(list.get(i).getPid(),newQty,con);
           con.commit();
           
           }
           
       }
       catch(Exception ex)
       {
           System.out.println(ex);
       try{
       }
       catch(Exception e)
       {
           System.out.println(e);
       }
       
       }
       finally{
            try{
                con.close();
            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
            }
    }
     public int saveTransaction(SalesProduct sp)
    {
     int count=0;
	try {
	Connection con=DBConnection.getConnection();
		String sql="insert into transactions(paidDate,paidTime,paidAmount,uid,oid) values (?,?,?,?,?)";
                PreparedStatement st=con.prepareStatement(sql);
                               st.setDate(1, new java.sql.Date(sp.getDos().getTime()));
                
                st.setTime(2, new java.sql.Time(sp.getDos().getTime()));
                st.setDouble(3, sp.getTotalamount());
                st.setInt(4, sp.getUid());
                st.setInt(5, sp.getOid());
               count=st.executeUpdate();
                con.close();
        }
	
	catch(Exception ex)
	{
		System.out.println(ex);
	}
        return count;
    }
}
