/**
 * Diese Klasse definiert <Zusammenfassung ergänzen>
 *
 * @author Julius Walczynski
 */
class NPC {
    private final GameObject gameObject;
    private final int walkDistance;
    private int progress;
    private boolean returning = false;
    public NPC(final int _x, final int _y, final String _fileName, int _walkDistance, int _progress){
        gameObject = new GameObject(_x, _y, 0, _fileName);
        walkDistance = _walkDistance;
        progress = (_progress>walkDistance||_progress<0)?0:_progress;
    }
    
    public void act(){
        if(progress>=walkDistance-1){
            returning = !returning;
            progress = 0;
        }
        int _direction = returning?2:0;
        Move(_direction);
        progress++;
    }
    
    private void Move(int _direction){
        gameObject.setRotation(_direction);
        int _x = gameObject.getX();
        int _y = gameObject.getY();
        switch(_direction){
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
        gameObject.setLocation(MinMax(0, RPGGame.FIELD_X-1, _x), MinMax(0, RPGGame.FIELD_Y-1, _y));
    }
    
    private static int MinMax(int _min, int _max, int _value){
        return _value<_min?_min:_value>_max?_max:_value;
    }
    
    public int getX(){
        return gameObject.getX();
    }
    
    public int getY(){
        return gameObject.getY();
    }
}
