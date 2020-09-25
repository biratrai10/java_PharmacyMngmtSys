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
import org.pharmacy.model.Login;

public class LoginDAO {
    public int verifyUser(Login u)
    {
        int count=0;
        try {
	Connection con=DBConnection.getConnection();
		String sql="select * from admins where username = ? and password = ? ";
                PreparedStatement pst=con.prepareStatement(sql);
                pst.setString(1,u.getUsername());
                pst.setString(2,u.getPassword());
               ResultSet rs=pst.executeQuery();
               if(rs.next())
			{
                                count=rs.getInt("aid");
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
		String sql="update admins set password= ? where aid=? ";
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
}
