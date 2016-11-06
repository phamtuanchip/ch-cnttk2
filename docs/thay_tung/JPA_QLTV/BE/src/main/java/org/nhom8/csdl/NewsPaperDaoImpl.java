package org.nhom8.csdl;

import java.util.List;

import org.nhom8.dttn.TapChi;
import org.nhom8.tienich.Persistent;

//@Transactional
public class NewsPaperDaoImpl extends Persistent<TapChi> {
//	@PersistenceContext
//	protected EntityManager em;
	public Long save(TapChi s) {
		em.persist(s);
		return s.getId();
	}
	
	public List<TapChi>getAll() {
		return em.createQuery("SELECT p FROM TapChi p", TapChi.class).getResultList();
	}

	@Override
	public TapChi read(Long id) {
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
		return em.createQuery("SELECT p FROM TapChi p where p.id = " + id, TapChi.class).getSingleResult();
		
	}

	@Override
	public Long update(TapChi o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TapChi> findAll(TapChi o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TapChi read(TapChi o) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	
}
