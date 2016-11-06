package org.nhom8.csdl;

import java.util.List;

import org.nhom8.dttn.DauMuc;
import org.nhom8.dttn.PhieuDatMuon;
import org.nhom8.dttn.PhieuNhacTra;
import org.nhom8.dttn.SystemLog;
import org.nhom8.tienich.Persistent;

public class SystemDaoImpl extends Persistent<SystemLog> {

	@Override
	public SystemLog read(SystemLog o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemLog read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long update(SystemLog o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long save(SystemLog o) {
		 em.persist(o);
		return o.getId();
	}

	@Override
	public List<SystemLog> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SystemLog> findAll(SystemLog o) {
		// TODO Auto-generated method stub
		return null;
	}
 
	
	
}
