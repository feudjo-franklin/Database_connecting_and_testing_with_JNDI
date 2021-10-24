package org.studyeasy;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Demo
 */
@WebServlet("/Demo")
public class Demo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/project")
	private DataSource dataSource;
   
    //public Demo() {
        // TODO Auto-generated constructor stub
    //}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Step 1: Initialize connection objects
		PrintWriter out = response.getWriter();
		Connection connect = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connect = dataSource.getConnection();
			
			//Step 2: Create a SQL statements string
			String query = "Select * from users";
			stmt = connect.createStatement();
			
			//Step 3: Execute SQL query
			rs = stmt.executeQuery(query);
			
			//Step 4: Process the result set
			while(rs.next()) {
				out.print("<br/>" + rs.getString("email"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
