package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import VO.Cat;
import VO.Exam;

public class Add_exam {

	
	
	public void insert(Exam ex){
			
			Session session = null;

			try {
				SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
				session = sessionFactory.openSession();
				System.out.println("Inserting Record");
				
				Transaction y=session.beginTransaction();
				// session.saveOrupdate();
				session.save(ex);
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
	
public List load(Exam ex1){
		
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
		 Query z=session.createQuery("from Exam");
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


	public List search(Exam qf1) {
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

	public List edit(Exam qf2) {
	List ls2=null;
		
		Session session = null;

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			System.out.println("fetching Record");
			
			Transaction y=session.beginTransaction();
			Query q= session.createQuery("from Qualification where id="+qf2.getExam_id());
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

	public void update(Exam qf3) {
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

	public boolean delete(Exam qf4) {
		Session session = null;

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			System.out.println("deleting Record");
			
			Transaction y=session.beginTransaction();
			Exam vo=(Exam)session.get(Exam.class, qf4.getExam_id());   
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
