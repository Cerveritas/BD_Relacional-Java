import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Main {

    static String query;
    static Statement st;
    static Connection conn;
    public static void main(String[] args) throws SQLException {

        int opcion = 0;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("------------------------------------------");
            System.out.println("0 - Salir del programa");
            System.out.println("1 - Establecer conexion con el SGBD");
            System.out.println("2 - Crear la BD ut14");
            System.out.println("3 - Crear la tabla paciente");
            System.out.println("4 - Eliminar Base De Datos");
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

                default:
                    System.out.println("El numero introducido es incorrecto");
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



        // asignar la base de datos por defecto

        query = "use ut14";
        st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("Asignacion realizada correctamente");
    }


    // CREAR TABLA
    private static void crear_tabla() throws SQLException {
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
        query = "DROP DATABASE ut14";
        st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("Base de datos eliminada correctamente");
    }
}