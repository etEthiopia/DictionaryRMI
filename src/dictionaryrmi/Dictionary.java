package dictionaryrmi;

import java.rmi.*;
import java.rmi.server.*;

public class Dictionary extends UnicastRemoteObject implements DictionaryServerInterface
{       
    DictionaryFileAPI dfa;
	
    public Dictionary()throws RemoteException{            
        super();
        dfa=new DictionaryFileAPI();
    }
    public String find(String key ){
        return dfa.getDef(key);
    }
    public String add(String key,String value){
        return dfa.addWord(key, value);
    }
    public String remove(String key){
        return dfa.deleteWord(key);
    }
}
