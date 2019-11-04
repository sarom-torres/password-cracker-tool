package STD29006.Trabalhador;
import STD29006.Status;
import STD29006.TrabalhadorDistribuido;
import java.rmi.RemoteException;
import java.util.UUID;

public class Trabalhador implements TrabalhadorDistribuido {

    private UUID id; //nome para teste
    private Status status;

    //construtor para teste
    public Trabalhador(UUID id) {

        this.id = id;
        this.status = Status.ONLINE;
    }

    @Override
    public Status getStatus() throws RemoteException {
        return status;
    }


    @Override
    public boolean enviarArquivo(String arq, char type) throws RemoteException {
        return false;
    }


    @Override
    public boolean executar() throws RemoteException {
        return false;
    }

    @Override
    public boolean pararExecucao() throws RemoteException {
        return false;
    }

    @Override
    public UUID getNome() throws RemoteException {
        return id;
    }


}
