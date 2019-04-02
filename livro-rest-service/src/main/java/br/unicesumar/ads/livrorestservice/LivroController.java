/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unicesumar.ads.livrorestservice;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gustavo
 */
@RestController
public class LivroController {
        @Autowired
    private LivroRepository livroRepository;
    
    @GetMapping("/livros")
    public List<Livro> consultaTodosLivros() {
            return livroRepository.findAll();
    }    
    
    @GetMapping("/livros/{id}")
    public Livro consultaLivro(@PathVariable long id) {
	Optional<Livro> livro = livroRepository.findById(id);

	if (!livro.isPresent())
		throw new RuntimeException("id-" + id);

	return livro.get();
    }
    
    @DeleteMapping("/livros/{id}")
    public void deleteLivro(@PathVariable long id) {
            livroRepository.deleteById(id);
    }

    @PostMapping("/livros")
    public Livro createLivro(@RequestBody Livro livro) {
        Optional<Livro> novoLivro = livroRepository.findById(livro.getId());
        if (novoLivro.isPresent())
            throw new RuntimeException("id-" + livro.getId());

	livroRepository.save(livro);
	return livro;
    }

    
}
