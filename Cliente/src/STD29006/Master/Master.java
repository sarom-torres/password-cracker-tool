package STD29006.Master;

import STD29006.TrabalhadorDistribuido;

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
            TrabalhadorDistribuido Trabalhador1 = (TrabalhadorDistribuido) registro.lookup(NOMEOBJDIST);

            //Status st1 = Trabalhador1.getStatus();

            System.out.println("O " + Trabalhador1.getNome() + " está " + Trabalhador1.getStatus().toString());

            System.out.println("End...");

        }catch (RemoteException | NotBoundException ex){
            Logger.getLogger(Master.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

}
