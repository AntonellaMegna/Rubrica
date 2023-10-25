package service;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.Contatti;
import model.HibernateUtil;
 
 


public class Dao {
	
	
public static Contatti selectCon(String email){
		 
	Contatti cor;
		Session session =HibernateUtil.openSession();
		  cor=(Contatti)session.createQuery("FROM Contatti where email ='"+ 
				email+"'" ).uniqueResult();
		
		 		
		session.close();
		return cor;
	}

public static Contatti selectConLike(String last_name, String first_name){
	 
	Contatti con= null;
	Session session =HibernateUtil.openSession();
	if(last_name ==null){
		 
		  con=(Contatti)session.createQuery("FROM Contatti where first_name Like '"+ 
				  first_name+"%'" ).uniqueResult();
		
	}else{
		 con=(Contatti)session.createQuery("FROM Contatti where last_name Like '"+ 
				 last_name+"%'" ).uniqueResult();
	}
	
		session.close();
		return con;
	}
 

public static Contatti selectCon(int cel){
	 
	Contatti con;
		Session session =HibernateUtil.openSession();
		con=(Contatti)session.createQuery("FROM Contatti where cel1 ="+ 
		cel+" ").uniqueResult();
			
		session.close();
		return con;
	}
	
	public static boolean insertCon(Contatti c )
	{
	  boolean ok= true;
		
		Session session =HibernateUtil.openSession();
		Transaction tx = null;

		try {
			
		 
		   tx = session.beginTransaction();
		   
		   session.save(c);
		 		   
		   tx.commit();
		}

		catch (Exception e) {
			ok= true;
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		} finally {
		   session.close();
		}

		return ok;
		
	}
	
public static boolean updateContatti(Contatti cont){
	boolean ok = true;
	Session session = HibernateUtil.openSession();
	Transaction tx = null;

	try {
		tx = session.beginTransaction();
		
		session.update(cont);
		
		tx.commit();
	}

	catch (Exception e) {

		if (tx != null) {
			tx.rollback();
			ok = false;
		}
		e.printStackTrace();
	} finally {
		session.close();

	}
	return ok;
	}
public static List<Contatti> selectConAll(){
	 
	List<Contatti> mov=new ArrayList<Contatti>();
	Session session =HibernateUtil.openSession();
	Transaction tx = null;
	 
	 tx = session.beginTransaction();
	 //subList(0, 10) prende solo i primi 10 record
	// mov= session.createQuery("FROM Contatti " ).list().subList(0, 1);  
	 mov= session.createQuery("FROM Contatti " ).list();
	 session.close();
	 
      return mov;
	}
public static boolean delCont(Contatti c )
{
  boolean ok= true;
	
	Session session =HibernateUtil.openSession();
	Transaction tx = null;

	try {
		
	 
	   tx = session.beginTransaction();
	   
	   session.delete(c);
	 		   
	   tx.commit();
	}

	catch (Exception e) {
		ok= true;
	   if (tx!=null) tx.rollback();
	   e.printStackTrace(); 
	} finally {
	   session.close();
	}

	return ok;
	
}
}