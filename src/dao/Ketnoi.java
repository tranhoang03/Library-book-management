package dao;

import java.sql.Connection;
import java.sql.DriverManager;
public class Ketnoi {

		public static Connection cn;
		public void ketnoi() throws Exception{
			//B1 xac dinh he quan tri co so du lieu
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			//B2 ketnoi
			String url="jdbc:sqlserver://LAPTOP-E4D1FUO1\\SQLEXPRESS:1433;databaseName=QLSach; user=sa; password=123";
			cn=DriverManager.getConnection(url);
			
		}	
		
		public void ketnoi(String sever,String Dtb,String username,String password) throws Exception{
			//B1 xac dinh he quan tri co so du lieu
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			//B2 ketnoi
			String url="jdbc:sqlserver://"+sever+":1433;databaseName="+Dtb+"; user="+username+"; password="+password+"";
			cn=DriverManager.getConnection(url);
			
		}	

}