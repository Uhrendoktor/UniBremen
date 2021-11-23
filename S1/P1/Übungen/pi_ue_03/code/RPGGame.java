// Importieren der VK_*-Tastenkonstanten
import static java.awt.event.KeyEvent.*;
import java.util.Map;
import java.util.HashMap;

/**
 * Dies ist die Hauptklasse eines Spiels. Sie enthält die Hauptmethode, die zum
 * Starten des Spiels aufgerufen werden muss.
 *
 * @author Moritz Salger
 */
abstract class RPGGame extends Game
{
    public static final int FIELD_X = 5;
    public static final int FIELD_Y = 4;
    
    private static GameObject player;
    private static NPC[] NPCs = new NPC[2];
    private static GameObject goal;
    private static GameObject[][] background = new GameObject[FIELD_Y][FIELD_X];
    private static String[][] definedBackground = new String[][]{
        {"pe0", "pi0", "pl1", "we1", "g"},
        {"pe0", "pi0", "px", "b0", "pl1"},
        {"pe0", "pi0", "pt2", "wi1", "pe3"},
        {"pe0", "pi0", "pl2", "we3", "g"}
    };
    private static Map<String, String> backgroundDict = new HashMap<String, String>(){{
        put("g", "grass");
        put("pe0", "path-e-0");
        put("pe1", "path-e-1");
        put("pe2", "path-e-2");
        put("pe3", "path-e-3");
        put("pi0", "path-i-0");
        put("pi1", "path-i-1");
        put("pl0", "path-l-0");
        put("pl1", "path-l-1");
        put("pl2", "path-l-2");
        put("pl3", "path-l-3");
        put("pt0", "path-t-0");
        put("pt1", "path-t-1");
        put("pt2", "path-t-2");
        put("pt3", "path-t-3");
        put("px", "path-x");
        put("b0", "bridge-0");
        put("b1", "bridge-1");
        put("we0", "water-e-0");
        put("we1", "water-e-1");
        put("we2", "water-e-2");
        put("we3", "water-e-3");
        put("wi0", "water-i-0");
        put("wi1", "water-i-1");
        put("wl0", "water-l-0");
        put("wl1", "water-l-1");
        put("wl2", "water-l-2");
        put("wl3", "water-l-3");
        put("wt0", "water-t-0");
        put("wt1", "water-t-1");
        put("wt2", "water-t-2");
        put("wt3", "water-t-3");
        put("wx", "water-x");
    }};
    
    /** Das Spiel beginnt durch Aufruf dieser Methode. */
    static void main() {
        for(int y = 0; y < background.length; y++){
            for(int x = 0; x<background[y].length; x++){
                String _code = definedBackground[y][x];
                String _img = backgroundDict.get(_code);
                background[y][x] = new GameObject(x,y,0, _img);
            }
        }
        goal = new GameObject(4,2,0,"goal");
        player = new GameObject(0,3, 0,"laila");
        NPCs[0] = new NPC(new GameObject(1, 0, "claudius"), 3, 1);
        NPCs[1] = new NPC(new GameObject(4, 1, "claudius"), 5, 4);
        
        int key = 0;
        int x;
        int y;
        while(true){
            x = player.getX();
            y = player.getY();
            key = Game.getNextKey();
            switch(key){
                case VK_RIGHT:
                    player.setRotation(0);
                    x++;
                    break;
                case VK_LEFT:
                    player.setRotation(2);
                    x--;
                    break;
                case VK_DOWN:
                    player.setRotation(1);
                    y++;
                    break;
                case VK_UP:
                    player.setRotation(3);
                    y--;
                    break;
                default:
                    Game.playSound("error");
                    continue;
            }
            x = MinMax(0, FIELD_X-1, x);
            y = MinMax(0, FIELD_Y-1, y);
            
            if(definedBackground[y][x].startsWith("w") || (x==player.getX() && y==player.getY())) continue;
                
            player.setLocation(x, y);
            Game.playSound("step");

            for(NPC npc: NPCs){
                npc.act();
                if(checkCollision(player.getX(), player.getY(), npc.getX(), npc.getY())){
                    player.setVisible(false);
                    System.exit(0);
                }
            }
        }
    }
    private static int MinMax(int _min, int _max, int _value){
        return _value<_min?_min:_value>_max?_max:_value;
    }
    
    private static boolean checkCollision(int _x1, int _y1, int _x2, int _y2){
        return (_x1==_x2)&&(_y1==_y2);
    }
}
