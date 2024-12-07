// ClientPanel Class
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class ClientPanel extends JFrame {
  private ArrayList<Game> games = new ArrayList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> gameList;
    private JTextArea descriptionArea;
    private JTextField searchField;
    private Cart cart;
    private JLabel imageLabel;

    public ClientPanel() {
        cart = new Cart(); // Initialize the Cart

        setTitle("Client Panel");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null); // Center the window
        setBackground(new Color(240, 240, 240));

        // Load games into the ArrayList
        loadGames();

        // Create UI components
        createTopPanel();
        createCenterPanel();
        createBottomPanel();

        // Populate the game list
        setupGameList();

        setVisible(true);
    }

    private void createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 14));
        searchField = new JTextField();
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchButton.addActionListener(e -> searchGames());

        topPanel.add(searchLabel, BorderLayout.WEST);
        topPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(searchButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
    }

    private void createCenterPanel() {
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Game List Panel
        JPanel listPanel = new JPanel(new BorderLayout(10, 10));
        listPanel.setBorder(BorderFactory.createTitledBorder("Available Games"));
        gameList = new JList<>(listModel);
        gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gameList.addListSelectionListener(e -> updateDescription());
        JScrollPane listScrollPane = new JScrollPane(gameList);
        listPanel.add(listScrollPane, BorderLayout.CENTER);

        // Description Panel
        JPanel descriptionPanel = new JPanel(new BorderLayout(10, 10));
        descriptionPanel.setBorder(BorderFactory.createTitledBorder("Game Details"));

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        descriptionPanel.add(imageLabel, BorderLayout.NORTH);

        descriptionArea = new JTextArea();
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        descriptionPanel.add(descriptionScrollPane, BorderLayout.CENTER);

        centerPanel.add(listPanel);
        centerPanel.add(descriptionPanel);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void createBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setFont(new Font("Arial", Font.BOLD, 14));
        addToCartButton.addActionListener(e -> addToCart());

        JButton viewCartButton = new JButton("View Cart");
        viewCartButton.setFont(new Font("Arial", Font.BOLD, 14));
        viewCartButton.addActionListener(e -> openCartPanel());

        bottomPanel.add(addToCartButton);
        bottomPanel.add(viewCartButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

   private void loadGames() {
    games.add(new Game(
        "The Legend of Zelda: Breath of the Wild",
        "An open-world action-adventure game where you explore the vast kingdom of Hyrule, solve puzzles, and battle enemies.",
        59.99,
        "images/zelda.jpg"
    ));
    games.add(new Game(
        "The Witcher 3: Wild Hunt",
        "A story-driven RPG set in a visually stunning fantasy universe filled with meaningful choices and impactful consequences.",
        49.99,
        "images/witcher3.jpg"
    ));
    games.add(new Game(
        "Minecraft",
        "A sandbox game where you can build, explore, and survive in a blocky, procedurally-generated 3D world.",
        29.99,
        "images/minecraft.jpg"
    ));
    games.add(new Game(
        "Red Dead Redemption 2",
        "An epic tale of life in America’s unforgiving heartland with a huge, richly detailed open world.",
        69.99,
        "images/rdr2.jpg"
    ));
    games.add(new Game(
        "Cyberpunk 2077",
        "An open-world action-adventure story set in Night City, a megalopolis obsessed with power, glamour, and body modification.",
        49.99,
        "images/cyberpunk2077.jpg"
    ));
    games.add(new Game(
        "God of War",
        "An action-adventure game that follows Kratos and his son Atreus on an epic journey through Norse mythology.",
        39.99,
        "images/godofwar.jpg"
    ));
    games.add(new Game(
        "Grand Theft Auto V",
        "An open-world action game with a gripping story, multiple characters, and countless activities to explore.",
        29.99,
        "images/gta5.jpg"
    ));
    games.add(new Game(
        "Hollow Knight",
        "A challenging action-adventure game set in a vast interconnected world full of insects and mystery.",
        14.99,
        "images/hollowknight.jpg"
    ));
    games.add(new Game(
        "Dark Souls III",
        "An action RPG known for its dark fantasy setting, intricate world design, and punishing difficulty.",
        39.99,
        "images/darksouls3.jpg"
    ));
    games.add(new Game(
        "Among Us",
        "A multiplayer social deduction game where players work together to complete tasks, but beware of impostors!",
        4.99,
        "images/amongus.jpg"
    ));
}


    private void setupGameList() {
        listModel.clear();
        for (Game game : games) {
            listModel.addElement(game.getName() + " - $" + game.getPrice());
        }
    }


private void updateDescription() {
    int selectedIndex = gameList.getSelectedIndex();
    if (selectedIndex >= 0) {
        Game selectedGame = games.get(selectedIndex);

        // Update description text
        descriptionArea.setText(selectedGame.getDescription());

        // Update the image
        ImageIcon gameImage = new ImageIcon(selectedGame.getImagePath());
        Image scaledImage = gameImage.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
    }
}


    private void searchGames() {
        String searchText = searchField.getText().toLowerCase();
        listModel.clear();

        for (Game game : games) {
            if (game.getName().toLowerCase().contains(searchText)) {
                listModel.addElement(game.getName() + " - $" + game.getPrice());
            }
        }

        if (listModel.isEmpty()) {
            listModel.addElement("No games found.");
        }
    }

    private void addToCart() {
        int selectedIndex = gameList.getSelectedIndex();
        if (selectedIndex >= 0) {
            Game selectedGame = games.get(selectedIndex);
            cart.addGame(selectedGame); // Add game to the cart
            JOptionPane.showMessageDialog(this,
                    selectedGame.getName() + " has been added to your cart.",
                    "Game Added", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please select a game first.",
                    "No Game Selected", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void openCartPanel() {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your cart is empty.", "Cart", JOptionPane.WARNING_MESSAGE);
        } else {
            new CartPanel(cart);
        }
    }
}
