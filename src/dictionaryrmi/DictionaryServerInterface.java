package dictionaryrmi;
import java.rmi.*;

public interface DictionaryServerInterface extends Remote {
    String find(String a) throws RemoteException;
    String add(String a, String b) throws RemoteException;
    String remove(String a) throws RemoteException;
}
