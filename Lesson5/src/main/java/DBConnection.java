import lombok.Data;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Data
public class DBConnection implements AutoCloseable{

    private Connection connection;
    private static final String CONNECTION_STRING = "jdbc:h2:file:~/STUDENTDB";
    private static final String USER = "databaseUser";
    private static final String PASSWORD = "databasePassword";

    private void initDB () {
        Flyway flyway = Flyway.configure().dataSource(
                CONNECTION_STRING, USER, PASSWORD).load();
        flyway.migrate();
    }

    public Connection get() throws ClassNotFoundException, SQLException {
        if (connection == null || connection.isClosed())
        connection = DriverManager.
                getConnection(CONNECTION_STRING, USER, PASSWORD);
        return connection;

    }

    @Override
    public void close() throws Exception {
        if (connection!=null && !connection.isClosed())
            connection.close();
    }

    {
        initDB();
    }

}
