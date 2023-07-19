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
        } catch(EOFException e){
            System.out.println("Error while reading File: File is empty ");
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
    @SuppressWarnings("unchecked") //I didn't know that was a thing until 5 seconds ago
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
     * @throws IOException
     */
    public void close () throws IOException {
        out.close();
    }

    
}
