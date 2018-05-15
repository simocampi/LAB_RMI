import com.sun.corba.se.spi.ior.ObjectKey;

import java.io.Serializable;
import java.net.URL;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SharedServer extends Remote, Serializable {

    public void connect(SharedClient obj) throws RemoteException;
    public void request(URL url) throws RemoteException;

}


