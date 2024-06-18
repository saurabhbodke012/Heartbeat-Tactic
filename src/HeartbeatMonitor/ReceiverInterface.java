package HeartbeatMonitor;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ReceiverInterface extends Remote{

    /*Starts up the receiver and sets up mechanism for critical components to report health */
    void initializeReceiver() throws RemoteException;

    /* Remote method invoked by any highly available module to send a hearbeat signal/status*/
    void read() throws RemoteException;

    /*Monitors the health of the localization module every seconds */
    void monitorSenderModule() throws RemoteException;
}
