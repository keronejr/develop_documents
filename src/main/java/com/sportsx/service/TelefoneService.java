package com.sportsx.service;

import java.util.List;

import com.sportsx.model.Telefone;

public interface TelefoneService {

	void salvar(Telefone telefone);
	List<Telefone> recuperarTodos();
	List<Telefone> recuperarPorClienteID(long id);
	Telefone recuperarPorID(long id);
	void ataulizar(Telefone telefone);
	void excluir(long id);
	void excluirPorClienteID(long id);

}
