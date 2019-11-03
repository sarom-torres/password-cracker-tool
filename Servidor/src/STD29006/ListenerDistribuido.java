package STD29006;

import java.rmi.Remote;

public interface ListenerDistribuido extends Remote {
    public void notificar(ObservadoDistribuido lst);
}
