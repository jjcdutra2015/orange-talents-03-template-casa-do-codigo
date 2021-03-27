package br.com.zupacademy.juliodutra.casadocodigo.controller;

import br.com.zupacademy.juliodutra.casadocodigo.AutorRepository;
import br.com.zupacademy.juliodutra.casadocodigo.controller.dto.AutorDto;
import br.com.zupacademy.juliodutra.casadocodigo.controller.dto.AutorForm;
import br.com.zupacademy.juliodutra.casadocodigo.model.Autor;
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
@RequestMapping("/autores")
public class AutorController {

    @Autowired private AutorRepository autorRepository;

    @PostMapping
    public ResponseEntity<AutorDto> cadastrar(@Valid @RequestBody AutorForm form, UriComponentsBuilder uriBuilder) {
        Autor autor = form.converter();
        autorRepository.save(autor);

        URI uri = uriBuilder.path("/autores/{id}").buildAndExpand(autor.getId()).toUri();

        return ResponseEntity.created(uri).body(new AutorDto(autor));
    }
}
