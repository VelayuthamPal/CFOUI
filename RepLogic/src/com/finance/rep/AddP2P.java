package com.finance.rep;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddP2P")
public class AddP2P extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con;
	int status;
	
    public AddP2P() {
        super();
    }

    
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		
		String accNo=request.getParameter("accountNumber");
		String p2pDays=request.getParameter("p2p");
		
		try {
			PreparedStatement ps=con.prepareStatement("UPDATE dtlist SET promisedays=? WHERE accountnumber=?");
			ps.setString(1, p2pDays);
			ps.setString(2, accNo);
			
			ResultSet rs=ps.executeQuery();
			
			response.sendRedirect("http://localhost:8080/RepLogic/RepPage.html#menu2");
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
