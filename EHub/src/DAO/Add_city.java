package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import VO.City;
import VO.Subcat;


public class Add_city {
	
public List load(City cy){
		
		Session session = null;
		List ln=null;
		  try{
		  // This step will read hibernate.cfg.xml 

		//and prepare hibernate for use
		  SessionFactory sessionFactory = new 

		Configuration().configure().buildSessionFactory();
		session =sessionFactory.openSession();
		 
		 //Transaction y = session.beginTransaction();// for more then trans.
		 
		  //Create new instance of Contact and set 

		//values in it by reading them from form object
		
	
		 System.out.println("Finding");
		  Query z= session.createQuery("from City");
		  ln=z.list();
		  
		
		  
		  System.out.println("Done");
		  }catch(Exception e){
		  e.printStackTrace();
		  }
		  
		  finally{
		  // Actual contact insertion will happen at this step
			  session.flush();
			  session.close();
		
		  
		  }return ln;
		}

public List search (City ct1){
	
	Session session = null;
	List lm1=null;
	  try{
		  SessionFactory sessionFactory = new 

	Configuration().configure().buildSessionFactory();
	session =sessionFactory.openSession();
		 System.out.println("Finding");
	  Query z= session.createQuery("from City");
	  lm1=z.list();
	  
	
	  
	  System.out.println("Done");
	  }catch(Exception e){
	  e.printStackTrace();
	  }
	  
	  finally{
	  // Actual contact insertion will happen at this step
		//  session.flush();
		  session.close();
	
	  
	  }return lm1;
	}


public void insert(City ct){
	
	Session session = null;

	try {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		System.out.println("Inserting Record");
		
		Transaction y=session.beginTransaction();
		// session.saveOrupdate();
		session.save(ct);
		y.commit();

		System.out.println("Done");
	} catch (Exception e) {
		System.out.println(e.getMessage());
	} finally {
		// Actual contact insertion will happen at this step
		session.flush();
		session.close();
		
	
	}

}

	

}
