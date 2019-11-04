package STD29006;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ListenerDistribuido extends Remote {
    public void notificar(ObservadoDistribuido obs/*, ArrayList<TrabalhadorDistribuido> trabOnline*/) throws RemoteException;
}
