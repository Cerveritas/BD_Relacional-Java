package ExamenTercerTrimestre;
//hereda de inmueble
public class Hotel extends Inmueble  {
    //atributo
    boolean isPetFriendly;

    //constructor

    public Hotel(int identificador, String nombre, int anyo_edificacion, String direccion, double m2, boolean isPetFriendly) throws AccionInvalida {
        super(identificador, nombre, anyo_edificacion, direccion, m2);
        this.isPetFriendly = isPetFriendly;

    }


    //getter y setter

    public boolean isPetFriendly() {
        return isPetFriendly;
    }

    public void setPetFriendly(boolean petFriendly) {

       isPetFriendly = petFriendly;
    }


    //tostring

    @Override
    public String toString() {
        return "Hotel{" +
                ", identificador=" + identificador +
                ", nombre='" + nombre + '\'' +
                ", anyo_edificacion=" + anyo_edificacion +
                ", direccion='" + direccion + '\'' +
                ", ibi=" + calcular_ibi() +
                ", m2=" + m2 +
                "admite mascotas=" + isPetFriendly +
                '}';
    }


//calcular ibi

    public double calcular_ibi() {
        int edad_edificio = 2023 - anyo_edificacion;
        return (m2 * 100) + (50 * edad_edificio);
    }

}
