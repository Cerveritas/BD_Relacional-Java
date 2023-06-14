package PackIrene;

import java.util.Date;

public abstract class Funcionario {

    protected int id;
    protected Categoria categoria;
    protected Nivel nivel;
    protected int anyo_experiencia;
    protected Date fecha_contrato;
    protected double salario;


    public Funcionario(int id, Categoria categoria, Nivel nivel, int anyo_experiencia, Date fecha_contrato) {
        this.id = id;
        this.categoria = categoria;
        this.nivel = nivel;
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

    public void setAnyo_experiencia(int anyo_experiencia) {
        this.anyo_experiencia = anyo_experiencia;
    }

    public Date getFecha_contrato() {
        return fecha_contrato;
    }

    public void setFecha_contrato(Date fecha_contrato) {
        this.fecha_contrato = fecha_contrato;
    }

    public double getSalario() {
        return salario;
    }


    public abstract double calcularSalario();













}
