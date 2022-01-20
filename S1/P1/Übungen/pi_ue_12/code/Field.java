import java.util.List;
import java.util.ArrayList;
import java.util.stream.*;

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
    
    private List<GameObject> tiles = new ArrayList<GameObject>();
    
    public Field(String[] _map){
        this.map=_map==null?new String[0]:_map;
        for(int i = 0; i < this.map.length; i++){
            if(this.map[i]==null) this.map[i]="";
        }
        IntStream.iterate(0, y->y<this.map.length, y->y+2).forEach(y->{
            IntStream.iterate(0, x->x<this.map[y].length(), x->x+2).forEach(x->{
                int signature = getNeighborhood(x,y);
                this.tiles.add(new GameObject(x/2, y/2, 0, NEIGHBORHOOD_TO_FILENAME[signature]));
            });
        });
    }
    
    private int getNeighborhood(int x, int y){
        return IntStream.range(0,4)
            .reduce(0, (total, direction) -> {
                return total=total|(isCellWhitespace(x,y,direction)<<direction);
            });
    }
    
    private int isCellWhitespace(int x, int y, int direction){
        int _x = x+(direction%2==0?-(direction-1):0);
        int _y = y+(direction%2==1?-(direction-2):0);
        return this.isCellWhitespace(_x,_y);
    }
    
    private int isCellWhitespace(int x, int y){
        return getCell(x,y)==' '?0:1;
    }
    
    private char getCell(int x, int y){
        if(y<0||x<0||y>=this.map.length||x>=this.map[y].length()){
            return ' ';
        }
        return this.map[y].charAt(x);
    }
    
    /**
     * @param x x-coordinate of tile to check from.
     * @param y y.coordinate of tile to check from.
     * @param direction the direction from the tile to the neighboring one to check.
     * @return if a tile has a neighboring and to itself connected pathway.   
    */
    public boolean hasNeighbor(int x, int y, int direction){
        return ((getNeighborhood(x*2, y*2)>>direction)&1)==1;
    }
    
    /**
     * hides all tile-gamobjects of the field, by making them invisble
     */
    public void hide(){
        for(GameObject tile: this.tiles){
            tile.setVisible(false);
        }
    }
}
