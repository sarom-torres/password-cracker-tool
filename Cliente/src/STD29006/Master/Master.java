package STD29006.Master;

import STD29006.NotificacaoDistribuida;
import STD29006.TrabalhadorDistribuido;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Master {

    //TODO não deixar o ip fixado
    private static String nomeServidor = "127.0.0.1";
    private static int porta = 12345;
    private static final String NOMEMASTER = "Master";
    private static Vector<TrabalhadorDistribuido> trabOnline = new Vector<>();
    private static ArrayList<String> listaArqConf = new ArrayList<>();


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

            //Criando Registro
            System.setProperty("java.rmi.server.hostname", nomeServidor);
            Registry registro = LocateRegistry.createRegistry(porta);

            //TODO decidir em qual classe será adicionado o arquivo de configuracao do trabalhador
            //Setando lista com arquivos de configuração dos trabalhadores
//            listaArqConf.add("A115"); //all5
//            listaArqConf.add("A116");
//            listaArqConf.add("A117");

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
