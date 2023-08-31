package com.blogPessoal.blogPessoal.controller;

import com.blogPessoal.blogPessoal.model.Postagem;
import com.blogPessoal.blogPessoal.repository.PostagemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/*
 *   Camada responsável por receber todas as Requisições HTTP (HTTP Request), enviadas por um Cliente HTTP (Postman ou o
 *   Front-end da API), para a nossa aplicação e responder (HTTP Response) as requisições de acordo com o resultado do
 *   processamento da requisição no Back-end.
 */
@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders="*")
public class PostagemController {
    @Autowired
    private PostagemRepository postagemRepository;

    @GetMapping
    public ResponseEntity<List <Postagem>> getAll() {
        return ResponseEntity.ok(postagemRepository.findAll());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity <List<Postagem>> getByTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
    }

    @PostMapping
    public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postagemRepository.save(postagem));
    }

    @PutMapping
    public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem) {
        return postagemRepository.findById(postagem.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                .body(postagemRepository.save(postagem)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<Postagem> postagem = postagemRepository.findById(id);

        if(postagem.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        postagemRepository.deleteById(id);
    }

}
