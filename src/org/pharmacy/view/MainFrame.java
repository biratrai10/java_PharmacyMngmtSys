/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pharmacy.view;
import java.awt.CardLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.color.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import org.pharmacy.controller.*;
import org.pharmacy.controller.PharmacyDAO;
import org.pharmacy.model.*;
import org.pharmacy.util.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 *
 * @author DELL
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form mainFrame
     */
        CardLayout c;
        DefaultTableModel model,model1,model2;    
        private String pname,pcatagory;
        private Date mfdyear;
        private String searchValue,searchValue1,compName,address,compAddress,email,mfd,password;
        private double ppu;
        private int productid,compid,pquantity;
        private long contactno;
       
        int status=1;
    public MainFrame() {
        initComponents();
        
        model=new DefaultTableModel(null, new String[]{"Product Id","Product Name","Company Name","Product Category","Mfd Year","Quantity","Price per unit"});
                model1=new DefaultTableModel(null, new String[]{"Supplier Id","Company Name","Address","Company Country","Email","Contact No"});
        model2=new DefaultTableModel(null, new String[]{"Transaction Id","Customer Name","Customer Address","Age","User Approved","Transaction Date","Transaction Time","Amount"});

                jTable_View.setModel(model);
        jTable_View1.setModel(model);
                jTable_View2.setModel(model1);
                jTable_SalesTransaction.setModel(model2);
loadData();

                          c= (CardLayout)(jPanel_Main.getLayout());
                          
                        
    }
   
public void clearTable()
    {
        int count=jTable_View.getRowCount();
        for(int i=0;i<count;i++)
        {
            model.removeRow(0);

        }
    }
public void clearTable1()
    {
        int count=jTable_View2.getRowCount();
        for(int i=0;i<count;i++)
        {
            model1.removeRow(0);

        }
    }
public void clearTableSalesTransaction()
{
      int count=jTable_SalesTransaction.getRowCount();
        for(int i=0;i<count;i++)
        {
            model2.removeRow(0);
        }
}
public void loadData()
    {
        PharmacyDAO pDAO=new PharmacyDAO();
List<ProductsSuppliers> productList=pDAO.fetchData();
clearTable();       
for(ProductsSuppliers p:productList)
        {
            model.addRow(new Object[]{p.getPid(),p.getProductName(),p.getSupplierName(),p.getProductCategory(),p.getMfgYear(),p.getQuantity(),p.getPrice()});
        }
    }
public void loadData1()
    {
        PharmacyDAO pDAO=new PharmacyDAO();
List<Suppliers> suppliersList=pDAO.fetchData1();
clearTable1();       
for(Suppliers s:suppliersList)
        {
            model1.addRow(new Object[]{s.getSid(),s.getCompanyName(),s.getAddress(),s.getCompanyCountry(),s.getEmail(),s.getContactNo()});
        }
    }

//HERE is the combobox data retriving part
public void loadComp()
{
    
   // String comp[] ={"Ram","Hari","Shyam"};
    //for(int i=0;i<3;i++)
   // {
 //jComboBox_Comp.addItem(comp[i]);
  //  }
   PharmacyDAO pDAO=new PharmacyDAO();
List<Suppliers> suppliersList=pDAO.fetchData1();
clearTable1();       
for(Suppliers s:suppliersList)
        {
jComboBox_Comp.addItem(s.getCompanyName());
        }
  
    }
public void comboboxclear()
{
    jComboBox_Comp.removeAllItems();

}
        
 public void searchData()
    {
         PharmacyDAO pDAO=new PharmacyDAO();
List<ProductsSuppliers> productList=pDAO.searchList(searchValue);
clearTable();       
for(ProductsSuppliers  p:productList)
        {
            model.addRow(new Object[]{p.getPid(),p.getProductName(),p.getSupplierName(),p.getProductCategory(),p.getMfgYear(),p.getQuantity(),p.getPrice()});
        }
    }
 public void searchData1()
    {
         PharmacyDAO pDAO=new PharmacyDAO();
List<Suppliers> suppliersList=pDAO.searchList1(searchValue1);
clearTable1();       
for(Suppliers s:suppliersList)
        {
            model1.addRow(new Object[]{s.getSid(),s.getCompanyName(),s.getAddress(),s.getCompanyCountry(),s.getEmail(),s.getContactNo()});
        }
    }
 public void searchData2()
    {
         PharmacyDAO pDAO=new PharmacyDAO();
List<Suppliers> suppliersList=pDAO.searchCompanyId(compName);
//clearTable1();       
for(Suppliers s:suppliersList)
        {
           compid=s.getSid();
           System.out.println(compid);
        }
    }
 public void clearFields(){
     jTextField_Cname.setText("");
                    jTextField_Caddress.setText("");
          jTextField_Ccountry.setText("");
          jTextField_Cemail.setText("");

               jTextField_Ccontact.setText("");
 }
 //selecting data from list table
 public void clickData()
  {
      int row=jTable_View2.getSelectedRow();
        compid=Integer.parseInt(jTable_View2.getValueAt(row,0).toString());
                //sid=id;
        compName=jTable_View2.getValueAt(row,1).toString();
      address=jTable_View2.getValueAt(row,2).toString();
       compAddress=jTable_View2.getValueAt(row,3).toString();
      email=jTable_View2.getValueAt(row,4).toString();
     //  Grade=Integer.parseInt(jTable_View.getValueAt(row,6).toString());
          //    Batch=Integer.parseInt(jTable_View.getValueAt(row,7).toString());
                     //Due=Double.parseDouble(jTable_View.getValueAt(row,8).toString());
              contactno=Long.parseLong(jTable_View2.getValueAt(row,5).toString());  
      companyutil.cid=compid;
                  companyutil.cname=compName;
            companyutil.address=address;
            companyutil.caddress=compAddress;
            companyutil.email=email;
            companyutil.contactno=contactno;
           
  }
 public void clickDataMedic()
 {
     int row=jTable_View1.getSelectedRow();
        productid=Integer.parseInt(jTable_View1.getValueAt(row,0).toString());
                //sid=id;
        pname=jTable_View1.getValueAt(row,1).toString();
       compName=jTable_View1.getValueAt(row,2).toString();
             pcatagory=jTable_View1.getValueAt(row,3).toString();

      mfd=jTable_View1.getValueAt(row,4).toString();
      pquantity=Integer.parseInt(jTable_View.getValueAt(row,5).toString());
          //    Batch=Integer.parseInt(jTable_View.getValueAt(row,7).toString());
                     ppu=Double.parseDouble(jTable_View.getValueAt(row,6).toString());
                    
 }
 public void setMedicUpdate()
 {
     jTextField_ProductName2.setText(pname);
     jTextField_ProductCatagory2.setText(pcatagory);
     jTextField_mfd3.setText(mfd);
     jTextField_Quantity2.setText(pquantity+"");
     jTextField_Price2.setText(ppu+"");
     jTextField_pcompany2.setText(compName);
 }
 public void clickCompDetails()
 {
     jTextField_CompanyName1.setText(compName);
    jTextField_Address1.setText(address);
     jTextField_Country1.setText(compAddress);
     jTextField_Email1.setText(email);
     jTextField_ContactNo1.setText(contactno+"");
 }
 public void  clearFields1()
 {
      jTextField_CompanyName1.setText("");
                    jTextField_Address1.setText("");
          jTextField_Country1.setText("");
          jTextField_Email1.setText("");
               jTextField_ContactNo1.setText("");
              compid=0; 
 compName="";
         address="";
         compAddress="";
                 email="";
                 contactno=0;
 }
 public void clearMedicFrame()
 {
      jTextField_ProductName1.setText("");
     jTextField_ProductCatagory1.setText("");
           
jTextField_ProductQuantity.setText("");
jTextField_ppu.setText("");
 }

