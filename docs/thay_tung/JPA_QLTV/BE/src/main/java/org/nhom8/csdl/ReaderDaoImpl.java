package org.nhom8.csdl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Parameter;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.Restrictions;
import org.nhom8.dttn.NguoiMuon;
import org.nhom8.dttn.Sach;
import org.nhom8.tienich.Persistent;

//@Transactional
public class ReaderDaoImpl extends Persistent<NguoiMuon> {
//	@PersistenceContext
//	protected EntityManager em;
	public Long save(NguoiMuon s) {
		em.persist(s);
		return s.getId();
	}
	
	public List<NguoiMuon>getAll() {
		return em.createQuery("SELECT p FROM NguoiMuon p", NguoiMuon.class).getResultList();
	}

	@Override
	public NguoiMuon read(Long id) {
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//
//		CriteriaQuery<Sach> cq = cb.createQuery(Sach.class);
//		
//		  Root<Sach> c = cq.from(Sach.class);
//		  cq.select(c);
//		  Expression<Long> id = c.get("id");
//		  
//		  ParameterExpression<Long> p = cb.parameter(Long.class);
//		  Predicate eq1 = cb.equal(id, p);
//		  cq.where(eq1);
		return em.createQuery("SELECT p FROM NguoiMuon p where p.id = " + id, NguoiMuon.class).getSingleResult();
		
	}

	@Override
	public Long update(NguoiMuon o) {
		em.merge(o);
		return o.getId();
	}

	@Override
	public NguoiMuon read(NguoiMuon o) {
		 
		  
//		if(o.getName() != null)
//		// TODO Auto-generated method stub
//		return em.createQuery("SELECT p FROM NguoiMuon p where p.name = '" + o.getName()+"'", NguoiMuon.class).getSingleResult();
//		if(o.getCardNum() != null)
//			return em.createQuery("SELECT p FROM NguoiMuon p where p.cardNum = '" + o.getCardNum()+"'", NguoiMuon.class).getSingleResult();
		return null;
		  
	}

	 
	@Override
	public List<NguoiMuon> findAll(NguoiMuon o) {
		if(o.getName() != null)
			// TODO Auto-generated method stub
			return em.createQuery("SELECT p FROM NguoiMuon p where p.name like '%" + o.getName()+"%'", NguoiMuon.class).getResultList();
			if(o.getCardNum() != null)
				return em.createQuery("SELECT p FROM NguoiMuon p where p.cardNum like '%" + o.getCardNum()+"%'", NguoiMuon.class).getResultList();
			return null;
 
	}

	 
	
}
