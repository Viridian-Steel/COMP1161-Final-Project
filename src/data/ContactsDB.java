/**
 * @author 620156598
 * @version 0.1
 */

package data;
import java.io.*;
import java.util.ArrayList;



import contact.*;
import errHandling.*;

/**
 * Saves and Loads the Contact Database from file.
 */
public class ContactsDB {

    private String filename;
    ObjectInputStream in;
    ObjectOutputStream out;

    private ArrayList<Contact> db = new ArrayList<Contact>();
    
    //Autosave.

    public ContactsDB(String filename){
        this.filename = filename;
        
        try {
            File file = new File(filename);
            if(file.createNewFile()){
                System.out.println("File Created");
            }
            else{
                System.out.println("File Exists");
            }
        } catch (IOException e) {
            System.out.println("File Error");
            
        }

        try {
            in = new ObjectInputStream(new FileInputStream(filename));
            updateDb();
            in.close();
        } catch (IOException | NullPointerException ioe) {
            System.out.println("Error while reading File");
            ioe.printStackTrace();
        } catch (SecurityException sec_e){
            System.out.println("Object presents a security threat");
            System.out.println(sec_e.getMessage());
        } catch (ClassNotFoundException clE){
            clE.printStackTrace();
        }
    
    }

    /**
     * Gets the current DB
     * @return the ArrayList of Contacts;
     */
    public ArrayList<Contact> getDb() {
        return db;
    }
    
    /**
     * Updates the Database and then Saves
     * @param db The Up-to-Date ArrayList of Contacts
     * @return {@link CaResult#CARESULT_SUCESS} if sucessful, {@link CaResult#CARESULT_SERIALIZATION_ERROR} or {@link CaResult#CARESULT_FILE_ERROR} if unsucessful
     */
    public CaResult updateDb(ArrayList<Contact> db) {
        this.db = db;
        return Save();
    }
    
    /**
     * Populates the Database by pulling from the SaveFile.
     * 
     * @return {@link CaResult#CARESULT_SUCESS} if sucessful. 
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws EOFException
     * @see {@link ContactsDB#updateDb(ArrayList)}
     */
    private CaResult updateDb() throws ClassNotFoundException, IOException{
        
        try {
            db = (ArrayList<Contact>) in.readObject();
        } catch (EOFException e) {
           System.out.println("File Empty");
        } //this is fine.
        return CaResult.CARESULT_SUCESS;

    }
     
    /**
     * Saves the currently loaded Datbase into a file
     * @return {@link CaResult#CARESULT_SUCESS} if sucessful, {@link CaResult#CARESULT_SERIALIZATION_ERROR} or {@link CaResult#CARESULT_FILE_ERROR} if unsucessful
     * 
     */
    private CaResult Save(){
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename, false));
            out.writeObject(db);
            out.flush();
            out.close();
            return CaResult.CARESULT_SUCESS;
        } catch (InvalidClassException | NotSerializableException icE) {
            System.out.println("There was a error serializing objets");
            System.out.println(icE.getMessage());
            return CaResult.CARESULT_SERIALIZATION_ERROR;
        } catch(IOException ioE){
            System.out.println("There was a problem saving the file");
            System.out.println(ioE.getMessage());
            return  CaResult.CARESULT_FILE_ERROR;
        }

    }

    /**
     * Closes the file
     * @return {@link CaResult#CARESULT_SUCESS} if sucessful, {@link CaResult#CARESULT_FILE_ERROR} if unsucessful
     */
    public CaResult close () {
        try{
            out.close();
            return CaResult.CARESULT_SUCESS;
        } catch (IOException | NullPointerException io) {
            System.out.println("Problem closing the file.");
            System.out.println(io.getMessage());
            return CaResult.CARESULT_FILE_ERROR;
        }
    }

    
}
