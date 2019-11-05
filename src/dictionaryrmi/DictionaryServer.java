package dictionaryrmi;

import java.net.MalformedURLException;
import java.rmi.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DictionaryServer {
    public static void main(String args[]) {
        try {
            DictionaryServerInterface dictionaryService=new Dictionary();
            Naming.rebind("dictionaryService",dictionaryService);
        }catch(RemoteException e){ 
            e.printStackTrace();
        } catch (MalformedURLException ex) {
            Logger.getLogger(DictionaryServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
