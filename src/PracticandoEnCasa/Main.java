package PracticandoEnCasa;

import java.sql.*;
import java.util.*;

public class Main {

    static ArrayList<Paciente> listadoPacientes = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static Connection conn;

    static Paciente pac;



    public static void main(String[] args) throws SQLException {



        int opcion = 0;

        do {

            System.out.println("------------------------------------------------");
            System.out.println("0 - Salir del programa");
            System.out.println("1 - Crear la BBDD ut14");
            System.out.println("2 - Crear la tabla paciente");
            System.out.println("3 - Eliminar BBDD");
            System.out.println("4 - Borrar tabla paciente");
            System.out.println("5 - Insertar nuevo registro");
            System.out.println("6 - Eliminar registro");
            System.out.println("7 - Vaciar tabla");
            System.out.println("8 - Actualizar el numero de operaciones");
            System.out.println("9 - Consultar datos");
            System.out.println("10 - Insertar en BBDD a partir de una clase");
            System.out.println("11 - Almacenar los registros de la BBDD en un arrayList");
            System.out.println("12 - Ordenar el ArrayList por el numero de operaciones de manera descendente");
            System.out.println("13 - SHOW DATABASES normal");
            System.out.println("14 - SHOW DARTABASES METADATA");
            System.out.println(" ");
            System.out.println("Introduce un numero: ");
            System.out.println("------------------------------------------------");
            System.out.println(" ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 0:
                    System.out.println("Gracias por usar el programa...");
                    break;

                case 1:
                    creacionBBDD();
                    break;

                case 2:
                    crearTablaPaciente();
                    break;

                case 3:
                    eliminarBBDD();
                    break;

                case 4:
                    borrarTablaPaciente();
                    break;

                case 5:
                    insertarNuevoRegistro();
                    break;

                case 6:
                    eliminarRegistro();
                    break;

                case 7:
                    vaciarTabla();
                    break;

                case 8:
                    actualizarN_operaciones();
                    break;

                case 9:
                    consultarDatos();
                    break;

                case 10:
                    pac = construirObjeto();
                    insertamosObjeto(pac);
                    break;

                case 11:
                    insertarBBDDArrayList();
                    break;

                case 12:
                    ordenarArrayListPorN_OperacionesDescendente();
                    break;

                case 13:
                    showDatabasesNormal();
                    break;

                case 14:
                    showDatabasesMETADATA();
                    break;

                default:
                    System.out.println("El numero introducido no es correcto, intentelo de nuevo...");


            }

        } while (opcion != 0);
    }




/********************************************************************************/
    // METODOS FANTASMAS -> Establecer conexion con el SGBD
    public static void establecerConexion() throws SQLException {

        String url="jdbc:mysql://localhost:3306/";
        String user= "root";
                                                                                                                                                                                                                                                        String pwd="Myandroidop5";
        conn = DriverManager.getConnection(url,user,pwd);
    }
    public static void asignar() throws SQLException {
        String query = "use ut14";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
    }
/********************************************************************************/




    // METODO 1 -> Crear la base de datos ut14
    public static void creacionBBDD() throws SQLException {
        establecerConexion();

        String query = "CREATE DATABASE ut14";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("La base de datos ut14 fue creada correctamente...");
    }


    // METODO 2 -> Crear la tabla paciente
    public static void crearTablaPaciente() throws SQLException {
        establecerConexion();
        asignar();

        String query = "create table paciente ("+
                "dni varchar(9) primary key," +
                "nombre varchar(30) not null, "+
                "apellidos varchar(100), "+
                "n_operaciones int)";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("La tabla paciente fue creada correctamente...");

    }


    // METODO 3 -> Eliminar BBDD
    public static void eliminarBBDD() throws SQLException {
        establecerConexion();
        asignar();

        String query = "DROP DATABASE ut14";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("La base de datos ut14 fue eliminada correctamente...");
    }


    // METODO 4 -> Borrar tabla paciente
    public static void borrarTablaPaciente() throws SQLException {
        establecerConexion();
        asignar();

        String query = "DROP TABLE paciente";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        System.out.println("La tabla paciente fue eliminada correctamente...");
    }


    // METODO 5 -> Insertar nuevo registro
    public static void insertarNuevoRegistro() throws SQLException {
        establecerConexion();
        asignar();

        PreparedStatement ps = conn.prepareStatement("insert into paciente values (?,?,?,?)");
            System.out.print("Indicame el DNI del paciente: ");
                String dni = sc.next();
            System.out.print("Indicame el nombre del paciente: ");
                String nombre = sc.next();
            System.out.print("Indicame el apellido del paciente: ");
                String apellidos = sc.next();
            System.out.print("Indicame el numero de operaciones del paciente: ");
                int n_operaciones = sc.nextInt();

        ps.setString(1, dni);
        ps.setString(2, nombre);
        ps.setString(3, apellidos);
        ps.setInt(4, n_operaciones);

        ps.executeUpdate();



        System.out.println("El registro fue insertado correctamente...");
    }


