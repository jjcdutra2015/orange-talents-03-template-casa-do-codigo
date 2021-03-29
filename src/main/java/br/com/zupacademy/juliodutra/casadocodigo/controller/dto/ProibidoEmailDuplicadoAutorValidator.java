package br.com.zupacademy.juliodutra.casadocodigo.controller.dto;

import br.com.zupacademy.juliodutra.casadocodigo.AutorRepository;
import br.com.zupacademy.juliodutra.casadocodigo.model.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibidoEmailDuplicadoAutorValidator implements Validator {

    @Autowired
    private AutorRepository repository;

    @Override
    public boolean supports(Class<?> aClass) {
        return AutorForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        AutorForm request = (AutorForm) target;
        Optional<Autor> possivelAutor = repository.findByEmail(request.getEmail());
        if (possivelAutor.isPresent()) {
            errors.rejectValue("email", null, "Já existe um outro autor cadastrado com o mesmo email, " + request.getEmail());
        }
    }
}
