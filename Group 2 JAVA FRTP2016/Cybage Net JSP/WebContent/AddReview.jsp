<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import="java.sql.*" %>
<%@ page import="databaseconnection.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>

<%

		 session=request.getSession();
		String username=session.getAttribute("loginuser").toString();
		Integer bookid=Integer.parseInt(request.getParameter("radio"));
		String review=request.getParameter("review");
		MyConnection c=new MyConnection();
		Connection con;
		try 
		{
			//this is to get user id and name of the user
			con = c.connect();
       Statement st = null;
       PreparedStatement ps=null;
       String sql="SELECT name,user.userId FROM login join user on login.userId = user.loginId where login.userName=  ?";
       ps = con.prepareStatement(sql);
		
	
		ps.setString(1 , username);
	
       
       ResultSet rs=ps.executeQuery();
       rs.next();
       	String name=rs.getString("name");
       	Integer userid =rs.getInt("userId");
PreparedStatement pst = null;
		

//this is to get latest reviewid from review table.
		 sql="select * from review";
	       st=con.createStatement();
	 rs=st.executeQuery(sql);
	       while(rs.next())
	       {
	    	
	    	   	
	       }
	       rs.previous();
	       Integer rid=rs.getInt("reviewId");
	       rid++;
	

	     //this is to insert record in review table .
	       String insertQuery = "INSERT INTO review"	+ "(reviewId,description) VALUES" + "(?,?)";
		pst = con.prepareStatement(insertQuery);
		
		pst.setInt(1, rid);
		pst.setString( 2, review);
		pst.executeUpdate();
		
		//this is to get last primary id user_review table;
		 sql="select * from user_review";
	       st=con.createStatement();
	 rs=st.executeQuery(sql);
	       while(rs.next())
	       {
	    	
	    	   	
	       }
	       rs.previous();
		Integer id=rs.getInt("id");
		id++;
		
		  //this is to insert record in user_review table .
 insertQuery = "INSERT INTO user_review"	+ "(id,bookid,reviewid,userid) VALUES" + "(?,?,?,?)";
			pst = con.prepareStatement(insertQuery);
			
			pst.setInt(1, id);
			pst.setInt(2, bookid);
			pst.setInt(3, rid);
			pst.setInt(4, userid);
		System.out.println(pst.executeUpdate());
		System.out.println("review submited successfully.");
		response.sendRedirect("User.html");
		}
		catch(Exception e)
		{
				System.out.println(e);
		}
		%>