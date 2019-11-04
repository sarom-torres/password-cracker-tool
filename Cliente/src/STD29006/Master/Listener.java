package STD29006.Master;

import STD29006.ListenerDistribuido;
import STD29006.ObservadoDistribuido;
import STD29006.TrabalhadorDistribuido;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.UUID;

public class Listener implements ListenerDistribuido {

    private String nomeWorker;
    //TODO Trocar para lista de ID
    private ArrayList<UUID> trabOnline;

    public Listener(ArrayList <UUID> trabOnline){
        this.trabOnline = trabOnline;
    }

    @Override
    public void notificar(ObservadoDistribuido obs/*, ArrayList<TrabalhadorDistribuido> trabOnline*/) throws RemoteException {
        UUID nome =  obs.getNome();
        System.out.println("Foi notificado pelo "+ nome);
        trabOnline.add(nome);
    }

    public void addOnline(UUID id){
        trabOnline.add(id);
    }

}
