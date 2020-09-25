/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pharmacy.view;

import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.pharmacy.controller.*;
import org.pharmacy.model.*;
import org.pharmacy.util.*;

/**
 *
 * @author DELL
 */
public class SalesBillingOp extends javax.swing.JInternalFrame {

    /**
     * Creates new form SalesBillingOp
     */
 private String customername,customeraddress,productName;
         DefaultTableModel model;    
private int productid;
    private int cid,age,productQuantity,quantity,oid;
private Double grandTotal;    
    public SalesBillingOp() {
        initComponents();

                        jLabel_CurrentDate.setText(new Date().toString());
                        jLabel_User.setText(Global.user);
                                model=new DefaultTableModel(null, new String[]{"Product Id","Product Name","Company Name","Product Category","Mfd Year","Quantity","Price per unit"});
        jTable_Sales.setModel(model);

                        loadProduct();
                        
    }
public void NewCustomerEntry()
{
    customerDetails cd=new customerDetails();
        jDesktopPane2.add(cd);
        cd.setVisible(true);
}
public void loadData()
{
    cid=customerUtil.customerid;
customername=customerUtil.customerName;
customeraddress=customerUtil.customerAddress;
age=customerUtil.age;

}
public void displayData(){
    jLabel_cname1.setText(customername);
jLabel_address.setText(customeraddress);
jLabel_age1.setText(age+"");
jLabel_cid.setText(cid+"");
}
public void clearProduct()
{
    jTextField_productId.setText("");
    jTextField_EnterQunatity.setText("");
}
public void loadProduct()
{
    
   // String comp[] ={"Ram","Hari","Shyam"};
    //for(int i=0;i<3;i++)
   // {
 //jComboBox_Comp.addItem(comp[i]);
  //  }
   PharmacyDAO pDAO=new PharmacyDAO();
List<ProductsSuppliers> productList=pDAO.productData();
//clearTable1();       
for(ProductsSuppliers p:productList)
        {
jComboBox_Product.addItem(p.getProductName());
        }
  
    }
public void searchData()
{
      PharmacyDAO pDAO=new PharmacyDAO();
List<ProductsSuppliers> productList=pDAO.searchProductId(productName);
//clearTable1();       
for(ProductsSuppliers s:productList)
        {
           productid=s.getPid();
           productQuantity=s.getQuantity();
           
           System.out.println(productid);
                      System.out.println(productQuantity);

        }
}
public void comboData()
{
    System.out.println(jComboBox_Product.getSelectedItem().toString());
        productName=jComboBox_Product.getSelectedItem().toString();
       
       searchData();
jTextField_productId.setText(productid+"");
}
public void grandTotal()
 {
     int count=jTable_Sales.getRowCount();
     double gtotal=0;
     for(int i=0;i<count;i++)
     {
         int quantity=Integer.parseInt(jTable_Sales.getValueAt(i,5).toString());
     double total=Double.parseDouble(jTable_Sales.getValueAt(i,6).toString());

    gtotal+=total*quantity;
    
     }
     grandTotal=gtotal;
     jLabel_grandTotal.setText(gtotal+"");
 }
public void orderInfo()
{
    Date d=new Date();
    SalesProduct sp=new SalesProduct();
    sp.setCid(cid);
    sp.setDos(d);
    sp.setOrderStatus("Active");
    PharmacyDAO PDAO=new PharmacyDAO();
PDAO.orderInfoId(sp);
int count=PDAO.orderGetId(sp);
oid=count;
jLabel_orderId.setText(oid+"");
}
public void transaction()
{
    Date d=new Date();
    int uid=Global.uid;
    SalesProduct sp=new SalesProduct();
    sp.setUid(uid);
    sp.setOid(oid);
    sp.setDos(d);
  sp.setTotalamount(grandTotal);
    PharmacyController PDAO=new PharmacyController();
int chck=PDAO.saveTransaction(sp);
if(chck!=0)
{
                                         JOptionPane.showMessageDialog(null,"Saved ");

}
else{
                                         JOptionPane.showMessageDialog(null,"Error");

}
}
public void printBill()
 {
     int count=jTable_Sales.getRowCount();
     String str="                        Mahadevsthan Pharmacy Pvt Ltd\n                        Koteshowr,kathmandu\n";
          str+="\n                  Medicine Recepit                  ";
 str+="         "+jLabel_CurrentDate.getText()+"\n";
     str+="\nCustomer Id. "+jLabel_cid.getText()+"\n";
     str+="\nCustomer Name. "+jLabel_cname1.getText()+"\n";
          str+="\nCustomer Address. "+jLabel_address.getText()+"\n";
     str+="\nCustomer Age. "+jLabel_age1.getText()+"\n";

     str+="Sno.   "+"Medicine Name               "+"Company Name             "+"Medicine Category             "+"Medicine Mfd             "+"Quantity             "+"Price Per Unit             "+"\n";
     for(int i=0;i<count;i++)
     {
         int l=1;
         
         str+=l+"        ";
                  str+=jTable_Sales.getValueAt(i, 1).toString()+"                                ";
         str+=jTable_Sales.getValueAt(i, 2).toString()+"            ";
         str+=jTable_Sales.getValueAt(i, 3).toString()+"                                        ";
                  str+=jTable_Sales.getValueAt(i, 4).toString()+"                 ";
         str+=jTable_Sales.getValueAt(i, 5).toString()+"                        ";

         str+=jTable_Sales.getValueAt(i, 6).toString()+"\n";
         l++;
     }
     str+="Grand Total: "+jLabel_grandTotal.getText();
     str+="\nThank You For Visting!!!";
     BillPrint ob=new BillPrint();
     ob.jTextArea_Bill.setLineWrap(true);
     ob.jTextArea_Bill.setText(str);
     ob.setVisible(true);
     try{
         ob.jTextArea_Bill.print();
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

        jDesktopPane2 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel_CurrentDate = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel_cid = new javax.swing.JLabel();
        jLabel_address = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel_cname1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel_orderId = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField_EnterQunatity = new javax.swing.JTextField();
        jComboBox_Product = new javax.swing.JComboBox<>();
        jTextField_productId = new javax.swing.JTextField();
        jButton_Delete = new javax.swing.JButton();
        jButton_AddOrder = new javax.swing.JButton();
        jButton_Done1 = new javax.swing.JButton();
        jLabel_age1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton_Customer2 = new javax.swing.JButton();
        jButton_NewCustomer1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Sales = new javax.swing.JTable();
        jLabel_CurrentDate1 = new javax.swing.JLabel();
        jLabel_User = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel_grandTotal = new javax.swing.JLabel();
        jButton_SavePrint = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle(" Billing Operator");
        setPreferredSize(new java.awt.Dimension(1250, 650));

        jPanel1.setBackground(new java.awt.Color(20, 130, 220));

        jLabel_CurrentDate.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel_CurrentDate.setText("Current Date Time");

        jPanel2.setBackground(new java.awt.Color(20, 130, 220));
        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 4), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 2)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 0, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Age :-");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 150, 40));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 0, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Address :-");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 120, 40));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 0, 102));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Enter Quantity");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, 150, 40));

        jLabel_cid.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel_cid.setForeground(new java.awt.Color(102, 0, 102));
        jLabel_cid.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_cid.setText("Demo");
        jPanel2.add(jLabel_cid, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 60, 40));

        jLabel_address.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel_address.setForeground(new java.awt.Color(102, 0, 102));
        jLabel_address.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_address.setText("Demo");
        jPanel2.add(jLabel_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 160, 40));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 0, 102));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Name :-");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, 40));

        jLabel_cname1.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel_cname1.setForeground(new java.awt.Color(102, 0, 102));
        jLabel_cname1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_cname1.setText("Demo");
        jPanel2.add(jLabel_cname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 160, 40));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 0, 102));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Customer ID :-");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 170, 40));

        jLabel_orderId.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel_orderId.setForeground(new java.awt.Color(102, 0, 102));
        jLabel_orderId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_orderId.setText("Id");
        jPanel2.add(jLabel_orderId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 140, 90, 20));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 0, 102));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Product Name");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, 150, 40));

        jTextField_EnterQunatity.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField_EnterQunatity.setText("0");
        jTextField_EnterQunatity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField_EnterQunatityMouseClicked(evt);
            }
        });
        jPanel2.add(jTextField_EnterQunatity, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 140, 80, -1));

        jComboBox_Product.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jComboBox_Product.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choice Product" }));
        jComboBox_Product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_ProductActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox_Product, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, 130, 30));

        jTextField_productId.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField_productId.setText("0");
        jPanel2.add(jTextField_productId, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, 80, -1));

        jButton_Delete.setBackground(new java.awt.Color(255, 255, 0));
        jButton_Delete.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_Delete.setForeground(new java.awt.Color(102, 0, 102));
        jButton_Delete.setText("Delete Order");
        jButton_Delete.setBorder(null);
        jButton_Delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Delete.setPreferredSize(new java.awt.Dimension(135, 25));
        jButton_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteActionPerformed(evt);
            }
        });
        jPanel2.add(jButton_Delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 80, 151, 30));

        jButton_AddOrder.setBackground(new java.awt.Color(255, 255, 0));
        jButton_AddOrder.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_AddOrder.setForeground(new java.awt.Color(102, 0, 102));
        jButton_AddOrder.setText("Add Order");
        jButton_AddOrder.setBorder(null);
        jButton_AddOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_AddOrder.setPreferredSize(new java.awt.Dimension(135, 25));
        jButton_AddOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AddOrderActionPerformed(evt);
            }
        });
        jPanel2.add(jButton_AddOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, 151, 30));

        jButton_Done1.setBackground(new java.awt.Color(255, 255, 0));
        jButton_Done1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_Done1.setForeground(new java.awt.Color(102, 0, 102));
        jButton_Done1.setText("Done");
        jButton_Done1.setBorder(null);
        jButton_Done1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Done1.setPreferredSize(new java.awt.Dimension(135, 25));
        jButton_Done1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Done1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton_Done1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 135, 151, 30));

        jLabel_age1.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel_age1.setForeground(new java.awt.Color(102, 0, 102));
        jLabel_age1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_age1.setText("Demo");
        jPanel2.add(jLabel_age1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 160, 40));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 0, 102));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Product Id");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 140, 40));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 0, 102));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Order Id:");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 80, 90, 30));

        jButton_Customer2.setBackground(new java.awt.Color(255, 255, 0));
        jButton_Customer2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_Customer2.setForeground(new java.awt.Color(102, 0, 102));
        jButton_Customer2.setText("Load Customer");
        jButton_Customer2.setBorder(null);
        jButton_Customer2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Customer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Customer2ActionPerformed(evt);
            }
        });

        jButton_NewCustomer1.setBackground(new java.awt.Color(255, 255, 0));
        jButton_NewCustomer1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_NewCustomer1.setForeground(new java.awt.Color(102, 0, 102));
        jButton_NewCustomer1.setText("New Customer");
        jButton_NewCustomer1.setBorder(null);
        jButton_NewCustomer1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_NewCustomer1.setPreferredSize(new java.awt.Dimension(135, 25));
        jButton_NewCustomer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_NewCustomer1ActionPerformed(evt);
            }
        });

        jTable_Sales.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTable_Sales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_Sales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_SalesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Sales);

        jLabel_CurrentDate1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel_CurrentDate1.setText("Logged In As :- ");

        jLabel_User.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel_User.setText("USER0");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Grand Total :- Rs");

        jLabel_grandTotal.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel_grandTotal.setForeground(new java.awt.Color(255, 255, 0));
        jLabel_grandTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_grandTotal.setText("Demo");

        jButton_SavePrint.setBackground(new java.awt.Color(255, 255, 0));
        jButton_SavePrint.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButton_SavePrint.setForeground(new java.awt.Color(102, 0, 102));
        jButton_SavePrint.setText("Save & Print");
        jButton_SavePrint.setBorder(null);
        jButton_SavePrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_SavePrint.setPreferredSize(new java.awt.Dimension(135, 25));
        jButton_SavePrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SavePrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_NewCustomer1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Customer2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel_CurrentDate1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_User, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_CurrentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1024, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_grandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_SavePrint, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_CurrentDate)
                    .addComponent(jLabel_User)
                    .addComponent(jLabel_CurrentDate1))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton_NewCustomer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Customer2))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_grandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton_SavePrint, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        jDesktopPane2.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane2)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_NewCustomer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_NewCustomer1ActionPerformed
        
        NewCustomerEntry();
    // dispose();
    
    }//GEN-LAST:event_jButton_NewCustomer1ActionPerformed

    private void jButton_Customer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Customer2ActionPerformed
        loadData();

        if(cid!=0)
{
        displayData();
    clearProduct();
}
else{
                                     JOptionPane.showMessageDialog(null,"Enter New Customer");
}
    }//GEN-LAST:event_jButton_Customer2ActionPerformed

    private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeleteActionPerformed
