package ATMInterfaceGUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;

public class ATM extends JFrame {
    private Account account;
    private JLabel balanceLabel;
    private JTextField amountField;
    private JButton withdrawButton, depositButton, checkBalanceButton, exitButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATM().setVisible(true));
    }

    public ATM() {
        setupFrame();
        initializeComponents();
        layoutComponents();
        setupEventHandlers();
        
        account = new Account(new BigDecimal("1000"));
        updateBalanceLabel();
    }

    private void setupFrame() {
        setTitle("ATM Machine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        balanceLabel = new JLabel("Balance: 0.00");
        amountField = new JTextField(10);
        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");
        checkBalanceButton = new JButton("Check Balance");
        exitButton = new JButton("Exit");
    }

    private void layoutComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("ATM"), gbc);

        gbc.gridy++;
        panel.add(amountField, gbc);

        gbc.gridy++;
        panel.add(withdrawButton, gbc);

        gbc.gridy++;
        panel.add(depositButton, gbc);

        gbc.gridy++;
        panel.add(checkBalanceButton, gbc);

        gbc.gridy++;
        panel.add(exitButton, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(balanceLabel, gbc);

        add(panel);
    }

    private void setupEventHandlers() {
        withdrawButton.addActionListener(this::handleWithdraw);
        depositButton.addActionListener(this::handleDeposit);
        checkBalanceButton.addActionListener(this::handleCheckBalance);
        exitButton.addActionListener(e -> System.exit(0));
    }

    private void handleWithdraw(ActionEvent e) {
        try {
            BigDecimal amount = new BigDecimal(amountField.getText());
            account.withdraw(amount);
            updateBalanceLabel();
            showMessage("Withdrawal successful.");
        } catch (NumberFormatException ex) {
            showMessage("Invalid amount. Please enter a numeric value.");
        } catch (IllegalArgumentException ex) {
            showMessage("Invalid amount. Please enter a positive number.");
        } catch (InsufficientFundsException ex) {
            showMessage("Insufficient funds.");
        } finally {
            clearAmountField(); // Clear the input field
        }
    }

    private void handleDeposit(ActionEvent e) {
        try {
            BigDecimal amount = new BigDecimal(amountField.getText());
            account.deposit(amount);
            updateBalanceLabel();
            showMessage("Deposit successful.");
        } catch (NumberFormatException ex) {
            showMessage("Invalid amount. Please enter a numeric value.");
        } catch (IllegalArgumentException ex) {
            showMessage("Invalid amount. Please enter a positive number.");
        } finally {
            clearAmountField(); // Clear the input field
        }
    }

    private void handleCheckBalance(ActionEvent e) {
        updateBalanceLabel();
        showMessage("Your current balance is: " + account.getBalance());
        clearAmountField(); // Clear the input field
    }

    private void updateBalanceLabel() {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
        balanceLabel.setText("Balance: " + currencyFormatter.format(account.getBalance()));
    }

    private void clearAmountField() {
        amountField.setText(""); // Clears the input field
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
