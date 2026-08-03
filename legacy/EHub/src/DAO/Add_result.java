package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



import VO.Result;

public class Add_result {
public void insert(Result qf){
		
		Session session = null;

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			System.out.println("Inserting Record");
			
			Transaction y=session.beginTransaction();
			// session.saveOrupdate();
			session.save(qf);
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

public List load(Result cy){
	
	Session session = null;
	List lm=null;
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
	  Query z= session.createQuery("from Result");
	  lm=z.list();
	  
	
	  
	  System.out.println("Done");
	  }catch(Exception e){
	  e.printStackTrace();
	  }
	  
	  finally{
	  // Actual contact insertion will happen at this step
		  session.flush();
		  session.close();
	
	  
	  }return lm;
	}


}
