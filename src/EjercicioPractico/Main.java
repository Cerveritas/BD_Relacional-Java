
package EjercicioPractico;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static Jugador[] array_estatico_jugadores;
    public static ArrayList<Jugador> array_dinamico_jugadores = new ArrayList<>();
    public static Jugador j = null;
public static Connection conn;

    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);


        int opcion = 0;
        int dorsal = 0;



        do {
            System.out.println("------------------------------------------");
            System.out.println("0 - Salir del programa");
            System.out.println("1 - Crear Base De Datos EjercicioPractico");
            System.out.println("2 - Crear tabla jugador");
            System.out.println("3 - Vaciar tabla jugador");
            System.out.println("4 - Eliminar tabla");
            System.out.println("5 - Insertar jugador ");
            System.out.println("6 - Buscar jugador por dorsal y almacenamos en un objeto jugador e imprimimos ese objeto");
            System.out.println("7 - Insertar registros en un array estático");
            System.out.println("8 - Insertar registros en un array dinámico");
            System.out.println("9 - Mostrar las tablas de nuestra base de datos");
            System.out.println("Introzca una opcion por favor");
            System.out.println("------------------------------------------");


            opcion = sc.nextInt();

            switch (opcion) {
                case 0:
                    System.out.println("Gracias por usar el programa");
                    break;

                case 1:
                    crear_BBDD();
                    break;

                case 2:
                    crear_tabla();
                    break;

                case 3:
                    vaciar_tabla();
                    break;

                case 4:
                    eliminarTabla();
                    break;

                case 5:
                        System.out.println("introduzca el dni");
                            String dni = sc.next();
                        System.out.println("introduzca nombre");
                            String nombre = sc.next();
                        System.out.println("introduzca dorsal camiseta");
                            dorsal = sc.nextInt();
                        System.out.println("introduzca el salario");
                            double salario = sc.nextDouble();
                        System.out.println("introduzca la edad");
                            int edad = sc.nextInt();

                        Jugador j1 = new Jugador(dni, nombre, dorsal, salario, edad);

                    insertar_jugador(j1);
                    break;

                case 6:
                        System.out.println("Introduzca el dorsal del jugador a buscar en la BBDD");
                            dorsal = sc.nextInt();
                        Jugador j2 = buscarPorDorsal(dorsal);
                    System.out.println(j2.toString());
                    break;

                case 7:
                    array_estatico_jugadores =  almacenar_array_estatico();
                    System.out.println(array_estatico_jugadores.length);
                    System.out.println(Arrays.toString(array_estatico_jugadores));
                    break;

                case 8:
                    array_dinamico_jugadores = almacenar_array_dinamico();
                    for (Jugador p : array_dinamico_jugadores
                         ) {
                        System.out.println(p.toString());
                    }
                    break;

                case 9:
                    //mostrar_tablaMETADATO();
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
    }



    // MÉTODO PARA CREAR UNA BBDD
    private static void crear_BBDD() throws SQLException {
        establecer_conexion();

        String query = "create database  EjercicioPracticoJugadores";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("Base De Datos creada correctamente...");
        asignar_bd();
    }


    // MÉTODO PARA CREAR TABLA
    private static void crear_tabla() throws SQLException {
        asignar_bd();

        String query = "create table jugador (" +
                "dni varchar(9) not null primary key," +
                "nombre varchar(100)," +
                "dorsal_camiseta int," +
                "salario double," +
                "edad int" +
                ") ";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("Tabla jugador creada correctamente...");

    }


    // MÉTODO PARA VACIAR LA TABLA JUGADOR
    private static void vaciar_tabla() throws SQLException {
        String query = "truncate jugador";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("Tabla jugador vaciada correctamente...");
    }

    // MÉTODO PARA ELIMINAR LA TABLA JUGADOR

    private static void eliminarTabla() throws SQLException {
        String query = "drop table jugador";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("Tabla jugador eliminada correctamente...");
    }

    // MÉTODO PARA INSERTAR JUGADOR EN LA TABLA JUGADOR
    private static void insertar_jugador(Jugador j1) throws SQLException {
        asignar_bd();
        PreparedStatement ps = conn.prepareStatement("insert into jugador values (?,?,?,?,?)");
        ps.setString(1, j1.getDni());
        ps.setString(2, j1.getNombre());
        ps.setInt(3, j1.getDorsal_camiseta());
        ps.setDouble(4, j1.getSalario());
        ps.setInt(5, j1.getEdad());

        ps.executeUpdate();
    }


    // MÉTODO PARA BUSCAR JUGADOR POR EL NUMERO DE DORSAL
    private static Jugador buscarPorDorsal(int dorsal) throws SQLException {
        establecer_conexion();
        asignar_bd();

        Jugador j = null;
        PreparedStatement ps = conn.prepareStatement("select * from jugador where dorsal_camiseta = ?");
        ps.setInt(1, dorsal);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
           j = new Jugador(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getInt(5));
        }

        return j;
    }


    // MÉTODO PARA ALMACENAR LOS JUGADORES EN UN ARRAY ESTÁTICO
    private static Jugador[] almacenar_array_estatico() throws SQLException {
        establecer_conexion();
        asignar_bd();

        PreparedStatement ps = conn.prepareStatement("select count(*) from jugador");
        ResultSet rs = ps.executeQuery();

        int dimension = 0;

        while (rs.next()){
            dimension = rs.getInt(1);
        }

        array_estatico_jugadores = new Jugador[dimension];


        ps = conn.prepareStatement("select * from jugador");
        rs = ps.executeQuery();
        int i = 0;

        while (rs.next()){
            j = new Jugador(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getInt(5));
            array_estatico_jugadores[i] = j;
            i++;
        }
        return array_estatico_jugadores;
    }


    // MÉTODO PARA ALMACENAR LOS JUGADORES EN UN ARRAY DINÁMICO
    private static ArrayList<Jugador> almacenar_array_dinamico() throws SQLException {
        establecer_conexion();
        asignar_bd();

        PreparedStatement ps = conn.prepareStatement("select * from jugador");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            j = new Jugador(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getInt(5));
            array_dinamico_jugadores.add(j);
        }
        return  array_dinamico_jugadores;
    }

/*
    private static void mostrar_tablaMETADATO() throws SQLException {
        establecer_conexion();
        asignar_bd();

        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet resultados =  databaseMetaData.getTables();

        while (resultados.next()){
            System.out.println(resultados.getString(1));
        }
    }

*/






    // método para asignar
    private static void asignar_bd() throws SQLException {
        String query = "use EjercicioPracticoJugadores";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
    }

}
