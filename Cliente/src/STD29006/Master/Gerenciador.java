package STD29006.Master;

import STD29006.Status;
import STD29006.TrabalhadorDistribuido;

import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Gerenciador {

    private Vector<TrabalhadorDistribuido> trabOnline;

    public Gerenciador(Vector<TrabalhadorDistribuido>trabOnline) {
        this.trabOnline = trabOnline;
    }

    public String trabalhadoresOnline() throws RemoteException {
        String dados = "";
        if(trabOnline.size() != 0){
            for (TrabalhadorDistribuido trab : trabOnline) {
                dados += "O Trabalhador " + trab.getNome() +
                        " está online e " + trab.getStatus() +".\n";
            }
        }
        return dados;
    }

    public String qtOnline() {
        String frase = "";
        int qt = trabOnline.size();
        if(qt == 0){
            frase = "Não há trabalhadores ONLINE.";
        } else if(qt == 1) {
            frase = qt + " trabalhador ONLINE.";
        }else{
            frase = qt + " trabalhadores ONLINE.";
        }
        return frase;
    }

    public boolean enviaTarefa(String nomeArquivo) throws RemoteException{

        for (TrabalhadorDistribuido trab: trabOnline) {
            if(trab.getStatus()== Status.EM_ESPERA){
                return enviarArquivo(nomeArquivo,trab);
            }
        }
        return false;
    }

    /**
     * Esse método é usado pela classe Gerenciador para ler as linhas
     * do arquivo e enviá-la ao trabalhador
     * @param nomeArquivo é o nome do arquivo a ser enviado
     * @param trab é o trabalahdor a qual será enviado o arquivo
     * */
    private boolean enviarArquivo(String nomeArquivo, TrabalhadorDistribuido trab){

        File arquivo = new File(nomeArquivo);

        try {
            Scanner leitor = new Scanner(arquivo);
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                System.out.println(linha);
                trab.receberLinha(linha);
            }
            String strEOF = "EOF";
            trab.receberLinha(strEOF);
            leitor.close();
            return true;
        }catch (Exception e){
            System.err.println("Erro ao ler arquivo: " + e.toString());
        }
        return false;
    }
}
