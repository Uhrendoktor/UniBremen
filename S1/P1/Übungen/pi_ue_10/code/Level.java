import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Generiert aus einer Textdatei mit angegeben Namen ein Field mit NPCs und einem Player
 * 
 * @author Julius Walczynski
 * 
 */
public class Level {
    
    private Field field;
    public List<Actor> actors = new ArrayList<Actor>();
    
    public final String[] NPCNames = new String[]{
        "child",
        "claudius",
        "laila",
        "woman"
    };
    
    public Level(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            
            List<String> map = new ArrayList<String>();
            
            String line = br.readLine();
            while(line != null){
                map.add(line);
                line = br.readLine();
            }
            
            String[] tmp = new String[map.size()];
            map.toArray(tmp);
            
            for(int i = 0; i < tmp.length; i++) tmp[i] = tmp[i].replaceAll("[\\x21-\\x2c\\x2e-\\x7b\\x7d-\\x7e]", "O");
            this.field = new Field(tmp);
            
            for(int y = 0; y < map.size(); y+=2){
                for(int x = 0; x < map.get(y).length(); x+=2){
                    char info = map.get(y).charAt(x);
                    if(info == 'O'|| info == ' ') continue;
                    
                    int rotation = info&3;
                    int actorID = (info>>2)-9;
                    
                    if(actorID<0||actorID>NPCNames.length) throw new IllegalArgumentException("invalid character[s] in map file");
                    
                    if(actorID == 0){
                        if(actors.size()>0 && (actors.get(0) instanceof Player)) throw new IllegalArgumentException("more than one symbolized player in map");
                        actors.add(0, new Player(x/2, y/2, rotation, this.field));
                    }else{
                        actors.add(new NPC(x/2, y/2, rotation, NPCNames[actorID-1], this.field));
                    }
                }
            }
            if(actors.size()<=0||!(actors.get(0) instanceof Player)) throw new IllegalArgumentException("no player on map");
        } catch(FileNotFoundException e){
            throw new IllegalArgumentException("Invalid filename.");
        } catch(IOException e){
            throw new IllegalArgumentException("something wen wrong while reading file.");
        }
    }
    
    /**
     * @return A list of all Actors in the level
     */
    public List<Actor> getActors(){
        return this.actors;
    }
    
    /**
     * @return Player object of the level.
     */
    public Player getPlayer(){
        return (Player)this.actors.get(0);
    }
    
    /**
     * hides all GameObjects included in the level
     * (Player, NPCs, Tiles, etc.)
     * by making them invisible
     */
    public void hide(){
        this.field.hide();
        for(Actor actor: this.actors){
            actor.setVisible(false);
        }
    }
}
