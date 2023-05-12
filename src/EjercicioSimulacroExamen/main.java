/*
package EjercicioSimulacroExamen;

import EjercicioPractico.Jugador;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class main {

    public static Scanner sc = new Scanner(System.in);
    public static Emisora[] listado_emisoras;
    public static ArrayList<Emisora> arrayList_emisoras = new ArrayList<>();
    public static Connection conn = null;


    public static void main(String[] args) throws SQLException {

        int opcion = 0;


        do {
            System.out.println("------------------------------------------");
            System.out.println("0 - Salir del programa");
            System.out.println("1 - Crear Base De Datos SimulacroExamen");
            System.out.println("2 - Crear tabla emisoraOnline");
            System.out.println("3 - Insertar emisora online");
            System.out.println("4 - Buscar emisora por URL introducida por teclado y añadirlo a un array estatico");
            System.out.println("5 - Almacenar en el arraylist emisoras con beneficios superiores a 4000€");
            System.out.println("6 - Sacar la version del SGBD");
            System.out.println("Introzca una opcion por favor");
            System.out.println("------------------------------------------");


            opcion = sc.nextInt();

            switch (opcion) {
                case 0:
                    System.out.println("Gracias por usar el programa");
                    break;

                case 1:
                    crear_bd();
                    break;

                case 2:
                    crear_tabla_emisora();
                    break;

                case 3:
                    //crear un objeto de la clase emisora
                    insertar_emisora(e);
                    break;

                case 4:
                    buscar_añadir_array_estatico(url);
                    break;

                case 5:
                    añadir_arraylist_beneficios();
                    break;

                case 6:
                    String versionSGBD = sacar_version_SGBD();
                    System.out.println("La version SGBD es" +versionSGBD);
                    break;


                default:
                    System.out.println("El numero introducido es incorrecto, intentelo de nuevo");
                    break;
            }

        } while (opcion != 0);
    }

    // METODO 1
    public static void crear_bd() throws SQLException {
        String query = "create database simlacroexamen";
        Statement st = conn.createStatement();
        st.executeUpdate(query);

        System.out.println("Base de datos creada correctamente...");
    }

    // METODO 2
    private static void crear_tabla_emisora() throws SQLException {

        String query = "create table emisoraOnline (" +
                "num_emisora int not null primary key," +
                "nombre varchar(100) not null," +
                "emitiendo boolean," +
                "beneficios double," +
                "num_oyentes int," +
                "url varchar(100)" +
                ") ";



        Statement st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("Tabla emisora creada correctamente...");

    }



    // METODO 6
    public static String sacar_version_SGBD() throws SQLException {

        DatabaseMetaData databaseMetaData = conn.getMetaData();
        return  databaseMetaData.getDatabaseProductVersion();
    }




}
*/