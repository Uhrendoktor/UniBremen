
/**
 * Beschreiben Sie hier die Klasse Actor.
 * 
 * @author Julius Walczynski
 * 
 */
public abstract class Actor extends GameObject {
    
    protected Field field;
    
    public Actor(final int x, final int y, int rotation, final String fileName, final Field field) {
        super(x, y, rotation, fileName);
        this.field = field;
    }
    
    /**
     * checks if the actor is able to walk and stay on a pathway if he goes in a given direction. 
     */
    public boolean canWalk(int direction) {
        return this.field.hasNeighbor(super.getX(), super.getY(), direction);
    }
    
    /**
     * abstract act void to be implemented in child classes
     */
    public abstract void act();
}
