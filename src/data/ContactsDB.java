package data;
import java.io.*;
import java.util.ArrayList;



import contact.*;
import errHandling.*;

public class ContactsDB {

    private String filename;
    private char delimiter;
    private BufferedReader fileR;
    private BufferedWriter fileW;
    private String temp;

    private ArrayList<Contact> db = new ArrayList<Contact>();
    

    

    //Autosave.

    public ContactsDB(String filename, char delimiter){
        this.filename = filename;
        this.delimiter = delimiter;

        try {
            fileR = new BufferedReader(new FileReader(filename));
            updateDb();
        } catch (IOException ioe) {
            System.out.println("There was a problem reading the data file.");
            System.out.println(ioe.getMessage());
        }



    }


    // TODO: Check necessity of this function
    public String getFilename() {
        return filename;
    }
    
    
    // TODO: Check necessity of this function
    public char getDelimiter() {
        return delimiter;
    }


    public ArrayList<Contact> getDb() {
        return db;
    }

    public CaResult updateDb(ArrayList<Contact> db) {
        this.db = db;

        CaResult r = Save();
        
        return CaResult.CARESULT_SUCESS;
    }

    private CaResult updateDb(){
        // While there's another line
        // create new contacts and add them to db
        // if error then skip

        return CaResult.CARESULT_SUCESS;
    }
     
    private CaResult Save(){
        StringBuilder out = new StringBuilder();
        rewrite();
        for(Contact contact : db){
            
        }

        return CaResult.CARESULT_SUCESS;
    }

    /**
     * hasNext Method - checks if there is more data to be read from the file
     * @return returns true or false
     */
    public boolean hasNext() {
        boolean result = false;
        try{
            String line = fileR.readLine();
            temp = line;
            result = line != null;
        } catch (IOException | NullPointerException io) {
            System.out.println("Problem executing hasNext()");
            System.out.println(io.getMessage());
        }
        return result;
    }

    /**
     * Reads a line of text from the file
     * splits the line into tokens,
     * and returns an array containing the tokens (strings) from the line that was split
     * @return A string array with client data
     */
    public String[] getNext() {
        String[] tokens;
        // Do not split commented lines
        if (temp.startsWith("#")){
            tokens = new String[1];
            tokens[0] = temp;
        } else {
            tokens = temp.split(Character.toString(delimiter), -1);
        }
        return tokens;
    }

    /**
     * Closes the file and reopens it for data to be written to it
     */
    public void rewrite () {
        try{
            fileR.close();
            fileW = new BufferedWriter(new FileWriter(filename, false));
        } catch (IOException | NullPointerException io) {
            System.out.println("Problem executing rewrite()");
            System.out.println(io.getMessage());
        }
    }



    /**
     * Closes the file
     */
    public void close () {
        try{
            fileW.close();
        } catch (IOException | NullPointerException io) {
            System.out.println("Problem closing the file.");
            System.out.println(io.getMessage());
        }
    }
// Method to update contact db
    // Method to retrieve db

    
}

    
