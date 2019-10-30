package STD29006.Master;

import STD29006.CrackerDistribuido;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Master {

    private static String nomeServidor = "127.0.0.1";
    private static int porta = 12345;
    private static final String NOMEOBJDIST = "Servidor1";

    public static void main(String args []) {

        try{
            if (args[0] != null){
                nomeServidor = args[0];
            }

            if (args[1] != null){
                porta = Integer.parseInt(args[1]);
            }

            System.out.println("Conectando no servidor " + nomeServidor);

            Registry registro = LocateRegistry.getRegistry(nomeServidor, porta);

            CrackerDistribuido stub = (CrackerDistribuido) registro.lookup(NOMEOBJDIST);

            System.out.println("Say hello..." + stub.sayHello());

            System.out.println("End...");
        }catch (RemoteException | NotBoundException ex){
            Logger.getLogger(Master.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

}
