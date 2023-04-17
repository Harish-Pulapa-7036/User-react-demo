package Crud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class Insert
 */
@WebServlet("/Insert")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Insert() {
		super();
		// TODO Auto-generated constructor stub
	}

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Db_connect myobj = new Db_connect();
		Connection con = myobj.get_connection();
		PrintWriter out = response.getWriter();
		PreparedStatement ps = null;
		try {

//			String[] values=request.getParameterValues("Insert");
//			System.out.print(values);// getting Null
//			request.getParameterMap("sl","user_name");
			Map<String, String[]> m = request.getParameterMap();
//			 System.out.print(m);
			Set s = m.entrySet();
//			  System.out.print(s);

			Iterator it = s.iterator();

			Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();

			String key = entry.getKey();
			JSONObject json = new JSONObject(key);
			Map<String, Object> map = json.toMap();
//			System.out.print(map.get("sl"));

			String sl =(String) map.get("sl");
			String user_name = (String) map.get("user_name");
			String email = (String) map.get("email");
			String mobile =(String) map.get("mobile");

			String query = "insert into user(sl,user_name,email,mobile) values(?,?,?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1, sl);
			ps.setString(2, user_name);
			ps.setString(3, email);
			ps.setString(4, mobile);
			ps.executeUpdate();

			
			response.setContentType("application/json");
			response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
			response.setHeader("Access-Control-Allow-Headers",
					"Origin, X-Requested-With, Content-Type, Accept, Authorization");
			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
			response.setCharacterEncoding("UTF-8");
			out.print("Successfully Added");
			out.flush();

		} catch (Exception e) {
			response.setContentType("application/json");
			response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
			response.setHeader("Access-Control-Allow-Headers",
					"Origin, X-Requested-With, Content-Type, Accept, Authorization");
			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
			response.setCharacterEncoding("UTF-8");
			System.out.println(e.getMessage());
			out.print(e.getMessage());
			out.flush();
		
		}

	}

}