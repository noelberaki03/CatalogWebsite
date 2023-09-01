import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    // instance variables
    private static final long serialVersionUID = 10L;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    // constructor
    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    // constructor
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Mutator
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Accessor
    public String getFirstName() {
        return this.firstName;
    }

    // Mutator
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Accessor
    public String getLastName() {
        return this.lastName;
    }

    // Mutator
    public void setEmail(String email) {
        this.email = email;
    }

    // Accessor
    public String getEmail() {
        return this.email;
    }

    // Mutator
    public void setPassword(String password) {
        this.password = password;
    }

    // Accessor
    public String getPassword() {
        return this.password;
    }


    // equals method
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        User that = (User) other;
        return this.getEmail() != null && this.getEmail().equalsIgnoreCase(that.getEmail());
    }


    // hash code method
        @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    // toString
    public String toString() {
        return String.format("Name: %s %s", this.firstName, this.lastName);
    }

    public static void main(String[] args) {
        User user1 = new User("Noel", "Beraki", "noelberaki03@gmail.com", "123");
        User user2 = new User("Leon", "James", "noelberaki03@gmail.com", "1234");

        System.out.printf("Email: %s | HashCode: %s\n", user1.getEmail(), user1.hashCode());
        System.out.printf("Email: %s | HashCode: %s\n", user2.getEmail(), user2.hashCode());
       
        System.out.println("Do user1 and user2 have the same email: " + user1.equals(user2));
    }
}