public void passwordMD5()
{
    
      try
                          {
                               String pssword=password;
   MessageDigest md= MessageDigest.getInstance("MD5");
   byte[] hashInBytes =md.digest(pssword.getBytes(StandardCharsets.UTF_8));
   
   StringBuilder sb= new StringBuilder();
   for(byte b : hashInBytes)
   {
       sb.append(String.format("%02x", b));
       //System.out.println(sb.toString());
       password=sb.toString();
   }
  // System.out.println(password);
                          }
                          catch(Exception e)
                          {
                              System.out.println(e);
                          }
  ///Encrption
}
//public void NewCustomerEntry()
//{
//    customerDetails cd=new customerDetails();
//        jDesktopPane1.add(cd);
//        cd.setVisible(true);
//}
public void NewSalesEntry()
{
    SalesBillingOp cd=new SalesBillingOp();
        jDesktopPane1.add(cd);
        cd.setVisible(true);
}
public void loadTransaction()
{
      PharmacyDAO pDAO=new PharmacyDAO();
List<Transaction> paymentList=pDAO.fetchPayment();
clearTableSalesTransaction(); 
int i=0;
int k=0;
String uname="";
String pname="";
for(Transaction p:paymentList)
        {
                            
            model2.addRow(new Object[]{p.getTid(),p.getOid(),p.getUid(),p.getPaidDate(),p.getPaidTime(),p.getPaidAmount()});
 int oid=Integer.parseInt(jTable_SalesTransaction.getValueAt(i,1).toString());
 int uid=Integer.parseInt(jTable_SalesTransaction.getValueAt(i,2).toString());
//StudentDAO u=new StudentDAO();


    jTable_SalesTransaction.setValueAt(p.getTid(), i, 1);
     jTable_SalesTransaction.setValueAt(p.getPaidDate(), i, 5);
     jTable_SalesTransaction.setValueAt(p.getPaidTime(), i, 6);
   jTable_SalesTransaction.setValueAt(p.getPaidAmount(), i, 7);
   

List<customers> customerList=pDAO.verifyCustomer(oid);
for(customers s:customerList)
{
    
       jTable_SalesTransaction.setValueAt(s.getCustomerName(), k, 1);
         jTable_SalesTransaction.setValueAt(s.getCustomerAddress(), k, 2);
  jTable_SalesTransaction.setValueAt(s.getAge(), k, 3);

k++;

}

    
uname=pDAO.verifyUser(uid);
// jTable_Transaction.setValueAt(uname, i, 2);
  jTable_SalesTransaction.setValueAt(uname, i, 4);
      grandTotal();
 
         i++;
        }
}
public void grandTotal()
{
    int count=  jTable_SalesTransaction.getRowCount();
     double gtotal=0;
     for(int i=0;i<count;i++)
     {
     double total=Double.parseDouble(  jTable_SalesTransaction.getValueAt(i,7).toString());

    gtotal+=total;
     }
     jLabel_cash.setText(gtotal+"");
}
public void ChoiceTransaction()
{
   Date sdate=jDateChooser_start.getDate();
   Date edate=jDateChooser_end.getDate();
   int chck=0;
      DateLoad dl=new DateLoad();

      if(this.jDateChooser_start.getDate()!=null)
         {
   dl.setStartDate(sdate);

         }
         else
         {
                      chck++;
                    JOptionPane.showMessageDialog(null,"Choice Date");

          }
         if(this.jDateChooser_end.getDate()!=null)
         {
   dl.setEndDate(edate);

         }
         else
         {
                      chck++;
                    JOptionPane.showMessageDialog(null,"Choice Date");

          }
           if(chck==0)
           {
    PharmacyDAO pDAO=new PharmacyDAO();
List<Transaction> paymentList=pDAO.fetchPaymentDate(dl);
clearTableSalesTransaction(); 
int i=0;
int k=0;
String uname="";
String pname="";
for(Transaction p:paymentList)
        {
                            
            model2.addRow(new Object[]{p.getTid(),p.getOid(),p.getUid(),p.getPaidDate(),p.getPaidTime(),p.getPaidAmount()});
 int oid=Integer.parseInt(jTable_SalesTransaction.getValueAt(i,1).toString());
 int uid=Integer.parseInt(jTable_SalesTransaction.getValueAt(i,2).toString());
//StudentDAO u=new StudentDAO();


    jTable_SalesTransaction.setValueAt(p.getTid(), i, 1);
     jTable_SalesTransaction.setValueAt(p.getPaidDate(), i, 5);
     jTable_SalesTransaction.setValueAt(p.getPaidTime(), i, 6);
   jTable_SalesTransaction.setValueAt(p.getPaidAmount(), i, 7);
   

List<customers> customerList=pDAO.verifyCustomer(oid);
for(customers s:customerList)
{
    
       jTable_SalesTransaction.setValueAt(s.getCustomerName(), k, 1);
         jTable_SalesTransaction.setValueAt(s.getCustomerAddress(), k, 2);
  jTable_SalesTransaction.setValueAt(s.getAge(), k, 3);

k++;

}

    
uname=pDAO.verifyUser(uid);
// jTable_Transaction.setValueAt(uname, i, 2);
  jTable_SalesTransaction.setValueAt(uname, i, 4);
      grandTotal();
 
         i++;
        }
}
}
public void generateBill(){
    try {
//	FileOutputStream fout=new FileOutputStream("e:/pharmacy/employee.txt");
//	DataOutputStream dout=new DataOutputStream(fout);
//	
//	int i;
//	Scanner input=new Scanner(System.in);
//	for(i=0;i<5;i++)
//	{
//		System.out.println("Enter id,name,salary");
//		int id=input.nextInt();
//		String name=input.next();
//		double salary=input.nextDouble();
//		dout.writeInt(id);
//		dout.writeUTF(name);
//		dout.writeDouble(salary);
//	}
//	dout.close();
//	fout.close();
	
	
}
catch(Exception ex)
{
	System.out.println(ex);
}
	
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel_Main = new javax.swing.JPanel();
        jPanel_First = new javax.swing.JPanel();
        jButton_Med = new javax.swing.JButton();
        jButton_Comp = new javax.swing.JButton();
        jButton_Sales = new javax.swing.JButton();
        jButton_ViewSales = new javax.swing.JButton();
        jButton_Pass = new javax.swing.JButton();
        jButton_CreateUser = new javax.swing.JButton();
        jButton_LogOut = new javax.swing.JButton();
        jTextField_Search = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_View = new javax.swing.JTable();
        jPanel_Medic = new javax.swing.JPanel();
        jButton_InsertMedic = new javax.swing.JButton();
        jButton_UpdateMedic = new javax.swing.JButton();
        jButton_EraseMedic = new javax.swing.JButton();
        jButton_Back = new javax.swing.JButton();
        jTextField_Search1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_View1 = new javax.swing.JTable();
        jPanel_Suppliers = new javax.swing.JPanel();
        jButton_InsertComp = new javax.swing.JButton();
        jButton_UpdateComp = new javax.swing.JButton();
        jButton_EraseComp = new javax.swing.JButton();
        jButton_Back1 = new javax.swing.JButton();
        jTextField_Search2 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_View2 = new javax.swing.JTable();
        jPanel_InsetMedic = new javax.swing.JPanel();
        jButton_InsertMedic1 = new javax.swing.JButton();
        jButton_Back2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField_ppu = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField_ProductName1 = new javax.swing.JTextField();
        jTextField_ProductCatagory1 = new javax.swing.JTextField();
        jTextField_ProductQuantity = new javax.swing.JTextField();
        jDateChooser_mfd = new com.toedter.calendar.JDateChooser();
        jComboBox_Comp = new javax.swing.JComboBox<>();
        jPanel_InsertCompany = new javax.swing.JPanel();
        jButton_InsertCompany = new javax.swing.JButton();
        jButton_Back3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField_Ccontact = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField_Cname = new javax.swing.JTextField();
        jTextField_Ccountry = new javax.swing.JTextField();
        jTextField_Cemail = new javax.swing.JTextField();
        jTextField_Caddress = new javax.swing.JTextField();
        jPanel_UpdateMedic = new javax.swing.JPanel();
        jButton_UpdateMedic2 = new javax.swing.JButton();
        jButton_Back4 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField_Price2 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField_ProductName2 = new javax.swing.JTextField();
        jTextField_pcompany2 = new javax.swing.JTextField();
        jTextField_Quantity2 = new javax.swing.JTextField();
        jTextField_ProductCatagory2 = new javax.swing.JTextField();
        jTextField_mfd3 = new javax.swing.JTextField();
        jPanel_UpdateComp = new javax.swing.JPanel();
        jButton_UpdateComp3 = new javax.swing.JButton();
        jButton_Back5 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTextField_Country1 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextField_CompanyName1 = new javax.swing.JTextField();
        jTextField_Address1 = new javax.swing.JTextField();
        jTextField_Email1 = new javax.swing.JTextField();
        jTextField_ContactNo1 = new javax.swing.JTextField();
        jPanel_CreateUser = new javax.swing.JPanel();
        jButton_InsertUser = new javax.swing.JButton();
        jButton_Back6 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jTextField_Contact = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jTextField_Username = new javax.swing.JTextField();
        jTextField_Address = new javax.swing.JTextField();
        jTextField_Email = new javax.swing.JTextField();
        jTextField_Name = new javax.swing.JTextField();
        jPasswordField_Password = new javax.swing.JPasswordField();
        jLabel35 = new javax.swing.JLabel();
        jPanel_ChangePassword = new javax.swing.JPanel();
        jButton_InsertUser1 = new javax.swing.JButton();
        jButton_Back7 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jTextField_Username1 = new javax.swing.JTextField();
        jPasswordField_RePassword2 = new javax.swing.JPasswordField();
        jLabel42 = new javax.swing.JLabel();
        jPasswordField_NewPassword2 = new javax.swing.JPasswordField();
        jPasswordField_Password3 = new javax.swing.JPasswordField();
        jPasswordField_Password4 = new javax.swing.JPasswordField();
        jPanel_SalesTransaction = new javax.swing.JPanel();
        jButton_Load = new javax.swing.JButton();
        jButton_Back8 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_SalesTransaction = new javax.swing.JTable();
        jLabel38 = new javax.swing.JLabel();
        jLabel_cash = new javax.swing.JLabel();
        jDateChooser_start = new com.toedter.calendar.JDateChooser();
        jDateChooser_end = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton_Load1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pharmacy ");

        jDesktopPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel_Main.setBackground(new java.awt.Color(204, 204, 204));
        jPanel_Main.setLayout(new java.awt.CardLayout());

        jPanel_First.setBackground(new java.awt.Color(20, 130, 220));

        jButton_Med.setBackground(new java.awt.Color(255, 255, 0));
        jButton_Med.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_Med.setForeground(new java.awt.Color(102, 0, 102));
        jButton_Med.setText("Medicines");
        jButton_Med.setBorder(null);
        jButton_Med.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Med.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_MedActionPerformed(evt);
            }
        });

        jButton_Comp.setBackground(new java.awt.Color(255, 255, 0));
        jButton_Comp.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_Comp.setForeground(new java.awt.Color(102, 0, 102));
        jButton_Comp.setText("Suppliers");
        jButton_Comp.setBorder(null);
        jButton_Comp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Comp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CompActionPerformed(evt);
            }
        });

        jButton_Sales.setBackground(new java.awt.Color(255, 255, 0));
        jButton_Sales.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_Sales.setForeground(new java.awt.Color(102, 0, 102));
        jButton_Sales.setText("Sales");
        jButton_Sales.setBorder(null);
        jButton_Sales.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Sales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SalesActionPerformed(evt);
            }
        });

        jButton_ViewSales.setBackground(new java.awt.Color(255, 255, 0));
        jButton_ViewSales.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_ViewSales.setForeground(new java.awt.Color(102, 0, 102));
        jButton_ViewSales.setText("View Sales");
        jButton_ViewSales.setBorder(null);
        jButton_ViewSales.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_ViewSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ViewSalesActionPerformed(evt);
            }
        });

        jButton_Pass.setBackground(new java.awt.Color(255, 255, 0));
        jButton_Pass.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_Pass.setForeground(new java.awt.Color(102, 0, 102));
        jButton_Pass.setText("Change Password");
        jButton_Pass.setBorder(null);
        jButton_Pass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PassActionPerformed(evt);
            }
        });

        jButton_CreateUser.setBackground(new java.awt.Color(255, 255, 0));
        jButton_CreateUser.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_CreateUser.setForeground(new java.awt.Color(102, 0, 102));
        jButton_CreateUser.setText("Create User");
        jButton_CreateUser.setBorder(null);
        jButton_CreateUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_CreateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CreateUserActionPerformed(evt);
            }
        });

        jButton_LogOut.setBackground(new java.awt.Color(255, 255, 0));
        jButton_LogOut.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_LogOut.setForeground(new java.awt.Color(102, 0, 102));
        jButton_LogOut.setText("Log Out");
        jButton_LogOut.setBorder(null);
        jButton_LogOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_LogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LogOutActionPerformed(evt);
            }
        });

        jTextField_Search.setBackground(new java.awt.Color(240, 240, 240));
        jTextField_Search.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField_Search.setForeground(new java.awt.Color(51, 51, 51));
        jTextField_Search.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField_Search.setText("Search.....");
        jTextField_Search.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        jTextField_Search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_SearchMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField_SearchMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField_SearchMousePressed(evt);
            }
        });
        jTextField_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_SearchActionPerformed(evt);
            }
        });
        jTextField_Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_SearchKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_SearchKeyTyped(evt);
            }
        });

        jTable_View.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTable_View.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_View.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_ViewMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_View);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 432, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_FirstLayout = new javax.swing.GroupLayout(jPanel_First);
        jPanel_First.setLayout(jPanel_FirstLayout);
        jPanel_FirstLayout.setHorizontalGroup(
            jPanel_FirstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FirstLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel_FirstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_FirstLayout.createSequentialGroup()
                        .addGroup(jPanel_FirstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_CreateUser, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_LogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_FirstLayout.createSequentialGroup()
                                .addGroup(jPanel_FirstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton_Pass, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_ViewSales, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_Sales, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_Comp, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_Med, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_FirstLayout.createSequentialGroup()
                        .addComponent(jTextField_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 898, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(187, 187, 187))))
        );
        jPanel_FirstLayout.setVerticalGroup(
            jPanel_FirstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_FirstLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jTextField_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(jPanel_FirstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_FirstLayout.createSequentialGroup()
                        .addComponent(jButton_Med, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Comp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Sales, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_ViewSales, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Pass, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_CreateUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton_LogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jPanel_Main.add(jPanel_First, "pnlCard");

        jPanel_Medic.setBackground(new java.awt.Color(20, 130, 220));

        jButton_InsertMedic.setBackground(new java.awt.Color(255, 255, 0));
        jButton_InsertMedic.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_InsertMedic.setForeground(new java.awt.Color(102, 0, 102));
        jButton_InsertMedic.setText("Insert Medicines");
        jButton_InsertMedic.setBorder(null);
        jButton_InsertMedic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_InsertMedic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_InsertMedicActionPerformed(evt);
            }
        });

        jButton_UpdateMedic.setBackground(new java.awt.Color(255, 255, 0));
        jButton_UpdateMedic.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_UpdateMedic.setForeground(new java.awt.Color(102, 0, 102));
        jButton_UpdateMedic.setText("Update Medicines");
        jButton_UpdateMedic.setBorder(null);
        jButton_UpdateMedic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_UpdateMedic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_UpdateMedicActionPerformed(evt);
            }
        });

        jButton_EraseMedic.setBackground(new java.awt.Color(255, 255, 0));
        jButton_EraseMedic.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_EraseMedic.setForeground(new java.awt.Color(102, 0, 102));
        jButton_EraseMedic.setText("Erase Medicines");
        jButton_EraseMedic.setBorder(null);
        jButton_EraseMedic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_EraseMedic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EraseMedicActionPerformed(evt);
            }
        });

        jButton_Back.setBackground(new java.awt.Color(255, 255, 0));
        jButton_Back.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_Back.setForeground(new java.awt.Color(102, 0, 102));
        jButton_Back.setText("BACK");
        jButton_Back.setBorder(null);
        jButton_Back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BackActionPerformed(evt);
            }
        });

        jTextField_Search1.setBackground(new java.awt.Color(240, 240, 240));
        jTextField_Search1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField_Search1.setForeground(new java.awt.Color(51, 51, 51));
        jTextField_Search1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField_Search1.setText("Search.....");
        jTextField_Search1.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        jTextField_Search1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_Search1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField_Search1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField_Search1MousePressed(evt);
            }
        });
        jTextField_Search1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Search1ActionPerformed(evt);
            }
        });
        jTextField_Search1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_Search1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_Search1KeyTyped(evt);
            }
        });

        jTable_View1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTable_View1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_View1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_View1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_View1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 898, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel_MedicLayout = new javax.swing.GroupLayout(jPanel_Medic);
        jPanel_Medic.setLayout(jPanel_MedicLayout);
        jPanel_MedicLayout.setHorizontalGroup(
            jPanel_MedicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MedicLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel_MedicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_MedicLayout.createSequentialGroup()
                        .addGroup(jPanel_MedicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_UpdateMedic, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_EraseMedic, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_InsertMedic, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_MedicLayout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(jTextField_Search1, javax.swing.GroupLayout.PREFERRED_SIZE, 898, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(153, 153, 153))
        );
        jPanel_MedicLayout.setVerticalGroup(
            jPanel_MedicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MedicLayout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(jButton_InsertMedic, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jButton_UpdateMedic, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton_EraseMedic, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
            .addGroup(jPanel_MedicLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jTextField_Search1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(186, Short.MAX_VALUE))
        );

        jPanel_Main.add(jPanel_Medic, "pnlCard1");

        jPanel_Suppliers.setBackground(new java.awt.Color(20, 130, 220));
        jPanel_Suppliers.setPreferredSize(new java.awt.Dimension(1367, 710));

        jButton_InsertComp.setBackground(new java.awt.Color(255, 255, 0));
        jButton_InsertComp.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_InsertComp.setForeground(new java.awt.Color(102, 0, 102));
        jButton_InsertComp.setText("Insert Company");
        jButton_InsertComp.setBorder(null);
        jButton_InsertComp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_InsertComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_InsertCompActionPerformed(evt);
            }
        });

        jButton_UpdateComp.setBackground(new java.awt.Color(255, 255, 0));
        jButton_UpdateComp.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_UpdateComp.setForeground(new java.awt.Color(102, 0, 102));
        jButton_UpdateComp.setText("Update Company");
        jButton_UpdateComp.setBorder(null);
        jButton_UpdateComp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_UpdateComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_UpdateCompActionPerformed(evt);
            }
        });

        jButton_EraseComp.setBackground(new java.awt.Color(255, 255, 0));
        jButton_EraseComp.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_EraseComp.setForeground(new java.awt.Color(102, 0, 102));
        jButton_EraseComp.setText("Erase Company");
        jButton_EraseComp.setBorder(null);
        jButton_EraseComp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_EraseComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EraseCompActionPerformed(evt);
            }
        });

        jButton_Back1.setBackground(new java.awt.Color(255, 255, 0));
        jButton_Back1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_Back1.setForeground(new java.awt.Color(102, 0, 102));
        jButton_Back1.setText("BACK");
        jButton_Back1.setBorder(null);
        jButton_Back1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Back1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Back1ActionPerformed(evt);
            }
        });

        jTextField_Search2.setBackground(new java.awt.Color(240, 240, 240));
        jTextField_Search2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField_Search2.setForeground(new java.awt.Color(51, 51, 51));
        jTextField_Search2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField_Search2.setText("Search.....");
        jTextField_Search2.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        jTextField_Search2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_Search2MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField_Search2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextField_Search2MousePressed(evt);
            }
        });
        jTextField_Search2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Search2ActionPerformed(evt);
            }
        });
        jTextField_Search2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_Search2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_Search2KeyTyped(evt);
            }
        });

        jTable_View2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTable_View2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_View2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_View2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable_View2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 898, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel_SuppliersLayout = new javax.swing.GroupLayout(jPanel_Suppliers);
        jPanel_Suppliers.setLayout(jPanel_SuppliersLayout);
        jPanel_SuppliersLayout.setHorizontalGroup(
            jPanel_SuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SuppliersLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel_SuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_Back1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_SuppliersLayout.createSequentialGroup()
                        .addGroup(jPanel_SuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_EraseComp, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_InsertComp, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_UpdateComp, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel_SuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_Search2)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(162, Short.MAX_VALUE))
        );
        jPanel_SuppliersLayout.setVerticalGroup(
            jPanel_SuppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_SuppliersLayout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(jButton_InsertComp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_UpdateComp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_EraseComp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Back1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_SuppliersLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jTextField_Search2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
        );

        jPanel_Main.add(jPanel_Suppliers, "pnlCard2");

        jPanel_InsetMedic.setBackground(new java.awt.Color(20, 130, 220));

        jButton_InsertMedic1.setBackground(new java.awt.Color(255, 255, 0));
        jButton_InsertMedic1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_InsertMedic1.setForeground(new java.awt.Color(102, 0, 102));
        jButton_InsertMedic1.setText("Insert");
        jButton_InsertMedic1.setBorder(null);
        jButton_InsertMedic1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_InsertMedic1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_InsertMedic1ActionPerformed(evt);
            }
        });

        jButton_Back2.setBackground(new java.awt.Color(255, 255, 0));
        jButton_Back2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_Back2.setForeground(new java.awt.Color(102, 0, 102));
        jButton_Back2.setText("BACK");
        jButton_Back2.setBorder(null);
        jButton_Back2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Back2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Back2ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(20, 130, 220));
        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102), 2)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Product Company ");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 180, 40));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Medicine Details");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 190, 40));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 0, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Product Name");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 180, 40));

        jTextField_ppu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_ppu.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField_ppu.setText("0");
        jTextField_ppu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ppuActionPerformed(evt);
            }
        });
        jTextField_ppu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_ppuKeyTyped(evt);
            }
        });
        jPanel2.add(jTextField_ppu, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 180, 40));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 0, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Product Catagory ");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 180, 40));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 0, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Mfd Date");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 180, 40));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 0, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Quantity");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 180, 40));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 0, 102));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Price per unit");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 180, 40));

        jTextField_ProductName1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_ProductName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ProductName1ActionPerformed(evt);
            }
        });
        jTextField_ProductName1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_ProductName1KeyTyped(evt);
            }
        });
        jPanel2.add(jTextField_ProductName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 180, 40));

        jTextField_ProductCatagory1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_ProductCatagory1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ProductCatagory1ActionPerformed(evt);
            }
        });
        jTextField_ProductCatagory1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_ProductCatagory1KeyTyped(evt);
            }
        });
        jPanel2.add(jTextField_ProductCatagory1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 180, 40));

        jTextField_ProductQuantity.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_ProductQuantity.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField_ProductQuantity.setText("0");
        jTextField_ProductQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ProductQuantityActionPerformed(evt);
            }
        });
        jTextField_ProductQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_ProductQuantityKeyTyped(evt);
            }
        });
        jPanel2.add(jTextField_ProductQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, 180, 40));
        jPanel2.add(jDateChooser_mfd, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 180, 40));

        jComboBox_Comp.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jComboBox_Comp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Company" }));
        jComboBox_Comp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_CompItemStateChanged(evt);
            }
        });
        jComboBox_Comp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_CompActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox_Comp, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 370, 180, 40));

        javax.swing.GroupLayout jPanel_InsetMedicLayout = new javax.swing.GroupLayout(jPanel_InsetMedic);
        jPanel_InsetMedic.setLayout(jPanel_InsetMedicLayout);
        jPanel_InsetMedicLayout.setHorizontalGroup(
            jPanel_InsetMedicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_InsetMedicLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jButton_Back2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_InsertMedic1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
            .addGroup(jPanel_InsetMedicLayout.createSequentialGroup()
                .addGap(353, 353, 353)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_InsetMedicLayout.setVerticalGroup(
            jPanel_InsetMedicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_InsetMedicLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(124, 124, 124)
                .addGroup(jPanel_InsetMedicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Back2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_InsertMedic1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        jPanel_Main.add(jPanel_InsetMedic, "pnlCard3");

        jPanel_InsertCompany.setBackground(new java.awt.Color(20, 130, 220));

        jButton_InsertCompany.setBackground(new java.awt.Color(255, 255, 0));
        jButton_InsertCompany.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_InsertCompany.setForeground(new java.awt.Color(102, 0, 102));
        jButton_InsertCompany.setText("Insert");
        jButton_InsertCompany.setBorder(null);
        jButton_InsertCompany.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_InsertCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_InsertCompanyActionPerformed(evt);
            }
        });

        jButton_Back3.setBackground(new java.awt.Color(255, 255, 0));
        jButton_Back3.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_Back3.setForeground(new java.awt.Color(102, 0, 102));
        jButton_Back3.setText("BACK");
        jButton_Back3.setBorder(null);
        jButton_Back3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Back3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Back3ActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(20, 130, 220));
        jPanel6.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102), 2)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 0, 102));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Company Details");
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 190, 40));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 0, 102));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText(" Name");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 180, 40));

        jTextField_Ccontact.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_Ccontact.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField_Ccontact.setText("0");
        jTextField_Ccontact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_CcontactActionPerformed(evt);
            }
        });
        jPanel6.add(jTextField_Ccontact, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 180, 40));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 0, 102));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Address");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 180, 40));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 0, 102));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Country");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 180, 40));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 0, 102));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Email");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 180, 40));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 0, 102));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Contact No");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 180, 40));

        jTextField_Cname.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_Cname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_CnameActionPerformed(evt);
            }
        });
        jPanel6.add(jTextField_Cname, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 180, 40));

        jTextField_Ccountry.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_Ccountry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_CcountryActionPerformed(evt);
            }
        });
        jPanel6.add(jTextField_Ccountry, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 180, 40));

        jTextField_Cemail.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_Cemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_CemailActionPerformed(evt);
            }
        });
        jPanel6.add(jTextField_Cemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, 180, 40));

        jTextField_Caddress.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_Caddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_CaddressActionPerformed(evt);
            }
        });
        jPanel6.add(jTextField_Caddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 180, 40));

        javax.swing.GroupLayout jPanel_InsertCompanyLayout = new javax.swing.GroupLayout(jPanel_InsertCompany);
        jPanel_InsertCompany.setLayout(jPanel_InsertCompanyLayout);
        jPanel_InsertCompanyLayout.setHorizontalGroup(
            jPanel_InsertCompanyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_InsertCompanyLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jButton_Back3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_InsertCompany, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
            .addGroup(jPanel_InsertCompanyLayout.createSequentialGroup()
                .addGap(353, 353, 353)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_InsertCompanyLayout.setVerticalGroup(
            jPanel_InsertCompanyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_InsertCompanyLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(118, 118, 118)
                .addGroup(jPanel_InsertCompanyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Back3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_InsertCompany, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        jPanel_Main.add(jPanel_InsertCompany, "pnlCard4");

        jPanel_UpdateMedic.setBackground(new java.awt.Color(20, 130, 220));

        jButton_UpdateMedic2.setBackground(new java.awt.Color(255, 255, 0));
        jButton_UpdateMedic2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_UpdateMedic2.setForeground(new java.awt.Color(102, 0, 102));
        jButton_UpdateMedic2.setText("Update");
        jButton_UpdateMedic2.setBorder(null);
        jButton_UpdateMedic2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_UpdateMedic2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_UpdateMedic2ActionPerformed(evt);
            }
        });

        jButton_Back4.setBackground(new java.awt.Color(255, 255, 0));
        jButton_Back4.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_Back4.setForeground(new java.awt.Color(102, 0, 102));
        jButton_Back4.setText("BACK");
        jButton_Back4.setBorder(null);
        jButton_Back4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Back4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Back4ActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(20, 130, 220));
        jPanel7.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102), 2)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 0, 102));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Product Company ");
        jPanel7.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 180, 40));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 0, 102));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Medicine Details");
        jPanel7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 190, 40));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 0, 102));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Product Name");
        jPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 180, 40));

        jTextField_Price2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_Price2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Price2ActionPerformed(evt);
            }
        });
        jTextField_Price2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_Price2KeyTyped(evt);
            }
        });
        jPanel7.add(jTextField_Price2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 180, 40));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 0, 102));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Product Catagory ");
        jPanel7.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 180, 40));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 0, 102));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Mfd Year");
        jPanel7.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 180, 40));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 0, 102));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Quantity");
        jPanel7.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 180, 40));

        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 0, 102));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Price per unit");
        jPanel7.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 180, 40));

        jTextField_ProductName2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_ProductName2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ProductName2ActionPerformed(evt);
            }
        });
        jTextField_ProductName2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_ProductName2KeyTyped(evt);
            }
        });
        jPanel7.add(jTextField_ProductName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 180, 40));

        jTextField_pcompany2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_pcompany2.setEnabled(false);
        jTextField_pcompany2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_pcompany2ActionPerformed(evt);
            }
        });
        jPanel7.add(jTextField_pcompany2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 370, 180, 40));

        jTextField_Quantity2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_Quantity2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Quantity2ActionPerformed(evt);
            }
        });
        jTextField_Quantity2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_Quantity2KeyTyped(evt);
            }
        });
        jPanel7.add(jTextField_Quantity2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, 180, 40));

        jTextField_ProductCatagory2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_ProductCatagory2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ProductCatagory2ActionPerformed(evt);
            }
        });
        jTextField_ProductCatagory2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_ProductCatagory2KeyTyped(evt);
            }
        });
        jPanel7.add(jTextField_ProductCatagory2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 180, 40));

        jTextField_mfd3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_mfd3.setEnabled(false);
        jTextField_mfd3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_mfd3ActionPerformed(evt);
            }
        });
        jPanel7.add(jTextField_mfd3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 180, 40));

        javax.swing.GroupLayout jPanel_UpdateMedicLayout = new javax.swing.GroupLayout(jPanel_UpdateMedic);
        jPanel_UpdateMedic.setLayout(jPanel_UpdateMedicLayout);
        jPanel_UpdateMedicLayout.setHorizontalGroup(
            jPanel_UpdateMedicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_UpdateMedicLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jButton_Back4, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_UpdateMedic2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
            .addGroup(jPanel_UpdateMedicLayout.createSequentialGroup()
                .addGap(353, 353, 353)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_UpdateMedicLayout.setVerticalGroup(
            jPanel_UpdateMedicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_UpdateMedicLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(118, 118, 118)
                .addGroup(jPanel_UpdateMedicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Back4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_UpdateMedic2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        jPanel_Main.add(jPanel_UpdateMedic, "pnlCard5");

        jPanel_UpdateComp.setBackground(new java.awt.Color(20, 130, 220));

        jButton_UpdateComp3.setBackground(new java.awt.Color(255, 255, 0));
        jButton_UpdateComp3.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_UpdateComp3.setForeground(new java.awt.Color(102, 0, 102));
        jButton_UpdateComp3.setText("Update");
        jButton_UpdateComp3.setBorder(null);
        jButton_UpdateComp3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_UpdateComp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_UpdateComp3ActionPerformed(evt);
            }
        });

        jButton_Back5.setBackground(new java.awt.Color(255, 255, 0));
        jButton_Back5.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_Back5.setForeground(new java.awt.Color(102, 0, 102));
        jButton_Back5.setText("BACK");
        jButton_Back5.setBorder(null);
        jButton_Back5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Back5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Back5ActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(20, 130, 220));
        jPanel8.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102), 2)));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 0, 102));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText(" Details");
        jPanel8.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 190, 40));

        jLabel25.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 0, 102));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Company Name");
        jPanel8.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 180, 40));

        jTextField_Country1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_Country1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Country1ActionPerformed(evt);
            }
        });
        jPanel8.add(jTextField_Country1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 180, 40));

        jLabel26.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(102, 0, 102));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Address");
        jPanel8.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 180, 40));

        jLabel27.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(102, 0, 102));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Country");
        jPanel8.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 180, 40));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(102, 0, 102));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Email");
        jPanel8.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 180, 40));

        jLabel29.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 0, 102));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Contact No");
        jPanel8.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 180, 40));

        jTextField_CompanyName1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_CompanyName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_CompanyName1ActionPerformed(evt);
            }
        });
        jPanel8.add(jTextField_CompanyName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 180, 40));

        jTextField_Address1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_Address1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Address1ActionPerformed(evt);
            }
        });
        jPanel8.add(jTextField_Address1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 180, 40));

        jTextField_Email1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_Email1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Email1ActionPerformed(evt);
            }
        });
        jPanel8.add(jTextField_Email1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, 180, 40));

        jTextField_ContactNo1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_ContactNo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ContactNo1ActionPerformed(evt);
            }
        });
        jPanel8.add(jTextField_ContactNo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 180, 40));

        javax.swing.GroupLayout jPanel_UpdateCompLayout = new javax.swing.GroupLayout(jPanel_UpdateComp);
        jPanel_UpdateComp.setLayout(jPanel_UpdateCompLayout);
        jPanel_UpdateCompLayout.setHorizontalGroup(
            jPanel_UpdateCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_UpdateCompLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jButton_Back5, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_UpdateComp3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
            .addGroup(jPanel_UpdateCompLayout.createSequentialGroup()
                .addGap(353, 353, 353)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_UpdateCompLayout.setVerticalGroup(
            jPanel_UpdateCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_UpdateCompLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(118, 118, 118)
                .addGroup(jPanel_UpdateCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Back5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_UpdateComp3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        jPanel_Main.add(jPanel_UpdateComp, "pnlCard6");

        jPanel_CreateUser.setBackground(new java.awt.Color(20, 130, 220));

        jButton_InsertUser.setBackground(new java.awt.Color(255, 255, 0));
        jButton_InsertUser.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_InsertUser.setForeground(new java.awt.Color(102, 0, 102));
        jButton_InsertUser.setText("Create");
        jButton_InsertUser.setBorder(null);
        jButton_InsertUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_InsertUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_InsertUserActionPerformed(evt);
            }
        });

        jButton_Back6.setBackground(new java.awt.Color(255, 255, 0));
        jButton_Back6.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_Back6.setForeground(new java.awt.Color(102, 0, 102));
        jButton_Back6.setText("BACK");
        jButton_Back6.setBorder(null);
        jButton_Back6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Back6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Back6ActionPerformed(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(20, 130, 220));
        jPanel9.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102), 2)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 0, 102));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("User Details");
        jPanel9.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 190, 40));

        jLabel30.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 0, 102));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText(" Name");
        jPanel9.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 180, 40));

        jTextField_Contact.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_Contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ContactActionPerformed(evt);
            }
        });
        jPanel9.add(jTextField_Contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, 180, 40));

        jLabel31.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 0, 102));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Address");
        jPanel9.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 180, 40));

        jLabel32.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(102, 0, 102));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Password");
        jPanel9.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 180, 40));

        jLabel33.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(102, 0, 102));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Email");
        jPanel9.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 180, 40));

        jLabel34.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(102, 0, 102));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Contact No");
        jPanel9.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 180, 40));

        jTextField_Username.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_Username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_UsernameActionPerformed(evt);
            }
        });
        jPanel9.add(jTextField_Username, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 180, 40));

        jTextField_Address.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_Address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_AddressActionPerformed(evt);
            }
        });
        jPanel9.add(jTextField_Address, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, 180, 40));

        jTextField_Email.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_EmailActionPerformed(evt);
            }
        });
        jPanel9.add(jTextField_Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 320, 180, 40));

        jTextField_Name.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_NameActionPerformed(evt);
            }
        });
        jPanel9.add(jTextField_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 180, 40));

        jPasswordField_Password.setPreferredSize(new java.awt.Dimension(6, 28));
        jPanel9.add(jPasswordField_Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 180, 40));

        jLabel35.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(102, 0, 102));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Username");
        jPanel9.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 180, 40));

        javax.swing.GroupLayout jPanel_CreateUserLayout = new javax.swing.GroupLayout(jPanel_CreateUser);
        jPanel_CreateUser.setLayout(jPanel_CreateUserLayout);
        jPanel_CreateUserLayout.setHorizontalGroup(
            jPanel_CreateUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CreateUserLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jButton_Back6, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_InsertUser, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
            .addGroup(jPanel_CreateUserLayout.createSequentialGroup()
                .addGap(353, 353, 353)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_CreateUserLayout.setVerticalGroup(
            jPanel_CreateUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CreateUserLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(118, 118, 118)
                .addGroup(jPanel_CreateUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Back6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_InsertUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        jPanel_Main.add(jPanel_CreateUser, "pnlCard7");

        jPanel_ChangePassword.setBackground(new java.awt.Color(20, 130, 220));

        jButton_InsertUser1.setBackground(new java.awt.Color(255, 255, 0));
        jButton_InsertUser1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_InsertUser1.setForeground(new java.awt.Color(102, 0, 102));
        jButton_InsertUser1.setText("Update");
        jButton_InsertUser1.setBorder(null);
        jButton_InsertUser1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_InsertUser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_InsertUser1ActionPerformed(evt);
            }
        });

        jButton_Back7.setBackground(new java.awt.Color(255, 255, 0));
        jButton_Back7.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_Back7.setForeground(new java.awt.Color(102, 0, 102));
        jButton_Back7.setText("BACK");
        jButton_Back7.setBorder(null);
        jButton_Back7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Back7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Back7ActionPerformed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(20, 130, 220));
        jPanel10.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102), 2)));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(102, 0, 102));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Change Password");
        jPanel10.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 190, 40));

        jLabel37.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(102, 0, 102));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Re-Type Password");
        jPanel10.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 180, 40));

        jLabel39.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(102, 0, 102));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("New Password");
        jPanel10.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 180, 40));

        jTextField_Username1.setEditable(false);
        jTextField_Username1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField_Username1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Username1ActionPerformed(evt);
            }
        });
        jPanel10.add(jTextField_Username1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 180, 40));

        jPasswordField_RePassword2.setText("********");
        jPasswordField_RePassword2.setPreferredSize(new java.awt.Dimension(6, 28));
        jPasswordField_RePassword2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPasswordField_RePassword2MouseClicked(evt);
            }
        });
        jPasswordField_RePassword2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField_RePassword2ActionPerformed(evt);
            }
        });
        jPanel10.add(jPasswordField_RePassword2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 180, 40));

        jLabel42.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(102, 0, 102));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("Username");
        jPanel10.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 180, 40));

        jPasswordField_NewPassword2.setText("********");
        jPasswordField_NewPassword2.setPreferredSize(new java.awt.Dimension(6, 28));
        jPasswordField_NewPassword2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPasswordField_NewPassword2MouseClicked(evt);
            }
        });
        jPanel10.add(jPasswordField_NewPassword2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 180, 40));

        jPasswordField_Password3.setPreferredSize(new java.awt.Dimension(6, 28));
        jPanel10.add(jPasswordField_Password3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 180, 40));

        jPasswordField_Password4.setPreferredSize(new java.awt.Dimension(6, 28));
        jPanel10.add(jPasswordField_Password4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 180, 40));

        javax.swing.GroupLayout jPanel_ChangePasswordLayout = new javax.swing.GroupLayout(jPanel_ChangePassword);
        jPanel_ChangePassword.setLayout(jPanel_ChangePasswordLayout);
        jPanel_ChangePasswordLayout.setHorizontalGroup(
            jPanel_ChangePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ChangePasswordLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jButton_Back7, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_InsertUser1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
            .addGroup(jPanel_ChangePasswordLayout.createSequentialGroup()
                .addGap(353, 353, 353)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_ChangePasswordLayout.setVerticalGroup(
            jPanel_ChangePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ChangePasswordLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(118, 118, 118)
                .addGroup(jPanel_ChangePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Back7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_InsertUser1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        jPanel_Main.add(jPanel_ChangePassword, "pnlCard8");

        jPanel_SalesTransaction.setBackground(new java.awt.Color(20, 130, 220));

        jButton_Load.setBackground(new java.awt.Color(255, 255, 0));
        jButton_Load.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_Load.setForeground(new java.awt.Color(102, 0, 102));
        jButton_Load.setText("Load");
        jButton_Load.setBorder(null);
        jButton_Load.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LoadActionPerformed(evt);
            }
        });

        jButton_Back8.setBackground(new java.awt.Color(255, 255, 0));
        jButton_Back8.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_Back8.setForeground(new java.awt.Color(102, 0, 102));
        jButton_Back8.setText("BACK");
        jButton_Back8.setBorder(null);
        jButton_Back8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Back8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Back8ActionPerformed(evt);
            }
        });

        jTable_SalesTransaction.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTable_SalesTransaction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_SalesTransaction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_SalesTransactionMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable_SalesTransaction);

        jLabel38.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel38.setText("Total Collection Amount : Rs");

        jLabel_cash.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel_cash.setText("Current cash");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Start Date");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Last Date");

        jButton_Load1.setBackground(new java.awt.Color(255, 255, 0));
        jButton_Load1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_Load1.setForeground(new java.awt.Color(102, 0, 102));
        jButton_Load1.setText("Generate File");
        jButton_Load1.setBorder(null);
        jButton_Load1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Load1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Load1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_SalesTransactionLayout = new javax.swing.GroupLayout(jPanel_SalesTransaction);
        jPanel_SalesTransaction.setLayout(jPanel_SalesTransactionLayout);
        jPanel_SalesTransactionLayout.setHorizontalGroup(
            jPanel_SalesTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_SalesTransactionLayout.createSequentialGroup()
                .addGap(0, 265, Short.MAX_VALUE)
                .addGroup(jPanel_SalesTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_SalesTransactionLayout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_cash)
                        .addGap(12, 12, 12)))
                .addGap(162, 162, 162))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_SalesTransactionLayout.createSequentialGroup()
                .addGroup(jPanel_SalesTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_SalesTransactionLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser_start, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser_end, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_SalesTransactionLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jButton_Back8, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(348, 348, 348)
                        .addComponent(jButton_Load1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Load, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(161, 161, 161))
        );
        jPanel_SalesTransactionLayout.setVerticalGroup(
            jPanel_SalesTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SalesTransactionLayout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_SalesTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jLabel_cash, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel_SalesTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_SalesTransactionLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jDateChooser_end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_SalesTransactionLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_SalesTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jDateChooser_start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addGap(43, 43, 43)
                .addGroup(jPanel_SalesTransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Load, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Load1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Back8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );

        jPanel_Main.add(jPanel_SalesTransaction, "pnlCard9");

        jDesktopPane1.setLayer(jPanel_Main, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenuBar1.setBackground(new java.awt.Color(20, 130, 220));

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_MedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_MedActionPerformed
    //  int status=Global.status;
        int check=0;
        System.out.println(status);
        if(status==check )
        {
                        JOptionPane.showMessageDialog(null,"As A User No Have A Authority To Access This Part.");

        }
        else{
            c.show(jPanel_Main,"pnlCard1");
               loadData();
        }
        
        

    }//GEN-LAST:event_jButton_MedActionPerformed

    private void jButton_CompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CompActionPerformed
      c.show(jPanel_Main, "pnlCard2");
                loadData1();

    }//GEN-LAST:event_jButton_CompActionPerformed

    private void jButton_SalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SalesActionPerformed
