package org.unibenchLoader;

import java.sql.*;

public class OracleConnection {
    public Connection con;

    public OracleConnection(String user, String pw) {
         
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl",user,pw);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
    }

    public void closeCon() {
        try {
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public ResultSet getResults(String sqlStr) {
         ResultSet rs = null;
        try {
            Statement stmt = con.createStatement();
            //rs=stmt.executeQuery("select EMPLOYEE_ID,LAST_NAME,DEPARTMENT_ID,SALARY  from HR.EMPLOYEES");
            rs=stmt.executeQuery(sqlStr);

         
         } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;  
    }

}

