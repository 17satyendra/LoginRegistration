package com.user.dto;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.user.model.UserReg;

/**
 * @author bridgeit satyendra singh
 * This class basically gives dao utility 
 * In this class having two method one is saveUser to save the data for user registration page.
 * and another is validation to check username and password to be enter in login page.
 */
public class UserRegDao {
	public UserRegDao() {
		System.out.println(this.getClass().getSimpleName() + "Created...");
	}
	/**
	 * @param reg UserReg class reference.
	 * In this method used to save the data of particular UserReg object 
	 * Through Registration form page.
	 * Using {@link Session} and {@Transaction}
	 */
	public void saveUser(UserReg reg) {
		System.out.println("Saving Data...");
		Configuration cf = new Configuration();
		cf.configure().addAnnotatedClass(UserReg.class);
		cf.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		Session session = cf.buildSessionFactory().openSession();

		try {

			session.save(reg);
			Transaction tx = session.beginTransaction();
			tx.commit();
		} catch (HibernateException he) {
			he.printStackTrace();

		} finally {
			session.close();
		}

	}
	/**
	 * @param username User name from Login page.
	 * @param password Password from Login page. 
	 * In this method we gives the two String user name  and password from login pages to validate.
	 * Using {@link Criteria} with {@link Restrictions}
	 * @return UserReg object based on user name and password.
	 */
	public UserReg validation(String username, String password) {
		
		Configuration cf = new Configuration();
		cf.configure().addAnnotatedClass(UserReg.class);
		
		Session session =cf.buildSessionFactory().openSession();
		
		try {
			Criteria cr = session.createCriteria(UserReg.class);

			UserReg user = (UserReg) cr.add(Restrictions.conjunction().add(Restrictions.eq("UserName", username))
					.add(Restrictions.eq("password", password))).uniqueResult();
			return user;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
}
