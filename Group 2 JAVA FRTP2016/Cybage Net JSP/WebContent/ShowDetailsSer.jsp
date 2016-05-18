<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*" %>
<%@ page import="databaseconnection.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align="center">;
<link rel="stylesheet" type="text/css" href="CSS/RemoveBook.css">
 <table border=1 width=50% height=25%>
   <form action="DisplayBookDetails.jsp"    method="get" >
   <tr><th>Book Name</th><th>Book Id </th><tr>
<%

	session=request.getSession(); 
	response.setContentType("text/html");
%>
    
     <h1>Hello<%session.getAttribute("loginuser").toString();%></h1>
 <% 
    MyConnection c=new MyConnection();
	Connection con;
	try 
	{
		con = c.connect();
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      
      String bookname= request.getParameter("bname");
      PreparedStatement pst = null;
      
      	//searching the book from database according to key entered in user.html
        String insertQuery = "select distinct  BookId, BookName from book where BookName like ?";
   
        pst = con.prepareStatement(insertQuery);
     
        pst.setString(1,"%"+bookname+"%");

    ResultSet rs=  pst.executeQuery();
      
      
        String radio="";
      
        
    
       while (rs.next()) {
        	
            String a = rs.getString("BookName");
            int id=rs.getInt("BookId");
            radio=""+id;
            out.println("<tr><td>"+" <input type='radio'  name='radio' value='"+radio+"' > "+ a + "</td><td>" + id + "</td>"); 
        }
       
       
       %>
        </table>
		<input type="submit" value="Details">
        </form>
        </div>
        </html></body>
        
        <% con.close();
       
		}
        catch (Exception e) {
        out.println(""+e);
    	}

		%>


















</body>
</html>