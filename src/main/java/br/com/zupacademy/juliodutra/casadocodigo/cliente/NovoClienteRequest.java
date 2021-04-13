package br.com.zupacademy.juliodutra.casadocodigo.cliente;

import br.com.zupacademy.juliodutra.casadocodigo.config.compartilhado.UniqueValue;
import br.com.zupacademy.juliodutra.casadocodigo.estado.Estado;
import br.com.zupacademy.juliodutra.casadocodigo.estado.EstadoRepository;
import br.com.zupacademy.juliodutra.casadocodigo.pais.PaisRepository;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NovoClienteRequest {

    @Email
    @NotBlank
    @UniqueValue(domainClass = Cliente.class, fieldName = "email")
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String sobreNome;
    @NotBlank
    @UniqueValue(domainClass = Cliente.class, fieldName = "documento")
    private String documento;
    @NotBlank
    private String endereco;
    @NotBlank
    private String complemento;
    @NotBlank
    private String cidade;
    @NotNull
    private Long paisId;
    private Long estadoId;
    @NotBlank
    private String telefone;
    @NotBlank
    private String cep;

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public Long getPaisId() {
        return paisId;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public Cliente toModel(PaisRepository paisRepository, EstadoRepository estadoRepository) throws Exception {
        var pais = paisRepository.findById(paisId);
        if (!pais.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Cliente cliente = new Cliente(this.email, this.nome, this.sobreNome, cpfOuCnpj(this.documento), this.endereco, this.complemento, this.cidade,
                pais.get(), this.telefone, this.cep);

        if (estadoId != null) {
            var estado = estadoRepository.findById(estadoId);
            cliente.setEstado(estado.get());
        }

        return cliente;
    }

    private String cpfOuCnpj(String documento) throws Exception {
        if (documento.length() == 11) {
            this.documento = documento;
        } else if (documento.length() == 14) {
            this.documento = documento;
        } else {
            throw new Exception("Formato de CPF ou CNPJ inválidos!");
        }

        return documento;
    }

    public boolean temEstado() {
        return Optional.ofNullable(this.estadoId).isPresent();
    }
}
