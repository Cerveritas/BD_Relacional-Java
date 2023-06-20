package AntesExamen;

public class Profesorete extends  Funcionario {


    protected String ies;
    protected Asignatura asignatura;

    public Profesorete(int id, String nombre, String apellidos, int edad, String ies, Asignatura asignatura) {
        super(id, nombre, apellidos, edad);
        this.ies = ies;
        this.asignatura = asignatura;
    }

    public String getIes() {
        return ies;
    }

    public void setIes(String ies) {
        this.ies = ies;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }




    public double obtener_salario(double salario) {
        salario = 0;

        switch (asignatura) {
            case INGLES:
                salario = 100 + 2000 * 2;
                break;

            case LENGUA:
                salario = 200 + 2000 * 2;
                break;

            case SOCIALES:
                salario = 300 + 2000 * 2;
                break;

            case NATURALES:
                salario = 400 + 2000 * 2;
                break;

            case MATEMATICAS:
                salario = 500 + 2000 * 2;
                break;

            default:
                System.out.println("La asignatura introducida es incorrecta");
        }


        return salario;
    }
}
