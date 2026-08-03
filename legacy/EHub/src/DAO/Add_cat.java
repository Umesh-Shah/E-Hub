package DAO;

import java.util.List;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import VO.Cat;


import VO.Subcat;

public class Add_cat {

	public void insert(Cat ca){
		
		Session session = null;

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			System.out.println("Inserting Record");
			
			Transaction y=session.beginTransaction();
			// session.saveOrupdate();
			session.save(ca);
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
public void update(Cat cv){
		
		Session session = null;

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			System.out.println("Upadting Record");
			
			Transaction y=session.beginTransaction();
			
			session.update(cv);
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

	
public boolean delete(Cat ct3){
	
	Session session = null;

	try {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		System.out.println("deleting Record");
		
		Transaction y=session.beginTransaction();
		Cat vo=(Cat)session.get(Cat.class, ct3.getId());   
		session.delete(vo);
		//session.save(ct3);
		y.commit();

		System.out.println("Done");
	} 
	catch(Exception e)
	{
		
		String []s =e.getCause()!=null?e.getCause().toString().split(":"):null;
		
		if(s!=null && s[0].equals("java.sql.BatchUpdateException"))
		{
			return false;
			//System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhh");
		}
	}
	
	finally
	{
		//session.close();
	}
	return true;


}
	


public List load(Cat c2){
		
		Session session = null;
		List lm1=null;
		  try{
		  // This step will read hibernate.cfg.xml 

		//and prepare hibernate for use
		  SessionFactory sessionFactory = new 

		Configuration().configure().buildSessionFactory();// cgf file read. and create obj
		 
		 session =sessionFactory.openSession();
		 
		 //Transaction y = session.beginTransaction();// for more then trans.
		 
		  //Create new instance of Contact and set 

		//values in it by reading them from form object
		 
		  
		 System.out.println("insude course dao before querry");
		 Query z=session.createQuery("from Cat");
		  lm1=z.list();
		  
		  System.out.println("inside cat dao after querry");
		  
		  System.out.println("Done");
		  }catch(Exception e){
		  e.printStackTrace();
		  }
		  
		  finally{
		  // Actual contact insertion will happen at this step
			   
		  session.flush();
		  session.close();
		  
		  }return lm1;
		}
	
public List search(Cat ct1)
{

	List ls= null;
	Session session = null;

	try {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		System.out.println("Fetching");
		Transaction y=session.beginTransaction();
		Query q= session.createQuery("from Cat");
		ls= q.list();
		//session.save(ct1);
		y.commit();

		System.out.println("Done");
	} catch (Exception e) {
		System.out.println(e.getMessage());
	} finally {
		//session.flush();
		 //session.close();

	
	}
	return ls;
}


public List edit(Cat ct3){
	
	List ls2=null;
	
	Session session = null;

	try {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		System.out.println("fetching Record");
		
		Transaction y=session.beginTransaction();
		Query q= session.createQuery("from Cat where id="+ct3.getId());
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


}


