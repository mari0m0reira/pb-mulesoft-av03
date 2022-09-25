package com.example.compass.api.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.example.compass.api.modelo.Estados;
import com.example.compass.api.repository.EstadosRepository;

public class AtualizacaoEstadosForm {

	@NotNull @NotEmpty @Length(min = 1)
	private String nome;
	
	@NotNull @NotEmpty @Length(min = 1)
	private String regiao;
	
	@NotNull
	private int populacao;
	
	@NotNull @NotEmpty @Length(min = 1)
	private String capital;
	
	@NotNull
	private int area;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRegiao() {
		return regiao;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
	public int getPopulacao() {
		return populacao;
	}
	public void setPopulacao(int populacao) {
		this.populacao = populacao;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public Estados atualizar(Long id, EstadosRepository estadosRepository) {
		Estados estados = estadosRepository.findById(id).get();
		estados.setNome(this.nome);
		estados.setRegiao(this.regiao);
		estados.setPopulacao(this.populacao);
		estados.setCapital(this.capital);
		estados.setArea(this.area);		
		return estados;
	}
	
	
}
