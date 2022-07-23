package epproject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			  String e=request.getParameter("txtEmail");  
		      String p=request.getParameter("txtPwd");        
		  try{  
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankfull","root","Krishnaprasad@3");
		  PreparedStatement ps=con.prepareStatement("select email from user where email=? and password=?");  
		  ps.setString(4,e);  
		  ps.setString(3,p);
		  ResultSet rs=ps.executeQuery();
          System.out.println("Function Running and query finished");
          if(rs.next()) {
          	RequestDispatcher rd1=request.getRequestDispatcher("welcome.jsp");
          	rd1.forward(request,response);
			System.out.print("Hello world");
          }
          else {
          	RequestDispatcher rd1=request.getRequestDispatcher("failure.jsp");
          	rd1.forward(request, response);
          }
		}
		catch(ClassNotFoundException y) {
			y.printStackTrace();
		} catch (SQLException z) {
			
			z.printStackTrace();
		}
	}
}