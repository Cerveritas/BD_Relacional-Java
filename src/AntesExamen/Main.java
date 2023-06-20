package AntesExamen;

import PackIreneWhasapp.AccionIncorrecta;
import PackIreneWhasapp.Profesor;
import RepasoExtraordinaria.Jugador;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    public static Connection conn;

    public static ArrayList<Profesorete> lista_profesores = new ArrayList<>();

    public static Profesorete[] lista_profesores_estatica;
    public static Profesorete j1;

    public static void main(String[] args) throws SQLException, AccionIncorrecta {

        int opcion = 0;

        do {

            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("0 - Salir del programa");
            System.out.println("1 - Conectar con el SGBD");
            System.out.println("2 - Crear la base de datos aaaaaaaaaa y asginarla");
            System.out.println("3 - Crear la tabla profeloco");
            System.out.println("4 - Insertar registro a partir de un objeto de la clase profeloco que se ha valorizado por los valores introducidos por teclado");
            System.out.println("5 - Consultar profesor por id intoducido por teclado y almacenarlo en un objeto de la clase profesor. Mostrar info");
            System.out.println("6 - Insertar profesores de la bd en un arraylist, se mostrara por pantalla");
            System.out.println("7 - Insertar en bd un array estatico de profesores, se solicitara al usuario la dimension");
            System.out.println("8 - Insertar en un array estaico los datos de la base de datos. Mostrar info");
            System.out.println(" ");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Indique una opcion");
            System.out.println(" ");

            opcion = sc.nextInt();

            switch (opcion) {

                case 0:
                    System.out.println("Gracias por usar el programa...");
                    break;

                case 1:
                    conectarSGBD();
                    break;

                case 2:
                    crearBD();
                    break;

                case 3:
                    crearTabla();
                    break;

                case 4:

                    System.out.println("Inserte el id del profesor");
                        int id = sc.nextInt();
                    System.out.println("Inserte el nombre del profesor");
                        String nombre = sc.next();
                    System.out.println("Inserte los apellidos del profesor");
                        String apellidos = sc.nextLine();
                    System.out.println("Indicame la edad del profesor");
                        int edad = sc.nextInt();
                    System.out.println("Indique el nombre del instituto");
                        String ies = sc.next();
                    System.out.println("Indique el nombre de asignatura que imparte");
                        String asignatura = sc.next();

                    Profesorete pe = new Profesorete (id, nombre, apellidos,edad, ies, Asignatura.valueOf(asignatura));


                    insertarRegistro(pe);
                    break;

                case 5:
                    System.out.println("Indique el id del profesor que desea ser mostrado");
                    int identidicador = sc.nextInt();
                    consultarProfesor(identidicador);
                    System.out.println(j1.toString());
                    break;

                case 6:
                    insertarEnArrayList();
                    break;

                case 7:
                    insertarBBDDenArrayEstatico();
                    break;

                case 8:
                    System.out.println("diga la dimension");
                    int dimen = sc.nextInt();
                    insertarArrayEstaticoo(dimen);
                    break;

                default:
                    System.out.println("El numero introducido es incorrecto, intentelo de nuevo...");
            }
        } while (opcion != 0);
    }

    public static void conectarSGBD() throws SQLException {
        String url="jdbc:mysql://localhost:3306/";
        String user= "root";
        String pwd="Myandroidop5";
        conn= DriverManager.getConnection(url,user,pwd);
        System.out.println("Conexión establecida...");
    }

    public static void crearBD() throws SQLException {
        String query = "CREATE DATABASE aaaaaaaaaa";
        Statement st = conn.createStatement();
        st.executeUpdate(query);

        System.out.println("Base de datos creada correctamente...");
    }

    public static void crearTabla() throws SQLException {

        String query = "CREATE TABLE profeloco (" +
                "id INT PRIMARY KEY," +
                "nombre VARCHAR(50)," +
                "apellidos VARCHAR(50)," +
                "edad INT," +
                "ies VARCHAR(50)," +
                "asignatura VARCHAR(50)," +
                "salario double" +
                ")";

        Statement st = conn.createStatement();
        st.executeUpdate(query);
    }

    public static void insertarRegistro(Profesorete p1) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("INSERT INTO profeloco VALUES (?,?,?,?,?,?,?)");

        ps.setInt(1, p1.getId());
        ps.setString(2, p1.getNombre());
        ps.setString(3, p1.getApellidos());
        ps.setInt(4, p1.getEdad());
        ps.setString(5, p1.getIes());
        ps.setString(6, String.valueOf(p1.getAsignatura()));
        ps.setDouble(7, p1.getSalario());
        ps.executeUpdate();

        System.out.println("Datos insertados correctamente en la tabla...");

    }

    public static Profesorete consultarProfesor(int id) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM profeloco WHERE id = ?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            j1 = new Profesorete(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), Asignatura.valueOf(rs.getString(6)));
        }

        return j1;
    }

    public static void insertarEnArrayList() throws SQLException {

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM profeloco");
        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            j1 = new Profesorete(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), Asignatura.valueOf(rs.getString(6)));

            lista_profesores.add(j1);
        }
        imprimirArrayList();
    }

    public static void insertarBBDDenArrayEstatico() throws SQLException {

        PreparedStatement ps = conn.prepareStatement("SELECT count(*) FROM profeloco");
        ResultSet rs = ps.executeQuery();

        int dimension = 0;

        while (rs.next()){
            dimension = rs.getInt(1);
        }


        lista_profesores_estatica = new Profesorete[dimension];

        ps = conn.prepareStatement("SELECT * FROM profeloco");
        rs = ps.executeQuery();

        int i = 0;
        while (rs.next()){

            j1 = new Profesorete(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), Asignatura.valueOf(rs.getString(6)));

            lista_profesores_estatica[i] = j1;
            i++;

            for (int j = 0; j < lista_profesores_estatica.length; i++){
                System.out.println(lista_profesores_estatica[j]);
            }
        }
    }


    public static void insertarArrayEstaticoo(int dimension) throws SQLException {

        Profesorete[] estatic = new Profesorete[dimension];

        for(int i = 0; i < estatic.length; i++){

            System.out.println("Inserte el id del profesor");
            int id = sc.nextInt();
            System.out.println("Inserte el nombre del profesor");
            String nombre = sc.next();
            System.out.println("Inserte los apellidos del profesor");
            String apellidos = sc.nextLine();
            System.out.println("Indicame la edad del profesor");
            int edad = sc.nextInt();
            System.out.println("Indique el nombre del instituto");
            String ies = sc.next();
            System.out.println("Indique el nombre de asignatura que imparte");
            String asignatura = sc.next();

            Profesorete pe = new Profesorete (id, nombre, apellidos,edad, ies, Asignatura.valueOf(asignatura));

            estatic[i] = pe;
        }

        for (int i = 0; i < estatic.length; i++){

            PreparedStatement ps = conn.prepareStatement("INSERT INTO profeloco VALUES (?,?,?,?,?,?,?)");

            ps.setInt(1, estatic[i].getId());
            ps.setString(2, estatic[i].getNombre());
            ps.setString(3, estatic[i].getApellidos());
            ps.setInt(4, estatic[i].getEdad());
            ps.setString(5, estatic[i].getIes());
            ps.setString(6, String.valueOf(estatic[i].getAsignatura()));
            ps.setDouble(7, estatic[i].getSalario());
            ps.executeUpdate();

        }



    }








    /************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
    public static void imprimirArrayList(){

        for (Profesorete pro : lista_profesores){
            System.out.println(pro.toString());
        }
    }

}
