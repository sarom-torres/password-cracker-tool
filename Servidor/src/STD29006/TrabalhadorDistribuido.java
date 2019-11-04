package STD29006;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface TrabalhadorDistribuido extends Remote {

    public boolean enviarArquivo(String arq, char type) throws RemoteException;
    public Status getStatus() throws RemoteException;
    public boolean executar() throws  RemoteException;
    public boolean pararExecucao() throws RemoteException;
    public UUID getNome() throws RemoteException; // método para teste

}
