package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Add_result;
import VO.Result;

/**
 * Servlet implementation class Result
 */
@WebServlet("/Result")
public class ResultController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
String flag=request.getParameter("flag");
		
		System.out.println(flag);
				if(flag!=null&&flag.equals("load"))
				{
				load(request, response);
					
				}

	}

	private void load(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
	
		Result s = new Result();
		Add_result c= new Add_result();
		
		List lm=new ArrayList();
		System.out.println("hiii,helo");
	    lm=c.load(s);
	    HttpSession session=request.getSession();
		session.setAttribute("lssearch",lm);
	    
	    response.sendRedirect("EH_User/your_result.jsp");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
