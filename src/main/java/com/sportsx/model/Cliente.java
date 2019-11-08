package com.sportsx.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "Cliente")
public class Cliente {

	@Id
    @GeneratedValue
    private Long id;
	
	@Column(name = "tipo")
	private String tipo = "PF";
	 
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "razao_social")
	private String razao_social;
	
	@Column(name = "classificacao")
	private String classificacao = "A";
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "cnpj")
	private String cnpj;
	
	@Column(name = "cep")
	private String cep;
	
	@Column(name = "email")
	private String email;
	
	@Range(min = 0, max = 9999)
	@Column(name= "pontos")
	private int pontos;
	
	@Column(name= "data_cadastro")
	private Date data_cadastro;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Telefone> telefones = new ArrayList<Telefone>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getRazao_social() {
		return razao_social;
	}

	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}
	
	public String getDocumentoStr() { 
		return "PF".equals(tipo) ? cpf : cnpj; 
	}
	
	public String getClassificacaoStr() {
		return "A".equals(classificacao) ? "Ativo" : "I".equals(classificacao) ? "Inativo" : "Preferencial" ;
	}
	
	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public String getTelefonesStr() {
		StringBuilder tels = new StringBuilder();
        tels.append("");

        for (Telefone tel : telefones) {
        
            if (!"".equals(tels.toString()))
                tels.append(" - ");
            tels.append(tel.getNumero());
        };

        return tels.toString();
	}
	
	public int getDesconto() {
		int intBase = 1;
        if ("I".equals(classificacao.toString()))
            return 0;
        
        if ("P".equals(classificacao.toString()))
            intBase++;

        int desconto_cadastro = getMesesCadastro(data_cadastro);

        int desconto_pontos;

        if (pontos > 1000)
            desconto_pontos = 10;
        else if (pontos > 300)
            desconto_pontos = 5;
        else
            desconto_pontos = 3;

        desconto_cadastro = desconto_cadastro * intBase;
        desconto_pontos = desconto_pontos * intBase;

        return desconto_cadastro > desconto_pontos ? desconto_cadastro : desconto_pontos;
	}
	
	public String getDescontoStr() {
		return String.valueOf(getDesconto())+ " %";
	}
	
    private int getMesesCadastro(Date data_Cadastro)
    {
        if (data_Cadastro == null)
            return 0;

        Date data_atual = Calendar.getInstance().getTime();

        	//calculo 

        return 10;
    }
    
    public transient String telefone;
    
    public String getTelefone() {
		return telefone;
	}
    public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
