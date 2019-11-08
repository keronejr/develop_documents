package com.sportsx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sportsx.dao.RestClienteDAO;
import com.sportsx.model.Cliente;
import com.sportsx.model.RestCliente;

@Service
@Transactional
public class RestClienteServiceImpl implements RestClienteService{

	@Autowired
	private RestClienteDAO dao;
	
	@Override
	public RestCliente salvar(RestCliente cliente) {
		cliente = dao.insert(cliente);
		return cliente;
	}

	@Override
	@Transactional(readOnly=true)
	public List<RestCliente> recuperarTodos() {
		return dao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public RestCliente recuperarPorID(long id) {
		return dao.findByID(id);
	}

	@Override
	public void ataulizar(RestCliente cliente) {
		dao.update(cliente);
	}

	@Override
	public void excluir(long id) {
		dao.delete(id);
	}

	@Override
	public List<RestCliente> recuperarPorTipo(String tipo) {
		return dao.findByType(tipo);
	}
	
	@Override
	public List<RestCliente> recuperarPorTipoENome(String tipo, String nome) {
		return dao.findByTypeAndName(tipo, nome);
	}

}
