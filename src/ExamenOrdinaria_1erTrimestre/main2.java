package ExamenOrdinaria_1erTrimestre;

import java.util.Scanner;

public class main2 {

    static Scanner sc = new Scanner(System.in);
    static double [][] notas = null;
    public static void main(String[] args) {


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
                    break;

                case 3:
                    break;

                case 4:
                    break;

                default:
                    System.out.println("Introduzca una opcion valida");

            }
        } while (opcion != 0);
    }

    private static void crearArrayBidimensional() {
        notas = new double[3][2];


    }


}

