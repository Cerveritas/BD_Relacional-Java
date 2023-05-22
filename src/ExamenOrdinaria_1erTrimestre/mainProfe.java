package ExamenOrdinaria_1erTrimestre;

import java.sql.*;
import java.util.Scanner;

public class mainProfe {

    static Scanner sc = new Scanner(System.in);
    static double[][] notas = null;
    static Connection conn =null;

    public static void main(String[] args) throws SQLException {
        int opcion = 0;

        do {
            System.out.println("0.salir");
            System.out.println("1.Crear array de notas");
            System.out.println("2.Rellenar array de notas");
            System.out.println("3.Insertar array estático en base de datos");
            System.out.println("4. Nota media de las asignaturas ");
            System.out.println("Introduzca una opción");
            opcion = sc.nextInt();
            switch (opcion) {
                case 0:
                    System.out.println("graicas por usar el programa");
                    break;
                case 1:
                    crear_array();
                    break;
                case 2:
                    rellenar_array();
                    break;

                case 3:
                    insertar_array_enbd();
                    break;
                case 4:

                    break;
                case 5:
                    break;


            }


        } while (opcion != 0);


    }

    private static void insertar_array_enbd() throws SQLException {

        establish_connection();
        create_db();
        create_table();
        PreparedStatement st = conn.prepareStatement("insert into asignatura values(?,?,?,?)");

        Asignatura programacion = new Asignatura(0, "programacion", notas[0][0], notas[0][1]);

        Asignatura bases_datos = new Asignatura(1, "bases de datos", notas[1][0], notas[1][1]);

        Asignatura entornos = new Asignatura(2, "entornos de desarrollo", notas[2][0], notas[2][1]);

    //insertar objeto programación a base de datos
        st.setInt(1, programacion.getId());
        st.setString(2, programacion.getNombre());
        st.setDouble(3, programacion.getNota_examen());
        st.setDouble(4, programacion.getNota_practica());

    //insertar objeto programación a base de datos
        st.setInt(1, bases_datos.getId());
        st.setString(2, bases_datos.getNombre());
        st.setDouble(3, bases_datos.getNota_examen());
        st.setDouble(4, bases_datos.getNota_practica());

    //insertar objeto programación a base de datos
        st.setInt(1, entornos.getId());
        st.setString(2, entornos.getNombre());
        st.setDouble(3, entornos.getNota_examen());
        st.setDouble(4, entornos.getNota_practica());

        st.executeUpdate();

        System.out.println("Sa rellenado tope de flama");




    }

    private static void establish_connection() throws SQLException {

        conn= DriverManager.getConnection("jdbc:mysql://localhost:3006","root","admin");



    }

    private static void create_db() throws SQLException {

        String query = "create database notas";
        Statement st = conn.createStatement();
        st.executeUpdate(query);



    }

    private static void create_table() throws SQLException {

        String query = "create table asignatura(" +
                "id int not null primary key," +
                "nombre varchar(50)," +
                "nota_examen double," +
                "nota_practica double)";
        Statement st = conn.createStatement();
        st.executeUpdate(query);


    }




    private static void rellenar_array() {


        for (int i = 0; i < notas.length; i++) {
            for (int j = 0; j < notas[i].length; j++) {

                switch (i) {
                    case 0: //notas d eprogramación
                        switch (j) {
                            case 0:
                                System.out.println("Introduzca nota de examen de programación");
                                notas[i][j] = sc.nextDouble();

                                break;
                            case 1:
                                System.out.println("Introduzca nota de práctica de programación");

                        }
                    case 1: //notas d eprogramación
                        switch (j) {
                            case 0:
                                System.out.println("Introduzca nota de examen de base de datos");
                                notas[i][j] = sc.nextDouble();

                                break;
                            case 1:
                                System.out.println("Introduzca nota de práctica de bases de datos");

                        }
                    case 2: //notas d eprogramación
                        switch (j) {
                            case 0:
                                System.out.println("Introduzca nota de examen de entornos");
                                notas[i][j] = sc.nextDouble();

                                break;
                            case 1:
                                System.out.println("Introduzca nota de práctica de entornos");

                        }

                }
            }


        }


    }

    private static void crear_array() {
        notas = new double[3][2];


    }
}

