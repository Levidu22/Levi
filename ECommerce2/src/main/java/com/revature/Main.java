package com.revature;

import com.revature.controllers.OrdersController;
import com.revature.controllers.UserController;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repos.OrdersDAO;
import com.revature.repos.OrdersDAOImpl;
import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOImpl;
import com.revature.services.OrdersService;
import com.revature.services.UserService;

import java.util.Scanner;

public class Main {
    /*
    Today we're going to get started building out a simple banking application. It will have a couple
    basic features such as registering, logging in, creating an account, viewing all accounts, depositing/
    withdrawing/transferring for those accounts.

    Application has 3 major layers and a model layer
    Models
        - Define what the data looks like
    Repo/DAO Layer
        - In charge of directly interfacing with the data
    Service Layer
        - In charge of any business logic
    Controller Layer
        - This will be in charge of display logic (typically this layer handles web traffic)

    This main class is in charge of starting and running the application so we'll hold some menu logic here
    and allow a user to interface with the application from here
     */

    public static void main(String[] args) {

        // Define some variables and make instances of our controllers, services and daos
        UserDAO userDAO = new UserDAOImpl();
        //     for(User u: userDAO2.getAll()){
        //        System.out.println(u);
        //   }
        Scanner scan = new Scanner(System.in);

        //    System.out.println("enter an email: ");
        //  String emailEntered = scan.nextLine();
        // System.out.println(userDAO2.getUserByemail(emailEntered));

        //
        UserService userService = new UserService(userDAO);


        UserController userController = new UserController(userService, scan);

        // Here's we'll do some simple menu logic to allow the user to interact with the application
        // Login, register and view all users

        OrdersDAO ordersDAO= new OrdersDAOImpl();
        OrdersService ordersService = new OrdersService(ordersDAO);

        OrdersController ordersController = new OrdersController(ordersService, scan);



        boolean running = true;
        // I want to keep track of who is logged in
        User loggedInUser = null;

        while (running){
            if (loggedInUser == null){
                // Sign in or register

                System.out.println("Welcome to our ECommerce application! Please press 1 to register, press 2 to login " +
                        "or press q to quit");

                String input = scan.nextLine();

                // If the input is not a q, 1, or 2 it is invalid so we need a new input
                // An input is valid if it is q OR 1 OR 2
                while(!(input.equals("q") || input.equals("1") || input.equals("2"))){
                    System.out.println("Invalid input. Please enter a new value: ");
                    input = scan.nextLine();
                }

                if (input.equals("1")){
                    // Register Logic
//                    System.out.println("Register");
                    loggedInUser = userController.registerNewUser();

                } else if (input.equals("2")){

                    loggedInUser = userController.loginUser();

                } else if (input.equals("q")){
                    System.out.println("Quitting application!");
                    break; // Cancels out of the while loop and allows the program to end
                }


            } else {
                // This means we're logged in so we'll have application logic here
                // There isn't a lot of logic we can do right now. Here the Customers cannot do anything but the
                // admin should be able to view all users
                if (loggedInUser.getRole() == Role.USER){
                    System.out.println("Thanks for signing in, there is not for now current Customer functionality. Logging out!");
                    loggedInUser = null;
                } else{
                    // Logged in user is an Admin
                    System.out.println("Please enter a choice. Press 1 to view all users, press 2 to view all Orders" +
                            " or q to logout");

                    String choice = scan.nextLine();

                    while (!(choice.equals("1") || choice.equals("q") || choice.equals("2"))){
                        System.out.println("Invalid choice, please enter a new choice");
                        choice = scan.nextLine();
                    }

                    // View all users or logout

                    if (choice.equals("1")){
                        // View all users logic
                        userController.getAllUsersHandler();
                    } else if (choice.equals("2")){
                        ordersController.viewAllOrdersHandler();


                    }else{
                        loggedInUser = null;
                    }
                }
            }
        }
    }
}