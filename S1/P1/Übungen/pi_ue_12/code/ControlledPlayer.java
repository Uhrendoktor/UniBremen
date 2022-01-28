import java.net.*;
import java.io.*;
/**
 * Beschreiben Sie hier die Klasse ControlledPlayer2.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class ControlledPlayer extends Player {
    
    private ServerSocket server;
    private Socket client;
    final private int PORT = 1234;
    public ControlledPlayer(final int x, final int y, int rotation, final Field field) {
        super(x, y, rotation, field);
        try{
            this.server = new ServerSocket();
            this.server.bind(new InetSocketAddress(this.PORT));
            
            this.client = this.server.accept();
        }catch(BindException e){
            System.out.println("port is already in use");
        }catch(SecurityException e){
            System.out.println("not allowed to open server on given port");
        }catch(IOException e){
            System.out.println("error while creating server");
        }
    }
    
    @Override
    public void act() {
        try{
            InputStream in = this.client.getInputStream();
            
            int length;
            byte[] buffer = new byte[32];
            if ((length = in.read(buffer)) > 0){
              int rotation = (int) buffer[0];
              super.Move(rotation);
            }
        }catch(IOException e){
            System.out.println("error while reading data");
        }
    }
    
    @Override
    void setVisible(final boolean visible){
        super.setVisible(visible);
        if(!visible){
            try{
                this.client.close();
                this.server.close();
            } catch (IOException e){
                System.out.println("unable to close socket");
            }
        }
    }
}
