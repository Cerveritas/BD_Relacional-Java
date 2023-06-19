package PackIreneWhasapp;

import java.time.LocalDate;
import java.util.Date;

public class Profesor extends Funcionario{

    protected String IES;
    protected boolean activo;
    protected Grado grado;


    public Profesor(int id, Categoria categoria, Nivel nivel, int anyo_experiencia, String fecha_contrato, String IES, boolean activo, Grado grado) throws AccionIncorrecta {
        super(id, categoria, nivel, anyo_experiencia, fecha_contrato);
        this.IES = IES;
        this.activo = activo;
        this.grado = grado;
    }


    public String getIES() {
        return IES;
    }

    public void setIES(String IES) {
        this.IES = IES;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }


    public double calcularSalario() {

        double salario = 0;

        LocalDate fechaActual = LocalDate.now();

        /*

        int dias_trabajados = fechaActual - ;

        switch (categoria){
            case A:
                salario = categoria * anyo_experiencia + nivel * 800 + dias_trabajados * 50 + grado;
                break;

            case B:
                break;

            case C:
                break;

            default:
                System.out.println("La categoria introducida es incorrecta, intetelo de nuevo");
        }



*/
        return salario;

    }

    @Override
    public String toString() {
        return "Profesor{" +
                "grado='" + grado + '\'' +
                ", IES=" + IES +
                ", activo=" + activo +
                ", id=" + id +
                ", categoria=" + categoria +
                ", nivel=" + nivel +
                ", anyo_experiencia=" + anyo_experiencia +
                ", fecha_contrato='" + fecha_contrato + '\'' +
                ", salario=" + salario +
                "} " + super.toString();
    }
}
