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
import DAO.Add_skill;
import VO.Cat;
import VO.Skill;
/**
 * Servlet implementation class SkillRegController
 */
@WebServlet("/SkillRegController")
public class SkillRegController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SkillRegController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag=request.getParameter("flag");
		
		
		if(flag.equals("insert")&&flag!=null)
		{
			insert(request, response);
			
		}
		else if(flag.equals("search")&&flag!=null)
		{
			search(request, response);
		}

	}
	protected void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String s1= request.getParameter("req1");
		String s2= request.getParameter("req2");
		
		String id2=(String)request.getParameter("id");
		
		Skill sl= new Skill();
		sl.setSkillName(s1);
		sl.setSkillDes(s2);
		//ct.setId(Long.valueOf(id2));
		
		
		Add_skill sc= new Add_skill();

		List ls= sc.search(sl);
		HttpSession session=request.getSession();
		session.setAttribute("ls1",ls);
		System.out.println("hi");
		response.sendRedirect("EH_Admin/search_skill.jsp");

	}

	protected void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String s1= request.getParameter("req1");
		String s2= request.getParameter("req2");
		
		String id2=(String)request.getParameter("id");
		
		
		Cat ct= new Cat();
		
		ct.setCatName(s1);
		ct.setCatDes(s2);
		//ct.setId(Long.valueOf(id2));
		
		Add_cat ac= new Add_cat();
		ac.insert(ct);
		response.sendRedirect("EH_Admin/add_skill.jsp");

		
	}

}
