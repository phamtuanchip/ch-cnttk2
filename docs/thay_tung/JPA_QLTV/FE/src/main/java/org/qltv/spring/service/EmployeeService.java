package org.qltv.spring.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.nhom8.dttn.Employee;
import org.nhom8.tienich.Persistent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


//@Component
//public class EmployeeService {
//	@Autowired
//	private SessionFactory sessionFactory;
//
//	public SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}
//
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
//	
//	@Transactional
//	public void register(Employee emp){
//		// Acquire session
//		Session session = sessionFactory.getCurrentSession();
//		//session.persist(emp);
//		// Save employee, saving behavior get done in a transactional manner
//		session.save(emp);		
//	}

	@Component 
	public class EmployeeService extends Persistent {

		@Override
		public Object read(Object o) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object read(Long id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Long update(Object o) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Long save(Object o) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List getAll() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List findAll(Object o) {
			// TODO Auto-generated method stub
			return null;
		}
//		@Autowired
//		private SessionFactory sessionFactory;
//
//		public SessionFactory getSessionFactory() {
//			return sessionFactory;
//		}
//
//		public void setSessionFactory(SessionFactory sessionFactory) {
//			this.sessionFactory = sessionFactory;
//		}
//		
		public void register(Employee emp){
			// Acquire session
			//Session session = sessionFactory.getCurrentSession();
			em.persist(emp);
			// Save employee, saving behavior get done in a transactional manner
			//session.save(emp);		
		}

}
