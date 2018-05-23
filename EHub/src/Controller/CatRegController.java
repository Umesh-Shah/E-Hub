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
import DAO.Add_subcat;
import VO.Cat;
import VO.Subcat;

/**
 * Servlet implementation class CatRegController
 */
@WebServlet("/CatRegController")
public class CatRegController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatRegController() {
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
    	protected void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
    		String id2=(String)request.getParameter("id");
    		Cat ct2= new Cat();
    		ct2.setId(Long.valueOf(id2));
    		
    		
    		Add_cat ac2= new Add_cat();

    			
    			List ls=ac2.edit(ct2);
    			HttpSession session=request.getSession();
    			session.setAttribute("ls2",ls);
    			response.sendRedirect("EH_Admin/cat_update.jsp");
    			
    		
    		}
    	protected void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
    		// TODO Auto-generated method stub
    		
    		try {
    			
    			String id2=(String)request.getParameter("id");
    			Cat ct2= new Cat();
        		ct2.setId(Long.valueOf(id2));
        		Add_cat bv= new Add_cat();
        		
    			
    			
    			HttpSession session= request.getSession();
    			
    			
    			if(!bv.delete(ct2))
    			{
    				session.setAttribute("deleteflag", "false");
    			}
    				
    			search(request, response);
    		
    		
    		} catch (Exception e) {
    			// TODO: handle exception
    		}
    	}
    		
    		
    	

		
	
		protected void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
  	// TODO Auto-generated method stub

    		
    		String id2=(String)request.getParameter("id");
    		
    		Cat ct= new Cat();
    		//ct.setId(Long.valueOf(id2));
    		
    		
    		Add_cat ac1= new Add_cat();

    		List ls= ac1.search(ct);
    		HttpSession session=request.getSession();
    		session.setAttribute("ls1",ls);
    		System.out.println("hi");
    		response.sendRedirect("EH_Admin/search_cat.jsp");
    		
    	
    	
    
    	
    	
	}
		
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

		String s1= request.getParameter("req1");
		String s2= request.getParameter("req2");
		
		String id2=(String)request.getParameter("id");
		
		
		Cat ct4= new Cat();
		ct4.setCatName(s1);
		ct4.setCatDes(s2);
		ct4.setId(Long.valueOf(id2));
		
		Add_cat ac4= new Add_cat();
		
		ac4.update(ct4);
		System.out.println("hey");
		search(request, response);

	}

		
	

	protected void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String s1= request.getParameter("req1");
		String s2= request.getParameter("req2");
		
		String id2=(String)request.getParameter("id");
		
		
		Cat ct= new Cat();
		ct.setCatName(s1);
		ct.setCatDes(s2);
		//ct.setId(Long.valueOf(id2));
		
		Add_cat ac= new Add_cat();
		ac.insert(ct);
		response.sendRedirect("EH_Admin/add_category.jsp");

	}

	}

