/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sales_and_inventory;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Fernyl Jean
 */
public class product_manager extends javax.swing.JPanel {

    private static final String username="root";
     private static final String password="";
     private static final String dataConn="jdbc:mysql://localhost:3306/sales and inventory management";
   
     Connection sqlConn= null;
     PreparedStatement pst= null;
     ResultSet rs = null;
     
     
    public product_manager() {
        initComponents();
        UpdateDB();
        
        this.setBounds(0,0,1393, 895);
      //  this.setVisible(true);
        this.setLayout(new CardLayout());
        
        
        ProductManager_table.getTableHeader().setFont(new Font ("Yu Gothic UI", Font.BOLD, 11));
        ProductManager_table.getTableHeader().setOpaque(false);
        ProductManager_table.getTableHeader().setBackground(new java.awt.Color (20,166,151));
        ProductManager_table.getTableHeader().setForeground(new java.awt.Color (255,255,255));
        
        
        TableCellRenderer rendererFromHeader = ProductManager_table.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        
        //set columns to center
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        ProductManager_table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        ProductManager_table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        ProductManager_table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        
       
    }
    
    
          
     //function== display data onto the jtable
    
   @SuppressWarnings({"unchecked", "empty-statement"})
   private void UpdateDB(){
       
       int q,i;
     
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
            sqlConn=DriverManager.getConnection(dataConn,username,password);
           pst = sqlConn.prepareStatement("select * from prod_manager order by prod_name");
           
           rs= pst.executeQuery();
           ResultSetMetaData StData= rs.getMetaData();
           
           q = StData.getColumnCount();
           
           DefaultTableModel RecordTable = (DefaultTableModel)ProductManager_table.getModel();
           RecordTable.setRowCount(0);
           
           while(rs.next()){
               
               Vector columnData = new Vector();
               
               for (i=1; i<=q; i++);{
               
               columnData.add(rs.getString("prod_name"));
               columnData.add(rs.getString("purchase_price"));
               columnData.add(rs.getString("sale_price"));
                 
               RecordTable.addRow(columnData);
           }
   
     }
            
            rs.close();
            pst.close();
            sqlConn.close();  
           
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(product_manager.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(product_manager.class.getName()).log(Level.SEVERE, null, ex);
       }
        
   }
    
    
    //end function 
    
    
    
    
         // searchbox
    
   @SuppressWarnings({"unchecked", "empty-statement"})
   private void searchProdname(String searchprod){
       
       int q,i;
       
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
            sqlConn=DriverManager.getConnection(dataConn,username,password);
           pst = sqlConn.prepareStatement("select * from prod_manager where prod_name like '%"+searchprod+"%'");
           
           rs= pst.executeQuery();
           ResultSetMetaData StData= rs.getMetaData();
           
           q = StData.getColumnCount();
           
           DefaultTableModel RecordTable = (DefaultTableModel)ProductManager_table.getModel();
           RecordTable.setRowCount(0);
           
           while(rs.next()){
               
               Vector columnData = new Vector();
               
               for (i=1; i<=q; i++);{
               
               columnData.add(rs.getString("prod_name"));
               columnData.add(rs.getString("purchase_price"));
               columnData.add(rs.getString("sale_price"));
                 
               RecordTable.addRow(columnData);
           }
   
     }
            
            rs.close();
            pst.close();
            sqlConn.close();    
        
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(product_manager.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(product_manager.class.getName()).log(Level.SEVERE, null, ex);
       }
        
   }
   
   
    
    
    
    
    
    
      
   // validate if the textfields are empty or not
   private boolean validateField(){
       
       if (prodnameTf_main.getText().isEmpty()| prchspriceTf_main.getText().isEmpty() 
               | salepriceTf_main.getText().isEmpty()){
           
           
           JLabel label = new JLabel("Please provide the following details!");
           label.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
           JOptionPane.showMessageDialog(null,label,"", JOptionPane.OK_OPTION);
           
            clearFields();
            return false;
            
       }       
       
      
       
       return true;
   }
   
  //end validation
   
    
    
    
          
    // set textfields to empty/null after insertion 
    
   private void clearFields(){
    
    prodnameTf_main.setText(null);
    prchspriceTf_main.setText(null);
    salepriceTf_main.setText(null);
    
    
}
   

    
      
     //validate if the user enters item that is already exists in database 

