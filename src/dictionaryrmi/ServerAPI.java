package dictionaryrmi;

import java.net.*;
import java.rmi.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerAPI {

    private  static ServerAPI serverAPI=null;
    public DictionaryServerInterface ds;

    public ServerAPI() throws MalformedURLException{
        try {
            ds = (DictionaryServerInterface) Naming.lookup("rmi://"+Constants.SERVER_IP+"/dictionaryService");
        } catch (NotBoundException | RemoteException ex) {
            Logger.getLogger(ServerAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ServerAPI getServerAPI(){
        if (serverAPI == null){
            try {
                serverAPI=new ServerAPI();
            }catch (Exception e) {
                System.out.println("Network Error");
            }
        }
        return serverAPI;
    }

    public String search(String keyWord){
        String res="No Found";
        try {
            res=ds.find(keyWord);
        } catch (RemoteException ex) {
            Logger.getLogger(ServerAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public String add(String keyWord,String definition){
        String res="No Response";
        try {
            res=ds.add(keyWord,definition);
        } catch (RemoteException ex) {
            Logger.getLogger(ServerAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public String delete(String keyWord){
        String res="No Response";
        try {
            ds.remove(keyWord);
        } catch (RemoteException ex) {
            Logger.getLogger(ServerAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

}