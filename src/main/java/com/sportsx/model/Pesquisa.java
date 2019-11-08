package com.sportsx.model;

public class Pesquisa {

	String tipo  ="";
	String nome ="";
	
	public Pesquisa() {
	}
	
	public Pesquisa(String tipo) {
		setTipo(tipo);
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
