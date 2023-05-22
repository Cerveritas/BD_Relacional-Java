package ExamenOrdinaria_1erTrimestre;

public class Asignatura {

    private int id;
    private String nombre;
    private double nota_examen;
    private double nota_practica;

    public Asignatura(int id, String nombre, double nota_examen, double nota_practica) {
        this.id = id;
        this.nombre = nombre;
        this.nota_examen = nota_examen;
        this.nota_practica = nota_practica;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getNota_examen() {
        return nota_examen;
    }


    public void setNota_examen(double nota_examen) {
        this.nota_examen = nota_examen;
    }

    public double getNota_practica() {
        return nota_practica;
    }

    public void setNota_practica(double nota_practica) {
        this.nota_practica = nota_practica;
    }

    @Override
    public String toString() {
        return "Asignatura{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nota_examen=" + nota_examen +
                ", nota_practica=" + nota_practica +
                '}';
    }
}
