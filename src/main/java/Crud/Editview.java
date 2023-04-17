package Crud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;



/**
 * Servlet implementation class Editview
 */
@WebServlet("/Editview")
public class Editview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Editview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Db_connect myobj=new Db_connect();
		Connection con= myobj.get_connection();
		ResultSet rs;
		PreparedStatement ps;
		try {
			//System.out.println(request.getParameter("sl"));
			String sl=request.getParameter("sl");
			PrintWriter pw=response.getWriter();
			String query="select * from user where sl=?";
			ps = con.prepareStatement(query);
			ps.setString(1, sl);
			rs=ps.executeQuery();
			
			
				
				JSONObject user = new JSONObject();
				
				while(rs.next()) {
				user.put("sl", rs.getString("sl"));
				user.put("user_name", rs.getString("user_name"));
				user.put("email", rs.getString("email"));
				user.put("mobile", rs.getString("mobile"));
				}
//				System.out.println(user);
				
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
				response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
				response.setHeader("Access-Control-Allow-Credentials", "true");
				response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
				response.setCharacterEncoding("UTF-8");
				out.print(user);
				out.flush();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}