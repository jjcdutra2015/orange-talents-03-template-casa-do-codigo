package br.com.zupacademy.juliodutra.casadocodigo.pais;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/paises")
public class PaisController {

    private PaisRepository repository;

    public PaisController(PaisRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@Valid @RequestBody NovoPaisRequest request, UriComponentsBuilder builder) {
        Pais pais = request.toModel();
        repository.save(pais);

        URI uri = builder.path("/paises/{id}").buildAndExpand(pais.getId()).toUri();

        return ResponseEntity.created(uri).body(new PaisResponse(pais));
    }
}
