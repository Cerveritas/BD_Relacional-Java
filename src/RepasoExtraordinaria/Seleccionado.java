package RepasoExtraordinaria;

//no se puede instanciar objetos
public abstract class Seleccionado {
    //atributos

    protected int id;
    protected String nombre;
    protected String apellidos;
    protected int edad;
    protected int anyo_experiencia;
    protected double salario;




    // constructor parametrizado


    public Seleccionado(int id, String nombre, String apellidos, int edad, int anyo_experiencia) throws AccionIncorrecta {
            this.id = id;
            this.nombre = nombre;
            this.apellidos = apellidos;
        if (edad <= 0){
            throw new AccionIncorrecta("la edad o experiencia no puede ser menor a 0");
        }
            this.edad = edad;
        if (anyo_experiencia <= 0){
            throw new AccionIncorrecta("la edad o experiencia no puede ser menor a 0");
        }
            this.anyo_experiencia = anyo_experiencia;
            this.salario = calcular_salario();
    }

    //método calcular_salario implementado por hija
    public abstract double calcular_salario();


    //getter setter


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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) throws AccionIncorrecta {
        if (edad <= 0){
            throw new AccionIncorrecta("la edad o experiencia no puede ser menor a 0");
        }
        this.edad = edad;
    }

    public int getAnyo_experiencia() {
        return anyo_experiencia;
    }

    public void setAnyo_experiencia(int anyo_experiencia) throws AccionIncorrecta {
        if (edad <= 0){
            throw new AccionIncorrecta("la edad o experiencia no puede ser menor a 0");
        }
        this.anyo_experiencia = anyo_experiencia;
    }

    public double getSalario() {
        return salario;
    }

}
