package cybagenetpackage;

import java.io.IOException;
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

import DAO.ReviewDAO;
import DatabaseConnection.MyConnection;


@WebServlet("/AddReviewServlet")
public class AddReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AddReviewServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{
		HttpSession session=request.getSession();
		String username=session.getAttribute("loginuser").toString();
		//Integer bookid=Integer.parseInt(request.getParameter("radio"));
		Integer bookid=(Integer)session.getAttribute("bookid");
		String review=request.getParameter("review");
		ReviewDAO rd=new ReviewDAO();
		rd.addReview(username, bookid, review);
		response.sendRedirect("User.html");
		}
		catch(Exception e)
		{
				System.out.println(e);
		}

	}


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
