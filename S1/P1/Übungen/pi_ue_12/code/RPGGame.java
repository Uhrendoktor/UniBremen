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
        while(player.isVisible()) level.getActors().forEach(actor->actor.act());
        System.exit(0);
    }
    
    /**
     * @return the privatly stored Level object
     */
    public static Level getLevel(){
        return level;
    }
}
