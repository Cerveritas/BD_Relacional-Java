
package EjercicioSimulacroExamen;

import EjercicioPractico.Jugador;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class main {

    public static Scanner sc = new Scanner(System.in);
    public static Emisora[] listado_emisoras;
    public static ArrayList<Emisora> arrayList_emisoras = new ArrayList<>();
    public static Connection conn = null;


    public static void main(String[] args) throws SQLException, AccionInvalida {

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
                    EmisoraOnline e = new EmisoraOnline(1, "los 40", 1000, "www.los40.com");
                    insertar_emisora(e);
                    break;

                case 4:
                    System.out.println("¿Cual es el filtro de numero de oyentes?");
                    int num_oyentes = sc.nextInt();
                    buscar_añadir_array_estatico(num_oyentes);
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

    // MÉTODO 3
    private static void insertar_emisora(EmisoraOnline e) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("insert into emisoraOnline values(?,?,?,?,?,?)");
        ps.setInt(1, e.getNum_emisora());
        ps.setString(2, e.getNombre_emisora());
        ps.setBoolean(3, e.isEmitiendo());
        ps.setDouble(4, e.getBeneficios());
        ps.setInt(5, e.getNum_oyentes());
        ps.setString(6, e.getUrl());

        ps.executeUpdate();
        System.out.println("el registro se insertó correctamente...");

    }


    // MÉTODO 4

    private static void buscar_añadir_array_estatico(int numero_oyentes) throws SQLException, AccionInvalida {

        PreparedStatement ps = conn.prepareStatement("select count(*) from emisoraonline where num_oyentes < ?");
        ps.setInt(1, numero_oyentes);

        ResultSet rs = ps.executeQuery();

        int dimension = 0;
        while (rs.next()){
            dimension = rs.getInt(1);
        }
        listado_emisoras = new EmisoraOnline[dimension];
        ps = conn.prepareStatement("select * from emisoraonline where num_oyentes<?");
        ps.setInt(1, numero_oyentes);
        rs = ps.executeQuery();

        int i = 0;
        while (rs.next()){
            EmisoraOnline e = new EmisoraOnline(rs.getInt(1), rs.getString(2), rs.getInt(5), rs.getString(6));
            listado_emisoras[i] = e;
            i++;
        }
        for (int j = 0; j < listado_emisoras.length; j++){
            System.out.println(listado_emisoras[j].toString());
        }



    }





    // MÉTODO 5
    private static void añadir_arraylist_beneficios() throws SQLException, AccionInvalida {
        //solo insertaremos las radios cuyos beneficios superen los 3000 euros

        PreparedStatement ps = conn.prepareStatement("select * from emisoraonline");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if (rs.getDouble(4) > 3000) {
                EmisoraOnline e = new EmisoraOnline(rs.getInt(1), rs.getString(2), rs.getInt(5), rs.getString(6));
                arrayList_emisoras.add(e);
            }
        }
    }


    // METODO 6
    public static String sacar_version_SGBD() throws SQLException {

        DatabaseMetaData databaseMetaData = conn.getMetaData();
        return  databaseMetaData.getDatabaseProductVersion();
    }




}
