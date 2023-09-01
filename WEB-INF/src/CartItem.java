import java.io.Serializable;
import java.util.Objects;

public class CartItem implements Serializable {
    // instance variables
    private static final long serialVersionUID = 10L;

    private String imgAddress;
    private String name;
    private int price;

    // constructor
    public CartItem(String imgAddress, String name, int price) {
        this.imgAddress = imgAddress;
        this.name = name;
        this.price = price;
    }

    // Mutator
    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    // Accessor
    public String getImgAddress() {
        return this.imgAddress;
    }

    // Mutator
    public void setName(String name) {
        this.name = name;
    }

    // Accessor
    public String getName() {
        return this.name;
    }

    // Mutator
    public void setPrice(int price) {
        this.price = price;
    }

    // Accessor
    public int getPrice() {
        return this.price;
    }

    // equals method
        @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }

        CartItem that = (CartItem) other;
        return this.getName().equals(that.getName());
    }

    // hash code method
        @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    // toString
    public String toString() {
        return "Item: " + this.name;
    }

    public static void main(String[] args) {
        CartItem item1 = new CartItem("NBA.com", "Basketball", 55);
        CartItem item2 = new CartItem("Espn.com", "Basketball", 65);

        System.out.printf("Name: %s | HashCode: %s\n", item1.getName(), item1.hashCode());
        System.out.printf("Name: %s | HashCode: %s\n", item2.getName(), item2.hashCode());
       
        System.out.println("Do item1 and item2 have the same name: " + item1.equals(item2));
    }
}
