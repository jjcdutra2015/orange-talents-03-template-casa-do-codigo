package br.com.zupacademy.juliodutra.casadocodigo.controller.dto;

import br.com.zupacademy.juliodutra.casadocodigo.model.Categoria;
import br.com.zupacademy.juliodutra.casadocodigo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibeNomeDuplicadoCategoriaValidator implements Validator {

    @Autowired
    private CategoriaRepository repository;

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoriaForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        CategoriaForm request = (CategoriaForm) target;
        Optional<Categoria> possivelCategoria = repository.findByNome(request.getNome());
        if (possivelCategoria.isPresent()) {
            errors.rejectValue("nome", null, "Já existe uma categoria cadastrada para o mesmo nome, " + request.getNome());
        }
    }
}
