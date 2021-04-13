package br.com.zupacademy.juliodutra.casadocodigo.estado;

import br.com.zupacademy.juliodutra.casadocodigo.pais.Pais;

import javax.persistence.*;

@Entity
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    @ManyToOne
    private Pais pais;

    @Deprecated
    public Estado() {
    }

    public Estado(String nome, Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Pais getPais() {
        return pais;
    }

    public boolean pertenceAPais(Pais pais) {
        return this.pais.equals(pais);
    }
}