//NewCustomerEntry();
NewSalesEntry();
//dispose();
    }//GEN-LAST:event_jButton_SalesActionPerformed

    private void jButton_LogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LogOutActionPerformed
LoginFrame lf=new LoginFrame();
lf.setVisible(true);
dispose();

    }//GEN-LAST:event_jButton_LogOutActionPerformed

    private void jTextField_SearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_SearchMouseClicked

        jTextField_Search.setText("");
    }//GEN-LAST:event_jTextField_SearchMouseClicked

    private void jTextField_SearchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_SearchMouseExited
       // jTextField_Search.setText("Search.....");

    }//GEN-LAST:event_jTextField_SearchMouseExited

    private void jTextField_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_SearchActionPerformed

        //snum=Integer.parseInt(jTextField_Search.getText());
        //searchNum();
    }//GEN-LAST:event_jTextField_SearchActionPerformed

    private void jTextField_SearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_SearchKeyTyped
       
       searchValue=jTextField_Search.getText();
        //int a=Integer.parseInt(jTextField_Search.getText());
        char ch=evt.getKeyChar();
        if(!(ch>='0' &&ch<='9'))
        {
            searchData();
        }
        
        
        /*else if(ch>='0' && ch<='9')
        {
            //snum=Integer.parseInt(jTextField_Search.getText().toString());
            //System.out.println("Here it is ");
            //System.out.println(a);
        }
        //searchData();
        */
    }//GEN-LAST:event_jTextField_SearchKeyTyped

    private void jTextField_SearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_SearchMousePressed
        jTextField_Search.setText("");


    }//GEN-LAST:event_jTextField_SearchMousePressed

    private void jTextField_SearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_SearchKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_SearchKeyPressed

    private void jButton_InsertMedicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_InsertMedicActionPerformed
 c.show(jPanel_Main, "pnlCard3");
                //loadData1();
                loadComp();

        
    }//GEN-LAST:event_jButton_InsertMedicActionPerformed

    private void jButton_UpdateMedicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_UpdateMedicActionPerformed
                  //loadData1();
                  if(productid!=0)
                  {
setMedicUpdate();
  c.show(jPanel_Main, "pnlCard5");

                  }
                  else{
                                         JOptionPane.showMessageDialog(null,"Select Data ");
                  }
    }//GEN-LAST:event_jButton_UpdateMedicActionPerformed

    private void jButton_EraseMedicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EraseMedicActionPerformed
