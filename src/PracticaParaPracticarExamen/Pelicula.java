package PracticaParaPracticarExamen;

public class Pelicula {

    protected int id;
    protected String titulo;
    protected String genero;
    protected int estreno;


    public Pelicula(int id, String titulo, String genero, int estreno) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.estreno = estreno;
    }

    public Pelicula() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEstreno() {
        return estreno;
    }

    public void setEstreno(int estreno) {
        this.estreno = estreno;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", genero=" + genero +
                ", estreno=" + estreno +
                '}';
    }
}
