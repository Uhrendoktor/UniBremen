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
abstract class RPGGame extends Game {    
    private static GameObject player;
    private static NPC[] NPCs = new NPC[2];
    private static GameObject goal;
    private static Field field;
        
    /** Das Spiel beginnt durch Aufruf dieser Methode. */
    static void main() {
        field = new Field(new String[] {
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
        
        goal = new GameObject(4,2,0,"goal");
        player = new GameObject(0,3, 0,"laila");
        NPCs[0] = new NPC(new GameObject(1, 0, 0, "claudius"), true);
        NPCs[1] = new NPC(new GameObject(2, 2, 0, "claudius"), false);
        
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
            x = MinMax(0, field.SIZE_X-1, x);
            y = MinMax(0, field.SIZE_Y-1, y);
            
            if(!field.hasNeighbor(player.getX(), player.getY(), player.getRotation()) || (x==player.getX() && y==player.getY())) continue;
                
            player.setLocation(x, y);
            Game.playSound("step");

            for(NPC npc: NPCs){
                npc.addPlayerStep(player.getRotation());
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
    
    public static int getFieldX(){
        return field.SIZE_X;
    }
    
    public static int getFieldY(){
        return field.SIZE_Y;
    }
    
    public static Field getField(){
        return field;
    }
    
    public static GameObject getPlayer(){
        return player;
    }
}
