import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    static String query;
    public static Statement st;
    public static Connection conn;
    public static Scanner sc = new Scanner(System.in);
    public static Paciente p = null;
    public static ArrayList<Paciente> listado_pacientes = new ArrayList<>();
    public static void main(String[] args) throws SQLException {

        int opcion = 0;


        do {
            System.out.println("------------------------------------------");
            System.out.println("0 - Salir del programa");
            System.out.println("1 - Establecer conexion con el SGBD");
            System.out.println("2 - Crear la BD ut14");
            System.out.println("3 - Crear la tabla paciente");
            System.out.println("4 - Eliminar Base De Datos");
            System.out.println("5 - Borrar tabla paciente");
            System.out.println("6 - Insertar nuevo registro");
            System.out.println("7 - Eliminar registro");
            System.out.println("8 - Vaciar tabla");
            System.out.println("9 - Actualizar el numero de operaciones");
            System.out.println("10 - Consultar datos");
            System.out.println("11 - Insertar en BBDD a partir de una clase");
            System.out.println("12 - Almacenar los registros de la BBDD en un arrayList");
            System.out.println(" ");
            System.out.println("Introzca una opcion por favor");
            System.out.println("------------------------------------------");


            opcion = sc.nextInt();

            switch (opcion){
                case 0:
                    System.out.println("Gracias por usar el programa");
                    break;

                case 1:
                    establecer_conexion();
                    break;

                case 2:
                    crear_BD();
                    break;

                case 3:
                    crear_tabla();
                    break;

                case 4:
                    eliminar_BBDD();
                    break;

                case 5:
                    borrarTabla();
                    break;

                case 6:
                    insertarRegistro();
                    break;

                case 7:
                    eliminarRegistro();
                    break;

                case 8:
                    vaciarTabla();
                    break;

                case 9:
                    actualizarRegistro();
                    break;

                case 10:
                    consultarDatos();
                    break;

                case 11:
                    p = construirObjeto();
                    insertarObjeto(p);
                    break;

                case 12:
                    insertarArray();
                    break;

                default:
                    System.out.println("El numero introducido es incorrecto, intentelo de nuevo");
                    break;
            }

        } while (opcion != 0);


    }


    //METODO ESTABLECER CONEXION
    private static void establecer_conexion() throws SQLException {
        //conexion al SGBD

        String url="jdbc:mysql://localhost:3306/";
        String user= "root";
        String pwd="admin";
        conn= DriverManager.getConnection(url,user,pwd);
        System.out.println("Conexion establecida...");
    }


    // METODO CREAR BBDD Y ASIGNAR POR DEFECTO
    private static void crear_BD() throws SQLException {
        // crear la base de datos

        query = "create database ut14";
        st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("BD creada correctamente");
        asignar();




    }

    private static void asignar() throws SQLException {
        // asignar la base de datos por defecto

        query = "use ut14";
        st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("Asignacion realizada correctamente");
    }


    // CREAR TABLA
    private static void crear_tabla() throws SQLException {
        asignar();
        //crear la tabla

        query = "create table paciente ("+
                "dni varchar(9) primary key," +
                "nombre varchar(30) not null, "+
                "apellidos varchar(100), "+
                "n_operaciones int)";
        st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("Tabla creada correctamente");
    }


    // ELIMINAR BBDD

    private static void eliminar_BBDD() throws SQLException {
        asignar();
        query = "DROP DATABASE ut14";
        st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("Base de datos eliminada correctamente");
    }


    // ELIMINAR TABLA
    private static void borrarTabla() throws SQLException {
        asignar();

        query = "drop table paciente";
        st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("Tabla eliminada corretamente...");
    }


    // INSERTAR NUEVO REGIRO
    private static void insertarRegistro() throws SQLException {
        asignar();
        PreparedStatement ps = conn.prepareStatement("insert into paciente values (?,?,?,?)");
        System.out.println("Introduce el dni");
            String dni = sc.next();
        System.out.println("Introduce el nombre");
            String nombre = sc.next();
        System.out.println("Introduce el apellido");
            String apellido = sc.next();
        System.out.println("Introdudce el n_operaciones");
            int n_operaciones = sc.nextInt();

        ps.setString(1, dni);
        ps.setString(2, nombre);
        ps.setString(3, apellido);
        ps.setInt(4, n_operaciones);

        ps.executeUpdate();

        System.out.println("Registro metido correctamente");
    }

    // ELIMINAR REGISTRO
    private static void eliminarRegistro() throws SQLException{
        asignar();
        PreparedStatement ps = conn.prepareStatement("delete from paciente where dni=?");
        System.out.println("Introduce el dni del paciente para eliminarle del registro");
        String dni = sc.next();
        ps.setString(1, dni);
        ps.executeUpdate();
        System.out.println("Registro eliminado correctamente...");
    }


    // VACIAR TABLA
    private static void vaciarTabla() throws SQLException{
        asignar();
        Statement ps = conn.createStatement();
        query = "truncate paciente";
        st.executeUpdate(query);
        System.out.println("tabla vacia correctamente");
    }


    // ACTUALIZAR REGISTRO
    private static void actualizarRegistro() throws SQLException{
        asignar();
        PreparedStatement ps = conn.prepareStatement("update paciente set n_operaciones = ? where dni=?");
        System.out.println("Intoduzca el dni del paciente que quieres actualizar");
            String dni = sc.next();
        System.out.println("Introduzca el nuevo numero de operaciones");
            int n_opera = sc.nextInt();

        ps.setString(2, dni);
        ps.setInt(1, n_opera);
        ps.executeUpdate();

        System.out.println("Registro actualizado correctamente");
    }


    // IMPRIME POR PANTALLA EL REGISTRO DEL PACIENTE SEGUN EL DNI INTRODUCIDO
    private static void consultarDatos() throws SQLException{
        asignar();
        PreparedStatement ps = conn.prepareStatement("select * from paciente where dni = ?");
        System.out.println("Introduzca el dni del paciente a actualizar");
            String dni = sc.next();

        ps.setString(1, dni);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println("El paciente con dni "+rs.getString(1)+" y nombre: "+
                    rs.getString(2)+" y apellidos "+rs.getString(3)+
                    " tiene un total de "+rs.getString(4)+" operaciones");
        }
    }


    // CONSTRUIMOS UN OBJETO PARA METODO INSERTAR OBJETO
    private static Paciente construirObjeto() {
        //generar un objeto de la clase paciente a partir de datos introducidos por teclado

        System.out.println("Introduce el dni");
        String dni = sc.next();
        System.out.println("Introduce el nombre");
        String nombre = sc.next();
        System.out.println("Introduce el apellido");
        String apellido = sc.next();
        System.out.println("Introdudce el n_operaciones");
        int n_operaciones = sc.nextInt();

        Paciente p = new Paciente(dni, nombre, apellido, n_operaciones);

        return p;
    }


    // METODO PARA INSERTAR REGISTROS DESDE OTRA CLASE
    private static void insertarObjeto(Paciente p) throws SQLException {
        asignar();

        PreparedStatement ps = conn.prepareStatement("insert into paciente values (?,?,?,?)");
        ps.setString(1, Main.p.getDni());
        ps.setString(2, Main.p.getNombre());
        ps.setString(3, Main.p.getApellidos());
        ps.setInt(4, Main.p.getN_operaciones());

        ps.executeUpdate();

        System.out.println("Registro objeto insertado correctamente");
    }


    private static void insertarArray() throws SQLException{
        asignar();

        PreparedStatement ps = conn.prepareStatement("select * from paciente");
        ResultSet rs = ps.executeQuery();

        Paciente p = null;
        while (rs.next()){

            p = new Paciente(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            listado_pacientes.add(p);

        }



        for (Paciente e:listado_pacientes){
            System.out.println(e.toString());
        }






    }


}