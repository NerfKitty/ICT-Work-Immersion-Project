/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sales_and_inventory;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class transactions extends javax.swing.JPanel {
    
     private static final String username="root";
     private static final String password="";
     private static final String dataConn="jdbc:mysql://localhost:3306/sales and inventory management";
   
     Connection sqlConn= null;
     PreparedStatement pst= null;
     ResultSet rs = null;
     
      ArrayList prodname = new ArrayList();
     
 
    public transactions() {
        initComponents();
        UpdateDB();
        loadData();
        
        
        this.setBounds(0,0,1393, 895);
       // this.setVisible(true);
        this.setLayout(new CardLayout());
        
          
        TableCellRenderer rendererFromHeader = dashboard_table.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        
        
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
        
        
        
        all_radiobtn.setSelected(true);
        profit_nameMain.setVisible(false);
        
        amount_nameMain.setEditable(false);
        
        
        
        
        
        
    }

    
    
    
        
     // load data to prodname text field 
   
   @SuppressWarnings({"unchecked", "empty-statement"})  
   private void loadData(){
    
    try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            sqlConn=DriverManager.getConnection(dataConn,username,password);
            pst = sqlConn.prepareStatement("select prod_name from prod_manager");
            rs= pst.executeQuery();
            
            while (rs.next()){
                
                String pdname = rs.getString("prod_name");
                
                prodname.add(pdname);
            }
            
            rs.close();
            pst.close();
            sqlConn.close();   
            
    }  catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
       }
    
}
//===================================

  
    
    
    
         
// autocomplete prod name textfield

   
   public void autoComplete (String txt){
   
       String complete = "";
       int start = txt.length();
       int last = txt.length();
       int a;
       
       for (a=0; a<prodname.size(); a++){
       
           if (prodname.get(a).toString().startsWith(txt)){
           
              complete = prodname.get(a).toString();
              last = complete.length();
              break;
           } 

       }
        
       if (last>start) {
          prod_nameMain.setText(complete);
          prod_nameMain.setCaretPosition(last);
          prod_nameMain.moveCaretPosition(start);
       
       }
   
   }

