import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SharedClient extends Remote,Serializable {

        public void notify(int res) throws RemoteException;
}
