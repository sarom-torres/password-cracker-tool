package STD29006.Worker;
import STD29006.CrackerDistribuido;

import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Worker {

    //Para que servia o static nesses atributos
    private static String nomeServidor = "127.0.0.1";
    private static int porta = 12345;
    private static final String NOMEOBJDIST = "Servidor1";
    private String status;


    public static void main(String args []){

        try{
            if (args[0] != null){
                nomeServidor = args[0];
            }

            if (args[1] != null){
                porta = Integer.parseInt(args[1]);
            }

            Cracker ck = new Cracker();

            System.setProperty("java.rmi.server.hostname", nomeServidor);

            CrackerDistribuido stub = (CrackerDistribuido) UnicastRemoteObject.exportObject(ck,0);

            Registry registro = LocateRegistry.createRegistry(porta);

            registro.bind(NOMEOBJDIST,stub);

            System.out.println("Worker pronto!/n");


        } catch (RemoteException | AlreadyBoundException ex){

            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null,ex);
        }


    }
}
