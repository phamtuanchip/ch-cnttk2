package org.nhom8.csdl;

import java.util.List;

import org.nhom8.dttn.DauMuc;
import org.nhom8.dttn.Employee;
import org.nhom8.dttn.PhieuDatMuon;
import org.nhom8.dttn.PhieuNhacTra;
import org.nhom8.tienich.Persistent;

public class EmployeeDaoImpl extends Persistent<Employee> {

	@Override
	public Employee read(Employee o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long update(Employee o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long save(Employee o) {
		// TODO Auto-generated method stub
		em.persist(o);
		return o.getId();
	}

	@Override
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findAll(Employee o) {
		// TODO Auto-generated method stub
		return null;
	}
 
	
	
}
