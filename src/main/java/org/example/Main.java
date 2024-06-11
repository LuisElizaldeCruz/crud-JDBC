package org.example;

import java.sql.*;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        ConnectionDB con = new ConnectionDB();
        CrudOperations crOp = new CrudOperations();
        Connection bd = con.conectarBD("carros");

        int opcion;
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("introduzca una operación a realizar a la base de datos");
            System.out.println("1.- Mostra BD");
            System.out.println("2.- Insertar un dato");
            System.out.println("3.- Eliminar fila por id");
            System.out.printf("-1 Salir del programa%n");
            opcion = in.nextInt();
            switch (opcion) {
                case 1:
                    crOp.read(bd);
                    break;
                case 2:
                    crOp.insertUser(bd);
                    break;
                case 3:
                    crOp.Delete(bd);
                    break;
                default:
                    String res = (opcion != -1) ? "eliga una opcion valida  %n" : "";
                    System.out.printf(res);
                    break;
            }
        } while (opcion != -1);
        con.desconexion(bd);

    }
}