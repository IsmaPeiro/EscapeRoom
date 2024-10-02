package MySQLConnectionTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class MySQLConnectionTest {

    private Connection connection;

    @BeforeEach
    public void setUp() throws SQLException {
        connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/escape_room2", "root", "Obokaman1976.");
    }

    @Test
    public void testConnectionIsNotNull() {
        assertNotNull(connection);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        connection.close();
    }
}
