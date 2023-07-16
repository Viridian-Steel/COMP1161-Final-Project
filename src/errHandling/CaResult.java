/**
 * @author 620156598
 */
package errHandling;

/**
 * Enum Class used to Diagnose Issues in methods that would otherwise be void
 */
public enum CaResult {
    /**
     * Indicates a sucessful exectuion
     */
    CARESULT_SUCESS,

    /**
     * Indicates that An Error has Occured in File Handling
     */
    CARESULT_FILE_ERROR,

    /**
     * Indicates that a Class is being compared to an incompatable class 
     */
    CARESULT_CLASS_MISMATCH_ERROR,

    /**
     * Indicates that an Error has occured during Deserialization (Loading)
     */
    CARESULT_DESERIALIZATION_ERROR,

    /**
     *  Indicates that an error has occured during Serialization (Saving)
     */
    CARESULT_SERIALIZATION_ERROR;
}
