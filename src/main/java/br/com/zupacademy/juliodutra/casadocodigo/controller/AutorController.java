package br.com.zupacademy.juliodutra.casadocodigo.controller;

import br.com.zupacademy.juliodutra.casadocodigo.controller.dto.AutorDto;
import br.com.zupacademy.juliodutra.casadocodigo.controller.dto.AutorForm;
import br.com.zupacademy.juliodutra.casadocodigo.model.Autor;
import br.com.zupacademy.juliodutra.casadocodigo.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    @PostMapping
    public ResponseEntity<AutorDto> cadastrar(@Valid @RequestBody AutorForm form, UriComponentsBuilder uriBuilder) {
        Autor autor = form.converter();
        autorRepository.save(autor);

        URI uri = uriBuilder.path("/autores/{id}").buildAndExpand(autor.getId()).toUri();

        return ResponseEntity.created(uri).body(new AutorDto(autor));
    }
}
