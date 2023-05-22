package ExamenOrdinaria_1erTrimestre;

import com.sun.source.doctree.SinceTree;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Scanner;

public class main2 {


    static double totalProgramacion;
    static double totalBaseDatos;
    static double totalEntornos;

    static Connection conn;
    static Scanner sc = new Scanner(System.in);
    static double [][] notas = null;
    public static void main(String[] args) throws SQLException {


        int opcion = 0;

        do {

            System.out.println("----------------------------------------------");
            System.out.println("0 - Salir");
            System.out.println("1 - Crear array bidimensional de notas");
            System.out.println("2 - Rellenar array bidimensional de notas");
            System.out.println("3 - Nota media por asignatura " +
                    "1. - Programacion" +
                    "2. - Base de datos" +
                    "3. - Entornos de Desarrollo");
            System.out.println("4 - Nota media de todas las asignaturas");
            System.out.println("5 - Crear BBDD notas");
            System.out.println("6 - Insertar Array en BBDD");
            System.out.println(" ");
            System.out.println("Introduzca una opcion");
            System.out.println("----------------------------------------------");

            opcion = sc.nextInt();

            switch (opcion) {

                case 0:
                    System.out.println("Gracias por usar el programa");
                    break;

                case 1:
                    crearArrayBidimensional();
                    break;

                case 2:
                    rellenarArrayBidimensional();
                    break;

                case 3:
                    int i = 0;
                    System.out.println("Elige la asignatura que desea ver la nota media");
                    i = sc.nextInt();
                    notaMediaPorAsignatura(i);
                    break;

                case 4:
                    double medial = notaMediaTodas();
                    System.out.println("La nota media de todas las asignaturas es: " + medial);
                    break;

                case 5:
                    setup_bd();
                    break;

                case 6:
                    insertar_array_en_bd();
                    break;


                default:
                    System.out.println("Introduzca una opcion valida");

            }
        } while (opcion != 0);
    }

    private static void crearArrayBidimensional() {
        notas = new double[3][2];
    }

    private static void rellenarArrayBidimensional() {
        System.out.println("Introduzca la nota del examen de de programacion");
            double notaExamenProgramacion = sc.nextDouble();
        System.out.println("introduzca la nota de la practica de programacion");
            double notaPracticaProgramacion = sc.nextDouble();


        System.out.println("introduzca la nota del examen de base de datos");
            double notaExamenBaseDatos = sc.nextDouble();
        System.out.println("introduzca la nota de la practica de base de datos");
            double notaPracticaBaseDatos = sc.nextDouble();


        System.out.println("introduzca la nota del examen de entornos de desarrollo");
            double notaExamenEntornosDesarrollo = sc.nextDouble();
        System.out.println("introduzca la nota de la practica de entornos de desarrollo");
            double notaPracticaEntornosDesarrollo = sc.nextDouble();


            notas[0][0] = notaExamenProgramacion;
            notas[0][1] = notaPracticaProgramacion;


            notas[1][0] = notaExamenBaseDatos;
            notas[1][1] = notaPracticaBaseDatos;


            notas[2][0] = notaExamenEntornosDesarrollo;
            notas[2][1] = notaPracticaEntornosDesarrollo;

            /*for (int i = 0; i < notas.length; i++) {
                for (int j = 0; j < notas[i].length; j++) {
                    //switch case;
                }
            }
*/
    }

    public static void notaMediaPorAsignatura(int i) {

        switch (i) {
            case 1:
                System.out.println("La nota media de Programacion es: " + ((((notas[0][0]/100)*60)  +   (notas[0][1]/100)*40)));
                //totalProgramacion = sc.nextDouble();
                break;
            case 2:
                System.out.println("La nota media de BBDD es: " + ((((notas[1][0]/100)*60)  +   (notas[1][1]/100)*40)));
                //totalBaseDatos = sc.nextDouble();
                break;
            case 3:
                System.out.println("La nota media de Entornos de Desarrollo es: "+ ((((notas[2][0]/100)*60)  +   (notas[2][1]/100)*40)));
                //totalEntornos = sc.nextDouble();
                break;

            default:
                System.out.println("Introduzca una opcion valida");
        }
    }


    private static void establecerConexion() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "admin");
    }

    public static double notaMediaTodas() {
        double media = 0;

        media = ( ((((notas[0][0]/100)*60)  +   (notas[0][1]/100)*40)) + ((((notas[1][0]/100)*60)  +   (notas[1][1]/100)*40)) + ((((notas[2][0]/100)*60)  +   (notas[2][1]/100)*40)) ) / 3;

       return media;
    }

    public static void setup_bd() throws SQLException {
        establecerConexion();

        String query = "CREATE DATABASE Notas ";

        Statement st = conn.createStatement();
        st.executeUpdate(query);
    }

    public static void insertar_array_en_bd() throws SQLException {
        establecerConexion();

        for (int i = 0; i < notas.length; i++){
            for (int j = 0; j < notas[i].length; j++){
                Asignatura as =
            }
        }



    }
}

