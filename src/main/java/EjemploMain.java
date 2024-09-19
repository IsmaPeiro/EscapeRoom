import java.sql.*;

public class EjemploMain {
    
    private Connection conexion = null;
    
    public EjemploMain() throws SQLException {
        try {
            conectar();
            System.out.println("Consulta 1");
            consulta();
            System.out.println("Consulta 2");
            consulta2("López");
            System.out.println("Transacción");
            transaccion();
        } finally {
            cerrar();
        }
    }
    
    public void conectar() throws SQLException {
        String jdbc = "jdbc:mysql://localhost:3306/ejemplo";
        conexion = DriverManager.getConnection(jdbc, "root", "Obokaman1976.");
        conexion.setAutoCommit(false);
    }
    
    public void consulta() throws SQLException {
        Statement statement = conexion.createStatement();
        ResultSet set = statement.executeQuery("select id_alumno, nombre, apellidos from alumnos");
        while (set.next()) {
            int idAlumno = set.getInt("id_alumno");
            String nombre = set.getString("nombre");
            String apellidos = set.getString("apellidos");
            System.out.println("Alumno, id: " + idAlumno + ", nombre: " + nombre + " Apellidos: " + apellidos);
        }
        set.close();
        statement.close();
    }
    
    public void consulta2(String apellidos) throws SQLException {
        String query = "select id_alumno, nombre, apellidos from alumnos where apellidos = ?";
        PreparedStatement statement = conexion.prepareStatement(query);
        statement.setString(1, apellidos);
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            int getIdAlumno = set.getInt("id_alumno");
            String getNombre = set.getString("nombre");
            String getApellidos = set.getString("apellidos");
            System.out.println("Alumno, id: " + getIdAlumno + ", nombre: " + getNombre + " Apellidos: " + getApellidos);
        }
        set.close();
        statement.close();
        
    }
    
    public void transaccion() throws SQLException {
        final String PROFESOR = "INSERT INTO profesores(id_profesor, nombre, apellidos) " +
                "VALUES (?, ?, ?)";
        final String ASIGNATURA = "INSERT INTO asignaturas(id_asignatura, nombre, profesor) " +
                "VALUES (?, ?, ?)";
        
        PreparedStatement profesor = null, asignatura = null;
        try {
            profesor = conexion.prepareStatement(PROFESOR);
            profesor.setInt(1, 61);
            profesor.setString(2, "José");
            profesor.setString(3, "Pérez");
            profesor.executeUpdate();
            
            asignatura = conexion.prepareStatement(ASIGNATURA);
            asignatura.setInt(1, 101);
            asignatura.setString(2, "Fundamentos de Bases de Datos 2");
            asignatura.setInt(3, 60);
            asignatura.executeUpdate();
            
            conexion.commit();
            System.out.println("Ejecutado.");
        } catch (SQLException e) {
            conexion.rollback();
            e.printStackTrace();
        } finally {
            if (profesor != null) {
                profesor.close();
            }
            if (asignatura != null) {
                asignatura.close();
            }
        }
    }
    
    public void cerrar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
    
    public static void main(String[] args) {
        try {
            new EjemploMain();
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Error durante el uso de JDBC");
        }
        
    }
}

        