if(productid==0)
           {
                             JOptionPane.showMessageDialog(null,"Select Data ");
           }
           else{
                ProductsSuppliers ps=new ProductsSuppliers();
           ps.setPid(productid);
           PharmacyDAO pDAO=new PharmacyDAO();
            int count=pDAO.eraseMedic(ps);
      if(count>0)
      {
          JOptionPane.showMessageDialog(null,"Company Erased");
         loadData();
          productid=0;
          }
}
    }//GEN-LAST:event_jButton_EraseMedicActionPerformed

    private void jButton_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BackActionPerformed
c.first(jPanel_Main);
loadData();

    }//GEN-LAST:event_jButton_BackActionPerformed

    private void jTextField_Search1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_Search1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Search1MouseClicked

    private void jTextField_Search1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_Search1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Search1MouseExited

    private void jTextField_Search1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_Search1MousePressed
        jTextField_Search1.setText("");
    }//GEN-LAST:event_jTextField_Search1MousePressed

    private void jTextField_Search1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Search1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Search1ActionPerformed

    private void jTextField_Search1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Search1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Search1KeyPressed

    private void jTextField_Search1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Search1KeyTyped
 searchValue=jTextField_Search1.getText();
        //int a=Integer.parseInt(jTextField_Search.getText());
        char ch=evt.getKeyChar();
        if(!(ch>='0' &&ch<='9'))
        {
            searchData();
        }    }//GEN-LAST:event_jTextField_Search1KeyTyped

    private void jTable_View1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_View1MouseClicked
