/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import Function.Code;
import Function.Display;
import Function.QueryGeneral;
import com.sun.org.apache.xpath.internal.objects.DTMXRTreeFrag;
import java.awt.Point;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Admin
 */
public class frmOrderBook extends javax.swing.JFrame {

    /**
     * Creates new form frmOrderBook
     */
    DefaultTableModel dtm;
    Connection conn;
    QueryGeneral objQG;
    DefaultTableModel dtmSelectManu = null;
    DefaultTableModel dtmSelectBook = null;
    String[] bookInfo = new String[4]; 
    Code objCode;
    int Number = 0;
    int Total = 0;
    ArrayList<String> listCurrentBook = new ArrayList<>();
            
    public frmOrderBook() {
        initComponents();
        dtmSelectBook = (DefaultTableModel) tblBook.getModel();
        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=Library";
            this.conn = DriverManager.getConnection(dbURL, "sa", "sa");
            System.out.println(""+conn);
            
        } catch (SQLException ex) {
            System.out.println(""+ex.toString());
           
        }
        objQG = new QueryGeneral();
        dtm = (DefaultTableModel) jTable1.getModel();
        
        //create code
        objCode = new Code("OR");//cr: stand for customer
        ResultSet rs = null;
        objQG = new QueryGeneral("BookOrder");
        objQG.setConn(this.conn);
        rs = objQG.selectQuery(new String[]{"count(*)"});//setlectQuery(String column[])
        txtCode.setText(objCode.createCode(rs)); //show ID
        
        dtm.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
              
                    int i = 0;
                    int num = 0;
                    int total = 0;
                    int numtemp = 0;
                    int totaltemp = 0;
                    while(i<jTable1.getRowCount())
                    {
                        numtemp = Integer.parseInt(jTable1.getValueAt(i, 2).toString());
                        totaltemp = Integer.parseInt(jTable1.getValueAt(i, 3).toString());
                        total = total + (numtemp * totaltemp);
                        num = num + numtemp;
                        
                        i++;
                    }
                    lblBookNum.setText(""+num);
                    lbltotal.setText(""+total);
                     
                
            }
        });
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frmBook = new javax.swing.JFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBook = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        frmManu = new javax.swing.JFrame();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblManu = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtContact = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnSelectBook = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lblBookNum = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbltotal = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();

        frmBook.setMinimumSize(new java.awt.Dimension(418, 330));
        frmBook.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                frmBookComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                frmBookComponentShown(evt);
            }
        });

        tblBook.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Author", "Category"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblBook);

        jButton3.setText("Add");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout frmBookLayout = new javax.swing.GroupLayout(frmBook.getContentPane());
        frmBook.getContentPane().setLayout(frmBookLayout);
        frmBookLayout.setHorizontalGroup(
            frmBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmBookLayout.createSequentialGroup()
                .addGroup(frmBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 32, Short.MAX_VALUE))
        );
        frmBookLayout.setVerticalGroup(
            frmBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmBookLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        frmManu.setMinimumSize(new java.awt.Dimension(400, 300));
        frmManu.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                frmManuComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                frmManuComponentShown(evt);
            }
        });

        tblManu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblManu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblManuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblManuMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblManu);

        jButton5.setText("Select");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout frmManuLayout = new javax.swing.GroupLayout(frmManu.getContentPane());
        frmManu.getContentPane().setLayout(frmManuLayout);
        frmManuLayout.setHorizontalGroup(
            frmManuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmManuLayout.createSequentialGroup()
                .addGroup(frmManuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frmManuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5)))
                .addContainerGap())
        );
        frmManuLayout.setVerticalGroup(
            frmManuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmManuLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jButton5))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Manufactory"));

        jLabel1.setText("ID");

        jButton1.setText("Select Manufactory");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Name");

        jLabel3.setText("Address");

        jLabel4.setText("Contact");

        jLabel5.setText("Phone");

        jLabel6.setText("Email");

        txtID.setEditable(false);

        txtName.setEditable(false);

        txtAddress.setEditable(false);

        txtContact.setEditable(false);

        txtPhone.setEditable(false);

        txtEmail.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtID)
                            .addComponent(txtName)
                            .addComponent(txtAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtContact, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        btnSelectBook.setText("Select Book");
        btnSelectBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectBookActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Number", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable1FocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton4.setText("ADD");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel8.setText("Book Number:");

        lblBookNum.setText("0");

        jLabel10.setText("Price:");

        lbltotal.setText("0");

        jButton2.setText("Delete Book");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnSelectBook)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(148, 148, 148)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblBookNum)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbltotal)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSelectBook)
                    .addComponent(jLabel8)
                    .addComponent(lblBookNum)
                    .addComponent(jLabel10)
                    .addComponent(lbltotal)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel7.setText("Order ID:");

        txtCode.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        frmManu.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSelectBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectBookActionPerformed
        int i = 0, col = jTable1.getRowCount();
        listCurrentBook.clear();
        while(i<col)
        {
            listCurrentBook.add(jTable1.getValueAt(i, 0).toString());
            i++;
        }
