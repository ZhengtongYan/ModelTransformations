package org.unibenchLoader;

import org.exampleQueries.PgqlExample;

public class Main {

	public static void main(String[] args) {
		//PopulatePersonPostGraph personPost = new PopulatePersonPostGraph();
		//personPost.executePopulateCustomerPostGraph("unibench", "oracle", "PersonPost_PG");
		
		//PopulatePersonPersonGraph personknowsperson = new PopulatePersonPersonGraph();
		//personknowsperson.executePopulateCustomerPostGraph("unibench", "oracle", "PersonPerson_PG");
		
		PgqlExample example = new PgqlExample();
		String pgql = 
		        "PATH fof := ()-[:knows]->() "+
		        "SELECT v2.firstName AS friend "+
		        "WHERE (v WITH firstName = 'Anatoly')-/:fof*/->(v2), "+
		        "       v != v2";
		
	      String pgql1 = 
	    	        "SELECT v "+
	    	        "WHERE (v) LIMIT 10";
	      
	      String pgql2 = "SELECT v, e, v2 "+
	    			"MATCH (v)-[e]->(v2) "
	    			+ "WHERE v.firstName = 'Choi'";
	      
	      String pgql3 = "SELECT n.firstName, n2.firstName, e " + 
	      		" MATCH (n)-[e]->(n2) LIMIT 10";
		
		try {
			example.execute("PersonPerson_PG", pgql2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
