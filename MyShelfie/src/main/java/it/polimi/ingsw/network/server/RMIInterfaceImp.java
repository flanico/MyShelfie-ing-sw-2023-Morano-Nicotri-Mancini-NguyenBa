package it.polimi.ingsw.network.server;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIInterfaceImp extends UnicastRemoteObject implements RMIInterface {
    @Serial
    private static final long serialVersionUID = 7973004963846163594L;
    private final transient Server server;


    public RMIInterfaceImp(Server server) throws RemoteException {
        this.server = server;
    }


}
