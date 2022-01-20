import java.net.ServerSocket;
import java.io.IOException;
import java.net.Socket;
import java.io.*;
import java.util.stream.*;
import java.util.List;

/**
 * Diese Klasse definiert <Zusammenfassung ergänzen>
 *
 * @author Julius Walczynski
 */
public class ControlledPlayer extends Player{
    
    private ServerSocket server;
    private boolean listening = false;
    final int PORT = 1234;
    
    int transmittedRotation = -1;
    public ControlledPlayer(final int x, final int y, int rotation, final Field field){
        super(x, y, rotation, field);
        //starts server socket thread. listens for incoming connections
        new Thread(()->{
            try{
                server = new ServerSocket(PORT);
                listening = true;
                while (true) new Client(server.accept(), (packetId, data)->{
                    if(packetId==1) transmittedRotation = (int)data.get(0);
                }).start();
            }catch(IOException e){}
        }).start();
    }
    
    @Override
    public void act(){
        while(this.transmittedRotation<0&&this.listening) {
            try{
                Thread.sleep(10);
            } catch (InterruptedException ie){}   
        };
        super.Move(this.transmittedRotation);
        this.transmittedRotation = -1;
    }
    
    @Override
    void setVisible(final boolean visible){
        super.setVisible(visible);
        if(!visible) this.stopServer();
    }
    
    public void stopServer(){
        try {
            this.server.close();
            listening = false;
        }catch (IOException e){}
    }
    
    interface IDataReceived{
        void handlePacket(byte packetId, List<Byte> data);
    }
    
    class Client extends Thread{
        
        private Socket socket;
        private InputStream in;
        private IDataReceived callback;
        
        public Client(Socket client, IDataReceived callback){
            this.socket = client;
            this.callback = callback;
        }
        
        public void run() {
            try{
                this.in = this.socket.getInputStream();
                
                int count;
                byte[] buffer = new byte[128];
                while ((count = in.read(buffer)) > 0){
                    List<Byte> data = IntStream.range(1, count)
                        .mapToObj(i -> buffer[i])
                        .collect(Collectors.toList());
                    callback.handlePacket(buffer[0], data);
                }
            }catch(IOException e){
                try {
                    this.in.close();
                    this.socket.close();
                } catch (IOException _e) {}
            }
        }
    }
}
