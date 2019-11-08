package STD29006.Trabalhador;
import STD29006.Status;
import STD29006.TrabalhadorDistribuido;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.UUID;

public class Trabalhador implements TrabalhadorDistribuido {

    private UUID id;
    private Status status;
    private String arqConf;



    public Trabalhador(UUID id) {

        this.id = id;
        this.status = Status.EM_ESPERA;
        this.arqConf = null;
    }

    @Override
    public void receberLinha(String linha) throws RemoteException {

        if(linha.equals("EOF")){
            Thread crackerThread = new Cracker(arqConf);
            crackerThread.start();
        }

        FileWriter fwArquivo;
        BufferedWriter bwArquivo;
        try {
            File arquivo = new File("senhas.txt");

            fwArquivo = new FileWriter(arquivo, arquivo.exists());
            bwArquivo = new BufferedWriter(fwArquivo);
            bwArquivo.write(linha + '\n');
            bwArquivo.close();
            fwArquivo.close();

        } catch (IOException e) {
            System.err.println("Erro ao tentar escrever no arquivo: " + e.toString());
        }
    }

    @Override
    public boolean enviarLinha(File arquivo, File dicionario) throws RemoteException {
        return false;
    }

    @Override
    public Status getStatus() throws RemoteException {
        return status;
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

    @Override
    public void setArquivoConfig(String arqConf) throws RemoteException {
        this.arqConf = arqConf;
    }

    public void setStatus(Status st){
        this.status = st;
    }


}
