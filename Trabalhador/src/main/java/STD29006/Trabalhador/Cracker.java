package STD29006.Trabalhador;

import STD29006.NotificacaoDistribuida;
import STD29006.Status;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cracker extends Thread {

    //TODO mudar o caminho dos comandos
    private final String CMD_INCREMENTAL = "/usr/sbin/john -i=";
    private final String CMD_DICIONARIO = "/usr/sbin/john --wordlist:dicionario.txt senhas.txt";
    private final String CMD_AUTOMATICO = "/usr/sbin/john senhas.txt";
    private final String ARQ_INCREMENTAL = " senhas.txt";
    private String comando;
    private NotificacaoDistribuida notificacao;
    private Status status;
    private Trabalhador trabalhador;


    public Cracker(String estrategia, String cmd, NotificacaoDistribuida notificacao, Status status,Trabalhador trabalhador){

        this.notificacao = notificacao;
        this.status = status;
        this.trabalhador = trabalhador;

        System.out.println(status);
        if(cmd.equals("1")){
            this.comando = CMD_INCREMENTAL+estrategia+ARQ_INCREMENTAL;
        }else if(cmd.equals("2")){
            this.comando = CMD_DICIONARIO;
            System.out.println(comando);
        }else{
            this.comando = CMD_AUTOMATICO;
        }
    }

    public void run(){


        System.out.println("Executando comando " + comando);

        ProcessBuilder processBuilder = trabalhador.getProcessBuilder();
        processBuilder.command("bash", "-c", comando);


        try {
            trabalhador.setProcess(processBuilder.start());

            Process process = trabalhador.getProcess();
            
            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));


            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                output.append(line + "\n");
            }

            byte [] byteOutput = output.toString().getBytes();
            trabalhador.setStatus(Status.EM_ESPERA);
            notificacao.atividadePronta(byteOutput);
            System.out.println("Termino da execução...");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
