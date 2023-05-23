package PracticaParaPracticarExamen;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import static PracticaParaPracticarExamen.Peliculas.conn;
import static PracticaParaPracticarExamen.Peliculas.p;

public class main  {

    static Scanner sc = new Scanner(System.in);
    static Peliculas pel = new Peliculas();



    public static void main(String[] args) throws SQLException {

        int opcion = 0;

        do {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("0 - Salir del programa.");
            System.out.println("1 - Crear tabla película.");
            System.out.println("2 - Eliminar tabla pelicula.");
            System.out.println("3 - Crear película.");
            System.out.println("4 - Eliminar película por ID.");
            System.out.println("5 - Buscar película por ID e imprimir la informacion.");
            System.out.println("6 - Buscar todas las peliculas e imprimir todas las peliculas.");
            System.out.println("7 - Buscar por género y por orden descendente de estreno e imprimir.");
            System.out.println("8 - SHOW DATABASE");
            System.out.println("9 - SHOW DATABASE TRADITIONAL");
            System.out.println("--------------------------------------------------------------------------------");
                                                                                                                        System.out.println(" ");
            System.out.print("ELIJA UNA OPCION: ");
            opcion = sc.nextInt();


            switch (opcion) {

                case 0:
                    System.out.println("Gracias por usar el programa...");
                    break;

                case 1:
                    pel.crearTabla();
                    break;

                case 2:
                    pel.eliminarTabla();
                    break;

                case 3:
                    pel.crearPelicula(p);
                    break;

                case 4:
                    pel.eliminarPelicula(p.id);
                    break;

                case 5:
                    Pelicula Pelicula = pel.buscarPelicula(p.id);
                    System.out.println(Pelicula);
                    break;

                case 6:
                    imprimirArrayList(pel.buscarTodo());
                    break;

                case 7:
                    imprimirArrayList(pel.buscarPorGeneroOrdenarEstreno(p.genero));
                    break;

                case 8:
                    mostrarBBDD();
                    break;

                case 9:
                    mostrarBBDDtraditional();
                    break;

                default:
                    System.out.println("El numero introducido es incorrecto, intentelo de nuevo...");
                    break;
            }
        } while (opcion != 0);
    }

    public static void imprimirArrayList(ArrayList<Pelicula> listadoPeliculas) {
        for (int i = 0; i < listadoPeliculas.size(); i++) {
            System.out.println(listadoPeliculas.get(i));
        }
    }

    public static void mostrarBBDD() throws SQLException {

       DatabaseMetaData databaseMetaData = conn.getMetaData();
       ResultSet rs = databaseMetaData.getCatalogs();

       while (rs.next()) {
           System.out.println(rs.getString(1));
       }
    }

    public static void mostrarBBDDtraditional() throws SQLException {

        String query = "SHOW DATABASES";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            System.out.println(rs.getString(1));
        }

    }







}
