package org.nhom8.csdl;

import java.util.List;

import org.nhom8.dttn.DauMuc;
import org.nhom8.dttn.PhieuDatMuon;
import org.nhom8.dttn.PhieuNhacTra;
import org.nhom8.dttn.SystemLog;
import org.nhom8.tienich.Persistent;

//@Transactional
public class ReturnDaoImpl extends Persistent<PhieuNhacTra> {
//	@PersistenceContext
//	protected EntityManager em;
	@Override
	public Long save(PhieuNhacTra s) {
//		 
//		for(DauMuc d : s.getItems()) {
//			s.setItemId(d.getId());
//			em.persist(s);
// 		d.setNum(d.getNum()+1);
// 		em.merge(d);
////			if(d instanceof BaoNgay) {
////				BaoNgay b = (BaoNgay)d;
////				em.persist(b);
////			}
////				
//			
////			if(d instanceof Sach)
////				em.persist((Sach)d);
////				if(d instanceof TapChi)
////					em.persist((TapChi)d);
//			
//		}
		SystemLog sl = new SystemLog("Tra", s.getNguoiMuon()+";ngay:"+ s.getDate());
		em.persist(s);
		return s.getId();
	}
	
	public List<PhieuNhacTra>getAll() {
		return em.createQuery("SELECT p FROM PhieuNhacTra p", PhieuNhacTra.class).getResultList();
	}

	@Override
	public PhieuNhacTra read(Long id) {
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
		return em.createQuery("SELECT p FROM PhieuNhacTra p where p.id = " + id, PhieuNhacTra.class).getSingleResult();
		
	}

	@Override
	public Long update(PhieuNhacTra o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PhieuNhacTra read(PhieuNhacTra o) {
		// TODO Auto-generated method stub
		return em.createQuery("SELECT p FROM PhieuNhacTra p where p.nguoiMuon = " + o.getNguoiMuon() +" and p.taiLieu = " + o.getTaiLieu(), PhieuNhacTra.class).getSingleResult();
	}


	@Override
	public List<PhieuNhacTra> findAll(PhieuNhacTra o) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
