package com.sportsx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sportsx.dao.TelefoneDAO;
import com.sportsx.model.Telefone;

@Service
@Transactional
public class TelefoneServiceImpl implements TelefoneService{

	@Autowired
	private TelefoneDAO dao;
	
	@Override
	public void salvar(Telefone telefone) {
		dao.insert(telefone);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Telefone> recuperarTodos() {
		return dao.findAll();

	}
	@Override
	@Transactional(readOnly=true)
	public List<Telefone> recuperarPorClienteID(long id) {
		return dao.findByClienteID(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Telefone recuperarPorID(long id) {
		return dao.findByID(id);
	}

	@Override
	public void ataulizar(Telefone telefone) {
		dao.update(telefone);
	}

	@Override
	public void excluir(long id) {
		dao.delete(id);
	}

	public void excluirPorClienteID(long id) {
		dao.deleteByClienteId(id);
	}

}
