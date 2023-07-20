/**
 * @author 620146910
 */

package contact;

import java.util.Comparator;
//implements comparator to sort by name
public class NameComp implements Comparator<Contact>{
    
    public int compare(Contact c1, Contact c2){
        return c1.getName().compareTo(c2.getName());
    }
}