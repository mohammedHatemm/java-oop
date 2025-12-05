package encapsulation;

/**
 * BankAccount class demonstrating advanced encapsulation with business logic validation
 * - Complex validation rules
 * - State management (active/closed)
 * - Business constraints (minimum balance, withdrawal limits)
 */
public class BankAccount {
    // Private fields
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private String accountType;  // "SAVINGS" or "CHECKING"
    private boolean isActive;

    // Constants for business rules
    private static final double MIN_BALANCE = 0.0;
    private static final double SAVINGS_MIN_BALANCE = 100.0;
    private static final double MAX_WITHDRAWAL = 5000.0;
    private static final double MAX_SINGLE_DEPOSIT = 100000.0;

    // Constructor
    public BankAccount(String accountNumber, String accountHolder, String accountType) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.balance = 0.0;
        this.isActive = true;
    }

    // ==================== GETTERS ====================

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public boolean isActive() {
        return isActive;
    }

    // ==================== BUSINESS METHODS (with validation) ====================

    /**
     * Deposit money into the account
     * Validates: account active, amount positive, amount within limits
     */
    public void deposit(double amount) {
        if (!isActive) {
            throw new IllegalStateException("Account is not active");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        if (amount > MAX_SINGLE_DEPOSIT) {
            throw new IllegalArgumentException(
                "Single deposit cannot exceed $" + MAX_SINGLE_DEPOSIT
            );
        }

        balance += amount;
        System.out.println("Deposited: $" + String.format("%.2f", amount));
        System.out.println("New balance: $" + String.format("%.2f", balance));
    }

    /**
     * Withdraw money from the account
     * Validates: account active, amount positive, sufficient balance,
     *            withdrawal limit, minimum balance requirement
     */
    public void withdraw(double amount) {
        // Validation 1: Account active
        if (!isActive) {
            throw new IllegalStateException("Account is not active");
        }

        // Validation 2: Amount positive
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        // Validation 3: Maximum withdrawal limit
        if (amount > MAX_WITHDRAWAL) {
            throw new IllegalArgumentException(
                "Single withdrawal cannot exceed $" + MAX_WITHDRAWAL
            );
        }

        // Validation 4: Sufficient balance
        if (amount > balance) {
            throw new IllegalArgumentException(
                String.format("Insufficient balance. Available: $%.2f, Requested: $%.2f",
                              balance, amount)
            );
        }

        // Validation 5: Minimum balance requirement
        double minBalance = accountType.equals("SAVINGS") ? SAVINGS_MIN_BALANCE : MIN_BALANCE;
        if (balance - amount < minBalance) {
            throw new IllegalArgumentException(
                String.format("Cannot withdraw. Minimum balance requirement: $%.2f", minBalance)
            );
        }

        balance -= amount;
        System.out.println("Withdrawn: $" + String.format("%.2f", amount));
        System.out.println("New balance: $" + String.format("%.2f", balance));
    }

    /**
     * Transfer money to another account
     */
    public void transfer(BankAccount targetAccount, double amount) {
        System.out.println("\n--- Initiating Transfer ---");

        // Withdraw from this account (validates everything)
        withdraw(amount);

        // Deposit to target account (validates everything)
        targetAccount.deposit(amount);

        System.out.println("Transferred $" + String.format("%.2f", amount) +
                           " to account: " + targetAccount.getAccountNumber());
    }

    /**
     * Close the account (only if balance is zero)
     */
    public void closeAccount() {
        if (balance > 0) {
            throw new IllegalStateException(
                String.format("Cannot close account with positive balance ($%.2f). " +
                              "Please withdraw all funds first.", balance)
            );
        }
        isActive = false;
        System.out.println("Account " + accountNumber + " closed successfully");
    }

    /**
     * Display complete account information
     */
    public void displayAccountInfo() {
        System.out.println("\n=== Account Information ===");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Type: " + accountType);
        System.out.println("Balance: $" + String.format("%.2f", balance));
        System.out.println("Status: " + (isActive ? "Active" : "Closed"));
        System.out.println("==========================");
    }

    @Override
    public String toString() {
        return String.format("BankAccount{number='%s', holder='%s', balance=%.2f, type='%s', active=%b}",
                             accountNumber, accountHolder, balance, accountType, isActive);
    }
}
