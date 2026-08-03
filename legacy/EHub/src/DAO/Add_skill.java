package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import VO.Cat;
import VO.Skill;

public class Add_skill {

public void insert(Skill sk){
		
		Session session = null;

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			System.out.println("Inserting Record");
			
			Transaction y=session.beginTransaction();
			// session.saveOrupdate();
			session.save(sk);
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

public List search(Skill sk)
{

	List ls= null;
	Session session = null;

	try {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		System.out.println("Fetching");
		Transaction y=session.beginTransaction();
		Query q= session.createQuery("from Skill where id="+sk.getId());
		ls= q.list();
		session.save(sk);
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



	
}

