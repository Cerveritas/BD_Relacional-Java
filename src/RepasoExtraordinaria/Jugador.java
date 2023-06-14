package RepasoExtraordinaria;

//hereda de Seleccionado
public class Jugador extends Seleccionado  {

//atributos

    protected boolean lesionado;
    protected Posicion posi;


    //constructor
    public Jugador(int id, String nombre, String apellidos, int edad, int anyo_experiencia, Posicion posi) throws AccionIncorrecta {
        super(id, nombre, apellidos, edad, anyo_experiencia);
        this.lesionado = false;
        this.posi = posi;
        this.salario = calcular_salario();
    }



    //método lesionarse()
    public void lesionarse(){

        if (lesionado == false) {
            lesionado = true;
        }

    }
    //método jugarPartido()
    public void jugarPartido(){

        if (lesionado == true){
            throw new IllegalArgumentException("no puede jugar un jugador lesionado");
        }
    }
    //método que implementa calcular_salario()


    public double calcular_salario() {
        double salario = 0;
        if(this.getPosi()!= null){
            switch (this.posi) {
                case DEFENSA -> salario = 100000 + anyo_experiencia * 1000;
                case PORTERO -> salario = 200000 + anyo_experiencia * 2000;
                case DELANTERO -> salario = 300000 + anyo_experiencia * 500;
                case CENTROCAMPISTA -> salario = 150000 + anyo_experiencia * 800;
                default -> System.out.println("La posicion introducida es incorrecta");
            }
        }


        return salario;

    }

    //getter y setter


    public boolean isLesionado() {
        return lesionado;
    }

    public void setLesionado(boolean lesionado) {
        this.lesionado = lesionado;
    }

    public Posicion getPosi() {
        return posi;
    }

    public void setPosi(Posicion posi) {
        this.posi = posi;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "lesionado=" + lesionado +
                ", posi=" + posi +
                ", id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", edad=" + edad +
                ", anyo_experiencia=" + anyo_experiencia +
                ", salario=" + calcular_salario() +
                '}';
    }
}
