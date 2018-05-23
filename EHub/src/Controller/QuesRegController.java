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

import DAO.Add_cat;
import DAO.Add_exam;
import DAO.Add_ques;
import VO.Cat;
import VO.Exam;
import VO.Ques;

/**
 * Servlet implementation class QuesRegController
 */
@WebServlet("/QuesRegController")
public class QuesRegController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuesRegController() {
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
					
				}/*
				else if(flag.equals("update")&&flag!=null){
					update(request, response);
				}
				else{System.out.println("error");}
		
	*/
		
	
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
		response.sendRedirect("EH_Admin/manage_questions.jsp"); 

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag=request.getParameter("flag");
		
		System.out.println(flag);
				if(flag.equals("insert")&&flag!=null)
				{
				insert(request, response);
					
				}
				else if(flag.equals("update")&&flag!=null){
					update(request, response);
				}
				else{System.out.println("error");}
						
		
			
		
		
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String s1= request.getParameter("req1");
		String s2= request.getParameter("req2");
		String s3= request.getParameter("req5");
		String s4= request.getParameter("req6");
		String s5= request.getParameter("req7");
		String s6= request.getParameter("req3");
		String s7= request.getParameter("req4");
		
		String id2=(String)request.getParameter("examtype");
		Exam ex= new Exam();
		ex.setExam_id(Long.valueOf(id2));
		
		
		
		Ques qs= new Ques();
		Add_ques aq= new Add_ques();
		qs.setQues(s1);
		qs.setAns1(s2);
		qs.setAns2(s3);
		qs.setAns3(s4);
		qs.setAns4(s5);
		qs.setCorrectAns(s7);
		qs.setMarks(Long.valueOf(s6));
		qs.setExam_id(ex);
		
		aq.insert(qs);
		
		
		response.sendRedirect("EH_Admin/manage_questions.jsp");
	

	}

}