int row=jTable_Sales.getSelectedRow();
if(row>=0)
{
        model.removeRow(row);
        grandTotal();
}
else{
                                         JOptionPane.showMessageDialog(null,"Select Order From Table");
}

    }//GEN-LAST:event_jButton_DeleteActionPerformed

    private void jButton_AddOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddOrderActionPerformed
int id=Integer.parseInt(jTextField_productId.getText());
quantity=Integer.parseInt(jTextField_EnterQunatity.getText());
//
if(productid!=0)
{
if(quantity>=0)
{
if(productQuantity>=quantity)
{
PharmacyDAO pdao=new PharmacyDAO();
 ProductsSuppliers op=pdao.fetchDetails(id);
 if(op.getPid()>0)
{
          model.addRow(new Object[]{op.getPid(),op.getProductName(),op.getSupplierName(),op.getProductCategory(),op.getMfgYear(),quantity,op.getPrice()});  
jTextField_productId.setText("0");
jTextField_EnterQunatity.setText("0");

}
grandTotal();
}
else{
                                         JOptionPane.showMessageDialog(null,"Related Product Stock is "+productQuantity);
}
}
else{
                                             JOptionPane.showMessageDialog(null,"Enter Quantity Order");
}
}
else{
                                                 JOptionPane.showMessageDialog(null,"Select Product");
}

    }//GEN-LAST:event_jButton_AddOrderActionPerformed

    private void jTable_SalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_SalesMouseClicked

        // selectData();
        // feesDetails();
    }//GEN-LAST:event_jTable_SalesMouseClicked

    private void jComboBox_ProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_ProductActionPerformed
