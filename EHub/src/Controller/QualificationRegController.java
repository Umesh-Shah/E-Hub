package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Add_cat;
import DAO.Add_qualification;
import VO.Cat;
import VO.Qualification;


/**
 * Servlet implementation class QualificationRegController
 */
@WebServlet("/QualificationRegController")
public class QualificationRegController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QualificationRegController() {
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
			Qualification qf4= new Qualification();
			Add_qualification aq4= new Add_qualification();
			qf4.setQuali_id(Long.valueOf(id2));
			
			
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
		Qualification qf2= new Qualification();
		qf2.setQuali_id(Long.valueOf(id1));
		Add_qualification aq2= new Add_qualification();
		
		List ls=aq2.edit(qf2);
		HttpSession session=request.getSession();
		session.setAttribute("ls2",ls);
		response.sendRedirect("EH_Admin/qualification_update.jsp");
		
		
		
	}

	protected void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		Qualification qf1= new Qualification();
		Add_qualification af1= new Add_qualification();
		
		List ls= af1.search(qf1);
		HttpSession session=request.getSession();
		session.setAttribute("ls1",ls);
		System.out.println("hi");
		response.sendRedirect("EH_Admin/search_qualification.jsp");
		

		
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
			
			Qualification qf3=new Qualification();
			Add_qualification aq3= new Add_qualification();
			qf3.setQuali_id(Long.valueOf(id2));
			qf3.setQualificationName(s1);
			qf3.setQualificationDes(s2);
			
			aq3.update(qf3);
			search(request, response);
			
			
	}

		protected void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
			String s1= request.getParameter("req1");
			String s2= request.getParameter("req2");
			
			String id2=(String)request.getParameter("id");
			
			Qualification qf= new Qualification();
			Add_qualification aq= new Add_qualification();
			qf.setQualificationName(s1);
			qf.setQualificationDes(s2);
			aq.insert(qf);
			
			response.sendRedirect("EH_Admin/add_qualification.jsp");
		
		
		
		}

		
		

}
