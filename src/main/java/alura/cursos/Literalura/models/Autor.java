package alura.cursos.Literalura.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;

    private Integer año_nacimiento;

    private  Integer año_muerte;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libro;

    public Autor(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAño_nacimiento() {
        return año_nacimiento;
    }

    public void setAño_nacimiento(Integer año_nacimiento) {
        this.año_nacimiento = año_nacimiento;
    }

    public Integer getAño_muerte() {
        return año_muerte;
    }

    public void setAño_muerte(Integer año_muerte) {
        this.año_muerte = año_muerte;
    }

    public List<Libro> getLibro() {
        return libro;
    }

    public void setLibro(List<Libro> libro) {
        this.libro = libro;
    }

    public Autor(DatosAutor autor) {
        this.nombre = autor.nombre();
        this.año_nacimiento = autor.año_nacimiento();
        this.año_muerte = autor.año_muerte();
    }

    @Override
    public String toString() {
        StringBuilder librosFormat = new StringBuilder();
        librosFormat.append("Libros: ");

        for(int i = 0; i < libro.size() ; i++) {
            librosFormat.append(libro.get(i).getTitulo());
            if (i < libro.size() - 1 ){
                librosFormat.append(", ");
            }
        }
        return String.format("********** Autor **********%nNombre:" +
                " %s%n%s%nAño de Nacimiento: %s%nAño de Muerte:" +
                " %s%n***************************%n",nombre, librosFormat,año_nacimiento,año_muerte);
    }

}
