package Crud;

import java.sql.Connection;
import java.sql.DriverManager;

public class Db_connect {
	public static void main(String[] args) {
		Db_connect myobj=new Db_connect();
		System.out.println(myobj.get_connection());
		
		
	}
	public Connection get_connection() {
		Connection connection=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/crud","root","4438");
			
		}catch(Exception e){
			System.out.println(e);
		}
		return connection;
		
	}
}