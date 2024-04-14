package com.example.repo6;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Repo6 {

    // Class to represent a bank account
    class Account {
        private String accountId;
        private String accountHolderName;
        private double balance;

        public Account(String accountId, String accountHolderName, double balance) {
            this.accountId = accountId;
            this.accountHolderName = accountHolderName;
            this.balance = balance;
        }

        public String getAccountId() {
            return accountId;
        }

        public String getAccountHolderName() {
            return accountHolderName;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            balance += amount;
        }

        public void withdraw(double amount) {
            if (balance >= amount) {
                balance -= amount;
            } else {
                System.out.println("Insufficient funds.");
            }
        }
    }

    // Class to manage user authentication
    static class AuthenticationManager {
        private Map<String, String> credentials;

        public AuthenticationManager() {
            credentials = new HashMap<>();
            credentials.put("user1", "password1");
            credentials.put("user2", "password2");
            // Add more users as needed
        }

        public boolean authenticate(String username, String password) {
            String storedPassword = credentials.get(username);
            return storedPassword != null && storedPassword.equals(password);
        }
    }

    // Main class representing the bank application
    public class BankApplication {
        private static Map<String, Account> accounts = new HashMap<>();
        private static AuthenticationManager authManager = new AuthenticationManager();

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Welcome to the Bank!");

            while (true) {
                System.out.println("\n1. Login");
                System.out.println("2. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                if (choice == 1) {
                    login(scanner);
                } else if (choice == 2) {
                    System.out.println("Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }

            scanner.close();
        }

        private static void login(Scanner scanner) {
            System.out.print("Enter username: ");
            String username = scanner.next();
            System.out.print("Enter password: ");
            String password = scanner.next();

            if (authManager.authenticate(username, password)) {
                System.out.println("Login successful!");

                while (true) {
                    System.out.println("\n1. Deposit");
                    System.out.println("2. Withdraw");
                    System.out.println("3. Check Balance");
                    System.out.println("4. Logout");
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();

                    if (choice == 1) {
                        deposit(scanner, username);
                    } else if (choice == 2) {
                        withdraw(scanner, username);
                    } else if (choice == 3) {
                        checkBalance(username);
                    } else if (choice == 4) {
                        System.out.println("Logged out.");
                        break;
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }

        private static void deposit(Scanner scanner, String username) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();

            Account account = accounts.get(username);
            account.deposit(amount);
            System.out.println("Deposit successful. New balance: $" + account.getBalance());
        }

        private static void withdraw(Scanner scanner, String username) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();

            Account account = accounts.get(username);
            account.withdraw(amount);
            System.out.println("Withdrawal successful. New balance: $" + account.getBalance());
        }

        private static void checkBalance(String username) {
            Account account = accounts.get(username);
            System.out.println("Account balance: $" + account.getBalance());
        }
    }

}
