package appeac5p3;
// @author Albert Figuerola Gomez

import java.util.Random;

public class DadesPartida {

    public static final int NUM_JUGADORS = 2;
    public static final int NUM_PARTIDES = 2;

    int numPartides;
    int partidesJugades;
    String[] nomJugadors = new String[NUM_JUGADORS];
    int[] tornsGuanyats = new int[NUM_PARTIDES];
    int apostaU;
    int apostaM;
    int min = 0;
    int max = 3;

    public void inicialitza() {
        Random rnd = new Random();
        numPartides = 0;
        partidesJugades = 0;
        apostaU = 0;
        apostaM = rnd.nextInt(max + min) + min;

    }
}
