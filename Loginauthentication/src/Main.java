import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        while (true) {
            System.out.println("\n===== LOGIN AUTHENTICATION SYSTEM =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();

                System.out.print("Enter email: ");
                String email = scanner.nextLine();

                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                boolean isRegistered = userDAO.registerUser(username, email, password);

                if (isRegistered) {
                    System.out.println("Registration Successful!");
                } else {
                    System.out.println("Registration Failed! Email may already exist.");
                }

            } else if (choice == 2) {
                System.out.print("Enter email: ");
                String email = scanner.nextLine();

                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                String username = userDAO.loginUser(email, password);

                if (username != null) {
                    System.out.println("Login Successful!");
                    System.out.println("Welcome, " + username + "!");
                } else {
                    System.out.println("Invalid email or password.");
                }

            } else if (choice == 3) {
                System.out.println("Thank you! Exiting...");
                break;

            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}