package STD29006.Trabalhador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cracker extends Thread {

    private ProcessBuilder processBuilder = new ProcessBuilder();

    public void run(){
        System.out.println("Entrou na thread");
        processBuilder.command("bash", "-c", "echo Hello World!");
//        processBuilder.command("echo Hello World!");
        try {
            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            System.out.println(output);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
