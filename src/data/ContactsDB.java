/**
 * @author 620156598
 * @version 0.1
 */

package data;
import java.io.*;
import java.util.ArrayList;

import contact.*;

/**
 * Saves and Loads the Contact Database from file.
 */
public class ContactsDB {

    private String filename;
    ObjectInputStream in;
    ObjectOutputStream out;

    private ArrayList<Contact> db = new ArrayList<Contact>();

    public ContactsDB(String filename){
        this.filename = filename;
        
        try {
            File file = new File(filename);
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("File Error");
            
        }

        try {
            in = new ObjectInputStream(new FileInputStream(filename));
            updateDb();
            in.close();
        } catch(EOFException e){
            System.out.println("File is empty.");
        }catch (IOException | NullPointerException ioe) {
            System.out.println("Error while reading File. " + ioe.getMessage());
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
     * 
     */
    public void updateDb(ArrayList<Contact> db) {
        this.db = db;
        try {
            Save();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Populates the Database by pulling from the SaveFile.
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws EOFException
     */
    @SuppressWarnings("unchecked") 
    private void updateDb() throws ClassNotFoundException, IOException{
        try {
            db = (ArrayList<Contact>) in.readObject();
        } catch (EOFException e) {
           System.out.println("File Empty");
        } catch(ClassCastException ccE){
            System.out.println("Cannot read data form file. " + filename + " may be corrupted.");
        }
    }
     
    /**
     * Saves the currently loaded Datbase into a file
     * @throws IOException
     * @throws FileNotFoundException
     * 
     */
    private void Save() throws FileNotFoundException, IOException{
        out = new ObjectOutputStream(new FileOutputStream(filename, false));
        out.writeObject(db);
        out.flush();
        out.close();

    }

    /**
     * Closes the file
     * @throws IOException If an I/O Error has occured
     */
    public void close () throws IOException {
        out.close();
    }

    
}
