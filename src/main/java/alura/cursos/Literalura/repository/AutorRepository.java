package alura.cursos.Literalura.repository;

import alura.cursos.Literalura.models.Autor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNombreContainsIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.año_nacimiento <= :año AND a.año_muerte >= :año")
    List<Autor> buscarPorAnoVivo(Integer año);


}