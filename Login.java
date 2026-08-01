package com.main;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Login extends HttpServlet{
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		     String email=req.getParameter("email").trim();
		     String epass=req.getParameter("password").trim();
		     resp.setContentType("text/html");
		     PrintWriter out=resp.getWriter();
		     
		     try {
		    	 Class.forName("com.mysql.cj.jdbc.Driver");
		    	 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_name","username","password");
		    	 String sql="Select * from users Where email=? and password=?";
		    	 PreparedStatement ps=con.prepareStatement(sql);
		    	 ps.setString(1, email);
		    	 ps.setString(2, epass);
		    	
		    	 ResultSet rs=ps.executeQuery();
		    	 if(rs.next()) {
		    		 String name=rs.getString("name");
		    		 out.println("<h2>LOGIN SUCCESSFULL!!</h2>");
		    		 out.println("<h3>WELCOME, " + name + " </h3>");
		    	 }else {
		    		 out.println("<h3>INVALID EMAIL OR PASSWORD</h3>");
		    		 out.println("<a href='login.html'>Try Again</a>");
		    	 }
		    	 con.close();
		    	 
		     }catch(Exception e) {
		    	 out.println("ERROR!!: " + e.getMessage());
		     }
		     
	}
}
