package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DatabaseConnection1
 */
@WebServlet("/DatabaseConnection1")
public class DatabaseConnection1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatabaseConnection1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 	PrintWriter pw=res.getWriter();
	        res.setContentType("text/html");        
	        String tb=req.getParameter("table");    

	        try
	        {
	             Class.forName("oracle.jdbc.driver.OracleDriver");
	             Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
	             Statement st=con.createStatement();
	             System.out.println("connection established successfully...!!");     

	             ResultSet rs=st.executeQuery("Select * from "+tb);

	             pw.println("<table border=1>");
	                 while(rs.next())
	                 {
	                     pw.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td>"+"<td>"+rs.getString(3)+"</td></tr>");
	                     System.out.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td>"+"<td>"+rs.getString(3)+"</td></tr>");
	                 }
	             pw.println("</table>");
	             pw.close();
	        }
	        catch(SQLException e)
	        {
	        	pw.print("<h1>SQL error </h1>");
	        }
	        catch (Exception e){
	            e.printStackTrace();
	        }
	}

}
