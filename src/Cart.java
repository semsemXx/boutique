import java.util.ArrayList;

public class Cart {
    private ArrayList<Game> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public void addGame(Game game) {
        cartItems.add(game);
    }

    public ArrayList<Game> getCartItems() {
        return cartItems;
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }
}
