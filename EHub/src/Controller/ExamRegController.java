package Controller;

import java.io.IOException;
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
 * Servlet implementation class ExamRegController
 */
@WebServlet("/ExamRegController")
public class ExamRegController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamRegController() {
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
    	if(flag.equals("search")&&flag!=null)
		{
			search(request, response);
			
		}
		else if(flag.equals("edit")&&flag!=null){
			edit(request, response);
		}
		else if(flag.equals("delete")&&flag!=null){
			delete(request, response);
		}
    }			

	protected void delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			
			String id2=(String)request.getParameter("id");
			Exam qf4= new Exam();
			Add_exam aq4= new Add_exam();
			qf4.setExam_id(Long.valueOf(id2));
			
			
			HttpSession session= request.getSession();
			
			
			if(!aq4.delete(qf4))
			{
				session.setAttribute("deleteflag", "false");
			}
				
			search(request, response);
		
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
		
		

		
		

	protected void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String id1=(String)request.getParameter("id");
		Exam qf2= new Exam();
		qf2.setExam_id(Long.valueOf(id1));
		Add_exam aq2= new Add_exam();
		
		List ls=aq2.edit(qf2);
		HttpSession session=request.getSession();
		session.setAttribute("ls2",ls);
		response.sendRedirect("EH_Admin/Exam_update.jsp");
		
		
		
	}

	protected void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		Exam qf1= new Exam();
		Add_exam af1= new Add_exam();
		
		List ls= af1.search(qf1);
		HttpSession session=request.getSession();
		session.setAttribute("ls1",ls);
		System.out.println("hi");
		response.sendRedirect("EH_Admin/search_Exam.jsp");
		

		
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
			
			protected void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
			// TODO Auto-generated method stub
				String s1= request.getParameter("req1");
				String s2= request.getParameter("req2");
				
				String id2=(String)request.getParameter("id");
				
				Exam qf3=new Exam();
				Add_exam aq3= new Add_exam();
				qf3.setExam_id(Long.valueOf(id2));
				qf3.setExamName(s1);
				qf3.setExamDes(s2);
				
				aq3.update(qf3);
				search(request, response);
				
				
		}

			protected void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
			// TODO Auto-generated method stub
				String s1= request.getParameter("req1");
				String s2= request.getParameter("req2");
				String s3= request.getParameter("req3");
				String s4= request.getParameter("req4");
				String s5= request.getParameter("req5");
				
				String id2=(String)request.getParameter("id");
				
				Exam qf= new Exam();
				Add_exam aq= new Add_exam();
				qf.setExamName(s1);
				qf.setExamDes(s2);
				qf.setDuration(Long.valueOf(s3));
				qf.setNo_ques(Long.valueOf(s4));
				qf.setMarks(Long.valueOf(s5));
				aq.insert(qf);
				
				response.sendRedirect("EH_Admin/manage_exam.jsp");
			
			
			
			}

			
			

	}


