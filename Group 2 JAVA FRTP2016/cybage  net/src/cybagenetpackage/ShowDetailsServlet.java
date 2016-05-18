package cybagenetpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DatabaseConnection.MyConnection;


@WebServlet("/ShowDetailsServlet")
public class ShowDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(); 
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
	            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	          
	          String bookname= request.getParameter("bname");
	          PreparedStatement pst = null;
	            String insertQuery = "select distinct  BookId, BookName from book where BookName like ?";
	       
	            pst = con.prepareStatement(insertQuery);
	         
	            pst.setString(1,"%"+bookname+"%");

	        ResultSet rs=  pst.executeQuery();
	          
	            out.println("<table border=1 width=50% height=25%>");
	            String radio="";
	           out.println(" <form action=\"DisplayBookDetails\"    method=\"get \" > ");
	            
	           out.println("<tr><th>Book Name</th><th>Book Id </th><tr>");
	           while (rs.next()) {
	            	
	                String a = rs.getString("BookName");
	                int id=rs.getInt("BookId");
	                radio=""+id;
	                out.println("<tr><td>"+" <input type='radio'  name='radio' value='"+radio+"' required> "+ a + "</td><td>" + id + "</td>"); 
	            }
	            out.println("</table>");
	    		out.println("<input type='submit' value='Details'>");
	            out.print("</form>");
	            out.println("</div>");
	            out.println("</html></body>");
	            con.close();
	           }
	            catch (Exception e) {
	            out.println(""+e);
	        }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
