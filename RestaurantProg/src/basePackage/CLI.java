package basePackage;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CLI {
    RestaurantManager restaurantManager= new RestaurantManager();
    private Connection connection;
    // public ArrayList<Customer> customers=new ArrayList<>();

    Scanner input = new Scanner(System.in);

    public CLI(Connection connection) {
        try {
            this.restaurantManager= new RestaurantManager();
            this.connection = connection;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public long getMobileNumber() {
        long mobileNumber = 0;
        System.out.println("Enter Mobile Number: ");

        while (!input.hasNextLong()) {
            System.out.printf("Your input was \"%s\". Please enter a number:%n", input.next());
        }

        mobileNumber = input.nextLong();
        System.out.println("Mobile Number Registered!\n");

        return mobileNumber;
    }

    public int getCustomerCodeArea() {
        int codeArea = 0;
        System.out.println("Enter Your CodeArea: ");

        while (!input.hasNextInt()) {
            System.out.printf("Your input was \"%s\". Please enter a number:%n", input.next());
        }

        codeArea = input.nextInt();
        System.out.println("Code Area Registered!\n");
        return codeArea;
    }

    public String getCustomerSelectedRestaurant(ArrayList<String> restaurantList) {
        String selectRestaurantName;
        boolean checkRestaurantName = false;
        do {
            System.out.println("Select Restaurant Name: ");
            selectRestaurantName = input.next();
            for (String item : restaurantList) {
                if (item.equalsIgnoreCase(selectRestaurantName)) {
                    checkRestaurantName = true;
                    break;
                }
            }
            if (!checkRestaurantName) {
                System.out.print("This Name is invalid. Try again!  ");
            }
        } while (checkRestaurantName == false);
        System.out.println("Restaurant Name Selected!\n");
        return selectRestaurantName;
    }


    public ArrayList<Order> customerSelectFood(ArrayList<Order> foodList){
        ArrayList<Order> orders=new ArrayList<>();
        boolean checkContinue=false;
        do{
            String foodName;
            int priceOfFood=0;
            boolean checkFoodName=false;
            do{
                System.out.println("Enter food name:");
                foodName=input.next();
                for (int i=0;i<foodList.size();i++){
                   if(foodList.get(i).getFoodName().equalsIgnoreCase(foodName)){
                        priceOfFood=foodList.get(i).getPriceOfFood();
                        checkFoodName=true;
                        break;
                    }
                }
                if(!checkFoodName){
                    System.out.print("This Name is invalid. Try again!  ");
                }
            }while (!checkFoodName);

            System.out.println("Enter numbers of food:");
            while (!input.hasNextInt()) {
                System.out.printf("Your input was \"%s\". Please enter a number:%n", input.next());
            }
            int numbersOfFood=input.nextInt();

            boolean checkThereIsFood=false;
            for(int i=0;i<orders.size();i++){
                if(orders.get(i).getFoodName().equalsIgnoreCase(foodName)){
                    orders.get(i).setNumbersOfFood(orders.get(i).getNumbersOfFood()+numbersOfFood);
                    checkThereIsFood=true;
                    break;
                }
            }
            if(!checkThereIsFood) {
                Order ord = new Order(foodName, numbersOfFood, priceOfFood);
                orders.add(ord);
            }

            System.out.println("Do you want to select another Food? .. Yes or No?");
            String answer;
            boolean checkAnswerToContinue=false;
            do {
                answer=input.next();
                if(answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no")){
                   checkAnswerToContinue=true;
                }else{
                    System.out.print("Type Correctly:  Yes or No  ");
                }
            }while (!checkAnswerToContinue);
            if(answer.equalsIgnoreCase("no")){
                checkContinue=true;
            }
        }while (!checkContinue);

        return orders;
    }

    public int calculateCustomerOrder(ArrayList<Order> orders){
        int sum=0;
        for (int i=0;i<orders.size();i++){
            sum+=orders.get(i).getNumbersOfFood()*orders.get(i).getPriceOfFood();
        }
        return sum;
    }

    public void showCustomerOrders(ArrayList<Order> orders){
        System.out.println("\n\n--------------Purchase Receipt--------------");
        System.out.println("FoodName  |  numbersOfFood  |  Price");
        for ( Order ord:orders) {
            System.out.println(ord.getFoodName()+"              "+ord.getNumbersOfFood()+"              "+ord.getNumbersOfFood()*ord.getPriceOfFood()+"     Rials");
        }
        System.out.println("------------------------------------------------");
        System.out.print("Total Amount:    "+calculateCustomerOrder(orders)+"    Rials\n\n");
    }

    public ArrayList<Order> deleteFoodFromOrders(ArrayList<Order> orders){
        boolean checkToContinue=false;
        String answerToDelete;
        System.out.println("Do you want to delete food from your order?  Yes or No..");
        answerToDelete = input.next();
        do {
            if (answerToDelete.equalsIgnoreCase("yes")) {
                boolean checkDeletedFood = false;
                do {
                    System.out.println("Enter foodName for delete from your foodList:");
                    String deletedFood = input.next();
                    for (Order ord : orders) {
                        if (ord.getFoodName().equalsIgnoreCase(deletedFood)) {
                            boolean checkNumbersDeletedFood=false;
                            System.out.println("How many to delete?");
                            do {
                                while (!input.hasNextInt()) {
                                    System.out.printf("Your input was \"%s\". Please enter a number:%n", input.next());
                                }
                                int numbersOfFoodToDelete = input.nextInt();

                                if (numbersOfFoodToDelete < ord.getNumbersOfFood()) {
                                    ord.setNumbersOfFood(ord.getNumbersOfFood() - numbersOfFoodToDelete);
                                    showCustomerOrders(orders);
                                    checkDeletedFood = true;
                                    break;

                                } else if (numbersOfFoodToDelete == ord.getNumbersOfFood()) {
                                    orders.remove(ord);
                                    showCustomerOrders(orders);
                                    checkDeletedFood = true;
                                    break;

                                } else {
                                    checkNumbersDeletedFood=true;
                                    System.out.println("Numbers Entered Is Invalid! Try Again: ");
                                }
                            }while(checkNumbersDeletedFood);
                        }
                    }
                    if (!checkDeletedFood) {
                        System.out.println("This Name is invalid for deleting. Try Again!");
                    }
                } while (!checkDeletedFood);
            }else{
                return orders;
            }
            System.out.println("Do you want to delete another? Yes or No..");
            answerToDelete = input.next();
            if(answerToDelete.equalsIgnoreCase("no")){
                checkToContinue=true;
            }
        }while (!checkToContinue);
        showCustomerOrders(orders);
        return orders;
    }


    public Customer registerCustomerOrders(ArrayList<Order> order,long mobileNumber,int customerCodeArea,String selectRestaurantName) throws SQLException {

        System.out.print("\nEnter LastName:  ");
        String lastName=input.next();
        System.out.print("\nEnter PostalCode:  ");
        long postalCode=input.nextInt();

        Statement statement=connection.createStatement();

        String query="insert into restaurants.customer(restaurants.customer.mobileNumber,restaurants.customer.customerName,restaurants.customer.cuctomerCodeArea,restaurants.customer.postalCode,restaurants.customer.Restaurant_restaurantName)" +
                        "value('"+mobileNumber+"','"+lastName+"','"+customerCodeArea+"','"+postalCode+"','"+selectRestaurantName+"');";

        statement.executeUpdate(query);

        Customer customer=new Customer(mobileNumber,customerCodeArea,lastName,postalCode);

        System.out.println("\n\nYour personal information has been registered in our database.");
        showCustomerOrders(order);
        System.out.println("--------------------------------------------------------------");
        System.out.println("Orders SuccessFully Registred at     : "+customer.getTimeStamp());
        System.out.println("--------------------------------------------------------------");

        return customer;
    }

    public void writeToFileCustomerInfo(Customer customer) throws IOException {
        FileOutputStream file=new FileOutputStream("E:\\RestaurantOutput\\"+customer.getCustomerName()+".txt");
        ObjectOutputStream outStream=new ObjectOutputStream(file);
        outStream.writeObject(customer);
        outStream.close();
    }

    public void readFileRestaurantInfo(Connection connection) throws IOException, ClassNotFoundException, SQLException {
        FileInputStream file=new FileInputStream("E:\\RestaurantOutput\\RestaurantInfo.txt");
        ObjectInputStream inStream=new ObjectInputStream(file);

        while(inStream.available()>0) {
            if (inStream.readObject() instanceof Restaurant) {
                Restaurant restaurant = (Restaurant) inStream.readObject();
                Statement statement=connection.createStatement();

                String query="insert into restaurants.restaurant(restaurants.restaurant.restaurantName,restaurants.restaurant.restaurantCodeArea,restaurants.restaurant.sendCost)\n" +
                        "value('"+restaurant.getRestaurantName()+"','"+restaurant.getRestaurantCodeArea()+"','"+restaurant.getSendCost()+"');";

                statement.executeUpdate(query);
                statement.close();

            }else if(inStream.readObject() instanceof Food){
                Food food=(Food) inStream.readObject();
                Statement statement=connection.createStatement();

                String query="insert into restaurants.food(restaurants.food.foodName,restaurants.food.foodType,restaurants.food.foodPrice)\n" +
                        "value('"+food.getFoodName()+"','"+food.getFoodType()+"','"+food.getFoodPrice()+"');";

                statement.executeUpdate(query);
                statement.close();
            }
        }
        System.out.println("Restaurant Information Set successfully in to Database.");
    }


    public void showOptions() throws SQLException, IOException, ClassNotFoundException {
        //readFileRestaurantInfo(connection);
        boolean checkMenu=false;
        do {
            System.out.println("\n\n****************** Restaurant Program ******************");
            System.out.println("Welcome! \n");

            long mobileNumber = getMobileNumber();
            int customerCodeArea = getCustomerCodeArea();

            String selectRestaurantName;
            ArrayList<Order> foodList;
            boolean checkShowRestaurantAgain;
            do {
                ArrayList<String> restaurantList = new ArrayList<>();
                do {
                    checkShowRestaurantAgain = false;
                    System.out.println("Select Options:");
                    System.out.println("1-Show All Restaurant in your Area\n2-Show Restaurant With Limited Food Type");
                    System.out.print("Select 1 or 2 :  ");
                    String answerShowRestaurant = input.next();

                    boolean checkHowShowRestaurant = true;
                    do {
                        if (answerShowRestaurant.equalsIgnoreCase("1")) {
                            System.out.println("----------------Restaurants in your Area:");
                            restaurantList = restaurantManager.showRestaurantPrimaryInfo(connection, customerCodeArea);
                            checkHowShowRestaurant = false;

                        } else if (answerShowRestaurant.equalsIgnoreCase("2")) {
                            System.out.println("Which type of Restaurant do you want to show? Irani or FastFood ..");
                            String whichType = input.next();
                            System.out.println("----------------" + whichType + " Restaurants in your Area:");
                            restaurantList = restaurantManager.showRestaurantWithLimitedOnFoodType(connection, customerCodeArea, whichType);
                            checkHowShowRestaurant = false;

                        } else {
                            System.out.println("Incorrect Selection. Try Again: ");
                        }
                    } while (checkHowShowRestaurant);

                    System.out.println("---------------- <<Previous Menu");
                    System.out.println("Do you want to go Previous Menu?  Yes or No..");
                    String answerToPreviousMenu;
                    boolean checkAnswer = false;
                    do {
                        answerToPreviousMenu = input.next();
                        if (answerToPreviousMenu.equalsIgnoreCase("yes") || answerToPreviousMenu.equalsIgnoreCase("no")) {
                            checkAnswer = true;
                        } else {
                            System.out.println("Type correctly  Yes or No .. Try Again:");
                        }
                    } while (!checkAnswer);

                    if (answerToPreviousMenu.equalsIgnoreCase("yes")) {
                        checkShowRestaurantAgain = true;
                    }
                }while (checkShowRestaurantAgain);

                System.out.println("----------------Your Selection Rstaurant:");
                selectRestaurantName = getCustomerSelectedRestaurant(restaurantList);

                System.out.println("----------------Foods in <<" + selectRestaurantName + ">> Restaurant:");
                foodList = restaurantManager.showRestaurantFoodInfo(connection, selectRestaurantName);

                System.out.println("---------------- <<Previous Menu");
                System.out.println("Do you want to go Previous Menu?  Yes or No..");
                String answerToPreviousMenu;
                boolean checkAnswer = false;
                do {
                    answerToPreviousMenu = input.next();
                    if (answerToPreviousMenu.equalsIgnoreCase("yes") || answerToPreviousMenu.equalsIgnoreCase("no")) {
                        checkAnswer = true;
                    } else {
                        System.out.println("Type correctly  Yes or No .. Try Again:");
                    }
                } while (!checkAnswer);

                if (answerToPreviousMenu.equalsIgnoreCase("yes")) {
                    checkShowRestaurantAgain = true;
                }
            } while (checkShowRestaurantAgain);

            System.out.println("----------------Select Foods :");
            ArrayList<Order> orders = customerSelectFood(foodList);
            showCustomerOrders(orders);

            System.out.println("----------------Delete Food :");
            orders = deleteFoodFromOrders(orders);

            System.out.println("----------------Registered Orders :");
            System.out.println("Do you want to register your orders?  Yes or No..");
            String answerRegister;
            boolean checkAnswerRegister=false;
            do {
                answerRegister = input.next();
                if (answerRegister.equalsIgnoreCase("yes") || answerRegister.equalsIgnoreCase("no")) {
                    checkAnswerRegister = true;
                }else{
                    System.out.println("Type correctly  Yes or No .. Try Again:");
                }
            }while (!checkAnswerRegister);
            if(answerRegister.equalsIgnoreCase("yes")) {
                Customer customer=registerCustomerOrders(orders, mobileNumber, customerCodeArea, selectRestaurantName);
                writeToFileCustomerInfo(customer);
                break;
            }else{
                checkMenu=true;
            }
        }while (checkMenu);
    }



}
