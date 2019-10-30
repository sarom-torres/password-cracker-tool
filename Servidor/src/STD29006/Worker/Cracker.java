package STD29006.Worker;
import STD29006.CrackerDistribuido;

import java.rmi.RemoteException;

public class Cracker implements CrackerDistribuido {

    @Override
    public String sayHello() throws RemoteException {
        return "Hello World";
    }
}
