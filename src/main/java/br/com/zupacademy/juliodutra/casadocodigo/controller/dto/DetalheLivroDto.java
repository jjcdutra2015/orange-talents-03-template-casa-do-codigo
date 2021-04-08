package br.com.zupacademy.juliodutra.casadocodigo.controller.dto;

import br.com.zupacademy.juliodutra.casadocodigo.model.Autor;
import br.com.zupacademy.juliodutra.casadocodigo.model.Categoria;
import br.com.zupacademy.juliodutra.casadocodigo.model.Livro;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DetalheLivroDto {

    private Long id;
    private String titulo;
    private String resumo;
    private String sumario;
    private Double preco;
    private Integer paginas;
    private String isbn;
    private String dataPublicacao;
    private Categoria categoria;
    private Autor autor;

    public DetalheLivroDto(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.paginas = livro.getPaginas();
        this.isbn = livro.getIsbn();
        this.dataPublicacao = livro.getDataPublicacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.categoria = livro.getCategoria();
        this.autor = livro.getAutor();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public Double getPreco() {
        return preco;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Autor getAutor() {
        return autor;
    }
}
