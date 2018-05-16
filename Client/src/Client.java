import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.AccessControlException;

public class Client implements SharedClient {
    private static SharedServer server;
    private static SharedClient client;
    private int numClient=0;
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {

        URL url= new URL("http://www.google.it");

        System.setProperty("java.security.policy","file:./POLICY.policy");
        System.setProperty("java.rmi.server.codebase","file:${workspace_loc}/Client/");
        //if (System.getSecurityManager() == null) System.setSecurityManager(new SecurityManager());
        try {
            Registry registry = LocateRegistry.getRegistry("127.1.1.0", 3040);
            for(String s : registry.list()) System.out.println(s);
            client=(SharedClient) UnicastRemoteObject.exportObject(new Client(),0);
            server=(SharedServer) registry.lookup("Shared");
            server.connect(client);
            server.request(url);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void notify(int res) throws RemoteException {
        System.out.println("Connection Succeded, Response Code: "+res+ " Client "+numClient++);
    }
}
