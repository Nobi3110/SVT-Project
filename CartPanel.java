import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CartPanel extends JPanel {

    private BikeWorkshopManagement frame;
    private JTextArea cartTextArea;
    private JTextField discountField;
    private double discount = 0;

    public CartPanel(BikeWorkshopManagement frame) {
        this.frame = frame;
        setLayout(new BorderLayout());

        // Text area to show cart details
        cartTextArea = new JTextArea();
        cartTextArea.setEditable(false);
        cartTextArea.setBackground(Color.LIGHT_GRAY); // Default background color for text area
        cartTextArea.setForeground(Color.BLACK); // Set text color to white
        add(new JScrollPane(cartTextArea), BorderLayout.CENTER);

        // Panel for discount input
        JPanel discountPanel = new JPanel();
        discountPanel.setLayout(new FlowLayout());

        JLabel discountLabel = new JLabel("Enter Discount (%)");
        discountField = new JTextField(5);
        JButton applyDiscountButton = new JButton("Apply Discount");

        // Apply discount action
        applyDiscountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double discountPercentage = Double.parseDouble(discountField.getText());
                    if (discountPercentage < 0 || discountPercentage > 100) {
                        JOptionPane.showMessageDialog(null, "Invalid discount percentage. Please enter a value between 0 and 100.");
                    } else {
                        discount = Cart.getTotalCost() * (discountPercentage / 100);
                        double discountedTotal = Cart.getTotalCost() - discount;

                        // Update the cart text area with items and discounted price
                        cartTextArea.setText("Cart Contents:\n");
                        for (String item : Cart.getCart()) {
                            cartTextArea.append(item + "\n");
                        }
                        cartTextArea.append("\nOriginal Total: BDT " + Cart.getTotalCost() + "\n");
                        cartTextArea.append("Discount Applied: -BDT " + discount + "\n");
                        cartTextArea.append("Total after Discount: BDT " + discountedTotal);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for discount.");
                }
            }
        });

        // Adding discount components to the panel
        discountPanel.add(discountLabel);
        discountPanel.add(discountField);
        discountPanel.add(applyDiscountButton);

        // Adding discount panel to the layout
        add(discountPanel, BorderLayout.NORTH);

        // Checkout button
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Clear the cart and reset the total
                Cart.getCart().clear();
                Cart.setTotalCost(0);

                // Reset the cartTextArea and discount field
                cartTextArea.setText("Cart is empty.\n");
                cartTextArea.setBackground(Color.LIGHT_GRAY); // Set background to red when cart is empty
                discountField.setText("");
                discount = 0;

                // Navigate back to the main menu
                frame.showPanel("Main");
            }
        });

        add(checkoutButton, BorderLayout.SOUTH);

        // Back button to return to Main Menu
        JButton backButton = new JButton("Back to Main");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.showPanel("Main");
            }
        });

        add(backButton, BorderLayout.WEST);
    }
}
