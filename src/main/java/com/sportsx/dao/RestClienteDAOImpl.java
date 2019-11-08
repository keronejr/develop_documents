package com.sportsx.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.sportsx.model.Cliente;
import com.sportsx.model.RestCliente;

@Repository
public class RestClienteDAOImpl implements RestClienteDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public RestCliente insert(RestCliente cliente) {
		em.persist(cliente);
		return cliente;
	}

	@Override
	public List<RestCliente> findAll() {
		List<RestCliente> clientes = em.createQuery("select c from RestCliente c", RestCliente.class).getResultList();
		return clientes;
	}

	@Override
	public RestCliente findByID(long id) {
		return em.find(RestCliente.class, id);
	}

	@Override
	public void update(RestCliente cliente) {
		em.merge(cliente);
	}

	@Override
	public void delete(long id) {
		em.remove(em.getReference(RestCliente.class, id));
	}

	@Override
	public List<RestCliente> findByType(String tipo) {
		if ("ALL".equals(tipo)) return findAll();
		return em.createQuery("select c from Cliente c where c.tipo = :tipo", RestCliente.class)
				.setParameter("tipo", tipo)
				.getResultList();
	}
	
	@Override
	public List<RestCliente> findByTypeAndName(String tipo, String nome) {
		List<RestCliente> clientes = new ArrayList<RestCliente>();
		if ("ALL".equals(tipo)) {
			clientes = em.createQuery("select c from Cliente c where UPPER(c.nome) like UPPER(:nome)", RestCliente.class)
				.setParameter("nome", nome+"%")
				.getResultList();
		} else {
			clientes = em.createQuery("select c from Cliente c where c.tipo = :tipo and UPPER(c.nome) like UPPER(:nome)", RestCliente.class)
					.setParameter("tipo", tipo)
					.setParameter("nome", nome+"%")
					.getResultList();
		}
		
		return clientes;
	}

}
