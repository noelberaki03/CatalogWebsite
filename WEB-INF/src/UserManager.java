import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    // instance variables
    private Map<String, User> users;

    // constructor
    public UserManager(Map<String, User> map) {
        this.users = new HashMap<>(map);
    }

    // constructor
    public UserManager() {
        this.users = new HashMap<>();
    }

    // return users data
    public Map<String, User> getUsers() {
        return new HashMap<>(this.users);
    }

    public void registerUser(User userToRegister) throws InvalidParameterException, IllegalStateException  {
        if (userToRegister == null) {
            throw new InvalidParameterException("Not a valid user to register");
        }
        if (users.containsKey(userToRegister.getEmail())) {
            throw new IllegalStateException("User already registered");
        }

        System.out.println("Succesfully registered: " + userToRegister.getEmail());
        users.put(userToRegister.getEmail(), userToRegister);
    }

    public User loginUser(User userToLogin) throws InvalidParameterException, IllegalStateException {

        if (userToLogin.getEmail() == null || userToLogin.getEmail().isEmpty() ||
            userToLogin.getPassword() == null || userToLogin.getPassword().isEmpty()) {
            throw new InvalidParameterException("Empty login attempt");
        }
        if (!users.containsKey(userToLogin.getEmail())) {
            throw new IllegalStateException("User not registered: " + userToLogin.getEmail());
        }
        if (users.containsKey(userToLogin.getEmail())) {
            String userPassword = users.get(userToLogin.getEmail()).getPassword();
            if (!userToLogin.getPassword().equals(userPassword)) {
                throw new IllegalStateException("Incorrect password for user: " + userToLogin.getEmail());
            }
        }
        return users.get(userToLogin.getEmail());
    }

    public static void main(String[] args) {
        User user1 = new User("Noel", "Beraki", "noelberaki30@gmail.com", "123");
        User user2 = new User("Leon", "James", "noelberaki03@gmail.com", "1234");
        User user3 = new User("Leon", "James", "noelberaki@gmail.com", "1234");

        UserManager manageUsers = new UserManager();
        
        System.out.println("\n\n");
        try {
            manageUsers.registerUser(user1);
            manageUsers.registerUser(user2);
        }
        catch (InvalidParameterException | IllegalStateException e) {
            e.printStackTrace();
        }        

        System.out.println("\nUSERS:");
        System.out.println("----------------");
        System.out.println(manageUsers.getUsers());

        try {
            manageUsers.loginUser(user3);
        }
        catch (InvalidParameterException | IllegalStateException e) {
            e.printStackTrace();
        }
        

        System.out.println("\n\n");
    }

}
