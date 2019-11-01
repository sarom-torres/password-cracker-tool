package STD29006.Master;
import STD29006.NotificacaoDistribuida;
import java.rmi.RemoteException;

public class Notificacao implements NotificacaoDistribuida  {

    @Override
    public String pronto() throws RemoteException {
        return null;
    }

    @Override
    public boolean online() throws RemoteException {
        return false;
    }
}
