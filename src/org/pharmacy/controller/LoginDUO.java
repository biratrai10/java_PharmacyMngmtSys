/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pharmacy.controller;
import java.io.*;
import org.pharmacy.util.DBConnection;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import org.pharmacy.model.*;

public class LoginDUO {
    public int verifyUser(Login u)
    {
        int count=0;
        try {
	Connection con=DBConnection.getConnection();
		String sql="select * from users where username = ? and password = ? ";
                PreparedStatement pst=con.prepareStatement(sql);
                pst.setString(1,u.getUsername());
                pst.setString(2,u.getPassword());
               ResultSet rs=pst.executeQuery();
               if(rs.next())
			{
                                count=rs.getInt("uid");
			}
			else {
                                count=0;
			}
                con.close();
        }
	
	catch(Exception ex)
	{
		System.out.println(ex);
	}
        return count;
    }
   
     public int updatePassword(int uid,String new1)
    {
        int count=0;
        try {
	Connection con=DBConnection.getConnection();
		String sql="update users set password= ? where uid=? ";
                PreparedStatement pst=con.prepareStatement(sql);
                pst.setString(1,new1);
                pst.setInt(2,uid);
               count=pst.executeUpdate();

                con.close();
        }
	
	catch(Exception ex)
	{
		System.out.println(ex);
	}
        return count;
    }
     
     public int createUser(UserAccount u)
       {
           int count=0;
	try {
	Connection con=DBConnection.getConnection();
		String sql="insert into users(username,password,full_name,address,email,contact,aid) values (?,?,?,?,?,?,?)";
                PreparedStatement st=con.prepareStatement(sql);
                st.setString(1,u.getUname());
                                st.setString(2,u.getUpass());
                st.setString(3,u.getFullname());

                st.setString(4,u.getAddress());
                st.setString(5, u.getEmail());
                //st.setDate(5, (Date) ps.getMfgYear());
                st.setLong(6, u.getUcontact());
                                st.setInt(7, u.getAid());
                                

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
