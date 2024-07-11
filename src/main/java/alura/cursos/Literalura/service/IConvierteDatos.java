package alura.cursos.Literalura.service;

public interface IConvierteDatos {
    <T> T obtenerData(String json, Class<T> clase);
}
