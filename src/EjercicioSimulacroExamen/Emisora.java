package EjercicioSimulacroExamen;

public abstract class Emisora {

    //atributos


    protected int num_emisora;
    protected String nombre_emisora;
    protected boolean emitiendo;
    protected double beneficios;
    protected int num_oyentes;

    //método abstracto
    abstract double ganacias();


    //contructor
    public Emisora(int num_emisora, String nombre_emisora, int num_oyentes) throws AccionInvalida {
        this.num_emisora = num_emisora;
        this.nombre_emisora = nombre_emisora;


        if (num_oyentes < 0) {
            throw new AccionInvalida("El numero de oyentes no puede ser negativo");
        }
        this.num_oyentes = num_oyentes;
        this.emitiendo = false;
        this.beneficios = ganacias();
    }


    //getters and setters

    //getters


    public int getNum_emisora() {
        return num_emisora;
    }

    public String getNombre_emisora() {
        return nombre_emisora;
    }

    public boolean isEmitiendo() {
        return emitiendo;
    }

    public double getBeneficios() {
        return beneficios;
    }

    public int getNum_oyentes() {
        return num_oyentes;
    }

    //setters


    public void setNum_emisora(int num_emisora) {
        this.num_emisora = num_emisora;
    }

    public void setNombre_emisora(String nombre_emisora) {
        this.nombre_emisora = nombre_emisora;
    }

    public void setEmitiendo(boolean emitiendo) {
        this.emitiendo = emitiendo;
    }

    public void setNum_oyentes(int num_oyentes) {
        this.num_oyentes = num_oyentes;
        this.beneficios = ganacias();
    }


    @Override
    public String toString() {
        return "Emisora{" +
                "num_emisora=" + num_emisora +
                ", nombre_emisora='" + nombre_emisora + '\'' +
                ", emitiendo=" + emitiendo +
                ", beneficios=" + beneficios +
                ", num_oyentes=" + num_oyentes +
                '}';
    }
}
