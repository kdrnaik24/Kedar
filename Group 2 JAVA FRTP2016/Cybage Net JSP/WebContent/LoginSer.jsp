<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="databaseconnection.*"%>
    
    <%! 
    	public static int sessionCount=0;
   		 
    %>

<%



try {
		
		Connection con = null;
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cybagenet","root","root");
	
	 	session=request.getSession();

		session.setAttribute("hitcount",sessionCount);
		
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
					/*if(rs.getString("userName").equals(uname)&&rs.getString("password").equals(passwd))
					{*/
						
							
							 type=rs.getString("type");
							 System.out.println(uname);
							 System.out.println(passwd);
							 System.out.println(type);
							 session.setAttribute("loginuser",uname );
							 response.addCookie(a);
							 System.out.println(session.getId());
							 Log.maintainLog(uname);
					
				
		}
		
				if(type.equals("admin"))
				{
					System.out.println("in admin");
				 	response.sendRedirect("Admin.html");
				}
				else  if (type.equals("normal"))
				{
					response.sendRedirect("User.html");
				}	
				else
					
					response.sendRedirect("error.html");
		
	} 
		
	catch (Exception e) 
	{

		e.printStackTrace();
	}
	
	
	



%>