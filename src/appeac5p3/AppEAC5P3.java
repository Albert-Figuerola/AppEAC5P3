package appeac5p3;
// @author Albert Figuerola Gomez

public class AppEAC5P3 {

    static final String MISSATGE_LINIA_SEPARACIO = "----------------------------------------------------------------------------";
    static final String MISSATGE_TITOL_MENU = "                       PEDRA/PAPER/TISORA EL VIDEOJOC";
    static final String MISSATGE_TRII_OPCIO = "Trieu una opció";
    static final String MISSATGE_ERROR_NO_NUMERO = "Error el que heu escrit no és un número. Torneu-ho a intentar";
    static final String MISSATGE_COMIAT = "Fins la propera";
    static final String MISSATGE_TITOL_IDENTIFICACIO = "PANTALLA D'IDENTIFICACIÓ DEL JUGADOR";
    static final String MISSATGE_TRII_OPCIO_NOM_JUGADOR = "Introduïu el nom del jugador: ";
    static final String MISSATGE_TITOL_MOSTRAR_DADES = "               MOSTRAR DADES D'UN JUGADOR";
    static final String MISSATGE_ERROR_DADES_PLENES = "No s'ha pogut enregistrar el jugador. S'ha sobrepassat la capacitat màxima";
    static final String INTRODUIR_APOSTA = "Introdueix la teva aposta: ";

    static final short NUM_JUGADORS = 30;
    static final short NUM_DADES_JUGADORS = 2;
    static final short POS_NOM = 0;
    static final short POS_PARTIDES_GUANYADES = 1;

    String[] menuPrincipal = {
            "1. Identificar jugador",
            "0. Sortir"
    };

    public static void main(String[] args) {
        AppEAC5P3 prg = new AppEAC5P3();
        prg.inici();
    }

    void inici() {
        String[][] dadesJugadors = {
                { "Adria", "5" }, { "Agnes", "0" }, { "Anna", "3" }, { "Arnau", "2" },
                { "Beth", "9" }, { "Blanca", "6" }, { "Bruna", "1" }, { "Carla", "7" },
                { "Cesc", "0" }, { "Clara", "5" }, { "Duna", "2" }, { "Laia", "4" },
                { "Eloi", "3" }, { "Emma", "6" }, { "Gerard", "8" }, { "Guillem", "5" },
                { "Lluc", "7" }, { "Jordi", "1" }, { "Martí", "5" }, { "Max", "3" },
                { "Neus", "6" }, { "Nico", "2" }, { "Nina", "1" }, { "Noa", "6" },
                { "Nora", "1" }, { "Nuria", "8" }, { "Oriol", "6" }, { "Pau", "1" },
                { "Paula", "9" }, { "Pep", "0" }, { "Pol", "10" }, { "Queralt", "3" },
                { "Quim", "1" }, { "Rita", "7" }, { "Roc", "8" }, { "Roger", "9" },
                { "Sergi", "3" }, { "Txell", "1" }, { "Xavi", "9" }, { "Alex", "4" },
                { "Èlia", "0" }, { "Èric", "6" }, { "", "" }, { "", "" }
        };
        int opcio;
        do {
            mostrarMenu(menuPrincipal);
            opcio = UtilsES.demanarEnter(MISSATGE_TRII_OPCIO, MISSATGE_ERROR_NO_NUMERO);
            switch (opcio) {
                case 1:
                    opcioMenuIdentificar(dadesJugadors);
                    break;
                case 0:
                    System.out.println(MISSATGE_COMIAT);
                    break;
            }
        } while (opcio != 0);
    }

    void mostrarMenu(String[] menu) {
        separarLinies(2);
        System.out.println(MISSATGE_LINIA_SEPARACIO);
        System.out.println(MISSATGE_TITOL_MENU);
        System.out.println(MISSATGE_LINIA_SEPARACIO);

        for (int i = 0; i < menu.length; ++i) {
            System.out.println(menu[i]);
        }
    }

    void opcioMenuIdentificar(String[][] dadesJugadors) {
        int posJug;
        separarLinies(4);
        System.out.println(MISSATGE_LINIA_SEPARACIO);
        System.out.println(MISSATGE_TITOL_IDENTIFICACIO);
        System.out.println(MISSATGE_LINIA_SEPARACIO);

        String nom = UtilsES.demanarString(MISSATGE_TRII_OPCIO_NOM_JUGADOR, MISSATGE_ERROR_NO_NUMERO);
        posJug = cercaPosJugador(nom, dadesJugadors);
        if (posJug < 0) {
            posJug = enregistrarNouJugador(nom, dadesJugadors);
        }
        if (posJug < 0) {
            System.out.println(MISSATGE_ERROR_DADES_PLENES);
        } else {
            mostraDadesJugador(posJug, dadesJugadors);
            separarLinies(1);
            inciPartida(dadesJugadors, nom, posJug);
        }
    }

