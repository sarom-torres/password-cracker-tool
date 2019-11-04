package STD29006;
/*Classe para gerar notificacao no servidor
 * */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface ObservadoDistribuido extends Remote {

    /**
     * Adiciona um listener na lista para receber notificações
     * @param listener listener a ser adicionado na lista de notificacoes
     * @throws RemoteException
    */
    public void adicionarListener(ListenerDistribuido listener) throws RemoteException;

    /**
     * Remove um listener da lista para receber notifcacoes
     * @param listener listener a ser removido da lista
     * @throws RemoteException
    * */
    public void removerListener(ListenerDistribuido listener) throws RemoteException;

    /**
     * mudanca de valor a ser feita pelo trabalhador e que será notificada ao master
     * @param novoValor novo valor que será notificado
     * @param nomeServidor identificador do servidor que está gerando a notificacao
     * @throws RemoteException
     * */
    public void setValor(Object novoValor, UUID nomeServidor) throws RemoteException;

    //TODO javadoc
    public UUID getNome() throws RemoteException;
}
