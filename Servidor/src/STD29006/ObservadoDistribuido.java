package STD29006;

import STD29006.ListenerDistribuido;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface ObservadoDistribuido extends Remote {


    public void adicionarListener(ListenerDistribuido listener) throws RemoteException;

    public void removerListener(ListenerDistribuido listener) throws RemoteException;

    public void setValor(Object novoValor, UUID nomeServidor) throws RemoteException;

    public UUID getNome() throws RemoteException;
}
