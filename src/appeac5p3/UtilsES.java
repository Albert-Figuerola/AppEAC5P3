package appeac5p3;

import static appeac5p3.Partida.*;
import java.util.Scanner;

public class UtilsES {

    static final short POS_NOM = 0;
    static final short POS_PARTIDES_GUANYADES = 1;
    static final String MISSATGE_LINIA_DOBLE_SEPARACIO = "==================================================================";
    static final String MISSATGE_LINIA_UNICA_SEPARACIO = "------------------------------------------------------------------";
    static final String NOM_VIDEOJOC = "PEDRA/PAPER/TISORA - EL VIDEOJOC";
    static final String MISSATGE_TRIA_PARTIDES = "Quantes jugades vols realitzar(3-5)?";
    static final String MISSATGE_ERROR_OPCIO = "El rang vàlid de jugades a escollir es troba entre 3 i 5 ambdúes inclosos. Torneu-ho a intentar.";
    static final String MISSATGE_ERROR_NO_NUMERO = "Error el que heu escrit no és un número. Torneu-ho a intentar";
    static final int MIN_JUGADES = 3;
    static final int MAX_JUGADES = 5;

    static int demanarQuantesJugades() {
        int numJugades = 0;
        boolean correcte = false;
        do {
            numJugades = demanarEnter(MISSATGE_TRIA_PARTIDES, MISSATGE_ERROR_NO_NUMERO);
            if (numJugades < MIN_JUGADES || numJugades > MAX_JUGADES) {
                System.out.println(MISSATGE_ERROR_OPCIO);
            } else {
                correcte = true;
            }
        } while (!correcte);
        return numJugades;
    }

    static String demanarString(String missatge, String missatgeError) {
        Scanner sc = new Scanner(System.in);
        System.out.println(missatge);
        String textIntroduit = sc.nextLine();

        while (textIntroduit.isEmpty()) {
            System.out.println(missatgeError);
            System.out.println(missatge);
            textIntroduit = sc.nextLine();
        }
        return textIntroduit;
    }

    static int demanarEnter(String missatge, String missatgeError) {
        Scanner sc = new Scanner(System.in);
        int numPartides;
        boolean correcte;
        do {
            System.out.println(missatge);
            correcte = sc.hasNextInt();
            if (!correcte) {
                sc.next();
                System.out.println(missatgeError);
            }
        } while (!correcte);
        numPartides = sc.nextInt();
        sc.nextLine();
        return numPartides;
    }

    static int demanarAposta() {
        Scanner sc = new Scanner(System.in);
        int aposta = 0;
        do {
            System.out.println("Introdueix la teva aposta: ");
            System.out.println("0 - PEDRA");
            System.out.println("1 - PAPER");
            System.out.println("2 - TISORES");
            System.out.print("Opció escollida: ");
            if (sc.hasNextInt()) {
                aposta = sc.nextInt();
            } else {
                sc.next();
            }
        } while (aposta < 0 || aposta > 2);

        return aposta;
    }

    static void mostrarGuanyadorPartida(String[][] dadesJugadors, int[] tornsGuanyats, int guanyador, int POS_NOM,
            int posJug) {
        String[] guanyadorPartida = { "la màquina guanya.", "el jugador guanya.", "empat." };
        System.out.println("");
        System.out.println("***********************");
        System.out.println("  FINAL DE LA PARTIDA");
        System.out.println("***********************");
        System.out.print(String.format("%9s", "Màquina"));
        System.out.print(String.format("%9s", dadesJugadors[posJug][POS_NOM]));

        System.out.println();
        System.out.println("========================");

        System.out.print(String.format("%7d", tornsGuanyats[MAQUINA]));
        System.out.print(String.format("%9d", tornsGuanyats[USUARI]));

        System.out.println("\n");
        System.out.println("Resultat: " + guanyadorPartida[guanyador]);
    }

    static void mostrarTitol(String titolPantalla) {
        System.out.println("\n");
        System.out.println(MISSATGE_LINIA_DOBLE_SEPARACIO);
        System.out.println("\t\t " + NOM_VIDEOJOC);
        System.out.println(MISSATGE_LINIA_DOBLE_SEPARACIO);
        System.out.println("\t\t PANTALLA DE CONFIGURACIÓ DEL JOC");
        System.out.println(MISSATGE_LINIA_UNICA_SEPARACIO);
    }

}
