package br.com.zupacademy.juliodutra.casadocodigo.controller.dto;

import br.com.zupacademy.juliodutra.casadocodigo.config.compartilhado.UniqueValue;
import br.com.zupacademy.juliodutra.casadocodigo.model.Autor;
import br.com.zupacademy.juliodutra.casadocodigo.model.Categoria;
import br.com.zupacademy.juliodutra.casadocodigo.model.Livro;
import br.com.zupacademy.juliodutra.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.juliodutra.casadocodigo.repository.CategoriaRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;

public class LivroForm {

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "titulo")
    private String titulo;
    @NotBlank
    @Length(max = 500)
    private String resumo;
    private String sumario;
    @NotNull
    @Min(value = 20)
    private Double preco;
    @NotNull
    @Min(value = 100)
    private int paginas;
    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "isbn")
    private String isbn;
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;
    @NotNull
    private Long categoriaId;
    @NotNull
    private Long autorId;

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

    public int getPaginas() {
        return paginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public Long getAutorId() {
        return autorId;
    }

    public Livro converter(CategoriaRepository categoriaRepository, AutorRepository autorRepository) throws Exception {
        Optional<Categoria> possivelCategoria = categoriaRepository.findById(categoriaId);

        if (!possivelCategoria.isPresent()) {
            throw new Exception("Categoria não cadastrada");
        }

        Optional<Autor> possivelAutor = autorRepository.findById(autorId);
        if (!possivelAutor.isPresent()) {
            throw new Exception("Autor não cadastrado");
        }


        Livro livro = new Livro(
                this.titulo, this.resumo, this.sumario, this.preco,
                this.paginas, this.isbn, this.dataPublicacao, possivelCategoria.get(), possivelAutor.get());

        return livro;
    }
}
