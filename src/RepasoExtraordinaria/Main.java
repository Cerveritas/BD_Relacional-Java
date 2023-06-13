package RepasoExtraordinaria;
import java.awt.*;
import java.nio.file.FileSystemNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Scanner;

public class Main {

    public static Connection conn;
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<Jugador> lista_futbolistas = new ArrayList<>();
    public static Jugador j1;


    public static void main(String[] args) throws SQLException, AccionIncorrecta {
        int opcion = 0;
        do {

            System.out.println(" -------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("0. Salir del programa");
            System.out.println("1. Conectar con el SGBD");
            System.out.println("2. Crear la base de datos y seleccionarla");
            System.out.println("3. Crear la tabla jugador en bbdd");
            System.out.println("4. Insertar registro en bbdd a partir de un objeto de la clase jugador introducido por teclado");
            System.out.println("5. Consultar jugador por id e almacenarlo en un objeto de la clase jugador, se mostrara por pantalla la info");
            System.out.println("6. Insertar jugadores de la tabla en un arraylist filtrando por la posicion indicada por teclado por el usuario. Se mostrara por pantalla la info");
            System.out.println("7. Ordenar arrayList por salario de manera descendente y por años de experiencia de manera ascendente. Se mostrara por pantalla la info.");
            System.out.println("8. Insertar en bd un array estatico de jugadores, se solicitara al usuario la dimension del array estatico a crear");
            System.out.println("9. Mostrar la URL de la conexion, la version del SGBD empleado");
            System.out.println(" -------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Introduzca una opcion:");
            System.out.println(" ");
            opcion = sc.nextInt();

            switch (opcion) {

                case 0:
                    System.out.println("Gracias por usar este programa...");
                    break;

                case 1:
                    conectarBD();
                    break;

                case 2:
                    crearBD();
                    break;

                case 3:
                    crearTabla();
                    break;

                case 4:

                    System.out.println("Introduzca el id del jugador: ");
                        int id = sc.nextInt();
                    System.out.println("Introduzca el nombre del jugador");
                        String nombre = sc.next();
                    System.out.println("Introduzca el apellido");
                        String apellido = sc.next();
                    System.out.println("Introduzca la edad");
                        int edad = sc.nextInt();
                    System.out.println("Introduzca los años de experiencia");
                        int anyo_experiencia = sc.nextInt();
                    System.out.println("Introduza la posicion del jugador [ PORTERO, DEFENSA, CENTROCAMPISTA, DELANTERO ]");
                       Posicion posicion = Posicion.valueOf(sc.next());


                    Jugador j2 = new Jugador(id, nombre, apellido, edad, anyo_experiencia, posicion);
                    insertarRegistro(j2);
                    break;

                case 5:

                    break;

                case 6:
                    break;

                case 7:
                    break;

                case 8:
                    break;

                case 9:
                    break;

                default:
                    System.out.println("EL numero introducido es incorrecto, intentelo de nuevo");
            }
        } while (opcion != 0);

    }



    public static void conectarBD() throws SQLException {

        String url="jdbc:mysql://localhost:3306/";
        String user= "root";
        String pwd="Myandroidop5";
        conn= DriverManager.getConnection(url,user,pwd);
        System.out.println("Conexión establecida...");
    }

    public static void crearBD() throws SQLException {

        String query = "CREATE DATABASE examen_ordinaria";
        Statement st = conn.createStatement();
        st.executeUpdate(query);

        query = "USE examen_ordinaria";
        st = conn.createStatement();
        st.executeUpdate(query);

        System.out.println("Base de datos creada y asignada correctamente");

    }

    public static void crearTabla() throws SQLException {

        String query = "CREATE TABLE jugador (" +
                "id INT PRIMARY KEY," +
                "nombre VARCHAR(20)," +
                "apellidos VARCHAR(30)," +
                "edad INT," +
                "anyo_experiencia INT," +
                "salario DOUBLE," +
                "lesionado BOOL," +
                "posi VARCHAR(50)" +
                ")";
        Statement st = conn.createStatement();
        st.executeUpdate(query);

        System.out.println("Tabla creada correctamente");

    }

    public static void insertarRegistro(Jugador j) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("INSERT INTO jugador VALUES (?,?,?,?,?,?,?,?)");

        ps.setInt(1, j.getId());
        ps.setString(2, j.getNombre());
        ps.setString(3, j.getApellidos());
        ps.setInt(4, j.getEdad());
        ps.setInt(5, j.getAnyo_experiencia());
        ps.setString(8, String.valueOf(j.getPosi()));

        ps.executeUpdate();

        System.out.println("Registro insertado correctamente");



    }

    public static Jugador consultarJugador(int id) throws SQLException, AccionIncorrecta {

        return j1;
    }


    public static void insertar_arraylist(String posicion) throws SQLException, AccionIncorrecta {

    }

    public static void ordenar_arraylist() throws SQLException, AccionIncorrecta {

    }

    public static void insertar_arrayestatico(Jugador[] lista) throws SQLException {


    }



    public static String mostrar_metadatos() throws SQLException {
        String cadena = null;

        return cadena;
    }


    public static void imprimirArrayList() {

    }




}
