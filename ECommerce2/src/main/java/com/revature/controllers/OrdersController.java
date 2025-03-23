package com.revature.controllers;

import com.revature.models.Orders;
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
    public void viewUsersOrdersHandler(int userId){
        List<Orders> allOrders = ordersService.getUsersOrders(userId);

        System.out.println("All Orders: ");
        for(Orders o: allOrders){
            System.out.println(o);
        }
    }
}