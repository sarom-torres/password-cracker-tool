package STD29006.Master;

import STD29006.ListenerDistribuido;
import STD29006.ObservadoDistribuido;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Observado implements ObservadoDistribuido {

    private Object online;
    private final ArrayList<ListenerDistribuido> listeners = new ArrayList<ListenerDistribuido>();

    private void notificarObservadores() throws RemoteException {
        for (ListenerDistribuido listener : listeners) {
            listener.notificar(this);
        }
    }
    public void adicionarListener(ListenerDistribuido listener) throws RemoteException {
        listeners.add(listener); //passará a ser notificado sobre mudanças em this
    }
    public void removerListener(ListenerDistribuido listener) throws RemoteException{
        listeners.remove(listener); //deixará de ser notificado sobre mudanças em this
    }
    public void setOnline(Object novoValor) throws RemoteException{
        online = novoValor;
        notificarObservadores();//avisa que houve uma alteração em this
    }

}
