package org.nhom8.tienich;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.annotations.Synchronize;
import org.nhom8.dttn.Sach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class Persistent<T> {
	@PersistenceContext
	protected EntityManager em;
	public abstract T read(T o);
	public abstract T read(Long id);
	public abstract Long update(T o);
	public abstract Long save(T o);
	public abstract List<T> getAll();
	public abstract List<T> findAll(T o);
	public Long countAll(String o){
			return (Long)em.createQuery("SELECT sum (num) FROM " + o).getSingleResult();
	}
	
	
}
