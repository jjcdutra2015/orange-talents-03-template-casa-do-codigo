package br.com.zupacademy.juliodutra.casadocodigo.pais;

import br.com.zupacademy.juliodutra.casadocodigo.config.compartilhado.UniqueValue;

import javax.validation.constraints.NotBlank;

public class NovoPaisRequest {

    @NotBlank
    @UniqueValue(domainClass = Pais.class, fieldName = "nome")
    private String nome;

    public Pais toModel() {
        return new Pais(this.nome);
    }

    public String getNome() {
        return nome;
    }
}
