package PackIreneWhasapp;

import ExamenTercerTrimestre.Hotel;
import RepasoExtraordinaria.Jugador;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static Connection conn;
    public static Profesor p2;
    public static ArrayList<Profesor> lista_profesores = new ArrayList<>();

    public static Profesor[] lista_profesores_estatica;

    public static void main(String[] args) throws SQLException, AccionIncorrecta {

        int opcion = 0;


        do {


            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("0 - Salir del programa");
            System.out.println("1 - Conectar con el SGBD");
            System.out.println("2 - Crear la base de datos ejercicio_repaso y asginarla");
            System.out.println("3 - Crear la tabla profesor");
            System.out.println("4 - Insertar registro a partir de un objeto de la clase profesor que se ha valorizado por los valores introducidos por teclado");
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
                    conectarBD();
                    break;

                case 2:
                    crearBBDD();
                    break;

                case 3:
                    crearTabla();
                    break;

                case 4:
                    System.out.println("introduzca el grado [ BASICO, MEDIO, SUPERIOR ]");
                        String grado = sc.next().toUpperCase();
                    System.out.println("Introduzca el ies");
                        String ies = sc.next();
                    System.out.println("Introduzca si esta activo o no");
                        boolean activo = sc.nextBoolean();
                    System.out.println("Introduzca la categoria [ A, B, C ]");
                        String categoria = sc.next().toUpperCase();
                    System.out.println("Introduzca el nivel [ UNO, DOS, TRES ]");
                        String nivel = sc.next().toUpperCase();
                    System.out.println("Introduzca el id");
                        int id = sc.nextInt();
                    System.out.println("introduzca los años de experiencia");
                        int anyosExperiencia = sc.nextInt();
                    System.out.println("Introduzca la fecha de contrato");
                        String fecha_contrato = sc.next();


                    Profesor p = new Profesor(id, Categoria.valueOf(categoria), Nivel.valueOf(nivel), anyosExperiencia, fecha_contrato, ies, activo, Grado.valueOf(grado));
                    insertarRegistro(p);
                    break;

                case 5:
                    System.out.print("Introduzca el id del profesor que desea ver: ");
                        int identificador = sc.nextInt();
                    consultarProfesor(identificador);
                    System.out.println(p2.toString());

                    break;

                case 6:
                    insertarEnArrayList();
                    break;

                case 7:
                    System.out.println("Introduzca la cantidad de profesores que desea meter en la bd");
                        int dimen = sc.nextInt();
                    insertarArrayEstatico(dimen);
                    break;

                case 8:
                    insertarBBDDenArrayEstatico();
                    break;

                default:
                    System.out.println("El numero introducido es incorrecto, intentelo de nuevo...");
            }
        } while (opcion != 0);
    }


    public static void conectarBD() throws SQLException {
        String url="jdbc:mysql://localhost:3306/";
        String user= "root";
        String pwd="Myandroidop5";
        conn= DriverManager.getConnection(url,user,pwd);
        System.out.println("Conexión establecida...");
    }

    public static void crearBBDD() throws SQLException {

        String query = "CREATE DATABASE ejercicio_repaso";
        Statement st = conn.createStatement();
        st.executeUpdate(query);

        query = "USE ejercicio_repaso";
        st = conn.createStatement();
        st.executeUpdate(query);

        System.out.println("Base de datos creada y asignada correctamente");
    }

    public static void crearTabla() throws SQLException {

        String query = "CREATE TABLE profesor (" +
                "grado VARCHAR(50)," +
                "ies VARCHAR(50)," +
                "activo bool," +
                "salario double," +
                "categoria varchar(50)," +
                "nivel VARCHAR(50)," +
                "id int primary key," +
                "anyos_experiencia int," +
                "fecha_contrato VARCHAR(50)" +
                ");";
        Statement st = conn.createStatement();
        st.executeUpdate(query);

        System.out.println("Tabla creada correctamente");

    }

    public static void insertarRegistro(Profesor p1) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("INSERT INTO profesor VALUES (?,?,?,?,?,?,?,?,?)");

        ps.setString(1, String.valueOf(p1.getGrado()));
        ps.setString(2, p1.getIES());
        ps.setBoolean(3, p1.isActivo());
        ps.setDouble(4, p1.getSalario());
        ps.setString(5, String.valueOf(p1.getCategoria()));
        ps.setString(6, String.valueOf(p1.getNivel()));
        ps.setInt(7, p1.getId());
        ps.setInt(8, p1.getAnyo_experiencia());
        ps.setString(9, p1.getFecha_contrato());

        ps.executeUpdate();

        System.out.println("Datos introducidos en la base de datos correctamente");
    }

    public static Profesor consultarProfesor(int id) throws SQLException, AccionIncorrecta {


        PreparedStatement ps = conn.prepareStatement("SELECT * FROM profesor WHERE id = ? ");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        while(rs.next()) {

            p2 = new Profesor(rs.getInt(7), Categoria.valueOf(rs.getString(5)), Nivel.valueOf(rs.getString(6)), rs.getInt(8), rs.getString(9), rs.getString(2), rs.getBoolean(3), Grado.valueOf(rs.getString(1)));

        }


        return p2;
    }

    public static void insertarEnArrayList() throws SQLException, AccionIncorrecta {

        PreparedStatement ps = conn.prepareStatement("SELECT * from profesor");
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {

            p2 = new Profesor(rs.getInt(7), Categoria.valueOf(rs.getString(5)), Nivel.valueOf(rs.getString(6)), rs.getInt(8), rs.getString(9), rs.getString(2), rs.getBoolean(3), Grado.valueOf(rs.getString(1)));


            lista_profesores.add(p2);

        }

        imprimirArrayList();

    }

    public static void insertarArrayEstatico(int dimension) throws AccionIncorrecta, SQLException {

        Profesor[] estatico = new Profesor[dimension];

        for (int i = 0; i < estatico.length; i++) {

            System.out.println("introduzca el grado [ BASICO, MEDIO, SUPERIOR ]");
            String grado = sc.next().toUpperCase();
            System.out.println("Introduzca el ies");
            String ies = sc.next();
            System.out.println("Introduzca si esta activo o no");
            boolean activo = sc.nextBoolean();
            System.out.println("Introduzca la categoria [ A, B, C ]");
            String categoria = sc.next().toUpperCase();
            System.out.println("Introduzca el nivel [ UNO, DOS, TRES ]");
            String nivel = sc.next().toUpperCase();
            System.out.println("Introduzca el id");
            int id = sc.nextInt();
            System.out.println("introduzca los años de experiencia");
            int anyosExperiencia = sc.nextInt();
            System.out.println("Introduzca la fecha de contrato");
            String fecha_contrato = sc.next();

            Profesor p = new Profesor(id, Categoria.valueOf(categoria), Nivel.valueOf(nivel), anyosExperiencia, fecha_contrato, ies, activo, Grado.valueOf(grado));

            estatico[i] = p;
        }

        for (int i = 0; i < estatico.length; i++){

            PreparedStatement ps = conn.prepareStatement("INSERT INTO profesor VALUES (?,?,?,?,?,?,?,?,?)");

            ps.setString(1, String.valueOf(estatico[i].getGrado()));
            ps.setString(2, estatico[i].getIES());
            ps.setBoolean(3, estatico[i].isActivo());
            ps.setDouble(4, estatico[i].getSalario());
            ps.setString(5, String.valueOf(estatico[i].getCategoria()));
            ps.setString(6, String.valueOf(estatico[i].getNivel()));
            ps.setInt(7, estatico[i].getId());
            ps.setInt(8, estatico[i].getAnyo_experiencia());
            ps.setString(9, estatico[i].getFecha_contrato());

            ps.executeUpdate();

            System.out.println("Datos introducidos en la base de datos correctamente");
        }
    }

    public static void insertarBBDDenArrayEstatico() throws SQLException, AccionIncorrecta {

        PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM profesor ");
        ResultSet rs = ps.executeQuery();

        int dimension = 0;
        while (rs.next()) {
            dimension = rs.getInt(1);
        }

        lista_profesores_estatica = new Profesor[dimension];

        ps = conn.prepareStatement("SELECT * FROM profesor ");
        rs = ps.executeQuery();

        int i = 0;
        while (rs.next()){

            p2 = new Profesor(rs.getInt(7), Categoria.valueOf(rs.getString(5)), Nivel.valueOf(rs.getString(6)), rs.getInt(8), rs.getString(9), rs.getString(2), rs.getBoolean(3), Grado.valueOf(rs.getString(1)));


            lista_profesores_estatica[i] = p2;
            i++;

            for (int j = 0; j < lista_profesores_estatica.length; j++){
                System.out.println(lista_profesores_estatica[j]);
            }

        }
    }






    public static void imprimirArrayList(){

        for (Profesor pro : lista_profesores){
            System.out.println(pro.toString());
        }
    }



}
