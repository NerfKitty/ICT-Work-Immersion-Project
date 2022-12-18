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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Fernyl Jean
 */
public class inventory extends javax.swing.JPanel {

       
   private static final String username="root";
   private static final String password="";
   private static final String dataConn="jdbc:mysql://localhost:3306/sales and inventory management";
   
   Connection sqlConn= null;
   PreparedStatement pst= null;
   ResultSet rs = null;

    public inventory() {
        initComponents();
        updateDB();
        
        this.setBounds(0,0,1393, 895);
      //  this.setVisible(true);
        this.setLayout(new CardLayout());
        
        
        
        
         stock_table.getTableHeader().setFont(new Font ("Yu Gothic UI", Font.BOLD, 11));
        stock_table.getTableHeader().setOpaque(false);
        stock_table.getTableHeader().setBackground(new Color (20,166,151));
        stock_table.getTableHeader().setForeground(new Color (255,255,255));
        
        TableCellRenderer rendererFromHeader = stock_table.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        
          //set columns to center
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        stock_table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        stock_table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        stock_table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        stock_table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        
        
    }
    
    
    
     // function for displaying the data onto the inventory table 
    
   @SuppressWarnings({"unchecked", "empty-statement"})
   private void updateDB(){
      
       int q,i;
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           sqlConn=DriverManager.getConnection(dataConn,username,password);
           pst = sqlConn.prepareStatement("SELECT purchase_transaction.prod_name, purchase_transaction.prod AS purchase, COALESCE(sale_transaction.prod1,0) AS sale, (purchase_transaction.prod - COALESCE((sale_transaction.prod1),0)) AS available_stock FROM ( SELECT prod_name, SUM(prod_qty) AS prod FROM purchase_transaction GROUP BY prod_name ) purchase_transaction LEFT JOIN ( SELECT prod_name, COALESCE(SUM(prod_qty), 0) AS prod1 FROM sale_transaction GROUP BY prod_name ) sale_transaction ON purchase_transaction.prod_name = sale_transaction.prod_name ORDER BY ((purchase_transaction.prod - COALESCE((sale_transaction.prod1),0))) ASC");
           rs= pst.executeQuery();
           ResultSetMetaData StData= rs.getMetaData();
           
           q = StData.getColumnCount();
           
           DefaultTableModel RecordTable = (DefaultTableModel)stock_table.getModel();
           RecordTable.setRowCount(0);
           
           while(rs.next()){
               
               Vector columnData = new Vector();
               
               for (i=1; i<=q; i++);{
               
              
               columnData.add(rs.getString("prod_name"));
               columnData.add(rs.getString("purchase"));
               columnData.add(rs.getString("sale"));
               columnData.add(rs.getString("available_stock"));
              
               RecordTable.addRow(columnData);
           }
   
     }
            rs.close();
            pst.close();
            sqlConn.close();     
           
       } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(inventory.class.getName()).log(Level.SEVERE, null, ex);
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
           pst = sqlConn.prepareStatement("SELECT purchase_transaction.prod_name, purchase_transaction.prod AS purchase, COALESCE(sale_transaction.prod1,0) AS sale, (purchase_transaction.prod - COALESCE((sale_transaction.prod1),0)) AS available_stock FROM ( SELECT prod_name, SUM(prod_qty) AS prod FROM purchase_transaction GROUP BY prod_name ) purchase_transaction LEFT JOIN ( SELECT prod_name, COALESCE(SUM(prod_qty), 0) AS prod1 FROM sale_transaction GROUP BY prod_name ) sale_transaction ON purchase_transaction.prod_name = sale_transaction.prod_name WHERE purchase_transaction.prod_name LIKE '%"+searchprod+"%'");
           
           rs= pst.executeQuery();
           ResultSetMetaData StData= rs.getMetaData();
           
           q = StData.getColumnCount();
           
           DefaultTableModel RecordTable = (DefaultTableModel)stock_table.getModel();
           RecordTable.setRowCount(0);
           
           while(rs.next()){
               
               Vector columnData = new Vector();
               
               for (i=1; i<=q; i++);{
               
               columnData.add(rs.getString("prod_name"));
               columnData.add(rs.getString("purchase"));
               columnData.add(rs.getString("sale"));
               columnData.add(rs.getString("available_stock"));
                 
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
        stock_table = new javax.swing.JTable();
        searchbar_inventory = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        refreshbtn = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(238, 238, 238));
        jPanel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(238, 238, 238));
        jPanel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 77, 69));
        jLabel1.setText("INVENTORY");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setOpaque(false);

        stock_table.setBackground(new java.awt.Color(255, 255, 255));
        stock_table.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        stock_table.setForeground(new java.awt.Color(51, 70, 112));
        stock_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PRODUCT NAME", "TOTAL PURCHASE", "SOLD ITEMS", "AVAILABLE STOCKS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        stock_table.setRequestFocusEnabled(false);
        stock_table.setRowHeight(20);
        stock_table.setSelectionBackground(new java.awt.Color(255, 213, 116));
        stock_table.setSelectionForeground(new java.awt.Color(51, 70, 112));
        stock_table.setShowHorizontalLines(false);
        stock_table.setShowVerticalLines(false);
        stock_table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(stock_table);
        if (stock_table.getColumnModel().getColumnCount() > 0) {
            stock_table.getColumnModel().getColumn(0).setResizable(false);
            stock_table.getColumnModel().getColumn(1).setResizable(false);
            stock_table.getColumnModel().getColumn(2).setResizable(false);
            stock_table.getColumnModel().getColumn(3).setResizable(false);
        }

        searchbar_inventory.setBackground(new java.awt.Color(255, 255, 255));
        searchbar_inventory.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        searchbar_inventory.setForeground(new java.awt.Color(51, 70, 112));
        searchbar_inventory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchbar_inventoryKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 70, 112));
        jLabel2.setText("🔍");

        refreshbtn.setBackground(new java.awt.Color(255, 255, 255));
        refreshbtn.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        refreshbtn.setForeground(new java.awt.Color(51, 70, 112));
        refreshbtn.setText("↻");
        refreshbtn.setAlignmentY(0.0F);
        refreshbtn.setBorder(null);
        refreshbtn.setBorderPainted(false);
        refreshbtn.setFocusPainted(false);
        refreshbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchbar_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(refreshbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchbar_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(refreshbtn))
                .addGap(19, 19, 19)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(152, Short.MAX_VALUE))
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

    private void searchbar_inventoryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchbar_inventoryKeyReleased
            
        String searchprod = searchbar_inventory.getText();
        if(searchprod.length()==0){
            updateDB();
        }
        else{
            searchProdname(searchprod);
        }  
    }//GEN-LAST:event_searchbar_inventoryKeyReleased

    private void refreshbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshbtnActionPerformed
       updateDB();
    }//GEN-LAST:event_refreshbtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private com.toedter.components.JSpinField jSpinField1;
    private javax.swing.JButton refreshbtn;
    private javax.swing.JTextField searchbar_inventory;
    private javax.swing.JTable stock_table;
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
