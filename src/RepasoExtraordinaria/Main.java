package RepasoExtraordinaria;

import java.sql.*;
import java.util.ArrayList;
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
                       //Posicion posicion = Posicion.valueOf(sc.next());
                    String pos = sc.next().toUpperCase();

                    Jugador j2 = new Jugador(id, nombre, apellido, edad, anyo_experiencia, Posicion.valueOf(pos));
                    insertarRegistro(j2);
                    break;

                case 5:
                    System.out.print("Introduce el ID del jugador a consultar: ");
                    int identificador = sc.nextInt();
                    j1 = consultarJugador(identificador);
                    System.out.println(j1.toString());
                    break;

                case 6:
                    System.out.print("¿Cual es la posicion de los jugagores que desea almacenar en arrayList?: ");
                    String posicion = sc.next().toUpperCase();
                    insertar_arraylist(posicion);
                    break;

                case 7:
                    ordenar_arraylist();
                    break;

                case 8:
                    System.out.print("Indique el numero de jugadores a insertar: ");
                    int dimension = sc.nextInt();

                    Jugador[] listaJugador = new Jugador[dimension];


                    insertar_arrayestatico(listaJugador);
                    break;

                case 9:
                    mostrar_metadatos();
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
        ps.setDouble(6, j.getSalario());
        ps.setBoolean(7, j.isLesionado());
        ps.setString(8, String.valueOf(j.getPosi()));

        ps.executeUpdate();

        System.out.println("Registro insertado correctamente");



    }

    public static Jugador consultarJugador(int id) throws SQLException, AccionIncorrecta {

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM jugador WHERE id = ?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {



       j1 = new Jugador(rs.getInt(1) , rs.getString(2) , rs.getString(3), rs.getInt(4) , rs.getInt(5), Posicion.valueOf(rs.getString(8)));

        }

        return j1;
    }


    public static void insertar_arraylist(String posicion) throws SQLException, AccionIncorrecta {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM jugador WHERE id = ?");
        ps.setString(1, posicion);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            j1 = new Jugador(rs.getInt(1) , rs.getString(2) , rs.getString(3), rs.getInt(4) , rs.getInt(5), Posicion.valueOf(rs.getString(8)));
            lista_futbolistas.add(j1);
            }

        for (int i = 0; i < lista_futbolistas.size(); i++) {
            System.out.println(lista_futbolistas.get(i));
        }

        for (Jugador j1 : lista_futbolistas){
            System.out.println(j1.toString());
        }








        // TERMINAR ESTE EJERCICIO NO ME SALE CORRECTAMENTE
    }

    public static void ordenar_arraylist() throws SQLException, AccionIncorrecta {
        // sino me sale el anterior este no me va a salir
    }

    public static void insertar_arrayestatico(Jugador[] lista) throws SQLException, AccionIncorrecta {


        for (int i = 0; i < lista.length; i++) {
            System.out.println("Indique el id del jugador");
            int id = sc.nextInt();
            System.out.println("Indique el nombre del jugador");
            String nombre = sc.next();
            System.out.println("Indique el apellido del jugador");
            String apellido = sc.next();
            System.out.println("Indique la edad del jugador");
            int edad = sc.nextInt();
            System.out.println("Indique los años de experiencia del jugador");
            int anyo_experiencia = sc.nextInt();
            System.out.println("Indique la posicion del jugador");
            String posicion = sc.next();

            lista[i] = new Jugador(id, nombre, apellido, edad, anyo_experiencia, Posicion.valueOf(posicion));
        }

        PreparedStatement ps = conn.prepareStatement("INSERT INTO jugador VALUES (?,?,?,?,?,?,?,?)");

        for (int i = 0; i < lista.length; i++){
            ps.setInt(1, lista[i].getId());
            ps.setString(2, lista[i].getNombre());
            ps.setString(3, lista[i].getApellidos());
            ps.setInt(4, lista[i].getEdad());
            ps.setInt(5, lista[i].getAnyo_experiencia());
            ps.setDouble(6, lista[i].getSalario());
            ps.setBoolean(7, lista[i].isLesionado());
            ps.setString(8, String.valueOf(lista[i].getPosi()));

            ps.executeUpdate();

            System.out.println("Registro insertado correctamente");
        }
    }



    public static String mostrar_metadatos() throws SQLException {
        String cadena = null;


        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet resultados =  databaseMetaData.getTables("examen_ordinaria", "examen_ordinaria", null, null);

        while (resultados.next()){
            System.out.println(resultados.getString(3));
        }

        System.out.println("-------------------------");
        System.out.println("URL: "+databaseMetaData.getURL());
        System.out.println("SGBD: "+databaseMetaData.getDatabaseProductVersion());
        System.out.println("-------------------------");










        return cadena;
    }


    public static void imprimirArrayList() {

    }




}
