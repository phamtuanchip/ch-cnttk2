package org.nhom8.csdl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.nhom8.dttn.Sach;
import org.nhom8.tienich.Persistent;
import org.springframework.stereotype.Component;


//@Transactional
public class BookDaoImpl extends Persistent<Sach> {
//	@PersistenceContext
//	protected EntityManager em;
	public Long save(Sach s) {
		em.persist(s);
		return s.getId();
	}
	
	public List<Sach>getAll() {
		return em.createQuery("SELECT p FROM Sach p", Sach.class).getResultList();
	}

	@Override
	public Sach read(Sach o) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Sach> cq = cb.createQuery(Sach.class);
		
		  Root<Sach> c = cq.from(Sach.class);
		  cq.select(c);
		  Expression<Long> id =   c.get("id");
		  
		  ParameterExpression<Long> p = cb.parameter(Long.class);
		 
		  Predicate eq1 = cb.equal(id, p);
		 
		  cq.where(eq1);
		 
		  
		return em.createQuery(cq).getSingleResult();
		
	}

	@Override
	public Long update(Sach o) {
		em.merge(o);
		return o.getId();
	}

	@Override
	public List<Sach> findAll(Sach o) {
		// TODO Auto-generated method stub
		return null;
	}

	public Sach read(Long id){
		return em.createQuery("SELECT p FROM Sach p where p.id = " + id, Sach.class).getSingleResult();
	}

}
