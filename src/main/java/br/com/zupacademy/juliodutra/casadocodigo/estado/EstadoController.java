package br.com.zupacademy.juliodutra.casadocodigo.estado;

import br.com.zupacademy.juliodutra.casadocodigo.pais.PaisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("estados")
public class EstadoController {

    private EstadoRepository repository;
    private PaisRepository paisRepository;
    private ProibidoEstadoDuplicadoPais proibidoEstadoDuplicadoPais;

    public EstadoController(EstadoRepository repository, PaisRepository paisRepository, ProibidoEstadoDuplicadoPais proibidoEstadoDuplicadoPais) {
        this.repository = repository;
        this.paisRepository = paisRepository;
        this.proibidoEstadoDuplicadoPais = proibidoEstadoDuplicadoPais;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(proibidoEstadoDuplicadoPais);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EstadoResponse> cadastrar(@Valid @RequestBody NovoEstadoRequest request, UriComponentsBuilder builder) {
        Estado estado = request.toModel(paisRepository);
        repository.save(estado);

        URI uri = builder.path("/estados/{id}").buildAndExpand(estado.getId()).toUri();

        return ResponseEntity.created(uri).body(new EstadoResponse(estado));
    }
}
