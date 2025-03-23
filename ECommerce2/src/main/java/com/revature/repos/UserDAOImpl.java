package com.revature.repos;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public User getUserByEmail(String email) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "Select * from Users where email = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);


            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setRole(Role.valueOf(rs.getString("role")));
                return u;

            }


        } catch (SQLException e) {
            System.out.println("Could not retrieve user by email");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User create(User obj) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO users (first_name,last_name, email, password, role) VALUES " +
                    "(?, ?,?, ?,?) Returning *;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, obj.getFirstName());
            ps.setString(2, obj.getLastName());
            ps.setString(3, obj.getEmail());
            ps.setString(4, obj.getPassword());
            ps.setString(5, obj.getRole().toString());


            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setRole(Role.valueOf(rs.getString("role")));

                return u;
            }
        } catch (SQLException e) {
            System.out.println("Could not save user");
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();
        Connection conn = ConnectionUtil.getConnection();
        String sql = "Select * from Users;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setRole(Role.valueOf(rs.getString("role")));
                allUsers.add(u);


            }

        } catch (SQLException e) {
            System.out.println("could not get all users");
            e.printStackTrace();
        }


        return allUsers;
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public User update(User obj) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

}