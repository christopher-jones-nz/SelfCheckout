import javax.swing.*;
import java.awt.*;

public class UserInterface {
  private SelfCheckout checkout;

  // Main Window components
  private JFrame frame;
  private JPanel cardPanel;
  private CardLayout cardLayout;

  // Dynamic Cart components
  private JPanel cartItemsContainer;
  private JLabel totalLabel;
  private JTextArea receiptText;

  // Passing the SelfCheckout instance allows the UI to call system methods
  public UserInterface(SelfCheckout checkout) {
    this.checkout = checkout;
    SwingUtilities.invokeLater(this::initializeGUI);
  }

  private void initializeGUI() {
    frame = new JFrame("Self Checkout");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 500);
    frame.setLocationRelativeTo(null);

    cardLayout = new CardLayout();
    cardPanel = new JPanel(cardLayout);

    // Build the three screens
    cardPanel.add(createWelcomeScreen(), "WELCOME");
    cardPanel.add(createMainScreen(), "MAIN");
    cardPanel.add(createReceiptScreen(), "RECEIPT");

    frame.add(cardPanel);
    frame.setVisible(true);
  }

  private JPanel createWelcomeScreen() {
    JPanel panel = new JPanel(new GridBagLayout());
    JButton startBtn = new JButton("Start");
    startBtn.setFont(new Font("Arial", Font.BOLD, 24));

    startBtn.addActionListener(e -> {
      refreshCartUI(); // Ensure cart is clean on start
      cardLayout.show(cardPanel, "MAIN");
    });

    panel.add(startBtn);
    return panel;
  }

  private JPanel createMainScreen() {
    JPanel panel = new JPanel(new BorderLayout());

    // Top: Cart container (scrollable)
    cartItemsContainer = new JPanel();
    cartItemsContainer.setLayout(new BoxLayout(cartItemsContainer, BoxLayout.Y_AXIS));
    JScrollPane scrollPane = new JScrollPane(cartItemsContainer);
    panel.add(scrollPane, BorderLayout.CENTER);

    // Bottom: Controls and Total
    JPanel bottomPanel = new JPanel(new GridLayout(4, 1, 5, 5));
    totalLabel = new JLabel("Total: $0.00", SwingConstants.CENTER);
    totalLabel.setFont(new Font("Arial", Font.BOLD, 18));

    JButton addItemBtn = new JButton("Add Item (Simulated)");
    JButton payNowBtn = new JButton("Pay Now");
    JButton cancelBtn = new JButton("Cancel Transaction");

    // Add Item Action (Simulating scanning an item for demonstration)
    addItemBtn.addActionListener(e -> {
      System.out.println("Item added clicked");
      refreshCartUI();
    });

    // Pay Now Action
    payNowBtn.addActionListener(e -> {
      generateReceipt();
      cardLayout.show(cardPanel, "RECEIPT");
    });

    // Cancel Action
    cancelBtn.addActionListener(e -> {
      int choice = JOptionPane.showConfirmDialog(
          frame,
          "Are you sure you want to cancel this transcation?",
          "Cancel Transaction",
          JOptionPane.YES_NO_OPTION);

      if (choice == JOptionPane.YES_OPTION) {
        checkout.cancelTransaction();
        cardLayout.show(cardPanel, "Welcome");
      }
    });

    bottomPanel.add(totalLabel);
    bottomPanel.add(addItemBtn);
    bottomPanel.add(payNowBtn);
    bottomPanel.add(cancelBtn);

    panel.add(bottomPanel, BorderLayout.SOUTH);
    return panel;
  }

  private JPanel createReceiptScreen() {
    JPanel panel = new JPanel(new BorderLayout());

    receiptText = new JTextArea();
    receiptText.setEditable(false);
    receiptText.setFont(new Font("Monospaced", Font.PLAIN, 14));
    panel.add(new JScrollPane(receiptText), BorderLayout.CENTER);

    JButton finishBtn = new JButton("Finish & Return");
    finishBtn.addActionListener(e -> cardLayout.show(cardPanel, "WELCOME"));
    panel.add(finishBtn, BorderLayout.SOUTH);

    return panel;
  }

  // Call this method whenever an item is added or removed
  public void refreshCartUI() {
    cartItemsContainer.removeAll();
    double total = 0.0;

    Transaction transaction = checkout.getTransaction();

    if (transaction != null && transaction.getItems() != null) {
      for (Item item : transaction.getItems()) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.add(new JLabel(item.getName() + " - $" + item.getPrice()));

        JButton removeBtn = new JButton("X");
        removeBtn.setForeground(Color.RED);
        removeBtn.addActionListener(e -> {
          transaction.removeItem(item);
          refreshCartUI(); // Reload UI after deleting the item
        });

        row.add(removeBtn);
        cartItemsContainer.add(row);
        total += item.getPrice();
      }
    }

    totalLabel.setText(String.format("Total: $%.2f", total));

    // Force the panel to visually redraw with the new items
    cartItemsContainer.revalidate();
    cartItemsContainer.repaint();
  }

  private void generateReceipt() {
    StringBuilder receipt = new StringBuilder();
    receipt.append("=== THANK YOU FOR SHOPPING ===\n\n");

    Transaction transaction = checkout.getTransaction();
    if (transaction != null && transaction.getItems() != null) {
      for (Item item : transaction.getItems()) {
        // TODO - create Item custom toString method for printing item name - $item
        // price (used here and in refresh cart UI above)
        receipt.append(item.getName()).append(" - $").append(item.getPrice()).append("\n");
      }
    }

    receipt.append("\n================================\n");
    receipt.append(totalLabel.getText());

    receiptText.setText(receipt.toString());
  }

}
