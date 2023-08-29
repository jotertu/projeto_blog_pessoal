package com.blogPessoal.blogPessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogPessoal.blogPessoal.model.Postagem;
public interface PostagemRepository extends JpaRepository <Postagem,Long> {

}
