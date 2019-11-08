package com.sportsx.dao;

import java.util.List;

import com.sportsx.model.Cliente;

public interface ClienteDAO {

	Cliente insert(Cliente cliente);
	List<Cliente> findAll();
	List<Cliente> findByType(String tipo);
	List<Cliente> findByTypeAndName(String tipo, String nome);
	Cliente findByID(long id);
	void update(Cliente cliente);
	void delete(long id);
	
}
