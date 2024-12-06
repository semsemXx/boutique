
import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

class CartPanel extends JFrame {
    public CartPanel(Cart cart) {
        setTitle("Cart");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Display games in the cart
        DefaultListModel<String> cartModel = new DefaultListModel<>();
        for (Game game : cart.getCartItems()) {
            cartModel.addElement(game.getName() + " - $" + game.getPrice());
        }

        JList<String> cartList = new JList<>(cartModel);
        JScrollPane scrollPane = new JScrollPane(cartList);

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
