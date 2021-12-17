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
    
    private String[] map;
    public final int SIZE_X;
    public final int SIZE_Y;
    
    public Field(String[] _map){
        this.map=_map;
        int sx = 0;
        for(int y = 0; y < this.map.length; y+=2){
            int length = this.map[y].length();
            if(length>sx) sx=length;
            for(int x = 0; x < length; x+=2){
                int signature = getNeighborhood(x,y);
                new GameObject(x/2, y/2, 0, NEIGHBORHOOD_TO_FILENAME[signature]);
            }
        }
        this.SIZE_X = sx;
        this.SIZE_Y = this.map.length;
    }
    
    public int getNeighborhood(int x, int y){
        return isCellWhitespace(x+1,y)<<0
              |isCellWhitespace(x,y+1)<<1
              |isCellWhitespace(x-1,y)<<2
              |isCellWhitespace(x,y-1)<<3;
    }
    
    private int isCellWhitespace(int x, int y){
        return getCell(x,y)==' '?0:1;
    }
    
    public char getCell(int x, int y){
        if(y<0||x<0||y>=this.map.length||x>=this.map[y].length()){
            return ' ';
        }
        return this.map[y].charAt(x);
    }
    
    public boolean hasNeighbor(int x, int y, int direction){
        return ((getNeighborhood(x*2, y*2)>>direction)&1)==1;
    }
    
    /** Ein Testfall, der alle Nachbarschaften enthält. */
    static void test()
    {
        new GameObject.Canvas(5, 5, 96, 96);

        // Einkommentieren, sobald Konstruktor vorhanden
        new Field(new String[] {
            "O-O-O-O  ",
            "|   |    ",
            "O O-O-O O",
            "| | | | |",
            "O-O-O-O-O",
            "| | | | |",
            "O O-O-O O",
            "    |   |",
            "O-O-O-O-O"
        });
    }
}
