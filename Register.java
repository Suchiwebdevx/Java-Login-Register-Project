package com.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Register extends HttpServlet {
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ename=req.getParameter("name");
		String email=req.getParameter("email");
		String contact=req.getParameter("contact");
		String epass=req.getParameter("password");
		PrintWriter out=resp.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_name","username","password"); 
			String sql="INSERT into users(name,email,contact,password)values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,ename);
			ps.setString(2,email);
			ps.setString(3,contact);
			ps.setString(4,epass);
			int res=ps.executeUpdate();
			con.close();
			
			if(res>0) {
				resp.sendRedirect("login.html");
				out.println("<h3><b>REGISTRATION SUCCESSFULL!!</b></h3>");
				out.println("<h5><i>Redirecting to next page</i></h5>");
			}else {
				out.println("<h2>REGISTRATION FAILED!!<h3>");
			}
		}catch(Exception e) {
			//e.printStackTrace();
			resp.setContentType("text/html");
			out.println("<h3>ERROR: " + e.getMessage() + " </h3>");
			out.println("<a href='register.html'>Try Again</a>");
		}
	}

}
