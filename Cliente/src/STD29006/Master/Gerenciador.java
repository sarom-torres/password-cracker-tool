/**
* Classe Gerenciador fazer o gerenciamentoe a criação dos principais obejtos distribuidos
 *
 * @author Sarom Torres
 *
* */

package STD29006.Master;

import STD29006.ListenerDistribuido;
import STD29006.ObservadoDistribuido;
import STD29006.TrabalhadorDistribuido;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.UUID;

public class Gerenciador{

    private Registry registro;
    private TrabalhadorDistribuido trabalhador;
    private Listener listener;
    private ListenerDistribuido lstDist;
    private Observado observado;
    private ObservadoDistribuido obsDist;


    public Gerenciador(String nomeServidor, int porta) throws RemoteException {
        System.setProperty("java.rmi.server.hostname", nomeServidor);
        this.registro = LocateRegistry.createRegistry(porta);
    }

    /**
     * Busca trabalhador distribuido no registry
     * @param nomeObjDist nome do objeto a ser procurado no registry
     * @throws RemoteException
     * @throws NotBoundException
     */
    public TrabalhadorDistribuido criarTrabalhador(String nomeObjDist) throws RemoteException, NotBoundException {
        trabalhador = (TrabalhadorDistribuido) registro.lookup(nomeObjDist);
        return trabalhador;
    }

    /**
     * Cria Listener Remoto para escutar as notificacoes do servidor
     * @param nomeObjDist nome do objeto a ser procurado no registry
     * @throws RemoteException
     * @throws RemoteException
     * */
    public ListenerDistribuido criarListener(String nomeObjDist, ArrayList<UUID> trabOnline) throws RemoteException, AlreadyBoundException {
        this.listener = new Listener(trabOnline);
        this.lstDist = (ListenerDistribuido) UnicastRemoteObject.exportObject(listener,0);
        this.registro.bind(nomeObjDist,lstDist);
        return lstDist;
    }

    /**
     * Cria Observado Remoto para gerar as notificacoes do servidor
     * @param nomeObjDist nome do objeto a ser procurado no registry
     * @throws RemoteException
     * @throws RemoteException
     * */
    public ObservadoDistribuido criarObservado(String nomeObjDist) throws RemoteException, AlreadyBoundException {
        this.observado = new Observado();
        this.obsDist = (ObservadoDistribuido) UnicastRemoteObject.exportObject(observado,0);
        this.registro.bind(nomeObjDist,obsDist);
        return obsDist;
    }


}
