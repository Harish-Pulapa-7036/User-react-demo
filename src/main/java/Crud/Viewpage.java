package Crud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class Viewpage
 */
@WebServlet("/Viewpage")
public class Viewpage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Viewpage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Db_connect myobj = new Db_connect();
		Connection con = myobj.get_connection();

		try {
			// System.out.println(request.getParameter("sl"));

			String query = "select * from user";

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			List <JSONObject> users = new ArrayList<JSONObject>();
			while (rs.next()) {
				
				JSONObject user = new JSONObject();
				// Add keys and values (Country, City)
				user.put("sl", rs.getString("sl"));
				user.put("user_name", rs.getString("user_name"));
				user.put("email", rs.getString("email"));
				user.put("mobile", rs.getString("mobile"));
				//System.out.println(user);
				
				users.add(user);
			}
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
			response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
			response.setCharacterEncoding("UTF-8");
			out.print(users);
			out.flush(); 
			
			
			
			/*request.getRequestDispatcher("/viewusers.html").forward(request, response);
			/*
			 * pw.println("<table width='400' border='1'>"); pw.println("<tr>");
			 * pw.println("<th>sl</th>"); pw.println("<th>user_name</th>");
			 * pw.println("<th>email</th>"); pw.println("<th>mobile</th>");
			 * pw.println("</tr>");
			 * 
			 * while (rs.next()) { String sl = rs.getString("sl"); String user_name =
			 * rs.getString("user_name"); String email = rs.getString("email"); String
			 * mobile = rs.getString("mobile"); pw.println("<tr>");
			 * pw.println("<td>"+sl+"</td>"); pw.println("<td>"+user_name+"</td>");
			 * pw.println("<td>"+email+"</td>"); pw.println("<td>"+mobile+"</td>");
			 * pw.println("<td>"+"<a href='Editview?sl="+sl+"'>Edit</a>"+"</td>");
			 * pw.println("<td>"+"<a href='Delete?sl="+sl+"'>delete</a>"+"</td>");
			 * pw.println("</tr>");
			 * 
			 * }
			 * 
			 * 
			 * pw.println("</table>");
			 */

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	
	

}
