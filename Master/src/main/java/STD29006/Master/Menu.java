package STD29006.Master;

import STD29006.TrabalhadorDistribuido;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu extends Thread {

    private Gerenciador gerenciador;
    private UUID uuidControle;


    public Menu(Gerenciador gerenciador){

        this.gerenciador = gerenciador;
        uuidControle = new UUID(0,0);
    }

    public void mostraMenu(){
        System.out.println("\n1 - Trabalhadores online e seus status;\n" +
                "2 - Enviar tarefa para trabalhador;\n" +
                "3 - Encerrar processo de trabalhador;\n" +
                "4 - Encerrar os processos de todos trabalhadores;\n"+
                "0 - Sair;\n");
    }
    public void run(){

        boolean condicao = true;
        while(condicao) {

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
                        UUID id = gerenciador.encerrarProcesso(indice);
                        if(id != uuidControle){
                            System.out.println("O processo do trabalhador "+ id.toString()+" foi encerrado.");
                        }else{
                            System.out.println("O processo não foi finalizado");
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case "4":
                    try {
                        System.out.println(gerenciador.trabalhadoresOnline());
                        Vector<Integer> indicesOcupados = gerenciador.qtOcupado();
                        for(Integer ind: indicesOcupados){
                            UUID id = gerenciador.encerrarProcesso(ind.toString());
                            if(id != uuidControle){
                                System.out.println("O processo do trabalhador "+ id.toString()+" foi encerrado.");
                            }else{
                                System.out.println("O processo não foi finalizado");
                            }
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case "0":
                    condicao = false;
                    break;

                default:
                    System.out.println("Comando inválido");
                    break;
            }
        }
    }
}
