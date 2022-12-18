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
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Fernyl Jean
 */
public class dashboard extends javax.swing.JPanel {

    
     private static final String username="root";
     private static final String password="";
     private static final String dataConn="jdbc:mysql://localhost:3306/sales and inventory management";
   
     Connection sqlConn= null;
     PreparedStatement pst= null;
     ResultSet rs = null;
    
     Color pink;
   
    
    
    public dashboard() {
        initComponents();
        getp_amt();
        gets_amt();
        get_profit();
        get_stockvalue();
        get_stocks();
        UpdateDB();
        get_transaction();
        
        
        
        
        this.setBounds(0,0,1396, 893);
        //this.setVisible(true);
        this.setLayout(new CardLayout());
        
        dashboard_table.getTableHeader().setFont(new Font ("Yu Gothic UI", Font.BOLD, 11));
        dashboard_table.getTableHeader().setOpaque(false);
        dashboard_table.getTableHeader().setBackground(new java.awt.Color (20,166,151));
        dashboard_table.getTableHeader().setForeground(new java.awt.Color (255,255,255));
        
        //set columns to center
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        dashboard_table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        dashboard_table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        dashboard_table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        dashboard_table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        dashboard_table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        dashboard_table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        dashboard_table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        
        
        
        TableCellRenderer rendererFromHeader = dashboard_table.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        
        
        pink = new Color (255,170,170);
        
    }
    
  
 
  
 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
       // to get the purchase amount to be displayed in a box 
    
   private void getp_amt(){
   
     
      try {
          
          Class.forName("com.mysql.cj.jdbc.Driver");
          sqlConn=DriverManager.getConnection(dataConn,username,password);
          
          pst = sqlConn.prepareStatement("select COALESCE(sum(prod_amount), 0) AS purchase_amount from purchase_transaction where prod_type = 'Purchase'");   
          rs= pst.executeQuery();
          
          if (rs.next()){
          //System.out.println("Rate price: "+ rs.getString("purchase_price"));
          //System.out.println("sucessful");
          
          purchase_num.setText("₱ "+rs.getString("purchase_amount"));
          purchase_num.setEditable(false);
          
            rs.close();
            pst.close();
            sqlConn.close();    
          
          }
          
          
      } catch(SQLException | ClassNotFoundException ex){
      }
      

   }
   
    
    
    
    
      
    
     // to get the sale amount to be displayed in a box 
    
    private void gets_amt(){
    
     
      try {
          
          Class.forName("com.mysql.cj.jdbc.Driver");
          sqlConn=DriverManager.getConnection(dataConn,username,password);
          
          pst = sqlConn.prepareStatement("select COALESCE(sum(prod_amount),0) AS sale_amount from sale_transaction where prod_type = 'Sale'");   
          rs= pst.executeQuery();
          
          if (rs.next()){
          //System.out.println("Rate price: "+ rs.getString("purchase_price"));
          //System.out.println("sucessful");
          
          sales_num.setText("₱ "+rs.getString("sale_amount"));
          sales_num.setEditable(false);
          
          }
          
            rs.close();
            pst.close();
            sqlConn.close();    
          
      } catch(SQLException ex){
      }catch(ClassNotFoundException ex){
      }
    
    }
    
    
    
    
    
    
       
    // get profit to be displayed in a box
    
     private void get_profit(){
   
     
      try {
          
          Class.forName("com.mysql.cj.jdbc.Driver");
          sqlConn=DriverManager.getConnection(dataConn,username,password);
          
          pst = sqlConn.prepareStatement("select COALESCE(sum(prod_profit), 0) AS product_profit from sale_transaction where prod_type = 'Sale'");   
          rs= pst.executeQuery();
          
          if (rs.next()){
          //System.out.println("Rate price: "+ rs.getString("purchase_price"));
          //System.out.println("sucessful");
          
          profit_num.setText("₱ "+rs.getString("product_profit"));
          profit_num.setEditable(false);
          
            rs.close();
            pst.close();
            sqlConn.close();    
          
          }
          
          
      } catch(SQLException ex){
      }catch(ClassNotFoundException ex){
      }
      

   }
    
    
    
    
    
             // to get the stock value to be displayed in a box 
    
