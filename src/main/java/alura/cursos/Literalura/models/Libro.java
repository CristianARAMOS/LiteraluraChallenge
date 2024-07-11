package alura.cursos.Literalura.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;


    private String titulo;

    @ManyToOne
    private Autor autor;

    @Enumerated(EnumType.ORDINAL)
    private Lenguaje lenguaje;

    private Integer descargas;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Lenguaje getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(Lenguaje lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }
    public Libro(){}

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.tiutlo();
        this.lenguaje = Lenguaje.fromString(datosLibro.language().toString().split(",")[0].trim());
        this.descargas = datosLibro.descargas();
    }

    @Override
    public String toString() {
        String nombreAutor = (autor != null) ? autor.getNombre() : "Autor desconocido";
        return String.format("********** Libro **********%nTitulo:" +
                " %s%nAutor: %s%nLenguaje: %s%nNumero de Descargar:" +
                " %d%n***************************%n",titulo,nombreAutor,lenguaje,descargas);
    }
}