//============================
    
    
    
    
    
    
    

    
    
    
    
    
    
       
 
    
    
    
    
    
    


    
    
          
    // function for displaying the data onto the transactions table 
    
   @SuppressWarnings({"unchecked", "empty-statement"})
   private void UpdateDB(){
      
       int q,i;
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           sqlConn=DriverManager.getConnection(dataConn,username,password);
           pst = sqlConn.prepareStatement("SELECT prod_code, prod_name, prod_type, prod_qty, prod_price, prod_amount, prod_date FROM sale_transaction UNION ALL SELECT prod_code, prod_name, prod_type, prod_qty, prod_price, prod_amount, prod_date FROM purchase_transaction ORDER BY prod_date DESC");
           
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
    
    
   
   
   
   
   
    
    
    
       
    // for search box transactions 
   
    @SuppressWarnings({"unchecked", "empty-statement"})
   private void searchProdname(String searchprod){
       
       int q,i;
       
       if (purchase_radiobtn.isSelected()){
        
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
            sqlConn=DriverManager.getConnection(dataConn,username,password);
           pst = sqlConn.prepareStatement("SELECT `prod_code`, `prod_name`, `prod_type`, `prod_qty`, `prod_price`, `prod_amount`, `prod_date` FROM purchase_transaction WHERE prod_name like '%"+searchprod+"%'");
           
          
            
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
           
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
       }
        
       
       
       }    if (sale_radiobtn.isSelected()){
        
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
            sqlConn=DriverManager.getConnection(dataConn,username,password);
           pst = sqlConn.prepareStatement("SELECT `prod_code`, `prod_name`, `prod_type`, `prod_qty`, `prod_price`, `prod_amount`, `prod_date`FROM sale_transaction WHERE prod_name like '%"+searchprod+"%' ");
           
          
            
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
           
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
       }
        
       
       
       } if (all_radiobtn.isSelected()){
        
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
            sqlConn=DriverManager.getConnection(dataConn,username,password);
           pst = sqlConn.prepareStatement(" SELECT `prod_code`,`prod_name`, `prod_type`, `prod_qty`, `prod_price`, `prod_amount`, `prod_date`FROM sale_transaction WHERE prod_name like '%"+searchprod+"%' UNION ALL SELECT  `prod_code`, `prod_name`, `prod_type`, `prod_qty`, `prod_price`, `prod_amount`, `prod_date` FROM purchase_transaction WHERE prod_name like '%"+searchprod+"%'");
           
          
            
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
           
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
       }
        
       
       
       } 
       
       
       
       
       
       
       
       
   }
   
   //===============================================
   
    
    
     
     // for radio button = purchase 
   
    @SuppressWarnings({"unchecked", "empty-statement"})
   private void radioButtonP(){
       
       int q,i;
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           sqlConn=DriverManager.getConnection(dataConn,username,password);
           pst = sqlConn.prepareStatement("SELECT `prod_code`,`prod_name`, `prod_type`, `prod_qty`, `prod_price`, `prod_amount`, `prod_date` FROM purchase_transaction WHERE prod_type = 'Purchase' ORDER BY prod_date DESC");
           
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
           
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
       }
        
   }
   
   //=============================================
   
    
     // for radio button = sale 
   
    @SuppressWarnings({"unchecked", "empty-statement"})
   private void radioButtonS(){
       
       int q,i;
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           sqlConn=DriverManager.getConnection(dataConn,username,password);
           pst = sqlConn.prepareStatement("SELECT `prod_code`,`prod_name`, `prod_type`, `prod_qty`, `prod_price`, `prod_amount`, `prod_date` FROM sale_transaction WHERE prod_type = 'Sale' ORDER BY prod_date DESC");
           
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
           
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
       }
        
   }
   
   //=============================================
   
    
    
       // for radio button = all 
   
    @SuppressWarnings({"unchecked", "empty-statement"})
   private void radioButtonA(){
       
       int q,i;
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           sqlConn=DriverManager.getConnection(dataConn,username,password);
           pst = sqlConn.prepareStatement("SELECT `prod_code`,`prod_name`, `prod_type`, `prod_qty`, `prod_price`, `prod_amount`, `prod_date` FROM sale_transaction UNION ALL SELECT `prod_code`,`prod_name`, `prod_type`, `prod_qty`, `prod_price`, `prod_amount`, `prod_date` FROM purchase_transaction ORDER BY prod_date DESC");
           
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
           
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
       }
        
   }
   
   //=============================================
   
    
   
   
     
   private boolean  validateFields(){
      
            
       if (prod_nameMain.getText().isEmpty()||quantity_nameMain.getText().isEmpty()||rate_nameMain.getText().isEmpty()|| amount_nameMain.getText().isEmpty()||date_nameMain.getDate()==null || profit_nameMain.getText()==null){
         
         
         //JOptionPane.showMessageDialog(null, "Please provide the following details!");
           //JDialog.setDefaultLookAndFeelDecorated(true);
          
          
           JLabel label = new JLabel("Please provide the following details!");
           label.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
           JOptionPane.showMessageDialog(null,label,"", JOptionPane.OK_OPTION);
         
          
        return false;
       
       }
        return true;
       
   }
   
   
   //===============================================
     
   
   
   
          
     // set transaction field to null after insertion 
      
   private void clearFields(){
 
      
   
    
    prod_nameMain.setText(null);
    type_combox.setSelectedIndex(0);
    quantity_nameMain.setText(null);
    rate_nameMain.setText(null);
    amount_nameMain.setText(null);
    date_nameMain.setDate(null);
     profit_nameMain.setText(null);
}
  
   
   
    
   // validate if there were purchased products to make a sale or not
   
   private boolean check_product(String txt){
   String combo  = type_combox.getSelectedItem().toString();
        
   boolean ver = true;
        
     if (combo.equals("Sale")){
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
             sqlConn=DriverManager.getConnection(dataConn,username,password);
            Statement stm = sqlConn.createStatement();
             
            String sqls = "SELECT IFNULL((SELECT prod_name FROM purchase_transaction WHERE prod_name = '"+txt+"'  LIMIT 1) ,'not found') AS product_name";
            rs = stm.executeQuery(sqls);
             
          
                 if (rs.next()){
                   
                    String prod1= rs.getString("product_name");
                    String prod2 = prod_nameMain.getText();
                    
                    if (prod1.equals(prod2)){
                        
                    System.out.println("Transaction done");
                    
                    } else {
                        
                      ver = false;                 
                      JOptionPane.showMessageDialog(null, "No available stocks for  ' "+txt+" '");
                      clearFields();
                    }
                    
                
                 }
             
        } catch (HeadlessException | ClassNotFoundException | SQLException ex) { }
     }
        
        return ver;
  
  
   
   }
   

         
