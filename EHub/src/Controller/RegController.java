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
import VO.Cat;


/**
 * Servlet implementation class RegCotroller
 */
@WebServlet("/RegController")
public class RegController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RegController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	/*	String s1= request.getParameter("fn");
		String s2= request.getParameter("ln");
		String flag=request.getParameter("flag"); */
		String id2=(String)request.getParameter("id");
		
		
		
		Cat ct= new Cat();
		
		ct.setId(Long.valueOf(id2));
	/*	
		FirstExample fe= new FirstExample();
	
	
		if(flag.equals("delete"))
		{
		fe.delete(ct);	
		}
		else if(flag.equals("edit"))
		{
			
			List ls=fe.edit(ct);
			HttpSession session=request.getSession();
			session.setAttribute("ls2",ls);
			response.sendRedirect("update.jsp");
			
		
		}
		
		//System.out.println("success");
		
	}
	
	*/
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		
String flag=request.getParameter("flag");
		
		if(flag.equals("insert")&&flag!=null)
		{
			insert(request, response);
			
		}
		else if(flag.equals("search")&&flag!=null){
			search(request, response);
		}
				
		
		
		
		
		
		
		/*
		
				else if(flag.equals("update"))
		{
			String s5= request.getParameter("fn1");
			String s6= request.getParameter("ln1");
			String id=(String)request.getParameter("id");
			ct.setId(Long.valueOf(id));
			ct.setFirstName(s5);
			ct.setLastName(s6);
		
			fe.update(ct);
			System.out.println("hi");
			
		}
*/
	}

	protected void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String s1= request.getParameter("req1");
		String s2= request.getParameter("req2");
		
		String id2=(String)request.getParameter("id");
		
		
		Cat ct= new Cat();
		ct.setCatName(s1);
		ct.setCatDes(s2);
		ct.setId(Long.valueOf(id2));
		//ct.setId(Long.valueOf(id2));
		
		Add_cat ac= new Add_cat();
		ac.insert(ct);
		response.sendRedirect("EH_Admin/add_category.jsp");

	}

	protected void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub

		String s1= request.getParameter("req1");
		String s2= request.getParameter("req2");
		
		String id2=(String)request.getParameter("id");
		
		
		

		
		Cat ct= new Cat();
		ct.setCatName(s1);
		ct.setCatDes(s2);
		ct.setId(Long.valueOf(id2));
		
		
		Add_cat ac= new Add_cat();

		List ls= ac.search(ct);
		HttpSession session=request.getSession();
		session.setAttribute("ls1",ls);
		System.out.println("hi");
		response.sendRedirect("search.jsp");
		
	}
}

