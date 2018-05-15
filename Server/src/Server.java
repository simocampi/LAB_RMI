import java.net.URL;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements SharedServer {

    public Server(){};

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

        try {
            //Policy danno i permessi per la connessione
            System.setProperty("java.security.policy","file:./POLICY.policy");
            System.setProperty("java.rmi.server.codebase","file:{workspace_loc}/MyServer/");
           //if(System.getSecurityManager()==null) System.getSecurityManager(new SecurityManager());
            System.setProperty("java.rmi.server.hostname","localhost");
            Server object= new Server();
            Registry registry;
            SharedServer stub= (SharedServer) UnicastRemoteObject.exportObject(object,0);
            try {
                registry = LocateRegistry.createRegistry(8000);
            }catch (RemoteException e){
                registry = LocateRegistry.getRegistry(8000);
            }
            registry.bind("Hello",stub);
            System.out.println("Server ready");
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public void connect(SharedClient obj) {


    }

    @Override
    public void request(URL url) {

    }
}
