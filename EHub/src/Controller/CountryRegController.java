package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Add_count;
import DAO.Add_subcat;
import VO.Country;
import VO.Subcat;

/**
 * Servlet implementation class CountryRegController
 */
@WebServlet("/CountryRegController")
public class CountryRegController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountryRegController() {
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
		else{ System.out.println("Error");}
				
	}

	protected void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		String s1= request.getParameter("req1");
		
		String id4= request.getParameter("countid");
		//Cat r1=new Cat();
		//r1.setId(Long.valueOf(id2));
		
	
		Country r=new Country();
		
		r.setCountName(s1);
		
		
			
		
		Add_count cu= new Add_count();
		cu.insert(r);
		response.sendRedirect("EH_Admin/manage_count.jsp");

		
	
	}
	
	
	
	
	
	}