// validate if stock amount/ purchase quantity of a certain product is sufficient to make a sale

  private boolean stock_sufficient(String txt){
  
      
      
   boolean ver = true;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
             sqlConn=DriverManager.getConnection(dataConn,username,password);
            Statement stm = sqlConn.createStatement();
             
            String sqls = "SELECT purchase_transaction.prod_name, purchase_transaction.prod AS purchase, COALESCE(sale_transaction.prod1,0) AS sale, (purchase_transaction.prod - COALESCE((sale_transaction.prod1),0)) AS available_stock FROM ( SELECT prod_name, SUM(prod_qty) AS prod FROM purchase_transaction GROUP BY prod_name ) purchase_transaction LEFT JOIN ( SELECT prod_name, COALESCE(SUM(prod_qty), 0) AS prod1 FROM sale_transaction GROUP BY prod_name ) sale_transaction ON purchase_transaction.prod_name = sale_transaction.prod_name WHERE purchase_transaction.prod_name= '"+txt+"'";
            rs = stm.executeQuery(sqls);
             
             if(rs.next()){
                 
                 int value1 = Integer.parseInt(rs.getString("available_stock"));
                 int value2 = Integer.parseInt(quantity_nameMain.getText());
                 if (value2>value1){
                 ver = false;    
                 //JOptionPane.showMessageDialog(null, "Insufficient stock for  ' "+txt+"'");
                  JLabel label = new JLabel("Insufficient stock for  ' "+txt+"'");
                  label.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
                  JOptionPane.showMessageDialog(this, label);
                  
                 clearFields();
                 }else if (value2<value1) {
                   System.out.println("transaction may done");
                 }
             }
             
               
             
        } catch (HeadlessException | ClassNotFoundException | SQLException  | NumberFormatException ex) {
       
       
   }
       return ver;
  
  
  
  }
   
   
  
  
  
  
    
  
   
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        type_category = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel12 = new RoundedPanel(25);
        jScrollPane1 = new javax.swing.JScrollPane();
        dashboard_table = new javax.swing.JTable();
        searchbar_dashboard = new javax.swing.JTextField();
        purchase_radiobtn = new javax.swing.JRadioButton();
        sale_radiobtn = new javax.swing.JRadioButton();
        all_radiobtn = new javax.swing.JRadioButton();
        profit_nameMain = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        refreshbtn = new javax.swing.JButton();
        printbtn = new javax.swing.JButton();
        jPanel13 = new RoundedPanel(25);
        prod_nameMain = new javax.swing.JTextField();
        quantity_nameMain = new javax.swing.JTextField();
        rate_nameMain = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        type_combox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        date_nameMain = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        clearbtn_dashboard = new javax.swing.JButton();
        addbtn_dashboard = new javax.swing.JButton();
        updatebtn_dashboard = new javax.swing.JButton();
        deletebtn_dashboard = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        amount_nameMain = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(238, 238, 238));
        jPanel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setOpaque(false);

        dashboard_table.setBackground(new java.awt.Color(255, 255, 255));
        dashboard_table.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        dashboard_table.setForeground(new java.awt.Color(51, 70, 112));
        dashboard_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODE", "PRODUCT NAME", "TYPE", "QUANTITY", "PRICE", "AMOUNT", "DATE"
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
        dashboard_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboard_tableMouseClicked(evt);
            }
        });
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

        searchbar_dashboard.setBackground(new java.awt.Color(255, 255, 255));
        searchbar_dashboard.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        searchbar_dashboard.setForeground(new java.awt.Color(51, 70, 112));
        searchbar_dashboard.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchbar_dashboardKeyReleased(evt);
            }
        });

        purchase_radiobtn.setBackground(new java.awt.Color(255, 255, 255));
        type_category.add(purchase_radiobtn);
        purchase_radiobtn.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        purchase_radiobtn.setForeground(new java.awt.Color(51, 70, 112));
        purchase_radiobtn.setText("PURCHASE");
        purchase_radiobtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchase_radiobtnActionPerformed(evt);
            }
        });

        sale_radiobtn.setBackground(new java.awt.Color(255, 255, 255));
        type_category.add(sale_radiobtn);
        sale_radiobtn.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        sale_radiobtn.setForeground(new java.awt.Color(51, 70, 112));
        sale_radiobtn.setText("SALE");
        sale_radiobtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sale_radiobtnActionPerformed(evt);
            }
        });

        all_radiobtn.setBackground(new java.awt.Color(255, 255, 255));
        type_category.add(all_radiobtn);
        all_radiobtn.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        all_radiobtn.setForeground(new java.awt.Color(51, 70, 112));
        all_radiobtn.setText("ALL");
        all_radiobtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                all_radiobtnActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 70, 112));
        jLabel16.setText("🔍");

        refreshbtn.setBackground(new java.awt.Color(255, 255, 255));
        refreshbtn.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        refreshbtn.setForeground(new java.awt.Color(51, 70, 112));
        refreshbtn.setText("↻");
        refreshbtn.setBorderPainted(false);
        refreshbtn.setFocusPainted(false);
        refreshbtn.setFocusable(false);
        refreshbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshbtnActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(profit_nameMain, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(purchase_radiobtn)
                .addGap(18, 18, 18)
                .addComponent(sale_radiobtn)
                .addGap(18, 18, 18)
                .addComponent(all_radiobtn)
                .addGap(24, 24, 24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 852, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchbar_dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 442, Short.MAX_VALUE)
                        .addComponent(printbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshbtn)))
                .addGap(17, 17, 17))
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(printbtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(refreshbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchbar_dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 9, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(purchase_radiobtn)
                    .addComponent(sale_radiobtn)
                    .addComponent(all_radiobtn)
                    .addComponent(profit_nameMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setOpaque(false);

        prod_nameMain.setBackground(new java.awt.Color(255, 255, 255));
        prod_nameMain.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        prod_nameMain.setForeground(new java.awt.Color(51, 70, 112));
        prod_nameMain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                prod_nameMainKeyReleased(evt);
            }
        });

        quantity_nameMain.setBackground(new java.awt.Color(255, 255, 255));
        quantity_nameMain.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        quantity_nameMain.setForeground(new java.awt.Color(51, 70, 112));
        quantity_nameMain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                quantity_nameMainKeyReleased(evt);
            }
        });

        rate_nameMain.setBackground(new java.awt.Color(255, 255, 255));
        rate_nameMain.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        rate_nameMain.setForeground(new java.awt.Color(51, 70, 112));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 77, 69));
        jLabel1.setText("PRODUCT NAME");

        type_combox.setBackground(new java.awt.Color(255, 255, 255));
        type_combox.setEditable(true);
        type_combox.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        type_combox.setForeground(new java.awt.Color(51, 70, 112));
        type_combox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Purchase", "Sale" }));
        type_combox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                type_comboxActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(4, 77, 69));
        jLabel2.setText("TRANSACTION TYPE");

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 1, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(4, 77, 69));
        jLabel3.setText("QUANTITY");

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(4, 77, 69));
        jLabel4.setText("PRICE");

        date_nameMain.setDateFormatString("yyyy-MM-dd");
        date_nameMain.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 1, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(4, 77, 69));
        jLabel5.setText("DATE");

        clearbtn_dashboard.setBackground(new java.awt.Color(20, 166, 151));
        clearbtn_dashboard.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        clearbtn_dashboard.setForeground(new java.awt.Color(255, 255, 255));
        clearbtn_dashboard.setText("CLEAR");
        clearbtn_dashboard.setBorderPainted(false);
        clearbtn_dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearbtn_dashboardActionPerformed(evt);
            }
        });

        addbtn_dashboard.setBackground(new java.awt.Color(20, 166, 151));
        addbtn_dashboard.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        addbtn_dashboard.setForeground(new java.awt.Color(255, 255, 255));
        addbtn_dashboard.setText("ADD");
        addbtn_dashboard.setBorderPainted(false);
        addbtn_dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtn_dashboardActionPerformed(evt);
            }
        });

        updatebtn_dashboard.setBackground(new java.awt.Color(20, 166, 151));
        updatebtn_dashboard.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        updatebtn_dashboard.setForeground(new java.awt.Color(255, 255, 255));
        updatebtn_dashboard.setText("UPDATE");
        updatebtn_dashboard.setBorderPainted(false);
        updatebtn_dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtn_dashboardActionPerformed(evt);
            }
        });

        deletebtn_dashboard.setBackground(new java.awt.Color(20, 166, 151));
        deletebtn_dashboard.setFont(new java.awt.Font("Yu Gothic UI", 1, 11)); // NOI18N
        deletebtn_dashboard.setForeground(new java.awt.Color(255, 255, 255));
        deletebtn_dashboard.setText("DELETE");
        deletebtn_dashboard.setBorderPainted(false);
        deletebtn_dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtn_dashboardActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(4, 77, 69));
        jLabel9.setText("TRANSACTION DETAILS");

        amount_nameMain.setBackground(new java.awt.Color(255, 255, 255));
        amount_nameMain.setFont(new java.awt.Font("Yu Gothic UI", 1, 36)); // NOI18N
        amount_nameMain.setForeground(new java.awt.Color(51, 70, 112));
        amount_nameMain.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        amount_nameMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amount_nameMainActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 1, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(4, 77, 69));
        jLabel15.setText("₱");
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prod_nameMain, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                            .addComponent(clearbtn_dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(addbtn_dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(updatebtn_dashboard)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(deletebtn_dashboard))
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(type_combox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(quantity_nameMain, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(rate_nameMain, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(date_nameMain, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(amount_nameMain, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)))
                .addGap(31, 31, 31))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(44, 44, 44)
                .addComponent(prod_nameMain, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(type_combox, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(34, 34, 34)
                .addComponent(quantity_nameMain, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(34, 34, 34)
                .addComponent(rate_nameMain, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(34, 34, 34)
                .addComponent(date_nameMain, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amount_nameMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addbtn_dashboard)
                    .addComponent(updatebtn_dashboard)
                    .addComponent(clearbtn_dashboard)
                    .addComponent(deletebtn_dashboard))
                .addGap(32, 32, 32))
        );

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(4, 77, 69));
        jLabel6.setText("TRANSACTIONS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(162, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void sale_radiobtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sale_radiobtnActionPerformed
          if (sale_radiobtn.isSelected()){
      
           radioButtonS();
      
         }
      
    }//GEN-LAST:event_sale_radiobtnActionPerformed

    private void searchbar_dashboardKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchbar_dashboardKeyReleased
           String searchprod = searchbar_dashboard.getText();
        
        if (purchase_radiobtn.isSelected()&&searchprod.length()==0){
         
          radioButtonP();
          all_radiobtn.setSelected(true);
     
          UpdateDB();
            
            } else if (sale_radiobtn.isSelected()&&searchprod.length()==0){
         
          radioButtonS();
          all_radiobtn.setSelected(true);
     
          UpdateDB();
            
            }else if (all_radiobtn.isSelected()&&searchprod.length()==0){
         
          radioButtonA();
          all_radiobtn.setSelected(true);
     
          UpdateDB();}
        
        
             else{
             searchProdname(searchprod);
             }  
    }//GEN-LAST:event_searchbar_dashboardKeyReleased

    private void purchase_radiobtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchase_radiobtnActionPerformed
             if (purchase_radiobtn.isSelected()){
      
             radioButtonP();
      
        }
      
    }//GEN-LAST:event_purchase_radiobtnActionPerformed

    private void all_radiobtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_all_radiobtnActionPerformed
          if (all_radiobtn.isSelected()){
      
          radioButtonA();
      
          }
      
    }//GEN-LAST:event_all_radiobtnActionPerformed

    private void prod_nameMainKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prod_nameMainKeyReleased

        switch (evt.getKeyCode()){
          case KeyEvent.VK_BACK_SPACE:
              break;
              
          case KeyEvent.VK_ENTER:
              prod_nameMain.setText(prod_nameMain.getText());
              break;
          default:
          EventQueue.invokeLater( new Runnable() {
          @Override
          public void run() {
             String txt = prod_nameMain.getText();
             autoComplete(txt);
          }
      });
 }
                    
                 
    }//GEN-LAST:event_prod_nameMainKeyReleased

    private void type_comboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_type_comboxActionPerformed
             String combo = type_combox.getSelectedItem().toString();
        
      if (combo.equals("Purchase")){
      
      try {
          
          Class.forName("com.mysql.cj.jdbc.Driver");
          sqlConn=DriverManager.getConnection(dataConn,username,password);
          String txt = prod_nameMain.getText();
          pst = sqlConn.prepareStatement("SELECT purchase_price FROM prod_manager WHERE prod_name = '"+txt+"'");   
          rs= pst.executeQuery();
          
          if (rs.next()){
          //System.out.println("Rate price: "+ rs.getString("purchase_price"));
          //System.out.println("sucessful");
          
          rate_nameMain.setText(rs.getString("purchase_price"));
          rate_nameMain.setEditable(false);
          
          
          
          }
          
           rs.close();
           pst.close();
           sqlConn.close();  
          
          
      } catch(SQLException | ClassNotFoundException ex){
      }
      
      
     
      
      } else if (combo.equals("Sale")){
          
      
      try {
      
          Class.forName("com.mysql.cj.jdbc.Driver");
          sqlConn=DriverManager.getConnection(dataConn,username,password);
          String txt = prod_nameMain.getText();
          
          pst = sqlConn.prepareStatement("SELECT sale_price FROM prod_manager WHERE prod_name = '"+txt+"'");   
          rs= pst.executeQuery();
         
          
          if (rs.next()){
          //System.out.println("Rate price: "+ rs.getString("sale_price"));
          //System.out.println("sucessful");
          rate_nameMain.setText(rs.getString("sale_price"));
          rate_nameMain.setEditable(false);
         
      
          }
          
           rs.close();
           pst.close();
           sqlConn.close();  
          
      } catch(SQLException | ClassNotFoundException ex){
      }
      
      
      
      } else if (combo.equals("Select")){
      
        rate_nameMain.setText("");
          
          rate_nameMain.setEditable(false);
      
      }
      
    }//GEN-LAST:event_type_comboxActionPerformed

    private void quantity_nameMainKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantity_nameMainKeyReleased
        String combo = type_combox.getSelectedItem().toString();
        String txt = prod_nameMain.getText();
        try{
            
          int a = Integer.parseInt(quantity_nameMain.getText()); //quantity 
          int b = Integer.parseInt(rate_nameMain.getText()); // price current
          int amt = a*b;//quantity x price = amount 
        
          
          amount_nameMain.setText(""+amt);
          amount_nameMain.setEditable(false);
   
         
          
       }catch(NumberFormatException ex ){
       
       } 
        
        
        
        if (combo.equals("Sale")){
        
        try {
        
           Class.forName("com.mysql.cj.jdbc.Driver");
          sqlConn=DriverManager.getConnection(dataConn,username,password);
          
         
          pst = sqlConn.prepareStatement("SELECT IFNULL((SELECT purchase_price FROM prod_manager WHERE prod_name = '"+txt+"' LIMIT 1) ,0) AS proffit");   
          rs= pst.executeQuery();
          
          if (rs.next()){
          
          int sale_amount = Integer.parseInt(amount_nameMain.getText());
          int purchase_price = Integer.parseInt(rs.getString("proffit"));
          int sale_qty = Integer.parseInt(quantity_nameMain.getText());
          
          int prof = sale_amount - (purchase_price*sale_qty);
          
          profit_nameMain.setText(""+prof);
          profit_nameMain.setEditable(false);
          
      
        
          
          
          }
        
        
        
        }catch(SQLException | ClassNotFoundException | NumberFormatException ex ){}
         
   
        
        
        
        }//
        
        else if (combo.equals("Purchase")){
        
        
        }
    }//GEN-LAST:event_quantity_nameMainKeyReleased

    private void clearbtn_dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearbtn_dashboardActionPerformed
       prod_nameMain.setText(null);
       type_combox.setSelectedIndex(0);
       quantity_nameMain.setText(null);
       rate_nameMain.setText(null);
       amount_nameMain.setText(null);
       date_nameMain.setDate(null);
       profit_nameMain.setText(null);
    
       UpdateDB();
       
    }//GEN-LAST:event_clearbtn_dashboardActionPerformed

    private void addbtn_dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtn_dashboardActionPerformed
       String combo  = type_combox.getSelectedItem().toString();
    String txt = prod_nameMain.getText();
    
    if (combo.equals("Purchase")||combo.equals("Select")){
    
    
      if (validateFields()){
     
        try{
        
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
            }
          
            
            sqlConn=DriverManager.getConnection(dataConn,username,password);
            pst = sqlConn.prepareStatement("INSERT INTO purchase_transaction (prod_name,prod_type,prod_qty,prod_price,prod_amount,prod_date) VALUES (?,?,?,?,?,?)");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(date_nameMain.getDate());
           
           
            pst.setString(1 , prod_nameMain.getText());
            pst.setString(2 , (String) type_combox.getSelectedItem());
            pst.setString(3 , quantity_nameMain.getText());
            pst.setString(4 , rate_nameMain.getText());
            pst.setString(5 , amount_nameMain.getText());
            pst.setString(6 , date);
           
            pst.executeUpdate();
            JLabel label = new JLabel("   Items added successfully.");
            label.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
            JOptionPane.showMessageDialog(this, label);
            
          
             UpdateDB();
             
             clearFields();
           
             
            
            
           
             if (purchase_radiobtn.isSelected()){
      
             radioButtonP();
             purchase_radiobtn.setSelected(true);
      
            } else if (sale_radiobtn.isSelected()){
            
            radioButtonS();
            sale_radiobtn.setSelected(true);
            
            }else if (all_radiobtn.isSelected()){
            
            radioButtonA();
            all_radiobtn.setSelected(true);
            
            }else{}
             
             
            
             
            rs.close();
            pst.close();
            sqlConn.close();   
            
            
            // if the type is purchase the stock must be added
            
            
            
            
            
        }
            catch (SQLException ex) {
            Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
             
        
        } catch(ArrayIndexOutOfBoundsException ex){}
        
     }
     
    
    } // 
     
    else if (combo.equals("Sale")||combo.equals("Select")){
    
    
      if (validateFields()){
     
        try{
        
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
            }
          
            
            sqlConn=DriverManager.getConnection(dataConn,username,password);
            pst = sqlConn.prepareStatement("INSERT INTO sale_transaction (prod_name,prod_type,prod_qty,prod_price,prod_amount,prod_date,prod_profit) VALUES (?,?,?,?,?,?,?)");
            
          
           if(check_product(txt) | stock_sufficient(txt)){
             
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(date_nameMain.getDate());
            
            pst.setString(1 , prod_nameMain.getText());
            pst.setString(2 , (String) type_combox.getSelectedItem());
            pst.setString(3 , quantity_nameMain.getText());
            pst.setString(4 , rate_nameMain.getText());
            pst.setString(5 , amount_nameMain.getText());
            pst.setString(6 , date);
            pst.setString(7, profit_nameMain.getText());
            
            pst.executeUpdate();
            
            JLabel label = new JLabel("   Items added successfully.");
            label.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
            JOptionPane.showMessageDialog(this, label);
            
            
            UpdateDB();
           
            clearFields();
            
           }
           
            
             if (purchase_radiobtn.isSelected()){
      
             radioButtonP();
             purchase_radiobtn.setSelected(true);
      
            } else if (sale_radiobtn.isSelected()){
            
            radioButtonS();
            sale_radiobtn.setSelected(true);
            
            }else if (all_radiobtn.isSelected()){
            
            radioButtonA();
            all_radiobtn.setSelected(true);
            
            }else{}
             
             
            
             
            rs.close();
            pst.close();
            sqlConn.close();   
            
            
        }
            catch (SQLException ex) {
            Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
             
        
        }
        catch(ArrayIndexOutOfBoundsException | NullPointerException ex){}
     }
     
    
    } // 
    }//GEN-LAST:event_addbtn_dashboardActionPerformed

    private void updatebtn_dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtn_dashboardActionPerformed
       DefaultTableModel RecordTable = (DefaultTableModel)dashboard_table.getModel();
     int SelectedRows = dashboard_table.getSelectedRow();
     String combo = type_combox.getSelectedItem().toString();
     
     
     
     if (combo.equals("Purchase") || combo.equals("Select")){
     
                if (validateFields()){
     
            try{
        
                try {
                     Class.forName("com.mysql.cj.jdbc.Driver");
                    } catch (ClassNotFoundException ex) {
                     Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
                    }
          
            String a = RecordTable.getValueAt(SelectedRows,0).toString();
            sqlConn=DriverManager.getConnection(dataConn,username,password);
            pst = sqlConn.prepareStatement("UPDATE purchase_transaction SET prod_name = ?,prod_type = ?,prod_qty= ?,prod_price= ?,prod_amount =?,prod_date=? WHERE prod_code = '"+a+"'");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(date_nameMain.getDate());
            
            pst.setString(1 , prod_nameMain.getText());
            pst.setString(2 , (String) type_combox.getSelectedItem());
            pst.setString(3 , quantity_nameMain.getText());
            pst.setString(4 , rate_nameMain.getText());
            pst.setString(5 , amount_nameMain.getText());
            pst.setString(6 , date);
            
            
            
            pst.executeUpdate();
            
            JLabel label = new JLabel(" Items updated successfully.");
            label.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
            JOptionPane.showMessageDialog(null,label,"", JOptionPane.OK_OPTION);
            
           
            UpdateDB();
           
          
            clearFields();
            
            rs.close();
            pst.close();
            sqlConn.close();  
        }
            catch (SQLException ex) {
            Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
             
        
        }
        
     }
     
     
     
     
     }// 
        
     else if (combo.equals("Sale") || combo.equals("Select")){
     
                if (validateFields()){
     
                 try{
        
                      try {
                     Class.forName("com.mysql.cj.jdbc.Driver");
                    } catch (ClassNotFoundException ex) {
                     Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
                    }
          
            String a = RecordTable.getValueAt(SelectedRows,0).toString();
            sqlConn=DriverManager.getConnection(dataConn,username,password);
            pst = sqlConn.prepareStatement("UPDATE sale_transaction SET prod_name = ?, prod_type = ?,prod_qty= ?,prod_price= ?,prod_amount =?,prod_date=?, prod_profit = ? WHERE prod_code = '"+a+"'");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(date_nameMain.getDate());
            
            pst.setString(1 , prod_nameMain.getText());
            pst.setString(2 , (String) type_combox.getSelectedItem());
            pst.setString(3 , quantity_nameMain.getText());
            pst.setString(4 , rate_nameMain.getText());
            pst.setString(5 , amount_nameMain.getText());
            pst.setString(6 , date);
            pst.setString(7, profit_nameMain.getText());
            
            
            pst.executeUpdate();
            JLabel label = new JLabel(" Items updated succesfully.");
            label.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
            JOptionPane.showMessageDialog(this, label);
            
            UpdateDB();
          
            clearFields();
            
            rs.close();
            pst.close();
            sqlConn.close();  
        }
            catch (SQLException ex) {
            Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
             
        
        }
        
     }
     
     
     
     
     }// 
     
     
     
    }//GEN-LAST:event_updatebtn_dashboardActionPerformed

    private void deletebtn_dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtn_dashboardActionPerformed
       DefaultTableModel RecordTable = (DefaultTableModel)dashboard_table.getModel();
     int SelectedRows = dashboard_table.getSelectedRow();
     String combo = type_combox.getSelectedItem().toString();
     
    
     if (combo.equals("Purchase") || combo.equals("Select")){
     
          if (validateFields()){
             try{
             
            
          
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String a = RecordTable.getValueAt(SelectedRows,0).toString();
            
            JLabel label2 = new JLabel("   Delete this item?");
            label2.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
            
            int deleteItem =  JOptionPane.showConfirmDialog(null, label2, "WARNING!",JOptionPane.YES_NO_OPTION);
            
            if (deleteItem==JOptionPane.YES_OPTION){
                
            sqlConn=DriverManager.getConnection(dataConn,username,password);
            pst = sqlConn.prepareStatement("DELETE FROM  purchase_transaction WHERE prod_code = '"+a+"'");
               prod_nameMain.setText("");
               type_combox.setSelectedIndex(0);
               quantity_nameMain.setText("");
               rate_nameMain.setText("");
               amount_nameMain.setText("");
               date_nameMain.setDate(null);
    
           
            
            pst.executeUpdate();
            
            JLabel label1 = new JLabel(" Items updated successfully.");
            label1.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
            JOptionPane.showMessageDialog(null,label1,"", JOptionPane.OK_OPTION);
            
            
           
            UpdateDB();
           
          
            clearFields();
                
                
            }
            
            rs.close();
            pst.close();
            sqlConn.close();  
           
        }
            catch (SQLException ex) {
            Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
        }
          
         
     }
     
     
     }//
     
      
    else if (combo.equals("Sale") || combo.equals("Select")){
     
          if (validateFields()){
             try{
             
            
          
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
            }
            String a = RecordTable.getValueAt(SelectedRows,0).toString();
            
            JLabel label2 = new JLabel("   Delete this item?");
            label2.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
            
            int deleteItem =  JOptionPane.showConfirmDialog(null, label2, "WARNING!",JOptionPane.YES_NO_OPTION);
            
            if (deleteItem==JOptionPane.YES_OPTION){
                
            sqlConn=DriverManager.getConnection(dataConn,username,password);
            pst = sqlConn.prepareStatement("DELETE FROM  sale_transaction WHERE prod_code = '"+a+"'");
               prod_nameMain.setText("");
               type_combox.setSelectedIndex(0);
               quantity_nameMain.setText("");
                rate_nameMain.setText("");
                amount_nameMain.setText("");
                date_nameMain.setDate(null);
    
           
            
            pst.executeUpdate();
           
            JLabel label1 = new JLabel(" Items updated successfully.");
            label1.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
            JOptionPane.showMessageDialog(this, label1);
            
            UpdateDB();
           
            clearFields();
                
                
            }
            
            rs.close();
            pst.close();
            sqlConn.close();  
           
        }
            catch (SQLException ex) {
            Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
        }
          
         
     }
     
     
     }//
    }//GEN-LAST:event_deletebtn_dashboardActionPerformed

    private void dashboard_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboard_tableMouseClicked
     DefaultTableModel RecordTable = (DefaultTableModel)dashboard_table.getModel();
     int SelectedRows = dashboard_table.getSelectedRow();
    
           
    try{
    
     prod_nameMain.setText(RecordTable.getValueAt(SelectedRows,1).toString());
     type_combox.setSelectedItem(RecordTable.getValueAt(SelectedRows,2).toString());
     quantity_nameMain.setText(RecordTable.getValueAt(SelectedRows,3).toString());
     rate_nameMain.setText(RecordTable.getValueAt(SelectedRows,4).toString());
     amount_nameMain.setText(RecordTable.getValueAt(SelectedRows,5).toString()); 
     Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)RecordTable.getValueAt(SelectedRows,6));
     date_nameMain.setDate(date);
      
     
    }
    
    catch(ClassCastException ex){
    
    }  catch (ParseException ex) {
           Logger.getLogger(transactions.class.getName()).log(Level.SEVERE, null, ex);
       }
     
    }//GEN-LAST:event_dashboard_tableMouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
       UpdateDB();
       all_radiobtn.setSelected(true);
    }//GEN-LAST:event_jPanel1MouseClicked

    private void refreshbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshbtnActionPerformed
        UpdateDB();
       all_radiobtn.setSelected(true);
       clearFields();
    }//GEN-LAST:event_refreshbtnActionPerformed

    private void printbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printbtnActionPerformed
     
       // MessageFormat footer = new MessageFormat("Page {0 ,number,integer}");
       
       if (purchase_radiobtn.isSelected()){
           
           
        MessageFormat header = new MessageFormat("Purchase Report");
        
        try{
            dashboard_table.print(JTable.PrintMode.FIT_WIDTH,header,null);

        }
        catch(java.awt.print.PrinterException ex){
            System.err.format("No printer available",ex.getMessage());
        }
       
       }// 
       
       else if (sale_radiobtn.isSelected()){
           
           
        MessageFormat header = new MessageFormat("Sales Report");
        
        try{
            dashboard_table.print(JTable.PrintMode.FIT_WIDTH,header,null);

        }
        catch(java.awt.print.PrinterException ex){
            System.err.format("No printer available",ex.getMessage());
        }
       
       }//
       
       if (all_radiobtn.isSelected()){
           
           
        MessageFormat header = new MessageFormat("Sale/Purchase Report");
        
        try{
            dashboard_table.print(JTable.PrintMode.FIT_WIDTH,header,null);

        }
        catch(java.awt.print.PrinterException ex){
            System.err.format("No printer available",ex.getMessage());
        }
       
       }//
       
       
       
       
    }//GEN-LAST:event_printbtnActionPerformed

    private void amount_nameMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amount_nameMainActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amount_nameMainActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addbtn_dashboard;
    private javax.swing.JRadioButton all_radiobtn;
    private javax.swing.JTextField amount_nameMain;
    private javax.swing.JButton clearbtn_dashboard;
    private javax.swing.JTable dashboard_table;
    private com.toedter.calendar.JDateChooser date_nameMain;
    private javax.swing.JButton deletebtn_dashboard;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton printbtn;
    private javax.swing.JTextField prod_nameMain;
    private javax.swing.JTextField profit_nameMain;
    private javax.swing.JRadioButton purchase_radiobtn;
    private javax.swing.JTextField quantity_nameMain;
    private javax.swing.JTextField rate_nameMain;
    private javax.swing.JButton refreshbtn;
    private javax.swing.JRadioButton sale_radiobtn;
    private javax.swing.JTextField searchbar_dashboard;
    private javax.swing.ButtonGroup type_category;
    private javax.swing.JComboBox<String> type_combox;
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
