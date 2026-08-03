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
import DAO.Add_count;
import VO.City;
import VO.Country;
import VO.Zip;
import DAO.Add_zip;

/**
 * Servlet implementation class ZipRegController
 */
@WebServlet("/ZipRegController")
public class ZipRegController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZipRegController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		load(request,response);
		
		
	}

	protected void load(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		Country r=new Country();
		City r1= new City();
		Add_count c =new Add_count();
		Add_city c1= new Add_city();
		List lm=new ArrayList();
		System.out.println("hiii,helo");
	    lm=c.load(r);
	    List ln=new ArrayList();
	    ln=c1.load(r1);
	  HttpSession session=request.getSession();
	session.setAttribute("lssearch",lm);
	session.setAttribute("ls1search", ln);
	response.sendRedirect("EH_Admin/manage_zip.jsp"); 

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
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
		
		String id6= request.getParameter("zipid");
		//Cat r1=new Cat();
		//r1.setId(Long.valueOf(id2));
		
	
		Zip r=new Zip();
		
		r.setZip(Long.valueOf(s1));
		
		Add_zip zp= new Add_zip();
		zp.insert(r);
		response.sendRedirect("EH_Admin/manage_zip.jsp");

		

	
	
	
	}

}
