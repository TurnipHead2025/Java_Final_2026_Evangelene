package org.keyin;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.keyin.gymproduct.GymProduct;
import org.keyin.gymproduct.GymProductService;
import org.keyin.memberships.Membership;
import org.keyin.memberships.MembershipService;
import org.keyin.user.User;
import org.keyin.user.UserService;
import org.keyin.workoutclasses.WorkoutClass;
import org.keyin.workoutclasses.WorkoutClassService;


public class GymApp {
    private static final Logger logger = Logger.getLogger(GymApp.class.getName());

    public static void main(String[] args) throws SQLException {
        //logger
        try{
            FileHandler fileHandler = new FileHandler("app.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        }catch (IOException e){
            System.out.println("Logger setup failed:" + e.getMessage());
            logger.warning("Database error: " + e.getMessage());
        } 
        logger.info("Gym Management System started.");       
        
        // Initialize services
        UserService userService = new UserService();
        MembershipService membershipService = new MembershipService();
        WorkoutClassService workoutService = new WorkoutClassService();
        GymProductService gymProductService = new GymProductService ();

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Gym Management System ===");
            System.out.println("1. Add a new user");
            System.out.println("2. Login as a user");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            // Validate input
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNewUser(scanner, userService);
                break;
                case 2:
                    logInAsUser(scanner, userService, membershipService, workoutService, gymProductService);
                break;
                case 3:
                    System.out.println("Exiting the program...");
                break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (choice != 3);

        scanner.close();
    }

    //Login Method
    private static void logInAsUser(Scanner scanner, UserService userService, MembershipService membershipService, WorkoutClassService workoutService, GymProductService gymProductService) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            User user = userService.loginForUser(username, password);
            if (user != null) {
                System.out.println("Login Successful! Welcome " + user.getUserName());
                switch (user.getRole().toLowerCase()) {
                    case "admin":
                        showAdminMenu(scanner, user, userService, membershipService, workoutService, gymProductService);
                    break;
                    case "trainer":
                        showTrainerMenu(scanner, user, membershipService, workoutService, gymProductService);
                    break;
                    case "member":
                        showMemberMenu(scanner, user, userService, membershipService, workoutService, gymProductService);
                    break;
                    default:
                        System.out.println("Unknown role. Please choose Member, Trainer, or Admin");
                    break;
                }
            } else {
                System.out.println("Login Failed! Invalid credentials.");
                logger.warning("Failed login attempt for username: " +  username);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while logging in.");
            e.printStackTrace();
        }
    }

