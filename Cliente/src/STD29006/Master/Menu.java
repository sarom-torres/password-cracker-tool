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
        System.out.println("\n1 - Trabalhadores online e seus status;\n" +
                "2 - Enviar tarefa para trabalhador;\n" +
                "3 - Encerrar processo de trabalhador;\n" +
                "4 - Encerrar os processos de todos trabalhadores;\n");
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

                case "2":
                    System.out.println("Entre com o arquivo de senhas:");
                    String arqSenhas = teclado.nextLine();
                    System.out.println("Entre com o arquivo dicionário:\n" +
                            "Atenção: Digite 0(zero) caso NÃO queira adicionar um arquivo dicionário");
                    String arqDic = teclado.nextLine();

                    try {
                        if (arqDic.equals("0")){
                            gerenciador.adicionarTarefa(arqSenhas,"s");
                        }else{
                            gerenciador.adicionarTarefa(arqSenhas,"s",arqDic,"d");
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    break;
                case "3":
                    try {
                        System.out.println(gerenciador.trabalhadoresOnline());
                        System.out.println("\nDigite o índice do processo que deseja terminar:");
                        String indice = teclado.nextLine();
                        gerenciador.encerrarProcesso(indice);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    String caminho = teclado.nextLine();

            }
        }
    }
}