clickDataMedic();
System.out.println(compName);
                                 System.out.println(mfd);
    }//GEN-LAST:event_jTable_View1MouseClicked

    private void jButton_CreateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CreateUserActionPerformed
                 c.show(jPanel_Main, "pnlCard7");
    }//GEN-LAST:event_jButton_CreateUserActionPerformed

    private void jButton_PassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PassActionPerformed
                                jTextField_Username1.setText(Global.user);

        c.show(jPanel_Main,"pnlCard8");
//int status=Global.status;
//
//if(status !=0)
//{
//    System.out.println("Admin");
//}
//else
//{
//        System.out.println("User");
//
//}




    }//GEN-LAST:event_jButton_PassActionPerformed

    private void jButton_ViewSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ViewSalesActionPerformed
c.show(jPanel_Main,"pnlCard9");
loadTransaction();

    }//GEN-LAST:event_jButton_ViewSalesActionPerformed

    private void jButton_InsertCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_InsertCompActionPerformed
//int status=Global.status;
        int check=0;
        System.out.println(status);
        System.out.println(Global.uid);
        if(status==check )
        {
                        JOptionPane.showMessageDialog(null,"As A User No Have A Authority To Access This Part.");

        }
        else{
           System.out.println("Inside else");
                 c.show(jPanel_Main, "pnlCard4");

           
        }    }//GEN-LAST:event_jButton_InsertCompActionPerformed

    private void jButton_UpdateCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_UpdateCompActionPerformed
