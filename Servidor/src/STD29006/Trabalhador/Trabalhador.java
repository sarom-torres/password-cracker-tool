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
//        this.arqConf = null;
    }

    @Override
    public boolean receberArquivo(byte[] arqSerial, String tipo) throws RemoteException {

        FileWriter fwArquivo;
        BufferedWriter bwArquivo;
        String nomeArquivo = "";
        try {

            if(tipo.equals("d")){
                nomeArquivo = "dicionario.txt";
            }else {
                nomeArquivo = "senhas.txt";
            }
            File arquivo = new File(nomeArquivo);

            fwArquivo = new FileWriter(arquivo, false);
            bwArquivo = new BufferedWriter(fwArquivo);

            String texto = new String(arqSerial, "UTF-8");
            bwArquivo.write(texto + '\n');
            bwArquivo.close();
            fwArquivo.close();
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao tentar escrever no arquivo: " + e.toString());
        }
        return false;
    }

    @Override
    public boolean receberTarefa(String estrategia, String cmd) {
        Thread crackerThread = new Cracker(estrategia,cmd);
        crackerThread.start();
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
