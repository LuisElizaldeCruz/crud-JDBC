package org.example;

import java.sql.*;
import java.util.Scanner;

import static org.example.CrudOperations.mostrarRegistroUnico;

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
            System.out.println("3.- Actualizar datos por id");
            System.out.println("4.- Eliminar fila por id");
            System.out.printf("-1 Salir del programa%n");
            opcion = in.nextInt();
            switch (opcion) {
                case 1:
                    crOp.read(bd);
                    break;
                case 2:
                    crOp.insert(bd);
                    break;
                case 3:
                    Scanner in2 = new Scanner(System.in);
                    int id;

                    System.out.printf("intruduce el id del registro a actualizar%n");
                    id = in.nextInt();
                    mostrarRegistroUnico(bd, id);
                    crOp.update(bd, id);
                    break;
                case 4:
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