package Crud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Db_connect myobj=new Db_connect();
		Connection con= myobj.get_connection();
		String sl=request.getParameter("sl");
		PreparedStatement ps;
		
		try {
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			
			String query="delete from user where sl=?";
			ps=con.prepareStatement(query);
			ps.setString(1, sl);
			ps.executeUpdate();
			response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
			response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");
			out.print("Deleted Successfully");
			out.flush();
//			RequestDispatcher rd=request.getRequestDispatcher("/viewusers.html");
//			rd.forward(request, response);
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	
}