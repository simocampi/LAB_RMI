import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.LinkedBlockingQueue;

public class Server implements SharedServer {
    private LinkedBlockingQueue<SharedClient> cli;

    public Server(){

        cli= new LinkedBlockingQueue();
    };

    //prende uno stub del client, in modo tale che il server sa l'indirizzo del client
    //Serve una struttura dati bloccante, per poter, quando invocato dal client, registrare reference al client

    void notifyAll(int resp) throws RemoteException {

        for(SharedClient sc:cli)  sc.notify(resp);
    }

    @Override
    public void connect(SharedClient obj) throws InterruptedException,RemoteException {
        if( obj != null) cli.put(obj);
    }

    @Override
    public int request(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        notifyAll(connection.getResponseCode());
        return connection.getResponseCode();
    }

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

        try {
            //Policy danno i permessi per la connessione
            System.setProperty("java.security.policy","file:C:\\Users\\simoc\\Dropbox\\laboratorio\\SecondoAnno\\PCAD\\Lab6\\LAB_RMI\\out\\POLICY.policy");
            // System.setProperty("java.rmi.server.codebase","file:{workspace_loc}/MyServer/");
            if(System.getSecurityManager()==null) System.setSecurityManager(new SecurityManager());
            // System.setProperty("java.rmi.server.hostname","localhost");
            Server object= new Server();
            Registry registry;
            SharedServer stub= (SharedServer) UnicastRemoteObject.exportObject(object,0);
            try {
                registry = LocateRegistry.createRegistry(3040);
            }catch (RemoteException e){
                registry = LocateRegistry.getRegistry(3040);
            }
            registry.bind("Shared",stub);
            System.out.println("Server ready");
        }catch (Exception e){
            e.printStackTrace();
        }
        while(true);
    }

}
