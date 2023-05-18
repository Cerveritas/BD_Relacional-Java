package ExamenOrdinaria_1erTrimestre;



import java.util.Arrays;
import java.util.Scanner;

public class main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {


        int opcion = 0;

        String[] array = null;


        do {

            System.out.println("--------------------------------------------");
            System.out.println("0 - Salir");
            System.out.println("1 - Crear array de String");
            System.out.println("2 - Rellenar array de elementos");
            System.out.println("3 - Imprimir por panttal");
            System.out.println("4 - Ver numero de consonantes");
            System.out.println("5 - Ver numero de vocales");
            System.out.println(" ");
            System.out.println("Introduzca una opcion: ");
            System.out.println("--------------------------------------------");
            System.out.println(" ");

            opcion = sc.nextInt();

            switch (opcion) {

                case 0:
                    System.out.println("Gracias por usar el programa");
                    break;

                case 1:
                    System.out.println("Intrduzca el tamaño que quiere dimensionar el array");
                    int dimension = sc.nextInt();
                    array = crear_array(array,  dimension);
                    break;

                case 2:
                    array = rellenar_array(array);
                    break;

                case 3:
                    String resultado = imprimir_array(array);
                    System.out.println(resultado);
                    break;

                case 4:
                    System.out.println("Cual es la posicion que quieres consultar: [ hasta el numero "+(array.length-1));
                    int posicion = sc.nextInt();
                    int consonantes = num_consonantes(posicion, array);
                    System.out.println(consonantes);
                    break;

                case 5:
                    System.out.println("Cual es la posicion que quieres consultar: [ hasta el numero "+(array.length-1));
                    posicion = sc.nextInt();
                    int vocales = num_vocales(posicion, array);
                    System.out.println(vocales);

                    break;


                default:
                    System.out.println("El numero introducido no es valido");
                    break;

            }
        } while (opcion!= 0);








    }

    /** Los metodos num_vovales y num_consonantes no estan hechos bien del todoo, hay que cambiar los signos [ != ] y [ || ]**/
    private static int num_vocales(int posicion, String[] array) {
        int vocales = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].charAt(posicion) != 'a' || array[i].charAt(posicion) != 'e' || array[i].charAt(posicion) != 'i' || array[i].charAt(posicion) != 'o' || array[i].charAt(posicion) != 'u') {
                vocales++;
            }
        }
        return vocales;


    }

    private static int num_consonantes(int posicion, String[] array) {

        int consonantes = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].charAt(posicion) == 'a' || array[i].charAt(posicion) == 'e' || array[i].charAt(posicion) == 'i' || array[i].charAt(posicion) == 'o' || array[i].charAt(posicion) == 'u') {
                consonantes++;
            }
        }
        return consonantes;



    }

    private static String imprimir_array(String[] array) {
        return Arrays.toString(array);
    }

    private static String[] rellenar_array(String[] array) {

        for (int i = 0; i < array.length; i++) {
            System.out.println("Introduzca una palabra en la posicion "+i);

            array[i] = sc.next();

        }
        return array;
    }


    private static String[] crear_array(String[] array, int dimension) {
        if (array == null) {
            array = new String[dimension];
        }
        return array;
    }
}
