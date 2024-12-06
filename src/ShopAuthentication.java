import java.awt.BorderLayout;
import javax.swing.*;

public class ShopAuthentication {

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Welcome to Our Shop");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create buttons for Admin and Client
        JButton adminButton = new JButton("Admin");
        JButton clientButton = new JButton("Client");

        // Add action listener for Admin button
        adminButton.addActionListener(e -> showAdminLoginDialog(frame));

        // Add action listener for Client button
        clientButton.addActionListener(e -> showClientLoginDialog(frame));

        // Add buttons to a panel
        JPanel panel = new JPanel();
        panel.add(adminButton);
        panel.add(clientButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void showAdminLoginDialog(JFrame parentFrame) {
        // Admin authentication dialog
        JPasswordField passwordField = new JPasswordField();
        Object[] message = {
            "Enter Admin Password:", passwordField
        };

        int option = JOptionPane.showConfirmDialog(parentFrame, message, "Admin Login",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String enteredPassword = new String(passwordField.getPassword());

            if (enteredPassword.equals("admin123")) { // Hardcoded password for demo
                JOptionPane.showMessageDialog(parentFrame, "Access Granted!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                openAdminPanel();
            } else {
                JOptionPane.showMessageDialog(parentFrame, "Incorrect Password!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void showClientLoginDialog(JFrame parentFrame) {
        // Client name entry dialog
        JTextField nameField = new JTextField();
        Object[] message = {
            "Enter Your Name:", nameField
        };

        int option = JOptionPane.showConfirmDialog(parentFrame, message, "Client Login",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String clientName = nameField.getText().trim();

            if (!clientName.isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame, "Welcome, " + clientName + "!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                openClientPanel(clientName);
            } else {
                JOptionPane.showMessageDialog(parentFrame, "Name cannot be empty!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void openAdminPanel() {
        // Admin panel window
        JFrame adminFrame = new JFrame("Admin Panel");
        adminFrame.setSize(400, 300);
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a label and center-align it
        JLabel welcomeLabel = new JLabel("Welcome to the Admin Panel", JLabel.CENTER);

        // Set a BorderLayout and add the label to the top
        adminFrame.setLayout(new BorderLayout());
        adminFrame.add(welcomeLabel, BorderLayout.PAGE_START);

        adminFrame.setVisible(true);
    }

    private static void openClientPanel(String clientName) {
        // Launch the ClientPanel
        SwingUtilities.invokeLater(ClientPanel::new);
    }
}