    // Member menu
    private static void showMemberMenu(Scanner scanner, User user, UserService userService, MembershipService membershipService, WorkoutClassService workoutService, GymProductService gymProductService) {
        int memberChoice;
        do{
            System.out.println("\n=== Member Menu ===");
            System.out.println("1. Purchase membership");
            System.out.println("2. View merch");
            System.out.println("3. Browse classes");
            System.out.println("4. View My membership");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()){
            System.out.println("Invalid input. Please enter a valid number");
            scanner.next();
            }
            memberChoice = scanner.nextInt();
            scanner.nextLine();

            switch(memberChoice){
                case 1: // Purchase membership
                    try {
                        System.out.print("Enter the membership type (standard, aluminum, gold):");
                        String membershipType = scanner.nextLine();
                        System.out.print("Enter the monthly price: ");
                        double price = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter the purchase date (YYYY-MM-DD): ");
                        String purchaseDate = scanner.nextLine();
                        int memberId = user.getId();

                        Membership member = new Membership(membershipType, price, memberId,purchaseDate);
                        membershipService.addMembership(member);
                        System.out.println();
                        System.out.println("Membership purchased successfully!");
                    } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                    logger.warning("Database error: " + e.getMessage());
                } 
                break;  
                case 2: //View merch
                    try{
                    System.out.println();
                    List<GymProduct> products = gymProductService.getAllProducts();
                    products.forEach(p -> System.out.println(p));
                    } catch (SQLException e){
                        System.out.println("Error: " + e.getMessage());
                        logger.warning("Database error: " + e.getMessage());
                    }                
                break;
                case 3: //browse all classes
                try {
                    System.out.println();
                    workoutService.getAllClasses().forEach(u -> System.out.println(u));
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                    logger.warning("Database error: " + e.getMessage());
                }
                break;
                case 4: //view membership
                try {
                    Membership myMembership = membershipService.getMembershipByMemberId(user.getId());
                    if (myMembership != null){
                        System.out.println(myMembership);
                    }else {
                        System.out.println("No membership found.");
                    }
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                    logger.warning("Database error: " + e.getMessage());
                } 
                break;
                case 5:
                    System.out.println();
                    System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid input. Please enter a valid number");
            }
        }while (memberChoice !=5);
    }

    // Trainer menu
    private static void showTrainerMenu(Scanner scanner, User user, MembershipService membershipService, WorkoutClassService workoutService, GymProductService gymProductService) {
        int trainerChoice;
        do { 
            System.out.println("\n=== Trainer Menu ===");
            System.out.println("1. Create Class");
            System.out.println("2. Update Class");
            System.out.println("3. Delete Class");
            System.out.println("4. View My Classes");
            System.out.println("5. Purchase Membership");
            System.out.println("6. View Merch");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");
            
        while (!scanner.hasNextInt()){
            System.out.println("Invalid input. Please enter a valid number");
            scanner.next();
        }
        trainerChoice = scanner.nextInt();
        scanner.nextLine();

        switch (trainerChoice) {
            case 1: //create class
                try {
                    System.out.print("Enter the Class Name: ");
                    String classname = scanner.nextLine();
                    System.out.print("Enter the description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter the schedule: ");
                    String schedule = scanner.nextLine();
                    int trainerId = user.getId();

                    WorkoutClass workoutClass = new WorkoutClass(classname, description,trainerId, schedule); 
                    workoutService.createClass(workoutClass);
                    System.out.println("Class added successfully!");
                } catch (Exception e) {
                System.out.println("Error adding class: " + e.getMessage());
                logger.warning("Database error: " + e.getMessage());
            }           
            break;

            case 2: // update - coming soon
                try {
                    System.out.print("Enter Class ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter the new class name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter the new description: ");
                    String newDescription = scanner.nextLine();
                    System.out.print("Enter the new schedule: ");
                    String newSchedule = scanner.nextLine();
                    int rowsUpdated = workoutService.updateClass(newName, newDescription, newSchedule, updateId);
                    if (rowsUpdated == 0) {
                        System.out.println();
                        System.out.println("No class found with that Id");
                    }else {
                        System.out.println();
                        System.out.println("Class Updated Successfully!");
                    }
                } catch (SQLException e){
                System.out.println("Error: " + e.getMessage());
                logger.warning("Database error: " + e.getMessage());
            }
            break;   
            
            case 3: //delete class
                System.out.print("Enter Class Id to delete: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                try {
                    int rowsDeleted = workoutService.deleteClass(id);
                    if (rowsDeleted == 0){
                        System.out.println();
                        System.err.println("No class found with that ID");
                    } else{
                    System.out.println("Class deleted.");
                    }

                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                    logger.warning("Database error: " + e.getMessage());
                }    
            break;
            case 4: //view classes
                try {
                    workoutService.getClassesByTrainerId(user.getId())
                        .forEach(c -> System.out.println(c));
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                    logger.warning("Database error: " + e.getMessage());
                } 
            break; 
            case 5: //Add membership (purchase)  
                try {
                    System.out.print("Enter the membership type (standard, aluminum, gold):");
                    String membershipType = scanner.nextLine();
                    System.out.print("Enter the monthly price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter the purchase date (YYYY-MM-DD): ");
                    String purchaseDate = scanner.nextLine();
                    int memberId = user.getId();

                    Membership member = new Membership(membershipType, price, memberId,purchaseDate);
                    membershipService.addMembership(member);
                    System.out.println();
                    System.out.println("Membership purchased successfully!");
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                    logger.warning("Database error: " + e.getMessage());
                } 
            break;  
            case 6: //View Merch
                try{
                    System.out.println();
                    List<GymProduct> products = gymProductService.getAllProducts();
                    products.forEach(p -> System.out.println(p));
                    }catch (SQLException e){
                        System.out.println("Error: " + e.getMessage());
                        logger.warning("Database error: " + e.getMessage());
                    }                
                break;   
            case 7:
                System.out.println();
                System.out.println("Logging out...");
            break;
            default:
                System.out.println("Invalid input. Please enter a valid number");
        }
    } while (trainerChoice !=7);
}

        

    // Admin Menu
