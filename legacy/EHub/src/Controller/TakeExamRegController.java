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

import DAO.Add_exam;
import VO.Exam;

/**
 * Servlet implementation class TakeExamRegController
 */
@WebServlet("/TakeExamRegController")
public class TakeExamRegController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeExamRegController() {
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
				if(flag.equals("load")&&flag!=null)
				{
				load(request, response);
					
				}
		
	
	}

	private void load(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		Exam r=new Exam();
		
		Add_exam c =new Add_exam();
		
			List lm=new ArrayList();
			System.out.println("hiii,helo");
		    lm=c.load(r);
		
		  HttpSession session=request.getSession();
		session.setAttribute("lssearch",lm);
		response.sendRedirect("EH_User/take_exam.jsp"); 

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
