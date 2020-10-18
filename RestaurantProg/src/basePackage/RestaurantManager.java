package basePackage;

import java.sql.*;
import java.util.*;

public class RestaurantManager {

    //Show Restaurant Primary Information: 1- Restaurant Name   2- Send Cost   3-Restaurant Food Type
    public ArrayList<String> showRestaurantPrimaryInfo(Connection connection, int codeArea) throws SQLException {
        Statement statement=connection.createStatement();

        String query="select restaurants.restaurant.restaurantName,restaurants.restaurant.sendCost ,restaurants.food.foodType from restaurants.restaurant " +
                "inner join restaurants.restaurant_has_food on restaurants.restaurant.restaurantName=restaurants.restaurant_has_food.Restaurant_restaurantName and restaurants.restaurant.restaurantCodeArea='"+String.valueOf(codeArea)+"'"+
                "inner join restaurants.food on restaurants.restaurant_has_food.Food_foodName=restaurants.food.foodName " +
                "group by( restaurants.restaurant.restaurantName);";

        ResultSet resultSet=statement.executeQuery(query);

        ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
        System.out.println(resultSetMetaData.getColumnLabel(1)+",  "+resultSetMetaData.getColumnLabel(2)+",  "+resultSetMetaData.getColumnLabel(3));

        ArrayList<String> restaurantList=new ArrayList<>();
        while(resultSet.next()) {
            System.out.println(resultSet.getString("restaurantName") + ", " + resultSet.getInt("sendCost") + ", " + resultSet.getString("foodType"));
            restaurantList.add(resultSet.getString("restaurantName"));
        }

        resultSet.close();
        statement.close();

        return restaurantList;
    }


    // Show Food Information : 1-FoodName    2-Food Price
    public ArrayList<Order> showRestaurantFoodInfo(Connection connection, String selectRestaurantName) throws SQLException {
        Statement statement=connection.createStatement();

        String query="select restaurants.food.foodName , restaurants.food.foodPrice from restaurants.food\n" +
                "inner join restaurants.restaurant_has_food on restaurants.food.foodName=restaurants.restaurant_has_food.Food_foodName\n" +
                "inner join restaurants.restaurant on restaurants.restaurant.restaurantName=restaurants.restaurant_has_food.Restaurant_restaurantName and restaurants.restaurant.restaurantName='"+selectRestaurantName+"';";

        ResultSet resultSet=statement.executeQuery(query);

        ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
        System.out.println(resultSetMetaData.getColumnLabel(1)+",  "+resultSetMetaData.getColumnLabel(2));

       // ArrayList<String> foodList=new ArrayList<>();
        ArrayList<Order> foodInfo=new ArrayList<>();

        while(resultSet.next()) {
            System.out.println(resultSet.getString("foodName") + "   |   " + resultSet.getInt("foodPrice"));
           // foodList.add(resultSet.getString("foodName"));

            Order ord=new Order(resultSet.getString("foodName"),resultSet.getInt("foodPrice"));
            foodInfo.add(ord);
        }

        resultSet.close();
        statement.close();

        return foodInfo;
    }


    //Show Restaurant Primary Information with limited food type: 1- Restaurant Name   2- Send Cost   3-Restaurant Food Type
    public ArrayList<String> showRestaurantWithLimitedOnFoodType(Connection connection, int codeArea , String foodType) throws SQLException {
        Statement statement=connection.createStatement();

        String query="select restaurants.restaurant.restaurantName,restaurants.restaurant.sendCost ,restaurants.food.foodType from restaurants.restaurant " +
                "inner join restaurants.restaurant_has_food on restaurants.restaurant.restaurantName=restaurants.restaurant_has_food.Restaurant_restaurantName and restaurants.restaurant.restaurantCodeArea='"+String.valueOf(codeArea)+"'"+
                "inner join restaurants.food on restaurants.restaurant_has_food.Food_foodName=restaurants.food.foodName and restaurants.food.foodType='"+foodType+"'" +
                "group by( restaurants.restaurant.restaurantName);";

        ResultSet resultSet=statement.executeQuery(query);

        ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
        System.out.println(resultSetMetaData.getColumnLabel(1)+",  "+resultSetMetaData.getColumnLabel(2)+",  "+resultSetMetaData.getColumnLabel(3));

        ArrayList<String> restaurantList=new ArrayList<>();
        while(resultSet.next()) {
            System.out.println(resultSet.getString("restaurantName") + ", " + resultSet.getInt("sendCost") + ", " + resultSet.getString("foodType"));
            restaurantList.add(resultSet.getString("restaurantName"));
        }

        resultSet.close();
        statement.close();

        return restaurantList;
    }

    /*
    public long calculatePriceOfNumberOfFood(Connection connection,Order order) throws SQLException {
        Statement statement=connection.createStatement();

        String query="select restaurants.food.foodPrice from restaurants.food where restaurants.food.foodName='"+order.getFoodName()+"';";

        ResultSet resultSet=statement.executeQuery(query);

        order.setPriceOfFood(resultSet.getInt("foodPrice"));

        long priceOfNumberOfFood=(order.getNumbersOfFood())*(order.getPriceOfFood());

        resultSet.close();
        statement.close();

        return priceOfNumberOfFood;
    }*/


}
