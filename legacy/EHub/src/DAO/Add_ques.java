package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import VO.Ques;

public class Add_ques {

	
	
	public void insert(Ques ex){
			
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
	
public List load(Ques ex1){
		
	
	
		Session session = null;
		List lm1=null;
		  try{
		  SessionFactory sessionFactory = new 

		Configuration().configure().buildSessionFactory();// cgf file read. and create obj
		 
		 session =sessionFactory.openSession();
		 System.out.println("insude course dao before querry");
		 
		 
		 Query z=session.createQuery("from Ques as w where w.exam_id.exam_id="+ex1.getExam_id().getExam_id());
		  
		 
		 lm1=z.list();
		  
		  System.out.println("inside cat dao after querry");
		  
		  System.out.println("Done = " + lm1.size());
		  }catch(Exception e){
		  e.printStackTrace();
		  }
		  
		  finally{
		  // Actual contact insertion will happen at this step
			   
		  session.flush();
		  session.close();
		  
		  }return lm1;
		}


public List checkAns(long quesid){
	
	
	
	Session session = null;
	List lm1=null;
	  try{
	  SessionFactory sessionFactory = new 

	Configuration().configure().buildSessionFactory();// cgf file read. and create obj
	 
	 session =sessionFactory.openSession();
	 System.out.println("insude course dao before querry");
	 
	 
	 Query z=session.createQuery("from Ques as w where w.quesid="+quesid);
	  
	 
	 lm1=z.list();
	  
	  System.out.println("inside cat dao after querry");
	  
	  System.out.println("Done = " + lm1.size());
	  }catch(Exception e){
	  e.printStackTrace();
	  }
	  
	  finally{
	  // Actual contact insertion will happen at this step
		   
	  session.flush();
	  session.close();
	  
	  }return lm1;
	}


	public List search(Ques qf1) {
		// TODO Auto-generated method stub
		List ls= null;
		Session session = null;

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			System.out.println("Fetching");
			Transaction y=session.beginTransaction();
			Query q= session.createQuery("from Ques");
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

	public List edit(Ques qf2) {
	List ls2=null;
		
		Session session = null;

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			System.out.println("fetching Record");
			
			Transaction y=session.beginTransaction();
			Query q= session.createQuery("from Ques where id="+qf2.getQuesid());
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

	public void update(Ques qf3) {
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

	public boolean delete(Ques qf4) {
		Session session = null;

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			System.out.println("deleting Record");
			
			Transaction y=session.beginTransaction();
			Ques vo=(Ques)session.get(Ques.class, qf4.getQuesid());   
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
