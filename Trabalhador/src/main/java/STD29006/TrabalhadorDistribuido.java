package STD29006;

import java.io.File;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface TrabalhadorDistribuido extends Remote {

    /**
     * Trabalhador recebe o arquivo serializado em bytes e o escreve em formato .txt
     * @param arqSerial array de baytes contendo o arquivo transmitido
     * @param tipo deifne o tipo do arquivo transmitido
     * s : arquivo de senhas
     * d : arquivo de dicionário
     * @throws RemoteException
     * */
    public boolean receberArquivo(byte[] arqSerial, String tipo) throws RemoteException;

    /**
     * Recebe a tarefa enviada pelo master e dispara uma thread para executá-la
     * @param estrategia define qual estratégia será usada pelo john para quebrar a senha:
     * All5, All6, All7, All8
     * @param cmd define qual comando será executado pelo john
     * 1 => john -i=digits nome_do_arquivo.txt
     * 2 => john --wordlist:arquivo_dicionario.txt nome_do_arquivo.txt
     * @throws IOException
     * */
    public boolean receberTarefa(String estrategia, String cmd) throws IOException;

    /**
     * Retorna um Enum Status que define se o trabalahdor está EM_ESPERA ou OCUPADO
     * @throws RemoteException
     * */
    public Status getStatus() throws RemoteException;

    /**
    * Encerra um processo o processo de quebra em execução
    * @throws RemoteException
    * */
    public boolean pararExecucao() throws RemoteException;

    /**
     * Retorna o UUID do Trabalhador
     * @throws RemoteException
     **/
    public UUID getNome() throws RemoteException;
}
