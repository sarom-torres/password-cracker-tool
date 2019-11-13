package STD29006.Trabalhador;

import STD29006.NotificacaoDistribuida;
import STD29006.Status;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

public class Cracker extends Thread {

    //TODO mudar o caminho dos comandos
    private final String CMD_INCREMENTAL = "/usr/sbin/john -i=";
    private final String CMD_DICIONARIO = "/usr/sbin/john --wordlist:";
    private final String CMD_AUTOMATICO = "/usr/sbin/john senhas.txt";
    private final String ARQ_INCREMENTAL = " senhas.txt";
    private final String ARQ_DICIONARIO = " dicionario.txt senhas.txt";
    private String comando;
    private NotificacaoDistribuida notificacao;
    private Status status;
    private Trabalhador trabalhador;


    public Cracker(String estrategia, String cmd, NotificacaoDistribuida notificacao, Status status,Trabalhador trabalhador){

        this.notificacao = notificacao;
        this.status = status;
        this.trabalhador = trabalhador;

        //TODO está correto fazer esse else if aqui?
        if(cmd.equals("1")){
            this.comando = CMD_INCREMENTAL+estrategia+ARQ_INCREMENTAL;
        }else if(cmd.equals("2")){
            this.comando = CMD_DICIONARIO+ARQ_DICIONARIO;
        }else{
            this.comando = CMD_AUTOMATICO;
        }
    }

    public void run(){
        System.out.println("Entrou na thread");

        ProcessBuilder processBuilder = trabalhador.getProcessBuilder();
        processBuilder.command("bash", "-c", comando);


        try {
            trabalhador.setProcess(processBuilder.start());

            Process process = trabalhador.getProcess();


            StringBuilder output = new StringBuilder();

            //Verificar o encerramento do processo
            int cont = 0;
            while (cont < 10){
                System.out.println(cont);
                cont++;
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            //TODO remover o print
            System.out.println(output);

            byte [] byteOutput = output.toString().getBytes();
            notificacao.atividadePronta(byteOutput);
            status = Status.EM_ESPERA;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
