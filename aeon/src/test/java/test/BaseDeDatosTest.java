package test;

import org.testng.annotations.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import utilities.Utilities;
public class BaseDeDatosTest extends Utilities {

    @Test
    public void testDatabaseConnectionAndQuery() {
        try {
            // Conectar a la base de datos utilizando el método de esta clase
            Connection connection = connectToDatabase();

            // Realizar operaciones en la base de datos
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);

            // Procesar los resultados de la consulta
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                // ... otros campos ...
                System.out.println("Username: " + name);
                System.out.println("Email: " + email);
            }

            // Cerrar la conexión a la base de datos
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
