package CurrencyConverter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.json.JSONObject;

public class CurrencyConverter {
    private static final String API_KEY = "6cb39ae0f3b74ac19da056bb";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public static void main(String[] args) {
        JFrame frame = new JFrame("Currency Converter");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel baseLabel = new JLabel("Base Currency:");
        baseLabel.setBounds(50, 50, 100, 30);
        frame.add(baseLabel);

        JComboBox<String> baseCurrency = new JComboBox<>(new String[] {
            "USD", "EUR", "INR", "JPY", "AUD", "CAD", "PHP", "MYR", "GBP", "CHF", "CNY", "NZD", "SGD", "HKD", "ZAR"
        });
        baseCurrency.setBounds(150, 50, 100, 30);
        frame.add(baseCurrency);

        JLabel targetLabel = new JLabel("Target Currency:");
        targetLabel.setBounds(50, 100, 100, 30);
        frame.add(targetLabel);

        JComboBox<String> targetCurrency = new JComboBox<>(new String[] {
            "USD", "EUR", "INR", "JPY", "AUD", "CAD", "PHP", "MYR", "GBP", "CHF", "CNY", "NZD", "SGD", "HKD", "ZAR"
        });
        targetCurrency.setBounds(150, 100, 100, 30);
        frame.add(targetCurrency);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(50, 150, 100, 30);
        frame.add(amountLabel);

        JTextField amountField = new JTextField();
        amountField.setBounds(150, 150, 100, 30);
        frame.add(amountField);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(150, 200, 100, 30);
        frame.add(convertButton);

        JLabel resultLabel = new JLabel("Converted Amount:");
        resultLabel.setBounds(50, 250, 300, 30);
        frame.add(resultLabel);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String base = (String) baseCurrency.getSelectedItem();
                String target = (String) targetCurrency.getSelectedItem();
                double amount = Double.parseDouble(amountField.getText());
                double convertedAmount = convertCurrency(base, target, amount);
                resultLabel.setText("Converted Amount: " + convertedAmount + " " + target);
            }
        });

        frame.setVisible(true);
    }

    private static double convertCurrency(String base, String target, double amount) {
        try {
            URL url = new URL(API_URL + base);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();

            JSONObject json = new JSONObject(content.toString());
            double exchangeRate = json.getJSONObject("conversion_rates").getDouble(target);
            return amount * exchangeRate;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
