// Importieren der VK_*-Tastenkonstanten
import java.util.Map;
import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Dies ist die Hauptklasse eines Spiels. Sie enthält die Hauptmethode, die zum
 * Starten des Spiels aufgerufen werden muss.
 *
 * @author Julius Walczynski
 */
abstract class RPGGame extends Game {
    
    private static Level level;
    /** Das Spiel beginnt durch Aufruf dieser Methode. */
    static void main() {
        
        level = new Level("Map.txt");
        
        Player player = level.getPlayer();
        while(true){
            for(Actor actor: level.getActors()){
                if(actor instanceof NPC) ((NPC)actor).addPlayerStep(player.getRotation());
                actor.act();
                if(actor == player) continue;
                if(checkCollision(player.getX(), player.getY(), actor.getX(), actor.getY())){
                    player.setVisible(false);
                    System.exit(0);
                }
            }
        }
    }
    
    private static boolean checkCollision(int _x1, int _y1, int _x2, int _y2){
        return (_x1==_x2)&&(_y1==_y2);
    }
    
    /**
     * @return the privatly stored Level object
     */
    public static Level getLevel(){
        return level;
    }
}
