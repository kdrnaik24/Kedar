

<%@ page import="java.sql.*" %>
<%@ page import="databaseconnection.*" %>




<%  






MyConnection c=new MyConnection();
Connection con;
 session=request.getSession();
try 
{
	con = c.connect();


PreparedStatement pst = null;

Integer id = Integer.parseInt(request.getParameter("bid"));
String bookname = request.getParameter("bname");
String authorname = request.getParameter("author");

String insertQuery = "INSERT INTO BOOK"	+ "(BookId,BookName,AuthorName) VALUES" + "(?,?,?)";
pst = con.prepareStatement(insertQuery);

pst.setInt(1, id);
pst.setString( 2, bookname);
pst.setString( 3, authorname);

System.out.println(pst.executeUpdate());
System.out.println("Book Inserted...");

response.sendRedirect("Admin.html");
} 
catch (Exception e)
{
	e.printStackTrace();
}













%>