package STD29006;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NotificacaoDistribuida extends Remote {

    public String pronto() throws RemoteException;
    public boolean online() throws RemoteException;

}
