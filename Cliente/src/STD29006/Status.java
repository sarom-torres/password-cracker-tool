package STD29006;

import java.rmi.Remote;

/*
* Status que podem ser usados pelo servidor
*
*/

public enum Status implements Remote {
    /*
    * Quando o servidor está online mas sem executar nenhuma tarefa
    * */
    EM_ESPERA,

    /*
    * Quando o servidor está online executando uma tarefa
    * */
    PRONTO;
}