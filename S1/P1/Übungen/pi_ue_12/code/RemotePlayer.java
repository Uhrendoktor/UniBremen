import java.net.*;
import java.io.*;
/**
 * Beschreiben Sie hier die Klasse RemotePlayer2.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class RemotePlayer extends Player {
  
    private final String IP = "127.0.0.1";
    private final int PORT = 1234;
    
    private Socket socket;
    public RemotePlayer(final int x, final int y, int rotation, final Field field) {
        super(x, y, rotation, field);
        
        try{
            this.socket = new Socket();
            this.socket.connect(new InetSocketAddress(IP, PORT));
        }catch(ConnectException e){
            System.out.println("unable to connect to server");
        }catch(IOException e){}
    }
    
    @Override
    public void act(){
        super.act();
        
        try{
            OutputStream out = this.socket.getOutputStream();
            out.write(new byte[]{ (byte)super.getRotation() });
            out.flush();
        }catch(IOException e){
            System.out.println("unable to write data");
        }
    }
    
    @Override
    void setVisible(final boolean visible){
        super.setVisible(visible);
        try{
            if(!visible) this.socket.close();
        }catch(IOException e){
            System.out.println("unable to close socket");
        }
    }
}
