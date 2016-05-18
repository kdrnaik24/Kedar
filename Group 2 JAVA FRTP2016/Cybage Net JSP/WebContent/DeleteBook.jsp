<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<%@ page import="databaseconnection.*" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>

<%

try 
{
    
  String loginuser=  session.getAttribute("loginuser").toString();

	 MyConnection c=new MyConnection();
		Connection con;
	con = c.connect();
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    
    int n=Integer.parseInt(request.getParameter("radio"));
    PreparedStatement pst = null;
    String insertQuery = "delete from book where BookId= ?";
   	pst = con.prepareStatement(insertQuery);
   	
   	pst.setInt(1,n);
   	System.out.println(pst.executeUpdate());
	System.out.println("Book Deleted..");
	response.sendRedirect("Admin.html");

   	
 
}
catch(Exception e)
{
	
}
finally
{
	
}

%>