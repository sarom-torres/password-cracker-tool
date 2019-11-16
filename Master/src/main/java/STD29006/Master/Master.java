package STD29006.Master;

import STD29006.NotificacaoDistribuida;
import STD29006.TrabalhadorDistribuido;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Master {

    //TODO não deixar o ip fixado
    private static String nomeServidor;
    private static int porta;
    private static final String NOMEMASTER = "Master";
    private static Vector<TrabalhadorDistribuido> trabOnline = new Vector<>();


    public static void main(String args []) {

        //TODO arrumar portas e ip

        try{

            if(args.length == 0){
                nomeServidor = "127.0.0.1";
                porta = 1099;
            }else{
                nomeServidor = args[0];
                porta = Integer.parseInt(args[1]);
            }

            System.out.println("Mestre online... " + nomeServidor);
            //-------------------------------------------------------------------------

            //Criando Registro
            System.setProperty("java.rmi.server.hostname", nomeServidor);
            Registry registro = LocateRegistry.createRegistry(porta);

            //Criando notificação distribuida
            Notificacao notificacao1 = new Notificacao(trabOnline,registro);
            NotificacaoDistribuida notStub = (NotificacaoDistribuida) UnicastRemoteObject.exportObject(notificacao1,0);
            registro.bind(NOMEMASTER,notStub);

            //Criando gerenciador e iniciando thread do menu
            Gerenciador gerenciador = new Gerenciador(trabOnline);
            Thread menuThread = new Menu(gerenciador);
            menuThread.start();


        }catch (RemoteException | AlreadyBoundException e){ // NotBoundException ex
            Logger.getLogger(Master.class.getName()).log(Level.SEVERE, null,e);
        }

    }

}
