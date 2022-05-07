package appeac5p3;
// @author Albert Figuerola Gomez

public class Partida {

    public static final int EMPAT = 2;
    public static final int MAQUINA = 0;
    public static final int USUARI = 1;
    public static final int PEDRA = 0;
    public static final int PAPER = 1;
    public static final int TISORA = 2;

    public DadesPartida crearDadesPartida(String[] nomJugador, int numPartides) {
        DadesPartida dp = new DadesPartida();
        dp.inicialitza();
        String[] nomJugadors = null;
        dp.nomJugadors = nomJugadors;
        dp.numPartides = numPartides;

        return dp;
    }

    int quinaApostaGuanya(int apostaM, int apostaU) {
        int resultat;

        if (apostaM == apostaU) {
            resultat = EMPAT;
        } else if (apostaM == PEDRA && apostaU == PAPER) {
            resultat = USUARI;
        } else if (apostaM == PAPER && apostaU == TISORA) {
            resultat = USUARI;
        } else if (apostaM == TISORA && apostaU == PEDRA) {
            resultat = USUARI;
        } else {
            resultat = MAQUINA;
        }

        return resultat;
    }

    int quiGuanyaLaPartida(DadesPartida dp, int guanyador) {
        int quiGuanya;

        if (dp.partidesJugades < dp.numPartides) {
            switch (guanyador) {
                case MAQUINA ->
                    dp.tornsGuanyats[MAQUINA]++;
                case USUARI ->
                    dp.tornsGuanyats[USUARI]++;
                case EMPAT -> {
                    dp.tornsGuanyats[MAQUINA]++;
                    dp.tornsGuanyats[USUARI]++;
                }
            }
            dp.partidesJugades++;
        }

        // GUANYADOR ACUMULAT
        if (dp.tornsGuanyats[MAQUINA] == dp.tornsGuanyats[USUARI]) {
            quiGuanya = EMPAT;
        } else if (dp.tornsGuanyats[MAQUINA] > dp.tornsGuanyats[USUARI]) {
            quiGuanya = MAQUINA;
        } else {
            quiGuanya = USUARI;
        }

        return quiGuanya; // resultat 
    }
}
