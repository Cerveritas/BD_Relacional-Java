package ExamenTercerTrimestre;

//no se puede instanciar objetos
public abstract class Inmueble {

    //atributos
    protected int identificador;
    protected String nombre;
    protected int anyo_edificacion;
    protected String direccion;
    protected double ibi;
    protected double m2;



    //constructor


    public Inmueble(int identificador, String nombre, int anyo_edificacion, String direccion, double m2) throws AccionInvalida {
        this.identificador = identificador;
        this.nombre = nombre;
            if (anyo_edificacion <= 0) {
                throw new AccionInvalida("No puede ser valor negativo");
            }
        this.anyo_edificacion = anyo_edificacion;
        this.direccion = direccion;
        if (m2 <= 0) {
            throw new AccionInvalida("No puede ser valor negativo");
        }
        this.m2 = m2;
        this.ibi = calcular_ibi();
    }

    //metodo que implementa Hotel
    public abstract double calcular_ibi();



    //setter getter


    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnyo_edificacion() {
        return anyo_edificacion;
    }

    public void setAnyo_edificacion(int anyo_edificacion) throws AccionInvalida {
        if (anyo_edificacion <= 0) {
            throw new AccionInvalida("No puede ser valor negativo");
        }
        this.anyo_edificacion = anyo_edificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getIbi() {
        return ibi;
    }


    public double getM2() {
        return m2;
    }

    public void setM2(double m2) throws AccionInvalida {
        if (anyo_edificacion <= 0) {
            throw new AccionInvalida("No puede ser valor negativo");
        }
        this.m2 = m2;
    }
}
