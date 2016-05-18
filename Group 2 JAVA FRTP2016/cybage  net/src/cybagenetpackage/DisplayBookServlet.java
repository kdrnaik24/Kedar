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
        out.println("<html><body>");
        String loginuser=  session.getAttribute("loginuser").toString();
        out.println("<h1 align=\"center\"> Hello     "+loginuser+"</h1>");
      
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
      
		out.println("<div id=\"hh\">");
		response.getWriter().append("<h2  align=\"right\"> Total  Logged In Users 	:"+sessioncount+"</h2>");
		response.getWriter().append("<h2 align=\"right\"> Total Hits  				:"+hitcount+"</h2>");
		out.println("</div>");
      
      
        c=new MyConnection();

		try 
		{
			con = c.connect();
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from book");
            
            out.println("<div align=\"center\">");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/RemoveBook.css\">");
            out.println("<table border=1 width=50% height=25%>");
            String radio="";
            out.println(" <form action=\"DeleteBookServlet\"    method=\"get \" > ");
            out.println("<tr><th>Book ID</th><th>Book Name</th><th>Author</th><tr>");
            while (rs.next()) {
            	
                String a = rs.getString("AuthorName");
                
                String bn = rs.getString("BookName");
                int id = rs.getInt("BookId"); 
                
                radio=""+id;
                //out.print("<tr><td><input type='radio'  name='radio' value='"+radio+"' > </td></tr>");
                out.println("<tr><td>"+" <input type='radio'  name='radio' value='"+radio+"' required > "+ id + "</td><td>" + bn + "</td><td>" + a + "</td></tr>"); 
            }
            
            out.println("</table>");
    		out.println("<input id=\"del\" type='submit' value='Delete'>");
            out.print("</form>");
            out.println("</div>");
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


