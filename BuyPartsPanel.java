import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyPartsPanel extends JPanel {

    private BikeWorkshopManagement frame;

    public BuyPartsPanel(BikeWorkshopManagement frame) {
        this.frame = frame;
        setLayout(new GridLayout(6, 2)); // Adjusting the layout to fit each part and the button

        // Set the background color to light gray
        setBackground(Color.LIGHT_GRAY);

        // Parts List and Prices
        String[] partsList = {"Brake Pads", "Tires", "Chain", "Lubricant"};
        double[] prices = {880.0, 3550.0, 4850.0, 1890.0};

        // Adding buttons for each part
        for (int i = 0; i < partsList.length; i++) {
            // Part label
            JLabel partLabel = new JLabel(partsList[i] + " - BDT " + prices[i]);
            add(partLabel);

            // Add to Cart button for each part
            JButton addButton = new JButton("Add to Cart");
            final int index = i; // Index to track the part

            // Add button functionality
            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Adding the part to the cart and updating total cost
                    Cart.getCart().add(partsList[index]);
                    Cart.setTotalCost(Cart.getTotalCost() + prices[index]);
                    JOptionPane.showMessageDialog(null, partsList[index] + " added to cart.");
                }
            });

            add(addButton);
        }

        // Back button to navigate back to the main panel
        JButton backButton = new JButton("Back to Main");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.showPanel("Main");
            }
        });

        // Adding back button to the layout
        add(backButton);
    }
}
