package STD29006.Trabalhador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cracker extends Thread {

    private ProcessBuilder processBuilder;
    private String arqConf;
    private String comando;

    public Cracker(String arqConf){
        this.processBuilder = new ProcessBuilder();
        this.arqConf = arqConf;
    }

    public void run(){
        System.out.println("Entrou na thread");

        this.comando = "john -i="+arqConf+" senhas.txt";

        //TODO O comando é executado como sudo, como fazer?
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
