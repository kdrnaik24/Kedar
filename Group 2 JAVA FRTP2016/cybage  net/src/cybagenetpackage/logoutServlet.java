package cybagenetpackage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logoutServlet")
public class logoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public logoutServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session=request.getSession();
			System.out.println(session.getId());
			
			session.invalidate();
			response.sendRedirect("login.html");
			System.out.println(session.getId());
	}


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
