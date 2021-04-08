package br.com.zupacademy.juliodutra.casadocodigo.controller;

import br.com.zupacademy.juliodutra.casadocodigo.controller.dto.DetalheLivroDto;
import br.com.zupacademy.juliodutra.casadocodigo.controller.dto.ListarLivrosDto;
import br.com.zupacademy.juliodutra.casadocodigo.controller.dto.LivroDto;
import br.com.zupacademy.juliodutra.casadocodigo.controller.dto.LivroForm;
import br.com.zupacademy.juliodutra.casadocodigo.model.Livro;
import br.com.zupacademy.juliodutra.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.juliodutra.casadocodigo.repository.CategoriaRepository;
import br.com.zupacademy.juliodutra.casadocodigo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public ResponseEntity<List<ListarLivrosDto>> listarLivros() {
        List<Livro> list = repository.findAll();
        List<ListarLivrosDto> listDto = ListarLivrosDto.converter(list);
        return ResponseEntity.ok(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalheLivroDto> buscarPorId(@PathVariable("id") Long id) {
        Optional<Livro> livro = repository.findById(id);
        if (livro.isPresent()) {
            return ResponseEntity.ok(new DetalheLivroDto(livro.get()));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
