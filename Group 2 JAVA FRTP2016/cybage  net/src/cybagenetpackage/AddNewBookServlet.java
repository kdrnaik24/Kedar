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

@WebServlet("/AddNewBookServlet")
public class AddNewBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AddNewBookServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
			
		MyConnection c=new MyConnection();
		Connection con;
		HttpSession session=request.getSession();
		try 
		{
			con = c.connect();
		
		
		PreparedStatement pst = null;
		
		Integer id = Integer.parseInt(request.getParameter("bid"));
		String bookname = request.getParameter("bname");
		String authorname = request.getParameter("author");
		
		BookDao b=new BookDao();
		//gives call to DAO Layer and addnew book method to add new book to database
		b.addNewBook(id, bookname, authorname);

		response.sendRedirect("Admin.html");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
