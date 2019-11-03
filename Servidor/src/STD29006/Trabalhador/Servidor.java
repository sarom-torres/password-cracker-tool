package STD29006.Trabalhador;
import STD29006.ListenerDistribuido;
import STD29006.ObservadoDistribuido;
import STD29006.TrabalhadorDistribuido;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    private static String nomeServidor = "127.0.0.1";
    private static int porta = 12345;
    private static final String NOMEOBJDIST1 = "Servidor1";
    private static final String NOMEOBJLIST = "Listener1";
    private static final String NOMEOBJOBS = "OBS1";

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

            //Obtendo objeto de notificacao distribuida
            //NotificacaoDistribuida Notificacacao = (NotificacaoDistribuida) registro.lookup(NOMEOBJNOTIF);

            ListenerDistribuido listener = (ListenerDistribuido) registro.lookup(NOMEOBJLIST);

            ObservadoDistribuido observado = (ObservadoDistribuido) registro.lookup(NOMEOBJOBS);
            observado.adicionarListener(listener);
            observado.setAtributoQualquer(true);



//  O observador cria no cliente e manda para o servidor
  //
//  O observado cria no servidro e manda para o cliente

            //Criando e compartilhando obj trabalhador distribuido
            Trabalhador trabalhador = new Trabalhador("Worker1");//Nome para teste
            TrabalhadorDistribuido stub = (TrabalhadorDistribuido) UnicastRemoteObject.exportObject(trabalhador,0);
            registro.bind(NOMEOBJDIST1,stub);

            System.out.println("Servidor pronto!/n");


        } catch (RemoteException |AlreadyBoundException ex){ // NotBoundException ex
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null,ex);
        } catch (NotBoundException ex){
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE,null,ex);
        }


}
}
