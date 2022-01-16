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
            int x = super.getX();
            int y = super.getY();
            switch(key){
                case VK_RIGHT:
                    super.setRotation(0);
                    x++;
                    break;
                case VK_LEFT:
                    super.setRotation(2);
                    x--;
                    break;
                case VK_DOWN:
                    super.setRotation(1);
                    y++;
                    break;
                case VK_UP:
                    super.setRotation(3);
                    y--;
                    break;
                default:
                    Game.playSound("error");
                    continue;
            }
            x = MinMax(0, field.SIZE_X-1, x);
            y = MinMax(0, field.SIZE_Y-1, y);
    
            if(!super.canWalk(super.getRotation()) || (x==super.getX() && y==super.getY())) continue;
    
            super.setLocation(x, y);
            Game.playSound("step");
            break;
        }
    }
    
    private static int MinMax(int _min, int _max, int _value){
        return _value<_min?_min:_value>_max?_max:_value;
    }
}
