import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client implements SharedClient {

    public Client(){};

    public static void main(String[] args) throws RemoteException, NotBoundException {

        try {
            Registry registry = LocateRegistry.getRegistry(8000);
            SharedServer stub = (SharedServer) registry.lookup("Hello");
           // String response = stub.sayHello();
           // System.out.println("response: " + response);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void notify(String res) throws RemoteException {

    }
}
