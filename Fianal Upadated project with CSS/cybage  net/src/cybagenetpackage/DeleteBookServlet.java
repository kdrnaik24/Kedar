package cybagenetpackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BookDao;
import DatabaseConnection.MyConnection;


@WebServlet("/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public DeleteBookServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
	    
		try 
		{
		    
	      String loginuser=  session.getAttribute("loginuser").toString();
	      response.getWriter().append("<h1>Hello"+loginuser+"</h1>");
            int n=Integer.parseInt(request.getParameter("radio"));
            BookDao b=new BookDao();
    		//gives call to DAO Layer and  deleteBook method to delete selected book from database.
    		b.deleteBook(n);
    		System.out.println("Book Deleted..");
    		response.sendRedirect("Admin.html");
    
           	
         
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			
		}
	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
