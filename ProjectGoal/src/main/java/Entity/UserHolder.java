package Entity;

public class UserHolder {
    private static User INSTANCE;
    public static User getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new User();
        }
        return INSTANCE;
    }
}

