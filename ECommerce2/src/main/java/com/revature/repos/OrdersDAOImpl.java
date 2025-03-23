package com.revature.repos;

import com.revature.models.Orders;
import com.revature.models.OrdersType;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOImpl implements OrdersDAO {

    @Override
    public List<Orders> getAllByUserId(int userId) {
        List<Orders> allOrders = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection()){
            String sql = "Select * from Orders where user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,userId);

            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()){
                Orders o = new Orders();
                o.setOrdersId(rs.getInt("order_id"));
                o.setTotal(rs.getDouble("total_price"));
                o.setType(OrdersType.valueOf(rs.getString("status")));
                o.setUserId(rs.getInt("user_id"));


                allOrders.add(o);

            }

        } catch (SQLException e) {
            System.out.println("Could not retrieve all Orders");
            e.printStackTrace();
        }
        return List.of();


    }

    @Override
    public Orders create(Orders obj) {
        try (Connection conn = ConnectionUtil.getConnection()){
            String sql = "insert into Orders (total_price, status, user_id) values (?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1,obj.getTotal());
            ps.setString(2,obj.getType().toString());
            ps.setInt(3,obj.getUserId());
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Orders o = new Orders();
                o.setOrdersId(rs.getInt("order_id"));
                o.setTotal(rs.getDouble("total_price"));
                o.setType(OrdersType.valueOf("status"));
                o.setUserId(rs.getInt("user_id"));
                return o;
            }

        } catch (SQLException e) {
            System.out.println("Could not create Orders");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Orders> getAll() {
        List<Orders> allOrders = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection()){
            String sql = "Select * from Orders";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                Orders o = new Orders();
                o.setOrdersId(rs.getInt("order_id"));
                o.setTotal(rs.getDouble("total_price"));
                o.setType(OrdersType.valueOf(rs.getString("status")));
                o.setUserId(rs.getInt("user_id"));


                allOrders.add(o);

            }

        } catch (SQLException e) {
            System.out.println("Could not retrieve all Orders");
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public Orders getById(int id) {
        return null;
    }

    @Override
    public Orders update(Orders obj) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}