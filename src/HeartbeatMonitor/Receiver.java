package HeartbeatMonitor;

import FaultHandler.FaultMonitor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Receiver extends UnicastRemoteObject implements ReceiverInterface{

    private static final int INTERVAL = 5000;
    private static final String HOST = "localhost";
    private static long lastHeartbeatTime;

    protected Receiver() throws RemoteException {
    }

    public void initializeReceiver(){
       Registry registry;
        try{
            Receiver receiver = new Receiver();
            registry = LocateRegistry.getRegistry(HOST);
            registry.rebind("Receiver", receiver);

        }catch(Exception e){
            System.out.println(" Receiver: Exception : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /* monitors the time difference between the last 2 heart beat signals */
    public void monitorSenderModule() throws RemoteException {
        while (true) {
            try {
                Thread.sleep(INTERVAL);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            if (!isAlive()) {
                System.out.println("HearBeat wait time exceeded - Sender Component failed - Check logs for details");
                FaultMonitor.handleFault("Sender");
            }
        }
    }

    /* Checks if the sender is alive*/
    private boolean isAlive(){
        long interval = System.currentTimeMillis() - lastHeartbeatTime;
        int error = 500; //500ms error tolerable
        return (INTERVAL + error) >= interval;
    }

    /*Receives heart beat messages from the monitored component*/
    public void read() throws RemoteException{
        lastHeartbeatTime = System.currentTimeMillis();
        System.out.println("Receiver: Received latest heartbeat at : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    public static void main(String [] args) throws RemoteException {
        Receiver receiver = new Receiver();
        receiver.initializeReceiver();
        try{
            receiver.monitorSenderModule();
        }catch(Exception ex){
            System.out.println("HearBeat Monitor: Module exception  - " + ex.getMessage());
        }
    }
}
