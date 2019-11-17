package STD29006;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface NotificacaoDistribuida extends Remote {

    /**
     * Método chamado pelo trabalhador para avisar seu nome e que está online
     * @param id id do trabalhador
     * @throws RemoteException
     * @throws NotBoundException
     * */
    public void anunciarOnline(UUID id) throws RemoteException, NotBoundException;

    /**
     * Método chamado pelo trabalhador para devolver o resultado de uma quebra
     * @param resultado array de bytes com a resposta da quebra
     * @throws RemoteException
     * */
    public void atividadePronta(byte[] resultado) throws RemoteException;
}
