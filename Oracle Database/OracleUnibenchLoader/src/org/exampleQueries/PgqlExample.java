package org.exampleQueries;

import oracle.pg.rdbms.*; 
import oracle.pg.common.*;
import java.util.*;
import java.text.*;
import java.sql.*;
import java.sql.Date;
import java.time.*;
import java.time.format.*;

/**
 * This example shows the behavior of the EDGE_SET_PARTIAL option with
 * PGQL queries.
 */
public class PgqlExample {

  public void execute(String szGraph, String query) throws Exception {

    Oracle oracle = null;
    OraclePropertyGraph opg = null;
    OraclePgqlStatement ops = null;
    OraclePgqlResultSet oprs= null;

    try {
      // Create a connection to Oracle
      oracle = new Oracle("jdbc:oracle:thin:@localhost:1521/orcl", "unibench", "oracle");
      opg = OraclePropertyGraph.getInstance(oracle, szGraph);
      ops = OraclePgqlExecutionFactory.createStatement(opg);
      OraclePgqlSqlTrans sqlTrans = ops.translateQuery(query, "");
      
      // Print SQL translation
      System.out.println("-- SQL Translation ----------------------");
      System.out.println(sqlTrans.getSqlTranslation());
      
      oprs = ops.executeQuery(query, " ");

      // iterate through result
       System.out.println("-- Objects retrieved from the query --");
       printResults(oprs);
       //Iterator<OraclePgqlResult> itr = oprs.getResults().iterator();
      //while (itr.hasNext()) {
       // OraclePgqlResult opr = itr.next();
        //OracleVertex v = opr.getVertex(1);
        //System.out.println(opr.toString());
      //} 
      
      System.out.println("Done.");

    } finally {
    	// close the result set
        if (oprs != null) {
          oprs.close();
        }
        // close the statement
        if (ops != null) {
          ops.close();
        }
        // close the property graph
        if (opg != null) {
          opg.shutdown();
        }
        // close oracle
        if (oracle != null) {
          oracle.dispose();
        }
	}
    
    }
  /**
   * Prints an OraclePgqlResultSet
   */
  static void printResults(OraclePgqlResultSet oprs) throws Exception
  {
    // Use OraclePgqlResultSetMetaData object to determine number of result columns
    OraclePgqlResultSetMetaData rsmd = oprs.getMetaData();
    int colCount = rsmd.getColumnCount();

    // Use an iterator to consume the result set
    Iterator<OraclePgqlResult> itr = oprs.getResults().iterator();
    while (itr.hasNext()) {
      OraclePgqlResult opr = itr.next();
      StringBuffer buff = new StringBuffer("[");
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
      for (int i = 1; i <= colCount; i++) {
        buff.append(rsmd.getColumnName(i)).append("=");
        OraclePgqlColumnDescriptor.Type t = opr.getColumnType(i);
        switch (t) {
          case VERTEX:
        	Object vertexResult = opr.getValue(i);
        	System.out.println(vertexResult);
            buff.append("Vertex: " + vertexResult.toString() + " \n");
            break;
          case EDGE:
        	  Object edgeResult = opr.getValue(i);
              buff.append("Edge: " + edgeResult.toString() + " \n");
              break;
          case VALUE:
            // process value
            int valueType = opr.getValueType(i);
            Object obj = opr.getValue(i);
            switch(valueType) {
              case OraclePropertyGraphBase.TYPE_DT_BOOL:
                buff.append("BOOLEAN:"+obj.toString()+" ");
                break;
              case OraclePropertyGraphBase.TYPE_DT_DATE:
                buff.append("DATE:"+sdf.format((Date)obj)+" ");
                break;
              case OraclePropertyGraphBase.TYPE_DT_DOUBLE:
                buff.append("DOUBLE:"+obj.toString()+" ");
                break;
              case OraclePropertyGraphBase.TYPE_DT_FLOAT:
                buff.append("FLOAT:"+obj.toString()+" ");
                break;
              case OraclePropertyGraphBase.TYPE_DT_INTEGER:
                buff.append("INTEGER:"+obj.toString()+" ");
                break;
              case OraclePropertyGraphBase.TYPE_DT_LONG:
                buff.append("LONG:"+obj.toString()+" ");
                break;
              case OraclePropertyGraphBase.TYPE_DT_STRING:
                buff.append("STRING:"+obj.toString()+" ");
                break;
            }      
            break;
        }
        }
      System.out.println(buff.toString() + "]");
      }
      }
    }
