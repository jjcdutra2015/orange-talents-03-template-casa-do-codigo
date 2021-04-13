package br.com.zupacademy.juliodutra.casadocodigo.cliente;

import br.com.zupacademy.juliodutra.casadocodigo.estado.Estado;
import br.com.zupacademy.juliodutra.casadocodigo.pais.Pais;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String nome;
    private String sobreNome;
    private String documento;
    private String endereco;
    private String complemento;
    private String cidade;
    @ManyToOne
    private Pais pais;
    @ManyToOne
    private Estado estado;
    private String telefone;
    private String cep;

    public Cliente(String email, String nome, String sobreNome, String documento, String endereco, String complemento,
                   String cidade, Pais pais, String telefone, String cep) {
        this.email = email;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.pais = pais;
        this.telefone = telefone;
        this.cep = cep;
    }

    public Long getId() {
        return id;
    }

    public void setEstado(Estado estado) {
        Assert.notNull(pais, "Estado não pode ser associado a país nulo.");
        Assert.isTrue(estado.pertenceAPais(pais), "Estado não pertence ao país selecionado.");
        this.estado = estado;
    }
}
