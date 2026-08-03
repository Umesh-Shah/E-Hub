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
import DAO.Add_subcat;

import DAO.Add_zip;

import VO.Cat;
import VO.City;
import VO.Country;
import VO.Subcat;

import VO.Zip;

/**
 * Servlet implementation class Zip1RegController
 */
@WebServlet("/Zip1RegController")
public class Zip1RegController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Zip1RegController() {
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
		else{load(request, response);}
	
	}			

		
		
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String id2=(String)request.getParameter("id");
		Zip ct2= new Zip();
		ct2.setZipid(Long.valueOf(id2));
		Country r=new Country();
		Add_count c =new Add_count();
		City r1=new City();
		Add_city c1=new Add_city(); 
		
			List lm=new ArrayList();
			System.out.println("hiii,helo");
		    lm=c.load(r);
		    List ln=new ArrayList();
		    ln=c1.load(r1);
		
		  HttpSession session1=request.getSession();
		session1.setAttribute("lssearch",lm);
		session1.setAttribute("ls1search", ln);
		//session1.setAttribute("lssearch", lm);
		Add_zip ac2= new Add_zip();

			
			List ls=ac2.edit(ct2);
			HttpSession session=request.getSession();
			session.setAttribute("ls2",ls);
			response.sendRedirect("EH_Admin/location_update.jsp");
			
		
		}
	


	
	protected void delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	protected void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
String id2=(String)request.getParameter("id");
		
		Zip zp= new Zip();
		//ct.setId(Long.valueOf(id2));
		
		
		Add_zip ct1= new Add_zip();

		List ls= ct1.search(zp);
		HttpSession session=request.getSession();
		session.setAttribute("ls1",ls);
		System.out.println("hi");
		response.sendRedirect("EH_Admin/search_location.jsp");

		
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
		else if(flag.equals("update")&&flag!=null){update(request, response);}
		else{ System.out.println("Error");}
				
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String s1= request.getParameter("req1");
		
		String CountryId=request.getParameter("country");
		String CityId= request.getParameter("city");
		String id2=(String)request.getParameter("zipId");
		
		System.out.println(CountryId);
		System.out.println(CityId);
		System.out.println(id2);
		
		Country c5= new Country();
		c5.setCountid(Long.valueOf(CountryId));
		
		City c6= new City();
		c6.setCityid(Long.valueOf(CityId));
		
		Zip ct4= new Zip();
		ct4.setZip(Long.valueOf(s1));
		ct4.setCityid(c6);
		ct4.setCountid(c5);
		ct4.setZipid(Long.valueOf(id2));
		
		
		
		Add_zip ac4= new Add_zip();
		
		ac4.update(ct4);
		System.out.println("hey");
		search(request, response);
		

	}



	protected void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String s1= request.getParameter("req1");
		
		String id2= request.getParameter("country");
		Country r1=new Country();
		r1.setCountid(Long.valueOf(id2));
		
		String id3= request.getParameter("city");
		City c1=new City();
		c1.setCityid(Long.valueOf(id3));
		
	
		Zip r=new Zip();
		
		r.setZip(Long.valueOf(s1));
		r.setCountid(r1);
		r.setCityid(c1);
		
		Add_zip zp= new Add_zip();
		zp.insert(r);
		response.sendRedirect("EH_Admin/manage_zip.jsp");

		

	
	
	
	}

	
	
	}


