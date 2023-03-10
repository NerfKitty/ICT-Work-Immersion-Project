/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sales_and_inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
 
/**
 *
 * @author Fernyl Jean
 * 
 * 
 * 
 */



public final class MainFrame extends javax.swing.JFrame {

    Color ClickedColor,DefaultColor,ClickedForeground,DefaultForeground;
    
    
    private final  dashboard dashboard;
    private final  transactions transactions;
    private final  product_manager product_manager;
    private final  inventory inventory;
    private final  settings settings;
   
    
   
 
    
    
    
    public MainFrame() {
        initComponents();
        
        ImageIcon img = new ImageIcon("C:\\Users\\Fernyl Jean\\Documents\\NetBeansProjects\\Sales_and_Inventory\\src\\imageIcon/logo1.png");
        this.setIconImage(img.getImage());
        
        this.setSize(1699, 936);
        this.setResizable(false);
        
       
        //initalize panels
        
       
       dashboard = new dashboard();
       transactions = new transactions();
       product_manager = new product_manager();
       inventory = new inventory();
       settings = new settings();
     
       
       
      
       
       
        //colors
        
        ClickedColor = new Color (238,238,238);
        DefaultColor = new Color (20,166,151);
        ClickedForeground = new Color(4,77,69);
        DefaultForeground = new Color (255,255,255);
        
          
        contentPanel.add(dashboard);
        contentPanel.add(transactions);
        contentPanel.add(product_manager);
        contentPanel.add(inventory);
        contentPanel.add(settings);
        contentPanel.repaint();
       
        MouseClicks(dashboard);
       
        
      
       
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 25, 25));
       
   }

    
   
     public void MouseClicks(JPanel panel){
    
    
     dashboard.setVisible(false); 
     transactions.setVisible(false);
     product_manager.setVisible(false);
     inventory.setVisible(false);
     settings.setVisible(false);
    
      
      
    panel.setVisible(true);
      
    
    
      
  }
    
 
   
 
     
     
     
     
     
     
     
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentFrame = new javax.swing.JPanel();
        menuPanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        dashboardPanelbtn = new javax.swing.JPanel();
        dashboardLabel = new javax.swing.JLabel();
        productManagerPanelbtn = new javax.swing.JPanel();
        productManagerLabel = new javax.swing.JLabel();
        stocksPanelbtn = new javax.swing.JPanel();
        stocksLabel = new javax.swing.JLabel();
        transactionsPanelbtn = new javax.swing.JPanel();
        transactionsLabel = new javax.swing.JLabel();
        settingsPanelbtn = new javax.swing.JPanel();
        settingsLabel = new javax.swing.JLabel();
        exitPanelbtn = new javax.swing.JPanel();
        exitLabel = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();
        exitButton = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        contentFrame.setBackground(new java.awt.Color(238, 238, 238));

        menuPanel.setBackground(new java.awt.Color(20, 166, 151));

        jSeparator1.setBackground(new java.awt.Color(238, 238, 238));
        jSeparator1.setForeground(new java.awt.Color(238, 238, 238));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("SALES AND INVENTORY MANAGEMENT ");

        jLabel17.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("SOLIS SARI-SARI STORE");

        dashboardPanelbtn.setBackground(new java.awt.Color(238, 238, 238));
        dashboardPanelbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboardPanelbtnMouseClicked(evt);
            }
        });

        dashboardLabel.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        dashboardLabel.setForeground(new java.awt.Color(51, 70, 112));
        dashboardLabel.setText("DASHBOARD");
        dashboardLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboardLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout dashboardPanelbtnLayout = new javax.swing.GroupLayout(dashboardPanelbtn);
        dashboardPanelbtn.setLayout(dashboardPanelbtnLayout);
        dashboardPanelbtnLayout.setHorizontalGroup(
            dashboardPanelbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardPanelbtnLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(dashboardLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dashboardPanelbtnLayout.setVerticalGroup(
            dashboardPanelbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dashboardPanelbtnLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dashboardLabel)
                .addContainerGap())
        );

        productManagerPanelbtn.setBackground(new java.awt.Color(20, 166, 151));
        productManagerPanelbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productManagerPanelbtnMouseClicked(evt);
            }
        });

        productManagerLabel.setBackground(new java.awt.Color(255, 255, 255));
        productManagerLabel.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        productManagerLabel.setForeground(new java.awt.Color(255, 255, 255));
        productManagerLabel.setText("PRODUCT MANAGER");
        productManagerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productManagerLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout productManagerPanelbtnLayout = new javax.swing.GroupLayout(productManagerPanelbtn);
        productManagerPanelbtn.setLayout(productManagerPanelbtnLayout);
        productManagerPanelbtnLayout.setHorizontalGroup(
            productManagerPanelbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productManagerPanelbtnLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(productManagerLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        productManagerPanelbtnLayout.setVerticalGroup(
            productManagerPanelbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productManagerPanelbtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(productManagerLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        stocksPanelbtn.setBackground(new java.awt.Color(20, 166, 151));
        stocksPanelbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stocksPanelbtnMouseClicked(evt);
            }
        });

        stocksLabel.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        stocksLabel.setForeground(new java.awt.Color(255, 255, 255));
        stocksLabel.setText("INVENTORY");
        stocksLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stocksLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout stocksPanelbtnLayout = new javax.swing.GroupLayout(stocksPanelbtn);
        stocksPanelbtn.setLayout(stocksPanelbtnLayout);
        stocksPanelbtnLayout.setHorizontalGroup(
            stocksPanelbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stocksPanelbtnLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(stocksLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        stocksPanelbtnLayout.setVerticalGroup(
            stocksPanelbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stocksPanelbtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(stocksLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        transactionsPanelbtn.setBackground(new java.awt.Color(20, 166, 151));
        transactionsPanelbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transactionsPanelbtnMouseClicked(evt);
            }
        });

        transactionsLabel.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        transactionsLabel.setForeground(new java.awt.Color(255, 255, 255));
        transactionsLabel.setText("TRANSACTIONS");
        transactionsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transactionsLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout transactionsPanelbtnLayout = new javax.swing.GroupLayout(transactionsPanelbtn);
        transactionsPanelbtn.setLayout(transactionsPanelbtnLayout);
        transactionsPanelbtnLayout.setHorizontalGroup(
            transactionsPanelbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transactionsPanelbtnLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(transactionsLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        transactionsPanelbtnLayout.setVerticalGroup(
            transactionsPanelbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transactionsPanelbtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(transactionsLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        settingsPanelbtn.setBackground(new java.awt.Color(20, 166, 151));
        settingsPanelbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsPanelbtnMouseClicked(evt);
            }
        });

        settingsLabel.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        settingsLabel.setForeground(new java.awt.Color(255, 255, 255));
        settingsLabel.setText("SETTINGS");
        settingsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout settingsPanelbtnLayout = new javax.swing.GroupLayout(settingsPanelbtn);
        settingsPanelbtn.setLayout(settingsPanelbtnLayout);
        settingsPanelbtnLayout.setHorizontalGroup(
            settingsPanelbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelbtnLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(settingsLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        settingsPanelbtnLayout.setVerticalGroup(
            settingsPanelbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelbtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(settingsLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        exitPanelbtn.setBackground(new java.awt.Color(20, 166, 151));
        exitPanelbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitPanelbtnMouseClicked(evt);
            }
        });

        exitLabel.setBackground(new java.awt.Color(255, 255, 255));
        exitLabel.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        exitLabel.setForeground(new java.awt.Color(255, 255, 255));
        exitLabel.setText("EXIT");
        exitLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout exitPanelbtnLayout = new javax.swing.GroupLayout(exitPanelbtn);
        exitPanelbtn.setLayout(exitPanelbtnLayout);
        exitPanelbtnLayout.setHorizontalGroup(
            exitPanelbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exitPanelbtnLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(exitLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        exitPanelbtnLayout.setVerticalGroup(
            exitPanelbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exitPanelbtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exitLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dashboardPanelbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(productManagerPanelbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(stocksPanelbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(settingsPanelbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(transactionsPanelbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(exitPanelbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuPanelLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17))
                .addGap(29, 29, 29))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(dashboardPanelbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(transactionsPanelbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(productManagerPanelbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stocksPanelbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(settingsPanelbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(exitPanelbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        contentPanel.setBackground(new java.awt.Color(238, 238, 238));
        contentPanel.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                contentPanelComponentAdded(evt);
            }
        });

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 893, Short.MAX_VALUE)
        );

        exitButton.setBackground(new java.awt.Color(238, 238, 238));
        exitButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        exitButton.setForeground(new java.awt.Color(51, 70, 112));
        exitButton.setText(" . . .");
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout contentFrameLayout = new javax.swing.GroupLayout(contentFrame);
        contentFrame.setLayout(contentFrameLayout);
        contentFrameLayout.setHorizontalGroup(
            contentFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentFrameLayout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contentFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentFrameLayout.createSequentialGroup()
                        .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentFrameLayout.createSequentialGroup()
                        .addGap(0, 1376, Short.MAX_VALUE)
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        contentFrameLayout.setVerticalGroup(
            contentFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(contentFrameLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(exitButton)
                .addGap(18, 18, 18)
                .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(contentFrame, "card2");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void productManagerLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productManagerLabelMouseClicked
    MouseClicks(product_manager); 
   
    
   
     productManagerLabel.setForeground(ClickedForeground);
     productManagerPanelbtn.setBackground(ClickedColor);
     
     dashboardPanelbtn.setBackground(DefaultColor);
     dashboardLabel.setForeground(DefaultForeground);
     
      stocksLabel.setForeground(DefaultForeground);
      stocksPanelbtn.setBackground(DefaultColor);
      
      transactionsPanelbtn.setBackground(DefaultColor);
       transactionsLabel.setForeground(DefaultForeground);
       
       settingsPanelbtn.setBackground(DefaultColor);
       settingsLabel.setForeground(DefaultForeground);
    
      
    
    }//GEN-LAST:event_productManagerLabelMouseClicked

    private void productManagerPanelbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productManagerPanelbtnMouseClicked
   MouseClicks(product_manager);  
    
   
    
      
     productManagerLabel.setForeground(ClickedForeground);
      productManagerPanelbtn.setBackground(ClickedColor);
      
      dashboardPanelbtn.setBackground(DefaultColor);
      dashboardLabel.setForeground(DefaultForeground);
      
     stocksLabel.setForeground(DefaultForeground);
      stocksPanelbtn.setBackground(DefaultColor);
      
      transactionsPanelbtn.setBackground(DefaultColor);
      transactionsLabel.setForeground(DefaultForeground);
      
       settingsPanelbtn.setBackground(DefaultColor);
       settingsLabel.setForeground(DefaultForeground);
    
    
    
    }//GEN-LAST:event_productManagerPanelbtnMouseClicked

    private void contentPanelComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_contentPanelComponentAdded
   
    }//GEN-LAST:event_contentPanelComponentAdded

    private void dashboardPanelbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardPanelbtnMouseClicked
     MouseClicks(dashboard); 
   
     
     
      dashboardPanelbtn.setBackground(ClickedColor);
      dashboardLabel.setForeground(ClickedForeground);
      
      productManagerLabel.setForeground(DefaultForeground);
      productManagerPanelbtn.setBackground(DefaultColor);
      
      stocksLabel.setForeground(DefaultForeground);
      stocksPanelbtn.setBackground(DefaultColor);
      
       transactionsPanelbtn.setBackground(DefaultColor);
       transactionsLabel.setForeground(DefaultForeground);
       
       
        settingsPanelbtn.setBackground(DefaultColor);
       settingsLabel.setForeground(DefaultForeground);
    
    }//GEN-LAST:event_dashboardPanelbtnMouseClicked

    private void dashboardLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardLabelMouseClicked
     MouseClicks(dashboard);
     
   
     
      dashboardPanelbtn.setBackground(ClickedColor);
     dashboardLabel.setForeground(ClickedForeground);
      
      productManagerLabel.setForeground(DefaultForeground);
     productManagerPanelbtn.setBackground(DefaultColor);
      
     stocksLabel.setForeground(DefaultForeground);
    stocksPanelbtn.setBackground(DefaultColor);
      
       transactionsPanelbtn.setBackground(DefaultColor);
       transactionsLabel.setForeground(DefaultForeground);
       
       settingsPanelbtn.setBackground(DefaultColor);
       settingsLabel.setForeground(DefaultForeground);
    
     
    }//GEN-LAST:event_dashboardLabelMouseClicked

    private void stocksPanelbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stocksPanelbtnMouseClicked
    MouseClicks(inventory);
     
      
      stocksLabel.setForeground(ClickedForeground);
     stocksPanelbtn.setBackground(ClickedColor);
      
     productManagerLabel.setForeground(DefaultForeground);
     productManagerPanelbtn.setBackground(DefaultColor);
      
      dashboardPanelbtn.setBackground(DefaultColor);
     dashboardLabel.setForeground(DefaultForeground);
      
      transactionsPanelbtn.setBackground(DefaultColor);
      transactionsLabel.setForeground(DefaultForeground);
       
   
       settingsPanelbtn.setBackground(DefaultColor);
       settingsLabel.setForeground(DefaultForeground);
    
    }//GEN-LAST:event_stocksPanelbtnMouseClicked

    private void stocksLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stocksLabelMouseClicked
    MouseClicks(inventory);
     
      
     stocksLabel.setForeground(ClickedForeground);
     stocksPanelbtn.setBackground(ClickedColor);
      
      productManagerLabel.setForeground(DefaultForeground);
     productManagerPanelbtn.setBackground(DefaultColor);
      
     dashboardPanelbtn.setBackground(DefaultColor);
     dashboardLabel.setForeground(DefaultForeground);
      
     transactionsPanelbtn.setBackground(DefaultColor);
     transactionsLabel.setForeground(DefaultForeground);
     
      settingsPanelbtn.setBackground(DefaultColor);
       settingsLabel.setForeground(DefaultForeground);
    
    }//GEN-LAST:event_stocksLabelMouseClicked

    private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
      this.setExtendedState(MainFrame.ICONIFIED);
    }//GEN-LAST:event_exitButtonMouseClicked

    private void transactionsPanelbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transactionsPanelbtnMouseClicked
      MouseClicks(transactions);
    
     
       
       transactionsPanelbtn.setBackground(ClickedColor);
       transactionsLabel.setForeground(ClickedForeground);
       
       dashboardPanelbtn.setBackground(DefaultColor);
       dashboardLabel.setForeground(DefaultForeground);
       
       productManagerLabel.setForeground(DefaultForeground);
       productManagerPanelbtn.setBackground(DefaultColor);
    
      stocksLabel.setForeground(DefaultForeground);
       stocksPanelbtn.setBackground(DefaultColor);
       
      
      settingsPanelbtn.setBackground(DefaultColor);
       settingsLabel.setForeground(DefaultForeground);
    
    }//GEN-LAST:event_transactionsPanelbtnMouseClicked

    private void transactionsLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transactionsLabelMouseClicked
    MouseClicks(transactions);
       
      
     
     
       
      transactionsPanelbtn.setBackground(ClickedColor);
       transactionsLabel.setForeground(ClickedForeground);
       
       dashboardPanelbtn.setBackground(DefaultColor);
      dashboardLabel.setForeground(DefaultForeground);
       
       productManagerLabel.setForeground(DefaultForeground);
       productManagerPanelbtn.setBackground(DefaultColor);
       
       stocksLabel.setForeground(DefaultForeground);
       stocksPanelbtn.setBackground(DefaultColor);
    
      
       settingsPanelbtn.setBackground(DefaultColor);
       settingsLabel.setForeground(DefaultForeground);
    
    }//GEN-LAST:event_transactionsLabelMouseClicked

    private void settingsPanelbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsPanelbtnMouseClicked
       MouseClicks(settings);
     
    
       
       settingsPanelbtn.setBackground(ClickedColor);
       settingsLabel.setForeground(ClickedForeground);
       
       transactionsPanelbtn.setBackground(DefaultColor);
       transactionsLabel.setForeground(DefaultForeground);
       
       
       dashboardPanelbtn.setBackground(DefaultColor);
      dashboardLabel.setForeground(DefaultForeground);
       
       productManagerLabel.setForeground(DefaultForeground);
       productManagerPanelbtn.setBackground(DefaultColor);
       
       stocksLabel.setForeground(DefaultForeground);
       stocksPanelbtn.setBackground(DefaultColor);
        
        
        
    }//GEN-LAST:event_settingsPanelbtnMouseClicked

    private void settingsLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsLabelMouseClicked
        MouseClicks(settings);
       
    
     
       settingsPanelbtn.setBackground(ClickedColor);
       settingsLabel.setForeground(ClickedForeground);
       
       transactionsPanelbtn.setBackground(DefaultColor);
       transactionsLabel.setForeground(DefaultForeground);
       
       
       dashboardPanelbtn.setBackground(DefaultColor);
      dashboardLabel.setForeground(DefaultForeground);
       
       productManagerLabel.setForeground(DefaultForeground);
       productManagerPanelbtn.setBackground(DefaultColor);
       
       stocksLabel.setForeground(DefaultForeground);
       stocksPanelbtn.setBackground(DefaultColor);
        
    }//GEN-LAST:event_settingsLabelMouseClicked
