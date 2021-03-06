package cybagenetpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DatabaseConnection.MyConnection;

@WebServlet("/DisplayBookDetails")
public class DisplayBookDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DisplayBookDetails() {
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
        int n=Integer.parseInt(request.getParameter("radio"));
        session.setAttribute("bookid",n);
   	 PrintWriter out = response.getWriter();
   	out.println("<div align=\"center\">");
    out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/RemoveBook.css\">");
     response.setContentType("text/html");
     String loginuser=  session.getAttribute("loginuser").toString();
     response.getWriter().append("<h1>Hello"+loginuser+"</h1>");
  
     out.println("<html><body>");
    
     MyConnection c=new MyConnection();
		Connection con;
		try 
		{
			con = c.connect();
 
       
            Statement st = null;
         String insertQuery = "SELECT distinct b.BookId, b.BookName,b.AuthorName,r.description,u.name"
         		+ "  FROM book  b   join user_review ur on b.BookId=ur.bookid"
         		+ "  join review r on r.reviewid=ur.reviewid   join user u on u.userid=ur.userid"
         		+ " where b.BookId="+n;
    
         st=con.createStatement();
     
      
      
     ResultSet rs=  st.executeQuery(insertQuery);
       	
     	
     	   out.println(" <form action=\"AddReviewServlet\"  name=\"myForm\"  method=\"get \"   > ");

     			

     	
         out.println("<table border=1 width=50% height=25%>");
         String radio="";
 
         out.println("<tr><th>Book Name</th><th>Author Name</th><th>Review</th><th>Reviewed By</th><tr>");
        
         if(!rs.next())
         {
        	 response.sendRedirect("FirstReview.html");
         }
         else 
         {
         while (rs.next()) {
         	
             String a = rs.getString("BookName");
             
             String bn = rs.getString("AuthorName");
         
             String review=rs.getString("description");
            
             
             String user=rs.getString("name");
            
           int id=rs.getInt("BookId");
             radio=""+id;
             //out.print("<tr><td><input type='radio'  name='radio' value='"+radio+"' > </td></tr>");
             out.println("<tr><td>"+" <input type='radio'  name='radio' value='"+radio+"' required> "+ a + "</td><td>" + bn + "</td><td>" + review + "</td><td>" + user + "</td></tr>"); 
           
             
         }
         
         
         out.println("</table>");
         out.println("<textarea rows=\" 5\" cols=\" 40 \" name='review' required=\"required\">  </textarea><br><br>");
         
         out.println("<input type='submit' value='Add Review' >");
         out.print("</form>");
         out.println("</div>");
         out.println("</html></body>");
         con.close();
         
         }
         
        
        }
         catch (Exception e) {
         out.println(""+e);
     }
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