comboData();
    }//GEN-LAST:event_jComboBox_ProductActionPerformed

    private void jButton_Done1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Done1ActionPerformed



    }//GEN-LAST:event_jButton_Done1ActionPerformed

    private void jTextField_EnterQunatityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField_EnterQunatityMouseClicked
jTextField_EnterQunatity.setText("");
    }//GEN-LAST:event_jTextField_EnterQunatityMouseClicked

    private void jButton_SavePrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SavePrintActionPerformed
orderInfo();
 List<SalesProduct> list=new ArrayList();
        int count=jTable_Sales.getRowCount();
        for(int i=0;i<count;i++)
        {
            SalesProduct ob=new SalesProduct();
            int pid=Integer.parseInt(jTable_Sales.getValueAt(i,0).toString());
            int uid=Global.uid;
                        int qty=Integer.parseInt(jTable_Sales.getValueAt(i,5).toString());
                        Date d=new Date();
                        ob.setPid(pid);
                        ob.setOid(oid);
                        ob.setUid(uid);
                        ob.setQuantity(qty);
                        ob.setDos(d);
                        list.add(ob);
        }
PharmacyController sc=new PharmacyController();
sc.saveData(list);
transaction();
printBill();
    }//GEN-LAST:event_jButton_SavePrintActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_AddOrder;
    private javax.swing.JButton jButton_Customer2;
    private javax.swing.JButton jButton_Delete;
    private javax.swing.JButton jButton_Done1;
    private javax.swing.JButton jButton_NewCustomer1;
    private javax.swing.JButton jButton_SavePrint;
    private javax.swing.JComboBox<String> jComboBox_Product;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_CurrentDate;
    private javax.swing.JLabel jLabel_CurrentDate1;
    private javax.swing.JLabel jLabel_User;
    private javax.swing.JLabel jLabel_address;
    private javax.swing.JLabel jLabel_age1;
    private javax.swing.JLabel jLabel_cid;
    private javax.swing.JLabel jLabel_cname1;
    private javax.swing.JLabel jLabel_grandTotal;
    private javax.swing.JLabel jLabel_orderId;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Sales;
    private javax.swing.JTextField jTextField_EnterQunatity;
    private javax.swing.JTextField jTextField_productId;
    // End of variables declaration//GEN-END:variables
}
