package PackIrene;

import java.util.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;



public class Profesor extends Funcionario {


    LocalDate fechaActual = LocalDate.now();
    protected String ies;
    protected Grado grado;

    public Profesor(int id, Categoria categoria, Nivel nivel, int anyo_experiencia, Date fecha_contrato, String ies, Grado grado) {
        super(id, categoria, nivel, anyo_experiencia, fecha_contrato);
        this.ies = ies;
        this.grado = grado;
    }


    public String getIes() {
        return ies;
    }

    public void setIes(String ies) {
        this.ies = ies;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }


    public double calcularSalario() {

        double salario = 0;

        switch (categoria) {



            case A:
                int nivel_num = 0;
                int grado_num = 0;

                if (nivel.equals(Nivel.uno)){
                    nivel_num = 800;
                } else if (nivel.equals(Nivel.dos)) {
                    nivel_num = 500;
                } else if (nivel.equals(Nivel.tres)) {
                    nivel_num = 300;
                }


                if (grado.equals(Grado.BASICO)){
                    grado_num = 500;
                } else if (grado.equals(Grado.MEDIO)) {
                    grado_num = 200;
                } else if (grado.equals(Grado.SUPERIOR)) {
                    grado_num = 100;
                }


/*
                salario = 1000 * anyo_experiencia + 800 * nivel_num + fechaActual - fecha_contrato * 50 + grado;*/
                break;






            case B:
                salario = 800;
                break;

            case C:
                salario = 600;
                break;

            default:
                System.out.println("La opcion introducida es incorrecta");
        }


        return salario;


    }
}