   private boolean verifyProd(String adc) {
    
     
     
       boolean ver = true;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
             sqlConn=DriverManager.getConnection(dataConn,username,password);
             Statement stm = sqlConn.createStatement();
             
             String sqls = "select * from prod_manager where prod_name = '"+adc+"'";
             rs = stm.executeQuery(sqls);
             
             if (rs.next()){
                 ver = false;
                 JOptionPane.showMessageDialog(null, "This item already exists!");
                 clearFields();
             }
             
               
             
        } catch (HeadlessException | ClassNotFoundException | SQLException ex) {
       
       
   }
       return ver;
   }
   
   //end validation
    
    
  
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinField1 = new com.toedter.components.JSpinField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new RoundedPanel(25);
        jScrollPane1 = new javax.swing.JScrollPane();
        ProductManager_table = new javax.swing.JTable();
        searchbar_prodmngr = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        printbtn = new javax.swing.JButton();
        jPanel5 = new RoundedPanel(25);
        prchspriceTf_main = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new RoundedPanel(25);
        prodnameTf_main = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new RoundedPanel(25);
        salepriceTf_main = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        clearbtn_dashboard = new javax.swing.JButton();
        addbtn_dashboard = new javax.swing.JButton();
        updatebtn_dashboard = new javax.swing.JButton();
        deletebtn_dashboard = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(238, 238, 238));
        jPanel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(238, 238, 238));
        jPanel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel1.setBackground(new java.awt.Color(4, 77, 69));
        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 77, 69));
        jLabel1.setText("PRODUCT MANAGER");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setOpaque(false);

        ProductManager_table.setBackground(new java.awt.Color(255, 255, 255));
        ProductManager_table.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        ProductManager_table.setForeground(new java.awt.Color(51, 70, 112));
        ProductManager_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PRODUCT NAME", "PURCHASE PRICE", "SALE PRICE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ProductManager_table.setRequestFocusEnabled(false);
        ProductManager_table.setSelectionBackground(new java.awt.Color(255, 213, 116));
        ProductManager_table.setSelectionForeground(new java.awt.Color(51, 70, 112));
        ProductManager_table.setShowHorizontalLines(false);
        ProductManager_table.setShowVerticalLines(false);
        ProductManager_table.getTableHeader().setReorderingAllowed(false);
        ProductManager_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProductManager_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ProductManager_table);
        if (ProductManager_table.getColumnModel().getColumnCount() > 0) {
            ProductManager_table.getColumnModel().getColumn(0).setResizable(false);
            ProductManager_table.getColumnModel().getColumn(1).setResizable(false);
            ProductManager_table.getColumnModel().getColumn(2).setResizable(false);
        }

        searchbar_prodmngr.setBackground(new java.awt.Color(255, 255, 255));
        searchbar_prodmngr.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        searchbar_prodmngr.setForeground(new java.awt.Color(51, 70, 112));
        searchbar_prodmngr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbar_prodmngrActionPerformed(evt);
            }
        });
        searchbar_prodmngr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchbar_prodmngrKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 70, 112));
        jLabel6.setText("🔍");

        printbtn.setBackground(new java.awt.Color(255, 255, 255));
        printbtn.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        printbtn.setForeground(new java.awt.Color(4, 77, 69));
        printbtn.setText("📄");
        printbtn.setBorderPainted(false);
        printbtn.setFocusPainted(false);
        printbtn.setFocusable(false);
        printbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 895, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchbar_prodmngr, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(printbtn)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(printbtn)
                    .addComponent(searchbar_prodmngr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setOpaque(false);

        prchspriceTf_main.setBackground(new java.awt.Color(255, 255, 255));
        prchspriceTf_main.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        prchspriceTf_main.setForeground(new java.awt.Color(51, 70, 112));
        prchspriceTf_main.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(4, 77, 69));
        jLabel4.setText("PURCHASE PRICE");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(prchspriceTf_main, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(prchspriceTf_main, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setOpaque(false);

        prodnameTf_main.setBackground(new java.awt.Color(255, 255, 255));
        prodnameTf_main.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        prodnameTf_main.setForeground(new java.awt.Color(51, 70, 112));
        prodnameTf_main.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(4, 77, 69));
        jLabel3.setText("PRODUCT NAME");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(prodnameTf_main, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(prodnameTf_main, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setOpaque(false);

        salepriceTf_main.setBackground(new java.awt.Color(255, 255, 255));
        salepriceTf_main.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        salepriceTf_main.setForeground(new java.awt.Color(51, 70, 112));
        salepriceTf_main.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(4, 77, 69));
        jLabel5.setText("SALE PRICE");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(salepriceTf_main, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(salepriceTf_main, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        clearbtn_dashboard.setBackground(new java.awt.Color(20, 166, 151));
        clearbtn_dashboard.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        clearbtn_dashboard.setForeground(new java.awt.Color(255, 255, 255));
        clearbtn_dashboard.setText("CLEAR");
        clearbtn_dashboard.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, java.awt.Color.lightGray, java.awt.Color.lightGray));
        clearbtn_dashboard.setBorderPainted(false);
        clearbtn_dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        clearbtn_dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearbtn_dashboardActionPerformed(evt);
            }
        });

        addbtn_dashboard.setBackground(new java.awt.Color(20, 166, 151));
        addbtn_dashboard.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        addbtn_dashboard.setForeground(new java.awt.Color(255, 255, 255));
        addbtn_dashboard.setText("ADD");
        addbtn_dashboard.setBorderPainted(false);
        addbtn_dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtn_dashboardActionPerformed(evt);
            }
        });

        updatebtn_dashboard.setBackground(new java.awt.Color(20, 166, 151));
        updatebtn_dashboard.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        updatebtn_dashboard.setForeground(new java.awt.Color(255, 255, 255));
        updatebtn_dashboard.setText("UPDATE");
        updatebtn_dashboard.setBorderPainted(false);
        updatebtn_dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtn_dashboardActionPerformed(evt);
            }
        });

        deletebtn_dashboard.setBackground(new java.awt.Color(20, 166, 151));
        deletebtn_dashboard.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        deletebtn_dashboard.setForeground(new java.awt.Color(255, 255, 255));
        deletebtn_dashboard.setText("DELETE");
        deletebtn_dashboard.setBorderPainted(false);
        deletebtn_dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtn_dashboardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(clearbtn_dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addbtn_dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updatebtn_dashboard)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deletebtn_dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1))
                        .addGap(185, 185, 185))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deletebtn_dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updatebtn_dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addbtn_dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clearbtn_dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(186, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void addbtn_dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtn_dashboardActionPerformed
                String adc = prodnameTf_main.getText();
       
     
     if (validateField()){
           
        try{
        
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(product_manager.class.getName()).log(Level.SEVERE, null, ex);
            }
          
            
            sqlConn=DriverManager.getConnection(dataConn,username,password);
            pst = sqlConn.prepareStatement("insert into prod_manager(prod_name, purchase_price,sale_price)values(?,?,?)");
            
           
          if (verifyProd(adc)){
            
            pst.setString(1 , prodnameTf_main.getText());
            pst.setString(2 , prchspriceTf_main.getText());
            pst.setString(3 , salepriceTf_main.getText());
            pst.executeUpdate();
           
             JLabel label = new JLabel("   Items added successfully.");
            label.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
            JOptionPane.showMessageDialog(this, label);
            
            UpdateDB();
            clearFields();
          }
          
            rs.close();
            pst.close();
            sqlConn.close();       
        }
            catch (SQLException ex) {
            Logger.getLogger(product_manager.class.getName()).log(Level.SEVERE, null, ex);
             
        
        }
        }    
  
    }//GEN-LAST:event_addbtn_dashboardActionPerformed

    private void clearbtn_dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearbtn_dashboardActionPerformed
          
    prodnameTf_main.setText(null);
    prchspriceTf_main.setText(null);
    salepriceTf_main.setText(null);
    
    UpdateDB();
    }//GEN-LAST:event_clearbtn_dashboardActionPerformed

    private void searchbar_prodmngrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbar_prodmngrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchbar_prodmngrActionPerformed

    private void searchbar_prodmngrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchbar_prodmngrKeyReleased
          String searchprod = searchbar_prodmngr.getText();
        if(searchprod.length()==0){
            UpdateDB();
        }
        else{
            searchProdname(searchprod);
        }       
        
    }//GEN-LAST:event_searchbar_prodmngrKeyReleased

    private void ProductManager_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductManager_tableMouseClicked
     DefaultTableModel RecordTable = (DefaultTableModel)ProductManager_table.getModel();
     int SelectedRows = ProductManager_table.getSelectedRow();
     
    
     prodnameTf_main.setText(RecordTable.getValueAt(SelectedRows,0).toString());
     prchspriceTf_main.setText(RecordTable.getValueAt(SelectedRows,1).toString());
     salepriceTf_main.setText(RecordTable.getValueAt(SelectedRows,2).toString());
    }//GEN-LAST:event_ProductManager_tableMouseClicked

    private void updatebtn_dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtn_dashboardActionPerformed
     DefaultTableModel RecordTable = (DefaultTableModel)ProductManager_table.getModel();
     int SelectedRows = ProductManager_table.getSelectedRow();
         
      if (validateField()){
            
        
     
        try{
            
            
          
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(product_manager.class.getName()).log(Level.SEVERE, null, ex);
            }
            String a = RecordTable.getValueAt(SelectedRows,0).toString();
            sqlConn=DriverManager.getConnection(dataConn,username,password);
            pst = sqlConn.prepareStatement("update prod_manager set prod_name=?, purchase_price=?,sale_price=? where prod_name = '"+a+"'");
            pst.setString(1 , prodnameTf_main.getText());
            pst.setString(2 , prchspriceTf_main.getText());
            pst.setString(3 , salepriceTf_main.getText());
           
            
            pst.executeUpdate();
           
             JLabel label = new JLabel("   Items updated successfully.");
            label.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
            JOptionPane.showMessageDialog(this, label);
            
            UpdateDB();
            clearFields();
            
            rs.close();
            pst.close();
            sqlConn.close();     
        }
            catch (SQLException ex) {
            Logger.getLogger(product_manager.class.getName()).log(Level.SEVERE, null, ex);
        }
          
      }   
     
     
    }//GEN-LAST:event_updatebtn_dashboardActionPerformed

    private void deletebtn_dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtn_dashboardActionPerformed
               DefaultTableModel RecordTable = (DefaultTableModel)ProductManager_table.getModel();
     int SelectedRows = ProductManager_table.getSelectedRow();
        
     
     if (validateField()){
        try{
            
            
          
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(product_manager.class.getName()).log(Level.SEVERE, null, ex);
            }
            String a = RecordTable.getValueAt(SelectedRows,0).toString();
            
             JLabel label2 = new JLabel("   Delete this item?");
            label2.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
            JOptionPane.showMessageDialog(this, label2);
            
            int deleteItem =  JOptionPane.showConfirmDialog(null, label2, "Warning!",JOptionPane.YES_NO_OPTION);
            
            if (deleteItem==JOptionPane.YES_OPTION){
                
            sqlConn=DriverManager.getConnection(dataConn,username,password);
            pst = sqlConn.prepareStatement("delete from  prod_manager where prod_name = '"+a+"'");
              prodnameTf_main.setText("");
              prchspriceTf_main.setText("");
               salepriceTf_main.setText("");
           
            
            pst.executeUpdate();
           
            
            JLabel label = new JLabel("   Items updated successfully.");
            label.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
            JOptionPane.showMessageDialog(this, label);
            
            UpdateDB();
            clearFields();
                
                
            }
            
            rs.close();
            pst.close();
            sqlConn.close();  
           
        }
            catch (SQLException ex) {
            Logger.getLogger(product_manager.class.getName()).log(Level.SEVERE, null, ex);
        }
          
         
     }
       
    }//GEN-LAST:event_deletebtn_dashboardActionPerformed

    private void printbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printbtnActionPerformed

        // MessageFormat footer = new MessageFormat("Page {0 ,number,integer}");

      MessageFormat header = new MessageFormat("Registered Products");
        
        try{
            ProductManager_table.print(JTable.PrintMode.FIT_WIDTH,header,null);

        }
        catch(java.awt.print.PrinterException ex){
            System.err.format("No printer available",ex.getMessage());
        }

      

    }//GEN-LAST:event_printbtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ProductManager_table;
    private javax.swing.JButton addbtn_dashboard;
    private javax.swing.JButton clearbtn_dashboard;
    private javax.swing.JButton deletebtn_dashboard;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private com.toedter.components.JSpinField jSpinField1;
    private javax.swing.JTextField prchspriceTf_main;
    private javax.swing.JButton printbtn;
    private javax.swing.JTextField prodnameTf_main;
    private javax.swing.JTextField salepriceTf_main;
    private javax.swing.JTextField searchbar_prodmngr;
    private javax.swing.JButton updatebtn_dashboard;
    // End of variables declaration//GEN-END:variables



class RoundedPanel extends JPanel
    {
        private Color backgroundColor;
        private int cornerRadius = 15;
        public RoundedPanel(LayoutManager layout, int radius) {
            super(layout);
            cornerRadius = radius;
        }
        public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
            super(layout);
            cornerRadius = radius;
            backgroundColor = bgColor;
        }
        public RoundedPanel(int radius) {
            super();
            cornerRadius = radius;
            
        }
        public RoundedPanel(int radius, Color bgColor) {
            super();
            cornerRadius = radius;
            backgroundColor = bgColor;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //Draws the rounded panel with borders.
            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
            } else {
                graphics.setColor(getBackground());
            }
            graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint background
            graphics.setColor(getForeground());
//            graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
//             
        }
    }



























}