   private void get_stockvalue(){
   
     
      try {
          
          Class.forName("com.mysql.cj.jdbc.Driver");
          sqlConn=DriverManager.getConnection(dataConn,username,password);
          
          pst = sqlConn.prepareStatement("SELECT COALESCE(SUM(sale_transaction.prod_price)*SUM((purchase_transaction.prod - COALESCE((sale_transaction.prod1),0))),0) AS inv_amount FROM ( SELECT prod_name, SUM(prod_qty) AS prod FROM purchase_transaction GROUP BY prod_name ) purchase_transaction LEFT JOIN ( SELECT prod_name, prod_price, COALESCE(SUM(prod_qty), 0) AS prod1 FROM sale_transaction GROUP BY prod_name ) sale_transaction ON purchase_transaction.prod_name = sale_transaction.prod_name ORDER BY ((purchase_transaction.prod - COALESCE((sale_transaction.prod1),0)))");   
          rs= pst.executeQuery();
          
          if (rs.next()){
          //System.out.println("Rate price: "+ rs.getString("purchase_price"));
          //System.out.println("sucessful");
          
          stockval_num.setText("₱ "+rs.getString("inv_amount"));
          stockval_num.setEditable(false);
          
            rs.close();
            pst.close();
            sqlConn.close();    
          
          }
          
          
      } catch(SQLException ex){
      }catch(ClassNotFoundException ex){
      }
      

   }
    
    
    
   
   
   
     // to get the current stock of all products to be displayed in a box 
    
     private void get_stocks(){
   
     
      try {
          
          Class.forName("com.mysql.cj.jdbc.Driver");
          sqlConn=DriverManager.getConnection(dataConn,username,password);
          
          pst = sqlConn.prepareStatement("SELECT COALESCE((SELECT SUM(prod_qty) FROM purchase_transaction)-(SELECT COALESCE(SUM(prod_qty),0) FROM sale_transaction),0) AS inv_qty");   
          rs= pst.executeQuery();
          
          if (rs.next()){
          //System.out.println("Rate price: "+ rs.getString("purchase_price"));
          //System.out.println("sucessful");
          
          invqty_num.setText(""+rs.getString("inv_qty"));
          invqty_num.setEditable(false);
          
            rs.close();
            pst.close();
            sqlConn.close();    
          
          }
          
          
      } catch(SQLException ex){
      }catch(ClassNotFoundException ex){
      }
      

   }
    
     
     
     
       // function for displaying the data onto the transactions table 
    
