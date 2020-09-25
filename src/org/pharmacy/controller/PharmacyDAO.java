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
import org.pharmacy.model.*;
import org.pharmacy.util.*;
/**
 *
 * @author DELL
 */
public class PharmacyDAO {
    public List fetchData()
  {
      List<ProductsSuppliers> productList=new ArrayList();
      try{
          Connection con=DBConnection.getConnection();
		String sql="SELECT p.pid,productName,companyName,productCatagory,productMfd,productQuantity,productPrice\n" +
"FROM products AS p\n" +
"INNER JOIN suppliers AS s\n" +
"ON p.sid=s.sid\n" +
"GROUP BY p.pid;";
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery(sql);
      while(rs.next())
      {
         ProductsSuppliers  ob=new ProductsSuppliers();
          ob.setPid(rs.getInt("pid"));
                              ob.setProductName(rs.getString("productName"));
                   ob.setSupplierName(rs.getString("companyName"));
                  // ob.setSupplierName("sk");
                                        ob.setProductCategory(rs.getString("productCatagory"));
                                        ob.setMfgYear(rs.getDate("productMfd"));

          ob.setQuantity(rs.getInt("productQuantity"));

                         ob.setPrice(rs.getDouble("productPrice"));
                   productList.add(ob);
      }
      }
      catch(Exception ex)
      {
          System.out.println(ex);
      }
      return productList;
  }
       public List searchList(String abc)
  {
      List<ProductsSuppliers> productList=new ArrayList();
      try{
          Connection con=DBConnection.getConnection();
		String sql="SELECT p.pid,productName,companyName,productCatagory,productMfd,productQuantity,productPrice\n" +
"FROM products AS p\n" +
"INNER JOIN suppliers AS s ON p.sid=s.sid\n" +
"WHERE productName LIKE ? \n" +
"GROUP BY p.pid;";
 PreparedStatement pst=con.prepareStatement(sql);
             pst.setString(1,"%"+abc+"%");
   //                       pst.setInt(2,xyz);
             ResultSet rs=pst.executeQuery();
      while(rs.next())
      {
          ProductsSuppliers  ob=new ProductsSuppliers();
          ob.setPid(rs.getInt("pid"));
                              ob.setProductName(rs.getString("productName"));
                   ob.setSupplierName(rs.getString("companyName"));
                  // ob.setSupplierName("sk");
                                        ob.setProductCategory(rs.getString("productCatagory"));
                                        ob.setMfgYear(rs.getDate("productMfd"));

          ob.setQuantity(rs.getInt("productQuantity"));

                         ob.setPrice(rs.getDouble("productPrice"));
                   productList.add(ob);
      }
      }
      catch(Exception ex)
      {
          System.out.println(ex);
      }
      return productList;
  }
        public List fetchData1()
  {
      List<Suppliers> suppliersList=new ArrayList();
      try{
          Connection con=DBConnection.getConnection();
		String sql="SELECT * FROM suppliers";
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery(sql);
      while(rs.next())
      {
         Suppliers  ob=new Suppliers();
          ob.setSid(rs.getInt("sid"));
                              ob.setCompanyName(rs.getString("companyName"));
                   ob.setAddress(rs.getString("address"));
                                        ob.setCompanyCountry(rs.getString("companyCountry"));
                                        ob.setEmail(rs.getString("cemail"));


                         ob.setContactNo(rs.getLong("ccontact"));
                   suppliersList.add(ob);
      }
      }
      catch(Exception ex)
      {
          System.out.println(ex);
      }
      return suppliersList;
  }
        public List searchList1(String abc)
  {
      List<Suppliers> suppliersList1=new ArrayList();
      try{
          Connection con=DBConnection.getConnection();
		String sql="SELECT * FROM suppliers WHERE companyName LIKE ?";
 PreparedStatement pst=con.prepareStatement(sql);
             pst.setString(1,"%"+abc+"%");
   //                       pst.setInt(2,xyz);
             ResultSet rs=pst.executeQuery();
      while(rs.next())
      {
           Suppliers  ob=new Suppliers();
          ob.setSid(rs.getInt("sid"));
                              ob.setCompanyName(rs.getString("companyName"));
                   ob.setAddress(rs.getString("address"));
                                        ob.setCompanyCountry(rs.getString("companyCountry"));
                                        ob.setEmail(rs.getString("cemail"));


                         ob.setContactNo(rs.getLong("ccontact"));
                   suppliersList1.add(ob);
      }
      }
      catch(Exception ex)
      {
          System.out.println(ex);
      }
      return suppliersList1;
  }
        public int insertCompany(Suppliers s)
    {
        int count=0;
	try {
	Connection con=DBConnection.getConnection();
		String sql="insert into suppliers(companyName,address,companyCountry,cemail,ccontact,aid) values (?,?,?,?,?,?)";
                PreparedStatement st=con.prepareStatement(sql);
                st.setString(1,s.getCompanyName());
                                st.setString(2,s.getAddress());
                st.setString(3,s.getCompanyCountry());

                st.setString(4,s.getEmail());
              
                st.setLong(5, s.getContactNo());
                st.setInt(6, s.getAid());
               count=st.executeUpdate();
                con.close();
        }
	
	catch(Exception ex)
	{
		System.out.println(ex);
	}
        return count;
    }
       public List searchCompanyId(String abc)
  {
      List<Suppliers> suppliersList1=new ArrayList();
      try{
          Connection con=DBConnection.getConnection();
		String sql="SELECT * FROM suppliers WHERE companyName LIKE ?";
 PreparedStatement pst=con.prepareStatement(sql);
             pst.setString(1,"%"+abc+"%");
   //                       pst.setInt(2,xyz);
             ResultSet rs=pst.executeQuery();
      while(rs.next())
      {
           Suppliers  ob=new Suppliers();
          ob.setSid(rs.getInt("sid"));
                              ob.setCompanyName(rs.getString("companyName"));
                   ob.setAddress(rs.getString("address"));
                                        ob.setCompanyCountry(rs.getString("companyCountry"));
                                        ob.setEmail(rs.getString("cemail"));


                         ob.setContactNo(rs.getLong("ccontact"));
                   suppliersList1.add(ob);
      }
      }
      catch(Exception ex)
      {
          System.out.println(ex);
      }
      return suppliersList1;
  }
       public int updateCompany(Suppliers s)
{
    int count=0;
    try {
		          Connection con=DBConnection.getConnection();
        String sql="update suppliers set companyName=?,address=?,companyCountry=?,cemail=?,ccontact=? where sid=?";
			PreparedStatement pst=con.prepareStatement(sql);
                       
                        pst.setString(1, s.getCompanyName());
                        pst.setString(2,s.getAddress());
                                                pst.setString(3,s.getCompanyCountry());
                        pst.setString(4,s.getEmail());
                        pst.setLong(5,s.getContactNo());
                        pst.setInt(6,s.getSid());

count=pst.executeUpdate();
                con.close();

        }
	
	catch(Exception ex)
	{
		System.out.println(ex);
	}
    return count;
}
       public int eraseCompany(Suppliers s)
  {
     int count=0;
    
     try {
	Connection con=DBConnection.getConnection();
		String sql="delete from suppliers where sid=? ";
             PreparedStatement pst=con.prepareStatement(sql);
             pst.setInt(1, s.getSid());
		count=pst.executeUpdate();
                con.close();
        }
	
	catch(Exception ex)
	{
		System.out.println(ex);
	}
        return count;
  }
       public int insertmedic(ProductsSuppliers ps)
       {
           int count=0;
	try {
	Connection con=DBConnection.getConnection();
		String sql="insert into products(productName,productCatagory,productPrice,productQuantity,productMfd,aid,sid) values (?,?,?,?,?,?,?)";
                PreparedStatement st=con.prepareStatement(sql);
                st.setString(1,ps.getProductName());
                                st.setString(2,ps.getProductCategory());
                st.setDouble(3,ps.getPrice());

                st.setInt(4,ps.getQuantity());
                st.setDate(5, new java.sql.Date(ps.getMfgYear().getTime()));
                //st.setDate(5, (Date) ps.getMfgYear());
                st.setInt(6, ps.getAid());
                                st.setInt(7, ps.getCid());
                                

               count=st.executeUpdate();
                con.close();
        }
	
	catch(Exception ex)
	{
		System.out.println(ex);
	}
        return count;
       }
       public int updateMedic(ProductsSuppliers s)
       {
         int count=0;
    try {
		          Connection con=DBConnection.getConnection();
        String sql="update products set productName=?,productCatagory=?,productPrice=?,productQuantity=?,aid=? where pid=?";
			PreparedStatement pst=con.prepareStatement(sql);
                       
                        pst.setString(1, s.getProductName());
                        pst.setString(2,s.getProductCategory());
                                                pst.setDouble(3,s.getPrice());
                        pst.setInt(4,s.getQuantity());
                        pst.setInt(5,s.getAid());
                        pst.setInt(6,s.getPid());

count=pst.executeUpdate();
                con.close();

        }
	
	catch(Exception ex)
	{
		System.out.println(ex);
	}
    return count;
       }
       public int eraseMedic(ProductsSuppliers ps)
       {
           int count=0;
    
     try {
	Connection con=DBConnection.getConnection();
		String sql="delete from products where pid=? ";
             PreparedStatement pst=con.prepareStatement(sql);
             pst.setInt(1, ps.getPid());
		count=pst.executeUpdate();
                con.close();
        }
	
	catch(Exception ex)
	{
		System.out.println(ex);
	}
        return count;
       }
        public int customers(customers s)
    {
        int count=0;
	try {
	Connection con=DBConnection.getConnection();
		String sql="insert into customers(customerName,customerAddress,Age) values (?,?,?)";
                PreparedStatement st=con.prepareStatement(sql);
                st.setString(1,s.getCustomerName());
                                st.setString(2,s.getCustomerAddress());
                st.setInt(3, s.getAge());
               count=st.executeUpdate();
                con.close();
        }
	
	catch(Exception ex)
	{
		System.out.println(ex);
	}
        return count;
    }
        public int customersInfo(customers c)
        {
            int count=0;
        try {
	Connection con=DBConnection.getConnection();
		String sql="select * from customers where customerName = ? and Age = ? ";
                PreparedStatement pst=con.prepareStatement(sql);
                pst.setString(1,c.getCustomerName());
                pst.setInt(2,c.getAge());
               ResultSet rs=pst.executeQuery();
               if(rs.next())
			{
                                count=rs.getInt("customerId");
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
        public List productData()
  {
      List<ProductsSuppliers> productList=new ArrayList();
      try{
          Connection con=DBConnection.getConnection();
		String sql="SELECT * FROM products";
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery(sql);
      while(rs.next())
      {
          ProductsSuppliers  ob=new ProductsSuppliers();
          ob.setPid(rs.getInt("pid"));
                              ob.setProductName(rs.getString("productName"));
//                   ob.setAddress(rs.getString("address"));
//                                        ob.setCompanyCountry(rs.getString("companyCountry"));
//                                        ob.setEmail(rs.getString("cemail"));
//
//
//                         ob.setContactNo(rs.getLong("ccontact"));
                   productList.add(ob);
      }
      }
      catch(Exception ex)
      {
          System.out.println(ex);
      }
      return productList;
  }
        public List searchProductId(String abc)
        {
             List<ProductsSuppliers> productList=new ArrayList();
      try{
          Connection con=DBConnection.getConnection();
		String sql="SELECT * FROM products WHERE productName = ?";
 PreparedStatement pst=con.prepareStatement(sql);
             pst.setString(1,abc);
   //                       pst.setInt(2,xyz);
             ResultSet rs=pst.executeQuery();

//String sql="SELECT p.pid,productName,companyName,productCatagory,productMfd,productQuantity,productPrice\n" +
//"FROM products AS p\n" +
//"INNER JOIN suppliers AS s\n" +
//"ON p.sid=s.sid\n" +
//"WHERE p.pid= ?\n" +
//"GROUP BY p.pid";
//              Statement st=con.createStatement();
//                
//               ResultSet rs=st.executeQuery(sql);
      while(rs.next())
      {
           ProductsSuppliers  ob=new ProductsSuppliers();
          ob.setPid(rs.getInt("pid"));
                              ob.setProductName(rs.getString("productName"));
                   ob.setProductCategory(rs.getString("productCatagory"));
                                        ob.setPrice(rs.getDouble("productPrice"));
                                        ob.setQuantity(rs.getInt("productQuantity"));


                         ob.setMfgYear(rs.getDate("productMfd"));
                   productList.add(ob);
      }
      }
      catch(Exception ex)
      {
          System.out.println(ex);
      }
      return productList;
  
        }
        public ProductsSuppliers fetchDetails(int id)
  {              ProductsSuppliers ob=new ProductsSuppliers();

             try{
          Connection con=DBConnection.getConnection();
		String sql="SELECT p.pid,productName,companyName,productCatagory,productMfd,productQuantity,productPrice\n" +
"FROM products AS p\n" +
"INNER JOIN suppliers AS s\n" +
"ON p.sid=s.sid\n" +
"WHERE p.pid= ?\n" +
"GROUP BY p.pid";
             // Statement st=con.createStatement();
                
//               ResultSet rs=st.executeQuery(sql);
             PreparedStatement pst=con.prepareStatement(sql);
             pst.setInt(1,id);
                ResultSet rs=pst.executeQuery();
                while(rs.next())
      {
          ob.setPid(rs.getInt("Pid"));
                    ob.setProductName(rs.getString("productName"));
                                        ob.setSupplierName(rs.getString("companyName"));
                                                            ob.setProductCategory(rs.getString("productCatagory"));                                                    
                                                            ob.setMfgYear(rs.getDate("productMfd"));                                                    
                                                           //ob.setQuantity(id);
                         ob.setPrice(rs.getDouble("productPrice"));
      }
                rs.close();
      }
      catch(Exception ex)
      {
          System.out.println(ex);
      }
                 return ob;
  }
        public void orderInfoId(SalesProduct sp)
        {
	try {
	Connection con=DBConnection.getConnection();
		String sql="insert into orders(orderDate,orderStatus,customerId) values (?,?,?)";
                PreparedStatement st=con.prepareStatement(sql);
                               st.setDate(1, new java.sql.Date(sp.getDos().getTime()));

                st.setString(2,sp.getOrderStatus());
              
                st.setInt(3, sp.getCid());
             st.executeUpdate();
                con.close();
        }
	
	catch(Exception ex)
	{
		System.out.println(ex);
	}
        }
        public int orderGetId(SalesProduct sp)
        {
            int count=0;
        try {
	Connection con=DBConnection.getConnection();
		String sql="select * from orders where customerId = ? and orderDate = ? ";
                PreparedStatement pst=con.prepareStatement(sql);
                pst.setInt(1,sp.getCid());
                pst.setDate(2, new java.sql.Date(sp.getDos().getTime()));
               ResultSet rs=pst.executeQuery();
               if(rs.next())
			{
                                count=rs.getInt("oid");
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
        public int getQty(int id,Connection con)
{
    int qty=0;
    try{
		String sql="select productQuantity from products where pid=?";
                
             PreparedStatement pst=con.prepareStatement(sql);
             pst.setInt(1,id);
                ResultSet rs=pst.executeQuery();
                while(rs.next())
      {
          qty=rs.getInt("productQuantity");
                   
      }
    }
    catch(Exception ex)
    {
        System.out.println(ex);
    }
    return qty;
}
public void updateQty(int id,int newQty,Connection con)
{
    try {
		String sql="update products set productQuantity=? where pid=?";
			PreparedStatement pst=con.prepareStatement(sql);
                       
                        pst.setInt(1, newQty);
                        pst.setInt(2, id);

               pst.execute();
        }
	
	catch(Exception ex)
	{
		System.out.println(ex);
	}
}

public List fetchPayment()
{
     List<Transaction> paymentList1=new ArrayList();
      try{
          Connection con=DBConnection.getConnection();
		String sql="select * from transactions";
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery(sql);
      while(rs.next())
      {
          Transaction ob1=new Transaction();                    
          ob1.setTid(rs.getInt("tid"));
                    ob1.setOid(rs.getInt("oid"));
                    ob1.setUid(rs.getInt("uid"));
                    ob1.setPaidDate(rs.getDate("paidDate"));
                    ob1.setPaidTime(rs.getTime("paidTime"));
                  
                                         ob1.setPaidAmount(rs.getDouble("paidAmount"));
                     paymentList1.add(ob1);
      }
      }
      catch(Exception ex)
      {
          System.out.println(ex);
      }
      return paymentList1;
}
public List fetchPaymentDate(DateLoad dl)
{
    
     List<Transaction> paymentList1=new ArrayList();
      try{
          Connection con=DBConnection.getConnection();
		String sql="SELECT * FROM transactions WHERE paidDate BETWEEN ? AND ? ";
                 PreparedStatement pst=con.prepareStatement(sql);
             pst.setDate(1,new java.sql.Date(dl.getStartDate().getTime()));
             pst.setDate(2,new java.sql.Date(dl.getEndDate().getTime()));   
//                       pst.setInt(2,xyz);
             ResultSet rs=pst.executeQuery();
      while(rs.next())
      {
          Transaction ob1=new Transaction();                    
          ob1.setTid(rs.getInt("tid"));
                    ob1.setOid(rs.getInt("oid"));
                    ob1.setUid(rs.getInt("uid"));
                    ob1.setPaidDate(rs.getDate("paidDate"));
                    ob1.setPaidTime(rs.getTime("paidTime"));
                  
                                         ob1.setPaidAmount(rs.getDouble("paidAmount"));
                     paymentList1.add(ob1);
      }
      }
      catch(Exception ex)
      {
          System.out.println(ex);
      }
      return paymentList1;
}
public List verifyCustomer(int oid)
{
      List<customers> customerList1=new ArrayList();

    String uname="";
      try{
          Connection con=DBConnection.getConnection();
		String sql="SELECT o.oid,customerName,customerAddress,Age\n" +
"FROM orders AS o\n" +
"INNER JOIN customers AS c\n" +
"ON o.customerId=c.customerId\n" +
"WHERE o.oid = ?\n" +                
"GROUP BY o.oid;";
                PreparedStatement pst=con.prepareStatement(sql);
                               System.out.println(oid);

             pst.setInt(1,oid);
   //                       pst.setInt(2,xyz);
             ResultSet rs=pst.executeQuery();
                while(rs.next())
      {                     
          customers s=new customers();
                     s.setCustomerName(rs.getString("customerName"));
                   s.setCustomerAddress(rs.getString("customerAddress"));
                   s.setAge(rs.getInt("Age"));
                                      
                                          customerList1.add(s);

      }
      }
      catch(Exception ex)
      {
          System.out.println(ex);
      }
      return customerList1;
}
public String verifyUser(int uid)
{
    String uname="";
    try{
          Connection con=DBConnection.getConnection();
		String sql="select username from users where uid=?";
                PreparedStatement pst=con.prepareStatement(sql);
               System.out.println(uid);
             pst.setInt(1,uid);
                ResultSet rs=pst.executeQuery();
                while(rs.next())
      {
                     uname=rs.getString("username");
      }
      }
      catch(Exception ex)
      {
          System.out.println(ex);
      }
      return uname;
}
}
