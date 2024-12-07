import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;


public class ShopAuthentication {

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Welcome to Our Shop");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Welcome to Our Shop", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        frame.add(titleLabel, BorderLayout.NORTH);
        
        // Create buttons for Admin and Client
       JButton adminButton = new JButton("Admin");
        adminButton.setFont(new Font("Arial", Font.PLAIN, 16));
        adminButton.setIcon(new ImageIcon("admin_icon.png")); // Replace with your icon path
        adminButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        adminButton.setHorizontalTextPosition(SwingConstants.CENTER);

         JButton clientButton = new JButton("Client");
        clientButton.setFont(new Font("Arial", Font.PLAIN, 16));
        clientButton.setIcon(new ImageIcon("client_icon.png")); // Replace with your icon path
        clientButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        clientButton.setHorizontalTextPosition(SwingConstants.CENTER);
        

       adminButton.addActionListener(e -> showAdminLoginDialog(frame));
        clientButton.addActionListener(e -> showClientLoginDialog(frame));

        // Add buttons to a panel
          JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        buttonPanel.add(adminButton);
        buttonPanel.add(clientButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        // Add footer
        JLabel footerLabel = new JLabel("© 2024 ShopApp Inc.", JLabel.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        footerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        frame.add(footerLabel, BorderLayout.SOUTH);

        // Set frame to be visible
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
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));

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



