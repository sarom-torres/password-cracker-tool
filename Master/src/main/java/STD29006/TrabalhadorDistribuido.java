package STD29006;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface TrabalhadorDistribuido extends Remote {

    public boolean receberArquivo(byte [] arqSerial, String tipo) throws RemoteException;
    public boolean receberTarefa(String estrategia, String cmd) throws RemoteException;
    public Status getStatus() throws RemoteException;
    public boolean executar() throws  RemoteException;
    public boolean pararExecucao() throws RemoteException;
    public UUID getNome() throws RemoteException;
}
