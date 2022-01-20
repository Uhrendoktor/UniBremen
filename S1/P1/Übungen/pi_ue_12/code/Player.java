import static java.awt.event.KeyEvent.*;
/**
 * Player Klasse - Tastatur steuerbarer Spieler.
 * 
 * @author Julius Walczynski 
 * 
 */
public class Player extends Actor {

    public Player(final int x, final int y, int rotation, final Field field) {
        super(x, y, rotation, "laila", field);
    }

    /**
     * waits for valid user input and moves the player object accordingly.
     * If invalid input is entered it starts over again.
     */
    public void act() {
        while(true){
            int key = Game.getNextKey();
            int rotation = 0;
            switch(key){
                case VK_RIGHT:
                    rotation = 0;
                    break;
                case VK_LEFT:
                    rotation = 2;
                    break;
                case VK_DOWN:
                    rotation = 1;
                    break;
                case VK_UP:
                    rotation = 3;
                    break;
                default:
                    Game.playSound("error");
                    continue;
            }
            if(this.Move(rotation)) break;
        }
    }
    
    public boolean Move(int direction){
        int x = super.getX()+(direction%2==0?-(direction-1):0);
        int y = super.getY()+(direction%2==1?-(direction-2):0);
        if(!super.canWalk(direction) || (x==super.getX() && y==super.getY())) return false;
    
        super.setRotation(direction);
        super.setLocation(x, y);
        Game.playSound("step");
        return true;
    }
    
    private static int MinMax(int _min, int _max, int _value){
        return _value<_min?_min:_value>_max?_max:_value;
    }
}
