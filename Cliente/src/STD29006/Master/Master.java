package STD29006.Master;

import STD29006.TrabalhadorDistribuido;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Master {

    private static String nomeServidor = "127.0.0.1";
    private static int porta = 12345;
    private static final String NOMEOBJDIST1 = "Servidor1";
    private static final String NOMEOBJLIST = "Listener1";
    private static final String NOMEOBJOBS = "OBS1";
    private static ArrayList<UUID> trabOnline = new ArrayList<>();

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


            Gerenciador gerenciador = new Gerenciador(nomeServidor, porta);

            gerenciador.criarListener(NOMEOBJLIST,trabOnline);
            gerenciador.criarObservado(NOMEOBJOBS);


            //TODO Sleep paliativo para execução
            try { Thread.sleep (10000); } catch (InterruptedException ex) {};

            System.out.println("End...");
            System.out.println(trabOnline.get(0).toString());

            while(true);

        }/*catch (RemoteException | NotBoundException ex){
            Logger.getLogger(Master.class.getName()).log(Level.SEVERE,null,ex);
        }*/ catch (RemoteException | AlreadyBoundException e){ // NotBoundException ex
            Logger.getLogger(Master.class.getName()).log(Level.SEVERE, null,e);
        }

    }

}
