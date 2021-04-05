package br.com.zupacademy.juliodutra.casadocodigo.controller.dto;

import br.com.zupacademy.juliodutra.casadocodigo.config.compartilhado.UniqueValue;
import br.com.zupacademy.juliodutra.casadocodigo.model.Autor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AutorForm {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    @UniqueValue(domainClass = Autor.class, fieldName = "email")
    private String email;

    @NotBlank
    @Size(max = 400, message = "Descrição não pode ter mais de 400 caracteres")
    private String descricao;

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDescricao() {
        return descricao;
    }

    public Autor converter() {
        return new Autor(this.nome, this.email, this.descricao);
    }
}
