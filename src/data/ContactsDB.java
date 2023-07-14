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
            in = new ObjectInputStream(new FileInputStream(filename));
            updateDb();
            in.close(); // just closing it manually, no need for it to be open after initialization.
        } catch (IOException | NullPointerException ioe) {
            System.out.println("Error while reading File");
            System.out.println(ioe.getMessage());
        } catch (SecurityException sec_e){
            System.out.println("Object presents a security threat");
            System.out.println(sec_e.getMessage());
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
     * {@link CaResult#CARESULT_CLASS_MISMATCH_ERROR} if the Class used is not seriablizable, contains a class tht is not serializable, or is incompatible with the class in the SaveFile.
     * {@link CaResult#CARESULT_DESERIALIZATION_ERROR} if the InputStream or File is Corrupted, {@link CaResult#CARESULT_FILE_ERROR} if an IOException is encountered.
     * @see {@link ContactsDB#updateDb(ArrayList)}
     */
    private CaResult updateDb(){
        try{
            db = (ArrayList<Contact>) in.readObject(); //this is fine.
            return CaResult.CARESULT_SUCESS;
            
        } catch (ClassNotFoundException | InvalidClassException clsE) {
            System.out.println("Class Error.");
            System.out.println(clsE.getMessage());
            return CaResult.CARESULT_CLASS_MISMATCH_ERROR;
        } catch (StreamCorruptedException | InvalidObjectException objE){
            System.out.println("Stream Error.");
            System.out.println(objE.getMessage());
            return CaResult.CARESULT_DESERIALIZATION_ERROR;
        } catch (IOException ioe){
            System.out.println("IO Error.");
            System.out.println(ioe.getMessage());
            return CaResult.CARESULT_FILE_ERROR;
        } catch (ClassCastException cE){
            System.out.println("..guess I need a new file handling system");
            System.out.println(cE.getMessage());
            return CaResult.CARESULT_CLASS_MISMATCH_ERROR;
        }

    }
     
    /**
     * Saves the currently loaded Datbase into a file
     * @return {@link CaResult#CARESULT_SUCESS} if sucessful, {@link CaResult#CARESULT_SERIALIZATION_ERROR} or {@link CaResult#CARESULT_FILE_ERROR} if unsucessful
     * 
     */
    private CaResult Save(){
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
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
