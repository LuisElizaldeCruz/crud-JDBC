package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CrudOperations {
    public static void read(Connection con) {
        //variables para la base de datos
        int id, year;
        String model, color;

        String sql = "SELECT * FROM automoviles";
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

    public static void insertUser(Connection con) {
        String model, color;
        int year, result;
        Statement stmt;


        Scanner in = new Scanner(System.in);

        System.out.println("intruduce el modelo de tu auto");
        model = in.next();
        System.out.println("intruduce el año de tu auto");
        year = in.nextInt();
        System.out.println("intruduce el color de tu auto");
        color = in.next();

        String sql = "INSERT INTO automoviles(model, year, color)" +
                "VALUES ('" + model + "', " + year + ", '" + color + "')";

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

    public static void Delete(Connection con) {
        int id, result;
        Statement stmt;
        Scanner in = new Scanner(System.in);

        System.out.println("Introduce un id de una fila a eliminar en la base de datos");
        id = in.nextInt();

        String sql = "DELETE FROM automoviles where id = '" + id + "'";

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
