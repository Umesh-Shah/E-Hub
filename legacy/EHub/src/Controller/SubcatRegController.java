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

import VO.Cat;
import VO.Subcat;
import DAO.Add_cat;
import DAO.Add_subcat;
/**
 * Servlet implementation class SubcatRegController
 */
@WebServlet("/SubcatRegController")
public class SubcatRegController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubcatRegController() {
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
    	if(flag!=null && flag.equals("search"))
		{
			search(request, response);
			
		}
		else 	if(flag!=null && flag.equals("edit")){
			edit(request, response);
		}
		else if(flag.equals("delete")&&flag!=null)
		{
			delete(request, response);
		}
		else{
			
			load(request, response);
			}
	
	}			

		
		
		
			
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String id2=(String)request.getParameter("id");
		Subcat ct2= new Subcat();
		ct2.setSubcatid(Long.valueOf(id2));
		Cat r=new Cat();
		
		Add_cat c =new Add_cat();
		
			List lm=new ArrayList();
			System.out.println("hiii,helo");
		    lm=c.load(r);
		
		  HttpSession session1=request.getSession();
		session1.setAttribute("lssearch",lm);
		
		Add_subcat ac2= new Add_subcat();

			
			List ls=ac2.edit(ct2);
			HttpSession session=request.getSession();
			session.setAttribute("ls2",ls);
			response.sendRedirect("EH_Admin/subcat_update.jsp");
			
		
		}
	

	

	protected void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String id2=(String)request.getParameter("id");
		Subcat ct2= new Subcat();
		ct2.setSubcatid(Long.valueOf(id2));
		Add_subcat bv= new Add_subcat();
		bv.delete(ct2);
		search(request, response);
	}

	/*	}
		else{System.out.println("Error");}
		
			}

*/
	protected void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub

		String id2=(String)request.getParameter("id");
		
		Subcat st= new
				Subcat();
		//ct.setId(Long.valueOf(id2));
		
		
		Add_subcat sc1= new Add_subcat();

		List ls= sc1.search(st);
		HttpSession session=request.getSession();
		session.setAttribute("ls1",ls);
		System.out.println("hi");
		response.sendRedirect("EH_Admin/search_subcat.jsp");

		
		
		
		
	}





	
	protected void load(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
	
		Cat r=new Cat();
		
		Add_cat c =new Add_cat();
		
			List lm=new ArrayList();
			System.out.println("hiii,helo");
		    lm=c.load(r);
		
		  HttpSession session=request.getSession();
		session.setAttribute("lssearch",lm);
		response.sendRedirect("EH_Admin/add_subcat.jsp"); 

	}

	/**
	 * @return 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag=request.getParameter("flag");
		
		if(flag.equals("insert")&&flag!=null)
		{
			insert(request, response);
			
		}
		else if(flag.equals("update")&&flag!=null)
		{
			update(request, response);
		}
		
		else{ System.out.println("Error");}
				
	}

	protected void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
			String s1= request.getParameter("req1");
			String s2= request.getParameter("req2");
			String catId=request.getParameter("catId");
			String id2=(String)request.getParameter("subid");
			
			System.out.println(id2);
			System.out.println(catId);
			
			Cat c5= new Cat();
			c5.setId(Long.valueOf(catId));
			
			Subcat ct4= new Subcat();
			ct4.setSubcatName(s1);
			ct4.setSubcatDes(s2);
			ct4.setSubcatid(Long.valueOf(id2));
			ct4.setId(c5);
			
			
			Add_subcat ac4= new Add_subcat();
			
			ac4.update(ct4);
			System.out.println("hey");
			search(request, response);
			

		}

	

	protected void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String s1= request.getParameter("req1");
		String s2= request.getParameter("req2");
		
		String id2= request.getParameter("category");
		Cat r1=new Cat();
		r1.setId(Long.valueOf(id2));
		
	
		Subcat r=new Subcat();
		
		r.setSubcatName(s1);
		r.setSubcatDes(s2);
		r.setId(r1);
			
		
		Add_subcat as= new Add_subcat();
		as.insert(r);
		response.sendRedirect("EH_Admin/add_subcat.jsp");
		
	}

}
