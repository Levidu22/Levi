package com.revature.models;

public class Orders {

    private int OrdersId;

    private double Total;


    private OrdersType type;

    private int userId;
    // This final field is in charge of linking this account to a specific user
    // We'll talk in detail about why this is here when we get to SQL

    // Constructors
    private static int OrdersIdCounter = 10000;

    public Orders(double Total, OrdersType type, int userId){
        this.Total = Total;
        this.type = type;
        this.userId = userId;

        this.OrdersId = OrdersIdCounter;
        OrdersIdCounter++;
    }
    public Orders(){

    }

    // Getters and Setters
    public int getOrdersId() {
        return OrdersId;
    }

    public void setOrdersId(int ordersId) {
        this.OrdersId = ordersId;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        this.Total = total;
    }

    public OrdersType getType() {
        return type;
    }

    public void setType(OrdersType type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "OrdersId=" + OrdersId +
                ", Total=" + Total +
                ", type=" + type +
                ", userId=" + userId +
                '}';
    }
}