    // METODO 6 -> Eliminar registro segun DNI
    public static void eliminarRegistro() throws SQLException {
        establecerConexion();
        asignar();

        PreparedStatement ps = conn.prepareStatement("delete from paciente where dni=?");
            System.out.print("Indicame el DNI del paciente que deseas eliminar: ");
                String dni = sc.next();

        ps.setString(1, dni);

        ps.executeUpdate();



        System.out.println("El paciente con DNI "+dni+" ha sido eliminado...");
    }


    // METODO 7 -> Vaciar tabla pacientes [Eliminar registros]
    public static void vaciarTabla() throws SQLException {
        establecerConexion();
        asignar();

        PreparedStatement ps = conn.prepareStatement("truncate table paciente");
        ps.executeUpdate();

        System.out.println("Tabla paciente vaciada correctamente...");
    }


    // METODO 8 -> Actualizar numero de operaciones de un paciente
    public static void actualizarN_operaciones() throws SQLException {
        establecerConexion();
        asignar();

        PreparedStatement ps = conn.prepareStatement("update paciente set n_operaciones = ? where dni = ?");
            System.out.print("Indique el DNI del paciente que desea actualizar el numero de operaciones: ");
                String dni = sc.next();
            System.out.print("Escriba el nuevo numero de operaciones del paciente: ");
                int n_operaciones = sc.nextInt();

        ps.setString(2, dni);
        ps.setInt(1, n_operaciones);

        ps.executeUpdate();

        System.out.println("Numero de operaciones del paciente "+dni+" ha sido cambiado correctamente...");
    }


    // METODO 9 -> Consultar datos de un paciente segun el dni introducido por pantalla
    public static void consultarDatos() throws SQLException {
        establecerConexion();
        asignar();

        PreparedStatement ps = conn.prepareStatement("select * from paciente where dni = ?");
            System.out.print("Introduce el DNI del paciente que desea ver: ");
            String dni = sc.next();

        ps.setString(1, dni);


        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            System.out.println("El paciente con DNI "+rs.getString(1)+", nombre: "+rs.getString(2)+"" +
                    ", apellido: "+rs.getString(3)+", tiene una cantidad de operaciones realizadas de: "+rs.getInt(4)+" operaciones.");
        }
    }


    // METODO 10 -> Construir objeto e insertarlo en nuestra BBDD
    public static Paciente construirObjeto() {
        System.out.print("Introduzca el DNI del paciente a insertar: ");
            String dni = sc.next();
        System.out.print("Introduzca el nombre del paciente: ");
            String nombre = sc.next();
        System.out.print("Introduzca el apellido del paciente: ");
            String apellido = sc.next();
        System.out.print("Introduzca el numero de operaciones del paciente: ");
            int n_operaciones = sc.nextInt();

        pac = new Paciente(dni, nombre, apellido, n_operaciones);

        return pac;
    }

    public static void insertamosObjeto(Paciente pac) throws SQLException {
        establecerConexion();
        asignar();

        PreparedStatement ps = conn.prepareStatement("insert into paciente values (?,?,?,?)");

        ps.setString(1, pac.getDni());
        ps.setString(2, pac.getNombre());
        ps.setString(3, pac.getApellido());
        ps.setInt(4, pac.getN_operaciones());

        ps.executeUpdate();
                                                                                                                        System.out.println(" ");
        System.out.println("Paciente introducido desde el objeto correctamente...");
    }


    // METODO 11 -> Insertar registros de la BBDD en un array list
    public static void insertarBBDDArrayList() throws SQLException {
        establecerConexion();
        asignar();

        PreparedStatement ps = conn.prepareStatement("select * from paciente");
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            pac = new Paciente(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            listadoPacientes.add(pac);
        }

        for (Paciente Pac: listadoPacientes){
            System.out.println(Pac.toString());
        }
    }


    // METODO 12 -> Ordenar ArrayList por numero de operaciones descendente
    public static void ordenarArrayListPorN_OperacionesDescendente() throws SQLException {
        establecerConexion();
        asignar();

        listadoPacientes.sort(Comparator.comparing(Paciente::getN_operaciones).reversed());
        Iterator<Paciente> itr = listadoPacientes.iterator();

        while (itr.hasNext()){
            System.out.println(itr.next().toString());
        }

    }


    // METODO 13 -> Mostrar las BBDD de manera normal
    public static void showDatabasesNormal() throws SQLException {
        establecerConexion();

        String query = "SHOW DATABASES";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()){
            System.out.println(rs.getString(1));
        }
    }


    // METODO 14 -> Mostrar las BBDD con los METADATOS
    public static void showDatabasesMETADATA() throws SQLException {
        establecerConexion();

        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet rs = databaseMetaData.getCatalogs();
        while (rs.next()){
            System.out.println(rs.getString(1));
        }
    }
}
