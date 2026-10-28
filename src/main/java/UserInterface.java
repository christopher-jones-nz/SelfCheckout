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

}
