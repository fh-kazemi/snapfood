package basePackage;

public class Restaurant {
    public String restaurantName;
    public int restaurantCodeArea;
    public int sendCost;
    public int numbersOfFoods;

    public Restaurant(String restaurantName,int restaurantCodeArea,int sendCost){
        this.restaurantName=restaurantName;
        this.restaurantCodeArea=restaurantCodeArea;
        this.sendCost=sendCost;
    }

    public String getRestaurantName() {
        return this.restaurantName;
    }

    public int getRestaurantCodeArea() {
        return this.restaurantCodeArea;
    }

    public int getSendCost() {
        return this.sendCost;
    }
}
