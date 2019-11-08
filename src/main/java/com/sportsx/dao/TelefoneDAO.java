package com.sportsx.dao;

import java.util.List;

import com.sportsx.model.Telefone;

public interface TelefoneDAO {

	void insert(Telefone telefone);
	List<Telefone> findAll();
	List<Telefone> findByClienteID(long id);
	Telefone findByID(long id);
	void update(Telefone telefone);
	void delete(long id);
	void deleteByClienteId(long id);
	
}
