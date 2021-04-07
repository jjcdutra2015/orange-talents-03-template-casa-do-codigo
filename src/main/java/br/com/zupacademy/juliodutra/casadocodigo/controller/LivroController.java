package br.com.zupacademy.juliodutra.casadocodigo.controller;

import br.com.zupacademy.juliodutra.casadocodigo.controller.dto.LivroDto;
import br.com.zupacademy.juliodutra.casadocodigo.controller.dto.LivroForm;
import br.com.zupacademy.juliodutra.casadocodigo.model.Livro;
import br.com.zupacademy.juliodutra.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.juliodutra.casadocodigo.repository.CategoriaRepository;
import br.com.zupacademy.juliodutra.casadocodigo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping
    public ResponseEntity<LivroDto> cadastrar(@RequestBody @Valid LivroForm form, UriComponentsBuilder builder) throws Exception {
        Livro livro = form.converter(categoriaRepository, autorRepository);
        repository.save(livro);

        URI uri = builder.path("/livros/{id}").buildAndExpand(livro.getId()).toUri();

        return ResponseEntity.created(uri).body(new LivroDto(livro));
    }
}
