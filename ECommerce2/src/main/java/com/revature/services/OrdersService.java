package com.revature.services;

import com.revature.models.Orders;
import com.revature.models.OrdersType;
import com.revature.repos.OrdersDAO;

import java.util.List;

public class OrdersService {


    private OrdersDAO ordersDAO;

    public OrdersService(OrdersDAO ordersDAO){
        this.ordersDAO=ordersDAO;
    }
    public Orders createOrders(int userId, OrdersType type,double total){

        if (total <0){
            throw new IllegalArgumentException("total cant be negative");

            }
        Orders ordersToBeSaved =new Orders(total,type,userId);
        return ordersDAO.create(ordersToBeSaved);
    }
    public List<Orders> getAllOrders(){
        return ordersDAO.getAll();
    }
    public List<Orders> getUsersOrders (int userId){
        return ordersDAO.getAllByUserId(userId);

    }


}