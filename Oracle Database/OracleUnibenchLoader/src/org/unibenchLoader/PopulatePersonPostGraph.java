package org.unibenchLoader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Timestamp;

import oracle.pg.common.ColumnToAttrMapping;
import oracle.pg.common.DataConverterListener;
import oracle.pg.rdbms.OraclePropertyGraph;
import oracle.pg.rdbms.OraclePropertyGraphDataLoader;
import oracle.pg.rdbms.OraclePropertyGraphUtils;
import oracle.pgx.common.types.PropertyType;
import oracle.pgx.config.GraphConfigBuilder;
import oracle.pgx.config.PgRdbmsGraphConfig;

public class PopulatePersonPostGraph {
    public void executePopulateCustomerPostGraph(String username, String password, String graphName) {
        //connect to database
        OracleConnection oc = new OracleConnection(username, password);
        
        //file locations for loading graph
        String opv = "/home/oracle/Desktop/panama/PersonsPosts.opv"; 
        OutputStream opvOS = null;
        
        String ope = "/home/oracle/Desktop/panama/hasCreatorRelation.ope"; 
        OutputStream opeOS = null;

        try {
            opvOS = new FileOutputStream(opv);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try {
            opeOS = new FileOutputStream(ope);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println("working Person hasCreated Post graph");
        
        //Load files from views on database
        //vertices
        // an array of ColumnToAttrMapping objects; each object defines how to map a column in the RDBMS table to an attribute of the vertex in an Oracle Property Graph.
        // Person:
        // The mapping is just identity mapping in order to include necessary attributes to the flat file
        ColumnToAttrMapping[] ctams = new ColumnToAttrMapping[9];
        ctams[0] = ColumnToAttrMapping.getInstance("id", "personId", Integer.class);
        ctams[1] = ColumnToAttrMapping.getInstance("firstName", "firstName", String.class);
        ctams[2] = ColumnToAttrMapping.getInstance("lastName", "lastName", String.class);
        ctams[3] = ColumnToAttrMapping.getInstance("gender", "gender", String.class);
        ctams[4] = ColumnToAttrMapping.getInstance("birthday", "birthday", Timestamp.class);
        ctams[5] = ColumnToAttrMapping.getInstance("creationDate", "creationDate", String.class);
        ctams[6] = ColumnToAttrMapping.getInstance("locationIP", "locationIP", String.class);
        ctams[7] = ColumnToAttrMapping.getInstance("browserUsed", "browserUsed", String.class);
        ctams[8] = ColumnToAttrMapping.getInstance("place", "place", Integer.class);
        
        OraclePropertyGraphUtils.convertRDBMSTable2OPV(oc.con, "person", "id", 100l, ctams, 8, opvOS, (DataConverterListener) null);
        
        // Post:
        // The mapping is just identity mapping in order to include necessary attributes to the flat file
        ColumnToAttrMapping[] ctams1 = new ColumnToAttrMapping[7];
        ctams1[0] = ColumnToAttrMapping.getInstance("id", "postId", Integer.class);
        ctams1[1] = ColumnToAttrMapping.getInstance("createDate", "createDate", String.class);
        ctams1[2] = ColumnToAttrMapping.getInstance("location", "location", String.class);
        ctams1[3] = ColumnToAttrMapping.getInstance("browserUsed", "postBrowserUsed", String.class);
        ctams1[4] = ColumnToAttrMapping.getInstance("language", "language", String.class);
        ctams1[5] = ColumnToAttrMapping.getInstance("content", "content", String.class);
        ctams1[6] = ColumnToAttrMapping.getInstance("length", "length", Integer.class);
        
        OraclePropertyGraphUtils.convertRDBMSTable2OPV(oc.con, "post", "id", 1000l, ctams1, 8, opvOS, (DataConverterListener) null);
        
        //edges
        // an array of ColumnToAttrMapping objects; each object defines how to map a column in the RDBMS table to an attribute of the edge in an Oracle Property Graph.
        // The mapping is just identity mapping in order to include necessary attributes to the flat file
        ColumnToAttrMapping[] ctams2 = new ColumnToAttrMapping[2];
        ctams2[0] = ColumnToAttrMapping.getInstance("postId", "postId", Integer.class);
        ctams2[1] = ColumnToAttrMapping.getInstance("personId", "personId", Integer.class);
        // convert RDBMS table â€œEmpRelationTab" into ope file â€œ./EmpRelationTab.opv", column â€œrelationID" is the edge ID column, offset 10000l will be applied to edge ID, the source and destination vertices of the edge are defined by columns â€œsource" and â€œdestination", offset 1000l will be applied to vertex ID, the RDBMS table has an column â€œrelationType" to be treated as edge labels, use ctams to map RDBMS columns to edge attributes, set DOP to 8
        OraclePropertyGraphUtils.convertRDBMSTable2OPE(oc.con, "postHasCreator", "postId", 10000l, "postId", "personId", 1000l, true, "postId", ctams2, 8, opeOS, (DataConverterListener) null);
        
        oc.closeCon();
        
        //create graph with properties and edges
        PgRdbmsGraphConfig cfg = GraphConfigBuilder.forPropertyGraphRdbms()
        		.setJdbcUrl("jdbc:oracle:thin:@127.0.0.1:1521/orcl") 
                .setUsername(username)
                .setPassword(password)
                .setName(graphName)
                .setMaxNumConnections(100) 
                .setLoadEdgeLabel(true)
                .addVertexProperty("firstname", PropertyType.STRING, "default_name")
                .addVertexProperty("lastname", PropertyType.STRING, "default_name")
                .addVertexProperty("gender", PropertyType.STRING, "default_name")
                .addVertexProperty("creationDate", PropertyType.STRING, "default_name")
                .addVertexProperty("locationIP", PropertyType.STRING, "default_name")
                .addVertexProperty("browserUsed", PropertyType.STRING, "default_name")
                .addVertexProperty("place", PropertyType.INTEGER, 0)
                .addVertexProperty("createDate", PropertyType.STRING, "default_name")
                .addVertexProperty("location", PropertyType.STRING, "default_name")
                .addVertexProperty("postBrowserUsed", PropertyType.STRING, "default_name")
                .addVertexProperty("language", PropertyType.STRING, "default_name")
                .addVertexProperty("content", PropertyType.STRING, "default_name")
                .addVertexProperty("length", PropertyType.INTEGER, 0)
                .addEdgeProperty("postId", PropertyType.INTEGER)
                .addEdgeProperty("personId", PropertyType.INTEGER)
                .build();
        
        OraclePropertyGraph opg = null;
        try {
            opg = OraclePropertyGraph.getInstance(cfg);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try {
            opg.clearRepository();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //Load graph with files
        OraclePropertyGraphDataLoader opgdl = OraclePropertyGraphDataLoader.getInstance();
        opgdl.loadData(opg, opv, ope, 2, 10000, true, null);

        System.out.println("Customer Post graph done.");
        
    }

}