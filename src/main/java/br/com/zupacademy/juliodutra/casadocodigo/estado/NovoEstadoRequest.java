package br.com.zupacademy.juliodutra.casadocodigo.estado;

import br.com.zupacademy.juliodutra.casadocodigo.pais.Pais;
import br.com.zupacademy.juliodutra.casadocodigo.pais.PaisRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class NovoEstadoRequest {

    @NotBlank
    private String nome;
    @NotNull
    private Long paisId;

    public NovoEstadoRequest(String nome, Long paisId) {
        this.nome = nome;
        this.paisId = paisId;
    }

    public String getNome() {
        return nome;
    }

    public Long getPaisId() {
        return paisId;
    }

    public Estado toModel(PaisRepository paisRepository) {
        Optional<Pais> optionalPais = paisRepository.findById(paisId);
        if (!optionalPais.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new Estado(this.nome, optionalPais.get());
    }
}
