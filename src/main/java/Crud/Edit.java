package Crud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;



/**
 * Servlet implementation class Edit
 */
@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Db_connect myobj=new Db_connect();
		Connection con= myobj.get_connection();
		
		PreparedStatement ps = null;
		try {
			Map m = request.getParameterMap();
//			 System.out.print(m);
			Set s = m.entrySet();
//			  System.out.print(s);

			Iterator it = s.iterator();

			Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();

			String key = entry.getKey();
			JSONObject json = new JSONObject(key);
			Map<String, Object> map = json.toMap();
			System.out.print(map.get("sl"));

			String sl =(String) map.get("sl");
			String user_name = (String) map.get("user_name");
			String email = (String) map.get("email");
			String mobile =(String) map.get("mobile");

			String query = "update user set user_name=? ,email=?,mobile=? where sl=?";
			ps = con.prepareStatement(query);
			ps.setString(1, user_name);
			ps.setString(2, email);
			
			ps.setString(3, mobile);
			ps.setString(4, sl);
			ps.executeUpdate();
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
			response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
			response.setCharacterEncoding("UTF-8");
			out.print("updated successfully");
			out.flush();
		

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
