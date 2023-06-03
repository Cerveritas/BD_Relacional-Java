package ExamenTercerTrimestre;

import EjercicioSimulacroExamen.EmisoraOnline;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main  {

    public static Connection conn;
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<Hotel> lista_hoteles= new ArrayList<>();
    public static Hotel h1;
    public static Hotel[] array_estatico_hoteles;
    public static void main(String[] args) throws SQLException, AccionInvalida {




        int opcion=0;
        do{

            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("0 - Salir del programa");
            System.out.println("1 - Conectar con el SGBD");
            System.out.println("2 - Crear la base de datos y seleccionarla");
            System.out.println("3 - Crear tabla hotel en bbdd");
            System.out.println("4 - Insertar registro en bbdd a partir de un objeto de la clase hotel introducido por parametro de entrada");
            System.out.println("5 - Consultar por hoteles friendly en bd y añadirlos a un arraylist ordenado de mayor a menor segun pague ibi");
            System.out.println("6 - Imprimir arraylist");
            System.out.println("7 - Mostrar campos de la tabla hotel");
            System.out.println("8 - Insertar registros de la tabla hotel en un array estatico");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                                                                                                                        System.out.println(" ");
            System.out.println("Introduce una opcion:");

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

                    System.out.println("Introduzca el identidicador");
                        int id = sc.nextInt();
                    System.out.println("Introduzca el nombre del hotel");
                        String nombre = sc.next();
                    System.out.println("Introduzca el año de edificacion del hotel");
                        int anyo_edificacion = sc.nextInt();
                    System.out.println("Introduzca la direccion del hotel");
                        String direccion = sc.next();
                    System.out.println("Introduzca los m2 del hotel");
                        double m2 = sc.nextDouble();
                    System.out.println("¿Admite mascotas? S o N");
                        boolean admiteMascotas = sc.nextBoolean();


                        h1 = new Hotel(id, nombre, anyo_edificacion, direccion, m2, admiteMascotas);



                    insertarRegistro(h1);
                    break;

                case 5:
                    filtro_hoteles();
                    break;

                case 6:
                    System.out.println(imprimirArrayList());
                    break;

                case 7:

                    System.out.println(mostrar_columnas());
                    break;

                case 8:
                    insertar_array_estatico();
                    imprimirarray_estatico();
                    break;

                default:
                    System.out.println("EL numero introducido es incorrecto, intentelo de nuevo");
            }
        }while (opcion!=0);

    }



    public static void conectarBD() throws SQLException {

        String url="jdbc:mysql://localhost:3306/";
        String user= "root";
        String pwd="Myandroidop5";
        conn= DriverManager.getConnection(url,user,pwd);
        System.out.println("Conexión establecida...");

    }

    public static void crearBD() throws SQLException {

        String query = "CREATE DATABASE examen_tercera";
        Statement st = conn.createStatement();
        st.executeUpdate(query);


        System.out.println("Base de datos creada correctamente...");
        asignarBBDD();


    }


    public static void crearTabla() throws SQLException {
        conectarBD();
        asignarBBDD();

        String query = "CREATE TABLE hotel (" +
                "identificador INT," +
                "nombre VARCHAR(50)," +
                "anyo_edificacion INT," +
                "direccion VARCHAR(100)," +
                "ibi DOUBLE," +
                "m2 DOUBLE," +
                "mascotas BOOLEAN" +
                ");";
        Statement st = conn.createStatement();
        st.executeUpdate(query);

        System.out.println("Tabla creada correctamente...");


    }


    public static void insertarRegistro(Hotel h1) throws SQLException {
        conectarBD();
        asignarBBDD();

        PreparedStatement ps = conn.prepareStatement("INSERT INTO hotel VALUES (?,?,?,?,?,?,?)");
        ps.setInt(1, h1.identificador);
        ps.setString(2, h1.nombre);
        ps.setInt(3, h1.anyo_edificacion);
        ps.setString(4, h1.direccion);
        ps.setDouble(5, h1.ibi);
        ps.setDouble(6, h1.m2);
        ps.setBoolean(7, h1.isPetFriendly);

        ps.executeUpdate();

        System.out.println("Registro insertado correctamente");
    }

    public static void filtro_hoteles() throws SQLException, AccionInvalida {
        conectarBD();
        asignarBBDD();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM hotel WHERE mascotas = 1 ORDER BY ibi DESC");
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {

            rs.getInt("identificador");
            rs.getString("nombre");
            rs.getInt("anyo_edificacion");
            rs.getString("direccion");
            rs.getDouble("ibi");
            rs.getDouble("m2");
            rs.getBoolean("mascotas");

            h1 = new Hotel(rs.getInt("identificador"), rs.getString("nombre"), rs.getInt("anyo_edificacion"), rs.getString("direccion"), rs.getDouble("m2"), rs.getBoolean("mascotas"));
            lista_hoteles.add(h1);
        }
        System.out.println("ArrayList creado y ordenado correctamente");
    }

    public static String imprimirArrayList() throws SQLException {
        conectarBD();
        asignarBBDD();

        String resultado = "";

            for (Hotel h1 : lista_hoteles) {
                resultado +=  h1 + "\n";
            }
        return resultado;
    }


    public static String mostrar_columnas() throws SQLException {
        conectarBD();
        asignarBBDD();

        String cadena="";

        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet rs = databaseMetaData.getColumns("examen_tercera", "examen_tercera", "hotel", null);

        while (rs.next()) {
            cadena += rs.getString("COLUMN_NAME") + " - " + rs.getString("TYPE_NAME") + "\n";
        }
        return cadena;
    }

    public static void insertar_array_estatico() throws SQLException, AccionInvalida {
        conectarBD();
        asignarBBDD();

        PreparedStatement ps = conn.prepareStatement("select count(*) from hotel");
        ResultSet rs = ps.executeQuery();

        int dimension = 0;
        while (rs.next()) {

            dimension = rs.getInt(1);
        }

        array_estatico_hoteles = new Hotel[dimension];

         ps = conn.prepareStatement("select * from hotel");
         rs = ps.executeQuery();

         int i = 0;
        while (rs.next()) {

            Hotel e = new Hotel(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDouble(6), rs.getBoolean(7));
            array_estatico_hoteles[i] = e;
            i++;
        }
    }


    private static void imprimirarray_estatico() {

        for (int j = 0; j < array_estatico_hoteles.length; j++){
            System.out.println(array_estatico_hoteles[j]);
        }
    }



    private static void asignarBBDD() throws SQLException {
        String query = "USE examen_tercera";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("Base de datos asignada");
    }
}