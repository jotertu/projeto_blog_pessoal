package com.blogPessoal.blogPessoal.repository;

import  java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogPessoal.blogPessoal.model.Postagem;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface PostagemRepository extends JpaRepository <Postagem,Long> {

    public List<Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);

}
