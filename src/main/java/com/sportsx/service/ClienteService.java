package com.sportsx.service;

import java.util.List;

import com.sportsx.model.Cliente;

public interface ClienteService {

	Cliente salvar(Cliente cliente);
	List<Cliente> recuperarTodos();
	List<Cliente> recuperarPorTipo(String tipo);
	List<Cliente> recuperarPorTipoENome(String tipo, String nome);
	Cliente recuperarPorID(long id);
	void ataulizar(Cliente cliente);
	void excluir(long id);
	
}
