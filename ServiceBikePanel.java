import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServiceBikePanel extends JPanel {

    private BikeWorkshopManagement frame;

    public ServiceBikePanel(BikeWorkshopManagement frame) {
        this.frame = frame;
        setLayout(new GridLayout(6, 2)); // Adjusting layout to properly fit services and buttons

        // Set the background color to light blue
        setBackground(Color.LIGHT_GRAY);

        // Service List and Prices
        String[] servicesList = {"Wheel Alignment", "Master Service", "Oil Change", "Normal Service"};
        double[] prices = {250.0, 3500.0, 100.0, 1750.0};

        // Adding buttons for each service
        for (int i = 0; i < servicesList.length; i++) {
            // Service label
            JLabel serviceLabel = new JLabel(servicesList[i] + " - BDT " + prices[i]);
            add(serviceLabel);

            // Add to Cart button for each service
            JButton addButton = new JButton("Add to Cart");
            final int index = i; // Index to track the service

            // Add button functionality
            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Adding the service to the cart and updating total cost
                    Cart.getCart().add(servicesList[index]);
                    Cart.setTotalCost(Cart.getTotalCost() + prices[index]);
                    JOptionPane.showMessageDialog(null, servicesList[index] + " added to cart.");
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
