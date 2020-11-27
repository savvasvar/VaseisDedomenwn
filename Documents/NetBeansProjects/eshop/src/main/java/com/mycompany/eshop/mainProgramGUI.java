/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.eshop;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Thanasis
 */
public class mainProgramGUI extends javax.swing.JFrame {
    user userInfo=new user();
    dbHandler db=new dbHandler();
    List<Orders> ordersList=new ArrayList<Orders>();
    
    /**
     * Creates new form mainProgramGUI
     */
    
    public mainProgramGUI() {
        initComponents();
        //components initialazation
        Panel.setVisible(false);
        jButton7.setOpaque(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setBorderPainted(false);
        jTabbedPane1.setEnabledAt(4, false);
        jTabbedPane1.setEnabledAt(3, false);
        jTabbedPane1.setEnabledAt(2, false);
        
        //Sets tooltips
        jTable1.setToolTipText("Double click to re-stock product");
        jTable2.setToolTipText("Double click to interact with orders");
        
        //Event listener for mainGui tabs switch
        jTabbedPane1.addChangeListener(new  ChangeListener(){
            public void stateChanged(ChangeEvent e) {
            //sets visible or not the new product and on stock buttons
            if(jTabbedPane1.getSelectedIndex()==0){
                jButton1.setVisible(true);
                jButton2.setVisible(true);
            }else{
                jButton1.setVisible(false);
                jButton2.setVisible(false);
            }
            //If group management tab is selected retrieve data
            if(jTabbedPane1.getSelectedIndex()==3){
               List<String> ret=new ArrayList<String>();
               List<String> users=new ArrayList<String>();
                try {
                    ret=db.getRoles();
                    users=db.getUsers();
                } catch (SQLException ex) {
                    Logger.getLogger(mainProgramGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                DefaultComboBoxModel model = new DefaultComboBoxModel(ret.toArray());
                jComboBox1.setModel(model);
                String[] userArray=new String[users.size()];
                for(int i=0;i<users.size();i++){
                    userArray[i]=users.get(i);
                }
                jList3.setListData(userArray);
                try {
                    jList1.setListData(db.refresh());
                } catch (SQLException ex) {
                    Logger.getLogger(mainProgramGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //If suppliers tab is selected retrieve suppliers
            if(jTabbedPane1.getSelectedIndex()==4){
               SuppListRefresh();
            }
//            if(userInfo.getRoleId() == 1){ /**I activate the log panel only for admins*/
//                Panel.setVisible(true);
//                jTabbedPane1.setEnabledAt(3, true);
//                jTabbedPane1.setEnabledAt(2, true);
//            }
        }
        });
        //Event listener if double click on orders table to open order details form
        jTable2.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent me){
                if(me.getClickCount()== 2){
                    JTable target = (JTable)me.getSource();
                    int row = target.getSelectedRow();
                    System.out.println(row);
                    OrderDetails ordDetails=new OrderDetails();
                    ordDetails.setOrder(ordersList.get(row));
                    ordDetails.setVisible(true);
                    ordDetails.addWindowListener(new java.awt.event.WindowAdapter(){
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent windowEvent){
                            TableDataHandler();
                            System.out.println("I did catch the event");
                        }
                    } );
                }
            }
        });
        //Event listener if double click on proudcts table to open restock form
        jTable1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent me){
                if(me.getClickCount()== 2){
                    JTable target = (JTable)me.getSource();
                    int row = target.getSelectedRow();
                    System.out.println(row);
//                    System.out.println("what"+jTable1.getModel().getValueAt(row, 0));
                    ProductsRestock prdR=new ProductsRestock();
                    prdR.setVisible(true);
                    int id=Integer.parseInt(jTable1.getModel().getValueAt(row, 0).toString());
                    try {
                        Product pr=db.getProduct(id);
                        prdR.setProduct(pr);
                    } catch (SQLException ex) {
                        Logger.getLogger(mainProgramGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    prdR.addWindowListener(new java.awt.event.WindowAdapter(){
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent windowEvent){
                            TableDataHandler();
                            System.out.println("I did catch the event");
                        }
                    } );
                }
            }
        });
    }
    //Populate user object
    public void userInfo(user user){
        userInfo.setEmail(user.getemail());
        userInfo.setUsername(user.getUsername());
        userInfo.setUserId(user.getUserId());
        userInfo.setRoleId(user.getRoleId());
        username_label.setText(user.getUsername()); /* Edo mporoume na to  baloume
                                                        na to pernei apo ti basei*/
        
        //raandom
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logButtonGroup = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        log_panel = new javax.swing.JPanel();
        Panel = new javax.swing.JScrollPane();
        log_Table = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton8 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jButton9 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        header_panel = new javax.swing.JPanel();
        icon_label = new javax.swing.JLabel();
        username_label = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTabbedPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusLost(evt);
            }
        });

        jPanel1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPanel1FocusLost(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Barcode", "Amount", "Price", "Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        jTabbedPane1.addTab("Products", jPanel1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Customer", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Orders", jPanel2);

        log_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "OPERATION", "TIME", "Product ID", "Product Name", "Description", "Amount", "Price"
            }
        ));
        Panel.setViewportView(log_Table);

        javax.swing.GroupLayout log_panelLayout = new javax.swing.GroupLayout(log_panel);
        log_panel.setLayout(log_panelLayout);
        log_panelLayout.setHorizontalGroup(
            log_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
        );
        log_panelLayout.setVerticalGroup(
            log_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Log", log_panel);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Role:");

        jLabel3.setText("User:");

        jButton3.setText("Set Role");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        jButton4.setText("Move Up");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Move Down");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Add");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jList3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane6.setViewportView(jList3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton3))
                            .addComponent(jSeparator1))
                        .addContainerGap())))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addGap(11, 11, 11)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(240, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Group Managment", jPanel3);

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jList2);

        jScrollPane5.setViewportView(jTextPane1);

        jButton8.setText("Add");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton9.setText("Delete selected!");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Add new supplier:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jButton8))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel4)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(180, 180, 180)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 237, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(jButton9)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Suppliers", jPanel4);

        jButton1.setText("New Product");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("On Stock");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        header_panel.setBackground(new java.awt.Color(255, 102, 51));

        icon_label.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        icon_label.setText("eShop");

        username_label.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        username_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton7.setText("Log Out");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout header_panelLayout = new javax.swing.GroupLayout(header_panel);
        header_panel.setLayout(header_panelLayout);
        header_panelLayout.setHorizontalGroup(
            header_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icon_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(18, 18, 18)
                .addComponent(username_label)
                .addContainerGap())
        );
        header_panelLayout.setVerticalGroup(
            header_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(icon_label, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
            .addComponent(username_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Main Form");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jButton1)
                        .addGap(59, 59, 59)
                        .addComponent(jButton2)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void rolesDropDownRefresh() throws SQLException{
        DefaultComboBoxModel model = new DefaultComboBoxModel(db.refresh());
        jComboBox1.setModel(model);
    }
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        TableDataHandler();
        //EDO PREPEI NA GINEI FIX GIA NA EINAI PIO GRIGORO FORTONEI TA PANTA MAZI
        if(userInfo.getRoleId() == 1){ /**I activate the log panel only for admins*/
                Panel.setVisible(true);
                jTabbedPane1.setEnabledAt(3, true);
                jTabbedPane1.setEnabledAt(2, true);
                jTabbedPane1.setEnabledAt(4, true);
            }
       jList3.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    jTextField1.setText(jList3.getSelectedValue().toString());
                    try {
                        jComboBox1.setSelectedIndex(db.getRolePos(jList3.getSelectedValue().toString()));
                    } catch (SQLException ex) {
                        Logger.getLogger(mainProgramGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }//GEN-LAST:event_formWindowOpened

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

    }//GEN-LAST:event_jButton1MouseClicked
    //Refreshes Suppliers list 
    public void SuppListRefresh(){
        List<String> ret=new ArrayList<String>();
                try {
                    ret=db.getSuppliers();
                    String[] supArray=new String[ret.size()];
                    for(int i=0;i<ret.size();i++){
                        supArray[i]=ret.get(i);
                    }
                    jList2.setListData(supArray);
                } catch (SQLException ex) {
                    Logger.getLogger(mainProgramGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            newProducts nPro=new newProducts();
            nPro.setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(mainProgramGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(mainProgramGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTabbedPane1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusGained

    }//GEN-LAST:event_jTabbedPane1FocusGained

    private void jTabbedPane1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusLost
   
    }//GEN-LAST:event_jTabbedPane1FocusLost

    private void jPanel1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel1FocusGained

    }//GEN-LAST:event_jPanel1FocusGained

    private void jPanel1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel1FocusLost

    }//GEN-LAST:event_jPanel1FocusLost

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       if(jTable1.getModel().getRowCount()!=0){
            DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
        }
         try {
             //filters proudct table
            Product[] data=db.getFilteredProducts(1);
            DefaultTableModel tblModel=(DefaultTableModel) jTable1.getModel();
            for(int i=0;i<data.length;i++){
                String proString[]=data[i].getProductString();
                tblModel.addRow(proString);
            }
        } catch (SQLException ex) {
            Logger.getLogger(mainProgramGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            //sets role to user
            int afR=db.setRoleToUser(jTextField1.getText(), jComboBox1.getSelectedIndex()+1);
            if(afR>0){
                System.out.println("done "+afR+" and index "+jComboBox1.getSelectedIndex()+1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(mainProgramGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed
//Re Orders selected groud id one UP
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            int pos=jList1.getSelectedIndex();
//            System.out.println(jList1.getModel().getElementAt(pos)+" "+jList1.getModel().getElementAt(pos-1));
            db.ReOrderUP(pos+1,jList1.getModel().getElementAt(pos),jList1.getModel().getElementAt(pos-1));
        } catch (SQLException ex) {
            Logger.getLogger(mainProgramGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            jList1.setListData(db.refresh());
            rolesDropDownRefresh();
        } catch (SQLException ex) {
            Logger.getLogger(mainProgramGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed
//Adds role to users
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        List<String> ret=new ArrayList<String>();
        if(!jTextField2.getText().isEmpty()){
            try {
                int r=db.addRole(jTextField2.getText());
                if(r>0){
                     ret=db.getRoles();
                     String[] listAr=new String[ret.size()];
                     for(int i=0;i<ret.size();i++){
                        listAr[i]=ret.get(i);
                     }
                    jList1.setListData(listAr);
                    jTextField2.setText(null);
                    rolesDropDownRefresh();
                }
            } catch (SQLException ex) {
                Logger.getLogger(mainProgramGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed
//Re Orders selected groud id one down
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            int pos=jList1.getSelectedIndex();
            System.out.println(jList1.getModel().getElementAt(pos)+" "+jList1.getModel().getElementAt(pos+1));
            System.out.println("Position "+(pos+1));
            db.ReOrderDown(pos+1,jList1.getModel().getElementAt(pos),jList1.getModel().getElementAt(pos+1));
        } catch (SQLException ex) {
            Logger.getLogger(mainProgramGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            jList1.setListData(db.refresh());
            rolesDropDownRefresh();
        } catch (SQLException ex) {
            Logger.getLogger(mainProgramGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed
//logsout
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            logout();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(mainProgramGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(mainProgramGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed
//Inserts suppliers
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {
            if(!jTextPane1.getText().isEmpty()){
                int x=db.insertSupplier(jTextPane1.getText());
                SuppListRefresh();
                jTextPane1.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(mainProgramGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            //Delete Supplier
            int ret=db.deleteSupplier(jList2.getSelectedValue().toString());
            SuppListRefresh();
        } catch (SQLException ex) {
            Logger.getLogger(mainProgramGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

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
            java.util.logging.Logger.getLogger(mainProgramGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainProgramGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainProgramGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainProgramGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainProgramGUI().setVisible(true);
            }
        });
    }
    public void TableDataHandler(){
        if(jTable1.getModel().getRowCount()!=0){
            DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
        }
        if(jTable2.getModel().getRowCount()!=0){
            DefaultTableModel model2=(DefaultTableModel) jTable2.getModel();
            model2.setRowCount(0);
        }
        if(log_Table.getModel().getRowCount()!=0){
            DefaultTableModel modellog=(DefaultTableModel) log_Table.getModel();
            modellog.setRowCount(0);
        }
         try {
            Product[] data=db.getProducts();
            DefaultTableModel tblModel=(DefaultTableModel) jTable1.getModel();
            for(int i=0;i<data.length;i++){
                String proString[]=data[i].getProductString();
                tblModel.addRow(proString);
            }
            List<Order_View> ord=db.getOrders();
            DefaultTableModel tblModel2=(DefaultTableModel) jTable2.getModel();
            for(int i=0;i<ord.size();i++){
                
//                ordersList.add(ord[i]);
//                String[] ordString=ord[i].getOrdersString();
                tblModel2.addRow(ord.get(i).getOrderViewString());
            }
            LogPanel[] log=db.getLogPanel();
            DefaultTableModel tblModellog=(DefaultTableModel) log_Table.getModel();
            for(int i=0;i<log.length;i++){
                String logString[]=log[i].getLogPanelString();
                tblModellog.addRow(logString);
            }
        } catch (SQLException ex) {
            Logger.getLogger(mainProgramGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void logout() throws ClassNotFoundException, SQLException{
        login lgin=new login();
        lgin.setVisible(true);
        this.dispose();
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane Panel;
    private javax.swing.JPanel header_panel;
    private javax.swing.JLabel icon_label;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.ButtonGroup logButtonGroup;
    private javax.swing.JTable log_Table;
    private javax.swing.JPanel log_panel;
    private javax.swing.JLabel username_label;
    // End of variables declaration//GEN-END:variables
}
