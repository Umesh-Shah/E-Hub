package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import VO.Cat;
import VO.Qualification;;

public class Add_qualification {

public void insert(Qualification qf){
		
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

public List search(Qualification qf1) {
	// TODO Auto-generated method stub
	List ls= null;
	Session session = null;

	try {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		System.out.println("Fetching");
		Transaction y=session.beginTransaction();
		Query q= session.createQuery("from Qualification");
		ls= q.list();
		
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

public List edit(Qualification qf2) {
List ls2=null;
	
	Session session = null;

	try {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		System.out.println("fetching Record");
		
		Transaction y=session.beginTransaction();
		Query q= session.createQuery("from Qualification where id="+qf2.getQuali_id());
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

public void update(Qualification qf3) {
	// TODO Auto-generated method stub
	
	Session session = null;

	try {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		System.out.println("Upadting Record");
		
		Transaction y=session.beginTransaction();
		
		session.update(qf3);
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

public boolean delete(Qualification qf4) {
	Session session = null;

	try {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		System.out.println("deleting Record");
		
		Transaction y=session.beginTransaction();
		Qualification vo=(Qualification)session.get(Qualification.class, qf4.getQuali_id());   
		session.delete(vo);
		
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

}


