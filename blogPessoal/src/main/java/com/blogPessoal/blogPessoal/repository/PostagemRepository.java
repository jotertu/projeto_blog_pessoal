package com.blogPessoal.blogPessoal.repository;

import  java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogPessoal.blogPessoal.model.Postagem;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Camada responsável por implementar as Interfaces, que contém diversos Métodos pré-implementados para a manipulação
 * de dados de uma entidade, como Métodos para salvar, deletar, listar e recuperar dados da Classe. Para criar estas Interfaces
 * basta Herdar (extends) a Interface JpaRepository.
 *
 * Uma interface em Java é uma Classe Abstrata (uma Classe que serve de modelo para outras Classes), composta somente por Métodos
 * abstratos. E como tal, obviamente não pode ser instanciada, ou seja, ela só contém as declarações dos Métodos e constantes, nenhuma
 * implementação, apenas as assinaturas dos Métodos, que serão implementados em uma Classe.
 *
 *      Métodos Padrão da Interface JpaRepository:
 *
 *      save(Objeto objeto)
 * 	    Cria ou Atualiza um objeto no Banco de Dados.
 *
 * 	    findById(Long id)
 * 	    Retorna (exibe) um Objeto persistido de acordo com o id informado.
 *
 * 	    existsById(Long id)
 * 	    Retorna True se um Objeto identificado pelo id estiver persistido no Banco de dados.
 *
 * 	    findAll()
 * 	    Retorna (exibe) todos os Objetos persistidos.
 *
 * 	    deletedById(Long id)
 * 	    Localiza um Objeto persistido pelo id e deleta caso ele seja encontrado. Não é possível
 * 	    desfazer esta operação.
 *
 * 	    deleteAll()
 * 	    Deleta todos os Objetos persistidos.Não é possível desfazer esta operação.
 * */

/*
 * A Anotação (Annotation) @Repository indica que a Interface é do tipo repositório, ou seja, ela é responsável pela interação com o
 * Banco de dados através dos Métodos padrão (Herdados da Interface JPA Repository) e das Query Methods, que são Métodos personalizados
 * que geram consultas (Instruções SQL do tipo Select), através da combinação de palavras chave, que representam os comandos da linguagem
 * SQL.
 * */
@Repository

/*
 * Observe que na declaração da Interface foi adicionada a Herança através da palavra reservada extends com a Interface JpaRepository,
 * que recebe 2 parâmetros:
 *
 * 1 - A Classe Postagem, que é a Entidade que será mapeada em nosso Banco de dados (Lembre-se que a Classe Postagem foi quem gerou a
 * nossa tabela tb_postagens)
 *
 * 2 - O Long representa a nossa Chave Primária (Primary Key), que é o Atributo que recebeu a anotação @Id na nossa Classe Postagem (o
 * Atributo também se chama id em nossa Classe Postagem).
 *
 * Estes 2 parâmetros são do tipo Java Generics (podem receber qualquer tipo de Objeto <T, T>). Dentro contexto do JPA são o mínimo
 * necessário para executar os Métodos padrão da Interface Repository, que serão implementados na próxima etapa na Classe
 * PostagemController. Estes Métodos básicos já ficam automaticamente disponíveis no Recurso Postagem a partir do momento que a Interface
 * PostagemRepository herda a Interface JpaRepository.
 *
 * */
public interface PostagemRepository extends JpaRepository <Postagem,Long> {

    public List<Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);

}
