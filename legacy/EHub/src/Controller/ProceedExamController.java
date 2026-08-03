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

import DAO.Add_city;
import DAO.Add_exam;
import DAO.Add_ques;
import DAO.Add_result;
import VO.City;
import VO.Country;
import VO.Exam;
import VO.Ques;
import VO.Result;
import VO.Signup;

/**
 * Servlet implementation class ProceedExamController
 */
@WebServlet("/ProceedExamController")
public class ProceedExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProceedExamController() {
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
				else if(flag!=null&&flag.equals("result"))
						{
						result(request, response);
						}
		
	}

	private void result(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("EH_User/proceed_exam.jsp");
	}

	private void load(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		String d = request.getParameter("exam_code");
		
		System.out.println("id = " + d);
		
		Exam v = new Exam();
		v.setExam_id(Long.valueOf(d));

		Ques s = new Ques();
		s.setExam_id(v);
		
		
		Add_ques c =new Add_ques();
		s.setExam_id(v);
		
			List lm=new ArrayList();
			System.out.println("hiii,helo");
		    lm=c.load(s);
		
		  HttpSession session=request.getSession();
		session.setAttribute("lssearch",lm);
		session.setAttribute("cntexam",1);
		session.setAttribute("id",d);
		session.removeAttribute("examresult");
		response.sendRedirect("EH_User/proceed_exam.jsp"); 

	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag=request.getParameter("flag");

    	System.out.println(flag);
    	if(flag.equals("next")&&flag!=null)
		{
    				
		String d= request.getParameter("ques1");
		String d1= request.getParameter("ans");
		HttpSession session=request.getSession();
		
		Add_ques ad=new Add_ques();
		List ls =ad.checkAns(Long.valueOf(d));
		Ques ques =(Ques) ls.get(0);
		
		if(d1.equals(ques.getCorrectAns()))
		{
			if(session.getAttribute("examresult")==null)
			{
				session.setAttribute("examresult",ques.getMarks());
				System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiifffffffffffffffff"+session.getAttribute("examresult"));
			}
			else
			{
				long exammark = (Long)session.getAttribute("examresult");
				session.setAttribute("examresult",ques.getMarks()+exammark);
				System.out.println("eeeeeeeeeeeeeeeeeeeeeeeee"+session.getAttribute("examresult"));
			}
			
			
			
		}
			
		
		session.setAttribute(d,d1);
		
		response.sendRedirect("EH_User/proceed_exam.jsp");
		
		
	}
	
	else if(flag.equals("insert")&&flag!=null){
		insert(request, response);
	}
}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, NumberFormatException {
		// TODO Auto-generated method stub
		String s1= request.getParameter("result");
		
		String id2= request.getParameter("exam_code");
		Exam r1=new Exam();
		r1.setExam_id(Long.valueOf(id2));
	
//		Signup sp= new Signup();
//		sp.setUserID(Long.valueOf(id2));
		
		Result rs= new Result();
		rs.setExam_id(r1);
		rs.setResult(Long.valueOf(s1));
		
			
		
		Add_result cu= new Add_result();
		cu.insert(rs);
		
		response.sendRedirect("Result?flag=load");

		
	

		
	}

	
}
