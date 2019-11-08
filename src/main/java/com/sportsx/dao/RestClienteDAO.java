package com.sportsx.dao;

import java.util.List;

import com.sportsx.model.Cliente;
import com.sportsx.model.RestCliente;

public interface RestClienteDAO {

	RestCliente insert(RestCliente cliente);
	List<RestCliente> findAll();
	List<RestCliente> findByType(String tipo);
	List<RestCliente> findByTypeAndName(String tipo, String nome);
	RestCliente findByID(long id);
	void update(RestCliente cliente);
	void delete(long id);
	
}
