<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<%@ page import="databaseconnection.*" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/RemoveBook.css">
</head>
<body>
<form action="DeleteBook.jsp"    method="post " >

<%
	session=request.getSession();
	response.setContentType("text/html");
	String cnt=session.getAttribute("hitcount").toString();

%>
<h1 align="center"> Hello 
<%=  session.getAttribute("loginuser").toString()%>
</h1>

<%
MyConnection c=new MyConnection();
Connection con;
int sessioncount=0;
int hitcount=0;
try 
{
	con = c.connect();
  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

  int cn=1;
  Statement stmt = con.createStatement();
  ResultSet rs = stmt.executeQuery("select * from session");
  int c1=0,c2=0;
  while(rs.next())
  {
  	c1=rs.getInt(1);
  	c2=rs.getInt(2);
  }
  sessioncount=c1;
  hitcount=c2;
  
}
catch(Exception e)
{
	
}
%>
<div id="hh">
<h2  align="right"> Total  Logged In Users 	: <% out.println(sessioncount); %></h2>
<h2 align="right"> Total Hits  				:<%out.println(hitcount); %></h2>
</div>

	<div align="center">
    
    <table border=1 width=50% height=25">
    
    
    <tr><th>Book ID</th><th>Book Name</th><th>Author</th><tr>
<%
String radio="";
c=new MyConnection();

try 
{
	con = c.connect();
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  
    
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("select * from book");    
    
    while (rs.next()) 
    {    	
        String a = rs.getString("AuthorName");
        
        String bn = rs.getString("BookName");
        int id = rs.getInt("BookId"); 
        
        radio=""+id;     
        
        out.println("<tr><td>"+" <input type='radio'  name='radio' value='"+radio+"' > "+ id + "</td><td>" + bn + "</td><td>" + a + "</td></tr>"); 
    }
    %>
    </table>
	<input id="del" type='submit' value='Delete'>
    </form>
    </div>
    </body></html>
   <%  con.close();

   }
    catch (Exception e) {
    out.println("error");
}

%>




