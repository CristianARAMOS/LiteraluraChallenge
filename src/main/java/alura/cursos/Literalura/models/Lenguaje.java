package alura.cursos.Literalura.models;

public enum Lenguaje {
    en("[en]", "Inglés"),
    es("[es]", "Español"),
    fr("[fr]", "Frances"),
    pt("[pt]", "Portugues"),
    it("[it]", "Italiano"),
    hu("[hu","Hungaro");

    private String idiomaGutndx;

    private String idiomaEnEspañol;

    Lenguaje(String idiomaGutndx , String idiomaEnEspañol){
        this.idiomaGutndx = idiomaGutndx;
        this.idiomaEnEspañol = idiomaEnEspañol;
    }

    public static Lenguaje fromString(String idioma){
        for (Lenguaje lenguaje : Lenguaje.values()){
            if(lenguaje.idiomaGutndx.equalsIgnoreCase(idioma)){
                return lenguaje;
            }
        }
        throw new IllegalArgumentException("Ningun lenguaje encontrado" + idioma);
    }

    public static Lenguaje fromEspañol(String idioma){
        for (Lenguaje lenguaje : Lenguaje.values()){
            if(lenguaje.idiomaEnEspañol.equalsIgnoreCase(idioma)){
                return lenguaje;
            }
        }
        throw new IllegalArgumentException("Ningun lenguaje encontrado" + idioma);
    }

    public String getIdiomaGutndx() {
        return idiomaGutndx;
    }

    public String getIdiomaEnEspañol() {
        return idiomaEnEspañol;
    }
}
