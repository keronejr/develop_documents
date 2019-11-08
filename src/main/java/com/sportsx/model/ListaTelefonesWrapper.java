package com.sportsx.model;

import java.util.ArrayList;
import java.util.List;

public class ListaTelefonesWrapper {

	List<Telefone> listaTelefones = new ArrayList<Telefone>();
	
	public ListaTelefonesWrapper() {
		
	}

	public void addtelefone(Telefone telefone) {
        this.listaTelefones.add(telefone);
    }
	
	public List<Telefone> getListaTelefones() {
		return listaTelefones;
	}

	public void setListaTelefones(List<Telefone> listaTelefones) {
		this.listaTelefones = listaTelefones;
	}
	
}
