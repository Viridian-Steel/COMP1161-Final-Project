package contact;

public enum Gender {
    MALE,
    FEMALE;


    public static Gender toGender(String gender) {
        switch (gender) {
            case "Male":
            case "MALE":
            case "male":
                return MALE;

            case "Female":
            case "FEMALE":
            case "female":
                return FEMALE;
                
            default:
                return MALE;
                
        }
    }
}
