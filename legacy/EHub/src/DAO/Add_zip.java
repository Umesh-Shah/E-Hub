package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import VO.Subcat;
import VO.Zip;

public class Add_zip {

	
	public void insert(Zip zp){
		
		Session session = null;

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			System.out.println("Inserting Record");
			
			Transaction y=session.beginTransaction();
			// session.saveOrupdate();
			session.save(zp);
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
	public List search (Zip zt){
		
		Session session = null;
		List lm1=null;
		  try{
			  SessionFactory sessionFactory = new 

		Configuration().configure().buildSessionFactory();
		session =sessionFactory.openSession();
			 System.out.println("Finding");
		  Query z= session.createQuery("from Zip");
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
	public List edit(Zip ct2) {
		List ls2=null;
		
		Session session = null;

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			System.out.println("fetching Record");
			
			Transaction y=session.beginTransaction();
			Query q= session.createQuery("from Zip where id="+ct2.getZipid());
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
	public void update(Zip sc4){
		
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

	}



	
	

