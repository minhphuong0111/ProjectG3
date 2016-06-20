/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Function;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author MINHPHUONG
 * 
 */
public class QueryGeneral {
    String table;
    ResultSet rs;
    Connection conn = null;
    boolean flag = false;
    
    public QueryGeneral() {}
    /**
     * 
     * @param table 
     *          QueryGeneral obj = new QueryGeneral(table);
     * parameter is table and connection name
     * have 3 method: selectQuery, insertQuery, updateQuery
     * before use method, you need create connect
     * before use insert or update, you need instance select
     */
    public QueryGeneral(String table)
    {
        this.table = table;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public boolean isFlag() {
        return flag;
    }
    
    
    
    /**
     * Connect to database
     */
    public void ConnectDB(String pathXMLFile)
    {
        Scanner input = new Scanner(System.in);
        String stringConnect="";
        String[] stringreturn = new String[5];
        
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //input and check xml file
            
            WriteXMLFile wXmlFile = new WriteXMLFile();
            CheckXMLFile cXMLFile = new CheckXMLFile(pathXMLFile);
            
            do
            {
                //CheckFile: neu file khong ton tai se return false
                //if: 
                if(!cXMLFile.CheckFile())
                {
                    System.out.println("Create file xml");
                    //wXmlFile.WriteFile(...): khi su dung tren frame se truyen truc tiep gia tri cua cac textfield duoc nhap vao tham so
                    wXmlFile.WriteFile("localhost", "1433", "InventoryManagement", "sa", "sa");
                    stringreturn = wXmlFile.ReadFile();
                    stringConnect +="jdbc:sqlserver://";
                    stringConnect +=""+stringreturn[0]+":"+stringreturn[1]+";databaseName="+stringreturn[2];
                    conn = DriverManager.getConnection(stringConnect,stringreturn[3],stringreturn[4]);
                }
                else {
                    System.out.println("File exist, read file");
                    stringreturn = wXmlFile.ReadFile();
                    stringConnect +="jdbc:sqlserver://";
                    stringConnect +=""+stringreturn[0]+":"+stringreturn[1]+";databaseName="+stringreturn[2];
                    conn = DriverManager.getConnection(stringConnect,stringreturn[3],stringreturn[4]);
                    System.out.println(conn);
                    if(conn==null)//doc du lieu tu file nhung khong ket noi duoc csdl
                    {
                        System.out.println("modify file");
                        //call frame input information connect db
                        //get information and assign conn
                        String[] str = new String[5];
                        System.out.println("Host: ");
                        str[0] = input.nextLine();
                        System.out.println("Host: ");
                        str[1] = input.nextLine();
                        System.out.println("Host: ");
                        str[2] = input.nextLine();
                        System.out.println("Host: ");
                        str[3] = input.nextLine();
                        System.out.println("Host: ");
                        str[4] = input.nextLine();
                        wXmlFile.ModifyXML(str);
                        
                        stringreturn = wXmlFile.ReadFile();
                        stringConnect +="jdbc:sqlserver://";
                        stringConnect +=""+stringreturn[0]+":"+stringreturn[1]+";databaseName="+stringreturn[2];
                        conn = DriverManager.getConnection(stringConnect,stringreturn[3],stringreturn[4]);
                    }
                }
            } while(conn == null);
            //System.out.println("End do while");
            //System.out.println(conn);
        }
        catch(Exception ex){System.out.println(ex.toString());}
        
       // return conn;
    }
    
