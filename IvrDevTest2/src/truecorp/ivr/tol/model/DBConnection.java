package truecorp.ivr.tol.model;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {
	
	private DataSource ds=null;
	public static String[] ds_source=new String[] {"jdbc/ivrlog","jdbc/cmsdb"};
	
	public void DataSource(String ResourceName){

		Context initContext;
		Context envContext;
		
		try {
			initContext = new InitialContext();
		    envContext  = (Context)initContext.lookup("java:/comp/env");

		    if(envContext==null){throw new Exception("No Context");}

            ds = (DataSource)envContext.lookup(ResourceName);

		} catch (NamingException e) {
			e.printStackTrace(System.out);
		} catch (Exception e){
			e.printStackTrace(System.out);
		}
		
	}
	
	public Connection getConnection(){
		
		try{
			return ds.getConnection();
			
		} catch (SQLException e){
			e.printStackTrace(System.out);
		} catch (Exception e){
			e.printStackTrace(System.out);
		}
		
		return null;
		
	}
}
