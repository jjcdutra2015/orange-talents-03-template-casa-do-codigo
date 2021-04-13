package br.com.zupacademy.juliodutra.casadocodigo.cliente;

import br.com.zupacademy.juliodutra.casadocodigo.estado.EstadoRepository;
import br.com.zupacademy.juliodutra.casadocodigo.pais.PaisRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EstadoPertenceAPaisValidator implements Validator {

    private PaisRepository paisRepository;
    private EstadoRepository estadoRepository;

    public EstadoPertenceAPaisValidator(PaisRepository paisRepository, EstadoRepository estadoRepository) {
        this.paisRepository = paisRepository;
        this.estadoRepository = estadoRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoClienteRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        NovoClienteRequest request = (NovoClienteRequest) target;
        if (request.temEstado()) {
            var pais = paisRepository.findById(request.getPaisId());
            var estado = estadoRepository.findById(request.getEstadoId());
            if (!estado.get().pertenceAPais(pais.get())) {
                errors.rejectValue("estadoId", null, "Estado não pertence ao país selecionado.");
            }
        }
    }
}
