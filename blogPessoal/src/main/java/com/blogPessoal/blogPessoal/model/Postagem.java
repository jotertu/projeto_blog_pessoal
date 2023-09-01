package com.blogPessoal.blogPessoal.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Camada responsável pela abstração dos nossos Objetos em registros das nossas tabelas, que serão geradas no Banco de
 * dados. As Classes criadas nesta camada representam os objetos que serão persistidos no Banco de dados.
 * */

/*
 * A Anotação (Annotation) @Entity indica que esta Classe define uma entidade, ou seja, ela será utilizada para gerar uma tabela no
 * Banco de dados da aplicação.
 * */
@Entity
@Table(name = "tb_postagens")
public class Postagem {

	// Identificador único

	// A Anotação @Id inidica que o Atributo anotado será a Chave Primária (Primary Key - PK) da Tabela tb_postagens.
	@Id

	// A Anotação @GeneratedValue indica que a Chave Primária será gerada pelo Spring Data JPA.
	// O parâmetro strategy indica de que forma esta Chave Primária será gerada.
	// A Estratégia GenerationType.IDENTITY indica que a Chave Primária será gerada pelo Banco de dados
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// Identificador único da tabela
	private Long id; // Atributo: ID; Tipo de dado Java: Long; Tipo de dado MySQL: BIGINT;

	// A anotação @NotBlank não permite que o Atributo seja Nulo ou contenha apenas Espaços em branco. Você pode
	// configurar uma mensagem para o usuário através do Atributo message.
	@NotBlank(message = "O atributo título é Obrigatório!")

	 // A anotação @Size define o valor Mínimo (min) e Máximo (max) de Caracteres do Atributo.
	@Size(min = 5, max = 100, message = "O atributo título deve conter no mínimo 05 e no máximo 100 caracteres")

	// Título da postagem
	private String titulo; // Atributo: título; Tipo de dado Java: String; Tipo de dado MySQL: VARCHAR(100);

	// Conteúdo da postagem
	@NotBlank(message = "O atributo texto é Obrigatório!")
	@Size(min = 5, max = 1000, message = "O atributo texto deve conter no mínimo 05 e no máximo 1000 caracteres")
	private String texto; // Atributo: texto; Tipo de dado Java: String; Tipo de dado MySQL: VARCHAR(1000);

	// Data e hora da publicação/atualização da postagem
	// A anotação @UpdateTimestamp configura o Atributo data como Timestamp
	@UpdateTimestamp
	private LocalDateTime data; // Atributo: data; Tipo de dado Java: LocalDateTime; Tipo de dado MySQL: DATETIME(6);

	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}
}
