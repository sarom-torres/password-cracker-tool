package STD29006;

import STD29006.ListenerDistribuido;

import java.rmi.Remote;

public interface ObservadoDistribuido extends Remote {


    public void adicionarListener(ListenerDistribuido listener);

    public void removerListener(ListenerDistribuido listener);

    public void setAtributoQualquer(Object novoValor);
}
