/*
package EjercicioPractico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Main {
public static Connection conn;

    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);


        int opcion = 0;


        do {
            System.out.println("------------------------------------------");
            System.out.println("0 - Salir del programa");
            System.out.println("1 -  Crear Base De Datos EjercicioPractico");
            System.out.println("2 - Crear tabla jugador");
            System.out.println("3 - ");
            System.out.println("4 - ");

            System.out.println("Introzca una opcion por favor");
            System.out.println("------------------------------------------");


            opcion = sc.nextInt();

            switch (opcion) {
                case 0:
                    System.out.println("Gracias por usar el programa");
                    break;

                case 1:
                    establecer_conexion();
                    break;

                case 2:
                    crear_BBDD();
                    break;

                case 3:
                    crear_tabla();
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 6:
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



    // MÉTODO PARA CREAR UNA BBDD
    private static void crear_BBDD() throws SQLException {

        String query = "create database ut14";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("BD creada correctamente");
    }

/*
    private static void crear_tabla() throws SQLException {

        String query = "create table "



    }


   */


    /*/


}

*/