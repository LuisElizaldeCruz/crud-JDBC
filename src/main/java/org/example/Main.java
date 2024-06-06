import java.sql.*;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        connectionDB con = new connectionDB();
        CrudOperations crOp = new CrudOperations();
        Connection bd = con.conectarBD("carros");

        int opcion;
        Scanner in = new Scanner(System.in);

/*
        try {
            crOp.read(bd);
            Thread.sleep(2000);
            crOp.insertUser(bd,"silverado",2020, "azul");
            con.desconexion(bd);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        */

        do {
            System.out.println("introduzca una operacion a realizar a la base de datos");
            System.out.printf("1.-Mostra BD%n2.-insertar un dato%n-1.-Salir del programa%n");
            opcion = in.nextInt();
            switch (opcion) {
                case 1:
                    crOp.read(bd);
                    break;
                case 2:
                    crOp.insertUser(bd, "silverado", 2020, "verde");
                    break;
                default:
                    String res = (opcion != -1) ? "eliga una opcion valida  %n" : "";
                    System.out.printf(res);
                    break;
            }
        } while (opcion != -1);

    }
}