package com.revature.controllers;

import com.revature.models.Orders;
import com.revature.models.OrdersType;
import com.revature.services.OrdersService;

import java.util.List;
import java.util.Scanner;

public class OrdersController {

    private OrdersService ordersService;
    private Scanner scan;

    public OrdersController(OrdersService ordersService, Scanner scan){

        this.ordersService = ordersService;
        this.scan = scan;

    }
    public void viewAllOrdersHandler(){
        List<Orders> allOrders = ordersService.getAllOrders();

        System.out.println("All Orders: ");
        for(Orders o: allOrders){
            System.out.println(o);
        }
    }
    public void createNewOrdersHandler(int userId) {
        System.out.println("please enter 1 to make a PENDING Order, 2 to make a SHIPPED Order ");
        String choice = scan.nextLine();

        while (!(choice.equals("1") || choice.equals("2")) ) {
            System.out.println("invalid option please pick 1 or 2");
            choice = scan.nextLine();
        }
      OrdersType type = choice.equals("1")? OrdersType.PENDING : OrdersType.SHIPPED;
        System.out.println("enter the Total price");
        String totalString = scan.nextLine();
        Double total = null;
      do {
          try {
              total = Double.parseDouble(totalString);
          } catch (NumberFormatException e) {
              System.out.println("Invalid value fot Total, enter a new one");
              totalString = scan.nextLine();

          }
      }while(total == null);
      try {

          Orders o =  ordersService.createOrders(userId,type,total);
        System.out.println("Created New Order: ");
        System.out.println(o);

      } catch (IllegalArgumentException e) {
          System.out.println("Could not create an Order");
      }


    }

    public void viewUsersOrdersHandler(int userId){
        List<Orders> allOrders = ordersService.getUsersOrders(userId);

        System.out.println("All Orders: ");
        for(Orders o: allOrders){
            System.out.println(o);
        }
    }




}