//        while(dtmSelectBook != null && i<dtmSelectBook.getRowCount())
//        {
//            dtmSelectBook.removeRow(i);
//            i++;
//        }
        frmBook.setVisible(true);
    }//GEN-LAST:event_btnSelectBookActionPerformed

    private void frmBookComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_frmBookComponentShown
        
            dtmSelectBook.setRowCount(0);
            objQG.setConn(conn);
            objQG.setTable("Book");
            ResultSet rsBook;
            String sql = "Select BookID, BookName, AuthorName, CateName "
            + "From Book, Category "
            + "Where Book.CateID = Category.CateID and "
                    + "BookID not in (";
            int temp = listCurrentBook.size();
            if(!listCurrentBook.isEmpty()){
                for(int i = 0; i<temp; i++)
                {
                    sql+= "'"+listCurrentBook.get(i)+"'";
                    if(i!=(temp-1))
                    {
                        sql+=",";
                    }
                }
            }else sql+="''";
                   sql+= ")";
            listCurrentBook.clear();
            rsBook = objQG.selectMultiTable(sql);
            ResultSet rsBookTemp;
            rsBookTemp = objQG.selectMultiTable(sql);
            int countTemp = 0;
            try {
                while(rsBookTemp.next())
                {
                    countTemp++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(frmOrderBook.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[][] sqlData = new String[countTemp][4];
            String[] listCol = new String[]{"BookID", "BookName", "AuthorName", "CateName"};
            String[] listType = new String[]{"String", "String", "String", "String"};
            Display objDisplay = new Display(listCol);
            sqlData = objDisplay.getData(rsBook, listType, countTemp, 4);
            for(int i = 0; i<countTemp;i++)
            {
                dtmSelectBook.addRow(sqlData[i]);
            }
        
    }//GEN-LAST:event_frmBookComponentShown

    private void frmManuComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_frmManuComponentShown
        if(dtmSelectManu == null)
        {
            dtmSelectManu = (DefaultTableModel) tblManu.getModel();
            objQG.setConn(conn);
            objQG.setTable("Manufactory");
            ResultSet rsManu;
            String sql = "Select ManuID, ManuName, ManuAddress "
                    + "From Manufactory ";

            rsManu = objQG.selectMultiTable(sql);
            ResultSet rsManuTemp;
            rsManuTemp = objQG.selectMultiTable(sql);
            int countTemp = 0;
            try {
                while(rsManuTemp.next())
                {
                    countTemp++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(frmOrderBook.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[][] sqlData = new String[countTemp][4];
            String[] listCol = new String[]{"ManuID", "ManuName", "ManuAddress"};
            String[] listType = new String[]{"String", "String", "String"};
            Display objDisplay = new Display(listCol);
            sqlData = objDisplay.getData(rsManu, listType, countTemp, 4);
            for(int i = 0; i<countTemp;i++)
            {
                dtmSelectManu.addRow(sqlData[i]);
            }
        }
    }//GEN-LAST:event_frmManuComponentShown

    private void tblManuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblManuMousePressed
//        Point p = evt.getPoint();
//        int row = tblManu.rowAtPoint(p);
//        if (evt.getClickCount() == 2) {
//            txtID.setText(t);
//        }

    }//GEN-LAST:event_tblManuMousePressed

    private void tblManuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblManuMouseClicked
        String ManuID = (String) tblManu.getValueAt(tblManu.getSelectedRow(), 0);
        objQG.setConn(conn);
        objQG.setTable("Manufactory");
        ResultSet rsManu;
        String sql = "SELECT * FROM Manufactory WHERE ManuID like '"+ManuID+"'";
        rsManu = objQG.selectMultiTable(sql);
        ResultSet rsManuTemp;
        rsManuTemp = objQG.selectMultiTable(sql);
        int countTemp = 0;
        try {
            while(rsManuTemp.next())
            {
                countTemp++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmOrderBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[][] sqlData = new String[countTemp][6];
        String[] listCol = new String[]{"ManuID", "ManuName", "ManuAddress","ManuPhone", "ManuEmail", "ManuContact"};
        String[] listType = new String[]{"String", "String", "String","String","String","String"};
        Display objDisplay = new Display(listCol);
        sqlData = objDisplay.getData(rsManu, listType, countTemp, 6);
        txtID.setText(ManuID);
        txtName.setText(sqlData[0][1]);
        txtAddress.setText(sqlData[0][2]);
        txtPhone.setText(sqlData[0][3]);
        txtEmail.setText(sqlData[0][4]);
        txtContact.setText(sqlData[0][5]);
    }//GEN-LAST:event_tblManuMouseClicked

    private void frmManuComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_frmManuComponentHidden
//        for (int i = 0; i < dtmSelectManu.getRowCount(); i++) {
//            dtmSelectManu.removeRow(i);
//        }
    }//GEN-LAST:event_frmManuComponentHidden

    private void frmBookComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_frmBookComponentHidden
        for (int i = 0; i < dtmSelectBook.getRowCount(); i++) {
            dtmSelectBook.removeRow(i);
        }
    }//GEN-LAST:event_frmBookComponentHidden

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(tblBook.getSelectedRowCount() > 1)
        {
            JOptionPane.showMessageDialog(rootPane, "Select only one book");
        }
        else if(tblBook.getSelectedRowCount() == 0)
        {
            JOptionPane.showMessageDialog(rootPane, "Select one book");
        }
        else if(tblBook.getSelectedRowCount()== 1)
        {
            bookInfo[0] = (String) dtmSelectBook.getValueAt(tblBook.getSelectedRow(), 0);
            bookInfo[1] = (String) dtmSelectBook.getValueAt(tblBook.getSelectedRow(), 1);
            bookInfo[2] = "1";
            bookInfo[3] = "1";
            dtm.addRow(bookInfo);
            dtmSelectBook.removeRow(tblBook.getSelectedRow());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        boolean flag = true;
        if(dtm.getRowCount() == 0)
        {
            JOptionPane.showMessageDialog(this, "Error: Order is empty");
            flag = false;
        }
        else
        {
            for (int i = 0; i < dtm.getRowCount(); i++) {
                if(dtm.getValueAt(i, 2).equals("0") || dtm.getValueAt(i, 3).equals("0"))
                {
                    JOptionPane.showMessageDialog(this, "Please input number and price");
                    flag = false;
                    break;
                }
            }
        }
        if(flag)
        {
            //date
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String sDate = dateFormat.format(date);
            
            objQG.setConn(conn);
            //insert book order
            objQG.setTable("BookOrder");
            String columName[] = new String[]{"OrderID","EmpID","ManuID","OrderDate","OrderState"};
            String columnType[] = new String[]{"String","String","String","date","String"};
            String columnData[] = new String[]{""+txtCode.getText(),"EM000001",""+txtID.getText(),""+sDate,"FALSE"};
            objQG.insertQuery(columName,columnType,columnData);
            
            //insert order detail
            objQG.setTable("BookOrderDetail");
            for (int i = 0; i < dtm.getRowCount(); i++) {
                String columDetailName[] = new String[]{"OrderID","BookID","BookNumber","BookPrice"};
                String columnDetailType[] = new String[]{"String","String","int","int"};
                String[] columnDetailData = new String[]{""+txtCode.getText(),""+dtm.getValueAt(i, 0),""+dtm.getValueAt(i, 2).toString(),""+dtm.getValueAt(i, 3).toString()};
                objQG.insertQuery(columDetailName,columnDetailType,columnDetailData);
            }
            JOptionPane.showMessageDialog(this, "Create Order Success \n Wait to Approve!");
        }   
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusGained
        
        
        
    }//GEN-LAST:event_jTable1FocusGained

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        frmManu.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(jTable1.getSelectedRowCount()== 1)
        {
            dtm.removeRow(jTable1.getSelectedRow());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(frmOrderBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmOrderBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmOrderBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmOrderBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmOrderBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelectBook;
    private javax.swing.JFrame frmBook;
    private javax.swing.JFrame frmManu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblBookNum;
    private javax.swing.JLabel lbltotal;
    private javax.swing.JTable tblBook;
    private javax.swing.JTable tblManu;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
