package Sender;

import HeartbeatMonitor.ReceiverInterface;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/* Component under monitoring */
public class Sender {

    private final int HEARTBEAT_INTERVAL = 2500;
    private Registry registry;
    private ReceiverInterface receiver_stub;

    public void initialize() throws IOException, NotBoundException {

        /* gets the rmi registry process once initialized */
        registry = LocateRegistry.getRegistry();

        /* Lookup registry to access the remote object of the monitoring component */
        receiver_stub = (ReceiverInterface) registry.lookup("ReceiverInterface");
    }

    public void sendHeartBeat() throws IOException{

        while(true){
            try {
                System.out.println("Sender: I am Alive.");
                Thread.sleep(HEARTBEAT_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String [] args){
        Sender sender = new Sender();
        try{
            sender.initialize();
            Thread.sleep(1000);
            sender.sendHeartBeat();

        }catch(NotBoundException | IOException | InterruptedException ex){
            ex.printStackTrace();
        }
        System.out.println("Sender initialized");
    }
}
