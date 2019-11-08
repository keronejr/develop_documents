package com.sportsx.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.sportsx.model.Cliente;

@Repository
public class ClienteDAOImpl implements ClienteDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Cliente insert(Cliente cliente) {
		em.persist(cliente);
		return cliente;
	}

	@Override
	public List<Cliente> findAll() {
		List<Cliente> clientes = em.createQuery("select c from Cliente c", Cliente.class).getResultList();
		return clientes;
	}

	@Override
	public Cliente findByID(long id) {
		return em.find(Cliente.class, id);
	}

	@Override
	public void update(Cliente cliente) {
		em.merge(cliente);
	}

	@Override
	public void delete(long id) {
		em.remove(em.getReference(Cliente.class, id));
	}

	@Override
	public List<Cliente> findByType(String tipo) {
		if ("ALL".equals(tipo)) return findAll();
		return em.createQuery("select c from Cliente c where c.tipo = :tipo", Cliente.class)
				.setParameter("tipo", tipo)
				.getResultList();
	}
	
	@Override
	public List<Cliente> findByTypeAndName(String tipo, String nome) {
		List<Cliente> clientes = new ArrayList<Cliente>();
		if ("ALL".equals(tipo)) {
			clientes = em.createQuery("select c from Cliente c where UPPER(c.nome) like UPPER(:nome)", Cliente.class)
				.setParameter("nome", nome+"%")
				.getResultList();
		} else {
			clientes = em.createQuery("select c from Cliente c where c.tipo = :tipo and UPPER(c.nome) like UPPER(:nome)", Cliente.class)
					.setParameter("tipo", tipo)
					.setParameter("nome", nome+"%")
					.getResultList();
		}
		
		return clientes;
	}

}
