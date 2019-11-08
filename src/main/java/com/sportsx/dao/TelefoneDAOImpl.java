package com.sportsx.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.sportsx.model.Telefone;

@Repository
public class TelefoneDAOImpl implements TelefoneDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void insert(Telefone telefone) {
		em.persist(telefone);
	}

	@Override
	public List<Telefone> findAll() {
		return em.createQuery("select t from Telefone t", Telefone.class).getResultList();
	}
	
	@Override
	public List<Telefone> findByClienteID(long id) {
		return em.createQuery("select t from Telefone t where t.cliente.id = :id", Telefone.class)
				.setParameter("id", id)
				.getResultList();
	}

	@Override
	public Telefone findByID(long id) {
		return em.find(Telefone.class, id);
	}

	@Override
	public void update(Telefone telefone) {
		em.merge(telefone);
	}

	@Override
	public void delete(long id) {
		em.remove(em.getReference(Telefone.class, id));
	}

	public void deleteByClienteId(long id) {
		List<Telefone> listaTel = findByClienteID(id);
		for (Telefone telefone : listaTel) {
			em.remove(em.getReference(Telefone.class, telefone.getId()));
		}
		
	}

}
