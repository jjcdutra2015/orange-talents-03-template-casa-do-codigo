package br.com.zupacademy.juliodutra.casadocodigo;

import br.com.zupacademy.juliodutra.casadocodigo.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
}