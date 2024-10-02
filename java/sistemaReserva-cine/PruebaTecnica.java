package javaapplication7;

import java.util.Scanner;

public class PruebaTecnica {

    // teatro 10x10
    // solo permitir reserva si un asiento esta en estado 'L'
    // caso contrario (estado 'x') se debera poder elegir otro
    // si esta en estado L pasa automaticamente a estado x
    // ingresar valor por el usuario sin limite.
    // validaciones para esas 10x10 asientos
    // no se permite sobreventa de asientos(fuera de la matriz)
    // opcion para mostrar los asientos por consola

    String[][] asientos = new String[10][10];

    public PruebaTecnica() {
        crearAsientosVacios();
        reserva("1", "1");
    }

    public Boolean esEntero(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void menu() {
        System.out.println("1: mostrar asientos");
        System.out.println("2: reservar asientos");
        System.out.println("0: cerrar programa");
    }

    public boolean reserva(String input1, String input2) {
        if (esEntero(input1) && esEntero(input2)) {
            int row = Integer.parseInt(input1) - 1;
            int colum = Integer.parseInt(input2) - 1;
            if (row < 10 && row >= 0 && colum < 10 && colum >= 0) {
                if (asientos[row][colum].equals("L")) {
                    asientos[row][colum] = "X";
                    System.out.println("reservado con exito");
                    imprimirAsientos();
                    return true;
                } else {
                    System.out.println("el asiento ya esta ocupado");
                    return false;
                }
            }

        }
        System.out.println("parametros incorrectos, por favor ingrese valores dentro de la matriz 10x10");
        return false;
    }

    public void crearAsientosVacios() {
        for (int row = 0; row < asientos.length; row++) {
            for (int colum = 0; colum < asientos[row].length; colum++) {
                asientos[row][colum] = "L";
            }
        }
    }

    public void imprimirAsientos() {
        for (int row = 0; row < asientos.length; row++) {
            for (int colum = 0; colum < asientos[row].length; colum++) {
                System.out.print("[ " + asientos[row][colum] + " ] ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        // cargar "mapa de asientos" con 'x' los ocupados y 'L' los libres
        // al comienzo todos deben estar libres

        PruebaTecnica pt = new PruebaTecnica();
        Scanner sc = new Scanner(System.in);

        boolean exitoReserva;
        boolean seguir = true;
        do {
            exitoReserva = false;
            pt.menu();
            String option = sc.nextLine();
            if (option.equals("1")) {
                pt.imprimirAsientos();
            } else if (option.equals("2")) {
                System.out.println("ingresa fila");
                String input1 = sc.nextLine();
                System.out.println("ingresa columna");
                String input2 = sc.nextLine();
                exitoReserva = pt.reserva(input1, input2);
            } else if (option.equals("0")) {
                System.out.println("adios.. gracias por confiar en nosotros");
                break;
            } else {
                System.out.println("error: valor ingresado es invalido");
            }

            if (exitoReserva) {
                System.out.println("1: seguir reservando? o 0: salir");
                String op = sc.nextLine();
                if (pt.esEntero(op)) {
                    if (Integer.parseInt(op) == 1) {
                        seguir = true;
                    } else if (Integer.parseInt(op) == 0) {
                        seguir = false;
                    } else {
                        System.err.println("opcion incorrecta");
                    }
                }
            }
        } while (seguir);

        sc.close();

    }
}
