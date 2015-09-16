package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
	
	public Connection conexiune() throws ClassNotFoundException{
		Connection con=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","DanescuOSF","DanescuOSF");
			//System.out.println("Connected");
		}
		catch(SQLException ex){			
		}
		return con;
	}
	
	public void close(Connection con, Statement stmt, ResultSet rs)throws SQLException{
		if(rs!=null){
			rs.close();
		}
		if(stmt!=null){
			stmt.close();
		}
		if(con!=null){
			con.close();
		}
	}
	
	public void close(Statement stmt, ResultSet rs) throws SQLException {
        close(null, stmt, rs);
    }

    public void close(Statement stmt) throws SQLException {
        close(null, stmt, null);
    }
	
}
