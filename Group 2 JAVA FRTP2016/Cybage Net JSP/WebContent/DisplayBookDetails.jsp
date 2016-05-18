<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@ page import="java.sql.*" %>
<%@ page import="databaseconnection.*" %>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<div align="center">
<link rel="stylesheet" type="text/css" href="CSS/RemoveBook.css">

</head>
<body>
 <form action="AddReview.jsp"    method="get" >
 <table border=1 width=50% height=25>
<tr><th>Book Name</th><th>Author Name</th><th>Review</th><th>Reviewed By</th><tr>
<%

session=request.getSession(); 
response.setContentType("text/html");
%>

 <h1>Hello<%session.getAttribute("loginuser").toString();%></h1>



<% 
int n=Integer.parseInt(request.getParameter("radio"));

response.setContentType("text/html");

MyConnection c=new MyConnection();
Connection con;
try 
{
	con = c.connect();
		

   Statement st = null;
String insertQuery = "SELECT distinct b.BookId, b.BookName,b.AuthorName,r.description,u.name"
		+ "  FROM book  b join user_review ur on b.BookId=ur.bookid"
		+ "  join review r on r.reviewid=ur.reviewid join user u on u.userid=ur.userid"
		+ " where b.BookId="+n;

st=con.createStatement();



ResultSet rs=  st.executeQuery(insertQuery);


String radio="";
	//deatils of selected book will be displayed here

while (rs.next()) {
	
    String a = rs.getString("BookName");
    
    String bn = rs.getString("AuthorName");

    String review=rs.getString("description");
    
    String user=rs.getString("name");
  int id=rs.getInt("BookId");
    radio=""+id;
    //out.print("<tr><td><input type='radio'  name='radio' value='"+radio+"' > </td></tr>");
    out.println("<tr><td>"+" <input type='radio'  name='radio' value='"+radio+"' > "+ a + "</td><td>" + bn + "</td><td>" + review + "</td><td>" + user + "</td></tr>"); 
  
    
}
con.close();

	%>
	

</table>
<textarea rows="5" cols="40" name="review">  </textarea><br><br>
<input type="submit" value="Add Review">
</form>
</div>
</html></body>
			
			
					<% 
					
				}
					catch (Exception e) 
					{
					out.println(""+e);
					}


		%>




</body>
</html>