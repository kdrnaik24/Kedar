package cybagenetpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import DatabaseConnection.Log;
import DatabaseConnection.MyConnection;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request , ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

	System.out.println("In Filter***********************");
	
	MyConnection c=new MyConnection();
	  RequestDispatcher rd=null;

	
	try {
		
		Connection con=c.connect();
		if(con==null)
		{
			System.out.println("not open");
		}
		else
		{
			System.out.println("connection open");
		}
		Statement st = con.createStatement();	
		String type="";
		String uname=request.getParameter("uname");
		Cookie a=new Cookie("Username", uname);
		String passwd=request.getParameter("passwd");
		boolean f=false;
		ResultSet rs = st.executeQuery("select * from login");
		while(rs.next())
		{
			System.out.println("in while rs");
					if(rs.getString("userName").equals(uname)&&rs.getString("password").equals(passwd))
					{
						
							f=true;
							 type=rs.getString("type");
							 System.out.println(uname);
							 System.out.println(passwd);
							 System.out.println(type);
							 Log.maintainLog(uname);
						
					}
				
		}
		if(f==true)
		{
				
					
					  rd=request.getRequestDispatcher("LoginServlet");  
					    rd.include(request, response);  
					
			
				
		}
		else if(f==false)
		{
		
			  rd=request.getRequestDispatcher("error.html");  
			    rd.include(request, response);  
		
		}
		
	} catch (Exception e) {

		e.printStackTrace();
	}
	
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
