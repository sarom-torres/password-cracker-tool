package STD29006.Trabalhador;
import STD29006.Status;
import STD29006.TrabalhadorDistribuido;
import java.rmi.RemoteException;

public class Trabalhador implements TrabalhadorDistribuido {

    private String nome; //nome para teste
    private Status status;

    //construtor para teste
    public Trabalhador(String nome) {

        this.nome = nome;
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
    public String getNome() throws RemoteException {
        return nome;
    }
}
