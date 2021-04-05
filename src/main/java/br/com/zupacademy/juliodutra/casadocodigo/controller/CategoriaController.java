package br.com.zupacademy.juliodutra.casadocodigo.controller;

import br.com.zupacademy.juliodutra.casadocodigo.controller.dto.CategoriaDto;
import br.com.zupacademy.juliodutra.casadocodigo.controller.dto.CategoriaForm;
import br.com.zupacademy.juliodutra.casadocodigo.model.Categoria;
import br.com.zupacademy.juliodutra.casadocodigo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;

    @PostMapping
    public ResponseEntity<CategoriaDto> cadastrar(@Valid @RequestBody CategoriaForm form, UriComponentsBuilder uriBuilder) {
        Categoria categoria = form.converter();
        repository.save(categoria);

        URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();

        return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
    }
}
