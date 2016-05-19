 package cybagenetpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DatabaseConnection.MyConnection;


@WebServlet("/DisplayBookServlet")
public class DisplayBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DisplayBookServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
        response.setContentType("text/html");
        String cnt=session.getAttribute("hitcount").toString();
        out.println("<html><head>"
        		+ "<link rel='stylesheet' type='text/css' href='CSS/style.css'>"
        		+ "</head><body><div id ='header'><h1>Welcome To  CybNet</h1></div>");
        out.println("<form action='logoutServlet' method='get'>"
        		+ "<div id='subheader'>"
        		+ "<button class='button' value='Logout'>LogOut</button>"
        		+ "</div></form>"
        		+ " <form action=\"DeleteBookServlet\"    method=\"get \" > ");
        String loginuser=  session.getAttribute("loginuser").toString();
        out.println("<div id='center'><h2 align=\"left\"> Hello     "+loginuser+"</h2>");
      
      	MyConnection c=new MyConnection();
		Connection con;
		int sessioncount=0;
		int hitcount=0;
		try 
		{
			con = c.connect();
          //Class.forName("com.mysql.jdbc.Driver");
        
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
      
		
		response.getWriter().append("<h2  align=\"right\"> Total  Logged In Users 	:"+sessioncount+"</h2>");
		response.getWriter().append("<h2 align=\"right\"> Total Hits  				:"+hitcount+"</h2>");
		
        c=new MyConnection();

		try 
		{
			con = c.connect();
            //Class.forName("com.mysql.jdbc.Driver");
          
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from book");
            
            out.println("<div align=\"center\">");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/RemoveBook.css\">");
            out.println("<table border=1 width=50% height=25%>");
            String radio="";
            
            out.println("<tr><th>Book ID</th><th>Book Name</th><th>Author</th><tr>");
            while (rs.next()) {
            	
                String a = rs.getString("AuthorName");
                
                String bn = rs.getString("BookName");
                int id = rs.getInt("BookId"); 
                
                radio=""+id;
                //out.print("<tr><td><input type='radio'  name='radio' value='"+radio+"' > </td></tr>");
                out.println("<tr><td>"+" <input type='radio'  name='radio' value='"+radio+"' > "+ id + "</td><td>" + bn + "</td><td>" + a + "</td></tr>"); 
            }
            
            out.println("</table><br><br>");
    		out.println("<button class='button' value='Delete'>Delete Book</button>");
    		
            out.print("</form>");
            out.println("</div></div>");
            out.println("</html></body>");
            con.close();

           }
           catch (Exception e) {
            out.println("error");
        }
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

}


