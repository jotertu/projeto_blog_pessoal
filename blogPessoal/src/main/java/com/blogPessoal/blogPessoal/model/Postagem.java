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
 *
 * ALERTA DE BSM: Mantenha a Atenção aos Detalhes ao criar a Camada Model. Um erro muito comum é criar o pacote
 * na Source Folder de Testes, ao invés de criar na Source Folder Principal.
 *
 * Observe que acima de cada Atributo foram adicionadas algumas Anotações. Estas anotações têm a função de configurar os parâmetros do
 * Banco de dados e criar validações para os dados que serão inseridos no objeto (tamanho, formato e etc).
 *
 *		Estratégias para geração da Chave Primária:
 *
 * 		GenerationType.AUTO
 * 		Valor padrão, deixa com o provedor de persistência a escolha da estratégia mais adequada de acordo com
 * 		o Banco de dados.
 *
 * 		GenerationType.IDENTITY
 * 		Informamos ao provedor de persistência que os valores a serem atribuídos ao identificador único serão
 * 		gerados pela coluna de auto incremento do banco de dados. Assim, um valor para o identificador é gerado
 * 		para cada registro inserido no banco. Alguns bancos de dados podem não suportar essa opção.
 *
 * 		GenerationType.SEQUENCE
 * 		Informamos ao provedor de persistência que os valores serão gerados a partir de uma sequence. Caso não
 * 		seja especificado um nome para a sequence, será utilizada uma sequence padrão, a qual será global, para
 * 		todas as entidades. Caso uma sequence seja especificada, o provedor passará a adotar essa sequence para
 * 		criação das chaves primárias. Alguns bancos de dados podem não suportar essa opção, como o MySQL por
 * 		exemplo.
 *
 * 		GenerationType.TABLE
 * 		Com a opção TABLE é necessário criar uma tabela para gerenciar as chaves primárias. Por causa da
 * 		sobrecarga de consultas necessárias para manter a tabela atualizada, essa opção é pouco recomendada.
 *
 * DICA: Para relembrar os tipos de dados em Java, clique aqui e explore os principais tipos de dados e Classes que o Java oferece.
 * LINK: https://github.com/rafaelq80/spring_content/blob/main/03_spring/java_tipos.md
 *
 * DICA: Acesse o Guia do JPA e explore outras opções de Validação para os Atributos. Essas validações serão muito úteis em seus projetos futuros.
 * LINK: https://github.com/rafaelq80/spring_content/blob/main/03_spring/guia_jpa.md
 * */

/*
 * A Anotação (Annotation) @Entity indica que esta Classe define uma entidade, ou seja, ela será utilizada para gerar uma tabela no
 * Banco de dados da aplicação.
 * */
@Entity

/*
 * A Anotação @Table indica o nome da Tabela no Banco de dados. Caso esta anotação não seja declarada, o Banco de dados criará a
 * tabela com o mesmo nome da Classe Model (Postagem). Observe que o nome da Tabela segue o padrão tb_nome-da-tabela (tb_postagens). O
 * prefixo tb indica que se trata de uma Table (Tabela). O nome da Tabela é recomendado que seja o mesmo da Classe Model (postagem), em
 * letras minúsculas, sem espaços em branco ou caracteres especiais e acentos.
 * */
@Table(name = "tb_postagens")
public class Postagem {

	// Identificador único

	// A Anotação @Id inidica que o Atributo anotado será a Chave Primária (Primary Key - PK) da Tabela tb_postagens.
	@Id

	/*
	 * A Anotação @GeneratedValue indica que a Chave Primária será gerada pelo Spring Data JPA. O parâmetro strategy indica de que
	 * forma esta Chave Primária será gerada. A Estratégia GenerationType.IDENTITY indica que a Chave Primária será gerada pelo Banco de dados
	 * através da opção auto-incremento (auto-increment), que gera uma sequência numérica iniciando em 1. Veja outras estratégias de geração da
	 * Chave Primária
	 *
	 * ATENÇÃO: Não confundir o auto-incremento do Banco de Dados que inicia em 1 com o índice de um Array (Vetor ou Matriz) que inicia em 0.
	 * */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // Atributo: ID; Tipo de dado Java: Long; Tipo de dado MySQL: BIGINT;

	// Título da postagem

	// A anotação @NotBlank não permite que o Atributo seja Nulo ou contenha apenas Espaços em branco. Você pode
	// configurar uma mensagem para o usuário através do Atributo message.
	@NotBlank(message = "O atributo título é Obrigatório!")

	/*
	 * A anotação @Size define o valor Mínimo (min) e Máximo (max) de Caracteres do Atributo. Não é obrigatório configurar os
	 * 2 parâmetros. Como o parâmetro max foi configurado, observe que o mesmo valor informado será inserido na definição dos Atributos título
	 * (varchar(100)) e texto (varchar(1000)) na tabela tb_postagens no Banco de dados. Você pode configurar uma mensagem para o usuário através
	 * do Atributo message.
	 *
	 * */
	@Size(min = 5, max = 100, message = "O atributo título deve conter no mínimo 05 e no máximo 100 caracteres")
	private String titulo; // Atributo: título; Tipo de dado Java: String; Tipo de dado MySQL: VARCHAR(100);

	// Conteúdo da postagem
	@NotBlank(message = "O atributo texto é Obrigatório!")
	@Size(min = 5, max = 1000, message = "O atributo texto deve conter no mínimo 05 e no máximo 1000 caracteres")
	private String texto; // Atributo: texto; Tipo de dado Java: String; Tipo de dado MySQL: VARCHAR(1000);

	// Data e hora da publicação/atualização da postagem

	/*
	 * A anotação @UpdateTimestamp configura o Atributo data como Timestamp, ou seja, o Spring se encarregará de obter a data do
	 * Sistema Operacional e inserir no Atributo data toda vez que um Objeto da Classe Postagem for criado ou atualizado.
	 * */
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
