/**
 * Aufgabe 2: Schatzsuche nach Karte (25 Punkte)
 *
 * Vervollständigt den Konstruktor und die Methode "bewege" entsprechend
 * ihrer Beschreibungen.
 *
 * Die Methode "main" soll zum Testen benutzt werden.
 *
 * Die Startposition der Figur und die Position des Schatzes werden bei
 * der Bewertung andere sein. Änderungen an der Methode "main" gehen nicht
 * in die Bewertung ein. Quelltexte, die nicht kompilieren, werden nicht
 * gewertet.
 */
class Aufgabe2
{
    /** Die Figur, die gesteuert wird. */
    private final GameObject figur = new GameObject(6, 17, 0, "claudius");

    // Hier weitere Attribute, falls ihr welche benötigt
    private final int treasureX;
    private final int treasureY;

    /**
     * Konstruktor einer Schatzsucher:in.
     * @param karte Die Karte enthält an einer Stelle ein 'X'. Dessen
     *         Koordinaten (x ist horizontal, y ist vertikal) markieren
     *         die Position des Schatzes.
     */
    Aufgabe2(final String[] karte) {
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
    }

    /**
     * Die Methode soll die Figur bei jedem Aufruf so drehen, dass sie in
     * Richtung des Schatzes guckt. Solange die Methode true zurückliefert,
     * wird die Figur danach automatisch einen Schritt vorwärts, also in
     * Blickrichtung, bewegt, wodurch sie dem Schatz näher kommen sollte.
     *
     * Benötigte Methoden der Figur: getX, getY, setRotation
     *
     * x-Koordinaten wachsen nach rechts, y-Koordinaten nach unten. Die
     * Rotation ist 0 in Richtung +x, 1 in Richtung +y, 2 in Richtung -x
     * und 3 in Richtung -y.
     *
     * @return true, wenn die Figur einen Schritt vorwärts machen soll.
     *         false, wenn der Schatz erreicht wurde.
     */
    boolean bewege() {
        int difX = treasureX-figur.getX();
        int difY = treasureY-figur.getY();
        int disX = Math.abs(difX);
        int disY = Math.abs(difY);
        if(disX<=0 && disY<=0) return false;
        if(disX<disY) figur.setRotation(difY>0?1:3);
        else figur.setRotation(difX>0?0:2);
        return true;
    }

    /**
     * Die Testmethode. Bei richtiger Implementierung nähert sich die Figur
     * dem Ziel ständig und bleibt stehen, wenn es erreicht wurde (Füße auf
     * dem Kreuz).
     * Insbesondere sollte dann auch das Programm anhalten.
     */
    static void main()
    {
        final Aufgabe2 instanz = new Aufgabe2(new String[] {
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
            figur.setLocation(figur.getX() + versatz[figur.getRotation()][0],
                              figur.getY() + versatz[figur.getRotation()][1]);
            Game.sleep(100);
        }
    }
}
