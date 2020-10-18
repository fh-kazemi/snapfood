package basePackage;

public class Food {
    private String foodName;
    private String foodType;
    private int foodPrice;


    public Food(String foodName,String foodType,int foodPrice){
        this.foodName=foodName;
        this.foodType=foodType;
        this.foodPrice=foodPrice;
    }

    public String getFoodName() {
        return this.foodName;
    }

    public String getFoodType() {
        return this.foodType;
    }

    public int getFoodPrice() {
        return this.foodPrice;
    }
}
