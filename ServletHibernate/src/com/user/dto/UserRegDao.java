package com.user.dto;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.user.model.UserReg;

public class UserRegDao {
	public UserRegDao() {
		System.out.println(this.getClass().getSimpleName() + "Created...");
	}

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
