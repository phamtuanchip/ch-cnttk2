package org.nhom8.csdl;

import java.util.List;

import org.nhom8.dttn.DauMuc;
import org.nhom8.dttn.PhieuDatMua;
import org.nhom8.dttn.Sach;
import org.nhom8.tienich.Persistent;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BuyDaoImpl extends Persistent<PhieuDatMua> {
	//	@PersistenceContext
	//	protected EntityManager em;
	public Long save(PhieuDatMua s) {
		 
		for(DauMuc d : s.getItems()) {
			if(d instanceof Sach)
 		em.persist(d);
 		s.setItemId(d.getId());
		em.persist(s);
		}
		//em.persist(s);
		return s.getId();
	}

	public List<PhieuDatMua>getAll() {
		return em.createQuery("SELECT p FROM PhieuDatMua p", PhieuDatMua.class).getResultList();
	}

	@Override
	public PhieuDatMua read(Long id) {
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
		return em.createQuery("SELECT p FROM PhieuDatMua p where p.id = " + id, PhieuDatMua.class).getSingleResult();

	}

	@Override
	public Long update(PhieuDatMua o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PhieuDatMua read(PhieuDatMua o) {
		// TODO Auto-generated method stub
		return em.createQuery("SELECT p FROM PhieuDatMua p where p.itemId = " + o.getItemId(), PhieuDatMua.class).getSingleResult();
	}


	@Override
	public List<PhieuDatMua> findAll(PhieuDatMua o) {
		// TODO Auto-generated method stub
		return null;
	}




}
