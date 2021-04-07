package br.com.zupacademy.juliodutra.casadocodigo.controller.dto;

import br.com.zupacademy.juliodutra.casadocodigo.model.Livro;

import java.util.List;
import java.util.stream.Collectors;

public class ListarLivrosDto {

    private Long id;
    private String nome;

    public ListarLivrosDto(Livro livro) {
        this.id = livro.getId();
        this.nome = livro.getTitulo();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static List<ListarLivrosDto> converter(List<Livro> list) {
        return list.stream().map(ListarLivrosDto::new).collect(Collectors.toList());
    }
}
