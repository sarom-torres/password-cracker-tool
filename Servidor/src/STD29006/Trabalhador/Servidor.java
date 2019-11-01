package STD29006.Trabalhador;
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
    private static final String NOMEOBJDIST = "Servidor1";

    public static void main(String args []){

        try{
            if (args[0] != null){
                nomeServidor = args[0];
            }

            if (args[1] != null){
                porta = Integer.parseInt(args[1]);
            }



            Registry registro = LocateRegistry.getRegistry(nomeServidor, porta);

            Trabalhador trabalhador = new Trabalhador("Worker1");//Nome para teste
            TrabalhadorDistribuido stub = (TrabalhadorDistribuido) UnicastRemoteObject.exportObject(trabalhador,0);
            registro.bind(NOMEOBJDIST,stub);

            System.out.println("Servidor pronto!/n");


        } catch (RemoteException |AlreadyBoundException ex){ // NotBoundException ex

            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null,ex);
        }


    }
}
