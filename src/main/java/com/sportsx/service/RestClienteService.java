package com.sportsx.service;

import java.util.List;

import com.sportsx.model.Cliente;
import com.sportsx.model.RestCliente;

public interface RestClienteService {

	RestCliente salvar(RestCliente cliente);
	List<RestCliente> recuperarTodos();
	List<RestCliente> recuperarPorTipo(String tipo);
	List<RestCliente> recuperarPorTipoENome(String tipo, String nome);
	RestCliente recuperarPorID(long id);
	void ataulizar(RestCliente cliente);
	void excluir(long id);
	
}
