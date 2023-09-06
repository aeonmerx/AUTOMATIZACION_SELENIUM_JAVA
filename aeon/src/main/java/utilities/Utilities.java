package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Utilities {

	  // Método para conectarse a la base de datos
    public   Connection connectToDatabase() throws SQLException {
        String host = "bewqutlbfrmppsm07ai9-mysql.services.clever-cloud.com";
        String databaseName = "bewqutlbfrmppsm07ai9";
        String user = "unl1rzmcxuwvloiz";
        String password = "TxscGRGru6BhtF7nNvig";
        int port = 3306;
        String jdbcUrl = "jdbc:mysql://" + host + ":" + port + "/" + databaseName;
        Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
        return connection;
    }
 // Método para ejecutar una consulta SELECT en la base de datos y obtener resultados
    public List<String> executeSelectQuery(String query) throws SQLException {
        List<String> results = new ArrayList<>();
        try (Connection connection = connectToDatabase();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                results.add(resultSet.getString(1)); // Agrega el resultado a la lista (ajusta según tu consulta)
            }
        }
        return results;
    }

    // Método para ejecutar una consulta UPDATE o DELETE en la base de datos
    public int executeUpdateQuery(String query) throws SQLException {
        try (Connection connection = connectToDatabase();
             Statement statement = connection.createStatement()) {
            return statement.executeUpdate(query);
        }
    }
    // Método para cerrar una conexión de base de datos
    public void closeConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    // Método para ejecutar una consulta INSERT en la base de datos
    public int executeInsertQuery(String query) throws SQLException {
        try (Connection connection = connectToDatabase();
             Statement statement = connection.createStatement()) {
            return statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        }
    }

    // Método para obtener el último ID generado en una operación INSERT
    public int getLastInsertedId(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.getGeneratedKeys()) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return -1; // Si no se encuentra ningún ID generado
            }
        }
    }

    // Método para convertir un ResultSet a una lista de objetos
    public List<Object> resultSetToList(ResultSet resultSet) throws SQLException {
        List<Object> resultList = new ArrayList<>();
        while (resultSet.next()) {
            // Agregar lógica para crear objetos a partir de las filas del ResultSet
            // resultList.add(new Objeto(resultSet.getInt("columna1"), resultSet.getString("columna2")));
        }
        return resultList;
    }
}
