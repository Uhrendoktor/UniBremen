/**
 * Diese Klasse definiert <Zusammenfassung ergänzen>
 *
 * @author Moritz Salger
 */
class NPC {
    private final GameObject avatar;
    private final int patrolDistance;
    private int progress;
    private boolean returning = false;

    public NPC(GameObject avatar, int patrolDistance, int progress){
        this.avatar = avatar;
        this.patrolDistance = patrolDistance;
        this.progress = progress;

        if (progress>patrolDistance || progress < 1) {
            this.progress = 1;
        }
    }
    
    public void act(){
        progress++;
        if(progress>=patrolDistance){
            returning = !returning;
            progress = 1;
        }
        int _direction = returning?2:0;
        Move(_direction);
    }
    
    private void Move(int direction){
        avatar.setRotation(direction);
        int _x = avatar.getX();
        int _y = avatar.getY();
        switch(direction){
            case 0:
                _x++;
                break;
            case 1:
                _y++;
                break;
            case 2:
                _x--;
                break;
            case 3:
                _y--;
                break;
        }
        avatar.setLocation(MinMax(0, RPGGame.FIELD_X-1, _x), MinMax(0, RPGGame.FIELD_Y-1, _y));
    }
    
    private static int MinMax(int _min, int _max, int _value){
        return _value<_min?_min:_value>_max?_max:_value;
    }
    
    public int getX(){
        return avatar.getX();
    }
    
    public int getY(){
        return avatar.getY();
    }
}
