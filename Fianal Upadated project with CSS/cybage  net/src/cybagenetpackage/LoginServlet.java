package cybagenetpackage;
import DatabaseConnection.MyConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static int sessionCount=0;
    public LoginServlet() {
    	System.out.println("in login servlet");
    }

    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyConnection c=new MyConnection();
		HttpSession session=request.getSession();
	
		
		try {
			session.setAttribute("hitcount",sessionCount);
			Connection con=c.connect();
			if(con==null)
			{
				System.out.println("not open");
			}
			else
			{
				System.out.println("connection open");
			}
			PreparedStatement pst=null;
			String type="";
			String uname=request.getParameter("uname");
			Cookie a=new Cookie("Username", uname);
			String passwd=request.getParameter("passwd");
			boolean f=false;
			pst=con.prepareStatement("select * from login where userName= ?");
			pst.setString(1, uname);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				System.out.println("in while rs");
						if(rs.getString("userName").equals(uname)&&rs.getString("password").equals(passwd))
						{
							
								
								 type=rs.getString("type");
								 System.out.println(uname);
								 System.out.println(passwd);
								 System.out.println(type);
								 session.setAttribute("loginuser",uname );
								 response.addCookie(a);
								 System.out.println(session.getId());
						
					
			}
			
					if(type.equals("admin"))
					{
						System.out.println("in admin");
						RequestDispatcher  rd=request.getRequestDispatcher("Admin.html");  
						    rd.include(request, response);  
					}
					else
					{
						RequestDispatcher  rd=request.getRequestDispatcher("User.html");  
					    rd.include(request, response);  
					}	
			}
		}
			
		catch (Exception e) {

			e.printStackTrace();
		}
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
