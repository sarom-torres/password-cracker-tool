package STD29006.Master;
import STD29006.NotificacaoDistribuida;

import java.rmi.RemoteException;

public class Notificacao implements NotificacaoDistribuida{


    @Override
    public boolean sendCommand(String code) throws RemoteException {
        return false;
    }

    @Override
    public boolean sendAnswer(String code) throws RemoteException {
        return false;
    }
}