private JFrame Fframe;
    private void exitLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitLabelMouseClicked
        
        
        Fframe = new JFrame("Logout");
        
         JLabel label2 = new JLabel("Are you sure you want to exit?");
         label2.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
        if (JOptionPane.showConfirmDialog(Fframe,label2,"SOLIS SARI SARI STORE",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_exitLabelMouseClicked

    private void exitPanelbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitPanelbtnMouseClicked
        Fframe = new JFrame("Logout");
         JLabel label2 = new JLabel("Are you sure you want to exit?");
         label2.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
        if (JOptionPane.showConfirmDialog(Fframe,label2,"SOLIS SARI SARI STORE",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_exitPanelbtnMouseClicked

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
                if ("Windows".equals(info.getName())) {
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentFrame;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel dashboardLabel;
    private javax.swing.JPanel dashboardPanelbtn;
    private javax.swing.JLabel exitButton;
    private javax.swing.JLabel exitLabel;
    private javax.swing.JPanel exitPanelbtn;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JLabel productManagerLabel;
    private javax.swing.JPanel productManagerPanelbtn;
    private javax.swing.JLabel settingsLabel;
    private javax.swing.JPanel settingsPanelbtn;
    private javax.swing.JLabel stocksLabel;
    private javax.swing.JPanel stocksPanelbtn;
    private javax.swing.JLabel transactionsLabel;
    private javax.swing.JPanel transactionsPanelbtn;
    // End of variables declaration//GEN-END:variables




}
