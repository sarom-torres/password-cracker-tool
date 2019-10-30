package STD29006;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NotificacaoDistribuida extends Remote {

    public boolean sendCommand(String code) throws RemoteException;
    public boolean sendAnswer(String code) throws RemoteException;

}
