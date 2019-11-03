package STD29006;

import STD29006.Master.Observado;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ListenerDistribuido extends Remote {
    public void notificar(ObservadoDistribuido lst) throws RemoteException;

}
