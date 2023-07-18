package Repositories;


import JdbcConn.JdbcConn;
import Resources.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static JdbcConn.JdbcConn.openConn;


public class UserRepo {


   public void addUser(User user)  {

       openConn();
       try {
           String sql="INSERT INTO fonebook_management.user (name, password, phone, address) VALUES (?, ?, ?, ?)";
           PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
           statement.setString(1, user.getName());
           statement.setString(2, user.getPassword());
           statement.setString(3, user.getPhone());
           statement.setString(4, user.getAddress());
           statement.executeUpdate();
           JdbcConn.statement.close();

       } catch (SQLException e) {
           System.out.println("Unable to register user check inputs and try again");
       }



   }

    public User getUserByName(String name) throws SQLException {
        User user = new User();
        openConn();
        String sql = "SELECT id, name, password, phone, address FROM fonebook_management.user WHERE name = ? ";
        PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);
        statement.setString(1,name);

        ResultSet result = statement.executeQuery();

        if (result.next()) {
            user.setName(result.getString("name"));
            user.setPassword(result.getString("password"));
            user.setPhone(result.getString("phone"));
            user.setAddress(result.getString("address"));
            user.setId(result.getLong("id"));

            }
        JdbcConn.connection.close();
        return user;

    }
    public List<User> getUsers() throws SQLException {
        ArrayList<User> userList = new ArrayList<>();
        openConn();
        String sql = "SELECT * FROM fonebook_management.user ";
        PreparedStatement statement = JdbcConn.connection.prepareStatement(sql);

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            User user = new User();
            user.setName(result.getString("name"));
            user.setPassword(result.getString("password"));
            user.setPhone(result.getString("phone"));
            user.setAddress(result.getString("address"));
            user.setId(result.getLong("id"));

            userList.add(user);

        }
        JdbcConn.connection.close();
        return userList;

    }


    }


