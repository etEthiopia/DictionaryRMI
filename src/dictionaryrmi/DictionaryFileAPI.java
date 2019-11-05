package dictionaryrmi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DictionaryFileAPI {
    
    private Map<String,String> DictionaryMap;
    private FileWriter fw;

    public DictionaryFileAPI() {
        loadFile();
    }
    
    public void updateFile(){
        File inputFile = new File("dict.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
            for (Entry<String, String> pairs : DictionaryMap.entrySet()) {
                writer.write(pairs.getKey() + "-" + pairs.getValue());
            }
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void loadFile(){
        try {
            DictionaryMap = new HashMap<>();
            BufferedReader in = new BufferedReader(new FileReader("dict.txt"));
            String line;
            while ((line = in.readLine()) != null) {
                String parts[] = line.split("-");
                DictionaryMap.put(parts[0], parts[1]);
            }
            in.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.toString());
        }
    }
    
    public String getDef(String word){
        if(DictionaryMap.containsKey(word)){
            return "DEF : " + DictionaryMap.get(word);
        }else {
            return "DEF NOT FOUND";
        }
    }
    
    public synchronized String addWord(String wrd, String meaning){
        if(DictionaryMap.containsKey(wrd)) return "WORD EXISTS";
        try{ 
            fw = new FileWriter("dict.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write( wrd +"-"+ meaning);
            bw.flush();
        } catch (Exception e) {
            return "COULD NOT ADD";
        }
        this.loadFile();
        return "SUCCESFULLY ADDED";
    }
    
    public synchronized String deleteWord(String wrd){
        DictionaryMap.remove(wrd);
        updateFile();        
        return "SUCCESFULLY DELETED";
    }
}
