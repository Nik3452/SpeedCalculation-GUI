import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.math.*;
import java.net.URL;
import java.util.regex.*;

public class SpeedGUI extends JFrame {
    private static String mphResult;
    private static String kmhResult;
    private static double mph, kmh;
    private static JFrame mainFrame;
    private static JCheckBox checkBox;
    private static JLabel statusLabel, resultLabel;
    private static Container contentPane;
    private static SpringLayout layout;

    public SpeedGUI(){
        prepareGUI();
     }
// Start of the Main Method
    public static void main(String[] args) {
        SpeedGUI speedCalculation = new SpeedGUI();
        speedCalculation.speedCalculationMain();
    }
    private void prepareGUI() {

        // Title for the program
        mainFrame = new JFrame("Speed Calculation");
        // Window listener for the program to shutdown when window closes

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
               System.exit(0);
            }        
         });   
        // Intializing the GUI variables with Sizes and adding them to the main Frame    
        statusLabel = new JLabel("");    
        statusLabel.setSize(350,100);
        resultLabel = new JLabel("");    
        resultLabel.setSize(350,100);
        mainFrame.add(statusLabel);
        mainFrame.add(resultLabel);
        URL iconURL = getClass().getResource("res/icon2.png");
        // iconURL is null when not found
        ImageIcon icon = new ImageIcon(iconURL);
        mainFrame.setIconImage(icon.getImage());
        // Create the content pane and set the layout
        contentPane = mainFrame.getContentPane();
        layout = new SpringLayout();
        contentPane.setLayout(layout);

    }
    void speedCalculationMain() {
        // Create some components
        JLabel distanceLabel = new JLabel("Distance");
        distanceLabel.setFont(new Font("Tahoma", Font.BOLD, 14));// Tahoma is an example, you could use any forn you want.
        JLabel distanceLabelInput = new JLabel("Distance in Metres:");
        JLabel timeLabel = new JLabel("Time Taken");
        timeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));// Tahoma is an example, you could use any forn you want.
        JLabel hoursLabel = new JLabel("Hours:");
        JLabel minutesLabel = new JLabel("Minutes:");
        JLabel secondsLabel = new JLabel("Seconds:");
        // Creating the Text Fields
        JTextField distanceTextField = new JTextField(1);
        JTextField hoursTextField = new JTextField(1);
        JTextField minutesTextField = new JTextField(1);
        JTextField secondsTextField = new JTextField(1);
        JButton calculateButton = new JButton("Calculate");
        checkBox = new JCheckBox("Simple Mode", false);

        // Add the components to the content pane
        contentPane.add(distanceLabel);
        contentPane.add(distanceLabelInput);
        contentPane.add(distanceTextField);

        contentPane.add(timeLabel);
        contentPane.add(hoursLabel);
        contentPane.add(hoursTextField);
        contentPane.add(minutesLabel);
        contentPane.add(minutesTextField);
        contentPane.add(secondsLabel);
        contentPane.add(secondsTextField);

        contentPane.add(calculateButton);
        contentPane.add(checkBox);

        // Define the constraints for each component
        // Defining the Distance Section
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, distanceLabel, 5, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, distanceLabel, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST, distanceLabelInput, 5, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, distanceLabelInput, 20, SpringLayout.SOUTH, distanceLabel);
        layout.putConstraint(SpringLayout.WEST, distanceTextField, 5, SpringLayout.EAST, distanceLabelInput);
        layout.putConstraint(SpringLayout.NORTH, distanceTextField, 0, SpringLayout.NORTH, distanceLabelInput);
        layout.putConstraint(SpringLayout.EAST, distanceTextField, -5, SpringLayout.EAST, contentPane);

        // Defining the Time Header
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, timeLabel, 5, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, timeLabel, 20, SpringLayout.SOUTH, distanceLabelInput);

        // Defining Hours Label and Text Field
        layout.putConstraint(SpringLayout.WEST, hoursLabel, 5, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, hoursLabel, 20, SpringLayout.SOUTH, timeLabel);
        layout.putConstraint(SpringLayout.WEST, hoursTextField, 5, SpringLayout.EAST, hoursLabel);
        layout.putConstraint(SpringLayout.NORTH, hoursTextField, 20, SpringLayout.SOUTH, timeLabel);
        layout.putConstraint(SpringLayout.EAST, hoursTextField, -5, SpringLayout.EAST, contentPane);

        // Defining Minutes Label and Text Field
        layout.putConstraint(SpringLayout.WEST, minutesLabel, 5, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, minutesLabel, 10, SpringLayout.SOUTH, hoursLabel);
        layout.putConstraint(SpringLayout.WEST, minutesTextField, 5, SpringLayout.EAST, minutesLabel);
        layout.putConstraint(SpringLayout.NORTH, minutesTextField, 5, SpringLayout.SOUTH, hoursTextField);
        layout.putConstraint(SpringLayout.EAST, minutesTextField, -5, SpringLayout.EAST, contentPane);

        // Defining Seconds Label and Text Field
        layout.putConstraint(SpringLayout.WEST, secondsLabel, 5, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, secondsLabel, 10, SpringLayout.SOUTH, minutesLabel);
        layout.putConstraint(SpringLayout.WEST, secondsTextField, 5, SpringLayout.EAST, secondsLabel);
        layout.putConstraint(SpringLayout.NORTH, secondsTextField, 5, SpringLayout.SOUTH, minutesTextField);
        layout.putConstraint(SpringLayout.EAST, secondsTextField, -5, SpringLayout.EAST, contentPane);

        // Defining Button
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, calculateButton, 10, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, calculateButton, 10, SpringLayout.SOUTH, secondsTextField);

        // Defining Checkbox
        layout.putConstraint(SpringLayout.EAST, checkBox, 0, SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, checkBox, 10, SpringLayout.SOUTH, secondsTextField);  

        // Defining Status or input from the user
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, statusLabel, 5, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, statusLabel, 5, SpringLayout.SOUTH, calculateButton);

        // Defining Results sections
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, resultLabel, 5, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, resultLabel, 5, SpringLayout.SOUTH, statusLabel);

        // Set the size and make the frame visible
        mainFrame.setSize(500, 500);
        mainFrame.setVisible(true);

        // Listener for the button to be pressed
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {   
                // Regular Expression with only numbers accepted
                String regex = "^[0-9]*$";

                // Defining the variables needed
                String distanceText = distanceTextField.getText();
                String hoursText = hoursTextField.getText();
                String minutesText = minutesTextField.getText();
                String secondsText = secondsTextField.getText();

                // Pattern to match with the variables above
                Pattern pattern = Pattern.compile(regex);

                // Utilizing the Matcher package to create a validation check
                Matcher distanceMatcher = pattern.matcher(distanceText);
                Matcher hoursMatcher = pattern.matcher(hoursText);
                Matcher minutesMatcher = pattern.matcher(minutesText);
                Matcher secondsMatcher = pattern.matcher(secondsText);

                // Using the variable above, we create a validation check for numbers only
                if (distanceTextField.getText().isEmpty() || hoursTextField.getText().isEmpty() || minutesTextField.getText().isEmpty() || secondsTextField.getText().isEmpty()) {
                    // If the fields contain a non number data, the program will not continue and instead post a error message with a pop up
                    JOptionPane.showMessageDialog(mainFrame, "Please ensure compliance!\nOne or more of the fields is empty",
                    "Error", JOptionPane.ERROR_MESSAGE);
                } else if (distanceMatcher.matches() && hoursMatcher.matches() && minutesMatcher.matches() && secondsMatcher.matches()) {
                    String data = "<html>Your Input:<br/><br/>Distance: " + distanceTextField.getText();
                    data += "<br/>Hours: " + hoursTextField.getText();
                    data += "<br/>Minutes: " + minutesTextField.getText();
                    data += "<br/>Seconds: " + secondsTextField.getText();
                    statusLabel.setText(data);
                    
                    // Calling the speedCalculation, speedConversionMph, speedConversionKmh Methods with arguements to pass through
                    String speedResult = calcuation(distanceTextField, hoursTextField, minutesTextField, secondsTextField);
                    mphResult = speedConversionMph(speedResult);
                    kmhResult = speedConversionKmh(speedResult);

                    // Setting the result label's text when we caluclated everything inputed by the user
                    String result = "<html>Your Output:<br/><br/>MPH: " + mphResult + "<br/>KMH: " + kmhResult + "</html>";
                    resultLabel.setText(result);
                } else {
                    // If the fields contain a non number data, the program will not continue and instead post a error message with a pop up
                    JOptionPane.showMessageDialog(mainFrame, "Please ensure compliance!\nOnly Integer Values are accepted!",
                    "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
         });
         checkBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(checkBox.isSelected() && mph > 0) {
                    BigDecimal bd2, bd1;
                    bd2 = new BigDecimal(Double.toString(mph));
                    bd1 = new BigDecimal(Double.toString(kmh));
                    bd2 = bd2.setScale(2, RoundingMode.HALF_UP);
                    bd1 = bd1.setScale(2, RoundingMode.HALF_UP);
                    String mphString = bd2.toString();
                    String kmhString = bd1.toString();
                    resultLabel.setText("<html>Your Output:<br/><br/>MPH: " + mphString + "<br/>KMH: " + kmhString + "</html>");
                } else if (checkBox.isSelected() == false && mph > 0) {
                    BigDecimal bd2, bd1;
                    bd2 = new BigDecimal(Double.toString(mph));
                    bd1 = new BigDecimal(Double.toString(kmh));
                    bd2 = bd2.setScale(5, RoundingMode.HALF_UP);
                    bd1 = bd1.setScale(5, RoundingMode.HALF_UP);
                    String mphString = bd2.toString();
                    String kmhString = bd1.toString();
                    resultLabel.setText("<html>Your Output:<br/><br/>MPH: " + mphString + "<br/>KMH: " + kmhString + "</html>");
                }
            }});
    }
    // Speed calculation Method that will calculate Speed
    public static String calcuation(JTextField distanceTextField, JTextField hoursTextField, JTextField minutesTextField, JTextField secondsTextField) {
        // Parsing the variable from string to double for better accuracy
        double d = Double.parseDouble(distanceTextField.getText());
        double h = Double.parseDouble(hoursTextField.getText());
        double m = Double.parseDouble(minutesTextField.getText());
        double s = Double.parseDouble(secondsTextField.getText());
        // Declaring Time and Speed variables
        double time;
        double speed;
        // Intialzing the variables and completing some calculations
        time = h * 3600 + m * 60 + s;
        speed = d / time;
        // Converting the double to string for return purposes
        String speedString = Double.toString(speed);
        return speedString;
    }
    // Given the speedCalculation variable we will then convert the speed to KMH
    public static String speedConversionKmh(String speed) {
        // Parsing the variable to double from string
        double s = Double.parseDouble(speed);
        // Calculating Kilometres per Hour (speed * 3.6)
        kmh = s * 3.6;
        // Rounding the KMH variable to 5 decimal places
        BigDecimal bd = new BigDecimal(Double.toString(kmh));

        if(checkBox.isSelected()) {
            bd = bd.setScale(2, RoundingMode.HALF_UP);
        } else {
            bd = bd.setScale(5, RoundingMode.HALF_UP);
        }
        // Converting the BD variable to string and returing the value
        String kmhString = bd.toString();
        return kmhString;
    }
    // Given the speedCalculation variable we will then convert the speed to MPH
    public static String speedConversionMph(String speed) {
        // Parsing the variable to double from string
        double s = Double.parseDouble(speed);
        // Calculating Miles per Hour (speed * 2.23694)
        mph = s * 2.23694;
        // Rounding the MPH variable to 5 decimal places
        BigDecimal bd = new BigDecimal(Double.toString(mph));

        if(checkBox.isSelected()) {
            bd = bd.setScale(2, RoundingMode.HALF_UP);
        } else {
            bd = bd.setScale(5, RoundingMode.HALF_UP);
        }
        // Converting the BD variable to string and returing the value
        String mphString = bd.toString();
        return mphString;
    }
}