package STD29006.Master;

import STD29006.TrabalhadorDistribuido;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
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
            //-------------------------------------------------------------------------


            //Criando o registro
            System.setProperty("java.rmi.server.hostname", nomeServidor);
            Registry registro = LocateRegistry.createRegistry(porta);

            //TODO Sleep paliativo para execução
            try { Thread.sleep (5000); } catch (InterruptedException ex) {};



            //Vai pocurar por um servidor que não existe ainda
            TrabalhadorDistribuido Trabalhador1 = (TrabalhadorDistribuido) registro.lookup(NOMEOBJDIST);


            System.out.println("O " + Trabalhador1.getNome() + " está " + Trabalhador1.getStatus().toString());
            System.out.println("End...");

            while(true);

        }catch (RemoteException | NotBoundException ex){
            Logger.getLogger(Master.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

}
