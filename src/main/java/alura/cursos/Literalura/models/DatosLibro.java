package alura.cursos.Literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
    @JsonAlias("title")
    String tiutlo,

    @JsonAlias("authors")
    List<DatosAutor>  autores,
    @JsonAlias("languages")
    List<String> language,

    @JsonAlias("download_count")
    Integer descargas

) {
}