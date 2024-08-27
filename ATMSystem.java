import java.util.Scanner;

public class ATMSystem {

    // BankAccount Class
    public static class BankAccount {
        private double balance;

        public BankAccount(double initialBalance) {
            this.balance = initialBalance;
        }

        public boolean deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                return true;
            }
            return false;
        }

        public boolean withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                return true;
            }
            return false;
        }

        public double getBalance() {
            return balance;
        }
    }

    // ATM Class
    public static class ATM {
        private BankAccount bankAccount;

        public ATM(BankAccount bankAccount) {
            this.bankAccount = bankAccount;
        }

        public String withdraw(double amount) {
            if (amount <= 0) {
                return "Invalid amount. Must be greater than zero.";
            }
            if (bankAccount.withdraw(amount)) {
                return String.format("Withdrawal successful! You withdrew %.2f. Current balance: %.2f.",
                                     amount, bankAccount.getBalance());
            }
            return "Insufficient funds or invalid amount.";
        }

        public String deposit(double amount) {
            if (amount <= 0) {
                return "Invalid amount. Must be greater than zero.";
            }
            if (bankAccount.deposit(amount)) {
                return String.format("Deposit successful! You deposited %.2f. Current balance: %.2f.",
                                     amount, bankAccount.getBalance());
            }
            return "Deposit failed. Please try again.";
        }

        public String checkBalance() {
            return String.format("Current balance: %.2f.", bankAccount.getBalance());
        }
    }

    // Main Method - ATM Interface
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = new BankAccount(1000); // Initialize with an initial balance
        ATM atm = new ATM(account);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");

            System.out.print("Enter your choice (1/2/3/4): ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println(atm.checkBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    System.out.println(atm.deposit(depositAmount));
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    System.out.println(atm.withdraw(withdrawAmount));
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return; // Exit the loop and end the program
                default:
                    System.out.println("Invalid option. Please select a number between 1 and 4.");
            }
        }
    }
}
