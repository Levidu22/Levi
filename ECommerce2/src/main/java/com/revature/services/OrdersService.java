package com.revature.services;

import com.revature.models.Orders;
import com.revature.repos.OrdersDAO;

import java.util.List;

public class OrdersService {


    private OrdersDAO ordersDAO;

    public OrdersService(OrdersDAO ordersDAO){
        this.ordersDAO=ordersDAO;
    }
    public List<Orders> getAllOrders(){
        return ordersDAO.getAll();
    }
    public List<Orders> getUsersOrders (int userId){
        return ordersDAO.getAllByUserId(userId);

    }


}