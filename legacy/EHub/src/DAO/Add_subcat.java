package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

//import com.espertech.esper.client.Configuration;


import VO.Subcat;
import VO.Cat;

public class Add_subcat {
	
public List load(Subcat sc){
		
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
		  Query z= session.createQuery("from Cat");
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
public List edit(Subcat sc3){
	
	List ls2=null;
	
	Session session = null;

	try {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		System.out.println("fetching Record");
		
		Transaction y=session.beginTransaction();
		Query q= session.createQuery("from Subcat where id="+sc3.getSubcatid());
		ls2= q.list();
		
		y.commit();

		System.out.println("Done");
	} catch (Exception e) {
		System.out.println(e.getMessage());
	} finally {
		// Actual contact insertion will happen at this step
		session.flush();
		session.close();
		
	
	}
return ls2;
}
public void update(Subcat sc4){
	
	Session session = null;

	try {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		System.out.println("Upadting Record");
		
		Transaction y=session.beginTransaction();
		
		session.update(sc4);
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

public void delete(Subcat ct3){
	
	Session session = null;

	try {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		System.out.println("deleting Record");
		
		Transaction y=session.beginTransaction();
		Query q= session.createQuery("delete from Subcat where id="+ct3.getSubcatid());
		q.executeUpdate();
		//session.save(ct3);
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

public List search (Subcat sc1){
	
	Session session = null;
	List lm1=null;
	  try{
		  SessionFactory sessionFactory = new 

	Configuration().configure().buildSessionFactory();
	session =sessionFactory.openSession();
		 System.out.println("Finding");
	  Query z= session.createQuery("from Subcat");
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



public void insert(Subcat sb){
	
	Session session = null;

	try {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		System.out.println("Inserting Record");
		
		Transaction y=session.beginTransaction();
		// session.saveOrupdate();
		session.save(sb);
		y.commit();

		System.out.println("Done");
	} catch (Exception e) {
	e.printStackTrace();
	} finally {
		// Actual contact insertion will happen at this step
		session.flush();
		session.close();
		
	
	}

}


}