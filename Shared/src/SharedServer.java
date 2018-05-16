import com.sun.corba.se.spi.ior.ObjectKey;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SharedServer extends Remote, Serializable {

    public void connect(SharedClient obj) throws RemoteException, InterruptedException;
    public int request(URL url) throws IOException;

}


