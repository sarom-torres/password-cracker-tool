package STD29006.Trabalhador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cracker extends Thread {

    private ProcessBuilder processBuilder;
    private String arqConf;

    //TODO mudar o caminho dos comandos
    private final String CMD_INCREMENTAL = "/usr/sbin/john -i=";
    private final String CMD_DICIONARIO = "/usr/sbin/john --wordlist:";
    private final String CMD_AUTOMATICO = "/usr/sbin/john senhas.txt";
    private final String ARQ_INCREMENTAL = " senhas.txt";
    private final String ARQ_DICIONARIO = " dicionario.txt senhas.txt";

    private String arq;
    private String comando;

    public Cracker(String estrategia, String cmd){
        this.processBuilder = new ProcessBuilder();

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

        processBuilder.command("bash", "-c", comando);
        try {
            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            //TODO devolver para o mestre o resultado
            System.out.println(output);

            //TODO como apagar arquivo de senhas após a execução


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
