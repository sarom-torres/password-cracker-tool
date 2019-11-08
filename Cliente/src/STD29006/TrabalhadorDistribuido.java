package STD29006;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface TrabalhadorDistribuido extends Remote {

    public void receberLinha(String linha) throws RemoteException;
    public boolean enviarLinha(File arquivo, File dicionario) throws RemoteException;
    public Status getStatus() throws RemoteException;
    public boolean executar() throws  RemoteException;
    public boolean pararExecucao() throws RemoteException;
    public UUID getNome() throws RemoteException;
    public void setArquivoConfig(String arqConf) throws RemoteException;
}
