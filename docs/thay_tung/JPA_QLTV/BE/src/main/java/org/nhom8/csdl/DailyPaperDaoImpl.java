package org.nhom8.csdl;

import java.util.List;

import org.nhom8.dttn.BaoNgay;
import org.nhom8.tienich.Persistent;

//@Transactional
public class DailyPaperDaoImpl extends Persistent<BaoNgay> {
//	@PersistenceContext
//	protected EntityManager em;
	public Long save(BaoNgay s) {
		em.persist(s);
		return s.getId();
	}
	
	public List<BaoNgay>getAll() {
		return em.createQuery("SELECT p FROM BaoNgay p", BaoNgay.class).getResultList();
	}

	@Override
	public BaoNgay read(Long id) {
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
		return em.createQuery("SELECT p FROM BaoNgay p where p.id = " + id, BaoNgay.class).getSingleResult();
		
	}

	@Override
	public Long update(BaoNgay o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BaoNgay> findAll(BaoNgay o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaoNgay read(BaoNgay o) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	
}
