package org.unibenchTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.unibenchLoader.OracleConnection;

class OracleConnectionTest {

    @Test
    void testOracleConnection() {
        OracleConnection oc = new OracleConnection("unibench","oracle");
        
        assertTrue(oc.con instanceof Connection);
        
        oc.closeCon();
    }

//    @Test
//    void testGetResults() {
//        OracleConnection oc = new OracleConnection("unibench","oracle");
//        
//        String sqlStr="select * from person where id = 4145";
//        
//        ResultSet rss = oc.getResults(sqlStr);
//        
//        int emplID = 0;
//
//            try {
//                while(rss.next()) 
//                    
//                    emplID=rss.getInt(1);
//            } catch (SQLException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            
//            assertEquals(4145,emplID);
//            oc.closeCon();
//    }

}