//int status=Global.status;
        int check=0;
        System.out.println(status);
        if(status==check )
        {/////////////////////"p;\
                        JOptionPane.showMessageDialog(null,"As A User No Have A Authority To Access This Part.");

        }
        else{
           System.out.println("Inside else");
                            if(compid==0)
                            {
                   JOptionPane.showMessageDialog(null,"Select Data ");

                            }
                            else
                            {
                              c.show(jPanel_Main, "pnlCard6");
                               clickCompDetails();
                            }
        }
    }//GEN-LAST:event_jButton_UpdateCompActionPerformed

    private void jButton_EraseCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EraseCompActionPerformed
 //int status=Global.status;
        int check=0;
        System.out.println(status);
        if(status==check )
        {
           JOptionPane.showMessageDialog(null,"As A User No Have A Authority To Access This Part.");

        }
        else{
           System.out.println("Inside else");
           if(compid==0)
           {
                             JOptionPane.showMessageDialog(null,"Select Data ");
           }
           else{
                Suppliers s=new Suppliers();
           s.setSid(compid);
           PharmacyDAO pDAO=new PharmacyDAO();
            int count=pDAO.eraseCompany(s);
      if(count>0)
      {
          JOptionPane.showMessageDialog(null,"Company Erased");
          loadData1();
          compid=0;
          }
           }
          
        }    }//GEN-LAST:event_jButton_EraseCompActionPerformed

    private void jButton_Back1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Back1ActionPerformed
c.first(jPanel_Main);
    }//GEN-LAST:event_jButton_Back1ActionPerformed

    private void jTextField_Search2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_Search2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Search2MouseClicked

    private void jTextField_Search2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_Search2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Search2MouseExited

    private void jTextField_Search2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_Search2MousePressed
        jTextField_Search2.setText("");
    }//GEN-LAST:event_jTextField_Search2MousePressed

    private void jTextField_Search2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Search2ActionPerformed
      }//GEN-LAST:event_jTextField_Search2ActionPerformed

    private void jTextField_Search2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Search2KeyPressed
searchValue1=jTextField_Search2.getText();
        //int a=Integer.parseInt(jTextField_Search.getText());
        char ch=evt.getKeyChar();
        if(!(ch>='0' &&ch<='9'))
        {
            searchData1();
        }     }//GEN-LAST:event_jTextField_Search2KeyPressed

    private void jTextField_Search2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Search2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Search2KeyTyped

    private void jTable_View2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_View2MouseClicked
                            clickData();
                                 System.out.println(compName);
                                 System.out.println(compid);

    }//GEN-LAST:event_jTable_View2MouseClicked

    private void jButton_InsertMedic1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_InsertMedic1ActionPerformed
 pname=jTextField_ProductName1.getText();
     pcatagory=jTextField_ProductCatagory1.getText();
                mfdyear=jDateChooser_mfd.getDate();
           
      pquantity=Integer.parseInt(jTextField_ProductQuantity.getText());
      ppu=Double.parseDouble(jTextField_ppu.getText());
      int chck=1;
            int aid=Global.uid;//default value it should be changed 

      ProductsSuppliers ps=new ProductsSuppliers();
      
      Date d=new Date();
      int vdate=d.getYear()+d.getMonth()+d.getDay();
      int mdate=mfdyear.getYear()+mfdyear.getMonth()+mfdyear.getDay();
      
      
      if(pname.length()!=0)
      {
          ps.setProductName(pname);
                              System.out.println(ps.getProductName());
                            
      }  
      else
      {
                    JOptionPane.showMessageDialog(null,"Enter product name");

                   // System.out.println("Enter product name");
                    chck++;

      }
         if(pcatagory.length()!=0)
      {
          ps.setProductCategory(pcatagory);
                              System.out.println(ps.getProductCategory());

      }
         else{
                                 JOptionPane.showMessageDialog(null,"Enter Catagory");

                               //  System.out.println("Enter Catagory");
                                                     chck++;

         }
         //validate to add
         if(this.jDateChooser_mfd.getDate()!=null)
         {
            // System.out.println("Vdate "+vdate+"Mdate"+mdate);
             if(mdate<=vdate)
             {
                                    ps.setMfgYear(mfdyear);
             }
             else{
                 chck++;
                    JOptionPane.showMessageDialog(null,"Choice Valid Date");
             }
         }
         else
         {
                      chck++;
                    JOptionPane.showMessageDialog(null,"Choice Date");

          }
         //
        // System.out.println(ps.getMfgYear());
        if(pquantity<=0)
      {
                              chck++;
                    JOptionPane.showMessageDialog(null,"Enter valid Quantity");

                                        // System.out.println("Enter Quantity");

      }
         else{
              ps.setQuantity(pquantity);
                             // System.out.println(ps.getQuantity());
         }
         if(ppu <=0)
      {
                              chck++;
                    JOptionPane.showMessageDialog(null,"Enter Valid Price");

                                // System.out.println("Enter Price");

      }
         else{
             ps.setPrice(ppu);
                            //  System.out.println(ps.getPrice());
         }
         if(compid==0)
         {
                                              chck++;

                    JOptionPane.showMessageDialog(null,"Choice MFD Company");

         }
         else{
                      ps.setCid(compid);
         //System.out.println(ps.getCid());

         }
         ps.setAid(aid);
         if(chck>1)
         {
                                 JOptionPane.showMessageDialog(null,"Enter valid data");

            // System.out.println("Enter valid Data");
         }
         else
         {
             PharmacyDAO PDAO=new PharmacyDAO();
      int count=PDAO.insertmedic(ps);
      if(count>0)
      {
          JOptionPane.showMessageDialog(null,"Product Saved");
          clearMedicFrame();
          jTextField_ProductQuantity.setText("0");
          jTextField_ppu.setText("0");
         // clearFields();
      }
             
             System.out.println("Success");
         }
    }//GEN-LAST:event_jButton_InsertMedic1ActionPerformed

    private void jButton_Back2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Back2ActionPerformed
      c.show(jPanel_Main, "pnlCard1");
    loadData();
                //comboboxclear();
                            jComboBox_Comp.addItem("Select Company");


      
    }//GEN-LAST:event_jButton_Back2ActionPerformed

    private void jTextField_ppuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_ppuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_ppuActionPerformed

    private void jTextField_ProductName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_ProductName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_ProductName1ActionPerformed

    private void jTextField_ProductCatagory1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_ProductCatagory1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_ProductCatagory1ActionPerformed

    private void jTextField_ProductQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_ProductQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_ProductQuantityActionPerformed

    private void jTable_ViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ViewMouseClicked

        // selectData();
        // feesDetails();
    }//GEN-LAST:event_jTable_ViewMouseClicked

    private void jButton_InsertCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_InsertCompanyActionPerformed
