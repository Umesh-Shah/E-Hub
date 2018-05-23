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

import VO.City;
import DAO.Add_count;
import DAO.Add_city;
import VO.Country;

/**
 * Servlet implementation class CityRegController
 */
@WebServlet("/CityRegController")
public class CityRegController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CityRegController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		load(request, response);
		
		
		
	}

	protected void load(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
			Country r=new Country();
			
			Add_count c =new Add_count();
		
			List lm=new ArrayList();
			System.out.println("hiii,helo");
		    lm=c.load(r);
		
		  HttpSession session=request.getSession();
		session.setAttribute("lssearch",lm);
		response.sendRedirect("EH_Admin/manage_city.jsp"); 

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag= request.getParameter("flag");
		
		// TODO Auto-generated method stub
		if(flag.equals("insert")&&flag!=null)
		{
			insert(request, response);
			
		}
		else{ System.out.println("Error");}
				
	}

	protected void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String s1= request.getParameter("req1");
		
		String id2= request.getParameter("country");
		Country r1=new Country();
		r1.setCountid(Long.valueOf(id2));
	
		City r=new City();
		
		r.setCityName(s1);
		r.setCountid(r1);
		
			
		
		Add_city cu= new Add_city();
		cu.insert(r);
		response.sendRedirect("EH_Admin/manage_city.jsp");

		
	
	}
	
	
	
	
	
	
	
	}

