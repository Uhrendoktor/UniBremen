import java.io.*;
import java.util.stream.*;
/**
 * Diese Klasse definiert <Zusammenfassung ergänzen>
 *
 * @author Julius Walczynski
 */
public class RemotePlayer extends Player{
    
    private Socket socket;
    private OutputStream out;
    
    public RemotePlayer(final int x, final int y, int rotation, final Field field){
        super(x, y, rotation, field);
        this.socket = new Socket("127.0.0.1", 1234);
        this.out = this.socket.getOutputStream();
    }
    
    @Override
    public void act(){
        super.act();
        this.out.write(new byte[]{(byte)1, (byte)super.getRotation()});
        this.out.flush();
    }
    
    @Override
    void setVisible(final boolean visible){
        super.setVisible(visible);
        if(!visible) this.socket.close();
    }
}