private static void showAdminMenu(Scanner scanner, User user, UserService userService, MembershipService membershipService, WorkoutClassService workoutService, GymProductService gymProductService) {
        int adminChoice;
        do {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. View all users");
            System.out.println("2. Delete User");
            System.out.println("3. View Total Membership Revenue");
            System.out.println("4. Add Merch");
            System.out.println("5. View Merch and Valuation");
            System.out.println("6. View all classes");
            System.out.println("7. Create Workout Class");
            System.out.println("8. Delete Workout Class");
            System.out.println("9. Update Workout Class");
            System.out.println("10. Logout");
            System.out.print("Enter your choice: ");

        while (!scanner.hasNextInt()){
            System.out.println("Invalid input! Please enter a valid number");
            scanner.next();
        }
        adminChoice = scanner.nextInt();
        scanner.nextLine();

        switch(adminChoice) {
            case 1: //view All users
                try {
                    System.out.println();
                    userService.getAllUsers().forEach(u -> System.out.println(u));
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                    logger.warning("Database error: " + e.getMessage());
                }
            break;
            case 2: //delete user
                System.out.print("Enter user Id to delete: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                try {
                    int rowsDeleted = userService.deleteUser(id);
                    if (rowsDeleted == 0){
                        System.out.println();
                        System.err.println("No user found with that ID");
                    } else{
                        System.out.println();    
                        System.out.println("User deleted.");
                        logger.warning("Admin deleted user with ID: " + id);
                    }

                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                    logger.warning("Database error: " + e.getMessage());
                }    
            break;
            case 3: //View total membership revenue
                try {
                    System.out.println();
                    double revenue = membershipService.getTotalRevenue();
                    System.out.println("Total Membership Revenue: $" + revenue);
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                    logger.warning("Database error: " + e.getMessage());
                }
            break;
            case 4: //add Merch
                System.out.print("Enter the product name: ");
                String productName = scanner.nextLine();
                System.out.print("Enter the product type: ");
                String productType = scanner.nextLine();
                System.out.print("Enter the product price: ");
                double productPrice = scanner.nextDouble();
                System.out.print("Enter the product quantity: ");
                int productQuantity = scanner.nextInt();
                scanner.nextLine(); //Consume newLine
                GymProduct gymProduct = new GymProduct(productName, productType, productPrice, productQuantity);
                try{
                    gymProductService.addNewProduct(gymProduct);
                    System.out.println();
                    System.out.println("Product added successfully");
                }catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                    logger.warning("Database error: " + e.getMessage());
                }
            break;
            case 5: //View Merch and total Valuation
                try{
                    System.out.println();
                    List<GymProduct> products = gymProductService.getAllProducts();
                    products.forEach(p -> System.out.println(p));
                    double totalValuation = 0;
                    for (GymProduct product : products){
                        totalValuation += product.getPrice() * product.getQuantity();
                    }          
                    System.out.println();
                    System.out.printf("Total Valuation of all products: $%.2f%n", totalValuation);          
                }catch(SQLException e){
                    System.out.println("Error: " + e.getMessage());
                    logger.warning("Database error: " + e.getMessage());
                }
            break;
            case 6: //View all classes 
                try {
                    System.out.println();
                    workoutService.getAllClasses().forEach(u -> System.out.println(u));
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                    logger.warning("Database error: " + e.getMessage());
                }
            break;
            case 7: //create workout class 
                try {
                    System.out.print("Enter the Class Name: ");
                    String classname = scanner.nextLine();
                    System.out.print("Enter the description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter the schedule: ");
                    String schedule = scanner.nextLine();
                    System.out.print("Enter Trainer ID to assign this class to: ");
                    int trainerId = scanner.nextInt();
                    scanner.nextLine();

                    WorkoutClass workoutClass = new WorkoutClass(classname, description, trainerId, schedule); 
                    workoutService.createClass(workoutClass);
                    System.out.println("Class added successfully!");
                } catch (Exception e) {
                System.out.println("Error adding class: " + e.getMessage());
                logger.warning("Database error: " + e.getMessage());
            }           
            break;
            case 8: //Delete Class
                System.out.print("Enter Class Id to delete: ");
                int classId = scanner.nextInt();
                scanner.nextLine();
                try {
                    int rowsDeleted = workoutService.deleteClass(classId);
                    if (rowsDeleted == 0){
                        System.out.println();
                        System.err.println("No class found with that ID");
                    } else{
                    System.out.println("Class deleted.");
                    }

                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                    logger.warning("Database error: " + e.getMessage());
                }    
            break;
            case 9: //update class by class Id
                try {
                    System.out.print("Enter Class ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter the new class name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter the new description: ");
                    String newDescription = scanner.nextLine();
                    System.out.print("Enter the new schedule: ");
                    String newSchedule = scanner.nextLine();
                    int rowsUpdated = workoutService.updateClass(newName, newDescription, newSchedule, updateId);
                    if (rowsUpdated == 0) {
                        System.out.println();
                        System.out.println("No class found with that Id");
                    }else {
                        System.out.println();
                        System.out.println("Class Updated Successfully!");
                    }
               } catch (SQLException e){
                System.out.println("Error: " + e.getMessage());
                logger.warning("Database error: " + e.getMessage());
               }
            break;
            case 10:
                System.out.println();
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid choice");                   
        }
    }while (adminChoice != 10);
}

    // Add New User
    private static void addNewUser(Scanner scanner, UserService userService) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter role (Admin/Trainer/Member): ");
        String role = scanner.nextLine();
        System.out.println();

        User user = new User(username, password, email, address, phone, role);
        try {
            userService.registerUser(user);
            System.out.println("User added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
            logger.warning("Database error: " + e.getMessage());
        }
    }
}
