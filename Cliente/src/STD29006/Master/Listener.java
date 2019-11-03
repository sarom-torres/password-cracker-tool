package STD29006.Master;

import STD29006.ListenerDistribuido;
import STD29006.ObservadoDistribuido;

public class Listener implements ListenerDistribuido {

    @Override
    public void notificar(ObservadoDistribuido lst) {
        //Chamado quando ocorrer uma alteração em "obs"
        //Atualiza dados, executa código que deve ser executado quando "obs" for alterado, etc.
        //Aqui dentro pode-se chamar GETTERS de "obs" para obter os novos dados em "obs", como chamar "obs.getAtributoQualquer()"

        System.out.println("Foi notificado!!! ;) ");
    }
}
