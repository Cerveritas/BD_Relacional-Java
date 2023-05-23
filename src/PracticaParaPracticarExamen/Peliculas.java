package PracticaParaPracticarExamen;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Peliculas implements PeliculasInterface {


    static Pelicula p = new Pelicula();
    static Connection conn;
    static Scanner sc = new Scanner(System.in);

    static {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/practica3", "root", "Myandroidop5");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void crearTabla() throws SQLException {

        String query = "CREATE TABLE Pelicula (" +
                "id INT," +
                "titulo VARCHAR(30)," +
                "genero VARCHAR(20)," +
                "estreno INT)";
        Statement st = conn.createStatement();
        st.executeUpdate(query);

        System.out.println("Tabla Película creada correctamente...");
    }

    public void eliminarTabla() throws SQLException {

        String query = "DROP TABLE Pelicula";
        Statement st = conn.createStatement();
        st.executeUpdate(query);

        System.out.println("Tabla Película eliminada correctamente...");
    }

    public void crearPelicula(Pelicula p) throws SQLException {


        PreparedStatement ps = conn.prepareStatement("insert into pelicula values (?,?,?,?)");

        System.out.println("Indicame el id: ");
        p.id = sc.nextInt();
        System.out.println("Indicame el titulo: ");
        p.titulo = sc.next();
        System.out.println("Indicame el genero [ ROMANTICA, MIEDO, COMEDIA ] :  ");
        p.genero = sc.next();
        System.out.println("Indicame el esteno: ");
        p.estreno = sc.nextInt();


        ps.setInt(1, p.getId());
        ps.setString(2, p.getTitulo());
        ps.setString(3, String.valueOf(p.getGenero()));
        ps.setInt(4, p.getEstreno());

        ps.executeUpdate();

        System.out.println("La pelicula ha sido insertada en la tabla Pelicula correctamente...");


    }

    public void eliminarPelicula(int id) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("DELETE FROM pelicula WHERE id = ?");

        System.out.println("Indica el ID de la pelicula que desea eliminar: ");
        id = sc.nextInt();

        ps.setInt(1, id);
        ps.executeUpdate();

        System.out.println("La pelicula con el ID " + id + " ha sido eliminada correctamente...");

    }

    public Pelicula buscarPelicula(int id) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM pelicula where id = ?");

        System.out.println("Indica el ID de la pelicula que desea buscar: ");
        id = sc.nextInt();

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            p.id = rs.getInt("id");
            p.titulo = rs.getString("titulo");
            p.genero = rs.getString("genero");
            p.estreno = rs.getInt("estreno");


        }
        Pelicula pelicula = new Pelicula(p.id, p.titulo, p.genero, p.estreno);

        return pelicula;
    }

    public ArrayList<Pelicula> buscarTodo() throws SQLException {
        ArrayList<Pelicula> pelis = new ArrayList<>();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM pelicula");

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            p.id = rs.getInt("id");
            p.titulo = rs.getString("titulo");
            p.genero = rs.getString("genero");
            p.estreno = rs.getInt("estreno");

            Pelicula pelicula = new Pelicula(p.id, p.titulo, p.genero, p.estreno);

            pelis.add(pelicula);


        }


        return pelis;
    }

    public ArrayList<Pelicula> buscarPorGeneroOrdenarEstreno(String genero) throws SQLException {

        ArrayList<Pelicula> pelis = new ArrayList<>();


        PreparedStatement ps = conn.prepareStatement("SELECT * FROM pelicula where genero = ?");
        System.out.println("indique el genero de la pelicula que desea buscar: ");
        genero = sc.next();

        ps.setString(1, genero);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            p.id = rs.getInt("id");
            p.titulo = rs.getString("titulo");
            p.genero = rs.getString("genero");
            p.estreno = rs.getInt("estreno");

            Pelicula pelicula = new Pelicula(p.id, p.titulo, p.genero, p.estreno);

            pelis.add(pelicula);
        }
        return pelis;
    }



}