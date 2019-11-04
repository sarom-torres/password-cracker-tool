package STD29006;

import STD29006.Master.Observado;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ListenerDistribuido extends Remote {
    /**
     * Método chamado quando ocorre uma alteracao no valor observado
     * @param obs objeto observado distribuido
 //    * @param trabOnline lista para adicionar o trabalhador que está online
     * @throws RemoteException
     * */
    public void notificar(ObservadoDistribuido obs/*, ArrayList<TrabalhadorDistribuido> trabOnline*/) throws RemoteException;

}
