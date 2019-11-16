package STD29006.Master;

import STD29006.NotificacaoDistribuida;
import STD29006.TrabalhadorDistribuido;

import java.io.UnsupportedEncodingException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.UUID;
import java.util.Vector;

public class Notificacao implements NotificacaoDistribuida {

    private Vector<TrabalhadorDistribuido> trabOnline;
    private Registry registro;

    public Notificacao(Vector <TrabalhadorDistribuido> trabOnline, Registry registro){
        this.trabOnline = trabOnline;
        this.registro = registro;
    }

    @Override
    public void anunciarOnline(UUID id) throws RemoteException, NotBoundException {

        TrabalhadorDistribuido trabalhador = (TrabalhadorDistribuido) registro.lookup(id.toString());
        trabOnline.add(trabalhador);

    }

    @Override
    public void atividadePronta(byte[] resultado) throws RemoteException {

        String strResultado = null;
        try {
            strResultado = new String(resultado, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("O resultado foi "+ strResultado);
    }
}