String cname=jTextField_Cname.getText();
String caddress=jTextField_Caddress.getText();
String ccountry=jTextField_Ccountry.getText();
String cemail=jTextField_Cemail.getText();
       long ccontact=Long.parseLong(jTextField_Ccontact.getText());
      
      int aid=Global.uid;//default value it should be changed 
    
                       // int aid=2;
                       
       int chck=0;
                       Suppliers s=new Suppliers();
        if(cname.length()!=0)
      {
          s.setCompanyName(cname);
                             // System.out.println(ps.getProductName());
                            
      }  
      else
      {
                    JOptionPane.showMessageDialog(null,"Enter Company name");

                   // System.out.println("Enter product name");
                    chck++;

      }
        if(caddress.length()!=0)
      {
          s.setAddress(caddress);
                             // System.out.println(ps.getProductName());
                            
      }  
      else
      {
                    JOptionPane.showMessageDialog(null,"Enter Company Address");

                   // System.out.println("Enter product name");
                    chck++;

      }
        if(ccountry.length()!=0)
      {
          s.setCompanyCountry(ccountry);
                             // System.out.println(ps.getProductName());
                            
      }  
      else
      {
                    JOptionPane.showMessageDialog(null,"Enter Company Country ");

                   // System.out.println("Enter product name");
                    chck++;

      }
      // s.setCompanyName(cname);
        //s.setAddress(caddress);
        //s.setCompanyCountry(ccountry);
        if(cemail.length()!=0)
        {
        s.setEmail(cemail);
        }
        else{
                        JOptionPane.showMessageDialog(null,"Enter Company Email ");
chck++;
        }
        if(ccontact<=0)
        {
            
            s.setContactNo(ccontact);
        }
        else{
            JOptionPane.showMessageDialog(null,"Enter Company Contact ");

                   // System.out.println("Enter product name");
                    chck++;
        }
        s.setAid(aid);
        //<<
        //now handover this object to controller class PharmacyDAO
        if(chck>1)
         {
                                 JOptionPane.showMessageDialog(null,"Enter valid data");

            // System.out.println("Enter valid Data");
         }
         else
         {
        PharmacyDAO PDAO=new PharmacyDAO();
      int count=PDAO.insertCompany(s);
      if(count>0)
      {
          JOptionPane.showMessageDialog(null,"Company Saved");
          clearFields();
          jTextField_Ccontact.setText("0");
      }
                   System.out.println("Success");

                  
         }
    }//GEN-LAST:event_jButton_InsertCompanyActionPerformed

    private void jButton_Back3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Back3ActionPerformed
      c.show(jPanel_Main, "pnlCard2");
                                            loadData1();

    }//GEN-LAST:event_jButton_Back3ActionPerformed

    private void jTextField_CcontactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_CcontactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_CcontactActionPerformed

    private void jTextField_CnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_CnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_CnameActionPerformed

    private void jTextField_CcountryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_CcountryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_CcountryActionPerformed

    private void jTextField_CemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_CemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_CemailActionPerformed

    private void jTextField_CaddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_CaddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_CaddressActionPerformed

    private void jButton_UpdateMedic2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_UpdateMedic2ActionPerformed

pname=jTextField_ProductName2.getText();
      pcatagory=jTextField_ProductCatagory2.getText();
                //mfdyear=jDateChooser_mfd2.getDate();
           
      pquantity=Integer.parseInt(jTextField_Quantity2.getText());
      ppu=Double.parseDouble(jTextField_Price2.getText());
      int chck=1;
            int aid=Global.uid;//default value it should be changed 
      ProductsSuppliers ps=new ProductsSuppliers();
      
      ps.setPid(productid);
      if(pname.length()!=0)
      {
          ps.setProductName(pname);
                              System.out.println(ps.getProductName());
                            
      }  
      else
      {
                    JOptionPane.showMessageDialog(null,"Enter product name");

                    System.out.println("Enter product name");
                    chck++;

      }
         if(pcatagory.length()!=0)
      {
          ps.setProductCategory(pcatagory);
                              System.out.println(ps.getProductCategory());

      }
         else{
                                 JOptionPane.showMessageDialog(null,"Enter Catagory");

                                 System.out.println("Enter Catagory");
                                                     chck++;

         }
         //// validate to add
//         if(mfdyear.equals(c))
//         {
//              chck++;
//                    JOptionPane.showMessageDialog(null,"Choice Date");
//         }
//         else
//         {
                      //ps.setMfgYear(mfdyear);

         // }
         ////
         //System.out.println(ps.getMfgYear());
        if(pquantity==0)
      {
                              chck++;
                    JOptionPane.showMessageDialog(null,"Enter Quantity");

                                         System.out.println("Enter Quantity");

      }
         else{
              ps.setQuantity(pquantity);
                              System.out.println(ps.getQuantity());
         }
         if(ppu ==0)
      {
                              chck++;
                    JOptionPane.showMessageDialog(null,"Enter Price");

                                 System.out.println("Enter Price");

      }
         else{
             ps.setPrice(ppu);
                              System.out.println(ps.getPrice());
         }
//         if(compid==0)
//         {
//             chck++;
//                    JOptionPane.showMessageDialog(null,"Choice MFD Company");
//         }
//         else{
//                      ps.setCid(compid);
//         System.out.println(ps.getCid());
//
//         }
         ps.setAid(aid);
         if(chck>1)
         {
                                 JOptionPane.showMessageDialog(null,"Enter valid data");

             System.out.println("Enter valid data");
         }
         else
         {
             PharmacyDAO PDAO=new PharmacyDAO();
      int count=PDAO.updateMedic(ps);
      if(count>0)
      {
          JOptionPane.showMessageDialog(null,"Product Updated");
           loadData();
          productid=0;
          
         // clearFields();
      }
             
           //  System.out.println("Success");
         }


    }//GEN-LAST:event_jButton_UpdateMedic2ActionPerformed

    private void jButton_Back4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Back4ActionPerformed
      c.show(jPanel_Main, "pnlCard1");
    }//GEN-LAST:event_jButton_Back4ActionPerformed

    private void jTextField_Price2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Price2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Price2ActionPerformed

    private void jTextField_ProductName2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_ProductName2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_ProductName2ActionPerformed

    private void jTextField_pcompany2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_pcompany2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_pcompany2ActionPerformed

    private void jTextField_Quantity2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Quantity2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Quantity2ActionPerformed

    private void jButton_UpdateComp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_UpdateComp3ActionPerformed

        
        String cname=jTextField_CompanyName1.getText();
String caddress=jTextField_Address1.getText();
String ccountry=jTextField_Country1.getText();
String cemail=jTextField_Email1.getText();
       long ccontact=Long.parseLong(jTextField_ContactNo1.getText());
        
        Suppliers s=new Suppliers();
        s.setSid(compid);
        s.setCompanyName(cname);
        s.setAddress(caddress);
        s.setCompanyCountry(ccountry);
        s.setEmail(cemail);
        s.setContactNo(ccontact);

        PharmacyDAO PDAO=new PharmacyDAO();
      int count=PDAO.updateCompany(s);
      if(count>0)
      {
          JOptionPane.showMessageDialog(null,"Update Company Saved");
         // clearFields1();
      }
    }//GEN-LAST:event_jButton_UpdateComp3ActionPerformed

    private void jButton_Back5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Back5ActionPerformed
                
        c.show(jPanel_Main,"pnlCard2");
 loadData1(); 
        clearFields1();

    }//GEN-LAST:event_jButton_Back5ActionPerformed

    private void jTextField_Country1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Country1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Country1ActionPerformed

    private void jTextField_CompanyName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_CompanyName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_CompanyName1ActionPerformed

    private void jTextField_Address1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Address1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Address1ActionPerformed

    private void jTextField_Email1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Email1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Email1ActionPerformed

    private void jTextField_ContactNo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_ContactNo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_ContactNo1ActionPerformed

    private void jComboBox_CompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_CompActionPerformed

        System.out.println(jComboBox_Comp.getSelectedItem().toString());
        compName=jComboBox_Comp.getSelectedItem().toString();
       
       searchData2();
    }//GEN-LAST:event_jComboBox_CompActionPerformed

    private void jComboBox_CompItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_CompItemStateChanged


    }//GEN-LAST:event_jComboBox_CompItemStateChanged

    private void jTextField_ProductCatagory2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_ProductCatagory2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_ProductCatagory2ActionPerformed

    private void jTextField_mfd3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_mfd3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_mfd3ActionPerformed

    private void jButton_InsertUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_InsertUserActionPerformed

        String username=jTextField_Username.getText();
        password=jPasswordField_Password.getText().toString();

 String uname=jTextField_Name.getText();
String address=jTextField_Address.getText();
String email=jTextField_Email.getText();
       long contact=Long.parseLong(jTextField_Contact.getText());
int aid=Global.uid;
int chck=1;
///Password Encryption
//  try
//                          {
//                               String pssword=password;
//   MessageDigest md= MessageDigest.getInstance("MD5");
//   byte[] hashInBytes =md.digest(pssword.getBytes(StandardCharsets.UTF_8));
//   
//   StringBuilder sb= new StringBuilder();
//   for(byte b : hashInBytes)
//   {
//       sb.append(String.format("%02x", b));
//       System.out.println(sb.toString());
//       password=sb.toString();
//   }
//   System.out.println(password);
//                          }
//                          catch(Exception e)
//                          {
//                              System.out.println(e);
//                          }
  ///Encrption
  passwordMD5();
