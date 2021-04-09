package br.com.zupacademy.juliodutra.casadocodigo.estado;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibidoEstadoDuplicadoPais implements Validator {

    private EstadoRepository repository;

    public ProibidoEstadoDuplicadoPais(EstadoRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoEstadoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        NovoEstadoRequest request = (NovoEstadoRequest) target;
        var estado = repository.findByNomeAndPaisId(request.getNome(), request.getPaisId());
        if (estado.isPresent()) {
            errors.rejectValue("nome", null, "O castastro do estado " + request.getNome() + " no país deve ser único");
        }
    }
}
