package alura.cursos.Literalura.principal;

import alura.cursos.Literalura.models.*;
import alura.cursos.Literalura.repository.AutorRepository;
import alura.cursos.Literalura.repository.LibroRepository;
import alura.cursos.Literalura.service.ComsumoAPI;
import alura.cursos.Literalura.service.ConvierteDatos;

import javax.swing.*;
import java.util.*;

public class Principal {
    Scanner teclado = new Scanner(System.in);

    private ComsumoAPI comsumoAPI = new ComsumoAPI();

    private ConvierteDatos convierteDatos = new ConvierteDatos();

    private final String LINK_API = "https://gutendex.com/books/?search=";

    private List<DatosLibro> libros = new ArrayList<>();

    private LibroRepository repositorio;

    private AutorRepository autorRepositorio;

    private List<Libro> librosGuardados;

    private List<Autor> autoresGuardados;

    public Principal(LibroRepository repository,AutorRepository autorRepository){
        this.repositorio = repository;
        this.autorRepositorio = autorRepository;
    }

    public void  menu(){
        var option = -1;
        while (option != 0){
            String menu = """
                     Seleccione una de las siguientes opciones: 
                     1 - Buscar libro por nombre 
                     2 - Mostrar libros buscados
                     3 - Mostrar Autores Guardados
                     4 - Mostrar Libros por idioma
                     5 - Mostrar Autores Por Año
                     0 - Salir 
                    """;
            System.out.println(menu);
            option = teclado.nextInt();
            teclado.nextLine();
            switch (option){
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    mostraraLibros();
                    break;
                case 3:
                    mostrarAutores();
                    break;
                case 4:
                    mostrarLibrosPorIdioma();
                    break;
                case 5:
                    mostrarLibrosPorAñoDeAutor();
                    break;
                case 0:
                    System.out.println("Cerrando aplicacion......");
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;

            }

        }




    }

    private DatosLibros obtenerDatosLibro(){
        System.out.println("Ingresa el nombre del libro que desea buscar: ");
        var libroBusqueda = teclado.nextLine();
        var json = comsumoAPI.obtenerData(LINK_API + libroBusqueda.toLowerCase().replace(" ","+"));
        DatosLibros datosLibro = convierteDatos.obtenerData(json,DatosLibros.class);
        return datosLibro;
    }

    private void buscarLibro() {

        DatosLibros datosLibros = obtenerDatosLibro();
        if (!datosLibros.Libros().isEmpty()){
            DatosLibro datosLibro = datosLibros.Libros().get(0);
            DatosAutor datosAutor = datosLibro.autores().get(0);
            Libro libro = new Libro(datosLibro);

            Optional<Autor> autoresGuardados1 = autorRepositorio.findByNombreContainsIgnoreCase(datosAutor.nombre());
            if (autoresGuardados1.isPresent()){
                Autor autorEncontrado = autoresGuardados1.get();
                libro.setAutor(autorEncontrado);
                repositorio.save(libro);
                System.out.println(libro);
            }   else {
                Autor autor = new Autor(datosAutor);
                autorRepositorio.save(autor);
                libro.setAutor(autor);
                repositorio.save(libro);
                System.out.println(libro);
            }

        } else {
            System.out.println("Libro no encontrado intente con uno diferente");
        }



    }
    private void mostraraLibros(){
        librosGuardados = repositorio.findAll();

        librosGuardados.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(a-> System.out.println(
                                "******************** Libro ********************" + '\n' +
                                "Titulo= " + a.getTitulo() + '\n' +
                                "Autor= " + a.getAutor().getNombre()+ '\n' +
                                "Lenguaje= " + a.getLenguaje() + '\n' +
                                "Descargas= " + a.getDescargas()+ '\n' +
                                "***********************************************" ));
    }

    private void mostrarAutores() {
        autoresGuardados = autorRepositorio.findAll();

        autoresGuardados.stream()
                .sorted(Comparator.comparing(Autor::getNombre))
                .forEach(System.out::println);

    }

    private void mostrarLibrosPorIdioma() {
        var lenguaje = Lenguaje.fromEspañol("español");
        String menu = """
                Seleccione un idioma: 
                1 - Español
                2 - Ingles
                3 - Frances
                4 - Italiano 
                """;
        System.out.println(menu);
        var opcion = teclado.nextInt();
        switch (opcion){
            case 1:
                librosGuardados = repositorio.findByLenguaje(lenguaje);
                break;
            case 2:
                lenguaje = Lenguaje.fromEspañol("Inglés");
                librosGuardados = repositorio.findByLenguaje(lenguaje);
                break;
            case 3:
                lenguaje = Lenguaje.fromEspañol("Frances");
                librosGuardados = repositorio.findByLenguaje(lenguaje);
                break;
            case 4:
                lenguaje = Lenguaje.fromEspañol("Italiano");
                librosGuardados = repositorio.findByLenguaje(lenguaje);
                break;
        }
       librosGuardados.stream()
               .forEach(a-> System.out.println("******************** Libro ********************" + '\n' +
                       "Titulo= " + a.getTitulo() + '\n' +
                       "Autor= " + a.getAutor().getNombre()+ '\n' +
                       "Lenguaje= " + a.getLenguaje() + '\n' +
                       "Descargas= " + a.getDescargas()+ '\n' +
                       "***********************************************"));

    }
    private void mostrarLibrosPorAñoDeAutor() {
        System.out.println("Ingrese el año: ");
        Integer año = teclado.nextInt();
        autoresGuardados = autorRepositorio.buscarPorAnoVivo(año);

        autoresGuardados.stream()
                .forEach(System.out::println);

    }





}
