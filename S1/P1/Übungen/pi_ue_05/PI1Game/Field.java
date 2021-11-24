/**
 * Diese Klasse repräsentiert ein Spielfeld. Ihr Konstruktor bekommt dieses als
 * String-Array übergeben.
 */
class Field
{
    /**
     * Die Dateinamen der Bodengitterelemente, die direkt mit einer
     * Rotation 0 verwendet werden können. Der Index ergibt sich
     * aus der Summe der folgenden Zahlen:
     * 1: In Richtung 0 (+1, 0) gibt es eine Verbindung.
     * 2: In Richtung 1 (0, +1) gibt es eine Verbindung.
     * 4: In Richtung 2 (-1, 0) gibt es eine Verbindung.
     * 8: In Richtung 3 (0, -1) gibt es eine Verbindung.
     */
    private static final String[] NEIGHBORHOOD_TO_FILENAME = {
        "grass",
        "path-e-0",
        "path-e-1",
        "path-l-0",
        "path-e-2",
        "path-i-0",
        "path-l-1",
        "path-t-1",
        "path-e-3",
        "path-l-3",
        "path-i-1",
        "path-t-0",
        "path-l-2",
        "path-t-3",
        "path-t-2",
        "path-x"
    };
    
    /** Ein Testfall, der alle Nachbarschaften enthält. */
    static void test()
    {
        new GameObject.Canvas(5, 5, 96, 96);

        // Einkommentieren, sobald Konstruktor vorhanden
        /*new Field(new String[] {
            "O-O-O-O  ",
            "|   |    ",
            "O O-O-O O",
            "| | | | |",
            "O-O-O-O-O",
            "| | | | |",
            "O O-O-O O",
            "    |   |",
            "O-O-O-O-O"
        });*/
    }
}
