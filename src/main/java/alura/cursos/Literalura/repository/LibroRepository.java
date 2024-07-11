package alura.cursos.Literalura.repository;

import alura.cursos.Literalura.models.Lenguaje;
import alura.cursos.Literalura.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {
        List<Libro> findByLenguaje(Lenguaje lenguaje);

        @Query("SELECT l FROM Libro l WHERE l.descargas >= :descgs")
        List<Libro> buscarPorTitulo(Integer descgs);

}
