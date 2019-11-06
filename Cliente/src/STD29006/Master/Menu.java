package STD29006.Master;

import STD29006.TrabalhadorDistribuido;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu extends Thread {


    private Gerenciador gerenciador;

    public Menu(Gerenciador gerenciador){

        this.gerenciador = gerenciador;
    }

    public void mostraMenu(){
        System.out.println("1 - Trabalhadores online e seus status;\n" +
                "2 - Enviar tarefa para trabalhador;\n" +
                "3 - Enviar arquivo para trabalhador;\n" +
                "4 - Encerrar processo de trabalhador;\n" +
                "5 - Encerrar os processos de todos trabalhadores;\n");
    }
    public void run(){

        while(true) {

            mostraMenu();

            Scanner teclado = new Scanner(System.in);
            String item = teclado.nextLine();

            switch (item) {
                case "1":
                    try {
                        System.out.println(gerenciador.qtOnline());
                        System.out.println(gerenciador.trabalhadoresOnline());
                    } catch (RemoteException ex) {
                        Logger.getLogger(Master.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                //Exemplos de tarefas a serem enviadas para o servidor?
                case "2":
                    System.out.println("Entre com o arquivo de senhas:");
                    String arqSenhas = teclado.nextLine();
                    try {
                        gerenciador.enviaTarefa(arqSenhas);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    break;
                case "3":
                    System.out.println("Entre com o caminho do arquivo.");
                    String caminho = teclado.nextLine();

            }
        }
    }
}
