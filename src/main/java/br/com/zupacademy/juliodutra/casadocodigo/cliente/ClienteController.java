package br.com.zupacademy.juliodutra.casadocodigo.cliente;

import br.com.zupacademy.juliodutra.casadocodigo.estado.EstadoRepository;
import br.com.zupacademy.juliodutra.casadocodigo.pais.PaisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    private ClienteRepository clienteRepository;
    private PaisRepository paisRepository;
    private EstadoRepository estadoRepository;
    private EstadoPertenceAPaisValidator estadoPertenceAPaisValidator;

    public ClienteController(ClienteRepository clienteRepository, PaisRepository paisRepository,
                             EstadoRepository estadoRepository, EstadoPertenceAPaisValidator estadoPertenceAPaisValidator) {
        this.clienteRepository = clienteRepository;
        this.paisRepository = paisRepository;
        this.estadoRepository = estadoRepository;
        this.estadoPertenceAPaisValidator = estadoPertenceAPaisValidator;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(estadoPertenceAPaisValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClienteResponse> cadastrar(@Valid @RequestBody NovoClienteRequest request) throws Exception {
        Cliente cliente = request.toModel(paisRepository, estadoRepository);
        clienteRepository.save(cliente);

        return ResponseEntity.ok(new ClienteResponse(cliente));
    }
}
