package org.loadData;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DataLoader {
	
	public  void startUp() {
	    StringBuffer sb = new StringBuffer();
	    String path = "sqlldr user/password@sid readsize=10485760 bindsize=10485760 rows=1000 control=controlFileName.ctl log=controlFileName.log direct=true \n pause";   
	    try {
	        Process pro = Runtime.getRuntime().exec(path);
	        BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()), 4096);
	        String line = null;
	        int i = 0;
	        while ((line = br.readLine()) != null) {
	            if (0 != i)
	                sb.append("\r\n");
	            i++;
	            sb.append(line);
	        }
	    } catch (Exception e) {
	        sb.append(e.getMessage());
	    }
	}

}
