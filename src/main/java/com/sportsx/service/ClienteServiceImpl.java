package com.sportsx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sportsx.dao.ClienteDAO;
import com.sportsx.model.Cliente;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteDAO dao;
	
	@Override
	public Cliente salvar(Cliente cliente) {
		cliente = dao.insert(cliente);
		return cliente;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Cliente> recuperarTodos() {
		return dao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente recuperarPorID(long id) {
		return dao.findByID(id);
	}

	@Override
	public void ataulizar(Cliente cliente) {
		dao.update(cliente);
	}

	@Override
	public void excluir(long id) {
		dao.delete(id);
	}

	@Override
	public List<Cliente> recuperarPorTipo(String tipo) {
		return dao.findByType(tipo);
	}
	
	@Override
	public List<Cliente> recuperarPorTipoENome(String tipo, String nome) {
		return dao.findByTypeAndName(tipo, nome);
	}

}