UserAccount u=new UserAccount();
if(!(username.isEmpty()))
{
       u.setUname(username);
}
else
{
    chck++;
              JOptionPane.showMessageDialog(null,"Enter Username ");

}
if(!(password.isEmpty()))
{
       u.setUpass(password);
}
else
{
    chck++;
              JOptionPane.showMessageDialog(null,"Enter Password ");

}
if(!(uname.isEmpty()))
{
       u.setFullname(uname);
}
else{
    chck++;
              JOptionPane.showMessageDialog(null,"Enter Fullname");

}
if(!(address.isEmpty()))
{
       u.setAddress(address);
}
else
{
    chck++;
              JOptionPane.showMessageDialog(null,"Enter Address ");

}
if(!(email.isEmpty()))
{
    
       u.setEmail(email);
}
else{
    chck++;
              JOptionPane.showMessageDialog(null,"Enter Email");

}
if(!(contact==0))
{
       u.setUcontact(contact);
}
else{
    chck++;
              JOptionPane.showMessageDialog(null,"Enter Contact");

}
       u.setAid(aid);
       if(chck==1)
       {
        LoginDUO LDAO=new LoginDUO();
      int count=LDAO.createUser(u);
      if(count>0)
      {
          JOptionPane.showMessageDialog(null,"Succesfully User Created");
         // clearFields1();
      }
       }
    }//GEN-LAST:event_jButton_InsertUserActionPerformed

    private void jButton_Back6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Back6ActionPerformed
                 c.first(jPanel_Main);
    }//GEN-LAST:event_jButton_Back6ActionPerformed

    private void jTextField_ContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_ContactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_ContactActionPerformed

    private void jTextField_UsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_UsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_UsernameActionPerformed

    private void jTextField_AddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_AddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_AddressActionPerformed

    private void jTextField_EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_EmailActionPerformed

    private void jTextField_NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_NameActionPerformed

    private void jButton_InsertUser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_InsertUser1ActionPerformed
int uid=Global.uid;
 password=jPasswordField_NewPassword2.getText().toString();
String retype=jPasswordField_RePassword2.getText().toString();
int count;
if((password.length()!=0) && (retype.length()!=0))
{
if(( password!=" ")||(retype!=" "))
{
    if(password.equals(retype))
    {
       passwordMD5();
if(Global.status!=0)
        {
          
    LoginDAO ldao=new LoginDAO();
    count=ldao.updatePassword( uid,  password );
    if(count>0)
    {
                                 JOptionPane.showMessageDialog(null,"Succesfull");
                                // dispose();
    }
    else{
                                         JOptionPane.showMessageDialog(null,"Unsuccesfull");

    }
        }
        else
        {
            LoginDUO ldao=new LoginDUO();
    count=ldao.updatePassword( uid,  password );
    if(count>0)
    {
                                 JOptionPane.showMessageDialog(null,"Succesfull");
                                // dispose();
    }
    else{
                                         JOptionPane.showMessageDialog(null,"Unsuccesfull");

    }
        }
    }
    else{
                         JOptionPane.showMessageDialog(null,"New Password Doesnt Match");
                         jPasswordField_RePassword2.setText("");
}

}
}
else{
                             JOptionPane.showMessageDialog(null,"Enter Vaild Password");

}
    }//GEN-LAST:event_jButton_InsertUser1ActionPerformed

    private void jButton_Back7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Back7ActionPerformed
c.first(jPanel_Main);
jPasswordField_NewPassword2.setText("********");
        jPasswordField_RePassword2.setText("********");
    }//GEN-LAST:event_jButton_Back7ActionPerformed

    private void jTextField_Username1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Username1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Username1ActionPerformed

    private void jPasswordField_RePassword2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField_RePassword2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField_RePassword2ActionPerformed

    private void jPasswordField_NewPassword2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField_NewPassword2MouseClicked

jPasswordField_NewPassword2.setText("");

    }//GEN-LAST:event_jPasswordField_NewPassword2MouseClicked

    private void jPasswordField_RePassword2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField_RePassword2MouseClicked
jPasswordField_RePassword2.setText("");
    }//GEN-LAST:event_jPasswordField_RePassword2MouseClicked

    private void jButton_LoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LoadActionPerformed

//loadTransaction();
ChoiceTransaction();


    }//GEN-LAST:event_jButton_LoadActionPerformed

    private void jButton_Back8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Back8ActionPerformed
c.first(jPanel_Main);
    }//GEN-LAST:event_jButton_Back8ActionPerformed

    private void jTable_SalesTransactionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_SalesTransactionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable_SalesTransactionMouseClicked

    private void jTextField_ProductName1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_ProductName1KeyTyped
//searchValue=jTextField_Search.getText();
//int a=Integer.parseInt(jTextField_Search.getText());
char ch=evt.getKeyChar();
if(ch>='0' &&ch<='9')
{
    evt.consume();
}    }//GEN-LAST:event_jTextField_ProductName1KeyTyped

    private void jTextField_ProductCatagory1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_ProductCatagory1KeyTyped
char ch=evt.getKeyChar();
if(ch>='0' &&ch<='9')
{
    evt.consume();
}       }//GEN-LAST:event_jTextField_ProductCatagory1KeyTyped

    private void jTextField_ProductQuantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_ProductQuantityKeyTyped
char ch=evt.getKeyChar();
if(!(ch>='0' &&ch<='9'))
{
    evt.consume();
}   

    }//GEN-LAST:event_jTextField_ProductQuantityKeyTyped

    private void jTextField_ppuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_ppuKeyTyped
char ch=evt.getKeyChar();
if(!(ch>='0' &&ch<='9'))
{
    evt.consume();
}       }//GEN-LAST:event_jTextField_ppuKeyTyped

    private void jTextField_ProductName2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_ProductName2KeyTyped
char ch=evt.getKeyChar();
if(ch>='0' &&ch<='9')
{
    evt.consume();
}       }//GEN-LAST:event_jTextField_ProductName2KeyTyped

    private void jTextField_ProductCatagory2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_ProductCatagory2KeyTyped
char ch=evt.getKeyChar();
if(ch>='0' &&ch<='9')
{
    evt.consume();
}       }//GEN-LAST:event_jTextField_ProductCatagory2KeyTyped

    private void jTextField_Quantity2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Quantity2KeyTyped
char ch=evt.getKeyChar();
if(!(ch>='0' &&ch<='9'))
{
    evt.consume();
}       }//GEN-LAST:event_jTextField_Quantity2KeyTyped

    private void jTextField_Price2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Price2KeyTyped
char ch=evt.getKeyChar();
if(!(ch>='0' &&ch<='9'))
{
    evt.consume();
}       }//GEN-LAST:event_jTextField_Price2KeyTyped

    private void jButton_Load1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Load1ActionPerformed
generateBill();
    }//GEN-LAST:event_jButton_Load1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Back;
    private javax.swing.JButton jButton_Back1;
    private javax.swing.JButton jButton_Back2;
    private javax.swing.JButton jButton_Back3;
    private javax.swing.JButton jButton_Back4;
    private javax.swing.JButton jButton_Back5;
    private javax.swing.JButton jButton_Back6;
    private javax.swing.JButton jButton_Back7;
    private javax.swing.JButton jButton_Back8;
    private javax.swing.JButton jButton_Comp;
    private javax.swing.JButton jButton_CreateUser;
    private javax.swing.JButton jButton_EraseComp;
    private javax.swing.JButton jButton_EraseMedic;
    private javax.swing.JButton jButton_InsertComp;
    private javax.swing.JButton jButton_InsertCompany;
    private javax.swing.JButton jButton_InsertMedic;
    private javax.swing.JButton jButton_InsertMedic1;
    private javax.swing.JButton jButton_InsertUser;
    private javax.swing.JButton jButton_InsertUser1;
    private javax.swing.JButton jButton_Load;
    private javax.swing.JButton jButton_Load1;
    private javax.swing.JButton jButton_LogOut;
    private javax.swing.JButton jButton_Med;
    private javax.swing.JButton jButton_Pass;
    private javax.swing.JButton jButton_Sales;
    private javax.swing.JButton jButton_UpdateComp;
    private javax.swing.JButton jButton_UpdateComp3;
    private javax.swing.JButton jButton_UpdateMedic;
    private javax.swing.JButton jButton_UpdateMedic2;
    private javax.swing.JButton jButton_ViewSales;
    private javax.swing.JComboBox<String> jComboBox_Comp;
    private com.toedter.calendar.JDateChooser jDateChooser_end;
    private com.toedter.calendar.JDateChooser jDateChooser_mfd;
    private com.toedter.calendar.JDateChooser jDateChooser_start;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_cash;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_ChangePassword;
    private javax.swing.JPanel jPanel_CreateUser;
    private javax.swing.JPanel jPanel_First;
    private javax.swing.JPanel jPanel_InsertCompany;
    private javax.swing.JPanel jPanel_InsetMedic;
    private javax.swing.JPanel jPanel_Main;
    private javax.swing.JPanel jPanel_Medic;
    private javax.swing.JPanel jPanel_SalesTransaction;
    private javax.swing.JPanel jPanel_Suppliers;
    private javax.swing.JPanel jPanel_UpdateComp;
    private javax.swing.JPanel jPanel_UpdateMedic;
    private javax.swing.JPasswordField jPasswordField_NewPassword2;
    private javax.swing.JPasswordField jPasswordField_Password;
    private javax.swing.JPasswordField jPasswordField_Password3;
    private javax.swing.JPasswordField jPasswordField_Password4;
    private javax.swing.JPasswordField jPasswordField_RePassword2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable_SalesTransaction;
    private javax.swing.JTable jTable_View;
    private javax.swing.JTable jTable_View1;
    private javax.swing.JTable jTable_View2;
    private javax.swing.JTextField jTextField_Address;
    private javax.swing.JTextField jTextField_Address1;
    private javax.swing.JTextField jTextField_Caddress;
    private javax.swing.JTextField jTextField_Ccontact;
    private javax.swing.JTextField jTextField_Ccountry;
    private javax.swing.JTextField jTextField_Cemail;
    private javax.swing.JTextField jTextField_Cname;
    private javax.swing.JTextField jTextField_CompanyName1;
    private javax.swing.JTextField jTextField_Contact;
    private javax.swing.JTextField jTextField_ContactNo1;
    private javax.swing.JTextField jTextField_Country1;
    private javax.swing.JTextField jTextField_Email;
    private javax.swing.JTextField jTextField_Email1;
    private javax.swing.JTextField jTextField_Name;
    private javax.swing.JTextField jTextField_Price2;
    private javax.swing.JTextField jTextField_ProductCatagory1;
    private javax.swing.JTextField jTextField_ProductCatagory2;
    private javax.swing.JTextField jTextField_ProductName1;
    private javax.swing.JTextField jTextField_ProductName2;
    private javax.swing.JTextField jTextField_ProductQuantity;
    private javax.swing.JTextField jTextField_Quantity2;
    private javax.swing.JTextField jTextField_Search;
    private javax.swing.JTextField jTextField_Search1;
    private javax.swing.JTextField jTextField_Search2;
    private javax.swing.JTextField jTextField_Username;
    private javax.swing.JTextField jTextField_Username1;
    private javax.swing.JTextField jTextField_mfd3;
    private javax.swing.JTextField jTextField_pcompany2;
    private javax.swing.JTextField jTextField_ppu;
    // End of variables declaration//GEN-END:variables
}
