import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CrudOperations {
    public static void read(Connection con) {
        //variables para la base de datos
        int id, year;
        String model, color;

        String sql = "SELECT * FROM camionetas";
        Statement stmt;
        ResultSet rs;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.printf("%-6s %-10s %-10s %-10s%n", "id", "model", "year", "color");

            while (rs.next()) {
                id = rs.getInt("id");
                model = rs.getString("model");
                year = rs.getInt("year");
                color = rs.getString("color");

                System.out.printf("%-6s %-10s %-10s %-10s%n", id, model, year, color);
            }
            System.out.printf("%n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void insertUser(Connection con, String model, int year, String color) {
        String sql = "INSERT INTO camionetas(model, year, color)" +
                "VALUES ('" + model + "', " + year + ", '" + color + "')";
        Statement stmt;
        int result;
        System.out.println("query");
        System.out.println(sql);

        try {
            stmt = con.createStatement();
            result = stmt.executeUpdate(sql);
            if (result == 1) {
                System.out.println("Los datos fueron agregados");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        read(con);
    }

    public static void Delete(Connection con, int id) {
        String sql = "DELETE FROM camionetas where id = '" + id + "'";
        Statement stmt;
        int result;

        try {
            stmt = con.createStatement();
            result = stmt.executeUpdate(sql);

            if (result == 1) {
                System.out.println("Se borro el usuario id " + id);
            } else {
                System.out.println("El usuario " + id + " no existe");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        read(con);
    }
}
