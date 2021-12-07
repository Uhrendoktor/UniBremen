/**
 * Aufgabe 3: Schatzsuche nach Karte mit Rückweg (25 Punkte)
 *
 * Vervollständigt den Konstruktor und die Methode "bewege" entsprechend
 * ihrer Beschreibungen.
 *
 * Die Methode "main" soll zum Testen benutzt werden.
 *
 * Die Startposition der Figur und die Position des Schatzes werden bei
 * der Bewertung andere sein. Änderungen an der Methode "main" gehen nicht
 * in die Wertung ein. Quelltexte, die nicht kompilieren, werden nicht
 * gewertet.
 */
class Aufgabe3
{
    /** Die Figur, die gesteuert wird. */
    private final GameObject figur = new GameObject(19, 4, 2, "woman");
    
    // Hier weitere Attribute, falls ihr welche benötigt
    private final int treasureX;
    private final int treasureY;
    private final int[] path;
    private int steps;

    /**
     * Konstruktor einer Schatzsucher:in.
     * @param karte Die Karte enthält an einer Stelle ein 'X'. Dessen
     *         Koordinaten (x ist horizontal, y ist vertikal) markieren
     *         die Position des Schatzes.
     */
    Aufgabe3(final String[] karte) {
        int tx = 0;
        int ty = 0;
        outer:
        for(int y = 0; y < karte.length; y++){
            for(int x = 0; x < karte[y].length(); x++){
                if(karte[y].charAt(x)!='X') continue;
                tx = x;
                ty = y;
                break outer;
            }
        }
        this.treasureX = tx;
        this.treasureY = ty;
        this.steps = Math.abs(tx-figur.getX())+Math.abs(ty-figur.getY());
        this.path = new int[steps];
    }

    /**
     * Die Methode soll die Figur anfangs bei jedem Aufruf so drehen, dass
     * sie in Richtung des Schatzes guckt. Solange die Methode true
     * zurückliefert, wird die Figur danach automatisch einen Schritt
     * vorwärts, also in Blickrichtung, bewegt, wodurch sie anfangs dem
     * Schatz näher kommen sollte.
     *
     * Hat sie den Schatz erreicht, muss sie den Rückweg zur Startposition
     * antreten und zwar auf genau demselben Weg, den sie gekommen ist.
     *
     * Benötigte Methoden der Figur: getX, getY, setRotation
     *
     * x-Koordinaten wachsen nach rechts, y-Koordinaten nach unten. Die
     * Rotation ist 0 in Richtung +x, 1 in Richtung +y, 2 in Richtung -x
     * und 3 in Richtung -y.
     * 
     * @return true, wenn die Figur einen Schritt vorwärts machen soll.
     *         false, wenn zur Startposition zurückgekehrt wurde.
     */
    boolean bewege() {
        int difX = treasureX-figur.getX();
        int difY = treasureY-figur.getY();
        if(steps>0){
            int rotation;
            if(Math.abs(difX)<Math.abs(difY)) rotation = difY>0?1:3;
            else rotation = difX>0?0:2;
            figur.setRotation(rotation);
            path[--steps]=rotation;
            return true;
        }else if(Math.abs(steps)<path.length){
            figur.setRotation((path[Math.abs(steps--)]+2)%4);
            return true;
        }
        return false;
    }

    /**
     * Die Testmethode. Bei richtiger Implementierung nähert sich die Figur
     * dem Ziel ständig, bis es erreicht ist, kehrt dann auf demselben Weg
     * wieder zum Startpunkt zurück und bleibt stehen, wenn er erreicht
     * wurde. Insbesondere sollte dann auch das Programm anhalten.
     */
    static void main()
    {
        final Aufgabe3 instanz = new Aufgabe3(new String[] {
            "                    ",
            "      ____          ",
            "     |    |___      ",
            "    |  ~      |     ",
            "     |     ~   |    ",
            "      |_______|     ",
            "                    ",
            "                    ",
            "                    ",
            "            X  __   ",
            "              |  |  ",
            "              |__|  ",
            "               ||   ",
            "               ||   ",
            "      /|            ",
            "     /  |__         ",
            "    /  /   |        ",
            "   /  /     |       ",
            "                    ",
            "                    ",
            "                    ",
        });

        final GameObject figur = instanz.figur;
        final int[][] versatz = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        while (instanz.bewege()) {
            new GameObject(figur.getX(), figur.getY(), figur.getRotation(), "ghost");
            figur.setLocation(figur.getX() + versatz[figur.getRotation()][0],
                              figur.getY() + versatz[figur.getRotation()][1]);
            Game.sleep(100);
        }
    }
}
