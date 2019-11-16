package STD29006;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface NotificacaoDistribuida extends Remote {

    public void anunciarOnline(UUID id) throws RemoteException, NotBoundException;
    public void atividadePronta(byte[] resultado) throws RemoteException;
}
