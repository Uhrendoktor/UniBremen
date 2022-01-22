/**
 * NPC-Klasse. Kann den Spieler verfolgen, Läuft hin und her, bla, bla...
 *
 * @author Julius Walczynski
 */
class NPC extends Actor{
    
    private final int triggerDistance = 3;
    private boolean triggered = false;
    
    private RingBuffer buffer;

    public NPC(final int x, final int y, int rotation, final String fileName, final Field field){
        super(x, y, rotation, fileName, field);
    }
    
    public void act(){
        if(triggered){
            this.addPlayerStep(RPGGame.getLevel().getPlayer().getRotation());
            Move(buffer.pop());
        }else{
            if(!super.canWalk(super.getRotation())){
                super.setRotation((super.getRotation()+2)%4);
            }
            int _direction = super.getRotation();
            Move(_direction);
            
            int dx = RPGGame.getLevel().getPlayer().getX()-super.getX(),
                dy = RPGGame.getLevel().getPlayer().getY()-super.getY();
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
        super.setRotation(direction);
        int _x = super.getX();
        int _y = super.getY();
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
        super.setLocation(_x, _y);
        Player player = RPGGame.getLevel().getPlayer();
        if(checkCollision(player.getX(), player.getY(), super.getX(), super.getY())){
            player.setVisible(false);
        }
    }
    
    private static boolean checkCollision(int _x1, int _y1, int _x2, int _y2){
        return (_x1==_x2)&&(_y1==_y2);
    }
}