    // INICI PARTIDA
    void inciPartida(String[][] dadesJugadors, String nom, int posJug) {
        System.out.println("Començant partida...");
        separarLinies(1);
        String[] apostes = { "PEDRA", "PAPER", "TISORES" };
        Partida prt = new Partida();
        DadesPartida dpt = new DadesPartida();
        dpt.numPartides = UtilsES.demanarQuantesJugades();
        System.out.println("Has demanat fer " + dpt.numPartides + " jugades.");
        do {
            // dpt.apostaM = dpt.apostaM; // = rnd.nextInt(max + min) + min;
            separarLinies(1);
            dpt.apostaU = UtilsES.demanarAposta();
            separarLinies(1);
            System.out.println("L'aposta del jugador és: " + apostes[dpt.apostaU] + ".");
            System.out.println("L'aposta de la máquina és: " + apostes[dpt.apostaM] + ".");
            // RESOL QUI ES EL GUANYADOR D'UNA PARTIDA
            int guanyador = prt.quinaApostaGuanya(dpt.apostaM, dpt.apostaU);
            // ACUMULA QUI VA GUANYANT LES PARTIDES I FA DE COMPTADOR DE PARTIDES
            prt.quiGuanyaLaPartida(dpt, guanyador);
            // MOSTRA RESULTAT DE LA PARTIDA JUGADA
            UtilsES.mostrarGuanyadorPartida(dadesJugadors, dpt.tornsGuanyats, guanyador, POS_NOM, posJug);
        } while (dpt.numPartides != dpt.partidesJugades);
        // ACTUALITZA LES PARTIDES GUANYADES DEL JUGADOR
        actualitzarPartidesGuanyades(dadesJugadors, dpt.tornsGuanyats, posJug, POS_PARTIDES_GUANYADES);
        // MOSTRA DADES DEL JUGADOR ACTUALITZADES TOTAL
        mostraDadesJugador(posJug, dadesJugadors);
    }

    void actualitzarPartidesGuanyades(String dadesJugadors[][], int[] tornsGuanyats, int posJug,
            int POS_PARTIDES_GUANYADES) {
        int partidesActualsGuanyades = Integer.parseInt(dadesJugadors[posJug][POS_PARTIDES_GUANYADES]);
        int novesPartidesGuanyades = tornsGuanyats[1];
        int totalPartidesGuanyades = partidesActualsGuanyades + novesPartidesGuanyades;
        dadesJugadors[posJug][POS_PARTIDES_GUANYADES] = Integer.toString(totalPartidesGuanyades);
    }

    void mostraDadesJugador(int posJugador, String[][] dadesJugadors) {
        separarLinies(2);
        System.out.println(MISSATGE_LINIA_SEPARACIO);
        System.out.println(MISSATGE_TITOL_MOSTRAR_DADES);
        System.out.println(MISSATGE_LINIA_SEPARACIO);
        System.out.println("PARTIDES GUANYADES     NOM JUGADOR");
        System.out.println(MISSATGE_LINIA_SEPARACIO);
        System.out.print("       ");
        System.out.print(dadesJugadors[posJugador][POS_PARTIDES_GUANYADES]);
        System.out.print("                ");
        System.out.println(dadesJugadors[posJugador][POS_NOM]);
    }

    void separarLinies(int linies) {
        for (int i = 0; i < linies; ++i) {
            System.out.println();
        }
    }

    int cercaPosJugador(String nom, String[][] dadesJugadors) {
        boolean trobat = false;
        int posArray = 0;
        int pos = 0;

        while (!trobat) {
            if (dadesJugadors[posArray][0].equals(nom)) {
                pos = posArray;
                trobat = true;
            } else if (posArray == dadesJugadors.length - 1) {
                pos = -1;
                trobat = true;
            }
            posArray++;
        }
        return pos;
    }

    int enregistrarNouJugador(String nom, String[][] dadesJugadors) {
        int posBuida = 0;
        while (posBuida < dadesJugadors.length && !dadesJugadors[posBuida][POS_NOM].equals("")) {
            posBuida++;
        }
        if (posBuida < dadesJugadors.length) {
            enregistrarDadesJugador(posBuida, nom, 0, dadesJugadors);
        } else {
            posBuida = -1;
        }
        return posBuida;
    }

    void enregistrarDadesJugador(int pos, String nom, int partidesGuanyades, String[][] dadesJugadors) {
        dadesJugadors[pos][POS_NOM] = nom;
        dadesJugadors[pos][POS_PARTIDES_GUANYADES] = Integer.toString(partidesGuanyades);
    }

}
