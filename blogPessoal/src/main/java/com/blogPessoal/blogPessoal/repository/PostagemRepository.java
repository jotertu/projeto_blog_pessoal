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
 * */

// A Anotação (Annotation) @Repository indica que a Interface é do tipo repositório
@Repository

public interface PostagemRepository extends JpaRepository <Postagem,Long> {

    public List<Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);

}
