package STD29006.Master;

import STD29006.NotificacaoDistribuida;
import STD29006.TrabalhadorDistribuido;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Master {

    private static String nomeServidor = "127.0.0.1";
    private static int porta = 12345;
    private static final String NOMEOBJDIST = "Trabalhador1";
    private static final String NOMEOBJLIST = "Listener1";
    private static final String NOMEOBJOBS = "OBS1";
    private static final String NOMEMASTER = "Master";
    private static ArrayList<TrabalhadorDistribuido> trabOnline = new ArrayList<>();

    public static void main(String args []) {

        try{
            if (args[0] != null){
                nomeServidor = args[0];
            }

            if (args[1] != null){
                porta = Integer.parseInt(args[1]);
            }

            System.out.println("Mestre online... " + nomeServidor);
            //-------------------------------------------------------------------------


            System.setProperty("java.rmi.server.hostname", nomeServidor);
            Registry registro = LocateRegistry.createRegistry(porta);

            Notificacao notificacao1 = new Notificacao(trabOnline,registro);
            NotificacaoDistribuida notStub = (NotificacaoDistribuida) UnicastRemoteObject.exportObject(notificacao1,0);
            registro.bind(NOMEMASTER,notStub);


       }/*catch (RemoteException | NotBoundException ex){
            Logger.getLogger(Master.class.getName()).log(Level.SEVERE,null,ex);
        }*/ catch (RemoteException | AlreadyBoundException e){ // NotBoundException ex
            Logger.getLogger(Master.class.getName()).log(Level.SEVERE, null,e);
        }

    }

}
