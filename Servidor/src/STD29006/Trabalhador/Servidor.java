package STD29006.Trabalhador;
import STD29006.NotificacaoDistribuida;
import STD29006.TrabalhadorDistribuido;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    private static String nomeServidor = "127.0.0.1";
    private static int porta = 12345;
    private static final String NOMEOBJDIST1 = "Servidor1";
    private static final String NOMEOBJLIST = "Listener1";
    private static final String NOMEOBJOBS = "OBS1";
    private static final String NOMEWORKER = "WORKER_A";
    private static UUID uuidServidor = null;
    private static final String NOMEMASTER = "Master";


    public static void main(String args []){

        try{
            if (args[0] != null){
                nomeServidor = args[0];
            }

            if (args[1] != null){
                porta = Integer.parseInt(args[1]);
            }

            //Pegando o registro no servidor
            Registry registro = LocateRegistry.getRegistry(nomeServidor, porta);

            //Cria UUID para identificar o servidor
            uuidServidor = UUID.randomUUID();


            //Criando e compartilhando obj trabalhador distribuido
            Trabalhador trabalhador = new Trabalhador(uuidServidor);//Nome para teste
            TrabalhadorDistribuido stub = (TrabalhadorDistribuido) UnicastRemoteObject.exportObject(trabalhador,0);
            registro.bind(uuidServidor.toString(),stub);

            //Pegando objeto de notificacao e anunciando ao master que está online
            NotificacaoDistribuida notificacao1 = (NotificacaoDistribuida) registro.lookup(NOMEMASTER);
            notificacao1.anunciarOnline(uuidServidor);

            System.out.println("Trabalhador " + uuidServidor.toString() + " pronto!/n");


        } catch (RemoteException |AlreadyBoundException ex){ // NotBoundException ex
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null,ex);
        } catch (NotBoundException ex){
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE,null,ex);
        }


}
}
