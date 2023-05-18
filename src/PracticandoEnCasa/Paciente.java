package PracticandoEnCasa;

public class Paciente {

    // ATRIBUTOS

    private String dni;
    private String nombre;
    private String apellido;
    private int n_operaciones;


    // CONSTRUCTORES

    public Paciente(String dni, String nombre, String apellido, int n_operaciones) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.n_operaciones = n_operaciones;
    }



    // GETTERS AND SETTERS

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getN_operaciones() {
        return n_operaciones;
    }

    public void setN_operaciones(int n_operaciones) {
        this.n_operaciones = n_operaciones;
    }



    // MÉTODOS

    @Override
    public String toString() {
        return "Paciente{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", n_operaciones=" + n_operaciones +
                '}';
    }
}