   @SuppressWarnings({"unchecked", "empty-statement"})
   private void UpdateDB(){
      
       int q,i;
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           sqlConn=DriverManager.getConnection(dataConn,username,password);
           pst = sqlConn.prepareStatement("SELECT * FROM sale_transaction ORDER BY prod_date DESC");
           
           rs= pst.executeQuery();
           ResultSetMetaData StData= rs.getMetaData();
           
           q = StData.getColumnCount();
           
           DefaultTableModel RecordTable = (DefaultTableModel)dashboard_table.getModel();
           RecordTable.setRowCount(0);
           
           while(rs.next()){
               
               Vector columnData = new Vector();
               
               for (i=1; i<=q; i++);{
               
               columnData.add(rs.getString("prod_code"));
               columnData.add(rs.getString("prod_name"));
               columnData.add(rs.getString("prod_type"));
               columnData.add(rs.getString("prod_qty"));
               columnData.add(rs.getString("prod_price"));
               columnData.add(rs.getString("prod_amount"));
               columnData.add(rs.getString("prod_date"));
               
               RecordTable.addRow(columnData);
           }
   
     }
            rs.close();
            pst.close();
            sqlConn.close();     
           
       } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
       }
       
        
       }
       
    //end function 
    
    
     
     
     
 // to get the current stock of all products to be displayed in a box 
    
     private void get_transaction(){
   
     
      try {
          
          Class.forName("com.mysql.cj.jdbc.Driver");
          sqlConn=DriverManager.getConnection(dataConn,username,password);
          
          pst = sqlConn.prepareStatement("SELECT COUNT(*) AS transaction FROM sale_transaction");   
          //SELECT (SELECT COUNT(*) FROM purchase_transaction)+(SELECT COUNT(*) FROM sale_transaction) AS transaction
          rs= pst.executeQuery();
          
          if (rs.next()){
          //System.out.println("Rate price: "+ rs.getString("purchase_price"));
          //System.out.println("sucessful");
          
          transactions_num.setText(""+rs.getString("transaction"));
          transactions_num.setEditable(false);
          
            rs.close();
            pst.close();
            sqlConn.close();    
          
          }
          
          
      } catch(SQLException ex){
      }catch(ClassNotFoundException ex){
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

        jPanel6 = new javax.swing.JPanel();
        dashboard_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        purchasePanel = new RoundedPanel(23);
        jLabel2 = new javax.swing.JLabel();
        purchase_num = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 =  new RoundedPanel(25);
        jScrollPane1 = new javax.swing.JScrollPane();
        dashboard_table = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        refreshbtn = new javax.swing.JButton();
        jPanel4 =  new RoundedPanel(23);
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        sales_num = new javax.swing.JTextField();
        jPanel5 =  new RoundedPanel(23);
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        profit_num = new javax.swing.JTextField();
        invqtyPanel =  new RoundedPanel(23);
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        invqty_num = new javax.swing.JTextField();
        jPanel8 =  new RoundedPanel(23);
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        stockval_num = new javax.swing.JTextField();
        jPanel9 =  new RoundedPanel(23);
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        transactions_num = new javax.swing.JTextField();

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 231, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 206, Short.MAX_VALUE)
        );

        dashboard_panel.setBackground(new java.awt.Color(238, 238, 238));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 77, 69));
        jLabel1.setText("DASHBOARD");

        purchasePanel.setBackground(new java.awt.Color(245, 110, 95));
        purchasePanel.setOpaque(false);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("PURCHASE");

        purchase_num.setBackground(new java.awt.Color(245, 110, 95));
        purchase_num.setFont(new java.awt.Font("Yu Gothic UI", 1, 39)); // NOI18N
        purchase_num.setForeground(new java.awt.Color(255, 255, 255));
        purchase_num.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        purchase_num.setText("121232");
        purchase_num.setBorder(null);
        purchase_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchase_numActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 60)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("🛒");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel9.setIconTextGap(0);
        jLabel9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout purchasePanelLayout = new javax.swing.GroupLayout(purchasePanel);
        purchasePanel.setLayout(purchasePanelLayout);
        purchasePanelLayout.setHorizontalGroup(
            purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(purchasePanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(purchase_num, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(purchasePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        purchasePanelLayout.setVerticalGroup(
            purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, purchasePanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(purchasePanelLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(purchase_num, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setOpaque(false);

        dashboard_table.setBackground(new java.awt.Color(255, 255, 255));
        dashboard_table.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        dashboard_table.setForeground(new java.awt.Color(51, 70, 112));
        dashboard_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODE", "PRODUCT NAME", "TYPE", "QUANTTY", "PRICE", "AMOUNT", "DATE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dashboard_table.setRequestFocusEnabled(false);
        dashboard_table.setSelectionBackground(new java.awt.Color(255, 213, 116));
        dashboard_table.setSelectionForeground(new java.awt.Color(51, 70, 112));
        dashboard_table.setShowHorizontalLines(false);
        dashboard_table.setShowVerticalLines(false);
        dashboard_table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(dashboard_table);
        if (dashboard_table.getColumnModel().getColumnCount() > 0) {
            dashboard_table.getColumnModel().getColumn(0).setResizable(false);
            dashboard_table.getColumnModel().getColumn(1).setResizable(false);
            dashboard_table.getColumnModel().getColumn(2).setResizable(false);
            dashboard_table.getColumnModel().getColumn(3).setResizable(false);
            dashboard_table.getColumnModel().getColumn(4).setResizable(false);
            dashboard_table.getColumnModel().getColumn(5).setResizable(false);
            dashboard_table.getColumnModel().getColumn(6).setResizable(false);
        }

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(4, 77, 69));
        jLabel8.setText("  ACTIVITY");

        refreshbtn.setBackground(new java.awt.Color(255, 255, 255));
        refreshbtn.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        refreshbtn.setForeground(new java.awt.Color(4, 77, 69));
        refreshbtn.setText("↻");
        refreshbtn.setBorderPainted(false);
        refreshbtn.setFocusPainted(false);
        refreshbtn.setFocusable(false);
        refreshbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(refreshbtn)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addComponent(refreshbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        jPanel4.setBackground(new java.awt.Color(245, 145, 60));
        jPanel4.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("SALES");

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 50)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText(" ✔");

        sales_num.setBackground(new java.awt.Color(245, 145, 60));
        sales_num.setFont(new java.awt.Font("Yu Gothic UI", 1, 39)); // NOI18N
        sales_num.setForeground(new java.awt.Color(255, 255, 255));
        sales_num.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        sales_num.setText("1312323");
        sales_num.setBorder(null);
        sales_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sales_numActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(sales_num, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(39, 39, 39))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sales_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(85, 155, 217));
        jPanel5.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("PROFIT");

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 50)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("📈");

        profit_num.setBackground(new java.awt.Color(85, 155, 217));
        profit_num.setFont(new java.awt.Font("Yu Gothic UI", 1, 36)); // NOI18N
        profit_num.setForeground(new java.awt.Color(255, 255, 255));
        profit_num.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        profit_num.setText("12324");
        profit_num.setBorder(null);
        profit_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profit_numActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(profit_num, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(26, 26, 26)
                .addComponent(profit_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        invqtyPanel.setBackground(new java.awt.Color(89, 146, 161));
        invqtyPanel.setOpaque(false);
        invqtyPanel.setPreferredSize(new java.awt.Dimension(345, 129));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("INVENTORY QUANTITY");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 53)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("📔");

        invqty_num.setBackground(new java.awt.Color(89, 146, 161));
        invqty_num.setFont(new java.awt.Font("Yu Gothic UI", 1, 39)); // NOI18N
        invqty_num.setForeground(new java.awt.Color(255, 255, 255));
        invqty_num.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        invqty_num.setText("123245");
        invqty_num.setBorder(null);
        invqty_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invqty_numActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout invqtyPanelLayout = new javax.swing.GroupLayout(invqtyPanel);
        invqtyPanel.setLayout(invqtyPanelLayout);
        invqtyPanelLayout.setHorizontalGroup(
            invqtyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(invqtyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(invqtyPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(invqty_num, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(57, 57, 57))
        );
        invqtyPanelLayout.setVerticalGroup(
            invqtyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(invqtyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(invqtyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(invqty_num, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(53, 94, 170));
        jPanel8.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("INVENTORY VALUE");

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("📊");

        stockval_num.setBackground(new java.awt.Color(53, 94, 170));
        stockval_num.setFont(new java.awt.Font("Yu Gothic UI", 1, 36)); // NOI18N
        stockval_num.setForeground(new java.awt.Color(255, 255, 255));
        stockval_num.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        stockval_num.setText("1312234");
        stockval_num.setBorder(null);
        stockval_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockval_numActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(stockval_num, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel13))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stockval_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );

        jPanel9.setBackground(new java.awt.Color(191, 101, 143));
        jPanel9.setOpaque(false);

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("SALE TRANSACTION");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 50)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("🔄");

        transactions_num.setEditable(false);
        transactions_num.setBackground(new java.awt.Color(191, 101, 143));
        transactions_num.setFont(new java.awt.Font("Yu Gothic UI", 1, 36)); // NOI18N
        transactions_num.setForeground(new java.awt.Color(255, 255, 255));
        transactions_num.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        transactions_num.setText("1232444");
        transactions_num.setBorder(null);
        transactions_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transactions_numActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(transactions_num, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(31, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(transactions_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );

        javax.swing.GroupLayout dashboard_panelLayout = new javax.swing.GroupLayout(dashboard_panel);
        dashboard_panel.setLayout(dashboard_panelLayout);
        dashboard_panelLayout.setHorizontalGroup(
            dashboard_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboard_panelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(dashboard_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(dashboard_panelLayout.createSequentialGroup()
                        .addGroup(dashboard_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(purchasePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(invqtyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dashboard_panelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(33, 33, 33)))
                        .addGap(63, 63, 63)
                        .addGroup(dashboard_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(69, 69, 69)
                        .addGroup(dashboard_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(35, 35, 35))
        );
        dashboard_panelLayout.setVerticalGroup(
            dashboard_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboard_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(dashboard_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(purchasePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addGroup(dashboard_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dashboard_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(invqtyPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dashboard_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dashboard_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void purchase_numActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchase_numActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_purchase_numActionPerformed

    private void sales_numActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sales_numActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sales_numActionPerformed

    private void profit_numActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profit_numActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_profit_numActionPerformed

    private void invqty_numActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invqty_numActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_invqty_numActionPerformed

    private void stockval_numActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockval_numActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stockval_numActionPerformed

    private void transactions_numActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transactions_numActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_transactions_numActionPerformed

    private void refreshbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshbtnActionPerformed
       getp_amt();
        gets_amt();
        get_profit();
        get_stockvalue();
        get_stocks();
        UpdateDB();
        get_transaction();
        
    }//GEN-LAST:event_refreshbtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel dashboard_panel;
    private javax.swing.JTable dashboard_table;
    private javax.swing.JPanel invqtyPanel;
    private javax.swing.JTextField invqty_num;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField profit_num;
    private javax.swing.JPanel purchasePanel;
    private javax.swing.JTextField purchase_num;
    private javax.swing.JButton refreshbtn;
    private javax.swing.JTextField sales_num;
    private javax.swing.JTextField stockval_num;
    private javax.swing.JTextField transactions_num;
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
