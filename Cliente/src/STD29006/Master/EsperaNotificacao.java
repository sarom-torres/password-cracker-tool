package STD29006.Master;

import java.rmi.RemoteException;

public class EsperaNotificacao extends Thread {

    private NotificacaoDistribuida notificacao;

    public EsperaNotificacao(NotificacaoDistribuida notifDist) {
        this.notificacao = notifDist;
    }

    @Override
    public void run() {

            while (true) {
                try {
                    if(!notificacao.verificaCondicao()) continue;
                    else notify();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        //Espera até o servidor ficar online
        //try { wait(); } catch (InterruptedException e) { System.err.println(e.toString()); }

    }
}
