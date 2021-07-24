import lombok.Data;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Data
public class DBConnection implements AutoCloseable{

    private Connection connection;

    private void initDB () {
        Flyway flyway = Flyway.configure().dataSource(
                "jdbc:h2:file:~/DATABASE", "databaseUser", "databasePassword").load();
        flyway.migrate();
    }

    public Connection get() throws ClassNotFoundException, SQLException {
        if (connection == null)
        connection = DriverManager.
                getConnection("jdbc:h2:file:~/DATABASE", "databaseUser", "databasePassword");
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
