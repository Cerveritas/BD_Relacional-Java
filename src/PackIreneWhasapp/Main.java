package PackIreneWhasapp;

import ExamenTercerTrimestre.Hotel;
import RepasoExtraordinaria.Jugador;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static Connection conn;
    public static Profesor p2;

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
            System.out.println("6 - Insertar todos los registros de la tabla profesor que tengan un salario mayor al indicado por parametria de entrada en un arraylist global llamado lista_profesores. Mostrar arrayList");
            System.out.println("7 - Ordenar arraylist lista_profesores de la opcion 6 primero por años de experiencia de manera ascendente y, luego, por salario de manera descendente. Mostrar arrayList ordenado");
            System.out.println("8 - Crear arraylist profesores_bd a partir de los datos que ya hay almacenados en la bd");
            System.out.println("9 - Insertar en bd un array estatico de profesores creado previamente. Primero preguntamos al usuario la dimension");
            System.out.println("10 - Comparar el contenido de lista_profesores y profesores_bd e imprimir 'todo correcto' si ambos poseen el mismo contenido");
            System.out.println("11 - Mostrar la url de la conexion y la version del SGBD con el siguiente formato...");
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
                    System.out.println("introduzca el grado");
                        String grado = sc.next();
                    System.out.println("Introduzca el ies");
                        String ies = sc.next();
                    System.out.println("Introduzca si esta activo o no");
                        boolean activo = sc.nextBoolean();
                    System.out.println("Introduzca la categoria");
                        String categoria = sc.next();
                    System.out.println("Introduzca el nivel");
                        String nivel = sc.next();
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
                    break;

                case 7:
                    break;

                case 8:
                    break;

                case 9:
                    break;

                case 10:
                    break;

                case 11:
                    break;

                default:
                    System.out.println("El numero introducido es incorrecto, intentelo de nuevo...");
            }
        } while (opcion != 0);
    }


    public static void conectarBD() throws SQLException {
        String url="jdbc:mysql://localhost:3306/";
        String user= "root";
        String pwd="admin";
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




}
