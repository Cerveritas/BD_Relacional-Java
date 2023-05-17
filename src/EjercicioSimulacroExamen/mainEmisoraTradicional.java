package EjercicioSimulacroExamen;

import java.sql.*;
import java.util.Scanner;

public class mainEmisoraTradicional {

    static Scanner sc = new Scanner(System.in);
    static Connection conn;

    static EmisoraTradicional[] array_estatico;






    public static void main(String[] args) throws SQLException, AccionInvalida {

        conn = DriverManager.getConnection("jdbc.mysql://localhost:3306/", "root", "admin");


        int opcion = 0;


        do {
            System.out.println("------------------------------------------");
            System.out.println("0 - Salir del programa");
            System.out.println("1 - Crear Base De Datos SimulacroExamen");
            System.out.println("2 - Crear tabla emisoraOnline");
            System.out.println("Introzca una opcion por favor");
            System.out.println("------------------------------------------");
            opcion = sc.nextInt();

            switch (opcion) {
                case 0:
                    System.out.println("Gracias por usar el programa");
                    break;

                case 1:
                    crear_tabla_emisora();
                    break;

                case 2:
                    insertar_array_estatico();
                    break;



                default:
                    System.out.println("El numero introducido es incorrecto, intentelo de nuevo");
                    break;
            }

        } while (opcion != 0);
    }


    private static void crear_tabla_emisora() throws SQLException {

        String query = "create table emisoraTradicional (" +
                "num_emisora int not null primary key," +
                "nombre varchar(100) not null," +
                "emitiendo boolean," +
                "beneficios double," +
                "num_oyentes int," +
                "frecuencia(2)" +
                "num_frecuencia double" +
                ") ";

        Statement st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("tabla emisoraOnline creada correctamente");

    }


    private static void insertar_array_estatico() throws AccionInvalida, SQLException {

        System.out.println("Introduzca el numeor de emisoras que quiere introducir");
        int dimension = sc.nextInt();

        //se dimensiona el array
        array_estatico = new EmisoraTradicional[dimension];

        //rellenar array estatico
        for (int i = 0; i < array_estatico.length; i++){
            System.out.println("procedemos a la insercion en el array de la posicion "+i);


            System.out.println("introduzca el indentificador de la emisora");
            int num_emisora  = sc.nextInt();

            System.out.println("Introduzca el nombre de la emisora");
            String nombre_emisora = sc.next();

            System.out.println("Introduzca el numero de oyentes");
            int numero_oyentes = sc.nextInt();

            System.out.println("introduzca la frecuencia de la emisora");
            String frecuencia = sc.next();

            System.out.println("introduzca el numero de la frecuencia");
            double num_frecuencia = sc.nextDouble();

            //creamos el objeto
            EmisoraTradicional e = new EmisoraTradicional(num_emisora, nombre_emisora, numero_oyentes, frecuencia, num_frecuencia);

            //insertamos el objeto en el array
            array_estatico[i] = e;
        }

        //imprimir array estatico para valida que hemos insertado

        //recorrerlo para insertar cada registro en bbdd
        for (int i = 0; i < array_estatico.length; i++){

            PreparedStatement ps = conn.prepareStatement("insert into emisoraTradicional values (?,?,?,?,?,?,?)");
            ps.setInt(1, array_estatico[i].getNum_emisora());
            ps.setString(2, array_estatico[i].getNombre_emisora());
            ps.setBoolean(3, array_estatico[i].isEmitiendo());
            ps.setDouble(4, array_estatico[i].getBeneficios());
            ps.setInt(5, array_estatico[i].getNum_oyentes());
            ps.setString(4, array_estatico[i].getFrecuencia().toString());
            ps.setDouble(5, array_estatico[i].getNum_frecuencia());
        }
    }
}
