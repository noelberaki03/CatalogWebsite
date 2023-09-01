import java.util.HashMap;
import java.util.Map;

public class CartManager {
    // instance variables
    private Map<String, Map<CartItem, Integer>> userCarts;

    // constructor
    public CartManager(Map<String, Map<CartItem, Integer>> userCarts) {
        this.userCarts = new HashMap<>(userCarts);
    }

    // constructor
    public CartManager() {
        this.userCarts = new HashMap<>();
    }

    // retrieves all user carts
    public Map<String, Map<CartItem, Integer>> getUserCarts() {
        return new HashMap<>(this.userCarts);
    }

    // return user cart for specific user
    public Map<CartItem, Integer> getUserCart(String email) {
        return userCarts.get(email);
    }

    // add item to a user's cart
    public void addToCart(String email, CartItem item) {
        Map<CartItem, Integer> userCart = userCarts.get(email);

        if (userCart == null) {
            userCart = new HashMap<>();
            userCarts.put(email, userCart);
        }
        
        if (userCart.containsKey(item)) {
            userCart.put(item, userCart.get(item) + 1);
        }
        else {
            userCart.put(item, 1);
        }
    }

    // remove item from a user's cart
    public void subractItemFromCart(String email, CartItem item) {
        Map<CartItem, Integer> userCart = userCarts.get(email);

        if (userCart == null) {
            userCart = new HashMap<>();
            userCarts.put(email, userCart);
        }
        
        if (userCart.containsKey(item)) {
            userCart.put(item, userCart.get(item) - 1);
        }
        else {
            userCart.put(item, 0);
        }
    }

    public static void main(String[] args) {
        User user1 = new User("Noel", "Beraki", "noelberaki30@gmail.com", "123");
        User user2 = new User("Leon", "James", "noelberaki03@gmail.com", "1234");

        CartItem item1 = new CartItem("NBA.com", "Basketball", 55);
        CartItem item2 = new CartItem("NFL.com", "Football", 35);
        CartManager manageCarts = new CartManager();
        
        manageCarts.addToCart(user1.getEmail(), item1);
        manageCarts.addToCart(user1.getEmail(), item1);
        manageCarts.addToCart(user1.getEmail(), item1);
        manageCarts.addToCart(user1.getEmail(), item2);
        manageCarts.addToCart(user1.getEmail(), item2);

        manageCarts.addToCart(user2.getEmail(), item1);
        manageCarts.addToCart(user2.getEmail(), item1);
        manageCarts.addToCart(user2.getEmail(), item2);
        manageCarts.addToCart(user2.getEmail(), item2);
        manageCarts.addToCart(user2.getEmail(), item2);


        System.out.println("\n\nNoel's cart");
        System.out.println("--------------");
        System.out.println(manageCarts.getUserCart(user1.getEmail()));
        System.out.println("\n--------------");
        System.out.println("Everyone's cart");
        System.out.println(manageCarts.getUserCarts());
        System.out.println("--------------\n");

        System.out.println("\nNoel's cart updated");
        System.out.println("--------------");
        manageCarts.subractItemFromCart(user1.getEmail(), item2);
        System.out.println(manageCarts.getUserCart(user1.getEmail()));
        System.out.println("--------------\n");
    }   
}
