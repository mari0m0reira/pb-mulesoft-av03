package com.example.compass.api.controller.dto;

import org.springframework.data.domain.Page;

import com.example.compass.api.modelo.Estados;

public class EstadosDto {

	//private Long id;
	private String nome;
	private String regiao;
	private int populacao;
	private String capital;
	private int area;
	
	public EstadosDto(Estados estados) {
	
		//this.id = estados.getId();
		this.nome = estados.getNome();
		this.regiao = estados.getRegiao();
		this.populacao = estados.getPopulacao();
		this.capital = estados.getCapital();
		this.area = estados.getArea();
	}

	//public Long getId() {
	//	return id;
	//}

	public String getNome() {
		return nome;
	}

	public String getRegiao() {
		return regiao;
	}

	public int getPopulacao() {
		return populacao;
	}

	public String getCapital() {
		return capital;
	}

	public int getArea() {
		return area;
	}

	public static Page<EstadosDto> converter(Page<Estados> estados) {
		return estados.map(EstadosDto::new);
	}
	
	

}
