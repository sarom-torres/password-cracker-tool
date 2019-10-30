package STD29006;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CrackerDistribuido extends Remote {

    public String sayHello() throws RemoteException;
}
