/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author MINHPHUONG
 * Method: setConn, CreateDB(String DBName), BackupDB(String path), RestoreDB(String path)
 * CreateDB: create new DB with DB name is DBName
 * Backup and restore: return boolean check run successful or fail
 */
public class WorkWithDB {
    
    Connection conn;
    String DBName;
    
    public void setDBName(String DBName)
    {
     this.DBName = DBName;   
    }
    
    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    public boolean CreateBD(String DBName) throws SQLException {
        boolean flag;
        String createDBScript = "USE [master]\n"
                + ""
                + "/****** Object:  Database [Lib]    Script Date: 6/4/2016 2:47:33 PM ******/\n"
                + "CREATE DATABASE ["+DBName+"]";
            String createTBScript = "Use "+DBName+" \n"
                + "CREATE TABLE [dbo].[Book](\n"
                + "	[BookID] [int] IDENTITY(1,1) NOT NULL,\n"
                + "	[Bname] [nvarchar](200) NULL,\n"
                + "	[CateID] [int] NULL,\n"
                + "	[Bnumber] [int] NULL,\n"
                + "	[Bprice] [int] NULL,\n"
                + "	[Bpubday] [date] NULL,\n"
                + "	[Bstatus] [date] NULL,\n"
                + "	[Bnumerr] [int] NULL,\n"
                + "	[Author] [nvarchar](50) NULL,\n"
                + " CONSTRAINT [PK_Book] PRIMARY KEY CLUSTERED \n"
                + "(\n"
                + "	[BookID] ASC\n"
                + ")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\n"
                + ") ON [PRIMARY]\n"
                + "\n"
                + ""
                + "/****** Object:  Table [dbo].[BookStatus]    Script Date: 6/4/2016 2:47:34 PM ******/\n"
                + "SET ANSI_NULLS ON\n"
                + ""
                + "SET QUOTED_IDENTIFIER ON\n"
                + ""
                + "CREATE TABLE [dbo].[BookStatus](\n"
                + "	[BookID] [int] NOT NULL,\n"
                + "	[Bnumber] [int] NOT NULL,\n"
                + "	[BorrowNum] [int] NOT NULL,\n"
                + "	[errnum] [int] NOT NULL,\n"
                + " CONSTRAINT [PK_BookStatus] PRIMARY KEY CLUSTERED \n"
                + "(\n"
                + "	[BookID] ASC\n"
                + ")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\n"
                + ") ON [PRIMARY]\n"
                + "\n"
                + ""
                + "/****** Object:  Table [dbo].[Borrow]    Script Date: 6/4/2016 2:47:34 PM ******/\n"
                + "SET ANSI_NULLS ON\n"
                + ""
                + "SET QUOTED_IDENTIFIER ON\n"
                + ""
                + "CREATE TABLE [dbo].[Borrow](\n"
                + "	[BorrowID] [int] IDENTITY(1,1) NOT NULL,\n"
                + "	[Bcheckin] [date] NULL,\n"
                + "	[Bcheckout] [date] NULL,\n"
                + "	[EmployeeID] [int] NULL,\n"
                + "	[Cidentity] [int] NULL,\n"
                + "	[Bprice] [int] NULL,\n"
                + " CONSTRAINT [PK_Borrow] PRIMARY KEY CLUSTERED \n"
                + "(\n"
                + "	[BorrowID] ASC\n"
                + ")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\n"
                + ") ON [PRIMARY]\n"
                + "\n"
                + ""
                + "/****** Object:  Table [dbo].[BorrowDetail]    Script Date: 6/4/2016 2:47:34 PM ******/\n"
                + "SET ANSI_NULLS ON\n"
                + ""
                + "SET QUOTED_IDENTIFIER ON\n"
                + ""
                + "CREATE TABLE [dbo].[BorrowDetail](\n"
                + "	[BorrowID] [int] NOT NULL,\n"
                + "	[BookID] [int] NOT NULL,\n"
                + "	[number] [int] NOT NULL,\n"
                + "	[price] [int] NULL,\n"
                + " CONSTRAINT [PK_BookBorrow] PRIMARY KEY CLUSTERED \n"
                + "(\n"
                + "	[BorrowID] ASC,\n"
                + "	[BookID] ASC\n"
                + ")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\n"
                + ") ON [PRIMARY]\n"
                + "\n"
                + ""
                + "/****** Object:  Table [dbo].[Category]    Script Date: 6/4/2016 2:47:34 PM ******/\n"
                + "SET ANSI_NULLS ON\n"
                + ""
                + "SET QUOTED_IDENTIFIER ON\n"
                + ""
                + "CREATE TABLE [dbo].[Category](\n"
                + "	[CateID] [int] IDENTITY(1,1) NOT NULL,\n"
                + "	[Catename] [nvarchar](200) NULL,\n"
                + " CONSTRAINT [PK_Author] PRIMARY KEY CLUSTERED \n"
                + "(\n"
                + "	[CateID] ASC\n"
                + ")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\n"
                + ") ON [PRIMARY]\n"
                + "\n"
                + ""
                + "/****** Object:  Table [dbo].[Customer]    Script Date: 6/4/2016 2:47:34 PM ******/\n"
                + "SET ANSI_NULLS ON\n"
                + ""
                + "SET QUOTED_IDENTIFIER ON\n"
                + ""
                + "SET ANSI_PADDING ON\n"
                + ""
                + "CREATE TABLE [dbo].[Customer](\n"
                + "	[Cidentity] [int] NOT NULL,\n"
                + "	[Cname] [varchar](50) NOT NULL,\n"
                + "	[Caddress] [varchar](200) NOT NULL,\n"
                + "	[Cphone] [int] IDENTITY(1,1) NOT NULL,\n"
                + "	[Cbirthday] [date] NOT NULL,\n"
                + "	[Cstatus] [int] NOT NULL,\n"
                + " CONSTRAINT [PK_Customer_1] PRIMARY KEY CLUSTERED \n"
                + "(\n"
                + "	[Cidentity] ASC\n"
                + ")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\n"
                + ") ON [PRIMARY]\n"
                + "\n"
                + ""
                + "SET ANSI_PADDING OFF\n"
                + ""
                + "/****** Object:  Table [dbo].[Employee]    Script Date: 6/4/2016 2:47:34 PM ******/\n"
                + "SET ANSI_NULLS ON\n"
                + ""
                + "SET QUOTED_IDENTIFIER ON\n"
                + ""
                + "SET ANSI_PADDING ON\n"
                + ""
                + "CREATE TABLE [dbo].[Employee](\n"
                + "	[EmployeeID] [int] IDENTITY(1,1) NOT NULL,\n"
                + "	[Ename] [nvarchar](50) NOT NULL,\n"
                + "	[Estatus] [varchar](50) NULL,\n"
                + "	[Eidentity] [int] NOT NULL,\n"
                + "	[Eaddress] [nvarchar](200) NOT NULL,\n"
                + "	[Ephone] [int] NOT NULL,\n"
                + "	[Epass] [varchar](20) NOT NULL,\n"
                + "	[Epermission] [int] NOT NULL,\n"
                + "	[Ebirthday] [date] NOT NULL,\n"
                + "	[Euser] [varchar](20) NOT NULL,\n"
                + "	[Eendday] [date] NULL,\n"
                + " CONSTRAINT [PK_Employee] PRIMARY KEY CLUSTERED \n"
                + "(\n"
                + "	[EmployeeID] ASC\n"
                + ")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\n"
                + ") ON [PRIMARY]\n"
                + "\n"
                + ""
                + "SET ANSI_PADDING OFF\n"
                + ""
                + "ALTER TABLE [dbo].[Book]  WITH CHECK ADD  CONSTRAINT [FK_Book_author] FOREIGN KEY([CateID])\n"
                + "REFERENCES [dbo].[Category] ([CateID])\n"
                + ""
                + "ALTER TABLE [dbo].[Book] CHECK CONSTRAINT [FK_Book_author]\n"
                + ""
                + "ALTER TABLE [dbo].[Borrow]  WITH CHECK ADD  CONSTRAINT [FK_Borrow_Cus] FOREIGN KEY([Cidentity])\n"
                + "REFERENCES [dbo].[Customer] ([Cidentity])\n"
                + ""
                + "ALTER TABLE [dbo].[Borrow] CHECK CONSTRAINT [FK_Borrow_Cus]\n"
                + ""
                + "ALTER TABLE [dbo].[Borrow]  WITH CHECK ADD  CONSTRAINT [FK_Borrow_emp] FOREIGN KEY([EmployeeID])\n"
                + "REFERENCES [dbo].[Employee] ([EmployeeID])\n"
                + ""
                + "ALTER TABLE [dbo].[Borrow] CHECK CONSTRAINT [FK_Borrow_emp]\n"
                + ""
                + "ALTER TABLE [dbo].[BorrowDetail]  WITH CHECK ADD  CONSTRAINT [FK_BookBorrow_Borrow] FOREIGN KEY([BorrowID])\n"
                + "REFERENCES [dbo].[Borrow] ([BorrowID])\n"
                + ""
                + "ALTER TABLE [dbo].[BorrowDetail] CHECK CONSTRAINT [FK_BookBorrow_Borrow]\n"
                + ""
                + "ALTER TABLE [dbo].[BorrowDetail]  WITH CHECK ADD  CONSTRAINT [FK_Borrow_Book] FOREIGN KEY([BookID])\n"
                + "REFERENCES [dbo].[Book] ([BookID])\n"
                + ""
                + "ALTER TABLE [dbo].[BorrowDetail] CHECK CONSTRAINT [FK_Borrow_Book]\n"
                + ""
                + "/****** Object:  Trigger [dbo].[CHKSTATUS]    Script Date: 6/4/2016 2:47:34 PM ******/\n"
                + "SET ANSI_NULLS ON\n"
                + ""
                + "SET QUOTED_IDENTIFIER ON\n"
                + ""
                + "USE [master]\n"
                + ""
                + "ALTER DATABASE ["+DBName+"] SET  READ_WRITE";

        /**
         * ****************************************************************
         */
        /**
         * ****************************************************************
         */
        /*                  END SCRIPT TO CREATE DATABASE                  */
        /**
         * ****************************************************************
         */
        /**
         * ****************************************************************
         */
        /*
        TRIGGER
        Trigger saved with String array
        loop element in array to execute create trigger (SQLServerException: trigger must be the first statement is a query batch)
         */
        ArrayList<String> triggerArr = new ArrayList<>();
        triggerArr.add("CREATE TRIGGER [dbo].[CHKSTATUS]\n"
                + "ON [dbo].[Borrow]\n"
                + "FOR UPDATE\n"
                + "AS\n"
                + "	IF UPDATE (Bcheckout)\n"
                + "	BEGIN\n"
                + "		DECLARE @Dcheckout date, @Dcheckin date, @victim int\n"
                + "		SET @Dcheckout = (SELECT Bcheckout FROM inserted)\n"
                + "		SET @Dcheckin = (SELECT Bcheckin FROM Borrow WHERE BorrowID = (SELECT BorrowID FROM inserted))\n"
                + "		SET @victim = (SELECT Borrow.Cidentity FROM Borrow WHERE Borrow.Cidentity = (SELECT Cidentity FROM inserted))\n"
                + "		if(DATEDIFF(DD,@Dcheckin,@Dcheckout) > 7)\n"
                + "		BEGIN\n"
                + "			UPDATE Customer SET Cstatus = 1 WHERE Customer.Cidentity = @victim\n"
                + "			PRINT 'victim '\n"
                + "			print +@victim\n"
                + "			PRINT 'trigger'\n"
                + "		END\n"
                + "	END\n"
                + "");
        //add trigger
        
        /*Statement
        */
        
        Statement st = conn.createStatement();
        //first: create DB
        flag = st.execute(createDBScript);
        if (flag) {
            flag = st.execute(createTBScript);
        }
        this.conn.setCatalog(DBName);

        System.out.println("work with db line 259 " + conn);
        /*
        loop array list
         */
        if (flag) {
            for (String triggerArr1 : triggerArr) {
                //create trigger
                flag = st.execute(triggerArr1);
                if (flag) {
                    break;
                }
            }
        }
        return flag;
    }
    
    public boolean BackupDB(String path) throws SQLException
    {
        boolean flag;
        Statement st = conn.createStatement();
        this.conn.setCatalog(DBName);
        String queryBackup = "BACKUP DATABASE "+DBName+" TO DISK "+path;
        flag = st.execute(queryBackup);
        return flag;
    }
    
    public boolean RestoreDB(String path) throws SQLException
    {
        boolean flag;
        String queryRestore = "use master alter database Lib set offline with rollback immediate "+
                "RESTORE DATABASE "+DBName+" FROM DISK = 'D:\\backup1.bak' WITH REPLACE "+
                "use master"+
                "alter database "+DBName+" set online with rollback immediate";
        Statement st = conn.createStatement();
        flag = st.execute(queryRestore);
        
        if(flag) this.conn.setCatalog(DBName);
        return flag;
    }
}
