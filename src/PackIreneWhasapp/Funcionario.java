package PackIreneWhasapp;

public abstract class Funcionario {

    protected int id;
    protected Categoria categoria;
    protected Nivel nivel;
    protected int anyo_experiencia;
    protected String fecha_contrato;
    protected double salario;

    public Funcionario(int id, Categoria categoria, Nivel nivel, int anyo_experiencia, String fecha_contrato) throws AccionIncorrecta {
        this.id = id;
        this.categoria = categoria;
        this.nivel = nivel;
        if (anyo_experiencia <= 0){
            throw new AccionIncorrecta("La experiencia no puede ser menor a 0");
        }
        this.anyo_experiencia = anyo_experiencia;
        this.fecha_contrato = fecha_contrato;
        this.salario = salario;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public int getAnyo_experiencia() {
        return anyo_experiencia;
    }

    public void setAnyo_experiencia(int anyo_experiencia) throws AccionIncorrecta {
        if (anyo_experiencia <= 0){
            throw new AccionIncorrecta("La experiencia no puede ser menor a 0");
        }
        this.anyo_experiencia = anyo_experiencia;
    }

    public String getFecha_contrato() {
        return fecha_contrato;
    }

    public void setFecha_contrato(String fecha_contrato) {
        this.fecha_contrato = fecha_contrato;
    }

    public double getSalario() {
        return salario;
    }


    public abstract double calcularSalario();






}
