package STD29006.Trabalhador;
import STD29006.NotificacaoDistribuida;
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
    private NotificacaoDistribuida notificacao;
    private ProcessBuilder processBuilder;
    private Process process;

    public Trabalhador(UUID id, NotificacaoDistribuida notificacao) {

        this.id = id;
        this.status = Status.EM_ESPERA;
        this.notificacao = notificacao;
        this.processBuilder = new ProcessBuilder();
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
    public boolean receberTarefa(String estrategia, String cmd) throws IOException {
        setStatus(Status.OCUPADO);
        Thread crackerThread = new Cracker(estrategia,cmd,notificacao,status,this);
        crackerThread.start();
        return false;
    }


    @Override
    public Status getStatus() throws RemoteException {
        return status;
    }


    @Override
    public boolean pararExecucao() throws RemoteException {
        process.destroy();
        setStatus(Status.EM_ESPERA);
        return true;
    }

    @Override
    public UUID getNome() throws RemoteException {
        return id;
    }

    public ProcessBuilder getProcessBuilder() {
        return processBuilder;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public void setStatus(Status st){
        this.status = st;
    }


}
