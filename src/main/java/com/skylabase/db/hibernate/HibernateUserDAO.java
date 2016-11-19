package com.skylabase.db.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.skylabase.db.UserDAO;
import com.skylabase.model.User;

@Repository
class HibernateUserDAO implements UserDAO {

	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	@Override
	public User getUser(Long userId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		User user = session.get(User.class, userId);
		session.getTransaction().commit();
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<User> users = session.createCriteria(User.class).list();
		session.getTransaction().commit();
		return users;
	}

	@Override
	public User saveUser(User user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(user);
		session.getTransaction().commit();
		return user;
	}

	@Override
	public void deleteUser(User user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(user);
		session.getTransaction().commit();
	}
}
