package org.example;

import java.sql.*;

public class ConnectionDB {

    public static Connection conectarBD(String db) {
        Connection conexion;
        String host = "jdbc:mysql://localhost/";
        String user = "student";
        String password = "student";

        System.out.println("Conectando...");
        try {
            conexion = DriverManager.getConnection(host + db, user, password);
            System.out.println("Conexion exitosa");

            //validacion si existe alguna tabla en la base de datos
            DatabaseMetaData dbm = conexion.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "%", null);

            if (!tables.next()) {
                System.out.println("La base de datos no tiene tablas.");
                // Aquí puedes tomar la decisión que necesites en caso de que no existan tablas
                System.out.println("La tabla 'autos' no existe. Creando la tabla...");

                Statement statement = conexion.createStatement();
                String sql = "CREATE TABLE autos (" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "model VARCHAR(45) NOT NULL, " +
                        "year INT NOT NULL, " +
                        "color VARCHAR(45) NOT NULL, " +
                        "PRIMARY KEY(id)" +
                        ");";
                statement.executeUpdate(sql);

                System.out.println("La tabla 'autos' ha sido creada exitosamente.");
            } else {
                // Aquí puedes tomar la decisión que necesites en caso de que existan tablas
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return conexion;
    }

    public static void desconexion(Connection desDB) {
        try {
            desDB.close();
            System.out.println("Desconectando");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
