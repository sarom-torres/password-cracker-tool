package STD29006.Master;

import STD29006.Status;
import STD29006.TrabalhadorDistribuido;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.Vector;

public class Gerenciador {

    private Vector<TrabalhadorDistribuido> trabOnline;
    private ArrayList<String> listaEstrategia;

    public Gerenciador(Vector<TrabalhadorDistribuido>trabOnline) {
        this.trabOnline = trabOnline;
        listaEstrategia = new ArrayList<>();

        //TODO está correto fazer isso no construtor?
        listaEstrategia.add("all5");
        listaEstrategia.add("all6");
        listaEstrategia.add("all7");
        listaEstrategia.add("all8");
    }

    public String trabalhadoresOnline() throws RemoteException {
        String dados = "";
        if(trabOnline.size() != 0){
            int cont = 1;
            for (TrabalhadorDistribuido trab : trabOnline) {
                dados += cont + " - Trabalhador " + trab.getNome() +
                        " está online e " + trab.getStatus() +".\n";
                cont++;
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

    public Vector<Integer> qtOcupado() throws RemoteException {
        Vector <Integer> indicesOcupado = new Vector<>();
        for (TrabalhadorDistribuido trab: trabOnline) {
            if(trab.getStatus() == Status.OCUPADO){
                indicesOcupado.add(trabOnline.indexOf(trab));
            }
        }
        return indicesOcupado;
    }

    /**
     * Adiciona uma tarefa ao trabalhador passando o arquivos e seus tipos
     * @param nomeArquivo1 nome do arquivo a ser transmitido ao trabalhador
     * @param nomeArquivo2 nome do arquivo a ser transmitido ao trabalhador
     * @param tipoArq1 tipo do arquivo a ser transmitido
     *                 s : para arquivo de senhas
     *                 d: para arquivo de dicionario
     * @param tipoArq2 tipo do arquivo a ser transmitido
     * * */
    public boolean adicionarTarefa(String nomeArquivo1,String tipoArq1,String nomeArquivo2,String tipoArq2) throws RemoteException{

        byte [] arqEnv1 = serializarArquivo(nomeArquivo1);
        byte [] arqEnv2 = serializarArquivo(nomeArquivo2);

        for (TrabalhadorDistribuido trab: trabOnline) {
            if(trab.getStatus()== Status.EM_ESPERA){
                trab.receberArquivo(arqEnv1,tipoArq1);
                trab.receberArquivo(arqEnv2,tipoArq2);
                //Comando dois para fazer a quebra com o dicionario;
                trab.receberTarefa(listaEstrategia.get(0),"2");
                listaEstrategia.add(listaEstrategia.remove(0));
                return true;
            }
        }
        return false;
    }

    /**
     * Adiciona uma tarefa ao trabalhador passando o arquivos e seus tipos
     * @param nomeArquivo1 nome do arquivo a ser transmitido ao trabalhador
     * @param tipoArq1 tipo do arquivo a ser transmitido
     *                 s : para arquivo de senhas
     *                 d: para arquivo de dicionario
     * * */
    public boolean adicionarTarefa(String nomeArquivo1,String tipoArq1) throws RemoteException{

        byte [] arqEnv1 = serializarArquivo(nomeArquivo1);


        for (TrabalhadorDistribuido trab: trabOnline) {
            if(trab.getStatus()== Status.EM_ESPERA){
                trab.receberArquivo(arqEnv1,tipoArq1);
                //Cmd = 1  para fazer a quebra incremental;
                trab.receberTarefa(listaEstrategia.get(0),"1");
                listaEstrategia.add(listaEstrategia.remove(0));
                return true;
            }
        }
        return false;
    }

    /**
     * Chama o método parar execução do trabalahdor distribuído,
     * caso a execução seja para retorna o UUID do processo terminado,
     * caso não retorna UUID 00000000-0000-0000-0000-000000000000
     * @param  indice posição mostrada em tela para o usuario
     * */
    public UUID encerrarProcesso(String indice) throws RemoteException {

        int i = Integer.parseInt(indice);
        TrabalhadorDistribuido trab = trabOnline.get(i-1);
        if(trab.pararExecucao()){
            return trab.getNome();
        }else{
            return new UUID(0,0);
        }

    }

    /**
     * Esse método é usado pela classe Gerenciador serializar as linhas
     * do arquivo e transformá-las em um vetor de bytes
     * @param nomeArquivo é o nome do arquivo a ser serializado
     * */
    private byte[] serializarArquivo(String nomeArquivo){

        File arquivo = new File(nomeArquivo);

        int tamanho = (int)arquivo.length();
        byte[] buffer = new byte[tamanho];
        FileInputStream file  = null;
        try {
            file = new FileInputStream(arquivo);
            file.read(buffer, 0, tamanho);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return buffer;
    }


}
