/**
 * Diese Klasse definiert <Zusammenfassung ergänzen>
 *
 * @author Moritz Salger
 */
class NPC {
    private final GameObject avatar;
    private boolean horizontalMovement;
    
    private final int triggerDistance = 3;
    private boolean triggered = false;
    
    private RingBuffer buffer;

    public NPC(GameObject avatar, boolean horizontalMovement){
        this.avatar = avatar;
        this.horizontalMovement = horizontalMovement;
    }
    
    public void act(){
        if(triggered){
            Move(buffer.pop());
        }else{
            if(!RPGGame.getField().hasNeighbor(avatar.getX(), avatar.getY(), avatar.getRotation())){
                avatar.setRotation((avatar.getRotation()+2)%4);
            }
            int _direction = avatar.getRotation();
            Move(_direction);
            
            int dx = RPGGame.getPlayer().getX()-avatar.getX(),
                dy = RPGGame.getPlayer().getY()-avatar.getY();
            if((_direction==0&&dy==0&&dx>0&&dx<=triggerDistance)
             ||(_direction==1&&dx==0&&dy>0&&dy<=triggerDistance)
             ||(_direction==2&&dy==0&&dx<0&&dx>=-triggerDistance)
             ||(_direction==3&&dx==0&&dy<0&&dy>=-triggerDistance)){
                 triggered = true;
                 int distance = Math.abs(Math.abs(dx)>Math.abs(dy)?dx:dy);
                 buffer = new RingBuffer(distance+1);
                 for(int i = 0; i < distance; i++){
                     buffer.push(_direction);   
                 }
            }
        }
    }
    
    public void addPlayerStep(int direction){
        if(triggered) buffer.push(direction);
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
        avatar.setLocation(MinMax(0, RPGGame.getFieldX()-1, _x), MinMax(0, RPGGame.getFieldY()-1, _y));
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