    public ResultSet checkPwd(String usr, String pwd)
    {
        try {
            String sql = "select * from Employee Where empID = '"+usr+"' and password = '"+pwd+"'";
            PreparedStatement stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(QueryGeneral.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    /**
     * return JdbcRowSet
     * select all column in table
     */
    public ResultSet selectQuery()
    {
        try{
            
            PreparedStatement stm = conn.prepareStatement("Select * From "+table);
            rs = stm.executeQuery();
            
        }
        catch(Exception ex){System.out.println("select query all column of table "+ex.toString());}
        
        return rs;
    }
    
    /**
     * 
     * @param column
     * @return 
     * select with list column not condition
     */
    public ResultSet selectQuery(String column[])
    {
        try
        {
            String sql = "Select ";
            for (int i = 0; i < column.length; i++) {
                sql+=column[i];
                if(i!=(column.length-1))
                {
                    sql+=" , ";
                }
            }
            sql+=" From "+table;
            PreparedStatement stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
        }
        catch(Exception ex){}
        
        return rs;
    }
    
    public ResultSet selectQuery(String colCond[], String colType[], String condData[])
    {
        try
        {
            String sql = "Select * from "+table+" Where ";
            for (int i = 0; i < colCond.length; i++) {
                sql += ""+colCond[i]+" = ";
                if(colType[i].equals("int"))
                {
                    sql += ""+condData[i];
                }
                else {
                    sql += "'"+condData[i]+"'";
                }
                if(i != colCond.length-1)
                {
                    sql+=" and ";
                }
            }
            
            PreparedStatement stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
        }
        catch(Exception ex){System.out.println("error: select query with condition "+ex.toString());}
        
        return rs;
    }
    
    public ResultSet selectMultiTable(String column[], String table[], String ID[])
    {
        String sql = "Select ";
        for (int i = 0; i < column.length; i++) {
            sql += ""+column[i];
            if(i<column.length)
            {
                sql += ", ";
            }
            else sql+= "From ";
        }
        
        for (int i = 0; i < table.length; i++) {
            sql += ""+table[i];
            if(i < table.length)
            {
                sql += ", ";
            }
            else sql += "Where ";
        }
        
        for (int i = 0; i < ID.length; i+=2) {
            sql += ""+table[1]+"."+ID[i]+" = "+table[2]+"."+ID[i+1];
            if(i < ID.length)
            {
                sql += " and ";
            }
        }
        try
        {
            PreparedStatement stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
        }
        catch(Exception ex){System.out.println("select multi table"+ex.toString());}
        return rs;
    }
    public ResultSet selectMultiTable(String sql)
    {
         try
        {
            PreparedStatement stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
        }
        catch(Exception ex){System.out.println("select multi table"+ex.toString());}
        return rs;
    }
    
    public ResultSet forgotPwdQuery(String empID)
    {
        try
        {
            String sql = "Select empID From Employee Where empID = '"+empID+"'";
            PreparedStatement stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
        }
        catch(Exception ex){System.out.println(""+ex.toString());}
        return rs;
    }
    
    /**
     * select with condition
     */
    public ResultSet LoginQuery(String usr, String pwd)
    {
        try
        {
            PreparedStatement stm = conn.prepareStatement("Select * From "+table+" Where empID = '"+usr+"' and password = '"+pwd+"'");
            rs = stm.executeQuery();
            
        }
        catch(Exception ex){JOptionPane.showMessageDialog(null, "Database is incorrect \nYou must setup your connection", "Error", JOptionPane.ERROR_MESSAGE);}
        
        return rs;        
    }
    /**
     * 
     * @param column
     * @param proID
     * @return 
     * lay danh sach
     */
    public ResultSet selectQuery(String proID)
    {
        String sql = "Select * from Inventory Where proID = '"+proID+"' and quantity > 0";
        try
        {
            PreparedStatement stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
        }
        catch(Exception ex){System.out.println("lay thong tin kho theo ma sp"+ ex.toString());}
        return rs;
    }
    
    public String[][] getInventory(String proID, int quantity)
    {
        ResultSet rs = this.selectQuery(proID);
        ResultSet rs1 = this.selectQuery(proID);
        int count = 0;
        try
        {
            while(rs1.next())
            {
                count++;
            }
        }
        catch(Exception ex) {System.out.println("count getInventory"+ex.toString());}
        String sreturn[][] = new String[count][];
        int sluongtemp = 0;
        int i = 0;
        try
        {
            while(rs.next())
            {
                if(rs.getInt("quantity") < quantity)
                {
                    sluongtemp += rs.getInt("quantity");
                    sreturn[i][0] = rs.getString(0);
                    sreturn[i][1] = rs.getString(1);
                    sreturn[i][2] = rs.getString(2);
                    sreturn[i][3] = rs.getString(3);
                    sreturn[i][4] = rs.getString(4);
                    quantity = quantity - sluongtemp;
                }
                else
                {
                    sluongtemp += quantity;
                    sreturn[i][0] = rs.getString(0);
                    sreturn[i][1] = rs.getString(1);
                    sreturn[i][2] = rs.getString(2);
                    sreturn[i][3] = rs.getString(3);
                    sreturn[i][4] = rs.getString(4);
                    quantity = 0;
                    flag = true;
                }
                i++;
            }
        }
        catch(Exception ex){System.out.println("calculator Inventory" +ex.toString());}
        return sreturn;
    }
    
    /**
     * 
     * @param colName
     * @param data
     * @return
     * @throws SQLException 
     * get string ID to search
     */
    public String getID(String colName, String data) throws SQLException
    {
        try
        {
            String sql = "";
            sql+= "Select * From "+this.table+" Where "+colName+" like N'%"+data+"%'";
            PreparedStatement stm = conn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            String result = rs.getString(1);
        }
        catch(Exception ex){}
        
        return rs.getString(1);
    }
    
    /**
     * public void insertQuery(String[] columName, String[] information)
     * 
     */
    public void insertQuery(String[] columnName, String[] information)
    {
        try{
            String sql = "";
            sql+="insert into from "+table;
            sql+="(";
            for (int i = 0; i < columnName.length; i++) {
                sql+=""+columnName[i];
                if(i==(columnName.length-1))
                {
                    sql+=")";
                }
                else sql+=",";
            }
            sql+="value(";
            for (int i = 0; i < columnName.length; i++) {
                sql+="?";
                if(i==(columnName.length-1))
                {
                    sql+=")";
                }
                else sql+=",";
            }
            
            PreparedStatement stm = conn.prepareStatement(sql);
            for (int i = 1; i <= columnName.length; i++) {
                stm.setString(i, information[i-1]);
            }
            stm.execute();
            
        }
        catch(Exception ex){System.out.println("error: insert data");}
    }
    
    /**
     * 
     * @param colName - String colName[]: list column name
     * @param ListType - String ListType[]: list data type
     * @param ListData - String ListData[]: list data insert
     */
    public void insertQuery(String colName[], String ListType[], String ListData[])
    {
        try{
            String sql = "";
            sql+="insert into "+table;
            sql+="( ";
            for (int i = 0; i < colName.length; i++) {
                sql+=""+colName[i];
                if(i==(colName.length-1))
                {
                    sql+=" )";
                }
                else sql+=", ";
            }
            sql+=" values( ";
            for (int i = 0; i < colName.length; i++) {
                sql+="? ";
                if(i==(colName.length-1))
                {
                    sql+=") ";
                }
                else sql+=", ";
            }
            
            PreparedStatement stm = conn.prepareStatement(sql);
            for (int i = 1; i <= colName.length; i++) {
                if(ListType[i-1].equals("int"))
                    {
                        String temp = ListData[i-1].toString();
                        int itemp = Integer.parseInt(temp);
                        stm.setInt(i,itemp);
                    }
                else if(ListType[i-1].equals("String"))
                {
                    stm.setString(i, ListData[i-1]);
                }
                else {
//                    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
//                    //Calendar cal = Calendar.getInstance();
//                    
//                    java.util.Date date = sdf.parse(ListData[i-1]);
//                    stm.setDate(i, (Date) date);
                    java.util.Date uDate = new java.util.Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    uDate = sdf.parse(ListData[i-1]);
                    stm.setDate(i, new java.sql.Date(uDate.getTime()));
                    System.out.println(""+new java.sql.Date(uDate.getTime()));
                }
            }
            System.out.println(""+sql);
            stm.execute();
            
        }
        catch(Exception ex){System.out.println(""+ex.toString());}
    }
    
    /**
     * 
     * @param colType - String colType[]: list column data type
     * @param columnName - String columnName[]: list column name
     * @param information - String information[]: list new data update
     * @param colCondType - String colCondType[]: list column condition data type
     * @param columnCon - String columnCon[]: list column condition
     * @param Cond - String Cond[]: list condition
     */
    public void updateQuery(String colType[], String columnName[], String information[], String colCondType[], String columnCon[], String Cond[])
    {
        try{
            String sql = "";
            sql+="update "+table;
            sql+=" set ";
            for (int i = 0; i < columnName.length; i++) {
                sql+=""+columnName[i];
                
                if(colType[i].equals("int"))
                {
                    sql+=" = "+(Integer.parseInt(information[i]));
                }
                else if(colType[i].equals("String"))
                {
                    
                    sql+=" = '"+information[i];
                    sql+="'";
                }
                else if(colType[i].equals("date")){
                    java.util.Date uDate = new java.util.Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
                    uDate = sdf.parse(information[i]);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(uDate);
                    cal.add(Calendar.DATE, Integer.parseInt(information[i]));
                    sql += " = '"+cal.getTime();
                }else if(colType[i].equals("bit"))
                {
                    System.out.println("bit");
                    String sbool = colType[i].toUpperCase();
                    sql += " = '"+information[i];
                    sql += "' ";
                }
                if(i!=(columnName.length - 1))
                {
                    sql+=",";
                }
                
            }
            sql+="Where ";
            for (int i = 0; i < columnCon.length; i++) {
                sql+=""+columnCon[i];
                if(colCondType.equals("int"))
                {
                    sql+= " = "+Cond[i];
                }
                else {
                    sql+=" = '"+Cond[i];
                    sql+="'";
                }
                if(i!=(colCondType.length-1))
                {
                    sql+=" and ";
                }
            }
            
           // System.out.println(""+sql);
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.execute();
            
        }
        catch(SQLException sqlex){
            System.out.println(""+sqlex.getMessage());
        }
        catch(Exception ex){System.out.println(""+ex.toString());}
    }
    
    public void closeConnect() throws SQLException
    {
        conn.close();
    }
}
