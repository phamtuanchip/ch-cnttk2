package org.nhom8.csdl;

import java.util.List;

import org.nhom8.dttn.DauMuc;
import org.nhom8.dttn.PhieuDatMuon;
import org.nhom8.dttn.SystemLog;
import org.nhom8.tienich.Persistent;

//@Transactional
public class ReserveDaoImpl extends Persistent<PhieuDatMuon> {
//	@PersistenceContext
//	protected EntityManager em;
	@Override
	public Long save(PhieuDatMuon s) {
		 
		for(DauMuc d : s.getItems()) {
			s.setItemId(d.getId());
			em.persist(s);
 		d.setNum(d.getNum()-1);
 		em.merge(d);
//			if(d instanceof BaoNgay) {
//				BaoNgay b = (BaoNgay)d;
//				em.persist(b);
//			}
//				
			
//			if(d instanceof Sach)
//				em.persist((Sach)d);
//				if(d instanceof TapChi)
//					em.persist((TapChi)d);
			
		}
		SystemLog sl = new SystemLog("Muon", s.getReader().getName()+";card:"+ s.getReader().getCardNum());
		//em.persist(sl);
		return s.getId();
	}
	
	public List<PhieuDatMuon>getAll() {
		return em.createQuery("SELECT p FROM PhieuDatMuon p", PhieuDatMuon.class).getResultList();
	}

	@Override
	public PhieuDatMuon read(Long id) {
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
		return em.createQuery("SELECT p FROM PhieuDatMuon p where p.id = " + id, PhieuDatMuon.class).getSingleResult();
		
	}

	@Override
	public Long update(PhieuDatMuon o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PhieuDatMuon read(PhieuDatMuon o) {
		// TODO Auto-generated method stub
		return em.createQuery("SELECT p FROM PhieuDatMuon p where p.resevId = " + o.getResevId(), PhieuDatMuon.class).getSingleResult();
	}


	@Override
	public List<PhieuDatMuon> findAll(PhieuDatMuon o) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
