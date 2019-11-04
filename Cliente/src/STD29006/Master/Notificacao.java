package STD29006.Master;

import STD29006.NotificacaoDistribuida;
import STD29006.TrabalhadorDistribuido;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.UUID;

public class Notificacao implements NotificacaoDistribuida {

    private ArrayList<TrabalhadorDistribuido> trabOnline;
    private Registry registro;

    public Notificacao(ArrayList <TrabalhadorDistribuido> trabOnline, Registry registro){
        this.trabOnline = trabOnline;
        this.registro = registro;
    }

    @Override
    public void anunciarOnline(UUID id) throws RemoteException, NotBoundException {

        TrabalhadorDistribuido trabalhador = (TrabalhadorDistribuido) registro.lookup(id.toString());
        trabOnline.add(trabalhador);

    }
}
