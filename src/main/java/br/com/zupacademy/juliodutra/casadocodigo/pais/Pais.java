package br.com.zupacademy.juliodutra.casadocodigo.pais;

import javax.persistence.*;

@Entity
public class Pais {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    @Deprecated
    public Pais() {
    }

    public Pais(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}