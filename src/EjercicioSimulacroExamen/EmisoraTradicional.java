package EjercicioSimulacroExamen;

import java.sql.Struct;
import java.util.Locale;

public class EmisoraTradicional extends Emisora{

    final double GANANCIA_ONLINE = 0.023;



    private Frecuencia frecuencia;
    private double num_frecuencia;

    public EmisoraTradicional(int num_emisora, String nombre_emisora, int num_oyentes, String frecuencia, double num_frecuencia) throws AccionInvalida {
        super(num_emisora, nombre_emisora, num_oyentes);

        if (Frecuencia.AM.toString().equals(frecuencia.toUpperCase()) || Frecuencia.FM.toString().equals(frecuencia.toUpperCase())) {
            this.frecuencia = Frecuencia.valueOf(frecuencia);
        } else {
            throw new AccionInvalida("No ha introducido una frecuencia correcta");
        }

        this.num_frecuencia = num_frecuencia;
    }



    public Frecuencia getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Frecuencia frecuencia) {
        this.frecuencia = frecuencia;
    }

    public double getNum_frecuencia() {
        return num_frecuencia;
    }

    public void setNum_frecuencia(double num_frecuencia) {
        this.num_frecuencia = num_frecuencia;
    }

    @Override
    double ganacias() {
        return num_oyentes*ganacias();
    }


    @Override
    public String toString() {
        return "EmisoraTradicional{" +
                "GANANCIA_ONLINE=" + GANANCIA_ONLINE +
                ", frecuencia=" + frecuencia +
                ", num_frecuencia=" + num_frecuencia +
                ", num_emisora=" + num_emisora +
                ", nombre_emisora='" + nombre_emisora + '\'' +
                ", emitiendo=" + emitiendo +
                ", beneficios=" + beneficios +
                ", num_oyentes=" + num_oyentes +
                "} " + super.toString();
    }
}
